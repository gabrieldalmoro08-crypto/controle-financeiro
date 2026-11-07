package com.gabrieldalmoro.controlefinanceiro.service;

import com.gabrieldalmoro.controlefinanceiro.dto.request.RequestAtualizacaoUsuarioDTO;
import com.gabrieldalmoro.controlefinanceiro.dto.request.RequestCadastroUsuarioDTO;
import com.gabrieldalmoro.controlefinanceiro.dto.response.ResponseUsuarioDTO;
import com.gabrieldalmoro.controlefinanceiro.exception.EmailJaCadastradoException;
import com.gabrieldalmoro.controlefinanceiro.exception.RecursoNaoEncontradoException;
import com.gabrieldalmoro.controlefinanceiro.exception.RegraDeNegocioException;
import com.gabrieldalmoro.controlefinanceiro.model.Usuario;
import com.gabrieldalmoro.controlefinanceiro.model.enums.StatusUsuario;
import com.gabrieldalmoro.controlefinanceiro.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseUsuarioDTO cadastrar(RequestCadastroUsuarioDTO requisicao){

        String email = requisicao.email().trim().toLowerCase();

        if(repository.existsByEmail(email)){
            throw new EmailJaCadastradoException(email);
        }

        Usuario usuario = new Usuario();
        usuario.setNome(requisicao.nome());
        usuario.setEmail(email);
        usuario.setSenha(passwordEncoder.encode(requisicao.senha()));
        usuario.setStatus(StatusUsuario.ATIVO);

        Usuario salvo = repository.save(usuario);

        return paraResponse(salvo);
    }

    public ResponseUsuarioDTO atualizar(Long id, RequestAtualizacaoUsuarioDTO req){

        Usuario us = repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário", id));

        if(us.getStatus() != StatusUsuario.ATIVO){
            throw new RegraDeNegocioException("Só é possível atualizar um usuário ativo");
        }

        String email = req.email().trim().toLowerCase();

        if (repository.existsByEmailAndIdNot(email, id)) {
            throw new EmailJaCadastradoException(email);
        }

        us.setNome(req.nome());
        us.setEmail(email);

        Usuario salvar = repository.save(us);

        return paraResponse(salvar);
    }

    public void inativar(Long id){

        Usuario us = repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário", id));

        if(us.getStatus() != StatusUsuario.ATIVO){
            throw new RegraDeNegocioException("Só é possível inativar um usuário ativo");
        }

        us.setStatus(StatusUsuario.INATIVO);

        repository.save(us);
    }

    public List<ResponseUsuarioDTO> listar(){
        return repository.findAll().stream().map(this::paraResponse).toList();
    }

    public ResponseUsuarioDTO buscarPorId(Long id){

        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário", id));

        return paraResponse(usuario);
    }

    public void reativar(Long id){

        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário", id));

        if(usuario.getStatus() != StatusUsuario.INATIVO){
            throw new RegraDeNegocioException("Só é possível reativar um usuário inativo");
        }

        usuario.setStatus(StatusUsuario.ATIVO);

        repository.save(usuario);
    }

    private ResponseUsuarioDTO paraResponse(Usuario usuario){
        return new ResponseUsuarioDTO(
            usuario.getId(),
            usuario.getNome(),
            usuario.getEmail(),
            usuario.getStatus()
        );
    }

}
