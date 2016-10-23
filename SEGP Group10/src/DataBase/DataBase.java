/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import jdk.nashorn.internal.ir.Statement;

/**
 *
 * @author abdul
 */
public class DataBase {

    private String database = "SEGP_Group_10";

    private String connectionString = "jdbc:mysql://localhost/" + database;
    private Connection con = null;
    private java.sql.Statement st = null;
    private ResultSet result = null;
    private int rows = 0;
    private ResultSetMetaData resultSet = null;

    public DataBase() {

        try {
            con = DriverManager.getConnection(connectionString, "root",
                    "pakistan");
            st = con.createStatement();

        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }
}
