package com.gabrieldalmoro.controlefinanceiro.repository;

import com.gabrieldalmoro.controlefinanceiro.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    boolean existsByEmail(String email);

    Optional<Usuario> findByEmail(String email);

    boolean existsByEmailAndIdNot(String email, Long id);

    List<Usuario> findByAll();
}
