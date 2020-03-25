package com.cubic3.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cubic3.entities.ContactInfoEntity;

@Repository
public interface ContactInfoRepository extends CrudRepository<ContactInfoEntity, Integer> {

}
