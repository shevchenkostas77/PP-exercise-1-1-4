package jm.task.core.jdbc.util;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    // реализуйте настройку соеденения с БД
    public static Connection getConnection() {
        Connection connection;
        Properties properties = getPropertiesForDatabaseConnection();

        try {
            connection = DriverManager
                    .getConnection(properties.getProperty("datasource.url"),
                            properties.getProperty("datasource.username"),
                            properties.getProperty("datasource.password"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return connection;
    }

    private static Properties getPropertiesForDatabaseConnection() {
        Properties properties = new Properties();

        try {
            Reader reader = new FileReader("resoures/database.properties");
            properties.load(reader);

            return properties;

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
