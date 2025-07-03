package com.radhesham.counsellor.repo;

import com.radhesham.counsellor.entity.Enquiry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnquiryRepo extends JpaRepository<Enquiry,Integer> {

}
