package com.springsecurity.unomuchos.services.implementations;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springsecurity.unomuchos.exception.EmailExistsException;
import com.springsecurity.unomuchos.model.entity.UsuarioEntity;
import com.springsecurity.unomuchos.repository.RolRepository;
import com.springsecurity.unomuchos.repository.UsuarioRepository;
import com.springsecurity.unomuchos.services.interfaces.UsuarioInterface;

@Service
public class UsuarioService implements UsuarioInterface {

    @Autowired
    private  UsuarioRepository usuarioRepository;

    @Autowired
    private  RolRepository rolRepository;

    

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsuarioEntity userEntity = usuarioRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario " + username + " no existe."));

        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        if (userEntity.getRol() != null) {
            authorities.add(new SimpleGrantedAuthority(userEntity.getRol().getNombre()));
        } else {
            throw new UsernameNotFoundException(
                    "Error en el Login: usuario '" + username + "' no tiene roles asignados!");
        }
        return new User(userEntity.getEmail(), userEntity.getContraseniaEncriptada(), authorities);
    }
    
    @Override
    public void guardarUsuario(UsuarioEntity usuarioEntity, String rolNombre) {
        if (usuarioRepository.findByEmail(usuarioEntity.getEmail()).isPresent()) {
            throw new EmailExistsException("El correo electronico ya existe");
        }

        try {
            usuarioEntity.setRol(rolRepository.findByNombre(rolNombre));
            usuarioRepository.save(usuarioEntity);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar al usuario");
        }
    }
}
