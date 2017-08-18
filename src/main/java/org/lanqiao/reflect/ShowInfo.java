package org.lanqiao.reflect;

import java.lang.reflect.Modifier;

public class ShowInfo {
    public static void main(String[] args) {
        Class<Integer> claz = Integer.class;
        System.out.println(claz.toGenericString());
        /*
         * PUBLIC: 1 PRIVATE: 2 PROTECTED: 4 STATIC: 8 FINAL: 16 SYNCHRONIZED:
         * 32 VOLATILE: 64 TRANSIENT: 128 NATIVE: 256 INTERFACE: 512 ABSTRACT:
         * 1024 STRICT: 2048
         */
        /*
         * int i = cls.getModifiers(); String retval = Modifier.toString(i);
         */
        System.out.println(toGenericStringX(claz));

    }

    private static <T> String toGenericStringX(Class<T> claz) {
        if (claz.isPrimitive()) {
            return claz.toString();
        } else {
            StringBuilder sb = new StringBuilder();
            int i = claz.getModifiers();
            String type = Modifier.toString(claz.getModifiers());
            if (i != 0) {
                sb.append(type);
                sb.append(" ");
            }
            if (claz.isInterface()) {
                sb.append("interface");

            } else if (claz.isEnum()) {
                sb.append("enum");
            } else {
                sb.append("class");
            }

            sb.append(" ");
            sb.append(claz.getName());
            return sb.toString();
        }

    }

}
