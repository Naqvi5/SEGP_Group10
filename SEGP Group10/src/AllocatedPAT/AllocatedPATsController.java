/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AllocatedPAT;

//import javafx.scene.control.TreeTableColumn;
//import javafx.application.Application;
//import javafx.beans.property.ReadOnlyStringWrapper;
//import javafx.scene.Scene;
//import javafx.scene.control.TreeItem;
//import javafx.scene.control.TreeTableColumn;
//import javafx.scene.control.TreeTableColumn.CellDataFeatures;
//import javafx.scene.control.TreeTableView;
//import javafx.stage.Stage;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Callback;

/**
 *
 * @author abdul
 */
public class AllocatedPATsController implements Initializable {

    @FXML
    private TreeTableView<Student> treeTable;

    @FXML
    private Tab tab;
    
    @FXML
    private Label nameOfPAT;
    
    @FXML
    private Label groupNumber;
    
    @FXML
    private VBox vBox;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        nameOfPAT.setFont(Font.font("Verdana", FontWeight.BOLD, 13));
        groupNumber.setFont(Font.font("Verdana", FontWeight.BOLD, 13));
        
        JFXTreeTableColumn<Student, String> studentUOB = new JFXTreeTableColumn<>("UOB");
        studentUOB.setPrefWidth(100);
        studentUOB.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Student, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Student, String> param) {
                return param.getValue().getValue().studentUOB;
            }
        });

        JFXTreeTableColumn<Student, String> studentName = new JFXTreeTableColumn<>("Name");
        studentName.setPrefWidth(150);
        studentName.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Student, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Student, String> param) {
                return param.getValue().getValue().studentName;
            }
        });

        JFXTreeTableColumn<Student, String> emailAddress = new JFXTreeTableColumn<>("Email");
        emailAddress.setPrefWidth(200);
        emailAddress.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Student, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Student, String> param) {
                return param.getValue().getValue().emailAddress;
            }
        });

        JFXTreeTableColumn<Student, String> studentYear = new JFXTreeTableColumn<>("Year");
        studentYear.setPrefWidth(100);
        studentYear.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Student, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Student, String> param) {
                return param.getValue().getValue().studentYear;
            }
        });

//        JFXTreeTableColumn<PAT, String> officeNumber = new JFXTreeTableColumn<>("Office Number");
//        officeNumber.setPrefWidth(150);
//        officeNumber.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<PAT, String>, ObservableValue<String>>() {
//
//            @Override
//            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<PAT, String> param) {
//                return param.getValue().getValue().officeNumber;
//            }
//        });

        JFXTreeTableColumn<Student, String> personalContact = new JFXTreeTableColumn<>("Personal Contact");
        personalContact.setPrefWidth(150);
        personalContact.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Student, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Student, String> param) {
                return param.getValue().getValue().contactNumber;
            }
        });

        JFXTreeTableColumn<Student, String> dept = new JFXTreeTableColumn<>("Department");
        dept.setPrefWidth(100);
        dept.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Student, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Student, String> param) {
                return param.getValue().getValue().dept;
            }
        });

        ObservableList<Student> students = FXCollections.observableArrayList();
        students.add(new Student("14031220", "Abdul Malik", "abdul@namal.edu.pk", "3", "03027867564", "CS"));
        students.add(new Student("14031221", "Abid Ali Naqvi", "abid@namal.edu.pk", "2", "03027867564", "CS"));
        students.add(new Student("14031222", "Najaf", "najaf@namal.edu.pk", "1", "03027867564", "CS"));
        students.add(new Student("14031223", "Badar", "badar@namal.edu.pk", "4", "03027867564", "CS"));
        students.add(new Student("14031224", "Qadeer", "qadeer@namal.edu.pk", "2", "03027867564", "CS"));
        students.add(new Student("14031225", "Sajid", "sajid@namal.edu.pk", "3", "03027867564", "CS"));

        final TreeItem<Student> root = new RecursiveTreeItem<Student>(students, RecursiveTreeObject::getChildren);
        treeTable.getColumns().setAll(studentUOB, studentName, emailAddress, studentYear, personalContact, dept);
        treeTable.setRoot(root);
        treeTable.setShowRoot(false);
        
//        vBox.getChildren().addAll(treeTable, new Label("Hello"));
    }

   private class Student extends RecursiveTreeObject<Student> {

        StringProperty studentUOB;
        StringProperty studentName;
        StringProperty emailAddress;
        StringProperty studentYear;
//        StringProperty officeNumber;
        StringProperty contactNumber;
        StringProperty dept;

        public Student(String studentUOB, String studentName, String emailAddress, String studentYear, String contactNumber,
                String dept) {

            this.studentUOB= new SimpleStringProperty(studentUOB);
            this.studentName = new SimpleStringProperty(studentName);
            this.emailAddress = new SimpleStringProperty(emailAddress);
            this.studentYear = new SimpleStringProperty(studentYear);
//            this.officeNumber = new SimpleStringProperty(officeNumber);
            this.contactNumber = new SimpleStringProperty(contactNumber);
            this.dept = new SimpleStringProperty(dept);
        }
    }
   
   public Tab getTab(){
       return tab;
   }

}
