package dev.bstk.okk.crudmodaantiga.core;

public class JDBCConnectionException extends RuntimeException {

    public JDBCConnectionException(final String mensagem, final Throwable throwable) {
        super(mensagem, throwable);
    }
}
