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
    }

    private void createTable() throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            statement.execute(CREATE_TABLE_SQL);
        }
    }

    private void insertDataIntoTable() throws SQLException {
        try (Connection connection = dataSource.getConnection();) {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL);

            preparedStatement.setString(1, "Test");
            preparedStatement.executeUpdate();

            preparedStatement.setString(1, "Test2");
            preparedStatement.executeUpdate();
        }
    }

    private void printResult() throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_SQL);
            while (resultSet.next()) {
                System.out.println(resultSet);
                String messageText = resultSet.getString(1);
                Timestamp timestamp = resultSet.getTimestamp(2);
                System.out.println(" - " + messageText + " {" + timestamp + "}");
            }
        }
    }

    public static void main(String[] args) throws SQLException {
        JdbcService jdbcService = new JdbcService();
        jdbcService.initDatasource();
        jdbcService.createTable();
        jdbcService.insertDataIntoTable();
        jdbcService.printResult();
    }
}