package ua.nure.khmelik.SummaryTask4.util;

import java.sql.Connection;
import java.sql.SQLException;

import ua.nure.khmelik.SummaryTask4.exceptions.NoSuchRoleException;
import ua.nure.khmelik.SummaryTask4.exceptions.NoSuchUserException;

public interface Operation<T> {
    
    T execute(Connection conn) throws SQLException, NoSuchRoleException, NoSuchUserException;
    
}
