package pers.platform.core.mail.model;

import org.apache.shiro.subject.Subject;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.File;

@Entity
@Table(name="email_entity")
public class EmailEntity {

    @Id
    private long id;
    private  String host;
    private  String[] to;
    private  String from;
    private  String[] receives;
    private  String subject;
    private  String text;
    private  String userName;
    private  String password;
    private  int type;
    private byte[] file;
    private String fileName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getHost() {
        return host;
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

    public void setHost(String host) {
        this.host = host;
    }

    public String[] getTo() {
        return to;
    }

    public void setTo(String[] to) {
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

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
