/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AllocatedPAT;

import DataBase.DataBase;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 *
 * @author abdul
 */
public class AllocatedPATsController implements Initializable {

    @FXML
    private Tab tab;

    @FXML
    private VBox vBox;

    private ArrayList<String> studentsInformation;
    private ArrayList<String> teachersInformation;
    private ArrayList[] studentsRefrences;
    private DataBase dataBase = new DataBase();
    private ObservableList[] observableArray;
    private JFXTreeTableView[] tables;

    private JFXTreeTableColumn<Student, String> studentUOB;
    private JFXTreeTableColumn<Student, String> studentName;
    private JFXTreeTableColumn<Student, String> emailAddress;
    private JFXTreeTableColumn<Student, String> studentYear;
    private JFXTreeTableColumn<Student, String> personalContact;
    private JFXTreeTableColumn<Student, String> dept;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

//        nameOfPAT.setFont(Font.font("Verdana", FontWeight.BOLD, 13));
//        groupNumber.setFont(Font.font("Verdana", FontWeight.BOLD, 13));
        studentUOB = new JFXTreeTableColumn<>("UOB");
        studentUOB.setPrefWidth(100);
        studentUOB.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Student, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Student, String> param) {
                return param.getValue().getValue().studentUOB;
            }
        });

        studentName = new JFXTreeTableColumn<>("Name");
        studentName.setPrefWidth(150);
        studentName.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Student, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Student, String> param) {
                return param.getValue().getValue().studentName;
            }
        });

        emailAddress = new JFXTreeTableColumn<>("Email");
        emailAddress.setPrefWidth(200);
        emailAddress.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Student, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Student, String> param) {
                return param.getValue().getValue().emailAddress;
            }
        });

        studentYear = new JFXTreeTableColumn<>("Year");
        studentYear.setPrefWidth(100);
        studentYear.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Student, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Student, String> param) {
                return param.getValue().getValue().studentYear;
            }
        });

        personalContact = new JFXTreeTableColumn<>("Personal Contact");
        personalContact.setPrefWidth(150);
        personalContact.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Student, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Student, String> param) {
                return param.getValue().getValue().contactNumber;
            }
        });

        dept = new JFXTreeTableColumn<>("Department");
        dept.setPrefWidth(100);
        dept.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Student, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Student, String> param) {
                return param.getValue().getValue().dept;
            }
        });

        setDataFromDataBase();
    }

    public void setDataFromDataBase() {

        studentsInformation = dataBase.getStudentsData();
        teachersInformation = dataBase.getTeachersData();
        studentsRefrences = new ArrayList[teachersInformation.size()];

        for (int i = 0; i < studentsRefrences.length; i++) {
            studentsRefrences[i] = new ArrayList<String>();
        }

        for (int i = 0; i < studentsInformation.size(); i++) {
            String[] tokens = studentsInformation.get(i).split(",");
            int index = Integer.parseInt(tokens[6]);
            studentsRefrences[index - 1].add(studentsInformation.get(i));
        }
        makeTables();
    }

    public void makeTables() {

        for (int i = 0; i < studentsRefrences.length; i++) {

            ArrayList<String> arrayList = studentsRefrences[i];
            String[] tokens;
            ObservableList<Student> student = FXCollections.observableArrayList();

            String a = null, b = null, c = null, d = null, e = null, f = null;
            for (int j = 0; j < arrayList.size(); j++) {
                tokens = arrayList.get(j).split(",");
                student.add(new Student(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4], tokens[5]));
            }
            if (i == 0) {
                fillTable(0, student);
            } else if (i == 1) {
                fillTable(1, student);
            } else if (i == 2) {
                fillTable(2, student);
            } else if (i == 3) {
                fillTable(3, student);
            } else if (i == 4) {
                fillTable(4, student);
            } else if (i == 5) {
                fillTable(5, student);
            } else if (i == 6) {
                fillTable(6, student);
            } else if (i == 7) {
                fillTable(7, student);
            } else if (i == 8) {
                fillTable(8, student);
            } else if (i == 9) {
                fillTable(9, student);
            } else if (i == 10) {
                fillTable(10, student);
            } else if (i == 11) {
                fillTable(11, student);
            } else if (i == 12) {
                fillTable(12, student);
            } else if (i == 13) {
                fillTable(13, student);
            } else if (i == 14) {
                fillTable(14, student);
            } else if (i == 15) {
                fillTable(15, student);
            } else if (i == 16) {
                fillTable(16, student);
            } else if (i == 17) {
                fillTable(17, student);
            } else if (i == 18) {
                fillTable(18, student);
            } else if (i == 19) {
                fillTable(19, student);
            } else if (i == 20) {
                fillTable(20, student);
            } else if (i == 21) {
                fillTable(21, student);
//            }else if (i == 22) {
//                fillTable(22, student);
//            } else if (i == 23) {
//                fillTable(23, student);
//            }else if (i == 24) {
//                fillTable(24, student);
//            } else if (i == 25) {
//                fillTable(25, student);
//            }else if (i == 26) {
//                fillTable(26, student);
//            } else if (i == 27) {
//                fillTable(27, student);
//            }else if (i == 28) {
//                fillTable(28, student);
//            } else if (i == 29) {
//                fillTable(29, student);
//            }else if (i == 30) {
//                fillTable(30, student);
//            }

            }
        }
    }

    public void fillTable(int index, ObservableList<Student> student) {

        TreeItem<Student> root = new RecursiveTreeItem<Student>(student, RecursiveTreeObject::getChildren);
        JFXTreeTableView<Student> table = new JFXTreeTableView<>();
        table.getColumns().setAll(studentUOB, studentName, emailAddress, studentYear, personalContact, dept);
        table.setRoot(root);
        table.setShowRoot(false);

        Label groupNumber = new Label("Group Number:  " + (index + 1));
        groupNumber.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        Label teacherName = new Label("Personal Accadeic Tutor Name: " + teachersInformation.get(index).split(",")[1]);
        teacherName.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        VBox vbox = new VBox();
        vbox.getChildren().addAll(groupNumber, teacherName, table);
        vbox.setSpacing(10);
        vBox.getChildren().addAll(groupNumber, teacherName, vbox);

    }

    private class Student extends RecursiveTreeObject<Student> {

        StringProperty studentUOB;
        StringProperty studentName;
        StringProperty emailAddress;
        StringProperty studentYear;
        StringProperty contactNumber;
        StringProperty dept;

        public Student(String studentUOB, String studentName, String emailAddress, String studentYear, String contactNumber,
                String dept) {

            this.studentUOB = new SimpleStringProperty(studentUOB);
            this.studentName = new SimpleStringProperty(studentName);
            this.emailAddress = new SimpleStringProperty(emailAddress);
            this.studentYear = new SimpleStringProperty(studentYear);
            this.contactNumber = new SimpleStringProperty(contactNumber);
            this.dept = new SimpleStringProperty(dept);
        }
    }

    @FXML
    public void extractPDFOnClicked() {

        Stage primaryStage = new Stage();
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory
                = directoryChooser.showDialog(primaryStage);

        if (selectedDirectory == null) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Warning");
            alert.setHeaderText("Look, an Information Dialog");
            alert.setContentText("You have not selected any directory!");
            alert.show();
        } else {

            getTables(selectedDirectory.getAbsolutePath());
        }
    }

    public void getTables(String selectedDirectory) {

        Document document = new Document();
        try {

            PdfWriter.getInstance(document, new FileOutputStream(selectedDirectory + "/Allocated Students.pdf"));
            document.open();

            Image imageNamalCollege = Image.getInstance("/home/abdul/Desktop/Logo5.png");
            document.add(imageNamalCollege);

            for (int i = 0; i < studentsRefrences.length; i++) {

                document.add(new Paragraph("Group Number: " + (i + 1)));
                document.add(new Paragraph("Personal Accademic Tutor Name: " + (teachersInformation.get(i).split(",")[1])));
                ArrayList<String> students = studentsRefrences[i];
                document.add(Chunk.NEWLINE);
                document.add(getATable(students));
                document.add(Chunk.NEWLINE);
                document.add(Chunk.NEWLINE);

            }
            
            document.close();
//
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public PdfPTable getATable(ArrayList<String> students) {

        PdfPTable table = new PdfPTable(6);

        PdfPCell uobCell = new PdfPCell(new Paragraph("UOB"));
        PdfPCell nameCell = new PdfPCell(new Paragraph("Name"));
        PdfPCell emailCell = new PdfPCell(new Paragraph("Email"));
        PdfPCell yearCell = new PdfPCell(new Paragraph("Year"));
        PdfPCell phoneNumberCell = new PdfPCell(new Paragraph("Phone Number"));
        PdfPCell deptCell = new PdfPCell(new Paragraph("Department"));

        uobCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        uobCell.setBackgroundColor(BaseColor.GREEN);
        table.addCell(uobCell);

        nameCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        nameCell.setBackgroundColor(BaseColor.GREEN);
        table.addCell(nameCell);

        emailCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        emailCell.setBackgroundColor(BaseColor.GREEN);
        table.addCell(emailCell);

        yearCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        yearCell.setBackgroundColor(BaseColor.GREEN);
        table.addCell(yearCell);

        phoneNumberCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        phoneNumberCell.setBackgroundColor(BaseColor.GREEN);
        table.addCell(phoneNumberCell);

        deptCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        deptCell.setBackgroundColor(BaseColor.GREEN);
        table.addCell(deptCell);

        for (int i = 0; i < students.size(); i++) {

            String[] tokens = students.get(i).split(",");
            for (int j = 0; j < 6; j++) {
                table.addCell(tokens[j]);
            }
        }

        return table;
    }

    public Tab getTab() {
        return tab;
    }

}
