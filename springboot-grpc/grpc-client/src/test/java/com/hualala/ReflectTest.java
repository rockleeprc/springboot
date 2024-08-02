package com.hualala;

import com.hualala.client.entity.Person;
import org.junit.Test;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.stream.Collectors;

public class ReflectTest {

    @Test
    public void test() {
        Person person = new Person(1, "a", "BJ");
        person.setNums(Arrays.asList("CC", "AA", "BB"));
        Long[] array = new Long[]{99L, 22L, 33L};
        person.setArray(array);
        Map m = new HashMap();
        m.put("A", "C");
        m.put("E", "a");
        person.setMap(m);

        StringBuilder sb = new StringBuilder();
        Method[] methods = person.getClass().getDeclaredMethods();
        int separatorIndex = 0;
        for (Method method : methods) {
            String methodName = method.getName();
            int modifiers = method.getModifiers();
            Class<?> returnType = method.getReturnType();
//            System.out.println(methodName + " | " + returnType + " | " + (Void.TYPE == returnType));
            if (methodName.startsWith("get") // getXXX
                    && Modifier.isPublic(modifiers) // public
                    && returnType != Void.TYPE // !void
            ) {
                if (separatorIndex != 0) sb.append("&");
                sb.append(methodName).append("=");

                Object returnValue = ReflectionUtils.invokeMethod(method, person);
                Class<?> returnObjectClazz = returnValue.getClass();
                // System.out.println(methodName + " | " + returnObject + " | " + returnType);

                if (List.class.isAssignableFrom(returnObjectClazz)) {
                    List list = (List) returnValue;
                    list.sort(Comparator.naturalOrder());
                    appendContent(list, sb);
                }
                if (Set.class.isAssignableFrom(returnObjectClazz)) {
                    Set set = (Set) returnValue;
                    appendContent(set, sb);
                }
                if (Map.class.isAssignableFrom(returnObjectClazz)) {
                    Map map = (Map) returnValue;
                    appendContent(map, sb);
                }

                if (returnObjectClazz.isArray()) {
                    Object[] objects = (Object[]) returnValue;
                    Comparator com = Comparator.naturalOrder();
                    List list = (List) Arrays.stream(objects).sorted(com).collect(Collectors.toList());
                    appendContent(list, sb);
                }
                separatorIndex++;
            }
        }
        System.out.println(sb);
    }

    private void appendContent(Map map, StringBuilder sb) {
        Set<Map.Entry> set = map.entrySet();
        int separatorIndex = 0;
        for (Map.Entry entry : set) {
            if (separatorIndex == 0) sb.append("[");
            if (separatorIndex != 0) sb.append(",");
            sb.append(entry.getKey());
            sb.append(":");
            sb.append(entry.getValue());
            separatorIndex++;
        }
        sb.append("]");
    }

    private void appendContent(Iterable iterable, StringBuilder sb) {
        int separatorIndex = 0;
        for (Object obj : iterable) {
            if (separatorIndex != 0) sb.append(",");
            sb.append(obj);
            separatorIndex++;
        }
    }

    @Test
    public void testCollection() {
        int[] arr1 = new int[10];
        long[] arr2 = new long[10];
        List list = new ArrayList();
        Set set = new HashSet();
        Map map = new HashMap();

        System.out.println(List.class.isAssignableFrom(list.getClass()));
        System.out.println(Set.class.isAssignableFrom(set.getClass()));
        System.out.println(Map.class.isAssignableFrom(map.getClass()));

        System.out.println(arr1.getClass().isArray());
        System.out.println(arr2.getClass().isArray());

    }

    @Test
    public void testSha() throws InterruptedException {
        System.out.println(returnValue());
    }

    private int returnValue() {
        int i = 0;
        try {
            i = 1;
            i = 1 / 0;
//            return i;
        }catch (Exception e){

        }finally {
//            return i;
        }
        return i;

    }
}
