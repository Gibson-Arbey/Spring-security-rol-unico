package com.springsecurity.unomuchos.services.implementations;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.springsecurity.unomuchos.exception.EmailExistsException;
import com.springsecurity.unomuchos.model.dto.UsuarioDTO;
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

    @Autowired
    private  PasswordEncoder passwordEncoder;



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
    public void guardarAuxiliar(UsuarioDTO usuarioDTO) {
        guardarUsuario(usuarioDTO, "ROL_AUXILIAR");
    }
    
    @Override
    public void guardarGerente(UsuarioDTO usuarioDTO) {
        guardarUsuario(usuarioDTO, "ROL_GERENTE");
    }
    
    @Override
    public void guardarCliente(UsuarioDTO usuarioDTO) {
        guardarUsuario(usuarioDTO, "ROL_CLIENTE");
    }
    
    @Override
    public void guardarUsuario(UsuarioDTO usuarioDTO, String rolNombre) {
        if (usuarioRepository.findByEmail(usuarioDTO.getEmail()) != null) {
            throw new EmailExistsException("El correo electronico ya existe");
        }
    
        UsuarioEntity userEntity = new UsuarioEntity();
        BeanUtils.copyProperties(usuarioDTO, userEntity);
    
        try {
            userEntity.setContraseniaEncriptada(passwordEncoder.encode(usuarioDTO.getContrasenia()));
            userEntity.setRol(rolRepository.findByNombre(rolNombre));
            usuarioRepository.save(userEntity);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar al usuario");
        }
    }
}
