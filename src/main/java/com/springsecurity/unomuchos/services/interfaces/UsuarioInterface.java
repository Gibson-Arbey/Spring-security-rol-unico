package com.springsecurity.unomuchos.services.interfaces;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.springsecurity.unomuchos.model.dto.UsuarioDTO;

public interface UsuarioInterface extends UserDetailsService{
    
    public void guardarAuxiliar(UsuarioDTO usuarioDTO);

    public void guardarGerente(UsuarioDTO usuarioDTO);

    public void guardarCliente(UsuarioDTO usuarioDTO);

    public void guardarUsuario(UsuarioDTO usuarioDTO, String rolNombre);
}
