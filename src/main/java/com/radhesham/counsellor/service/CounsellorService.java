package com.radhesham.counsellor.service;

import com.radhesham.counsellor.bean.DashBoardDetails;
import com.radhesham.counsellor.bean.request.LoginRequest;
import com.radhesham.counsellor.entity.Counsellor;

public interface CounsellorService {

    boolean isEmailExist(String email);

    boolean isContactExist(String contact);

    boolean registerUser(Counsellor userRegistrationRequest);

    Counsellor login(LoginRequest loginRequest);

    boolean isCounsellorIdExist(String id);

   }
