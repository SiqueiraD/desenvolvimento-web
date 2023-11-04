package src.main.webapp.domain.gerenciamento;

import src.main.webapp.domain.pagamento.Conta;
import src.main.webapp.domain.seguranca.Funcionalidade;
import src.main.webapp.domain.seguranca.Perfil;
import src.main.webapp.service.DaoGenerico;

import java.sql.SQLException;
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

    public Perfil getPerfil(){
        if (this.Perfil == null) {
            try {
                this.Perfil = new Perfil().consultar(this.IdPerfil);
            } catch (SQLException e) {
                throw new RuntimeException("Não foi possível consultar o perfil de acesso");
            }
        }
        return Perfil;
    }

    public Usuario getUsuario() {
        return Usuario;
    }

    public List<Conta> getListaContas() {
        var preparaConsulta = new Conta();
        preparaConsulta.setIdAcesso(Integer.parseInt(this.getValorId().toString()));
        try {
            return preparaConsulta.listar();
        } catch (SQLException e) {
            throw new RuntimeException("Não foi possível listar as contas do Perfil de acesso.");
        }
    }
    public List<Funcionalidade> getListaFuncionalidade() {
        return this.getPerfil().getListaFuncionalidades();
    }
    public List<Conta> getListaConta() {
        var preparaConsulta = new Conta();
        preparaConsulta.setIdAcesso(this.IdAcesso);
        try {
            return preparaConsulta.listar();
        } catch (SQLException e) {
            throw new RuntimeException("Não foi possível consultar a lista de contas do perfil de acesso");
        }
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
        return (Class<Acesso>) this.getClass();
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
