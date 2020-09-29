package com.example.marketplace.user.database;

public class Katalog {
    String id;
    String categoria;
    String title;
    String description;
    String time;
    String praice;
    String image;
    String sumfavorites;
    String email;
    String duratiom;
    String kol;

    public String getDuratiom() {
        return duratiom;
    }

    public void setDuratiom(String duratiom) {
        this.duratiom = duratiom;
    }

    public String getKol() {
        return kol;
    }

    public void setKol(String kol) {
        this.kol = kol;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    String type;
    String favorites;
    String sertificat;
    String programm;
    String people;
    String info;
    String imty;

    public String getImty() {
        return imty;
    }

    public void setImty(String imty) {
        this.imty = imty;
    }

    public String getSumfavorites() {
        return sumfavorites;
    }

    public void setSumfavorites(String sumfavorites) {
        this.sumfavorites = sumfavorites;
    }
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Katalog(String id, String categoria, String title, String description, String time, String praice, String sumfavorites,  String email, String image) {
        this.id = id;
        this.categoria = categoria;
        this.title = title;
        this.description = description;
        this.time = time;
        this.praice = praice;
        this.sumfavorites = sumfavorites;
        this.email = email;
        this.image = image;
    }

    public String getFavorites() {
        return favorites;
    }

    public void setFavorites(String favorites) {
        this.favorites = favorites;
    }

    public Katalog() {
    }

    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPraice() {
        return praice;
    }

    public void setPraice(String praice) {
        this.praice = praice;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSertificat() {
        return sertificat;
    }

    public void setSertificat(String sertificat) {
        this.sertificat = sertificat;
    }

    public String getProgramm() {
        return programm;
    }

    public void setProgramm(String programm) {
        this.programm = programm;
    }

    public String getPeople() {
        return people;
    }

    public void setPeople(String people) {
        this.people = people;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

}
