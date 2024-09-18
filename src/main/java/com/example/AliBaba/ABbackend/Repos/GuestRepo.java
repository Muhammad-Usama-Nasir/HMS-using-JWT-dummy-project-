package com.example.AliBaba.ABbackend.Repos;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.AliBaba.ABbackend.ORM.ORMSaveGuest;

@Repository
public interface GuestRepo extends JpaRepository<ORMSaveGuest, Long> {

}
