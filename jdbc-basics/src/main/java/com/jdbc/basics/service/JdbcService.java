package com.jdbc.basics.service;

import com.jdbc.basics.configuration.JdbcConfiguration;
import javax.sql.DataSource;
import java.sql.*;

import static com.jdbc.basics.values.DatabaseCredentials.*;
import static com.jdbc.basics.values.SqlQueries.*;

public class JdbcService {

    private DataSource dataSource;

    private void initDatasource() {
        this.dataSource = JdbcConfiguration
                .createInMemoryH2DatabaseDatasource(DATABASE_NAME, DATABASE_USER, DATABASE_USER_PASSWORD);
        System.out.println(" > created datasource : " + dataSource);
    }

    private void createTable() throws SQLException {
        System.out.println(" > opening connection");
        try (Connection connection = dataSource.getConnection()) {

            System.out.println(" > statement creation");
            Statement statement = connection.createStatement();

            System.out.println(" > executing (execute) sql query to create table");
            statement.execute(CREATE_TABLE_SQL);
        }
    }

    private void insertDataIntoTable() throws SQLException {
        System.out.println(" > opening connection");
        try (Connection connection = dataSource.getConnection();) {

            System.out.println(" > prepared statement creation");
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL);

            System.out.println(" > executing (executeUpdate) sql query to insert data");
            preparedStatement.setString(1, "Test");
            preparedStatement.executeUpdate();

            preparedStatement.setString(1, "Test2");
            preparedStatement.executeUpdate();
        }
    }

    private void printResult() throws SQLException {
        System.out.println(" > opening connection");
        try (Connection connection = dataSource.getConnection()) {

            System.out.println(" > statement creation");
            Statement statement = connection.createStatement();

            System.out.println(" > executing (executeQuery) sql query to select data");
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_SQL);

            while (resultSet.next()) {
                String messageText = resultSet.getString(1);
                Timestamp timestamp = resultSet.getTimestamp(2);
                System.out.println(" >> " + messageText + " {" + timestamp + "}");
            }
        }
    }

    public static void main(String[] args) throws SQLException {

        JdbcService jdbcService = new JdbcService();

        System.out.println("\n-- 1. database initialization: create datasource");
        jdbcService.initDatasource();

        System.out.println("\n-- 2. create table");
        jdbcService.createTable();

        System.out.println("\n-- 3. insertion data");
        jdbcService.insertDataIntoTable();

        System.out.println("\n-- 4. printing result");
        jdbcService.printResult();
    }
}