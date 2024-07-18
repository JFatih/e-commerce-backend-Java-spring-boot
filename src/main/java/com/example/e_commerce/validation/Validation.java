package com.example.e_commerce.validation;

import com.example.e_commerce.exceptions.ApiException;
import org.springframework.http.HttpStatus;

public class Validation {


    public static void emailAlreadyExist(String email) {

        throw new ApiException("Registration with given email:" + email + " already exist.", HttpStatus.BAD_REQUEST);
    }

    public static RuntimeException userNotExist(String email) {

        throw new ApiException( email + " not exist", HttpStatus.BAD_REQUEST);
    }

    public static void storeRegisterNullStoreData() {

        throw new ApiException("Store information must be filled in for store registration.",HttpStatus.BAD_REQUEST);
    }

    public static void isNotExist() {

        throw new ApiException("Data is null", HttpStatus.BAD_REQUEST);

    }

    public static void categoryExist(String code) {

        throw new ApiException(code + " is existing", HttpStatus.BAD_REQUEST);
    }
}
