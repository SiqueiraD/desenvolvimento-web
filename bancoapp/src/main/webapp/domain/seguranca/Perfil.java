package src.main.webapp.domain.seguranca;

import src.main.webapp.service.DaoGenerico;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class Perfil extends DaoGenerico<Perfil> {

    private int IdPerfil;
    private String Nome;
    private List<Funcionalidade> listaFuncionalidades;

    public List<Funcionalidade> getListaFuncionalidades() {
        return this.listaFuncionalidades != null && !this.listaFuncionalidades.isEmpty() ? listaFuncionalidades : carregarListaFuncionalidades();
    }

    @Override
    public String getNomeTabela() {
        return "Perfil";
    }

    @Override
    public String getNomeId() {
        return "IdPerfil";
    }

    @Override
    public Object getValorId() {
        return IdPerfil;
    }

    @Override
    public Class<Perfil> getTipoClass() {
        return (Class<Perfil>)this.getClass();
    }


    public boolean temPermissao(Funcionalidade f2) {
        if (this.listaFuncionalidades == null || this.listaFuncionalidades.isEmpty()) {
            carregarListaFuncionalidades();
        }
        return this.listaFuncionalidades.stream().anyMatch(f1 -> f1.getValorId() == f2.getValorId());
    }

    public List<Funcionalidade> carregarListaFuncionalidades() {
        try {
            List<Funcionalidade> lista = new ArrayList<Funcionalidade>();
            Permissao prepareConsulta = new Permissao();
            prepareConsulta.setIdPerfil(this.IdPerfil);
            List<Permissao> listaPermissao = prepareConsulta.listar();
            listaPermissao.forEach(x -> {
                lista.add(x.getFuncionalidade());
            });
            this.listaFuncionalidades = lista;
        } catch (SQLException ex) {
            throw new RuntimeException("Não foi possível executar a consulta de listaPermissao para buscar listaFuncionalidades: " + ex.getMessage());
        }
        return this.listaFuncionalidades;
    }

}
