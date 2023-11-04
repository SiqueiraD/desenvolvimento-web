package src.main.webapp.service;

import src.main.webapp.domain.gerenciamento.Acesso;
import src.main.webapp.domain.gerenciamento.Usuario;

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
    static long limiteTempoMinutos = 2;

    private static String MD5Hash(String value) throws NoSuchAlgorithmException {
        MessageDigest md = null;
        md = MessageDigest.getInstance("MD5");
        md.update(value.getBytes());
        byte[] hash = md.digest();
        String token = new BigInteger(1, hash).toString(16);
        return token;
    }

    private static String MontaToken(String CPF, String senha) throws NoSuchAlgorithmException {
        String token = MD5Hash(CPF + senha);
        token = CPF + "|" + token;
        return token;
    }

    public static Hashtable<String, Object> Logar(String CPF, String senha) throws ClientException {
        var usuario = new Usuario();
        usuario.setCPF(CPF);
        usuario.setSenha(senha);
        String token = "";
        Hashtable<String, Object> objRetorno = new Hashtable<String, Object>();
        try {
            Usuario preparaConsulta = new Usuario();
            preparaConsulta.setCPF(usuario.getCPF());
            List<Usuario> listusuario = preparaConsulta.listar();
            if (listusuario == null || listusuario.isEmpty())
                throw new ClientException("Cpf não está cadastrado");
            Optional<Usuario> usuarioBanco = listusuario.stream().findFirst();
            if (usuario.getSenha().equals(usuarioBanco.get().getSenha())) {
                List<Acesso> listaAcessoBanco = usuarioBanco.get().getListaAcessos();
                if (listaAcessoBanco.isEmpty())
                    throw new ClientException("Não há acesso para o seu usuário, entre em contato com gerente");
                else if ((long) listaAcessoBanco.size() > 1) {
                    Hashtable<String, Object> acessos = ConverterListaAcessoHashTableSession(listaAcessoBanco);
                    objRetorno.put("acessos", acessos);
                    token = MD5Hash((Instant.now().getEpochSecond() + "|" + MontaToken(usuario.getCPF(), usuario.getSenha())));
                    token = (token + ":" + usuarioBanco.get().getValorId());
                    objRetorno.put("token", token);
                } else {
                    token = MD5Hash((Instant.now().getEpochSecond() + "|" + MontaToken(usuario.getCPF(), usuario.getSenha())));
                    token = (token + ":" + usuario.getValorId() + ":" + listaAcessoBanco.stream().findFirst().get().getValorId());
                    objRetorno.put("token", token);
                }
                objRetorno.put("nome", usuarioBanco.get().getNome());
                objRetorno.put("tempo", Instant.now().getEpochSecond());
            } else {
                throw new ClientException("Há algo de errado com suas credenciais.");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Não foi possível consultar o Usuario: " + e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Não foi possível encriptar o token");
        }
        return objRetorno;
    }

    private static Hashtable<String, Object> ConverterListaAcessoHashTableSession(List<Acesso> listaAcessoBanco) {
        Hashtable<String, Object> acessos = new Hashtable<String, Object>();
        for (Acesso acessoBanco : listaAcessoBanco) {
            var perfil = acessoBanco.getPerfil();
            acessos.put(perfil.getNome(), acessoBanco.getValorId());
        }
        return acessos;
    }

    public static Hashtable<String, Object> ListarAcessoHashTableSession(int idUsuario) {
        try {
            Usuario usuario = new Usuario().consultar(idUsuario);
            Hashtable<String, Object> acessos = new Hashtable<String, Object>();
            var listaAcessoBanco = usuario.getListaAcessos();
            for (Acesso acessoBanco : listaAcessoBanco) {
                var perfil = acessoBanco.getPerfil();
                acessos.put(perfil.getNome(), acessoBanco.getValorId());
            }
            return acessos;
        } catch (SQLException e) {
            throw new RuntimeException("Não foi possível consultar o usuário");
        }
    }


    public static boolean VerificarTokenValido(String token, Object tempo) {
        try {
            var matcher = Pattern.compile("(\\w+):?(\\w?):?(\\w?)", Pattern.MULTILINE).matcher(token);

            if (tempo != null && (token != null) && !token.isEmpty() && matcher.find()) {
                var hashToken = matcher.group(1);
                Usuario usuario = new Usuario().consultar(Integer.parseInt(matcher.group(2)));

                if (usuario == null)
                    throw new RuntimeException("Usuario" + matcher.group(2) + " do token: " + token);

                return MD5Hash((tempo + "|" + MontaToken(usuario.getCPF(), usuario.getSenha()))).equals(hashToken);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Não foi possível consultar o Usuario para VerificarTokenValido: " + e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Não foi possível encriptar o token para VerificarTokenValido");
        }
        return false;
    }

    public static boolean VerificarTempoAcesso(HttpSession session) {
        Object tempoLogin = session.getAttribute("tempo");
        long tempoAtual = Instant.now().getEpochSecond();
        if (tempoLogin == null || tempoAtual - (long) tempoLogin > 60 * limiteTempoMinutos) {
            return false;
        } else
            session.setAttribute("tempoRestante", (60 * limiteTempoMinutos) - (tempoAtual - (long) tempoLogin));
        return true;
    }

    public static Acesso PegarAcesso(String token) {
        Acesso acesso = null;
        try {
            var matcher = Pattern.compile("(\\w+):?(\\w?):?(\\w?)", Pattern.MULTILINE).matcher(token);

            if ((token != null) && !token.isEmpty() && matcher.find() && matcher.group(3) != null && !matcher.group(3).isEmpty()) {
                acesso = new Acesso().consultar(Integer.parseInt(matcher.group(3)));

                if (acesso == null)
                    throw new RuntimeException("Acesso" + matcher.group(3) + " do token: " + token);

                return acesso;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Não foi possível consultar o Usuario para VerificarTokenValido: " + e);
        }
        return acesso;
    }

    public static boolean PermitirAcesso(HttpSession session, int idFuncionalidade) {
        String token = (String) session.getAttribute("token");
        String tempo = session.getAttribute("tempo") != null ? session.getAttribute("tempo").toString() : null;
        if (VerificarTokenValido(token, tempo)) {
            //TODO: validar se usuário tem permissão na funcionalidade
            return true;
        }
        return false;
    }

}
