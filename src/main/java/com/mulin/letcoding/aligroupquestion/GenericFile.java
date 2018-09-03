package com.mulin.letcoding.aligroupquestion;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Created by
 * @author mulin
 * @date 2018/8/29
 * @description:
 */
public class GenericFile {

    private final String path;
    private final File file;

    public GenericFile(String path) {
        this.path = path;
        this.file = new File(path);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void genFile(String line, long fileLen) throws IOException {

        long start = System.currentTimeMillis();

        RandomAccessFile randomAccessFile = null;

        try {
            randomAccessFile = new RandomAccessFile(file, "rw");
            long len = 0;

            while ( fileLen > (len = randomAccessFile.length())) {
                randomAccessFile.seek(len);
                randomAccessFile.write(line.getBytes("UTF-8"));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            randomAccessFile.close();
            System.out.printf("cost time: %l\n", System.currentTimeMillis() - start);
        }
    }

    public static void main(String[] args) {

        String path = System.getProperty("user.dir") + "/word.txt";
        String line = " that day, when the Princess was sitting at the table, something was heard coming up the marble stairs. " +
                "Splish, splosh, splish splosh! The sound came nearer and nearer, and a voice cried, \"Let me in, youngest daughter of the King.\"\n";
        long fileLen = 2L * 1024 * 1024 * 1024;

        GenericFile genericFile = new GenericFile(path);

        try {
            genericFile.genFile(line, fileLen);

        }catch (IOException e) {
            e.printStackTrace();
        }
    }


}
