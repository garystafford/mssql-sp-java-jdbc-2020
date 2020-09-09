package com.article.examples;

import java.util.List;

/**
 * Main class that calls all example methods
 *
 * @author Gary A. Stafford
 */
public class RunExamples {

    private static final Examples examples = new Examples();
    private static final ProcessTimer timer = new ProcessTimer();

    /**
     * @param args the command line arguments
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        System.out.println();
        System.out.println("SQL SERVER STATEMENT EXAMPLES");
        System.out.println("======================================");

        // Statement example, no parameters, returns Integer
        timer.setStartTime(System.nanoTime());
        double averageWeight = examples.getAverageProductWeightST();
        timer.setEndTime(System.nanoTime());
        System.out.println("Method: GetAverageProductWeightST");
        System.out.println("Description: Statement, no parameters, returns Integer");
        System.out.printf("Duration (ms): %d%n", timer.getDuration());
        System.out.printf("Results: Average product weight (lb): %s%n", averageWeight);
        System.out.println("---");

        // PreparedStatement example, no parameters, returns Integer
        timer.setStartTime(System.nanoTime());
        averageWeight = examples.getAverageProductWeightPS();
        timer.setEndTime(System.nanoTime());
        System.out.println("Method: GetAverageProductWeightPS");
        System.out.println("Description: PreparedStatement, no parameters, returns Integer");
        System.out.printf("Duration (ms): %d%n", timer.getDuration());
        System.out.printf("Results: Average product weight (lb): %s%n", averageWeight);
        System.out.println("---");

        // CallableStatement, no parameters, returns Integer
        timer.setStartTime(System.nanoTime());
        averageWeight = examples.getAverageProductWeightCS();
        timer.setEndTime(System.nanoTime());
        System.out.println("Method: GetAverageProductWeightCS");
        System.out.println("Description: CallableStatement, no parameters, returns Integer");
        System.out.printf("Duration (ms): %d%n", timer.getDuration());
        System.out.printf("Results: Average product weight (lb): %s%n", averageWeight);
        System.out.println("---");

        // CallableStatement example, (1) output parameter, returns Integer
        timer.setStartTime(System.nanoTime());
        averageWeight = examples.getAverageProductWeightOutCS();
        timer.setEndTime(System.nanoTime());
        System.out.println("Method: GetAverageProductWeightOutCS");
        System.out.println("Description: CallableStatement, (1) output parameter, returns Integer");
        System.out.printf("Duration (ms): %d%n", timer.getDuration());
        System.out.printf("Results: Average product weight (lb): %s%n", averageWeight);
        System.out.println("---");

        // CallableStatement example, (1) input parameter, returns ResultSet
        timer.setStartTime(System.nanoTime());
        String lastNameStartsWith = "Sa";
        List<String> employeeFullName =
                examples.getEmployeesByLastNameCS(lastNameStartsWith);
        timer.setEndTime(System.nanoTime());
        System.out.println("Method: GetEmployeesByLastNameCS");
        System.out.println("Description: CallableStatement, (1) input parameter, returns ResultSet");
        System.out.printf("Duration (ms): %d%n", timer.getDuration());
        System.out.printf("Results: Last names starting with '%s': %d%n", lastNameStartsWith, employeeFullName.size());
        if (employeeFullName.size() > 0) {
            System.out.printf("         Last employee found: %s%n", employeeFullName.get(employeeFullName.size() - 1));
        } else {
            System.out.printf("No employees found with last name starting with '%s'%n", lastNameStartsWith);
        }
        System.out.println("---");

        // CallableStatement example, (2) input parameters, returns ResultSet
        timer.setStartTime(System.nanoTime());
        String color = "Red";
        String size = "44";
        List<Product> productList =
                examples.getProductsByColorAndSizeCS(color, size);
        timer.setEndTime(System.nanoTime());
        System.out.println("Method: GetProductsByColorAndSizeCS");
        System.out.println("Description: CallableStatement, (2) input parameter, returns ResultSet");
        System.out.printf("Duration (ms): %d%n", timer.getDuration());
        if (productList.size() > 0) {
            System.out.printf("Results: Products found (color: '%s', size: '%s'): %d%n", color, size, productList.size());
            System.out.printf("         First product: %s (%s)%n", productList.get(0).getProduct(), productList.get(0).getProductNumber());
        } else {
            System.out.printf("No products found with color '%s' and size '%s'%n", color, size);
        }
        System.out.println("---");

        examples.closeConnection();
    }
}