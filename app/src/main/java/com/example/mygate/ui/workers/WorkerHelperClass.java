package com.example.mygate.ui.workers;

public class WorkerHelperClass {
    String name,phno,type;
    public WorkerHelperClass() {

    }
    public WorkerHelperClass(String name, String phno, String type) {
        this.name = name;
        this.phno = phno;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhno() {
        return phno;
    }

    public void setPhno(String phno) {
        this.phno = phno;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
