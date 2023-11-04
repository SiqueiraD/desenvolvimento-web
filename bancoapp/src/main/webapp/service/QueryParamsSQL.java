package src.main.webapp.service;

import java.sql.PreparedStatement;
import java.util.Hashtable;

public class QueryParamsSQL {
    public String Query;
    public int Skip;
    public int Top;

    public static QueryParamsSQL NewQueryParamsSQL(int skip, int top){
        var obj = new QueryParamsSQL();
        obj.Skip = skip;
        obj.Top = top;
        return obj;
    }
    public static QueryParamsSQL NewQueryParamsSQL(String query, int skip, int top){
        var obj = new QueryParamsSQL();
        obj.Skip = skip;
        obj.Top = top;
        obj.Query = query;
        return obj;
    }
}
