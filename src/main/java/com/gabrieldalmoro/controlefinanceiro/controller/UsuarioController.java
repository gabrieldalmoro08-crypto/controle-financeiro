package com.gabrieldalmoro.controlefinanceiro.controller;


import com.gabrieldalmoro.controlefinanceiro.dto.request.RequestAtualizacaoUsuarioDTO;
import com.gabrieldalmoro.controlefinanceiro.dto.request.RequestCadastroUsuarioDTO;
import com.gabrieldalmoro.controlefinanceiro.dto.response.ResponseUsuarioDTO;
import com.gabrieldalmoro.controlefinanceiro.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {


    private UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ResponseUsuarioDTO> cadastrar(@Valid @RequestBody RequestCadastroUsuarioDTO req){
        ResponseUsuarioDTO novo = service.cadastrar(req);

        return ResponseEntity.status(HttpStatus.CREATED).body(novo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseUsuarioDTO> atualizar(@PathVariable Long id, @Valid @RequestBody RequestAtualizacaoUsuarioDTO dto){
        ResponseUsuarioDTO atualizado = service.atualizar(id, dto);

        return ResponseEntity.ok(atualizado);
    }

    @PatchMapping("/{id}/inativar")
    public ResponseEntity<Void> inativar(@PathVariable Long id){
        service.inativar(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ResponseUsuarioDTO>> listar(){
        List<ResponseUsuarioDTO> lista = service.listar();

        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseUsuarioDTO> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PatchMapping("/{id}/reativar")
    public ResponseEntity<Void> reativar(@PathVariable Long id){
        service.reativar(id);

        return ResponseEntity.noContent().build();
    }

}
