package src.main.webapp.domain.seguranca;

public class Funcionalidade {
    private int idFuncionalidade;
    private String url;
    private String nome;

    public int getId(){
        return this.idFuncionalidade;
    }
    public String getUrl(){
        return this.url;
    }

    public String getNome() {
        return nome;
    }
}
