package com.springsecurity.unomuchos.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDTO {
    /**
     * El ID del usuario.
     */
    private Integer id;

    /**
     * El correo electrónico del usuario.
     */
    private String email;

    /**
     * La contraseña del usuario (sin encriptar).
     */
    private String contrasenia;

    /**
     * La contraseña del usuario encriptada.
     */
    private String contrasenisaEncriptada;
}
