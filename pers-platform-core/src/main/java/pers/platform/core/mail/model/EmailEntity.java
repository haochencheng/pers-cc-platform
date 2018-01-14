package pers.platform.core.mail.model;

import org.apache.shiro.subject.Subject;

import java.io.File;

public class EmailEntity {

    private  Subject token;
    private  String host;
    private  String to;
    private  String from;
    private  String[] receives;
    private  String subject;
    private  String text;
    private  String userName;
    private  String password;
    private  int type;
    private File file;


    public Subject getToken() {
        return token;
    }

    public String getHost() {
        return host;
    }

    public String getTo() {
        return to;
    }

    public String getFrom() {
        return from;
    }

    public String[] getReceives() {
        return receives;
    }

    public String getSubject() {
        return subject;
    }

    public String getText() {
        return text;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }


    public void setToken(Subject token) {
        this.token = token;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setReceives(String[] receives) {
        this.receives = receives;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
