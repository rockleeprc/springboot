package org.example7.instance;


import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class TestThread {
    private static final ReentrantLock lock = new ReentrantLock();
    private static final Object monitor = new Object();
    private static final ThreadLocal<String> LOCAL = new ThreadLocal<>();
    private static Thread t1;
    private static Thread t2;
    private static int i = 3;
    static final WeakReference<Person> weak = new WeakReference<Person>(new Person("liyan"));

    public static void main(String[] args) throws Exception {
        Class<UserController> controllerClazz = UserController.class;
//        Method[] methods = controllerClazz.getMethods(); // 父类、子类 public
        UserController userController = controllerClazz.getConstructor(null).newInstance(null);
        Method[] methods = controllerClazz.getDeclaredMethods(); // 当前类 所有权限 不包含父类
//        Field[] fields = controllerClazz.getFields();// public 本类
        Field[] fields = controllerClazz.getDeclaredFields(); // public private 本类

        /*
        UserController userController = controllerClazz.getConstructor(null).newInstance(null);
        Field[] fields = controllerClazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Class<?> fieldClazz = field.getType();
            Object obj = fieldClazz.getConstructor(null).newInstance(null);
            System.out.println("file obj " + obj);
            // 通过field注入
            field.set(userController, obj);

            // 方法注入
            String fileName = field.getName();
            String methodName = "set" + fileName.substring(0, 1).toUpperCase() + fileName.substring(1, fileName.length());
            Method method = controllerClazz.getMethod(methodName, fieldClazz);
            method.invoke(userController, obj);
        }
        System.out.println(userController.getUserService());
         */
    }

    public static void t7() {
        UserController userController = new UserController();
        Class<? extends UserController> controllerClazz = userController.getClass();
        Field[] fields = controllerClazz.getDeclaredFields();

        for (Field field : fields) {
            System.out.println(field.getName());
            if (field.isAnnotationPresent(Autowired.class)) {
                Autowired autowired = field.getAnnotation(Autowired.class);
                String value = autowired.value();
                String name = autowired.name();
                System.out.println(autowired);
            }
        }
    }

    public static void t6() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        UserController userController = new UserController();
        Class<? extends UserController> controllerClazz = userController.getClass();
        Field[] fileds = controllerClazz.getDeclaredFields();
        for (Field filed : fileds) {
//            filed.setAccessible(true); // 不需要通过filed操作
            // 1、field获取方法名称
            String filedName = filed.getName();
            String methodName = "set" + filedName.substring(0, 1).toUpperCase() + filedName.substring(1, filedName.length());
            // 2、通过filed名称获取setter方法
            Method setMethod = controllerClazz.getMethod(methodName, filed.getType());// getMethod(方法名称,方法参数)
            Class<?> serviceClazz = filed.getType();
            UserService userService = (UserService) serviceClazz.getConstructor(null).newInstance(null);
            // 3、通过setter方法注入service
            setMethod.invoke(userController, userService);
        }
        System.out.println(userController.getUserService());
    }

    public static void t5() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        UserController userController = new UserController();
        Class<? extends UserController> controllerClazz = userController.getClass();
//        Field[] fields1 = controllerClazz.getFields(); // public
        Field[] fields = controllerClazz.getDeclaredFields(); // public, protected, default , private
        for (Field field : fields) {
            // 获取filed所在类的class
            // System.out.println(field.getDeclaringClass()); // UserController
            // 1、可以操作任何权限属性
            field.setAccessible(true);
            // 2、获取filed对应class
            Class<?> serviceClazz = field.getType(); // UserService
            // 3、通过filed的class构建对象
            UserService userService = (UserService) serviceClazz.getConstructor(null).newInstance(null);
            // 4、注入
            field.set(userController, userService); // set(controller,service)
        }
        System.out.println(userController.getUserService());
    }


    private static class Person {

        public Person(String name) {
            this.name = name;
        }

        String name;

        @Override
        protected void finalize() throws Throwable {
            System.out.println("GC");
        }
    }

    public static void t4() {
        Condition t1Condition = lock.newCondition();
        Condition t2Condition = lock.newCondition();

        char[] c1 = "abcd".toCharArray();
        char[] c2 = "1234".toCharArray();

        t1 = new Thread(() -> {

            lock.lock();
            try {
                for (int i = 0; i < c1.length; i++) {
                    System.out.print(c1[i]);
                    t1Condition.await();
                    t2Condition.signal();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "t1");
        t1.start();

        t2 = new Thread(() -> {
            lock.lock();
            try {
                for (int i = 0; i < c2.length; i++) {
                    System.out.print(c2[i]);
                    t1Condition.signal();
                    t2Condition.await();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "t2");


        t2.start();
    }

    public static void t3() {
        char[] c1 = "abcd".toCharArray();
        char[] c2 = "1234".toCharArray();

        t1 = new Thread(() -> {
            synchronized (monitor) {
                for (int i = 0; i < c1.length; i++) {
                    System.out.print(c1[i]);
                    monitor.notify();
                    try {
                        monitor.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "t1");

        t2 = new Thread(() -> {
            synchronized (monitor) {
                for (int i = 0; i < c2.length; i++) {
                    try {
                        monitor.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.print(c2[i]);
                    monitor.notify();
                }
            }
        }, "t2");

        t1.start();
        t2.start();
    }

    public static void t2() {
        char[] c1 = "abcd".toCharArray();
        char[] c2 = "1234".toCharArray();

        t1 = new Thread(() -> {
            for (int i = 0; i < c1.length; i++) {
                System.out.print(c1[i]);
                LockSupport.unpark(t2);
                LockSupport.park();
            }
        }, "t1");

        t2 = new Thread(() -> {
            for (int i = 0; i < c2.length; i++) {
                LockSupport.park();
                System.out.print(c2[i]);
                LockSupport.unpark(t1);
            }
        }, "t2");

        t1.start();
        t2.start();
    }

    public static void t1() throws Exception {
        Thread t1 = new Thread(() -> {
            lock.lock();
            try {
                while (true) {
                }
            } finally {
                lock.unlock();
            }
        }, "t1");
        t1.start();

        TimeUnit.SECONDS.sleep(1);
        System.out.println("t2");

        Thread t2 = new Thread(() -> {
            lock.lock();
            try {
                System.out.println(".");
            } finally {
                lock.unlock();
            }
        }, "t2");
        t2.start();


        TimeUnit.SECONDS.sleep(1);
        System.out.println("t3");
        Thread t3 = new Thread(() -> {
            lock.lock();
            try {
                System.out.println(".");
            } finally {
                lock.unlock();
            }
        }, "t3");
        t3.start();

        System.in.read();
    }
}
