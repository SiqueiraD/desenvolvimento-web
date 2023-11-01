package src.main.webapp.domain.seguranca;

import src.main.webapp.service.DaoGenerico;

import java.sql.SQLException;

public class Permissao extends DaoGenerico<Permissao> {
    private int IdPermissao;
    private int IdPerfil;
    private int IdFuncionalidade;
    private Funcionalidade Funcionalidade;
    private Perfil Perfil;

    public void setIdPermissao(int idPermissao) {
        this.propriedades.put("idPermissao", idPermissao);
        IdPermissao = idPermissao;
    }

    public void setIdPerfil(int idPerfil) {
        this.propriedades.put("idPerfil", idPerfil);
        IdPerfil = idPerfil;
    }

    public void setIdFuncionalidade(int idFuncionalidade) {
        this.propriedades.put("idFuncionalidade", idFuncionalidade);
        IdFuncionalidade = idFuncionalidade;
    }

    @Override
    public String getNomeTabela() {
        return "Permissao";
    }

    @Override
    public String getNomeId() {
        return "IdPermissao";
    }

    @Override
    public Object getValorId() {
        return this.IdPermissao;
    }

    @Override
    public Class<Permissao> getTipoClass() {
        return (Class<Permissao>)this.getClass();
    }


    public int getIdPerfil() {
        return IdPerfil;
    }

    public int getIdFuncionalidade() {
        return IdFuncionalidade;
    }

    public Funcionalidade getFuncionalidade() {
        if(Funcionalidade == null) {
            try {
                Funcionalidade = new Funcionalidade().consultar(IdFuncionalidade);
            } catch (SQLException e) {
                throw new RuntimeException("Não foi possível executar a consulta da Funcionalidade na entidade Permissao: " + e.getMessage());
            }
        }
        return Funcionalidade;
    }

    public src.main.webapp.domain.seguranca.Perfil getPerfil() {
        if(Perfil == null) {
            try {
                Perfil = new Perfil().consultar(IdPerfil);
            } catch (SQLException e) {
                throw new RuntimeException("Não foi possível executar a consulta do Perfil na entidade Permissao: " + e.getMessage());
            }
        }
        return Perfil;
    }

}
