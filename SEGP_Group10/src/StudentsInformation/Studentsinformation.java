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
import DataBase.DataBase;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
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
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.function.Predicate;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author abdul
 */
public class Studentsinformation implements Initializable {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private JFXListView<Label> listView;

    @FXML
    private BorderPane borderPane;

    @FXML
    private Tab tab;

    @FXML
    private HBox searchBox;

    private JFXPopup popUpYear1 = new JFXPopup();
    private Label year1;

    private JFXPopup popUpYear2 = new JFXPopup();
    private Label year2;

    private JFXPopup popUpYear3 = new JFXPopup();
    private Label year3;

    private JFXPopup popUpYear4 = new JFXPopup();
    private Label year4;

    private JFXPopup popUpaAllYear = new JFXPopup();
    private Label all;

    private DataBase dataBase = new DataBase();
    private ArrayList<String> year1Students = new ArrayList<>();
    private ArrayList<String> year2Students = new ArrayList<>();
    private ArrayList<String> year3Students = new ArrayList<>();
    private ArrayList<String> year4Students = new ArrayList<>();
    ObservableList<Studentsinformation.Student> students = FXCollections.observableArrayList();
    ArrayList<String> studentsData;
    ArrayList<String> patsInformation;
    JFXButton buttonCSYear1 = new JFXButton("CS");
    JFXButton buttonEEYear1 = new JFXButton("EE");
    JFXButton buttonAllYear1 = new JFXButton("All");

    JFXButton buttonCSYear2 = new JFXButton("CS");
    JFXButton buttonEEYear2 = new JFXButton("EEE");
    JFXButton buttonAllYear2 = new JFXButton("All");

    JFXButton buttonCSYear3 = new JFXButton("CS");
    JFXButton buttonEEYear3 = new JFXButton("EEE");
    JFXButton buttonAllYear3 = new JFXButton("All");

    JFXButton buttonCSYear4 = new JFXButton("CS");
    JFXButton buttonEEYear4 = new JFXButton("EEE");
    JFXButton buttonAllYear4 = new JFXButton("All");

    JFXButton buttonCSYearAll = new JFXButton("CS");
    JFXButton buttonEEYearAll = new JFXButton("EEE");
    JFXButton buttonAllYear = new JFXButton("All");
    JFXTextField searchTextField1 = new JFXTextField();
    JFXTextField searchTextField2 = new JFXTextField();

    JFXTreeTableColumn<Studentsinformation.Student, String> studentUOB = new JFXTreeTableColumn<>("UOB");
    JFXTreeTableColumn<Studentsinformation.Student, String> studentName = new JFXTreeTableColumn<>("Name");
    JFXTreeTableColumn<Studentsinformation.Student, String> emailAddress = new JFXTreeTableColumn<>("Email");
    JFXTreeTableColumn<Studentsinformation.Student, String> studentYear = new JFXTreeTableColumn<>("Year");
    JFXTreeTableColumn<Studentsinformation.Student, String> personalContact = new JFXTreeTableColumn<>("Personal Contact");
    JFXTreeTableColumn<Studentsinformation.Student, String> dept = new JFXTreeTableColumn<>("Department");
    JFXTreeTableColumn<Studentsinformation.Student, String> allocatedPAT = new JFXTreeTableColumn<>("PAT");

    private int whichPDFToGenrate;

    public void addSearchField(JFXTextField searchTextField, JFXTreeTableView treeTable) {

        searchTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                treeTable.setPredicate(new Predicate<TreeItem<Student>>() {

                    @Override
                    public boolean test(TreeItem<Student> student) {

                        Boolean flag = student.getValue().studentUOB.getValue().contains(newValue)
                                || student.getValue().studentName.getValue().contains(newValue)
                                || student.getValue().studentYear.getValue().contains(newValue)
                                || student.getValue().dept.getValue().contains(newValue)
                                || student.getValue().allocatedPAT.getValue().contains(newValue)
                                || student.getValue().emailAddress.getValue().contains(newValue);
                        return flag;
                    }
                });
            }
        });

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        year1 = new Label("Year-1");
        year1.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent e) {

                if (e.getButton() == MouseButton.SECONDARY) {
                    popUpYear1.show(JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.RIGHT, e.getX(), e.getY());
                } else {
                    buttonAllYear1.fire();
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
                } else {
                    buttonAllYear2.fire();
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
                } else {
                    buttonAllYear3.fire();
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
                } else {
                    buttonAllYear4.fire();
                }
            }
        });
        listView.getItems().add(year4);

        all = new Label("All");
        all.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent e) {

                if (e.getButton() == MouseButton.SECONDARY) {
                    popUpaAllYear.show(JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.RIGHT, e.getX(), e.getY());
                } else {
                    buttonAllYear.fire();
                }
            }
        });
        listView.getItems().add(all);

        /**
         * ***************************************************************************************************
         * For Year 1 If the user wants to view the CS department Students from
         * 1st year then this action will take place*
         * ****************************************************************************************************
         */
        buttonCSYear1.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {

                whichPDFToGenrate = 1;
                students.clear();
                JFXTextField searchTextField = new JFXTextField();
                if (!searchBox.getChildren().isEmpty()) {
                    searchBox.getChildren().clear();
                }
                searchTextField.setPromptText("Search");
                searchTextField.setMinWidth(300);
                searchBox.getChildren().add(searchTextField);

                JFXTreeTableView treeTable = new JFXTreeTableView();
                treeTable.getColumns().setAll(studentUOB, studentName, emailAddress, studentYear, personalContact, dept, allocatedPAT);
                borderPane.setCenter(treeTable);

                for (int i = 0; i < year1Students.size(); i++) {

                    String[] tokens = year1Students.get(i).split(",");

                    if (tokens[5].equalsIgnoreCase("CS")) {
                        int id = Integer.parseInt(tokens[6]) - 1;
                        students.add(new Student(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4], tokens[5], patsInformation.get(id).split(",")[1]));
                    }
                }
                addSearchField(searchTextField, treeTable);
                popUpYear1.close();
            }

        });

        /**
         * ***************************************************************************************************
         * For Year 1 If the user wants to view the EEE department Students from
         * 1st year then this action will take place*
         * ****************************************************************************************************
         */
        buttonEEYear1.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {

                whichPDFToGenrate = 2;
                students.clear();
                JFXTextField searchTextField = new JFXTextField();
                if (!searchBox.getChildren().isEmpty()) {
                    searchBox.getChildren().clear();
                }
                searchTextField.setPromptText("Search");
                searchTextField.setMinWidth(300);
                searchBox.getChildren().add(searchTextField);

                JFXTreeTableView treeTable = new JFXTreeTableView();
                treeTable.getColumns().setAll(studentUOB, studentName, emailAddress, studentYear, personalContact, dept, allocatedPAT);
                borderPane.setCenter(treeTable);

                for (int i = 0; i < year1Students.size(); i++) {

                    String[] tokens = year1Students.get(i).split(",");

                    if (tokens[5].equalsIgnoreCase("EEE")) {
                        int id = Integer.parseInt(tokens[6]) - 1;
                        students.add(new Student(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4], tokens[5], patsInformation.get(id).split(",")[1]));
                    }
                }

                final TreeItem<Studentsinformation.Student> root = new RecursiveTreeItem<Studentsinformation.Student>(students, RecursiveTreeObject::getChildren);
                treeTable.setRoot(root);
                treeTable.setShowRoot(false);
                addSearchField(searchTextField, treeTable);
                popUpYear1.close();
            }

        });

        /**
         * ***************************************************************************************************
         * For Year 1 If the user wants to view the CS and EEE department
         * Students from 1st year then this action will take place*
         * ****************************************************************************************************
         */
        buttonAllYear1.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {

                whichPDFToGenrate = 3;
                students.clear();
                JFXTextField searchTextField = new JFXTextField();
                if (!searchBox.getChildren().isEmpty()) {
                    searchBox.getChildren().clear();
                }
                searchTextField.setPromptText("Search");
                searchTextField.setMinWidth(300);
                searchBox.getChildren().add(searchTextField);

                JFXTreeTableView treeTable = new JFXTreeTableView();
                treeTable.getColumns().setAll(studentUOB, studentName, emailAddress, studentYear, personalContact, dept, allocatedPAT);
                borderPane.setCenter(treeTable);

                for (int i = 0; i < year1Students.size(); i++) {

                    String[] tokens = year1Students.get(i).split(",");

                    int id = Integer.parseInt(tokens[6]) - 1;
                    students.add(new Student(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4], tokens[5], patsInformation.get(id).split(",")[1]));
                }

                final TreeItem<Studentsinformation.Student> root = new RecursiveTreeItem<Studentsinformation.Student>(students, RecursiveTreeObject::getChildren);
                treeTable.setRoot(root);
                treeTable.setShowRoot(false);
                addSearchField(searchTextField, treeTable);

                if (popUpYear1.isVisible()) {
                    popUpYear1.close();
                }
            }

        });

        VBox vboxYear1 = new VBox();
        vboxYear1.getChildren().addAll(buttonCSYear1, buttonEEYear1, buttonAllYear1);

        popUpYear1.setContent(vboxYear1);
        popUpYear1.setSource(year1);

        /**
         * ***************************************************************************************************
         * For Year 2 If the user wants to view the CS department Students from
         * 2nd year then this action will take place*
         * ****************************************************************************************************
         */
        buttonCSYear2.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {

                whichPDFToGenrate = 4;
                students.clear();
                JFXTextField searchTextField = new JFXTextField();
                if (!searchBox.getChildren().isEmpty()) {
                    searchBox.getChildren().clear();
                }
                searchTextField.setPromptText("Search");
                searchTextField.setMinWidth(300);
                searchBox.getChildren().add(searchTextField);

                JFXTreeTableView treeTable = new JFXTreeTableView();
                treeTable.getColumns().setAll(studentUOB, studentName, emailAddress, studentYear, personalContact, dept, allocatedPAT);
                borderPane.setCenter(treeTable);

                for (int i = 0; i < year2Students.size(); i++) {

                    String[] tokens = year2Students.get(i).split(",");

                    if (tokens[5].equalsIgnoreCase("CS")) {
                        int id = Integer.parseInt(tokens[6]) - 1;
                        students.add(new Student(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4], tokens[5], patsInformation.get(id).split(",")[1]));
                    }
                }

                final TreeItem<Studentsinformation.Student> root = new RecursiveTreeItem<Studentsinformation.Student>(students, RecursiveTreeObject::getChildren);
                treeTable.setRoot(root);
                treeTable.setShowRoot(false);
                addSearchField(searchTextField, treeTable);
                popUpYear2.close();
            }

        });

        /**
         * ***************************************************************************************************
         * For Year 2 If the user wants to view the EEE department Students from
         * 2nd year then this action will take place*
         * ****************************************************************************************************
         */
        buttonEEYear2.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {

                whichPDFToGenrate = 5;
                students.clear();
                JFXTextField searchTextField = new JFXTextField();
                if (!searchBox.getChildren().isEmpty()) {
                    searchBox.getChildren().clear();
                }
                searchTextField.setPromptText("Search");
                searchTextField.setMinWidth(300);
                searchBox.getChildren().add(searchTextField);

                JFXTreeTableView treeTable = new JFXTreeTableView();
                treeTable.getColumns().setAll(studentUOB, studentName, emailAddress, studentYear, personalContact, dept, allocatedPAT);
                borderPane.setCenter(treeTable);

                for (int i = 0; i < year2Students.size(); i++) {

                    String[] tokens = year2Students.get(i).split(",");

                    if (tokens[5].equalsIgnoreCase("EEE")) {
                        int id = Integer.parseInt(tokens[6]) - 1;
                        students.add(new Student(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4], tokens[5], patsInformation.get(id).split(",")[1]));
                    }
                }

                final TreeItem<Studentsinformation.Student> root = new RecursiveTreeItem<Studentsinformation.Student>(students, RecursiveTreeObject::getChildren);
                treeTable.setRoot(root);
                treeTable.setShowRoot(false);
                addSearchField(searchTextField, treeTable);
                popUpYear2.close();
            }

        });

        /**
         * ***************************************************************************************************
         * For Year 2 If the user wants to view the All department Students from
         * 2nd year then this action will take place*
         * ****************************************************************************************************
         */
        buttonAllYear2.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {

                whichPDFToGenrate = 6;
                students.clear();
                JFXTextField searchTextField = new JFXTextField();
                if (!searchBox.getChildren().isEmpty()) {
                    searchBox.getChildren().clear();
                }
                searchTextField.setPromptText("Search");
                searchTextField.setMinWidth(300);
                searchBox.getChildren().add(searchTextField);

                JFXTreeTableView treeTable = new JFXTreeTableView();
                treeTable.getColumns().setAll(studentUOB, studentName, emailAddress, studentYear, personalContact, dept, allocatedPAT);
                borderPane.setCenter(treeTable);

                for (int i = 0; i < year2Students.size(); i++) {

                    String[] tokens = year2Students.get(i).split(",");
                    int id = Integer.parseInt(tokens[6]) - 1;
                    students.add(new Student(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4], tokens[5], patsInformation.get(id).split(",")[1]));
                }

                final TreeItem<Studentsinformation.Student> root = new RecursiveTreeItem<Studentsinformation.Student>(students, RecursiveTreeObject::getChildren);
                treeTable.setRoot(root);
                treeTable.setShowRoot(false);
                addSearchField(searchTextField, treeTable);

                if (popUpYear2.isVisible()) {
                    popUpYear2.close();
                }

            }

        });

        VBox vboxYear2 = new VBox();
        vboxYear2.getChildren().addAll(buttonCSYear2, buttonEEYear2, buttonAllYear2);

        popUpYear2.setContent(vboxYear2);
        popUpYear2.setSource(year2);

        /**
         * ***************************************************************************************************
         * For Year 3 If the user wants to view the CS department Students from
         * 3rd year then this action will take place*
         * ****************************************************************************************************
         */
        buttonCSYear3.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {

                whichPDFToGenrate = 7;
                students.clear();
                JFXTextField searchTextField = new JFXTextField();
                if (!searchBox.getChildren().isEmpty()) {
                    searchBox.getChildren().clear();
                }
                searchTextField.setPromptText("Search");
                searchTextField.setMinWidth(300);
                searchBox.getChildren().add(searchTextField);

                JFXTreeTableView treeTable = new JFXTreeTableView();
                treeTable.getColumns().setAll(studentUOB, studentName, emailAddress, studentYear, personalContact, dept, allocatedPAT);
                borderPane.setCenter(treeTable);

                for (int i = 0; i < year3Students.size(); i++) {

                    String[] tokens = year3Students.get(i).split(",");

                    if (tokens[5].equalsIgnoreCase("CS")) {
                        int id = Integer.parseInt(tokens[6]) - 1;
                        students.add(new Student(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4], tokens[5], patsInformation.get(id).split(",")[1]));
                    }
                }

                final TreeItem<Studentsinformation.Student> root = new RecursiveTreeItem<Studentsinformation.Student>(students, RecursiveTreeObject::getChildren);
                treeTable.setRoot(root);
                treeTable.setShowRoot(false);
                addSearchField(searchTextField, treeTable);
                popUpYear3.close();
            }

        });

        /**
         * ***************************************************************************************************
         * For Year 3 If the user wants to view the EEE department Students from
         * 3rd year then this action will take place*
         * ****************************************************************************************************
         */
        buttonEEYear3.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {

                whichPDFToGenrate = 8;
                students.clear();
                JFXTextField searchTextField = new JFXTextField();
                if (!searchBox.getChildren().isEmpty()) {
                    searchBox.getChildren().clear();
                }
                searchTextField.setPromptText("Search");
                searchTextField.setMinWidth(300);
                searchBox.getChildren().add(searchTextField);

                JFXTreeTableView treeTable = new JFXTreeTableView();
                treeTable.getColumns().setAll(studentUOB, studentName, emailAddress, studentYear, personalContact, dept, allocatedPAT);
                borderPane.setCenter(treeTable);

                for (int i = 0; i < year3Students.size(); i++) {

                    String[] tokens = year3Students.get(i).split(",");

                    if (tokens[5].equalsIgnoreCase("EEE")) {
                        int id = Integer.parseInt(tokens[6]) - 1;
                        students.add(new Student(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4], tokens[5], patsInformation.get(id).split(",")[1]));
                    }
                }

                final TreeItem<Studentsinformation.Student> root = new RecursiveTreeItem<Studentsinformation.Student>(students, RecursiveTreeObject::getChildren);
                treeTable.setRoot(root);
                treeTable.setShowRoot(false);
                addSearchField(searchTextField, treeTable);
                popUpYear3.close();
            }

        });

        /**
         * ***************************************************************************************************
         * For Year 3 If the user wants to view the All Students from 3rd year
         * then this action will take place*
         * ****************************************************************************************************
         */
        buttonAllYear3.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {

                whichPDFToGenrate = 9;
                students.clear();
                JFXTextField searchTextField = new JFXTextField();
                if (!searchBox.getChildren().isEmpty()) {
                    searchBox.getChildren().clear();
                }
                searchTextField.setPromptText("Search");
                searchTextField.setMinWidth(300);
                searchBox.getChildren().add(searchTextField);

                JFXTreeTableView treeTable = new JFXTreeTableView();
                treeTable.getColumns().setAll(studentUOB, studentName, emailAddress, studentYear, personalContact, dept, allocatedPAT);
                borderPane.setCenter(treeTable);

                for (int i = 0; i < year3Students.size(); i++) {

                    String[] tokens = year3Students.get(i).split(",");
                    int id = Integer.parseInt(tokens[6]) - 1;
                    students.add(new Student(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4], tokens[5], patsInformation.get(id).split(",")[1]));
                }

                final TreeItem<Studentsinformation.Student> root = new RecursiveTreeItem<Studentsinformation.Student>(students, RecursiveTreeObject::getChildren);
                treeTable.setRoot(root);
                treeTable.setShowRoot(false);

                addSearchField(searchTextField, treeTable);

                if (popUpYear3.isVisible()) {
                    popUpYear3.close();
                }
            }

        });

        VBox vboxYear3 = new VBox();
        vboxYear3.getChildren().addAll(buttonCSYear3, buttonEEYear3, buttonAllYear3);

        popUpYear3.setContent(vboxYear3);
        popUpYear3.setSource(year3);

        /**
         * ***************************************************************************************************
         * For Year 4 If the user wants to view the CS Students from 4th year
         * then this action will take place*
         * ****************************************************************************************************
         */
        buttonCSYear4.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {

                whichPDFToGenrate = 10;
                students.clear();
                JFXTextField searchTextField = new JFXTextField();
                if (!searchBox.getChildren().isEmpty()) {
                    searchBox.getChildren().clear();
                }
                searchTextField.setPromptText("Search");
                searchTextField.setMinWidth(300);
                searchBox.getChildren().add(searchTextField);

                JFXTreeTableView treeTable = new JFXTreeTableView();
                treeTable.getColumns().setAll(studentUOB, studentName, emailAddress, studentYear, personalContact, dept, allocatedPAT);
                borderPane.setCenter(treeTable);

                for (int i = 0; i < year4Students.size(); i++) {

                    String[] tokens = year4Students.get(i).split(",");

                    if (tokens[5].equalsIgnoreCase("CS")) {
                        int id = Integer.parseInt(tokens[6]) - 1;
                        students.add(new Student(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4], tokens[5], patsInformation.get(id).split(",")[1]));
                    }
                }

                final TreeItem<Studentsinformation.Student> root = new RecursiveTreeItem<Studentsinformation.Student>(students, RecursiveTreeObject::getChildren);
                treeTable.setRoot(root);
                treeTable.setShowRoot(false);
                addSearchField(searchTextField, treeTable);
                popUpYear4.close();
            }

        });

        /**
         * ***************************************************************************************************
         * For Year 4 If the user wants to view the EEE Students from 4th year
         * then this action will take place*
         * ****************************************************************************************************
         */
        buttonEEYear4.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {

                whichPDFToGenrate = 11;
                students.clear();
                JFXTextField searchTextField = new JFXTextField();
                if (!searchBox.getChildren().isEmpty()) {
                    searchBox.getChildren().clear();
                }
                searchTextField.setPromptText("Search");
                searchTextField.setMinWidth(300);
                searchBox.getChildren().add(searchTextField);

                JFXTreeTableView treeTable = new JFXTreeTableView();
                treeTable.getColumns().setAll(studentUOB, studentName, emailAddress, studentYear, personalContact, dept, allocatedPAT);
                borderPane.setCenter(treeTable);

                for (int i = 0; i < year4Students.size(); i++) {

                    String[] tokens = year4Students.get(i).split(",");

                    if (tokens[5].equalsIgnoreCase("EEE")) {
                        int id = Integer.parseInt(tokens[6]) - 1;
                        students.add(new Student(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4], tokens[5], patsInformation.get(id).split(",")[1]));
                    }
                }

                final TreeItem<Studentsinformation.Student> root = new RecursiveTreeItem<Studentsinformation.Student>(students, RecursiveTreeObject::getChildren);
                treeTable.setRoot(root);
                treeTable.setShowRoot(false);
                addSearchField(searchTextField, treeTable);
                popUpYear4.close();
            }

        });

        /**
         * ***************************************************************************************************
         * For Year 4 If the user wants to view the All Students from 4th year
         * then this action will take place*
         * ****************************************************************************************************
         */
        buttonAllYear4.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {

                whichPDFToGenrate = 12;
                students.clear();
                JFXTextField searchTextField = new JFXTextField();
                if (!searchBox.getChildren().isEmpty()) {
                    searchBox.getChildren().clear();
                }
                searchTextField.setPromptText("Search");
                searchTextField.setMinWidth(300);
                searchBox.getChildren().add(searchTextField);

                JFXTreeTableView treeTable = new JFXTreeTableView();
                treeTable.getColumns().setAll(studentUOB, studentName, emailAddress, studentYear, personalContact, dept, allocatedPAT);
                borderPane.setCenter(treeTable);

                for (int i = 0; i < year4Students.size(); i++) {

                    String[] tokens = year4Students.get(i).split(",");

                    int id = Integer.parseInt(tokens[6]) - 1;
                    students.add(new Student(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4], tokens[5], patsInformation.get(id).split(",")[1]));
                }

                final TreeItem<Studentsinformation.Student> root = new RecursiveTreeItem<Studentsinformation.Student>(students, RecursiveTreeObject::getChildren);
                treeTable.setRoot(root);
                treeTable.setShowRoot(false);
                addSearchField(searchTextField, treeTable);
                if (popUpYear4.isVisible()) {
                    popUpYear4.close();
                }
            }

        });

        VBox vboxYear4 = new VBox();
        vboxYear4.getChildren().addAll(buttonCSYear4, buttonEEYear4, buttonAllYear4);

        popUpYear4.setContent(vboxYear4);
        popUpYear4.setSource(year4);

        /**
         * ***************************************************************************************************
         * For Year All Years If the user wants to view the CS Students from all
         * year then this action will take place*
         * ****************************************************************************************************
         */
        buttonCSYearAll.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {

                whichPDFToGenrate = 13;
                students.clear();
                JFXTextField searchTextField = new JFXTextField();
                if (!searchBox.getChildren().isEmpty()) {
                    searchBox.getChildren().clear();
                }
                searchTextField.setPromptText("Search");
                searchTextField.setMinWidth(300);
                searchBox.getChildren().add(searchTextField);

                JFXTreeTableView treeTable = new JFXTreeTableView();
                treeTable.getColumns().setAll(studentUOB, studentName, emailAddress, studentYear, personalContact, dept, allocatedPAT);
                borderPane.setCenter(treeTable);

                for (int i = 0; i < studentsData.size(); i++) {

                    String[] tokens = studentsData.get(i).split(",");
                    if (tokens[5].equalsIgnoreCase("cs")) {
                        int id = Integer.parseInt(tokens[6]) - 1;
                        students.add(new Student(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4], tokens[5], patsInformation.get(id).split(",")[1]));
                    }
                }
                final TreeItem<Studentsinformation.Student> root = new RecursiveTreeItem<Studentsinformation.Student>(students, RecursiveTreeObject::getChildren);

                treeTable.setRoot(root);
                treeTable.setShowRoot(false);
                addSearchField(searchTextField, treeTable);
                popUpaAllYear.close();
            }

        });

        /**
         * ***************************************************************************************************
         * For Year All Years If the user wants to view the EEE Students from
         * all year then this action will take place*
         * ****************************************************************************************************
         */
        buttonEEYearAll.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {

                whichPDFToGenrate = 14;
                students.clear();
                JFXTextField searchTextField = new JFXTextField();
                if (!searchBox.getChildren().isEmpty()) {
                    searchBox.getChildren().clear();
                }
                searchTextField.setPromptText("Search");
                searchTextField.setMinWidth(300);
                searchBox.getChildren().add(searchTextField);

                JFXTreeTableView treeTable = new JFXTreeTableView();
                treeTable.getColumns().setAll(studentUOB, studentName, emailAddress, studentYear, personalContact, dept, allocatedPAT);
                borderPane.setCenter(treeTable);

                for (int i = 0; i < studentsData.size(); i++) {

                    String[] tokens = studentsData.get(i).split(",");
                    if (tokens[5].equalsIgnoreCase("EEE")) {
                        int id = Integer.parseInt(tokens[6]) - 1;
                        students.add(new Student(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4], tokens[5], patsInformation.get(id).split(",")[1]));
                    }
                }
                final TreeItem<Studentsinformation.Student> root = new RecursiveTreeItem<Studentsinformation.Student>(students, RecursiveTreeObject::getChildren);

                treeTable.setRoot(root);
                treeTable.setShowRoot(false);
                addSearchField(searchTextField, treeTable);
                popUpaAllYear.close();
            }

        });

        /**
         * ***************************************************************************************************
         * For Year All Years If the user wants to view the CS and EEE Students
         * both from all year then this action will take place*
         * ****************************************************************************************************
         */
        buttonAllYear.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {

                whichPDFToGenrate = 15;
                students.clear();
                JFXTextField searchTextField = new JFXTextField();
                if (!searchBox.getChildren().isEmpty()) {
                    searchBox.getChildren().clear();
                }
                searchTextField.setPromptText("Search");
                searchTextField.setMinWidth(300);
                searchBox.getChildren().add(searchTextField);

                JFXTreeTableView treeTable = new JFXTreeTableView();
                treeTable.getColumns().setAll(studentUOB, studentName, emailAddress, studentYear, personalContact, dept, allocatedPAT);
                borderPane.setCenter(treeTable);

                for (int i = 0; i < studentsData.size(); i++) {

                    String[] tokens = studentsData.get(i).split(",");
                    int id = Integer.parseInt(tokens[6]) - 1;
                    students.add(new Student(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4], tokens[5], patsInformation.get(id).split(",")[1]));
                }
                final TreeItem<Studentsinformation.Student> root = new RecursiveTreeItem<Studentsinformation.Student>(students, RecursiveTreeObject::getChildren);

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

                addSearchField(searchTextField, treeTable);
                if (popUpaAllYear.isVisible()) {
                    popUpaAllYear.close();
                }
            }

        });

        VBox vboxAllYear = new VBox();
        vboxAllYear.getChildren().addAll(buttonCSYearAll, buttonEEYearAll, buttonAllYear);

        popUpaAllYear.setContent(vboxAllYear);
        popUpaAllYear.setSource(all);

        studentUOB.setPrefWidth(100);
        studentUOB.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Studentsinformation.Student, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Studentsinformation.Student, String> param) {
                return param.getValue().getValue().studentUOB;
            }
        });

        studentName.setPrefWidth(150);
        studentName.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Studentsinformation.Student, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Studentsinformation.Student, String> param) {
                return param.getValue().getValue().studentName;
            }
        });

        emailAddress.setPrefWidth(200);
        emailAddress.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Studentsinformation.Student, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Studentsinformation.Student, String> param) {
                return param.getValue().getValue().emailAddress;
            }
        });

        studentYear.setPrefWidth(100);
        studentYear.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Studentsinformation.Student, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Studentsinformation.Student, String> param) {
                return param.getValue().getValue().studentYear;
            }
        });

        personalContact.setPrefWidth(150);
        personalContact.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Studentsinformation.Student, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Studentsinformation.Student, String> param) {
                return param.getValue().getValue().contactNumber;
            }
        });

        dept.setPrefWidth(100);
        dept.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Studentsinformation.Student, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Studentsinformation.Student, String> param) {
                return param.getValue().getValue().dept;
            }
        });

        allocatedPAT.setPrefWidth(300);
        allocatedPAT.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Studentsinformation.Student, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Studentsinformation.Student, String> param) {
                return param.getValue().getValue().allocatedPAT;
            }
        });

        setStudentsData();
    }

    /**
     * If the user wants to edit the information of a student then call goes to
     * this method which only contains contact number text field because there
     * is no other thing which is editable in students information.
     */
    @FXML
    public void editInformationClicked() {

        /**
         * Here there is a need to get the selected row from the table and then
         * modifying the contact number and also in the backend database and
         * refresh the table view.
         */
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../StudentsInformation/EditStudentView.fxml"));
        try {
            loader.load();
        } catch (IOException e) {

        }
        final EditStudentsInofrmationController editStudentsInofrmationController = loader.getController();
        System.out.println(editStudentsInofrmationController);

        Scene scene = new Scene(editStudentsInofrmationController.ancorPane);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

        editStudentsInofrmationController.cancelButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                if (event.getButton() == MouseButton.PRIMARY) {
                    stage.close();
                }
            }
        });

        editStudentsInofrmationController.saveButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                if (event.getButton() == MouseButton.PRIMARY) {
                    stage.close();
                }
            }
        });

    }

    public void setStudentsData() {

        JFXTextField searchTextField = new JFXTextField();
        if (!searchBox.getChildren().isEmpty()) {
            searchBox.getChildren().clear();
        }
        searchTextField.setPromptText("Search");
        searchTextField.setMinWidth(300);
        searchBox.getChildren().add(searchTextField);

        JFXTreeTableView treeTable = new JFXTreeTableView();
        treeTable.getColumns().setAll(studentUOB, studentName, emailAddress, studentYear, personalContact, dept, allocatedPAT);
        borderPane.setCenter(treeTable);

        studentsData = dataBase.getStudentsData();
        patsInformation = dataBase.getTeachersData();

        whichPDFToGenrate = 15;
        for (int i = 0; i < studentsData.size(); i++) {

            String[] tokens = studentsData.get(i).split(",");
            int id = Integer.parseInt(tokens[6]) - 1;
            String arow;

            if (tokens[3].trim().equalsIgnoreCase("Year-1")) {
                year1Students.add(studentsData.get(i));
            } else if (tokens[3].trim().equalsIgnoreCase("Year-2")) {
                year2Students.add(studentsData.get(i));
            } else if (tokens[3].trim().equalsIgnoreCase("Year-3")) {
                year3Students.add(studentsData.get(i));
            } else if (tokens[3].trim().equalsIgnoreCase("Year-4")) {
                year4Students.add(studentsData.get(i));
            }

            students.add(new Student(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4], tokens[5], patsInformation.get(id).split(",")[1]));
        }

        /**
         * Here need to set the search text field in for the table.
         */
        final TreeItem<Studentsinformation.Student> root = new RecursiveTreeItem<Studentsinformation.Student>(students, RecursiveTreeObject::getChildren);
        treeTable.setRoot(root);
        treeTable.setShowRoot(false);
        addSearchField(searchTextField, treeTable);

    }

    private class Student extends RecursiveTreeObject<Student> {

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

    /**
     * When a user wants to extract the pdf file of the students information and
     * when he clicks the button call goes to under given method and it
     * generates the pdf file.
     */
    @FXML
    public void extractPDF() {

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
            PdfWriter.getInstance(document, new FileOutputStream(selectedDirectory + "/Students_Record.pdf"));
            document.open();

            Image imageNamalCollege = Image.getInstance("/home/abdul/NetBeansProjects/SEGP_Group10/src/icons/Logo5_1.png");
            document.add(imageNamalCollege);
            document.add(new Paragraph("The Record for All Students are: "));

            ArrayList<String> students = new ArrayList<>();
            if (whichPDFToGenrate == 1 || whichPDFToGenrate == 2 || whichPDFToGenrate == 3 || whichPDFToGenrate == 13
                    || whichPDFToGenrate == 14 || whichPDFToGenrate == 15) {
                getYear1Data(students);
            }
            if (whichPDFToGenrate == 4 || whichPDFToGenrate == 5 || whichPDFToGenrate == 6 || whichPDFToGenrate == 13
                    || whichPDFToGenrate == 14 || whichPDFToGenrate == 15) {
                getYear2Data(students);
            }
            if (whichPDFToGenrate == 7 || whichPDFToGenrate == 8 || whichPDFToGenrate == 9 || whichPDFToGenrate == 13
                    || whichPDFToGenrate == 14 || whichPDFToGenrate == 15) {
                getYear3Data(students);
            }
            if (whichPDFToGenrate == 10 || whichPDFToGenrate == 11 || whichPDFToGenrate == 12 || whichPDFToGenrate == 13
                    || whichPDFToGenrate == 14 || whichPDFToGenrate == 15) {
                getYear4Data(students);
            }

            document.add(Chunk.NEWLINE);
            document.add(getATable(students));
            document.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getYear1Data(ArrayList<String> students) {

        for (int i = 0; i < year1Students.size(); i++) {

            if ((whichPDFToGenrate == 1 || whichPDFToGenrate == 3 || whichPDFToGenrate == 13 || whichPDFToGenrate == 15) && year1Students.get(i).split(",")[5].equalsIgnoreCase("cs")) {
                students.add(year1Students.get(i));
            } else if ((whichPDFToGenrate == 2 || whichPDFToGenrate == 3 || whichPDFToGenrate == 14 || whichPDFToGenrate == 15) && year1Students.get(i).split(",")[5].equalsIgnoreCase("cs")) {
                students.add(year1Students.get(i));
            }
        }
    }

    public void getYear2Data(ArrayList<String> students) {

        for (int i = 0; i < year2Students.size(); i++) {

            if ((whichPDFToGenrate == 4 || whichPDFToGenrate == 6 || whichPDFToGenrate == 13 || whichPDFToGenrate == 15) && year2Students.get(i).split(",")[5].equalsIgnoreCase("cs")) {
                students.add(year2Students.get(i));
            } else if ((whichPDFToGenrate == 5 || whichPDFToGenrate == 6 || whichPDFToGenrate == 14 || whichPDFToGenrate == 15) && year2Students.get(i).split(",")[5].equalsIgnoreCase("cs")) {
                students.add(year2Students.get(i));
            }
        }

    }

    public void getYear3Data(ArrayList<String> students) {

        for (int i = 0; i < year3Students.size(); i++) {

            if ((whichPDFToGenrate == 7 || whichPDFToGenrate == 9 || whichPDFToGenrate == 13 || whichPDFToGenrate == 15) && year3Students.get(i).split(",")[5].equalsIgnoreCase("cs")) {
                students.add(year3Students.get(i));
            } else if ((whichPDFToGenrate == 8 || whichPDFToGenrate == 9 || whichPDFToGenrate == 14 || whichPDFToGenrate == 15) && year3Students.get(i).split(",")[5].equalsIgnoreCase("cs")) {
                students.add(year3Students.get(i));
            }
        }

    }

    public void getYear4Data(ArrayList<String> students) {

        for (int i = 0; i < year4Students.size(); i++) {

            if ((whichPDFToGenrate == 10 || whichPDFToGenrate == 12 || whichPDFToGenrate == 13 || whichPDFToGenrate == 15) && year4Students.get(i).split(",")[5].equalsIgnoreCase("cs")) {
                students.add(year4Students.get(i));
            } else if ((whichPDFToGenrate == 11 || whichPDFToGenrate == 12 || whichPDFToGenrate == 14 || whichPDFToGenrate == 15) && year4Students.get(i).split(",")[5].equalsIgnoreCase("cs")) {
                students.add(year4Students.get(i));
            }
        }
    }

    public PdfPTable getATable(ArrayList<String> students) {

        PdfPTable table = new PdfPTable(7);

        PdfPCell uobCell = new PdfPCell(new Paragraph("UOB"));
        PdfPCell nameCell = new PdfPCell(new Paragraph("Name"));
        PdfPCell emailCell = new PdfPCell(new Paragraph("Email"));
        PdfPCell yearCell = new PdfPCell(new Paragraph("Year"));
        PdfPCell phoneNumberCell = new PdfPCell(new Paragraph("Phone Number"));
        PdfPCell deptCell = new PdfPCell(new Paragraph("Dept"));
        PdfPCell assignedPATCell = new PdfPCell(new Paragraph("PAT"));

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

        assignedPATCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        assignedPATCell.setBackgroundColor(BaseColor.GREEN);
        table.addCell(assignedPATCell);

        for (int i = 0; i < students.size(); i++) {

            String[] tokens = students.get(i).split(",");
            int id = Integer.parseInt(tokens[6]) - 1;
            String patName = patsInformation.get(id).split(",")[1];

            for (int j = 0; j < 6; j++) {
                table.addCell(tokens[j]);
            }
            table.addCell(patName);
        }

        return table;
    }

    public AnchorPane getPane() {

        return anchorPane;
    }

    public Tab getTab() {
        return tab;
    }

    public BorderPane getBorderPane() {
        return borderPane;
    }
}
