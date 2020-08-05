package com.example.projectmadlab;

class dataHotel {

    String id;
    String name;
    String address;
    String price_per_night;
    String phone_number;
    String image;
    String LAT;
    String LNG;

    public dataHotel(String id, String name, String address, String price_per_night, String phone_number, String image, String LAT, String LNG) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.price_per_night = price_per_night;
        this.phone_number = phone_number;
        this.image = image;
        this.LAT = LAT;
        this.LNG = LNG;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPrice_per_night() {
        return price_per_night;
    }

    public void setPrice_per_night(String price_per_night) {
        this.price_per_night = price_per_night;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLAT() {
        return LAT;
    }

    public void setLAT(String LAT) {
        this.LAT = LAT;
    }

    public String getLNG() {
        return LNG;
    }

    public void setLNG(String LNG) {
        this.LNG = LNG;
    }
}