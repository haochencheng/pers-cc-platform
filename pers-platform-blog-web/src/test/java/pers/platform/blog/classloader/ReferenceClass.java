package pers.platform.blog.classloader;

public class ReferenceClass {

    private String str;

    public String getStr() {
        str = "helloWorld";
        System.out.println(
                "引用类加载器:" + ReferenceClass.class.getClassLoader().toString());
        return str;
    }

}
