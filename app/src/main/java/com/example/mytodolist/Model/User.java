package com.example.mytodolist.Model;

public class User {
    private String id;
    private String pseudo;
    private String mail;
    private String password;

    public User(String id, String pseudo, String mail, String password) {
        super();
        this.id = id;
        this.pseudo = pseudo;
        this.password = password;
        this.mail = mail;
    }

    public User(String pseudo, String mail, String password) {
        super();
        this.pseudo = pseudo;
        this.password = password;
        this.mail = mail;
    }

    public User(String mail, String password) {
        super();
        this.password = password;
        this.mail = mail;
    }

    public String getId() {
        return id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }
}
