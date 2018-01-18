package pers.platform.core.auth.model;


import org.hibernate.validator.constraints.NotEmpty;
import pers.platform.common.model.BaseModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * api分类表
 * @author cc
 * @date 2018-01-18
 */
@Entity
@Table(name="api_type")
public class ApiType extends BaseModel {

    @Id
    private long id;

    @NotEmpty(message = "apiTypeCode不可为空")
    @Column(name = "api_type_code")
    private String apiTypeCode;   //api类别编码

    @NotEmpty(message = "apiTypeName不可为空")
    @Column(name = "api_type_name")
    private int apiTypeName;   //api类别名称

    @NotEmpty(message = "isFree不可为空")
    @Column(name = "is_free")
    private int isFree;    //0免费，1收费

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getApiTypeCode() {
        return apiTypeCode;
    }

    public void setApiTypeCode(String apiTypeCode) {
        this.apiTypeCode = apiTypeCode;
    }

    public int getApiTypeName() {
        return apiTypeName;
    }

    public void setApiTypeName(int apiTypeName) {
        this.apiTypeName = apiTypeName;
    }

    public int getIsFree() {
        return isFree;
    }

    public void setIsFree(int isFree) {
        this.isFree = isFree;
    }
}
