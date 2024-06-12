package dev.bstk.okk.crudmodaantiga.core;

import java.sql.Connection;
import java.sql.DriverManager;

public final class JDBCConnection {

    private JDBCConnection() { }

    public static Connection conexao() {
        try {
            Class.forName("DRIVE_DE_CONEXAO");
            return DriverManager.getConnection("URL_CONEXAO_BANCO_DADOS");
        } catch (Exception ex) {
            throw new JDBCConnectionException(ex.getMessage(), ex);
        }
    }
}
