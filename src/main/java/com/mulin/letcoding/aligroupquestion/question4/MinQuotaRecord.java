package com.mulin.letcoding.aligroupquestion.question4;


import java.io.*;
import java.util.TreeMap;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by
 * @author mulin
 * @date 2018/8/29
 * @description:
 * 假设本地有一个文件夹，文件夹下面有若干文件（文件数大于50小于100），文件的存储格式是文本格式（后缀名是.txt)，文件的大小每个文件不会超过100k。
 * 文件格式如下：
 * 2000102，100，98.3
 * 2000103，101，73.3
 * 2000104，102，98.3
 * 2000105，100，101.3
 * 2000106，101，45.3......
 * 文件格式说明：文件每行都由三列构成，第一列是一个id，第二列是分组groupId, 第三列是指标quota。
 * id的数据类型是String, groupId的数据类型String, quota的数据类型float。
 *
 * 功能要求：
 * 1.把所有文件里面的内容按照分组进行排序，输出所有文件按照分组升序排序之后，每个分组下面的最小指标值。比如上面的数据输出结果为：
 * 100，2000102，98.3
 * 101，2000106，45.3
 * 102，2000104，98.3
 *
 * 非功能要求：
 * 1.文件读取要有线程池来执行，线程池的大小固定为10，文件内容需要存储到指定的内容数据结构当中。
 * 2.查找要求有独立线程来执行，直接消费读取线程池产生的内存数据结构。
 * 3.文件读取和排序要求并发作业，文件读取只要产生了数据，就可以把数据交给排序线程进行消费，计算最小值。
 *
 * 代码要求：
 * 1.重上面的要求语意里面抽象出合适的设计模式。
 * 2.需要考虑多线程的并发控制，同步机制。
 * 3.代码实现只能用JDK1.6或者1.8自带的工具类
 */
public class MinQuotaRecord {

    /**
     * 线程池核心数和最大线程数
     */
    private static final Integer THREAD_SIZE = 10;

    /**
     * 最大文件数
     */
    private static final Integer MAX_FILE_NUM = 100;

    /**
     * 缓冲区，生产者消费者模式
     */
    static BlockingQueue<Record> queue = new LinkedBlockingQueue();

    /**
     * 文件夹路径
     */
    private final String folderPath;

    /**
     * 读取线程完毕中断信号
     */
    volatile boolean interrupt = false;

    /**
     * 发布计数
     */
    static AtomicLong producerCount = new AtomicLong(0);

    /**
     * 消费计数
     */
    static AtomicLong consumerCount = new AtomicLong(0);

    /**
     * record 结果集
     */
    TreeMap<String, Record> treeMap = new TreeMap<>();

    public MinQuotaRecord(String folderPath) {
        this.folderPath = folderPath;
    }

    /**
     * 线程工厂记录线程名称方便定位
     */
    ThreadFactory namedThreadFactory = new ThreadFactory() {
        final String nameFormat = "file-read-pool-%d";
        final AtomicLong count = new AtomicLong(0L);

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = Executors.defaultThreadFactory().newThread(r);
            if (nameFormat != null) {
                thread.setName(String.format(nameFormat, count.getAndIncrement()));
            }
            return thread;
        }
    };

    /**
     * 文件读取线程池
     */
    final ExecutorService fileReadExecutor = new ThreadPoolExecutor(THREAD_SIZE, THREAD_SIZE, 0,
            TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(MAX_FILE_NUM), namedThreadFactory);

    /**
     * 处理计算分组排序计算最小值单独线程
     */
    final ExecutorService recordStatisticsExecutor = Executors.newSingleThreadExecutor();

    /**
     * 1. 启动线程池, 多线程读取文件夹下文件, 写入记录缓冲区
     * 2. 独立线程消费缓冲区, 比较 TreeMap 已有记录大小, 比原来的小覆盖
     * 3. 遍历 TreeMap 输出
     */
    public void startWork() throws Exception {
        File folder = new File(folderPath);
        if (!folder.exists() || !folder.isDirectory()) {
            throw new FileNotFoundException("folder not found");
        }

        //列出文件夹下文件名以 .txt 为后缀的文件
        File[] files =  folder.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.getName().endsWith(".txt");
            }
        });

        final CountDownLatch multiThreadCount = new CountDownLatch(files.length);
        final CountDownLatch singleThreadCount = new CountDownLatch(1);

        for (File file : files) {
            fileReadExecutor.execute(new FileReaderJob(file, multiThreadCount));
        }

        recordStatisticsExecutor.execute(() -> {

            /**
             * 中断且队列为空停止循环
             */
            while (!interrupt || !queue.isEmpty()) {

                //这里不使用 take 会阻塞线程当文件无记录时
                Record r = queue.poll();
                consumerCount.incrementAndGet();

                if (r != null) {
                    Record t;
                    if (treeMap.containsKey(r.groupId) && null != (t = treeMap.get(r.groupId))) {
                        r = t.quota < r.quota ? t : r;
                    }

                    //单线程不需要考虑线程安全问题
                    treeMap.put(r.groupId, r);
                }
            }

            singleThreadCount.countDown();
        });

        multiThreadCount.await();
        interrupt = true;
        singleThreadCount.await();

        System.out.printf("pub count: %s, cus count: %s\n", producerCount.get(), consumerCount.get());

        for (Record r : treeMap.values()) {
            System.out.println(r);
        }

        fileReadExecutor.shutdown();
        recordStatisticsExecutor.shutdown();
    }

    /**
     * 文件记录读取任务
     */
    static class FileReaderJob implements Runnable {

        private final File file;
        private final CountDownLatch countDownLatch;

        FileReaderJob(File file, CountDownLatch countDownLatch) {
            this.file = file;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {

            BufferedReader reader = null;
            InputStreamReader isr = null;
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(file);
                isr = new InputStreamReader(fis);
                reader = new BufferedReader(isr);

                String line;
                while (null != (line = reader.readLine())) {
                    String[] records = line.split("，");
                    if (records.length != 3) {
                        throw new IllegalStateException();
                    }

                    String id = records[0];
                    String groupId = records[1];
                    Float quota = Float.valueOf(records[2]);

                    //发布消息
                    queue.put(new Record(id, groupId, quota));
                    producerCount.incrementAndGet();
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            }
        }
    }


    /**
     * 文件数据记录id，groupId，quota
     */
    static class Record{

        private String id;
        private String groupId;
        private Float quota;

        public Record(String id, String groupId, Float quota) {
            this.id = id;
            this.groupId = groupId;
            this.quota = quota;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder();
            sb.append(groupId).append(", ");
            sb.append(id).append(", ");
            sb.append(quota);
            return sb.toString();
        }

    }

    /**
     * 测试
    public static void main(String[] args) {
        String path = System.getProperty("user.dir") + "/quota";

        MinQuotaRecord minQuotaRecord = new MinQuotaRecord(path);

        try {
            minQuotaRecord.startWork();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     **/

}
