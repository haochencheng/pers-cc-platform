package pers.platform.monitor.model;

import pers.platform.common.utils.cryptography.CryptographyUtil;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * @Title:
 * @Description:
 * @Author: cc
 * @Since:2017年8月19日
 * @Version:1.0.0
 */
@SuppressWarnings("serial")
@Entity
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id; // 主键

    @Column(nullable = false)
    private String userName; // 用户名

    @Column(nullable = false)
    private String password; // 密码

    @Column(nullable = true)
    private String phone; // 电话,短信提醒服务

    @Column(nullable = true)
    private String sex; // 性别

    @Column(nullable = false)
    private String roleId; // 角色外键

    private String email; // 邮箱地址,邮箱提醒服务

    private Date userCreateTime; // 用户注册时间

    private Date userModifyTime; // 用户修改时间

    private Integer locked = 0;

    private String salt; // 密码随机加盐

    private Integer rememberMe = 0; // 记住我

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
        this.salt = CryptographyUtil.md5(UUID.randomUUID().toString(),
                userName);
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

    public boolean isLocked() {
        return this.locked == 1;
    }

    public void setLocked(Integer locked) {
        this.locked = locked;
    }

    public boolean isRememberMe() {
        return rememberMe == null ? false : rememberMe == 1;
    }

    public void setRememberMe(Integer rememberMe) {
        this.rememberMe = rememberMe;
    }

    public String getPrincipal() {
        return userName;
    }

    public Object getCredentials() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getUserName() {
        return userName;
    }

    public Integer getLocked() {
        return locked;
    }

    public String getSalt() {
        return salt;
    }

    public Integer getRememberMe() {
        return rememberMe;
    }
}
