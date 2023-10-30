package src.main.webapp.domain.seguranca;

import java.util.List;

public class Perfil {
    private List<Funcionalidade> listaFuncionalidades;

    public List<Funcionalidade> getListaFuncionalidades() {
        return listaFuncionalidades;
    }

    public boolean TemPermissao(Funcionalidade f2){
        return this.listaFuncionalidades.stream().anyMatch(f1 -> f1.getId() == f2.getId());
    }
}
