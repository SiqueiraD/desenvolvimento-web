package src.main.webapp.domain.gerenciamento;

import src.main.webapp.domain.seguranca.Funcionalidade;
import src.main.webapp.domain.seguranca.Perfil;
import src.main.webapp.service.DaoGenerico;

import java.util.Hashtable;
import java.util.List;

public class Acesso extends DaoGenerico<Acesso> {
    private int IdAcesso;
    private int IdPerfil;
    private int IdUsuario;
    private Usuario Usuario;
    private Perfil Perfil;

    public Acesso() {
        super();
    }
    public Acesso(Hashtable<String, Object> propriedades) {
        super(propriedades);
    }

    public Perfil getPerfil() {
        return Perfil;
    }
    public Usuario getUsuario() {
        return Usuario;
    }

    public List<Funcionalidade> getListaFuncionalidade(){
        return this.getPerfil().getListaFuncionalidades();
    }


    @Override
    public String getNomeTabela() {
        return "Acesso";
    }

    @Override
    public String getNomeId() {
        return "idAcesso";
    }

    @Override
    public Object getValorId() {
        return this.IdAcesso;
    }

    @Override
    public Class<Acesso> getTipoClass() {
        return (Class<Acesso>)this.getClass();
    }


    public void setIdPerfil(int idPerfil) {
        this.propriedades.put("idPerfil", idPerfil);
        this.IdPerfil = idPerfil;
    }

    public void setIdUsuario(int idUsuario) {
        this.propriedades.put("idUsuario", idUsuario);
        this.IdUsuario = idUsuario;
    }

    public void setIdAcesso(int idAcesso) {
        this.propriedades.put("idAcesso", idAcesso);
        this.IdAcesso = idAcesso;
    }

    public int getIdPerfil() {
        return IdPerfil;
    }

    public int getIdUsuario() {
        return IdUsuario;
    }
}
