package com.spring.test;

import java.util.Date;

/**
 * Created by seasen on 2016/1/9.
 */
public class HelloBean {
    private String helloworld;
    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getHelloworld() {
        return helloworld;
    }

    public void setHelloworld(String helloworld) {
        this.helloworld = helloworld;
    }
}
