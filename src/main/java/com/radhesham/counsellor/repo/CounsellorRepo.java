package com.radhesham.counsellor.repo;

import com.radhesham.counsellor.entity.Counsellor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CounsellorRepo extends JpaRepository<Counsellor,Integer> {

     Optional<Boolean> existsCounsellorByEmailId(String emailId);

     Optional<Boolean> existsCounsellorByContact(String contact);

     Optional<Counsellor> findByEmailIdAndPassword(String username,String password);

     Optional<Counsellor> findByName(String username);
}
