package com.example.mygate.ui.PreApprove;

public class PreHelperClass {
    String name,message,date,blockno;

    public PreHelperClass() {
    }

    public PreHelperClass(String name, String message, String date, String blockno) {
        this.name = name;
        this.message = message;
        this.date = date;
        this.blockno = blockno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBlockno() {
        return blockno;
    }

    public void setBlockno(String blockno) {
        this.blockno = blockno;
    }
}
