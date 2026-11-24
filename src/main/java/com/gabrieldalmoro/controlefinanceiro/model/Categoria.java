package com.gabrieldalmoro.controlefinanceiro.model;


import com.gabrieldalmoro.controlefinanceiro.model.enums.StatusCategoria;
import com.gabrieldalmoro.controlefinanceiro.model.enums.TipoCategoria;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "categoria",
       uniqueConstraints = @UniqueConstraint(
               name = "uk_categoria_usuario_descricao",
               columnNames = {"usuario_id", "descricao"}
       )
    )
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusCategoria status;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoCategoria tipo;

    @ManyToOne(optional = false)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;
}
