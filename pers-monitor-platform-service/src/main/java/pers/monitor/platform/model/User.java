package pers.monitor.platform.model;

import javax.persistence.*;
import java.util.Date;

/**
 * @Title:
 * @Description:
 * @Author: cc
 * @Since:2017年8月19日
 * @Version:1.0.0
 */
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id; // 主键

    @Column(nullable = false)
    private String userName; // 用户名

    @Column(nullable = false)
    private String password; // 密码

    private String phone; // 电话,短信提醒服务

    private String sex; // 性别

    private String roleId; // 角色外键

    private String email; // 邮箱地址,邮箱提醒服务

    private Date userCreateTime; // 用户注册时间

    private Date userModifyTime; // 用户修改时间

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return this.sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getRoleId() {
        return this.roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getUserCreateTime() {
        return this.userCreateTime;
    }

    public void setUserCreateTime(Date userCreateTime) {
        this.userCreateTime = userCreateTime;
    }

    public Date getUserModifyTime() {
        return this.userModifyTime;
    }

    public void setUserModifyTime(Date userModifyTime) {
        this.userModifyTime = userModifyTime;
    }

}
