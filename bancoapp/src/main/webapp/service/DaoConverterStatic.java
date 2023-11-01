package src.main.webapp.service;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

public class DaoConverterStatic {
    public static <T> T converterResultSetParaObjeto(ResultSet rs, Class<T> classe) {
        try {
            // Um objeto para obter os metadados do ResultSet
            ResultSetMetaData rsmd = rs.getMetaData();
            // O número de colunas do ResultSet
            int colunas = rsmd.getColumnCount();
            // Um construtor para a classe passada como parâmetro
            Constructor<T> construtor = classe.getConstructor();
            T objeto = construtor.newInstance();
            for (int i = 1; i <= colunas; i++) {
                String nomeColuna = rsmd.getColumnName(i);
                Object valorColuna = rs.getObject(i);
                Field campo = classe.getDeclaredField(nomeColuna);
                campo.setAccessible(true);
                campo.set(objeto, valorColuna);
            }
            return objeto;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> List<T> converterResultSetParaListaObjeto(ResultSet rs, Class<T> classe) {
        // Uma lista para armazenar os objetos criados
        List<T> lista = new ArrayList<>();
        try {
            // Enquanto houver linhas no ResultSet
            while (rs.next()) {
                // Cria um novo objeto da classe usando o construtor sem parâmetros
                T objeto = converterResultSetParaObjeto(rs, classe);
                // Adiciona o objeto à lista
                lista.add(objeto);
            }
        } catch (Exception e) {
            // Em caso de exceção, imprime a pilha de rastreamento
            e.printStackTrace();
        }
        // Retorna a lista de objetos
        return lista;
    }
}
