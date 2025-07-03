package com.radhesham.counsellor.service;

import com.radhesham.counsellor.bean.DashBoardDetails;
import com.radhesham.counsellor.bean.request.LoginRequest;
import com.radhesham.counsellor.common.ConstantPool;
import com.radhesham.counsellor.entity.Counsellor;
import com.radhesham.counsellor.entity.Enquiry;
import com.radhesham.counsellor.repo.CounsellorRepo;
import com.radhesham.counsellor.repo.EnquiryRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CounsellorServiceImpl implements CounsellorService {

    private static final Logger logger = LoggerFactory.getLogger(CounsellorRepo.class);
    @Autowired
    CounsellorRepo counsellorRepo;

    @Override
    public boolean isEmailExist(String email) {
        boolean flag = false;
        try {
            logger.info("Entry in isEmailExist()");
            Optional<Boolean> res = counsellorRepo.existsCounsellorByEmailId(email);
            if (res.isPresent()) {
                flag = res.get();
            }
            logger.info("Exit from isEmailExist()");
        } catch (Exception e) {
            flag = false;
            logger.error("Exception in isEmailExist() :", e);
        }
        return flag;
    }

    @Override
    public boolean isContactExist(String contact) {
        boolean flag = false;
        try {
            logger.info("Entry in isContactExist()");
            Optional<Boolean> res = counsellorRepo.existsCounsellorByContact(contact);
            if (res.isPresent()) {
                flag = res.get();
            }
            logger.info("Exit from isContactExist()");
        } catch (Exception e) {
            flag = false;
            logger.error("Exception in isContactExist() :", e);
        }
        return flag;
    }

    @Override
    public boolean registerUser(Counsellor userRegistrationRequest) {
        boolean flag = false;
        try {
            logger.info("Entry in registerUser()");
            Counsellor createdUser = counsellorRepo.save(userRegistrationRequest);
            if (Objects.nonNull(createdUser)) {
                flag = true;
            }
            logger.info("Exit from registerUser()");
        } catch (Exception e) {
            logger.error("Exception in registerUser() :", e);
        }
        return flag;
    }

    @Override
    public Counsellor login(LoginRequest loginRequest) {
        Counsellor counsellor = null;
        try {
            logger.info("Entry in login()");
            Optional<Counsellor> loginRes = counsellorRepo.findByEmailIdAndPassword(loginRequest.getUserName(), loginRequest.getPassword());
            if (loginRes.isPresent()) {
                counsellor = loginRes.get();
            }
            logger.info("Exit from login()");
        } catch (Exception e) {
            logger.error("Exception in login() :", e);
        }
        return counsellor;
    }

    @Override
    public boolean isCounsellorIdExist(String id) {
        boolean res = false;
        try {
            res = counsellorRepo.existsById(Integer.parseInt(id));
        } catch (Exception e) {
            logger.error("Exception in isCounsellorIdExist() :", e);
        }
        return res;
    }


}
