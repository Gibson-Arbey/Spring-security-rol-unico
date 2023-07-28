package com.springsecurity.unomuchos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springsecurity.unomuchos.model.entity.RolEntity;

@Repository
public interface RolRepository extends JpaRepository<RolEntity, Long>{

    RolEntity findByNombre(String nombre);
    
}
