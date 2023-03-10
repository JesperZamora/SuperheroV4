package com.example.superherov4.dto;

import java.util.List;

public class CityDTO {
    private int cityID;
    private String city;
    private List<String> cityList;
    public CityDTO(int cityID, String city, List <String> cityList) {
        this.cityID = cityID;
        this.city = city;
        this.cityList = cityList;
    }

    public int getCityID() {
        return cityID;
    }

    public String getCity() {
        return city;
    }

    public List<String> getCityList() {
        return cityList;
    }
}
