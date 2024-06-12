package dev.bstk.okk.crudmodaantiga.produto;

import dev.bstk.okk.crudmodaantiga.comentario.Comentario;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Produto implements Serializable {

    private final Long produtoId;
    private final String nome;
    private final String descricao;
    private final BigDecimal preco;
    private final List<Comentario> comentarios;

    public Produto(final String nome,
                   final String descricao,
                   final BigDecimal preco) {
        this(null, nome, descricao, preco, new ArrayList<>());
    }

    public Produto(final Long produtoId,
                   final String nome,
                   final String descricao,
                   final BigDecimal preco,
                   final List<Comentario> comentarios) {
        this.produtoId = produtoId;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.comentarios = comentarios;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        final Produto produto = (Produto) object;
        return Objects.equals(produtoId, produto.produtoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(produtoId);
    }
}
