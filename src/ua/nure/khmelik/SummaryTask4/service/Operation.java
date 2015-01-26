package ua.nure.khmelik.SummaryTask4.service;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class Operation<T> {

    public abstract T execute(Connection conn) throws SQLException;

}