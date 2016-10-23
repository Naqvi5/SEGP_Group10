/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StudentsInformation;

//import com.jfoenix.controls.JFXButton;
//import com.jfoenix.controls.JFXListView;
//import com.jfoenix.controls.JFXPopup;
//import com.jfoenix.controls.JFXTreeTableColumn;
//import com.jfoenix.controls.JFXTreeTableView;
//import com.jfoenix.controls.RecursiveTreeItem;
import com.itextpdf.text.BaseColor;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;

import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXTextField;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.function.Predicate;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.Tab;

/**
 *
 * @author abdul
 */
public class Studentsinformation implements Initializable {

    @FXML
    private JFXTreeTableView treeTable;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private JFXListView<Label> listView;

    @FXML
    private JFXTextField searchTextField;

    @FXML
    private Tab tab;

    private JFXPopup popUpYear1 = new JFXPopup();
    private Label year1;

    private JFXPopup popUpYear2 = new JFXPopup();
    private Label year2;

    private JFXPopup popUpYear3 = new JFXPopup();
    ;
    private Label year3;

    private JFXPopup popUpYear4 = new JFXPopup();
    private Label year4;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//

        year1 = new Label("Year-1");
        year1.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent e) {

                if (e.getButton() == MouseButton.SECONDARY) {
                    popUpYear1.show(JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.RIGHT, e.getX(), e.getY());
                }
            }
        });
        listView.getItems().add(year1);

        year2 = new Label("Year-2");
        year2.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent e) {

                if (e.getButton() == MouseButton.SECONDARY) {
                    popUpYear2.show(JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.RIGHT, e.getX(), e.getY());
                }
            }
        });
        listView.getItems().add(year2);

        year3 = new Label("Year-3");
        year3.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent e) {

                if (e.getButton() == MouseButton.SECONDARY) {
                    popUpYear3.show(JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.RIGHT, e.getX(), e.getY());
                }
            }
        });
        listView.getItems().add(year3);

        year4 = new Label("Year-4");
        year4.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent e) {

                if (e.getButton() == MouseButton.SECONDARY) {
                    popUpYear4.show(JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.RIGHT, e.getX(), e.getY());
                }
            }
        });
        listView.getItems().add(year4);

        JFXButton buttonCSYear1 = new JFXButton("CS");
        buttonCSYear1.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                System.out.println("Year 1 CS Button Clicked");
            }

        });

        JFXButton buttonEEYear1 = new JFXButton("EEE");
        buttonEEYear1.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                System.out.println("Year 1 EE Button Clicked");
            }

        });

        JFXButton buttonAllYear1 = new JFXButton("All");
        buttonAllYear1.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                System.out.println("Year 1 All Button Clicked");
            }

        });

        VBox vboxYear1 = new VBox();
        vboxYear1.getChildren().addAll(buttonCSYear1, buttonEEYear1, buttonAllYear1);

        popUpYear1.setContent(vboxYear1);
        popUpYear1.setSource(year1);

        //For Year 2................
        JFXButton buttonCSYear2 = new JFXButton("CS");
        buttonCSYear2.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                System.out.println("Year 2 CS Button Clicked");
            }

        });

        JFXButton buttonEEYear2 = new JFXButton("EEE");
        buttonEEYear2.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                System.out.println("Year 2 EE Button Clicked");
            }

        });

        JFXButton buttonAllYear2 = new JFXButton("All");
        buttonAllYear2.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                System.out.println("Year 2 All Button Clicked");
            }

        });

        VBox vboxYear2 = new VBox();
        vboxYear2.getChildren().addAll(buttonCSYear2, buttonEEYear2, buttonAllYear2);

        popUpYear2.setContent(vboxYear2);
        popUpYear2.setSource(year2);

//        For Year 3
        JFXButton buttonCSYear3 = new JFXButton("CS");
        buttonCSYear3.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                System.out.println("Year 3 CS Button Clicked");
            }

        });

        JFXButton buttonEEYear3 = new JFXButton("EEE");
        buttonEEYear3.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                System.out.println("Year 3 EE Button Clicked");
            }

        });

        JFXButton buttonAllYear3 = new JFXButton("All");
        buttonAllYear3.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                System.out.println("Year 3 All Button Clicked");
            }

        });

        VBox vboxYear3 = new VBox();
        vboxYear3.getChildren().addAll(buttonCSYear3, buttonEEYear3, buttonAllYear3);

        popUpYear3.setContent(vboxYear3);
        popUpYear3.setSource(year3);

        //For Year 4
        JFXButton buttonCSYear4 = new JFXButton("CS");
        buttonCSYear4.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                System.out.println("Year 4 CS Button Clicked");
            }

        });

        JFXButton buttonEEYear4 = new JFXButton("EEE");
        buttonEEYear4.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                System.out.println("Year 4 EE Button Clicked");
            }

        });

        JFXButton buttonAllYear4 = new JFXButton("All");
        buttonAllYear4.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                System.out.println("Year 4 All Button Clicked");
            }

        });

        VBox vboxYear4 = new VBox();
        vboxYear4.getChildren().addAll(buttonCSYear4, buttonEEYear4, buttonAllYear4);

        popUpYear4.setContent(vboxYear4);
        popUpYear4.setSource(year4);

        //Tree Table View
        JFXTreeTableColumn<Studentsinformation.Student, String> studentUOB = new JFXTreeTableColumn<>("UOB");
        studentUOB.setPrefWidth(100);
        studentUOB.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Studentsinformation.Student, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Studentsinformation.Student, String> param) {
                return param.getValue().getValue().studentUOB;
            }
        });

        JFXTreeTableColumn<Studentsinformation.Student, String> studentName = new JFXTreeTableColumn<>("Name");
        studentName.setPrefWidth(150);
        studentName.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Studentsinformation.Student, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Studentsinformation.Student, String> param) {
                return param.getValue().getValue().studentName;
            }
        });

        JFXTreeTableColumn<Studentsinformation.Student, String> emailAddress = new JFXTreeTableColumn<>("Email");
        emailAddress.setPrefWidth(200);
        emailAddress.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Studentsinformation.Student, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Studentsinformation.Student, String> param) {
                return param.getValue().getValue().emailAddress;
            }
        });

        JFXTreeTableColumn<Studentsinformation.Student, String> studentYear = new JFXTreeTableColumn<>("Year");
        studentYear.setPrefWidth(100);
        studentYear.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Studentsinformation.Student, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Studentsinformation.Student, String> param) {
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
        JFXTreeTableColumn<Studentsinformation.Student, String> personalContact = new JFXTreeTableColumn<>("Personal Contact");
        personalContact.setPrefWidth(150);
        personalContact.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Studentsinformation.Student, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Studentsinformation.Student, String> param) {
                return param.getValue().getValue().contactNumber;
            }
        });

        JFXTreeTableColumn<Studentsinformation.Student, String> dept = new JFXTreeTableColumn<>("Department");
        dept.setPrefWidth(100);
        dept.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Studentsinformation.Student, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Studentsinformation.Student, String> param) {
                return param.getValue().getValue().dept;
            }
        });

        ObservableList<Studentsinformation.Student> students = FXCollections.observableArrayList();
        students.add(new Studentsinformation.Student("14031220", "Abdul Malik", "abdul@namal.edu.pk", "3", "03027867564", "CS"));
        students.add(new Studentsinformation.Student("14031221", "Abid Ali Naqvi", "abid@namal.edu.pk", "2", "03027867564", "CS"));
        students.add(new Studentsinformation.Student("14031222", "Najaf", "najaf@namal.edu.pk", "1", "03027867564", "CS"));
        students.add(new Studentsinformation.Student("14031223", "Badar", "badar@namal.edu.pk", "4", "03027867564", "CS"));
        students.add(new Studentsinformation.Student("14031224", "Qadeer", "qadeer@namal.edu.pk", "2", "03027867564", "CS"));
        students.add(new Studentsinformation.Student("14031225", "Sajid", "sajid@namal.edu.pk", "3", "03027867564", "CS"));

        final TreeItem<Studentsinformation.Student> root = new RecursiveTreeItem<Studentsinformation.Student>(students, RecursiveTreeObject::getChildren);
        treeTable.getColumns().setAll(studentUOB, studentName, emailAddress, studentYear, personalContact, dept);
        treeTable.setRoot(root);
        treeTable.setShowRoot(false);

        searchTextField.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                treeTable.setPredicate(new Predicate<TreeItem<Student>>() {

                    @Override
                    public boolean test(TreeItem<Student> student) {

                        Boolean flag = student.getValue().studentUOB.getValue().contains(newValue);
                        return flag;
                    }
                });
            }
        });
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

            this.studentUOB = new SimpleStringProperty(studentUOB);
            this.studentName = new SimpleStringProperty(studentName);
            this.emailAddress = new SimpleStringProperty(emailAddress);
            this.studentYear = new SimpleStringProperty(studentYear);
//            this.officeNumber = new SimpleStringProperty(officeNumber);
            this.contactNumber = new SimpleStringProperty(contactNumber);
            this.dept = new SimpleStringProperty(dept);
        }
    }

    @FXML
    public void extractPDF() {

        System.out.println("Calling");
        Document document = new Document();
        try {

            PdfWriter.getInstance(document, new FileOutputStream("/home/abdul/Desktop/Report.pdf"));
            document.open();
            document.add(new Paragraph("Something in it"));
            document.add(new Paragraph(new Date().toString()));
            document.add(new Paragraph("----------------------------"));
            PdfPTable table = new PdfPTable(3);

            PdfPCell cell = new PdfPCell(new Paragraph("UOB"));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.GREEN);
            table.addCell(cell);

            table.addCell("Name");
            table.addCell("Year");

            Image imageNamalCollege = Image.getInstance("/home/abdul/Desktop/NamalLogo.png");
            Image imageBradFord = Image.getInstance("/home/abdul/Desktop/University_of_Bradford_logo.svg.png");
            
            document.add(imageNamalCollege);
            document.add(imageBradFord);
            
            document.add(table);
            document.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public AnchorPane getPane() {

        return anchorPane;
    }

    public Tab getTab() {
        return tab;
    }
}
