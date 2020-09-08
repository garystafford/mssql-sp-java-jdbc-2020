# Calling Microsoft SQL Server Stored Procedures from a Java Application Using JDBC (2020 Edition)

## Demonstrate the use of the JDBC to call stored procedures from a Microsoft SQL Server database and return data to a Java-based console application.

An update of the original blog post and project files, [Calling Microsoft SQL Server Stored Procedures from a Java Application Using JDBC](https://programmaticponderings.com/2012/08/24/calling-sql-server-stored-procedures-with-java-using-jdbc/), published August 24, 2012. It has been the most popular blog post and project I ever created in the lat eight years, based on the number of viewers.

## Major Changes to Project

1. Microsoft SQL Server: Based on SQL Server 2008 R2 (10.50.1600.1), now SQL Server 2017 (14.00.3281.6.v1);
2. JDBC Driver: Was built with Microsoft JDBC Driver 4.0 for SQL Server (JDBC 4.0 for JRE 6), now Microsoft JDBC Driver 8.4 for SQL Server (JDBC Driver 8.4 for JRE 11);
3. Database: Was AdventureWorks2008R2, now AdventureWorks2017 (OLTP) full database;
4. Java Version: Was built with Java SE Development Kit (JDK) 6, now Java SE Development Kit (JDK) 13;
5. IDE: Was built with Netbeans 7.1.2, now IntelliJ IDEA 2020.2 (Ultimate Edition);
6. Build Automation Tool: Was built with Apache Ant, now Gradle 6.6;

## Commands

```bash
gradle clean
gradle build
gradle test
gradle run --warning-mode none
```
