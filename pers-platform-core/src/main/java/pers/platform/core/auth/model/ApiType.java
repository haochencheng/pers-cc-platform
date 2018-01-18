package pers.platform.core.auth.model;


import org.hibernate.validator.constraints.NotEmpty;
import pers.platform.common.model.BaseModel;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * api分类表
 * @author cc
 * @date 2018-01-18
 */
@Entity
@Table(name="api_type")
public class ApiType extends BaseModel {

    private int id;

    @NotEmpty(message = "apiTypeCode不可为空")
    private int apiTypeCode;   //api类别编码

    @NotEmpty(message = "apiTypeName不可为空")
    private int apiTypeName;   //api类别名称

    @NotEmpty(message = "apiType不可为空")
    private int apiType;    //0免费，1收费

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getApiTypeCode() {
        return apiTypeCode;
    }

    public void setApiTypeCode(int apiTypeCode) {
        this.apiTypeCode = apiTypeCode;
    }

    public int getApiTypeName() {
        return apiTypeName;
    }

    public void setApiTypeName(int apiTypeName) {
        this.apiTypeName = apiTypeName;
    }

    public int getApiType() {
        return apiType;
    }

    public void setApiType(int apiType) {
        this.apiType = apiType;
    }
}
