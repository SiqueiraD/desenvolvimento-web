package src.main.webapp.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private Connection conexao;

    public Conexao(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexao = DriverManager.getConnection("jdbc:mysql://db-server:3306/dbjava?user=user&password=user123&useSSL=false&allowPublicKeyRetrieval=true&useUnicode=true&characterEncoding=UTF-8");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Não foi possível executar a consulta");
        } catch (SQLException ex) {
            throw new RuntimeException("Não foi possível executar a consulta: "+ ex.getMessage());
        }
    }

    public Connection getConexao(){
        return this.conexao;
    }
    public void closeConexao(){
        try {
            this.conexao.close();
        }
        catch (SQLException e){
            System.err.println(e.getMessage());
        }

    }
}
