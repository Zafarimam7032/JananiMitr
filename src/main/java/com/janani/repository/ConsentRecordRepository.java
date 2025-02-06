package com.janani.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.janani.entity.ConsentRecord;

@Repository
public interface ConsentRecordRepository extends JpaRepository<ConsentRecord, Integer> {

}
