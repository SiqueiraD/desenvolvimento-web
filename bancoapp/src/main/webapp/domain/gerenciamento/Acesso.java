package src.main.webapp.domain.gerenciamento;

import src.main.webapp.domain.seguranca.Funcionalidade;
import src.main.webapp.domain.seguranca.Perfil;
import src.main.webapp.service.DaoGenerico;

import java.util.List;

public class Acesso extends DaoGenerico<Acesso> {
    private int idPerfil;
    private int idUsuario;
    private Usuario usuario;
    private Perfil perfil;

    public Perfil getPerfil() {
        return perfil;
    }
    public Usuario getUsuario() {
        return usuario;
    }

    public List<Funcionalidade> getListaFuncionalidade(){
        return this.getPerfil().getListaFuncionalidades();
    }


}
