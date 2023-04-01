package com.driver.model;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class User {
    public User(){

    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;
    private String password;

    private String originalIp;
    private String maskedIp;
    private Boolean connected;
    private Country updatedCountry;

    public Country getOriginalCountry() {
        return originalCountry;
    }

    public void setOriginalCountry(Country originalCountry) {
        this.originalCountry = originalCountry;
    }

    private Country originalCountry;

    public Country getUpdatedCountry() {
        return updatedCountry;
    }

    public void setUpdatedCountry(Country updatedCountry) {
        this.updatedCountry = updatedCountry;
    }

    @ManyToMany(mappedBy = "user",cascade = CascadeType.ALL)
    List<ServiceProvider> serviceProviderList = new ArrayList<>();

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    List<Connection> connectionList = new ArrayList<>();

    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
    Country country;

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country originalCountry) {
        this.country = originalCountry;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOriginalIp() {
        return originalIp;
    }

    public void setOriginalIp(String originalIp) {
        this.originalIp = originalIp;
    }

    public String getMaskedIp() {
        return maskedIp;
    }

    public void setMaskedIp(String maskedIp) {
        this.maskedIp = maskedIp;
    }

    public Boolean getConnected() {
        return connected;
    }

    public void setConnected(Boolean connected) {
        this.connected = connected;
    }

    public List<ServiceProvider> getServiceProviderList() {
        return serviceProviderList;
    }

    public void setServiceProviderList(List<ServiceProvider> serviceProviderList) {
        this.serviceProviderList = serviceProviderList;
    }

    public List<Connection> getConnectionList() {
        return connectionList;
    }

    public void setConnectionList(List<Connection> connectionList) {
        this.connectionList = connectionList;
    }





}
