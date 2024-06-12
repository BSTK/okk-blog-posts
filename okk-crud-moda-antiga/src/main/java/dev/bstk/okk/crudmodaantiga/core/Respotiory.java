package dev.bstk.okk.crudmodaantiga.core;

import java.util.List;

public interface Respotiory<T>  {

    /**
     * Lista de objetos cadastrados
     * @return
     */
    List<T> listar();

    /**
     * Obtem um determinado objeto pelo seu id
     * @param objetoId
     * @return
     */
    T obterPorId(final Long objetoId);

    /**
     * Salva um objeto no banco
     * @param objeto
     * @return
     */
    T salvar(final T objeto);

    /**
     * Atualiza dados de um objeto que já esteja salvo no banco
     * @param objeto
     * @return
     */
    T atualizar(final T objeto);

    /**
     * Excluir um determinado objeto
     * @param objeto
     */
    void excluir(final T objeto);

    /**
     * Excluir um determinado objeto, dado o id desse objeto
     * @param objetoId
     */
    void excluir(final Long objetoId);
}
