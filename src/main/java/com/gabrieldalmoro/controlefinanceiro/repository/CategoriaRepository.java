package com.gabrieldalmoro.controlefinanceiro.repository;

import com.gabrieldalmoro.controlefinanceiro.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    boolean existsByDescricaoIgnoreCaseAndUsuarioId(String descricao, Long usuario_id);

    boolean existsByDescricaoIgnoreCaseAndUsuarioIdAndIdNot(String descricao, Long usuario_id, Long id);

    List<Categoria> findByUsuarioId(Long usuarioId);
}
