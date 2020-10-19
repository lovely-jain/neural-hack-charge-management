package com.chargemanag1.bankmanag1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chargemanag1.bankmanag1.entity.Rules;
  
/*
 *  repository for rules
 */
@Repository
public interface RuleRepository   extends JpaRepository<Rules,Long>{
     List<Rules> findAllByStatus(boolean status);
     List<Rules> findByCodeAndStatus(long code,boolean status);
     void deleteByCodeAndStatus(long code,boolean status);
     
}
