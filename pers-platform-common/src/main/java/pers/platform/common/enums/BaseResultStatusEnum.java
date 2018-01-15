package pers.platform.common.enums;

public enum BaseResultStatusEnum {

    FAIL(0,"失败"),
    SUCCESS(1,"成功");

    private int code;
    private String desc;

    BaseResultStatusEnum(int code,String desc){
        this.code = code;
        this.desc = desc;
    }

    public static BaseResultStatusEnum valueOf(int value) {    //    手写的从int到enum的转换函数
        switch (value) {
            case 0:
                return FAIL;
            case 1:
                return SUCCESS;
            default:
                return FAIL;
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
