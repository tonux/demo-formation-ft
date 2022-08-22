package com.sn.finetech.finetechapp.services;

import com.sn.finetech.finetechapp.model.User;

import javax.xml.bind.ValidationException;


public interface UserService {

    User registration(User userRequest) throws ValidationException;

    User login(User userRequest);
}
