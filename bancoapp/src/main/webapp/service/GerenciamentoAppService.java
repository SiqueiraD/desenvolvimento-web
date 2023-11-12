package src.main.webapp.service;

import src.main.webapp.domain.gerenciamento.Acesso;
import src.main.webapp.domain.gerenciamento.Usuario;
import src.main.webapp.domain.pagamento.Conta;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class GerenciamentoAppService {
    public static Usuario CadastrarUsuario(Usuario usuario) throws ClientException {
        try {
            usuario.inserir();
        } catch (SQLException e) {
            throw new ClientException(e.getMessage());
        }
        return usuario;
    }
    public static List<Usuario> ListarUsuariosSolicitandoConta() throws ClientException {
        try {
            return new Usuario().getUsuariosSemAcesso();
        } catch (SQLException e) {
            throw new ClientException("Nao foi possível consultar usuarios Solicitando conta");
        }
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
