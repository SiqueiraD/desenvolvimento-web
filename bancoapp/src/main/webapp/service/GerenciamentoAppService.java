package src.main.webapp.service;

import src.main.webapp.domain.gerenciamento.Acesso;
import src.main.webapp.domain.gerenciamento.Usuario;
import src.main.webapp.domain.pagamento.Conta;
import src.main.webapp.domain.pagamento.Titular;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class GerenciamentoAppService {
    public Usuario AbrirConta(Conta conta, Titular titular) {
        return titular;
    }

    public Usuario FecharConta(Conta conta, Titular titular) {
        return titular;
    }

    public Usuario ContratarFuncionario(Acesso acesso) {
        return acesso.getUsuario();
    }

    public Conta TrocarGerenteConta(Conta conta, Usuario usuario) {
        return conta;
    }

    public static Hashtable<String, Object> ContasPerfilAcesso(int idAcesso) {
        Hashtable<String, Object> listaConta = new Hashtable<String, Object>();
        try {
            var acesso = new Acesso().consultar(idAcesso);
            List<Conta> lista = acesso.getListaContas();
            for (Conta conta :
                    lista) {
                listaConta.put(conta.getNumeroConta(), conta.getValorId());
            }
        } catch (SQLException e) {
            throw new RuntimeException("Não foi possível listar as contas do Perfil de acesso.");
        }
        return listaConta;
    }

}
