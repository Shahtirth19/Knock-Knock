package com.example.mygate;

public class UserHelperClass {
    String email,name,address,phno,response,id,uid,blockno,videoId;

    public UserHelperClass() {
    }

    public UserHelperClass(String email, String name, String address, String phno, String response, String id, String uid, String blockno, String videoId) {
        this.email = email;
        this.name = name;
        this.address = address;
        this.phno = phno;
        this.response = response;
        this.id = id;
        this.uid = uid;
        this.blockno = blockno;
        this.videoId = videoId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhno() {
        return phno;
    }

    public void setPhno(String phno) {
        this.phno = phno;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getBlockno() {
        return blockno;
    }

    public void setBlockno(String blockno) {
        this.blockno = blockno;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }
}
