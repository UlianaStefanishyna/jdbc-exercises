package com.jdbc.basics.configuration;

import org.h2.jdbcx.JdbcDataSource;

import javax.sql.DataSource;

public class JdbcConfiguration {

    public static DataSource createInMemoryH2DatabaseDatasource(String databaseName, String username, String pass) {
        JdbcDataSource h2DataSource = new JdbcDataSource();

        String url = String.format("jdbc:h2:mem:%s;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=false", databaseName);
        h2DataSource.setUser(username);
        h2DataSource.setPassword(pass);
        h2DataSource.setUrl(url);
        return h2DataSource;
    }
}
