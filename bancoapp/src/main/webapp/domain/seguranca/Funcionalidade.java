package src.main.webapp.domain.seguranca;

import src.main.webapp.service.DaoGenerico;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;

public class Funcionalidade extends DaoGenerico<Funcionalidade> {
    private int IdFuncionalidade;
    private String URL;
    private String Nome;
    public Funcionalidade() {
        super();
    }
    public Funcionalidade(Hashtable<String, Object> propriedades) {
        super(propriedades);
    }

    public String getURL(){
        return this.URL;
    }

    public String getNome() {
        return Nome;
    }

    @Override
    public String getNomeTabela() {
        return "Funcionalidade";
    }

    @Override
    public String getNomeId() {
        return "IdFuncionalidade";
    }

    @Override
    public Object getValorId() {
        return this.IdFuncionalidade;
    }

    @Override
    public Class<Funcionalidade> getTipoClass() {
        return (Class<Funcionalidade>)this.getClass();
    }


    public void setIdFuncionalidade(int idFuncionalidade) {
        this.propriedades.put("idFuncionalidade", idFuncionalidade);
        IdFuncionalidade = idFuncionalidade;
    }

    public void setURL(String URL) {
        this.propriedades.put("URL", URL);
        this.URL = URL;
    }

    public void setNome(String nome) {
        this.propriedades.put("nome", nome);
        Nome = nome;
    }
}
