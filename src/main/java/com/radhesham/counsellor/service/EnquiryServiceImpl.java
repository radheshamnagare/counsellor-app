package com.radhesham.counsellor.service;

import com.radhesham.counsellor.bean.DashBoardDetails;
import com.radhesham.counsellor.bean.request.EnquiryFilterRequest;
import com.radhesham.counsellor.common.CommonValidator;
import com.radhesham.counsellor.common.ConstantPool;
import com.radhesham.counsellor.entity.Counsellor;
import com.radhesham.counsellor.entity.Enquiry;
import com.radhesham.counsellor.repo.EnquiryRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class EnquiryServiceImpl implements EnquiryService {

    private static final Logger logger = LoggerFactory.getLogger(EnquiryServiceImpl.class);
    @Autowired
    private EnquiryRepo enquiryRepo;

    @Override
    public boolean addEnquiry(Enquiry enquiry) {
        boolean flag = true;
        try {
            logger.info("Entry in addEnquiry()");
            if (Objects.nonNull(enquiry)) {
                Enquiry addEnquiry = enquiryRepo.save(enquiry);
                if (Objects.isNull(addEnquiry))
                    flag = false;
            } else {
                logger.info("addEnquiry() :enquiry details is null");
                flag = false;
            }
            logger.info("Exit from addEnquiry()");
        } catch (Exception e) {
            flag = false;
            logger.error("Exception in addEnquiry() :", e);
        }
        return flag;
    }

    @Override
    public List<Enquiry> getEnquiryByFilter(EnquiryFilterRequest enquiryFilterRequest) {
        List<Enquiry> enquiryList = null;
        try {
            logger.info("Entry in getEnquiryByFilter()");
            Enquiry enquiry = new Enquiry();
            enquiry.setEnquiryId(null);
            Counsellor counsellor = new Counsellor();
            counsellor.setCounsellorId(enquiryFilterRequest.getEnquiryId());
            enquiry.setCounsellor(counsellor);
            if (CommonValidator.isEmpty(enquiry.getName()))
                enquiry.setName(null);
            else
                enquiry.setName(enquiry.getName());
            if (CommonValidator.isEmpty(enquiryFilterRequest.getCourse()))
                enquiry.setCourse(null);
            else
                enquiry.setCourse(enquiryFilterRequest.getCourse());

            if (CommonValidator.isEmpty(enquiryFilterRequest.getClassMode()))
                enquiry.setClassMode(null);
            else
                enquiry.setClassMode(enquiryFilterRequest.getClassMode());

            if (CommonValidator.isEmpty(enquiryFilterRequest.getStatus()))
                enquiry.setStatus(null);
            else
                enquiry.setStatus(enquiryFilterRequest.getStatus());

            enquiryList = enquiryRepo.findAll(Example.of(enquiry));
            logger.info("Exit from getEnquiryByFilter()");
        } catch (Exception e) {
            logger.error("Exception in getEnquiryByFilter() :", e);
        }
        return enquiryList != null ? enquiryList : List.of();
    }

    @Override
    public boolean isEnquiryIdExists(int id) {
        try {
            return enquiryRepo.existsById(id);
        } catch (Exception e) {
            logger.error("Exception in isEnquiryIdExists() ", e);
        }
        return false;
    }

    @Override
    public DashBoardDetails getDashBoardDetails(String counsellorId) {
        DashBoardDetails dashBoardDetails = new DashBoardDetails();
        try {
            logger.info("Entry in getDashBoardDetails()");
            Enquiry _enquiry = new Enquiry();
            Counsellor _counsellor = new Counsellor();
            _counsellor.setCounsellorId(Integer.valueOf(counsellorId));
            _enquiry.setCounsellor(_counsellor);
            List<Enquiry> enquiryList = enquiryRepo.findAll(Example.of(_enquiry));
            int totalEnquiry = enquiryList.size();
            int openEnquiry = enquiryList.stream().filter(e -> e.getStatus().equals(ConstantPool.STATUS_OPEN)).collect(Collectors.toList()).size();
            int enrolledEnquiry = enquiryList.stream().filter(e -> e.getStatus().equals(ConstantPool.STATUS_ENROLLED)).collect(Collectors.toList()).size();
            int lostEnquiry = enquiryList.stream().filter(e -> e.getStatus().equals(ConstantPool.STATUS_LOST)).collect(Collectors.toList()).size();

            dashBoardDetails.setTotalEnquiry(totalEnquiry);
            dashBoardDetails.setOpenEnquiry(openEnquiry);
            dashBoardDetails.setEnrolledEnquiry(enrolledEnquiry);
            dashBoardDetails.setLostEnquiry(lostEnquiry);
            logger.info("Exit from getDashBoardDetails()");
        } catch (Exception e) {
            logger.error("Exception in getDashBoardDetails() :", e);
        }
        return dashBoardDetails;
    }
}
