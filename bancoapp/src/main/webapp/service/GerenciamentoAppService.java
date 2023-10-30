package src.main.webapp.service;

import src.main.webapp.domain.gerenciamento.Acesso;
import src.main.webapp.domain.gerenciamento.Usuario;
import src.main.webapp.domain.pagamento.Conta;
import src.main.webapp.domain.pagamento.Titular;

public class GerenciamentoAppService {
    public Usuario AbrirConta(Conta conta, Titular titular){
        return titular;
    }
    public Usuario FecharConta(Conta conta, Titular titular){
        return titular;
    }

    public Usuario ContratarFuncionario(Acesso acesso){
        return acesso.getUsuario();
    }
    public Conta TrocarGerenteConta(Conta conta, Usuario usuario){
        return conta;
    }

}
