package com.vkstech.resourceserver.service;

import com.vkstech.resourceserver.controller.UserController;
import com.vkstech.resourceserver.dto.AddressDto;
import com.vkstech.resourceserver.model.Address;
import com.vkstech.resourceserver.repository.AddressRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private AddressRepository addressRepository;

    public Address addNewAddress(AddressDto addressDto, String username) {
        LOGGER.info("UserService addNewAddress...");

        Address address = new Address();
        address.setHouseNumber(addressDto.getHouseNumber());
        address.setArea(addressDto.getArea());
        address.setCity(addressDto.getCity());
        address.setState(addressDto.getState());
        address.setPinCode(addressDto.getPinCode());
        address.setUsername(username);

        return addressRepository.save(address);
    }

    public List<Address> getAllAddress(String username) {
        LOGGER.info("UserService getAllAddress...");
        return addressRepository.findByUsernameOrderByUpdatedDateAsc(username);
    }
}
