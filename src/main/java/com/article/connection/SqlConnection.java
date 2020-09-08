package com.article.connection;

import com.article.properties.ProjectProperties;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Gary A. Stafford
 */
public class SqlConnection {

    private static final ProjectProperties projectProperties = ProjectProperties.getInstance();
    private static final SQLServerDataSource dataSource = new SQLServerDataSource();
    private static Connection connection = null;

    /**
     * Constructor
     */
    public SqlConnection() {
        try {
            createConnection();
        } catch (Exception ex) {
            Logger.getLogger(SqlConnection.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
    }

    /**
     * @return the projectProperties
     */
    private static ProjectProperties getProjectProperties() {
        return projectProperties;
    }

    /**
     * @return the dataSource
     */
    public static SQLServerDataSource getDataSource() {
        return dataSource;
    }

    /**
     * @return @throws Exception
     */
    private static Connection createConnection() {

        try {
            getDataSource().setServerName(getProjectProperties().getProperties().
                    getProperty("connector.server"));
            getDataSource().setPortNumber(Integer.parseInt(getProjectProperties().getProperties().
                    getProperty("connector.port")));
            getDataSource().setInstanceName(getProjectProperties().getProperties().
                    getProperty("connector.instance"));
            getDataSource().setDatabaseName(getProjectProperties().getProperties().
                    getProperty("connector.database"));
            getDataSource().setUser(getProjectProperties().getProperties().
                    getProperty("connector.user"));
            getDataSource().setPassword(getProjectProperties().getProperties().
                    getProperty("connector.password"));

            connection = getDataSource().getConnection();
        } catch (Exception ex) {
            Logger.getLogger(SqlConnection.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
        return connection;
    }

    /**
     * @return the connection
     */
    public Connection getConnection() {
        return connection;
    }
}