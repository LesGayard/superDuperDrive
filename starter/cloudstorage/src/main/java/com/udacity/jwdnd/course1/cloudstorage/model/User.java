package com.udacity.jwdnd.course1.cloudstorage.model;

public class User {

    private Integer id;
    private String username;
    private String salt;
    private String password;
    private String firstName;
    private String lastname;

    public User() {
    }

    public User(Integer id, String username, String salt,String password, String firstName, String lastname) {
        this.id = id;
        this.username = username;
        this.salt = salt;
        this.password = password;
        this.firstName = firstName;
        this.lastname = lastname;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public void setPassword(String password){
        this.password = password;
    }
    public String getPassword(){
        return this.password;
    }
    public String getFirstname() {
        return firstName;
    }

    public void setFirstname(String firstName) {
        this.firstName = firstName;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", salt='" + salt + '\'' +
                ", paswword= " + password + " " +
                ", firstName='" + firstName + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }
}
