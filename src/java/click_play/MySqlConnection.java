package click_play;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.sql.*;
import java.util.Properties;

@Named
@SessionScoped
public class MySqlConnection implements Serializable {

    private static final String DATABASE_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306";
    private static final String USERNAME = "root";//Change to your root username
    private static final String PASSWORD = "babbster";//change to your password
    private static final String MAX_POOL = "250";

    // init connection object
    private Connection connection;
    // init Statement object
    private Statement statement;
    // init ResultSet object
    private ResultSet resultSet;
    // init properties object
    private Properties properties;

    public Connection getConnection() {
        return connection;
    }

    public Statement getStatement() {
        return statement;
    }

    public ResultSet getResultSet() {
        return resultSet;
    }

    // create properties
    private Properties getProperties() {
        if (properties == null) {
            properties = new Properties();
            properties.setProperty("user", USERNAME);
            properties.setProperty("password", PASSWORD);
            properties.setProperty("MaxPooledStatements", MAX_POOL);
        }
        return properties;
    }

    // connect database
    public Connection connect() throws SQLException {
        if (connection == null) {
            try {
                Class.forName(DATABASE_DRIVER);
                connection = DriverManager.getConnection(DATABASE_URL, getProperties());
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    // disconnect database
    public void disconnect() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void executeStatement(String sql) throws SQLException {
        statement = connection.prepareStatement(sql);
        statement.execute(sql);
    }

    public void resutSet() throws SQLException {
        resultSet = statement.getResultSet();
    }
}
