package com.springsecurity.unomuchos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springsecurity.unomuchos.model.entity.RolEntity;

public interface RolRepository extends JpaRepository<RolEntity, Long>{

    RolEntity findByNombre(String nombre);
    
}
