package pers.platform.common.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 通用字段实体
 * @author cc
 * @date 2018-01-16
 */
@MappedSuperclass
public class BaseModel implements Serializable {

    //声明两个时间列，用来作为创建时间和更新时间
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdTime; //创建时间

    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedTime; //修改时间

    private String  createUser; //创建人

    private String modifyUser; //修改人

    private String remark;// 描述

    private int version=0; //默认版本号0

    //在创建时，对创建时间和更新时间进行刷新
    @PrePersist
    public void prePersist(){
        this.createdTime = new Date();
    }

    //在更新时，对更新时间进行刷新
    @PreUpdate
    public void preUpdate(){
        this.modifiedTime = new Date();
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getModifyUser() {
        return modifyUser;
    }

    public void setModifyUser(String modifyUser) {
        this.modifyUser = modifyUser;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
