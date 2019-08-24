package com.vkstech.resourceserver.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AddressDto {

    @NotNull(message = "houseNumber cannot be null")
    private Integer houseNumber;

    @NotNull(message = "area cannot be null")
    @NotBlank(message = "area cannot be blank")
    @NotEmpty(message = "area cannot be null")
    private String area;

    @NotNull(message = "city cannot be null")
    @NotBlank(message = "city cannot be blank")
    @NotEmpty(message = "city cannot be null")
    private String city;

    @NotNull(message = "state cannot be null")
    @NotBlank(message = "state cannot be blank")
    @NotEmpty(message = "state cannot be null")
    private String state;

    @NotNull(message = "pinCode cannot be null")
    private Integer pinCode;

    public Integer getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(Integer houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getPinCode() {
        return pinCode;
    }

    public void setPinCode(Integer pinCode) {
        this.pinCode = pinCode;
    }
}
