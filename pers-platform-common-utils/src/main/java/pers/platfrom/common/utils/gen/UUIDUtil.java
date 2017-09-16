package pers.platfrom.common.utils.gen;

import java.util.UUID;

/**
 * Created by cc on  2017/9/14
 */
public class UUIDUtil {

    /**
     * 获取UUID
     *
     * @return uuid.toString
     * @Description:
     */
    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    public static void main(String[] args) {

        System.out.println(UUIDUtil.getUUID());
    }


}
