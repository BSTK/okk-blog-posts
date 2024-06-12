package dev.bstk.okk.crudmodaantiga.usuario;

import dev.bstk.okk.crudmodaantiga.comentario.Comentario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Usuario implements Serializable {

    private final Long usuarioId;
    private final String nome;
    private final String email;
    private final List<Comentario> comentarios;

    public Usuario(final String nome, final String email) {
        this(null, nome, email, new ArrayList<>());
    }

    public Usuario(final Long usuarioId, final String nome, final String email) {
        this(usuarioId, nome, email, new ArrayList<>());
    }

    public Usuario(final Long usuarioId,
                   final String nome,
                   final String email,
                   final List<Comentario> comentarios) {
        this.usuarioId = usuarioId;
        this.nome = nome;
        this.email = email;
        this.comentarios = comentarios;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
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

        final Usuario usuario = (Usuario) object;
        return Objects.equals(usuarioId, usuario.usuarioId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuarioId);
    }
}
