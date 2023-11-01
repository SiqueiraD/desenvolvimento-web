package src.main.webapp.service;

import java.lang.reflect.Constructor;
import java.sql.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public abstract class DaoGenerico<T> {

    // Atributo que armazena as propriedades e valores da entidade
    protected Hashtable<String, Object> propriedades;

    // Método construtor
    public DaoGenerico() {
        this.propriedades = new Hashtable<String, Object>();
    }

    // Método construtor que recebe a Hashtable como parâmetro
    public DaoGenerico(Hashtable<String, Object> propriedades) {
        this.propriedades = propriedades;
    }

    // Método abstrato para obter o nome da tabela no banco de dados
    public abstract String getNomeTabela();

    // Método abstrato para obter o nome do campo id no banco de dados
    public abstract String getNomeId();

    // Método abstrato para obter o valor do campo id
    public abstract Object getValorId();

    // Método abstrato para obter o valor do campo id
    public abstract Class<T> getTipoClass();

    // Método abstrato para converter um ResultSet em um objeto do tipo T
    public T converterResultSet(ResultSet rs) throws SQLException {
        return DaoConverterStatic.converterResultSetParaObjeto(rs, this.getTipoClass());
    }

    ;

    // Método para obter uma conexão com o banco de dados MySQL 8
    public Connection getConnection() throws SQLException {
        Conexao con = new Conexao();
        return con.getConexao();
    }

    // Método para inserir um objeto do tipo T no banco de dados
    public void inserir() throws SQLException {
        // Montar a query SQL com os nomes e valores das propriedades
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO ");
        query.append(getNomeTabela());
        query.append(" (");
        if (!propriedades.keySet().contains(getNomeId())) {
            query.append(getNomeId());
            query.append(", ");
        }
        for (String nome : this.propriedades.keySet()) {
            query.append(nome);
            query.append(", ");
        }
        query.delete(query.length() - 2, query.length()); // Remover a última vírgula
        query.append(") VALUES (");
        if (!propriedades.keySet().contains(getNomeId())) {
            query.append("?, ");
        }
        for (Object valor : this.propriedades.values()) {
            query.append("?, "); // Usar prepared statement para evitar SQL injection
        }
        query.delete(query.length() - 2, query.length()); // Remover a última vírgula
        query.append(")");

        // Obter uma conexão com o banco de dados
        Connection conn = getConnection();

        // Criar um prepared statement com a query montada
        PreparedStatement ps = conn.prepareStatement(query.toString());


        // Preencher os valores dos parâmetros na ordem correta
        int i = 1;
        if (!propriedades.keySet().contains(getNomeId())) {
            ps.setObject(i, novoId());
            i++;
        }
        for (Object valor : this.propriedades.values()) {
            ps.setObject(i, valor);
            i++;
        }

        // Executar o prepared statement e fechar a conexão
        ps.executeUpdate();
        conn.close();
    }

    // Método para atualizar um objeto do tipo T no banco de dados
    public void atualizar() throws SQLException {
        // Montar a query SQL com os nomes e valores das propriedades
        StringBuilder query = new StringBuilder();
        query.append("UPDATE ");
        query.append(getNomeTabela());
        query.append(" SET ");
        for (String nome : propriedades.keySet()) {
            if (!nome.equals(getNomeId())) { // Não atualizar o campo id
                query.append(nome);
                query.append(" = ?, ");
            }
        }
        query.delete(query.length() - 2, query.length()); // Remover a última vírgula
        query.append(" WHERE ");
        query.append(getNomeId());
        query.append(" = ?");

        // Obter uma conexão com o banco de dados
        Connection conn = getConnection();

        // Criar um prepared statement com a query montada
        PreparedStatement ps = conn.prepareStatement(query.toString());

        // Preencher os valores dos parâmetros na ordem correta
        int i = 1;
        for (Map.Entry<String, Object> entry : propriedades.entrySet()) {
            if (!entry.getKey().equals(getNomeId())) { // Não atualizar o campo id
                ps.setObject(i, entry.getValue());
                i++;
            } else { // Guardar o valor do campo id para o final da query
                Object id = entry.getValue();
            }
        }

        ps.setObject(i, getValorId()); // Preencher o valor do campo id no final da query

        // Executar o prepared statement e fechar a conexão
        ps.executeUpdate();
        conn.close();
    }

    // Método para remover um objeto do tipo T do banco de dados
    public void remover() throws SQLException {
        // Montar a query SQL com o nome e valor do campo id
        StringBuilder query = new StringBuilder();
        query.append("DELETE FROM ");
        query.append(getNomeTabela());
        query.append(" WHERE ");
        query.append(getNomeId());
        query.append(" = ?");

        // Obter uma conexão com o banco de dados
        Connection conn = getConnection();

        // Criar um prepared statement com a query montada
        PreparedStatement ps = conn.prepareStatement(query.toString());

        // Preencher o valor do parâmetro com o valor do campo id
        ps.setObject(1, getValorId());

        // Executar o prepared statement e fechar a conexão
        ps.executeUpdate();
        conn.close();
    }

    // Método para consultar um objeto do tipo T pelo seu id no banco de dados
    public T consultar(Object id) throws SQLException {
        // Montar a query SQL com o nome da tabela e do campo id
        StringBuilder query = new StringBuilder();
        query.append("SELECT * FROM ");
        query.append(getNomeTabela());
        query.append(" WHERE ");
        query.append(getNomeId());
        query.append(" = ?");

        // Obter uma conexão com o banco de dados
        Connection conn = getConnection();

        // Criar um prepared statement com a query montada
        PreparedStatement ps = conn.prepareStatement(query.toString());

        // Preencher o valor do parâmetro com o valor do id
        ps.setObject(1, id);

        // Executar o prepared statement e obter o resultado
        ResultSet rs = ps.executeQuery();

        // Verificar se há algum resultado e converter em um objeto do tipo T
        T obj = null;
        if (rs.next()) {
            obj = converterResultSet(rs);
        }

        // Fechar a conexão e retornar o objeto
        conn.close();
        return obj;
    }

    // Método para listar todos os objetos do tipo T do banco de dados
    public List<T> listar() throws SQLException {
        // Montar a query SQL com o nome da tabela
        StringBuilder query = new StringBuilder();
        query.append("SELECT * FROM ");
        query.append(getNomeTabela());
        if (!this.propriedades.isEmpty()) {
            query.append(" WHERE ");
            for (String nome : this.propriedades.keySet()) {
                query.append(nome);
                query.append(" = ?, ");
            }
            query.delete(query.length() - 2, query.length());
        }

        // Obter uma conexão com o banco de dados
        Connection conn = getConnection();

        // Criar um prepared statement com a query montada
        PreparedStatement ps = conn.prepareStatement(query.toString());
        int i = 1;
        for (Object valor : this.propriedades.values()) {
            ps.setObject(i, valor);
            i++;
        }

        // Executar o statement e obter o resultado
        ResultSet rs = ps.executeQuery();

        // Criar uma lista para armazenar os objetos do tipo T
        List<T> lista = new ArrayList<>();

        // Iterar sobre o resultado e converter cada linha em um objeto do tipo T
        while (rs.next()) {
            T obj = converterResultSet(rs);
            lista.add(obj);
        }

        // Fechar a conexão e retornar a lista
        conn.close();
        return lista;
    }

    public int novoId() throws SQLException {
        int retorno = 1;
        // Montar a query SQL com o nome da tabela
        StringBuilder query = new StringBuilder();
        query.append("SELECT ");
        query.append(getNomeId());
        query.append(" FROM ");
        query.append(getNomeTabela());
        query.append(" ORDER BY ");
        query.append(getNomeId());
        query.append(" DESC LIMIT 1");

        // Obter uma conexão com o banco de dados
        Connection conn = getConnection();

        // Criar um statement com a query montada
        Statement st = conn.createStatement();

        // Executar o statement e obter o resultado
        ResultSet rs = st.executeQuery(query.toString());
        // Iterar sobre o resultado e converter cada linha em um objeto do tipo T
        while (rs.next()) {
            retorno = rs.getInt(getNomeId());
        }

        // Fechar a conexão e retornar a lista
        conn.close();
        return retorno + 1;
    }
}
