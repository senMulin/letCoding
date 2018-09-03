package com.mulin.letcoding.aligroupquestion.question2;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

/**
 * Created by
 * @author mulin
 * @date 2018/8/29
 * @description:
 * JAVA中对文件读取的效率会受到文件大小本身的影响，本题目要求能够对于文本文件进行读取，在保证读取效率的同时，要求内存不能溢出。
 * 要求：
 * 1. 输入一个本地文本文件地址，文本文件大小为2G,文本编码类型为utf-8
 * 2. 读取该文本文件中出现特定单词的数量
 * 3. 把文本部分文件读取到内存中后，即可释放内存，并统计特定单词出现次数和总时间耗时
 * 4. 尽量减低字符统计耗时
 */
public class FileWordCount {

    /**
     * 统计大文件（大小<2G）特定单词出现次数和总时间耗时
     *
     * @param filePath 文件的绝对路径
     * @param word     需要统计的单词
     * @return 特定单词出现次数
     * @throws IOException
     */
    private static Count countWord(String filePath, String word) throws IOException {
        long start = System.currentTimeMillis();

        int count = 0;
        long offset = 0;

        //每次读取10M
        long size = 10 * 1024 * 1024;

        RandomAccessFile randomAccessFile = new RandomAccessFile(filePath, "r");
        FileChannel channel = randomAccessFile.getChannel();

        long fileLen = randomAccessFile.length();

        MappedByteBuffer mappedByteBuffer;

        //用来记录每轮统计的末尾字符串
        String endStr = "";

        for (;offset < fileLen; offset+=size) {

            size = fileLen - offset < size ? fileLen - offset : size;

            mappedByteBuffer = channel.map(FileChannel.MapMode.READ_ONLY, offset, size);

            CharBuffer charBuffer = null;
            try {
                Charset charset = Charset.defaultCharset();
                CharsetDecoder decoder = charset.newDecoder();
                charBuffer = decoder.decode(mappedByteBuffer);
                mappedByteBuffer.flip();

                mappedByteBuffer = null;
            } catch (Exception e) {
                e.printStackTrace();
            }

            String content = charBuffer.toString();
            String [] words = content.split("[\\pP\\s]");

            charBuffer = null;

            for (int i = 0; i < words.length; ++i) {
                String w = words[i];

                if ("".equals(w)) {
                    continue;
                }

                if (i == 0) {
                    w = endStr + w;
                    } else if (i == words.length - 1 &&
                        //内容字符串长度减去最后遇到该单词的位置等于该单词长度的情况下单词可能未完
                        content.length() - content.lastIndexOf(w) == w.length()) {
                    endStr = w;
                    continue;
                }

                if (word.equals(w)) {
                    count++;
                }
            }
        }

        //校验最后一个单词
        if (word.equals(endStr)) {
            count++;
        }

        channel.close();
        randomAccessFile.close();

        return new Count(count, (System.currentTimeMillis() - start));
    }

    static class Count {
        /**
         * 单词出现的次数
         */
        private int count;
        /**
         * 统计使用的时间（秒）
         */
        private long cost;

        public Count(int count, long cost) {
            this.count = count;
            this.cost = cost;
        }


        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Count{");
            sb.append("count=").append(count);
            sb.append(", cost=").append(cost);
            sb.append('}');
            return sb.toString();
        }
    }

    /**
     * 测试
    public static void main(String[] args) throws IOException {
        String path = System.getProperty("user.dir") + "/word.txt";
        String word = "King";
        Count result = countWord(path, word);

        System.out.println(result.toString());
    }
     **/

}
