package pers.platform.common.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * 通用字段实体
 * @author cc
 * @date 2018-01-16
 */

public class BaseModel {

    private Date createdTime; //创建时间

    private Date modifiedTime; //修改时间

    private String  createUser; //创建人

    private String modifyUser; //修改人

    private String remark;// 描述
}
