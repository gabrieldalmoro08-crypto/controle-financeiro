package com.gabrieldalmoro.controlefinanceiro.controller;

import com.gabrieldalmoro.controlefinanceiro.dto.request.RequestAtualizarCategoriaDTO;
import com.gabrieldalmoro.controlefinanceiro.dto.request.RequestCategoriaDTO;
import com.gabrieldalmoro.controlefinanceiro.dto.response.ResponseCategoriaDTO;
import com.gabrieldalmoro.controlefinanceiro.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios/{usuarioId}/categorias")
public class CategoriaController {

    private CategoriaService categoriaService;


    @PostMapping
    public ResponseEntity<ResponseCategoriaDTO> cadastrar(@PathVariable Long usuarioId, @Valid @RequestBody RequestCategoriaDTO req) {

        ResponseCategoriaDTO novo = categoriaService.cadastrar(usuarioId, req);

        return ResponseEntity.status(HttpStatus.CREATED).body(novo);
    }

    @PatchMapping("/{categoriaId}/inativar")
    public ResponseEntity<Void> inativar(@PathVariable Long usuarioId, @PathVariable Long categoriaId) {

        categoriaService.inativar(usuarioId, categoriaId);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{categoriaId}")
    public ResponseEntity<ResponseCategoriaDTO> atualizar(@PathVariable Long usuarioId, @PathVariable Long categoriaId, @Valid @RequestBody RequestAtualizarCategoriaDTO req) {

        ResponseCategoriaDTO atualizado = categoriaService.atualizar(usuarioId, categoriaId, req);

        return ResponseEntity.ok().body(atualizado);
    }

    @PatchMapping("/{categoriaId}/reativar")
    public ResponseEntity<Void> reativar(@PathVariable Long usuarioId, @PathVariable Long categoriaId){

        categoriaService.reativar(usuarioId, categoriaId);

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ResponseCategoriaDTO>> listarTodas(@PathVariable Long usuarioId){

        List<ResponseCategoriaDTO> lista = categoriaService.listar(usuarioId);

        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{categoriaId}")
    public ResponseEntity<ResponseCategoriaDTO> buscarPorId(@PathVariable Long usuarioId, @PathVariable Long categoriaId){

        ResponseCategoriaDTO buscado = categoriaService.buscarPorId(usuarioId, categoriaId);

        return ResponseEntity.ok(buscado);
    }

}
