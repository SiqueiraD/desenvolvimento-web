package src.main.webapp.domain.pagamento;

import src.main.webapp.domain.gerenciamento.Acesso;
import src.main.webapp.domain.gerenciamento.Usuario;
import src.main.webapp.domain.seguranca.Perfil;
import src.main.webapp.service.DaoGenerico;
import src.main.webapp.service.Perfis;

import java.sql.SQLException;
import java.util.List;

public class Conta extends DaoGenerico<Conta> {
    private int IdConta;
    private int IdAcesso;

    private String NumeroConta;
    private Titular Titular;
    private double SaldoAtual;
    private List<Movimentacao> listaMovimentacoes;

    @Override
    public String getNomeTabela() {
        return "Conta";
    }

    @Override
    public String getNomeId() {
        return "IdConta";
    }

    @Override
    public Object getValorId() {
        return this.IdConta;
    }

    @Override
    public Class<Conta> getTipoClass() {
        return (Class<Conta>) this.getClass();
    }


    public void setIdAcessoTitular(int idAcesso) {
        try {
            if (new Acesso().consultar(idAcesso).getIdPerfil() != Perfis.CLIENTE.getValor())
                throw new RuntimeException("O perfil de acesso deve ser Cliente");
            this.propriedades.put("idAcesso", idAcesso);
            IdAcesso = idAcesso;
        } catch (SQLException e) {
            throw new RuntimeException("Não foi possível consultar o perfil de acesso para conta");
        }
    }
    public void setIdAcesso(int idAcesso) {
        this.propriedades.put("idAcesso", idAcesso);
        IdAcesso = idAcesso;
    }


    public void setSaldoAtual(double saldoAtual) {
        this.propriedades.put("saldoAtual", saldoAtual);
        SaldoAtual = saldoAtual;
    }


    public void setNumeroConta(String numeroConta) {
        this.propriedades.put("numeroConta", numeroConta);
        NumeroConta = numeroConta;
    }

    public List<Movimentacao> getListaMovimentacoes() {
        try {
            var preparaConsulta = new Movimentacao();
            listaMovimentacoes = preparaConsulta.listarMovimentacoesConta(this.IdConta);
        } catch (SQLException e) {
            throw new RuntimeException("Não foi possível consultar as movimentações da conta");
        }
        return listaMovimentacoes;
    }

    public src.main.webapp.domain.pagamento.Titular getTitular() {
        if (this.Titular == null) {
            try {
                var acessoConsulta = new Acesso().consultar(this.IdAcesso);
                int idUsuario = acessoConsulta.getIdUsuario();
                this.Titular = (src.main.webapp.domain.pagamento.Titular) new Usuario().consultar(idUsuario);
            } catch (SQLException e) {
                throw new RuntimeException("Não foi possível consultar o Titular da conta");
            }
        }
        return Titular;
    }

    public double getSaldoAtual() {
        return SaldoAtual;
    }
    public String getNumeroConta() {
        return NumeroConta;
    }

}
