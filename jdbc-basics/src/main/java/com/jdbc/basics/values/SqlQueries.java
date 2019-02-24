package com.jdbc.basics.values;

public class SqlQueries {

    public static final String CREATE_TABLE_SQL =
            "CREATE TABLE message(" +
            "body VARCHAR(255)," +
            "creation_date TIMESTAMP DEFAULT now()" +
            ");";

    public static final String INSERT_SQL =
            "INSERT INTO message(body) VALUES (?)";

    public static final String SELECT_ALL_SQL =
            "SELECT * FROM message";
}