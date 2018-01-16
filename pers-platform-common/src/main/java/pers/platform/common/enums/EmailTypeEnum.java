package pers.platform.common.enums;

public enum EmailTypeEnum {

    TEXT(0,"普通格式"),

    HTML(1,"html格式"),

    FILE(2,"带附格式"),

    RESOURCE(3,"静态资源格式");

    private int code;
    private String desc;

    EmailTypeEnum(int code,String desc){
        this.code = code;
        this.desc = desc;
    }

    public static EmailTypeEnum valueOf(int value) {    //    手写的从int到enum的转换函数
        switch (value) {
            case 0:
                return TEXT;
            case 1:
                return HTML;
            case 2:
                return FILE;
            case 3:
                return RESOURCE;
            default:
                return TEXT;
        }
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
