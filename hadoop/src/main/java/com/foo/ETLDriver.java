package com.foo;

import com.foo.kd.ShopETLDriver;
import org.apache.hadoop.util.ProgramDriver;

public class ETLDriver {
    public ETLDriver() {
    }

    public static void main(String[] argv) {
        int exitCode = -1;
        ProgramDriver pgd = new ProgramDriver();

        try {
            pgd.addClass("shopetl", ShopETLDriver.class, "shop数据清洗");
            exitCode = pgd.run(argv);
        } catch (Throwable e) {
            e.printStackTrace();
        }

        System.exit(exitCode);
    }
}
