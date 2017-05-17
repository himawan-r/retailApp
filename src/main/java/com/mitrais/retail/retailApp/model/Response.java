package com.mitrais.retail.retailApp.model;

public class Response {
    public static final String SUCCESS = "success";
    public static final String FAILED = "failed";

    private Object data;
    private String status;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
