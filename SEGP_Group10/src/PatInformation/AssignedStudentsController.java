/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatInformation;

import StudentsInformation.Studentsinformation;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

/**
 *
 * @author abdul
 */
public class AssignedStudentsController implements Initializable {

    @FXML
    AnchorPane anchorPane;

    @FXML
    Label patName;

    @FXML
    Label groupNumber;

    @FXML
    JFXTreeTableView tableView;
    
    @FXML
     JFXButton backButtonClicked;

    JFXTreeTableColumn<Student, String> studentUOB = new JFXTreeTableColumn<>("UOB");
    JFXTreeTableColumn<Student, String> studentName = new JFXTreeTableColumn<>("Name");
    JFXTreeTableColumn<Student, String> emailAddress = new JFXTreeTableColumn<>("Email");
    JFXTreeTableColumn<Student, String> studentYear = new JFXTreeTableColumn<>("Year");
    JFXTreeTableColumn<Student, String> personalContact = new JFXTreeTableColumn<>("Personal Contact");
    JFXTreeTableColumn<Student, String> dept = new JFXTreeTableColumn<>("Department");
    JFXTreeTableColumn<Student, String> allocatedPAT = new JFXTreeTableColumn<>("PAT");

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        studentUOB.setPrefWidth(100);
        studentUOB.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Student, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Student, String> param) {
                return param.getValue().getValue().studentUOB;
            }
        });

        studentName.setPrefWidth(150);
        studentName.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Student, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Student, String> param) {
                return param.getValue().getValue().studentName;
            }
        });

        emailAddress.setPrefWidth(200);
        emailAddress.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Student, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Student, String> param) {
                return param.getValue().getValue().emailAddress;
            }
        });

        studentYear.setPrefWidth(100);
        studentYear.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Student, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Student, String> param) {
                return param.getValue().getValue().studentYear;
            }
        });

        personalContact.setPrefWidth(150);
        personalContact.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Student, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Student, String> param) {
                return param.getValue().getValue().contactNumber;
            }
        });

        dept.setPrefWidth(100);
        dept.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Student, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Student, String> param) {
                return param.getValue().getValue().dept;
            }
        });

        allocatedPAT.setPrefWidth(300);
        allocatedPAT.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Student, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Student, String> param) {
                return param.getValue().getValue().allocatedPAT;
            }
        });

        tableView.getColumns().setAll(studentUOB, studentName, emailAddress, studentYear, personalContact, dept, allocatedPAT);

    }

    protected class Student extends RecursiveTreeObject<Student> {

        StringProperty studentUOB;
        StringProperty studentName;
        StringProperty emailAddress;
        StringProperty studentYear;
        StringProperty allocatedPAT;
        StringProperty contactNumber;
        StringProperty dept;

        public Student(String studentUOB, String studentName, String emailAddress, String studentYear, String contactNumber,
                String dept, String pat) {

            this.studentUOB = new SimpleStringProperty(studentUOB);
            this.studentName = new SimpleStringProperty(studentName);
            this.emailAddress = new SimpleStringProperty(emailAddress);
            this.studentYear = new SimpleStringProperty(studentYear);
            this.allocatedPAT = new SimpleStringProperty(pat);
            this.contactNumber = new SimpleStringProperty(contactNumber);
            this.dept = new SimpleStringProperty(dept);
        }
    }

}
