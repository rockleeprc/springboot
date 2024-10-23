package org.example9;

import java.io.File;
import java.io.IOException;

/**
 * 加 / 前缀就是绝对路径
 * 没 / 前缀就是相对路径开始
 */
public class LoadResourceMain {
    public static void main(String[] args) throws IOException {
        String packageName = LoadResourceMain.class.getName();
        packageName = packageName.replace(".","/")+".java";
        File file = new File(packageName);
        System.out.println(file.getAbsoluteFile());

//        String path = basePath+pathContent+packageName;
//        System.out.println(path);
//
//        Stream<String> lines = Files.lines(Path.of(path));
//        lines.forEachOrdered(System.out::println);
    }

    public static void fileLoad() {
        // 相对路径：/Users/admin/Workspaces/git_repository/my_github/springboot/springboot2
        // 工程路径
        String property = System.getProperty("user.dir");
        System.out.println("property:" + property);
    }

    public static void classLoad() {
        // 据对路径：/Users/admin/Workspaces/git_repository/my_github/springboot/springboot2/09_annotation/target/classes/
        // classpath路径
        String path1 = LoadResourceMain.class.getResource("/").getPath();
        System.out.println(path1);

        // 相对路径：/Users/admin/Workspaces/git_repository/my_github/springboot/springboot2/09_annotation/target/classes/org/example9/
        // classpath + 当前类包路径
        String path2 = LoadResourceMain.class.getResource("").getPath();
        System.out.println(path2);
    }

    public static void classloaderLoad() {
        // classloader 没有绝对路径，没有以/开始的路径
//        String path1 = LoadResourceMain.class.getClassLoader().getResource("/").getPath();
//        System.out.println(path1);

        // 相对路径：/Users/admin/Workspaces/git_repository/my_github/springboot/springboot2/09_annotation/target/classes/
        // classpath 路径
        String path2 = LoadResourceMain.class.getClassLoader().getResource("").getPath();
        System.out.println(path2);
    }
}
