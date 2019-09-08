package com.vkstech.resourceserver.controller;

import com.vkstech.resourceserver.constants.ResponseMessages;
import com.vkstech.resourceserver.dto.AddressDto;
import com.vkstech.resourceserver.model.Address;
import com.vkstech.resourceserver.service.UserService;
import com.vkstech.resourceserver.utils.ResponseObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping("/address")
    public ResponseEntity addAddress(@Valid @RequestBody AddressDto addressDto, BindingResult bindingResult, Principal principal) {
        LOGGER.info("UserController addAddress...");

        if (bindingResult.hasErrors()) {
            List<ObjectError> fieldErrors = bindingResult.getAllErrors();
            for (ObjectError error : fieldErrors) {
                return new ResponseEntity(new ResponseObject(error.getDefaultMessage()), HttpStatus.BAD_REQUEST);
            }
        }

        Address userAddress = userService.addNewAddress(addressDto, principal.getName());
        if (userAddress == null)
            return new ResponseEntity(new ResponseObject(ResponseMessages.ADDRESS_SAVE_FAILURE), HttpStatus.BAD_REQUEST);

        return new ResponseEntity(new ResponseObject(ResponseMessages.ADDRESS_SAVE_SUCCESS, userAddress), HttpStatus.OK);
    }

    @GetMapping("/address/all")
    public ResponseEntity getAllAddress(Principal principal) {
        LOGGER.info("UserController getAllAddress...");

        List<Address> addressList = userService.getAllAddress(principal.getName());

        if (addressList == null || addressList.isEmpty())
            return new ResponseEntity(new ResponseObject(ResponseMessages.ADDRESS_FETCH_FAILURE), HttpStatus.BAD_REQUEST);

        return new ResponseEntity(new ResponseObject(ResponseMessages.ADDRESS_FETCH_SUCCESS, addressList), HttpStatus.OK);
    }

    @PutMapping("/address/{id}")
    public ResponseEntity editAddress(@PathVariable("id") @NotNull(message = "id cannot be null") Long id,
                                      @Valid @RequestBody AddressDto addressDto,
                                      BindingResult bindingResult,
                                      Principal principal) {
        LOGGER.info("UserController editAddress...");

        if (bindingResult.hasErrors()) {
            List<ObjectError> fieldErrors = bindingResult.getAllErrors();
            for (ObjectError error : fieldErrors) {
                return new ResponseEntity(new ResponseObject(error.getDefaultMessage()), HttpStatus.BAD_REQUEST);
            }
        }

        return userService.editAddress(id, addressDto, principal.getName());
    }

    @DeleteMapping("/address/{id}")
    public ResponseEntity deleteAddress(@PathVariable("id") @NotNull(message = "id cannot be null") Long id,
                                      Principal principal) {
        LOGGER.info("UserController deleteAddress...");

        return userService.deleteAddress(id, principal.getName());
    }
}
