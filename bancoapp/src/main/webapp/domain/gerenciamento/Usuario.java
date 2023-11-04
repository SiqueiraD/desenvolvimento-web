package src.main.webapp.domain.gerenciamento;

import src.main.webapp.domain.pagamento.Conta;
import src.main.webapp.service.DaoGenerico;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.List;

public class Usuario extends DaoGenerico<Usuario> {
    private int IdUsuario;
    private String CPF;
    private String Nome;
    private String Email;
    private String Senha;
    private String Endereco;
    private String Telefone;

    public Usuario (){
        super();
    }
    public Usuario (Hashtable<String, Object> propriedades){
        super(propriedades);
    }

    public List<Acesso> getListaAcessos() {
        var preparaConsulta = new Acesso();
        preparaConsulta.setIdUsuario(Integer.parseInt(this.getValorId().toString()));
        try {
            return preparaConsulta.listar();
        } catch (SQLException e) {
            throw new RuntimeException("Não foi possível listar os perfis de acesso do usuario.");
        }
    }
    @Override
    public String getNomeTabela() {
        return "Usuario";
    }

    @Override
    public String getNomeId() {
        return "IdUsuario";
    }

    @Override
    public Object getValorId() {
        return this.IdUsuario;
    }

    @Override
    public Class<Usuario> getTipoClass() {
        return (Class<Usuario>) this.getClass();
    }


    public void setCPF(String CPF) {
        this.propriedades.put("CPF", CPF);
        this.CPF = CPF;
    }

    public void setNome(String nome) {
        this.propriedades.put("nome", nome);
        this.Nome = nome;
    }

    public void setEndereco(String endereco) {
        this.propriedades.put("endereco", endereco);
        this.Endereco = endereco;
    }

    public void setTelefone(String telefone) {
        this.propriedades.put("telefone", telefone);
        this.Telefone = telefone;
    }

    public void setSenha(String senha) {
        try {
            MessageDigest md = null;
            md = MessageDigest.getInstance("MD5");
            md.update(senha.getBytes());
            byte[] hash = md.digest();
            senha = new BigInteger(1, hash).toString(16);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Não foi possível encriptar a senha");
        }
        this.propriedades.put("senha", senha);
        Senha = senha;
    }

    public String getCPF() {
        return CPF;
    }

    public String getNome() {
        return Nome;
    }

    public String getEndereco() {
        return Endereco;
    }

    public String getTelefone() {
        return Telefone;
    }

    public String getSenha() {
        return Senha;
    }
}
