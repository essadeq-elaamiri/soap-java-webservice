package me.elaamiri.webservice;

import java.util.Date;

public class Account {
    private  int code;
    private double balance;
    private Date createdAt;


    public Account() {
    }

    public Account(int code, double balance, Date createdAt) {
        this.code = code;
        this.balance = balance;
        this.createdAt = createdAt;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
