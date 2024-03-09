package com.example.mytodolist.Model.Class;

public class User {
    private String pseudo;
    private String mail;
    private String password;

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
