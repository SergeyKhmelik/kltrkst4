package ua.nure.khmelik.SummaryTask4.service;

import java.sql.Connection;

public abstract class Operation<T> {

    public abstract T execute(Connection conn);

}