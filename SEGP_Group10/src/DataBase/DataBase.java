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
import java.util.ArrayList;
import jdk.nashorn.internal.ir.Statement;

/**
 *
 * @author abdul
 */
public class DataBase {

    private String database = "project";

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

    public void executeQuery(String query) {
        try {
            st.executeUpdate(query);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void getResult(String query) {

        try {

            result = st.executeQuery(query);
            resultSet = result.getMetaData();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public ArrayList<String> getTeachersData() {

        String query = "select * from patsinformation;";
        getResult(query);
        ArrayList<String> teachersData = new ArrayList<>();
        try {
            while (result.next()) {

                String row = "";
                for (int i = 1; i <= resultSet.getColumnCount(); i++) {

                    if (row.equals("")) {
                        row = result.getString(i);
                    } else {
                        row += "," + result.getString(i);
                    }
                }
                teachersData.add(row);
            }
        } catch (SQLException e) {

        }
        return teachersData;
    }

    public ArrayList<String> getStudentsData() {

        String query = "select * from studentsinformation;";
        getResult(query);
        ArrayList<String> studentsData = new ArrayList<>();
        try {
            while (result.next()) {

                String row = "";
                for (int i = 1; i <= resultSet.getColumnCount(); i++) {

                    if (row.equals("")) {
                        row = result.getString(i);
                    } else {
                        row += "," + result.getString(i);
                    }
                }
                studentsData.add(row);
            }
        } catch (SQLException e) {

        }
        return studentsData;
    }
    
    public void editPatInformation(String contactNumber, String officeNumber, String id){
        
        String query = "UPDATE patsinformation SET contactnumber = " + '"' + contactNumber + '"' + ", phonenumber = " 
                + '"' + officeNumber + '"' +" WHERE id = " + '"' + id +'"' +";";
        executeQuery(query);
    }
    
    public void updatePATTable(String id, String assignedStudents){

        String query = "UPDATE patsinformation SET assignedstudents = " + '"' + assignedStudents + '"'
                +" WHERE id = " + '"' + id +'"' +";";
        executeQuery(query);        
    }
    
    public ArrayList<String> getUnallocatedStudentsData(){
        
         String query = "select * from unallocatedstudents;";
        getResult(query);
        ArrayList<String> studentsData = new ArrayList<>();
        try {
            while (result.next()) {

                String row = "";
                for (int i = 1; i <= resultSet.getColumnCount(); i++) {

                    if (row.equals("")) {
                        row = result.getString(i);
                    } else {
                        row += "," + result.getString(i);
                    }
                }
                studentsData.add(row);
            }
        } catch (SQLException e) {

        }
        return studentsData;
    }
}
