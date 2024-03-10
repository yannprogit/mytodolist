package com.example.mytodolist.Model.Class;

public class User {
    public String getUid() {
        return uid;
    }

    private String uid;
    private String pseudo;
    private String mail;

    public User(String uid, String pseudo, String mail) {
        super();
        this.uid = uid;
        this.pseudo = pseudo;
        this.mail = mail;
    }

    public User(String pseudo, String mail) {
        super();
        this.pseudo = pseudo;
        this.mail = mail;
    }

    public User() {
        super();
    }

    public String getPseudo() {
        return pseudo;
    }

    public String getMail() {
        return mail;
    }
}
