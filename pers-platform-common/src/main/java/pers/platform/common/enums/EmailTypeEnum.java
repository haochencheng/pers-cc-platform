package pers.platform.common.enums;

public enum EmailTypeEnum {

    TEXT(0,"普通格式"),

    HTML(1,"html格式"),

    FILE(2,"带图片格式");

    private int code;
    private String desc;

    EmailTypeEnum(int code,String desc){
        this.code = code;
        this.desc = desc;
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
