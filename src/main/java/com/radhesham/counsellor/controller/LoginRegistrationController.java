package com.radhesham.counsellor.controller;

import com.radhesham.counsellor.bean.request.LoginRequest;
import com.radhesham.counsellor.bean.request.UserRegistrationRequest;
import com.radhesham.counsellor.bean.response.DefaultResponse;
import com.radhesham.counsellor.bean.response.LoginUserResponse;
import com.radhesham.counsellor.model.ManageLoginRegistration;
import com.radhesham.counsellor.service.CounsellorService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginRegistrationController {

    private static final Logger logger = LoggerFactory.getLogger(LoginRegistrationController.class);
    @Autowired
    HttpServletRequest request;
    @Autowired
    CounsellorService loginRegistrationService;

    @RequestMapping(value = "/great", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<String> great() {
        return new ResponseEntity<>("Hello ,welcome to app", HttpStatus.OK);
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<DefaultResponse> registerUser(@RequestBody UserRegistrationRequest userRegistrationRequest) {
        DefaultResponse response = null;
        try {
            logger.info("Entry in registerUser()");
            ManageLoginRegistration manageLoginRegistration = new ManageLoginRegistration();
            manageLoginRegistration.setRequest(request);
            manageLoginRegistration.setLoginRegistrationService(loginRegistrationService);
            response = manageLoginRegistration.registerNewUser(userRegistrationRequest);
            logger.info("Exit from registerUser()");
        } catch (Exception e) {
            logger.error("Exception in registerUser() :",e);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<LoginUserResponse> login(@RequestBody LoginRequest loginRequest){
        LoginUserResponse loginUserResponse=null;
        try{
            logger.info("Entry in login()");
            ManageLoginRegistration manageLoginRegistration = new ManageLoginRegistration();
            manageLoginRegistration.setRequest(request);
            manageLoginRegistration.setLoginRegistrationService(loginRegistrationService);
            loginUserResponse = manageLoginRegistration.loginUser(loginRequest);
            logger.info("Exit from login()");
        }catch (Exception e){
            logger.error("Exception in login() :",e);
        }
        return new ResponseEntity<>(loginUserResponse,HttpStatus.OK);
    }
}
