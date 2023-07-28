package com.springsecurity.unomuchos.services.interfaces;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.springsecurity.unomuchos.model.entity.UsuarioEntity;

public interface UsuarioInterface extends UserDetailsService{

    public void guardarUsuario(UsuarioEntity usuarioEntity, String rolNombre);
}
