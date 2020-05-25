package com.project.qa.ui.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.*;

public class DBConnectionManager {

    static final Logger LOGGER = LoggerFactory.getLogger(DBConnectionManager.class);
    private Connection connection;

    public DBConnectionManager(String propertiesFilePath) {
        LOGGER.info("reading DB connection properties from given file: {}", propertiesFilePath);
        try (FileReader fileReader = new FileReader(getClass().getClassLoader().getResource(propertiesFilePath).getPath())) {
            Properties properties = new Properties();
            properties.load(fileReader);
            Class.forName(properties.getProperty("db.driver.name"));
            connection = DriverManager.getConnection(properties.getProperty("db.connection.url"), properties.getProperty("db.connection.username"), EncryptionManager.decrypt(properties.getProperty("db.connection.password")));
        } catch (IOException | ClassNotFoundException | SQLException e) {
            LOGGER.error("exception while creating DB connection: {}", e.getMessage());
        }
    }

    public DBConnectionManager(String url, String driverName, String userName, String password) {
        LOGGER.info("creating DB connection with method parameters ");
        try {
            Class.forName(driverName);
            connection = DriverManager.getConnection(url, userName, password);
        } catch (ClassNotFoundException | SQLException e) {
            LOGGER.error("exception while creating DB connection: {}", e.getMessage());
        }
    }

    /**
     * Convert the ResultSet to a List of Maps, where each Map represents a row with columnNames and columValues
     *
     * @param query
     * @return List of row maps
     * @throws SQLException
     */
    public List<Map<String, Object>> getResultSetToList(String query) {
        Statement statement = null;
        List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            ResultSetMetaData md = resultSet.getMetaData();
            int columns = md.getColumnCount();

            while (resultSet.next()) {
                Map<String, Object> row = new HashMap<String, Object>(columns);
                for (int i = 1; i <= columns; ++i) {
                    row.put(md.getColumnName(i), resultSet.getObject(i));
                }
                rows.add(row);
            }
        } catch (SQLException e) {
            LOGGER.error("exception while execution sql query: {}", e.getMessage());
        }
        return rows;
    }
}
