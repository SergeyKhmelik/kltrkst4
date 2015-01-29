package ua.nure.khmelik.SummaryTask4.util;

import java.sql.Connection;
import java.sql.SQLException;

public interface Operation<T> {
    
    T execute(Connection conn) throws SQLException;
    
}
