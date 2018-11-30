package com.foo.util;

import java.io.File;

public class FileUtils {
    public static boolean deleteDirectory(File directory) {
        if (directory.isDirectory()) {
            File[] files = directory.listFiles();
            for (File file : files) {
                if (file.isDirectory()) {
                    deleteDirectory(file);
                } else {
                    file.delete();
                }
            }
        }
        return directory.delete();
    }

    public static void main(String[] args) {
        File file = new File("F:" + File.separator + "data" + File.separator + "wc" + File.separator + "output");
        System.out.println(file.exists());
        System.out.println(deleteDirectory(file));
    }
}
