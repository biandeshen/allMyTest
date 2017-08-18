package org.lanqiao.reflect;


import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class Task2 {
    public static <T> T map2Bean(Map<String, Object> map, Class<T> claz) throws Exception {
        if (map == null)
            return null;

        T object = claz.newInstance();

        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            // field.set(object, map.get(field.getName()));
            String setter = "set" + upperFirstLetter(field.getName());
            Class<?> fieldType = field.getType();
            Method method;
            String value = ""+map.get(field.getName());
            switch (fieldType.getName()) {
            case "int":
                method = claz.getMethod(setter, int.class);
                method.invoke(object, Integer.valueOf(value));
                break;
            case "float":
                method = claz.getMethod(setter, float.class);
                method.invoke(object, Float.valueOf(value));
                break;
            default:
                method = claz.getMethod(setter, String.class);
                method.invoke(object, map.get(field.getName()));
                break;
            }

        }
        return object;

    }

    private static String upperFirstLetter(String setterName) {
        StringBuilder sb = new StringBuilder(("" + setterName.charAt(0)).toUpperCase());
        sb.append(setterName.substring(1));
        return sb.toString();
    }

    public static <T> void main(String[] args) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", "张三");
        map.put("age", "18");
        map.put("id", 001);
        map.put("tel", "110");

        Teacher teacher =map2Bean(map, Teacher.class);
        System.out.println(teacher.getName() + "    " + teacher.getId());
    }
}


class Teacher {
    private String name;
    private int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Teacher [name=" + name + ", id=" + id + "]";
    }

}