package pers.platform.common.enums;

public enum BaseStatusEnum {

    INVAILD(0,"不可用"),
    VAILD(1,"可用");

    private int code;
    private String desc;

    BaseStatusEnum(int code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public static BaseStatusEnum valueOf(int value) {    //    手写的从int到enum的转换函数
        switch (value) {
            case 0:
                return INVAILD;
            case 1:
                return VAILD;
        }
        return INVAILD;
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
