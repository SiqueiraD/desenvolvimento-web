package src.main.webapp.domain.pagamento;

import src.main.webapp.service.DaoGenerico;
import src.main.webapp.service.QueryParamsSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Movimentacao extends DaoGenerico<Movimentacao> {
    private int IdMovimentacao;
    private int IdContaOrigem;
    private int IdContaDestino;
    private double Valor;
    private Date Data;

    @Override
    public String getNomeTabela() {
        return "Movimentacao";
    }

    @Override
    public String getNomeId() {
        return "IdMovimentacao";
    }

    @Override
    public Object getValorId() {
        return this.IdMovimentacao;
    }

    @Override
    public Class<Movimentacao> getTipoClass() {
        return (Class<Movimentacao>) this.getClass();
    }

    public List<Movimentacao> listarMovimentacoesConta(int idConta) throws SQLException{
        return listarMovimentacoesConta(idConta,null);
    }
    public List<Movimentacao> listarMovimentacoesConta(int idConta, QueryParamsSQL queryParamsSQL) throws SQLException {
        StringBuilder query = new StringBuilder();
        query.append("SELECT * FROM ");
        query.append(getNomeTabela());
        String clausulaWhere = " WHERE IdContaOrigem = {idConta} or IdContaDestino = {idConta}";
        query.append(clausulaWhere.replace("{idConta}", String.valueOf(idConta)));
        if(queryParamsSQL != null){
            if(queryParamsSQL.Top > 0)
                query.append(" LIMIT ").append(queryParamsSQL.Top);
            if(queryParamsSQL.Skip > 0)
                query.append(" OFFSET ").append(queryParamsSQL.Skip);
        }
        Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement(query.toString());
        ResultSet rs = ps.executeQuery();
        List<Movimentacao> lista = new ArrayList<>();
        while (rs.next()) {
            Movimentacao obj = converterResultSet(rs);
            lista.add(obj);
        }
        conn.close();
        return lista;
    }
}
