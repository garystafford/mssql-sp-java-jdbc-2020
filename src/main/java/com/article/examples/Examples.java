package com.article.examples;

import com.article.connection.SqlConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Examples of calls to SQL Server using JDBC
 *
 * @author Gary A. Stafford
 */
public class Examples {

    private static final SqlConnection connection = new SqlConnection();

    /**
     * Statement example, no parameters, returns Integer
     *
     * @return Average weight of all products
     */
    public double getAverageProductWeightST() {
        double averageWeight = 0;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            stmt = connection.getConnection().createStatement();
            String sql = "WITH Weights_CTE(AverageWeight) AS" +
                    "(" +
                    "    SELECT [Weight] AS AverageWeight" +
                    "    FROM [Production].[Product]" +
                    "    WHERE [Weight] > 0" +
                    "        AND [WeightUnitMeasureCode] = 'LB'" +
                    "    UNION" +
                    "    SELECT [Weight] * 0.00220462262185 AS AverageWeight" +
                    "    FROM [Production].[Product]" +
                    "    WHERE [Weight] > 0" +
                    "        AND [WeightUnitMeasureCode] = 'G')" +
                    "SELECT ROUND(AVG([AverageWeight]), 2) AS [averageWeight]" +
                    "FROM [Weights_CTE];";
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                averageWeight = rs.getDouble(1);
            }
        } catch (Exception ex) {
            Logger.getLogger(RunExamples.class.getName()).
                    log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(RunExamples.class.getName()).
                            log(Level.WARNING, null, ex);
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(RunExamples.class.getName()).
                            log(Level.WARNING, null, ex);
                }
            }
        }
        return averageWeight;
    }

    /**
     * PreparedStatement example, no parameters, returns Integer
     *
     * @return Average weight of all products
     */
    public double getAverageProductWeightPS() {
        double averageWeight = 0;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            String sql = "WITH Weights_CTE(averageWeight) AS" +
                    "(" +
                    "    SELECT [Weight] AS AverageWeight" +
                    "    FROM [Production].[Product]" +
                    "    WHERE [Weight] > 0" +
                    "        AND [WeightUnitMeasureCode] = 'LB'" +
                    "    UNION" +
                    "    SELECT [Weight] * 0.00220462262185 AS AverageWeight" +
                    "    FROM [Production].[Product]" +
                    "    WHERE [Weight] > 0" +
                    "        AND [WeightUnitMeasureCode] = 'G')" +
                    "SELECT ROUND(AVG([AverageWeight]), 2) AS [averageWeight]" +
                    "FROM [Weights_CTE];";
            pstmt = connection.getConnection().prepareStatement(sql);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                averageWeight = rs.getDouble("averageWeight");
            }
        } catch (Exception ex) {
            Logger.getLogger(RunExamples.class.getName()).
                    log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(RunExamples.class.getName()).
                            log(Level.WARNING, null, ex);
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(RunExamples.class.getName()).
                            log(Level.WARNING, null, ex);
                }
            }
        }
        return averageWeight;
    }

    /**
     * CallableStatement, no parameters, returns Integer
     *
     * @return Average weight of all products
     */
    public double getAverageProductWeightCS() {
        CallableStatement cstmt = null;
        double averageWeight = 0;
        ResultSet rs = null;
        try {
            cstmt = connection.getConnection().prepareCall(
                    "{call Production.uspGetAverageProductWeight}");
            cstmt.execute();
            rs = cstmt.getResultSet();
            if (rs.next()) {
                averageWeight = rs.getDouble(1);
            }
        } catch (Exception ex) {
            Logger.getLogger(RunExamples.class.getName()).
                    log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(RunExamples.class.getName()).
                            log(Level.SEVERE, null, ex);
                }
            }
            if (cstmt != null) {
                try {
                    cstmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(RunExamples.class.getName()).
                            log(Level.WARNING, null, ex);
                }
            }
        }
        return averageWeight;
    }

    /**
     * CallableStatement example, (1) output parameter, returns Integer
     *
     * @return Average weight of all products
     */
    public double getAverageProductWeightOutCS() {
        CallableStatement cstmt = null;
        double averageWeight = 0;

        try {
            cstmt = connection.getConnection().prepareCall(
                    "{call Production.uspGetAverageProductWeightOUT(?)}");
            cstmt.registerOutParameter("averageWeight", Types.DECIMAL);
            cstmt.execute();
            averageWeight = cstmt.getDouble("averageWeight");
        } catch (Exception ex) {
            Logger.getLogger(RunExamples.class.getName()).
                    log(Level.SEVERE, null, ex);
        } finally {
            if (cstmt != null) {
                try {
                    cstmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(RunExamples.class.getName()).
                            log(Level.WARNING, null, ex);
                }
            }
        }
        return averageWeight;
    }

    /**
     * CallableStatement example, (1) input parameter, returns ResultSet
     *
     * @param lastNameStartsWith
     * @return List of employee names
     */
    public List<String> getEmployeesByLastNameCS(String lastNameStartsWith) {

        CallableStatement cstmt = null;
        ResultSet rs = null;
        List<String> employeeFullName = new ArrayList<>();

        try {
            cstmt = connection.getConnection().prepareCall(
                    "{call Person.uspGetEmployeesByLastName(?)}",
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            cstmt.setString("lastNameStartsWith", lastNameStartsWith);
            boolean results = cstmt.execute();
            int rowsAffected = 0;

            // Protects against lack of SET NOCOUNT in stored procedure
            while (results || rowsAffected != -1) {
                if (results) {
                    rs = cstmt.getResultSet();
                    break;
                } else {
                    rowsAffected = cstmt.getUpdateCount();
                }
                results = cstmt.getMoreResults();
            }
            while (rs.next()) {
                employeeFullName.add(
                        rs.getString("LastName") + ", "
                                + rs.getString("FirstName") + " "
                                + rs.getString("MiddleName"));
            }
        } catch (Exception ex) {
            Logger.getLogger(RunExamples.class.getName()).
                    log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(RunExamples.class.getName()).
                            log(Level.WARNING, null, ex);
                }
            }
            if (cstmt != null) {
                try {
                    cstmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(RunExamples.class.getName()).
                            log(Level.WARNING, null, ex);
                }
            }
        }
        return employeeFullName;
    }

    /**
     * CallableStatement example, (2) input parameters, returns ResultSet
     *
     * @param color
     * @param size
     * @return List of Product objects
     */
    public List<Product> getProductsByColorAndSizeCS(String color, String size) {

        CallableStatement cstmt = null;
        ResultSet rs = null;
        List<Product> productList = new ArrayList<>();

        try {
            cstmt = connection.getConnection().prepareCall(
                    "{call Production.uspGetProductsByColorAndSize(?, ?)}",
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            cstmt.setString("productColor", color);
            cstmt.setString("productSize", size);
            boolean results = cstmt.execute();
            int rowsAffected = 0;

            // Protects against lack of SET NOCOUNT in stored procedure
            while (results || rowsAffected != -1) {
                if (results) {
                    rs = cstmt.getResultSet();
                    break;
                } else {
                    rowsAffected = cstmt.getUpdateCount();
                }
                results = cstmt.getMoreResults();
            }

            while (rs.next()) {
                Product product = new Product(
                        rs.getString("Product"),
                        rs.getString("ProductNumber"),
                        rs.getString("Color"),
                        rs.getString("Size"),
                        rs.getString("Model"));
                productList.add(product);
            }
        } catch (Exception ex) {
            Logger.getLogger(RunExamples.class.getName()).
                    log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(RunExamples.class.getName()).
                            log(Level.WARNING, null, ex);
                }
            }
            if (cstmt != null) {
                try {
                    cstmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(RunExamples.class.getName()).
                            log(Level.WARNING, null, ex);
                }
            }
        }
        return productList;
    }

    /**
     * Close the database connection
     *
     * @return
     */
    public boolean closeConnection() {
        if (connection.getConnection() != null) {
            try {
                connection.getConnection().close();
            } catch (SQLException ex) {
                Logger.getLogger(RunExamples.class.getName()).
                        log(Level.WARNING, null, ex);
                return false;
            }
        }
        return true;
    }
}