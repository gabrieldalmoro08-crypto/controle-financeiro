package com.gabrieldalmoro.controlefinanceiro.service;

import com.gabrieldalmoro.controlefinanceiro.dto.request.RequestAtualizarCategoriaDTO;
import com.gabrieldalmoro.controlefinanceiro.dto.request.RequestCategoriaDTO;
import com.gabrieldalmoro.controlefinanceiro.dto.response.ResponseCategoriaDTO;
import com.gabrieldalmoro.controlefinanceiro.exception.RecursoNaoEncontradoException;
import com.gabrieldalmoro.controlefinanceiro.exception.RegraDeNegocioException;
import com.gabrieldalmoro.controlefinanceiro.model.Categoria;
import com.gabrieldalmoro.controlefinanceiro.model.Usuario;
import com.gabrieldalmoro.controlefinanceiro.model.enums.StatusCategoria;
import com.gabrieldalmoro.controlefinanceiro.model.enums.StatusUsuario;
import com.gabrieldalmoro.controlefinanceiro.repository.CategoriaRepository;
import com.gabrieldalmoro.controlefinanceiro.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final UsuarioRepository usuarioRepository;

    public CategoriaService(CategoriaRepository categoriaRepository, UsuarioRepository usuarioRepository) {
        this.categoriaRepository = categoriaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public ResponseCategoriaDTO cadastrar(Long usuarioId, RequestCategoriaDTO req){

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário", usuarioId));

        if(usuario.getStatus() != StatusUsuario.ATIVO){
            throw new RegraDeNegocioException("Só um usuário ativo pode criar categorias");
        }

        String desc = req.descricao().trim();

        if(categoriaRepository.existsByDescricaoIgnoreCaseAndUsuarioId(desc, usuarioId)){
            throw new RegraDeNegocioException("Você já possui uma categoria com a descrição: " + desc);
        }

        Categoria categoria = new Categoria();
        categoria.setDescricao(desc);
        categoria.setStatus(StatusCategoria.ATIVA);
        categoria.setTipo(req.tipo());
        categoria.setUsuario(usuario);

        Categoria salva = categoriaRepository.save(categoria);

        return paraResponse(salva);
    }

    public ResponseCategoriaDTO atualizar(Long usuarioId, Long categoriaId, RequestAtualizarCategoriaDTO req){

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário", usuarioId));

        if(usuario.getStatus() != StatusUsuario.ATIVO){
            throw new RegraDeNegocioException("Só um usuário ativo pode criar categorias");
        }

        Categoria categoria = categoriaRepository.findById(categoriaId)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Categoria", categoriaId));

        if (!categoria.getUsuario().getId().equals(usuarioId)) {
            throw new RegraDeNegocioException("Esta categoria não pertence ao usuário informado");
        }

        String desc = req.descricao().trim();

        if(categoriaRepository.existsByDescricaoIgnoreCaseAndUsuarioIdAndIdNot(desc, usuarioId, req.id())){
            throw new RegraDeNegocioException("Você já possui uma categoria com a descrição: " + desc);
        }

        categoria.setDescricao(desc);

        Categoria salva = categoriaRepository.save(categoria);

        return paraResponse(salva);
    }

    public void inativar(Long idUsuario, Long idCategoria){

        Categoria cat = categoriaRepository.findById(idCategoria)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Categoria", idCategoria));

        if(!cat.getUsuario().getId().equals(idUsuario)){
            throw new RegraDeNegocioException("Esta categoria não pertence ao usuário informado");
        }

        if(cat.getStatus() != StatusCategoria.ATIVA ){
            throw new RegraDeNegocioException("Só é possível inativar uma categoria ativa");
        }

        cat.setStatus(StatusCategoria.INATIVA);

        categoriaRepository.save(cat);
    }

    public List<ResponseCategoriaDTO> listar(Long usuarioId){

        usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário", usuarioId));

        return categoriaRepository.findByUsuarioId(usuarioId).stream().map(this :: paraResponse).toList();
    }

    public ResponseCategoriaDTO buscarPorId(Long usuarioId, Long idCategoria){

        Categoria cat = categoriaRepository.findById(idCategoria)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Categoria", idCategoria));

        if(!cat.getUsuario().getId().equals(usuarioId)){
            throw new RegraDeNegocioException("Esta categoria não pertence ao usuário informado");
        }

        return paraResponse(cat);
    }

    public void reativar(Long idUsuario, Long idCategoria){

        Categoria cat = categoriaRepository.findById(idCategoria)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Categoria", idCategoria));

        if(!cat.getUsuario().getId().equals(idUsuario)){
            throw new RegraDeNegocioException("Essa categoria não pertence ao usuário informado");
        }

        cat.setStatus(StatusCategoria.ATIVA);

        categoriaRepository.save(cat);
    }

    private ResponseCategoriaDTO paraResponse(Categoria categoria){
        return new ResponseCategoriaDTO(
                categoria.getId(),
                categoria.getDescricao(),
                categoria.getTipo(),
                categoria.getStatus()
        );
    }
}
