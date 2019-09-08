package com.vkstech.resourceserver.service;

import com.vkstech.resourceserver.constants.ResponseMessages;
import com.vkstech.resourceserver.controller.UserController;
import com.vkstech.resourceserver.dto.AddressDto;
import com.vkstech.resourceserver.model.Address;
import com.vkstech.resourceserver.repository.AddressRepository;
import com.vkstech.resourceserver.utils.ResponseObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    public ResponseEntity editAddress(Long id, AddressDto addressDto, String username) {

        Address address = addressRepository.findByIdAndUsername(id, username);
        if (address == null)
            return new ResponseEntity(new ResponseObject(ResponseMessages.ADDRESS_NOT_EXISTS), HttpStatus.BAD_REQUEST);

        address.setHouseNumber(addressDto.getHouseNumber());
        address.setArea(addressDto.getArea());
        address.setCity(addressDto.getCity());
        address.setState(addressDto.getState());
        address.setPinCode(addressDto.getPinCode());

        try {
            addressRepository.save(address);
        } catch (Exception e) {
            return new ResponseEntity(new ResponseObject(ResponseMessages.ADDRESS_UPDATE_FAILURE), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(new ResponseObject(ResponseMessages.ADDRESS_UPDATE_SUCCESS, address), HttpStatus.OK);
    }

    public ResponseEntity deleteAddress(Long id, String username) {
        Address address = addressRepository.findByIdAndUsername(id, username);
        if (address == null)
            return new ResponseEntity(new ResponseObject(ResponseMessages.ADDRESS_NOT_EXISTS), HttpStatus.BAD_REQUEST);

        try {
            addressRepository.deleteByIdAndUsername(id, username);
            return new ResponseEntity(new ResponseObject(ResponseMessages.ADDRESS_DELETE_SUCCESS), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(new ResponseObject(ResponseMessages.ADDRESS_DELETE_FAILURE), HttpStatus.BAD_REQUEST);
        }

    }
}
