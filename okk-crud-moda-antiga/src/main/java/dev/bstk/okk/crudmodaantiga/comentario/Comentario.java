package dev.bstk.okk.crudmodaantiga.comentario;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class Comentario implements Serializable {

    private final Long comentarioId;
    private final String texto;
    private final LocalDateTime data;

    public Comentario(final String texto) {
        this(null, texto, LocalDateTime.now());
    }

    public Comentario(final String texto, final LocalDateTime data) {
        this(null, texto, data);
    }

    public Comentario(final Long comentarioId,
                      final String texto,
                      final LocalDateTime data) {
        this.comentarioId = comentarioId;
        this.texto = texto;
        this.data = data;
    }

    public Long getComentarioId() {
        return comentarioId;
    }

    public String getTexto() {
        return texto;
    }

    public LocalDateTime getData() {
        return data;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        final Comentario comentario = (Comentario) object;
        return Objects.equals(comentarioId, comentario.comentarioId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(comentarioId);
    }
}
