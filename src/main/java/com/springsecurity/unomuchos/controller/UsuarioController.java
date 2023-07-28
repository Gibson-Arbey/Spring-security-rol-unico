package com.springsecurity.unomuchos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springsecurity.unomuchos.model.dto.UsuarioDTO;
import com.springsecurity.unomuchos.services.implementations.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/guardarAuxiliar")
    public ResponseEntity<String> guardarAuxiliar(@RequestBody UsuarioDTO usuarioDTO) {
        try {
            usuarioService.guardarAuxiliar(usuarioDTO);
            String response = "Auxiliar registrado con exito";
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            throw e;
        }
    }

    @PostMapping("/guardarGerente")
    public ResponseEntity<String> guardarGerente(@RequestBody UsuarioDTO usuarioDTO) {
        try {
            usuarioService.guardarAuxiliar(usuarioDTO);
            String response = "Gerente registrado con exito";
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            throw e;
        }
    }

    @PostMapping("/guardarCliente")
    public ResponseEntity<String> guardarCliente(@RequestBody UsuarioDTO usuarioDTO) {
        try {
            usuarioService.guardarAuxiliar(usuarioDTO);
            String response = "Cliente registrado con exito";
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            throw e;
        }
    }
}
