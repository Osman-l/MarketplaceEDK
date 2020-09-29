package com.example.marketplace.user;

public class User {
    String id;
    String name;
    String surname;
    String email;
    String password;
    String phone;
    String location;
    String facebook;
    String vk;
    String telegram;
    int sumads;
    String category;
    String companyname;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    String company;
    public int getSumads() {
        return sumads;
    }

    public void setSumads(int sumads) {
        this.sumads = sumads;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(String id, String name, String surname, String email, String password, String phone, String location, String facebook, String vk, String telegram) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.location = location;
        this.facebook = facebook;
        this.vk = vk;
        this.telegram = telegram;
    }



    public User() {
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getVk() {
        return vk;
    }

    public void setVk(String vk) {
        this.vk = vk;
    }

    public String getTelegram() {
        return telegram;
    }

    public void setTelegram(String telegram) {
        this.telegram = telegram;
    }

    public User(String id, String name, String surname, String email, String phone, String location, String facebook, String vk, String telegram) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
        this.location = location;
        this.facebook = facebook;
        this.vk = vk;
        this.telegram = telegram;
    }

    public User(String token, String name, String surname, String email) {
        this.id = token;
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
