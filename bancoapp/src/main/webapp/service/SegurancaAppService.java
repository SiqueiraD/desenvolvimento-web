package src.main.webapp.service;

import src.main.webapp.domain.gerenciamento.Acesso;
import src.main.webapp.domain.gerenciamento.Usuario;
import src.main.webapp.domain.seguranca.Perfil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.time.Instant;
import java.util.Hashtable;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public class SegurancaAppService {

    private String MontaToken(String CPF, String senha) throws NoSuchAlgorithmException {
        String token = CPF + senha;
        MessageDigest md = null;
        md = MessageDigest.getInstance("MD5");
        md.update(token.getBytes());
        byte[] hash = md.digest();
        token = CPF + "|" + new BigInteger(1, hash).toString(16);
        return token;
    }

    public String Logar(Usuario usuario, HttpSession session) throws ClientException {
        String token = "";
        String acessos = "";
        try {
            Usuario preparaConsulta = new Usuario();
            preparaConsulta.setCPF(usuario.getCPF());
            Optional<Usuario> usuarioBanco = preparaConsulta.listar().stream().findFirst();
            if (usuarioBanco.isPresent() && usuario.getSenha().equals(usuarioBanco.get().getSenha())) {
                var preparaConsultaAcesso = new Hashtable<String, Object>();
                preparaConsultaAcesso.put("IdUsuario", usuarioBanco.get().getValorId());
                List<Acesso> listaAcessoBanco = new Acesso(preparaConsultaAcesso).listar().stream().toList();
                if (listaAcessoBanco == null || listaAcessoBanco.isEmpty())
                    throw new ClientException("Não há acesso para o seu usuário, entre em contato com gerente");
                else if(listaAcessoBanco.stream().count() > 1) {
                    for (Acesso acessoBanco : listaAcessoBanco) {
                        var perfil = new Perfil().consultar(acessoBanco.getIdPerfil());
                        acessos = acessos + perfil.getNome() + "|";
                    }
                    session.setAttribute("acessos", acessos);
                }
                session.setAttribute("nome", usuarioBanco.get().getNome());
                token = Instant.now().getEpochSecond() + "|" + MontaToken(usuario.getCPF(), usuario.getSenha());
            }

        } catch (SQLException e) {
            throw new RuntimeException("Não foi possível consultar o Usuario: " + e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Não foi possível encriptar o token");
        }
        return token;
    }


    public boolean VerificarTokenValido(String token) {
        var matcher = Pattern.compile("\\|(.{11})\\|", Pattern.MULTILINE).matcher(token);

        if ((token != null) && !token.isEmpty() && matcher.find()) {
            var CPF = matcher.group(1);
            var preparaConsulta = new Hashtable<String, Object>();
            preparaConsulta.put("CPF", CPF);
            try {
                var usuario = new Usuario(preparaConsulta).listar().stream().findFirst();
                if (usuario.isPresent()) {
                    return token.endsWith(MontaToken(usuario.get().getCPF(), usuario.get().getSenha()));
                }
            } catch (SQLException e) {
                throw new RuntimeException("Não foi possível consultar o Usuario para VerificarTokenValido: " + e);
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException("Não foi possível encriptar o token para VerificarTokenValido");
            }
        }
        return true;
    }

    public boolean VerificarAcesso(String token) {
        var matcher = Pattern.compile("\\|(.{11})\\|", Pattern.MULTILINE).matcher(token);

        if ((token != null) && !token.isEmpty() && matcher.find()) {
            var CPF = matcher.group(1);
            var preparaConsulta = new Hashtable<String, Object>();
            preparaConsulta.put("CPF", CPF);
            try {
                var usuario = new Usuario(preparaConsulta).listar().stream().findFirst();
                if (usuario.isPresent()) {
                    return token.endsWith(MontaToken(usuario.get().getCPF(), usuario.get().getSenha()));
                }
            } catch (SQLException e) {
                throw new RuntimeException("Não foi possível consultar o Usuario para VerificarAcesso: " + e);
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException("Não foi possível encriptar o token para VerificarAcesso");
            }
        }
        return true;
    }

}
