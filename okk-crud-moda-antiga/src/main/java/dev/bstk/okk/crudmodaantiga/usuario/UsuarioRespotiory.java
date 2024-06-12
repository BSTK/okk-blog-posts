package dev.bstk.okk.crudmodaantiga.usuario;

import dev.bstk.okk.crudmodaantiga.core.JDBCConnection;
import dev.bstk.okk.crudmodaantiga.core.Respotiory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRespotiory implements Respotiory<Usuario> {

    private static final String QUERY_LISTAR_USUARIOS = "SELECT * FROM USUARIO";

    private static final String QUERY_USUARIO_POR_ID =
        """
        SELECT *
        FROM USUARIO u 
        WHERE u.ID = ?
        """;

    @Override
    public List<Usuario> listar() {
        try (final Connection conexao = JDBCConnection.conexao();
             final PreparedStatement preparedStatement = conexao.prepareStatement(QUERY_LISTAR_USUARIOS);
             final ResultSet resultSet = preparedStatement.executeQuery()) {

            final List<Usuario> usuarios = new ArrayList<>();
            while (resultSet.next()) {
                final Usuario usuario = new Usuario(
                    resultSet.getLong("ID"),
                    resultSet.getString("NOME"),
                    resultSet.getString("EMAIL"));

                usuarios.add(usuario);
            }

            return usuarios;
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public Usuario obterPorId(final Long usuarioId) {
        try (final Connection conexao = JDBCConnection.conexao();
            final PreparedStatement preparedStatement = conexao.prepareStatement(QUERY_USUARIO_POR_ID);
            final ResultSet resultSet = preparedStatement.executeQuery()) {

            preparedStatement.setLong(1, usuarioId);

            if (!resultSet.next()) {
                throw new IllegalArgumentException(
                    "Não há usuários cadastrados para Id = [ %s ] ".formatted(usuarioId)
                );
            }

            resultSet.next();

            return new Usuario(
                resultSet.getLong("ID"),
                resultSet.getString("NOME"),
                resultSet.getString("EMAIL"));
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public Usuario salvar(final Usuario usuario) {
        return null;
    }

    @Override
    public Usuario atualizar(final Usuario usuario) {
        return null;
    }

    @Override
    public void excluir(final Usuario usuario) {

    }

    @Override
    public void excluir(final Long objetoId) {

    }
}
