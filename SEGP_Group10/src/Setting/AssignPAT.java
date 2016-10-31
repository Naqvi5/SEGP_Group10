/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Setting;

import DataBase.DataBase;
import alertbox.*;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.function.Predicate;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Tab;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 *
 * @author abdul
 */
public class AssignPAT implements Initializable {

    @FXML
    private BorderPane borderPane; //Border pane to fit the voew

    @FXML
    private JFXListView<String> listView; // listview to maintain the data of PATS

    @FXML
    private JFXTreeTableView tableView; // table view to show the record of teh studetns

    @FXML
    private JFXRadioButton studentListButton; // to upload the studetns list it works as an identifier

    @FXML
    private JFXRadioButton patListButton; // same function as studnets list button but use to identify PATS list.

    @FXML
    private JFXTextField filePathField; // use for path showing

    @FXML
    private JFXTextField searchTextField; //use for searching any student

    @FXML
    private Tab tab; // Tab for assigining pat located in the tabpane

    private DataBase dataBase = new DataBase(); // use to get the data form database
    private ObservableList<String> listItems = FXCollections.observableArrayList(); // maintian the item of list i-e PATS Names
    private ObservableList<Student> students = FXCollections.observableArrayList(); //maintain the record of students
    private ObservableList<Student> assignedStudents = FXCollections.observableArrayList(); // maintain the list of assigned students
    private ObservableList<PAT> pats = FXCollections.observableArrayList(); // contains pats objects
//    private ObservableList<PAT> allocatedPATs = FXCollections.observableArrayList(); // 
    private ObservableList<Integer> selectedIndices = FXCollections.observableArrayList(); // stores the selected indices formthe table
    private ArrayList<String> year1Students = new ArrayList<>(); // maintains year1 students data
    private ArrayList<String> year2Students = new ArrayList<>(); // maintains year2 students data
    private ArrayList<String> year3Students = new ArrayList<>();// maintains year3 students data
    private ArrayList<String> year4Students = new ArrayList<>();// maintains year4 students data
    private ArrayList<String> remainingStudents = new ArrayList<>(); // maintains the remaining students data who are not assigned

    String[] tokensPATInformation; // contains a pat complete information one at atime
    String[] tokensStudentInformation; // contains a studnets complete information one at atime

    /**
     * Alert Boxes Controller reference variables
     */
    private FXMLLoader loader; //use for loading the fxml files

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ToggleGroup group = new ToggleGroup();
        studentListButton.setToggleGroup(group);
        patListButton.setToggleGroup(group);

        JFXTreeTableColumn<AssignPAT.Student, String> studentUOB = new JFXTreeTableColumn<>("UOB");
        studentUOB.setPrefWidth(100);
        studentUOB.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<AssignPAT.Student, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<AssignPAT.Student, String> param) {
                return param.getValue().getValue().studentUOB;
            }
        });

        /**
         * Creating the table columns
         */
        JFXTreeTableColumn<AssignPAT.Student, String> studentName = new JFXTreeTableColumn<>("Name");
        studentName.setPrefWidth(150);
        studentName.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<AssignPAT.Student, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<AssignPAT.Student, String> param) {
                return param.getValue().getValue().studentName;
            }
        });

        JFXTreeTableColumn<AssignPAT.Student, String> emailAddress = new JFXTreeTableColumn<>("Email");
        emailAddress.setPrefWidth(150);
        emailAddress.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<AssignPAT.Student, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<AssignPAT.Student, String> param) {
                return param.getValue().getValue().emailAddress;
            }
        });

        JFXTreeTableColumn<AssignPAT.Student, String> studentYear = new JFXTreeTableColumn<>("Year");
        studentYear.setPrefWidth(100);
        studentYear.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<AssignPAT.Student, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<AssignPAT.Student, String> param) {
                return param.getValue().getValue().studentYear;
            }
        });

        JFXTreeTableColumn<AssignPAT.Student, String> personalContact = new JFXTreeTableColumn<>("Personal Contact");
        personalContact.setPrefWidth(214);
        personalContact.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<AssignPAT.Student, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<AssignPAT.Student, String> param) {
                return param.getValue().getValue().contactNumber;
            }
        });

        JFXTreeTableColumn<AssignPAT.Student, String> dept = new JFXTreeTableColumn<>("Dept");
        dept.setPrefWidth(210);
        
        dept.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<AssignPAT.Student, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<AssignPAT.Student, String> param) {
                return param.getValue().getValue().dept;
            }
        });

        /**
         * Adding the columns in the table view
         */
        tableView.getColumns().setAll(studentUOB, studentName, emailAddress, studentYear, personalContact, dept);

        /**
         * Adding action listener to the search textfiels so that when user searches then it gives according
         * to the search.
         */
        searchTextField.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                tableView.setPredicate(new Predicate<TreeItem<Student>>() {

                    @Override
                    public boolean test(TreeItem<Student> student) {

                        Boolean flag = student.getValue().studentUOB.getValue().contains(newValue)
                                || student.getValue().studentName.getValue().contains(newValue)
                                || student.getValue().emailAddress.getValue().contains(newValue)
                                || student.getValue().studentYear.getValue().contains(newValue)
                                || student.getValue().dept.getValue().contains(newValue);
                        return flag;
                    }
                });
            }
        });

        setPATAndStudentData();
    }

    public BorderPane getBorderPane() {
        return borderPane;
    }

    @FXML
    public void listViewOnClicked() {

    }

    /**
     * when the browse button is clicked then it asks for slecting the path and upload the data in to the database.
     */
    @FXML
    private void browseButtonOnClicked() {

        boolean eraseDataOrNot = true;
        Stage primaryStage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("Excel Files", "*.xlsm", "*.xlsx", "*.xlt", "*.xlv", "*.ods", "*.csv"));
        File selectedFile = fileChooser.showOpenDialog(primaryStage);
        if (selectedFile.getAbsolutePath() != null) {
            filePathField.setText(selectedFile.getAbsolutePath());
        }

        Scanner input = null;
        try {
            input = new Scanner(new File(selectedFile.getAbsolutePath()));
        } catch (IOException e) {

        }

        /**
         * Need To ask from the user whether he wants to remove his previous
         * data or not if users proceed further all of the data would lost
         */
        if (confirmMessage("It will erase all of your previous data\nDo you want to proceed further?")) {

            /**
             * Here Need to execute a query which will erase all previous data.
             * The data of pats, allocated students and unallocated students
             * will be lost.
             */
            if (patListButton.isSelected()) {

                String deleteQuery = "TRUNCATE patsinformation;";
                dataBase.executeQuery(deleteQuery);
                listItems.clear();
                pats.clear();
            } /**
             * Here the under given query will erase of previous data. Delete
             * Everything from students information and unallocated students
             * table.
             */
            else if (studentListButton.isSelected()) {

                String deleteQuery = "TRUNCATE studentsinformation;";
                dataBase.executeQuery(deleteQuery);
                deleteQuery = "TRUNCATE unallocatedstudents;";
                dataBase.executeQuery(deleteQuery);

                students.clear();

            }

            /**
             * Getting the data from CSV file and adding it in to the unallocate
             * students table.
             */
            while (input.hasNextLine()) {

                if (patListButton.isSelected()) {

                    /**
                     * Getting the data from CSV file and uploading it in to the
                     * database.
                     */
                    tokensPATInformation = input.nextLine().trim().split(",");
//                    listItems.add(tokensPATInformation[1]);

//                    pats.add(new PAT(tokensPATInformation[0], tokensPATInformation[1], tokensPATInformation[2], tokensPATInformation[3], tokensPATInformation[4], tokensPATInformation[5],
//                            tokensPATInformation[6]));
                    String query = "INSERT INTO patsinformation values(" + '"' + tokensPATInformation[0] + '"'
                            + "," + '"' + tokensPATInformation[1] + '"' + "," + '"' + tokensPATInformation[2] + '"'
                            + "," + '"' + tokensPATInformation[3] + '"' + "," + '"' + tokensPATInformation[4] + '"'
                            + "," + '"' + tokensPATInformation[5] + '"' + "," + '"'
                            + tokensPATInformation[6] + '"' + "," + '"' + "0" + '"' + ");";
                    dataBase.executeQuery(query);

                }

                if (studentListButton.isSelected()) {

                    /**
                     * Getting the data from the CSV file of students
                     * information and uploading it in to the students
                     * information table in the database.
                     */
                    String result = input.nextLine();
                    tokensStudentInformation = result.trim().split(",");

                    String query = "INSERT INTO unallocatedstudents values(" + '"' + tokensStudentInformation[0] + '"'
                            + "," + '"' + tokensStudentInformation[1] + '"' + "," + '"' + tokensStudentInformation[2] + '"'
                            + "," + '"' + tokensStudentInformation[3] + '"' + "," + '"' + tokensStudentInformation[4] + '"'
                            + "," + '"' + tokensStudentInformation[5] + '"' + ");";
                    dataBase.executeQuery(query);
                }
            }
        }
        setPATAndStudentData();
    }

    /**
     * Setting the PATs and students information by getting them from database.
     */
    public void setPATAndStudentData() {

        /**
         * getting the data of each year student seperately to get handle the
         * data easily. And to avoid using of loops again and again to find the
         * data of a single year student. It is done because if auto allocation
         * is allowed then the system may able to select different students.
         */
        ArrayList<String> studentsInformation = dataBase.getUnallocatedStudentsData();
        ArrayList<String> patsData = dataBase.getTeachersData();

        for (int i = 0; i < studentsInformation.size(); i++) {

            String result = studentsInformation.get(i);
            String[] tokens = result.split(",");

            students.add(new Student(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4], tokens[5]));
        }

        final TreeItem<AssignPAT.Student> root = new RecursiveTreeItem<AssignPAT.Student>(students, RecursiveTreeObject::getChildren);

        tableView.setRoot(root);
        tableView.setShowRoot(false);
        tableView.getSelectionModel().setSelectionMode(
                SelectionMode.MULTIPLE
        );

        pats.clear();
        listItems.clear();
        for (int i = 0; i < patsData.size(); i++) {

            String[] tokens = patsData.get(i).split(",");
            pats.add(new PAT(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4], tokens[5], tokens[6], tokens[7]));
            listItems.add(tokens[1] + "   " + tokens[7]);
        }

        listView.setItems(listItems);
    }

    /**
     * This method is called when there is a need of confirmation somewhere in
     * the interface.
     */
    public boolean confirmMessage(String message) {

        ButtonType ok = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert alert = new Alert(AlertType.WARNING, null, ok, cancel);

        alert.setTitle("Warning");
        alert.setHeaderText("Look, an Information Dialog");
        alert.setContentText(message);
        alert.showAndWait();

        if (alert.getResult() != null) {

            if (alert.getResult().getText().equalsIgnoreCase("ok")) {
                return true;
            }
        }
        return false;
    }

    
    /**
     * If the user wants to deallocate the students then this method calls.
     */
    @FXML
    public void deallocateOnClicked() {

        int index = listView.getSelectionModel().getSelectedIndex();

        if (index >= 0) {
            PAT pat = pats.get(index);
            String patID = pat.patID.getValue().trim();
            ArrayList<String> studentsData = dataBase.deallocateStudents(patID);
            pat.assignedStudents.setValue("0");
            dataBase.updatePATTable(patID, "0");

            String selectedListItem = listItems.get(index);
            selectedListItem = pat.patName.getValue() + "   " + pat.assignedStudents.getValue();
            listItems.set(index, selectedListItem);

            for (int i = 0; i < studentsData.size(); i++) {

                String[] tokens = studentsData.get(i).split(",");
                students.add(new Student(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4], tokens[5]));
                String query = "INSERT INTO unallocatedstudents values(" + '"' + tokens[0] + '"' + "," + '"' + tokens[1]
                        + '"' + "," + '"' + tokens[2] + '"'+ "," + '"' + tokens[3] + '"' + "," + '"' + tokens[4] + '"'
                        + "," + '"' + tokens[5] + '"' + ");";
                dataBase.executeQuery(query);
            }

            final TreeItem<AssignPAT.Student> root = new RecursiveTreeItem<AssignPAT.Student>(students, RecursiveTreeObject::getChildren);
            tableView.setRoot(root);
            tableView.setShowRoot(false);
        } else {
            confirmMessage("Please Select a PAT before de-allocating");
        }

    }

    
    /**
     * call goes to this method when user wants to allocate students manually.
     */
    @FXML
    public void manualAllocationClicked() {

        /**
         * Here need to get the selected rows from the unallocated students
         * table.
         */
        selectedIndices = tableView.getSelectionModel().getSelectedIndices();

        /**
         * Getting selected pat from the list of pats.
         */
        int index = listView.getSelectionModel().getSelectedIndex();

        if (index >= 0 && selectedIndices.size() > 0) {
            PAT pat = pats.get(index);
            String patID = pat.patID.getValue();
            String patLoad = pat.load.getValue().toString();

            ObservableList<Integer> indexes = tableView.getSelectionModel().getSelectedIndices();
            Student[] studentInformation = new Student[selectedIndices.size()];

            boolean assignmentAllowed = false;
            if (patLoad.equalsIgnoreCase("Full")) {
                if ((Integer.parseInt(pat.assignedStudents.getValue()) + selectedIndices.size()) > 8) {
                    assignmentAllowed = false;
                    assignmentAllowed = confirmMessage("This teacher is already full of load\nDo you want to proceed fuether?");
                } else {
                    assignmentAllowed = true;
                }
            } else if (patLoad.equalsIgnoreCase("Partial")) {
                if ((Integer.parseInt(pat.assignedStudents.getValue()) + selectedIndices.size()) > 4) {
                    assignmentAllowed = false;
                    assignmentAllowed = confirmMessage("This teacher is already full of load\nDo you want to proceed fuether?");
                } else {
                    assignmentAllowed = true;
                }
            }

            if (assignmentAllowed) {

                /**
                 * Getting the selected indices from table.
                 */
                for (int i = 0; i < selectedIndices.size(); i++) {
                    studentInformation[i] = students.get(i);
                }
                /**
                 * Inserting the students information in to the allocated
                 * students table.
                 */
                for (int i = 0; i < selectedIndices.size(); i++) {
                    String query = "INSERT INTO studentsinformation values(" + '"' + studentInformation[i].studentUOB.getValue() + '"'
                            + "," + '"' + studentInformation[i].studentName.getValue() + '"' + "," + '"' + studentInformation[i].emailAddress.getValue() + '"'
                            + "," + '"' + studentInformation[i].studentYear.getValue() + '"' + "," + '"' + studentInformation[i].contactNumber.getValue() + '"'
                            + "," + '"' + studentInformation[i].dept.getValue() + '"' + "," + '"' + patID + '"' + ");";

                    dataBase.executeQuery(query);

                    int assignedStudentsNumber = Integer.parseInt(pat.assignedStudents.getValue());
                    assignedStudentsNumber += 1;
                    pat.assignedStudents.setValue(String.valueOf(assignedStudentsNumber));
                    dataBase.updatePATTable(patID, String.valueOf(assignedStudentsNumber));
                }

                String selectedListItem = listItems.get(index);
                selectedListItem = pat.patName.getValue() + "   " + pat.assignedStudents.getValue();
                listItems.set(index, selectedListItem);

                /**
                 * After deleting the assigning the user selected students to A
                 * PAT then there is a need to delete those students from
                 * unallocated students tables and insert them to allocated
                 * students table. To add students the above query will execute
                 * but to delete the data form the table call goes to delete
                 * method. And then need to refresh the table.
                 */
                delete(indexes.get(0), indexes.size());
                final TreeItem<AssignPAT.Student> root = new RecursiveTreeItem<AssignPAT.Student>(students, RecursiveTreeObject::getChildren);
                tableView.setRoot(root);
                tableView.setShowRoot(false);

            }
        }
    }

    
    /**
     * To assign the students automatically call goes to this method.
     */
    @FXML
    public void autoAllocatesClicked() {

        ArrayList<String> unallocatedStudents = dataBase.getUnallocatedStudentsData();

        for (int i = 0; i < unallocatedStudents.size(); i++) {

            String result = unallocatedStudents.get(i);
            String[] tokens = result.split(",");

            if (tokens[3].trim().equalsIgnoreCase("Year-1")) {
                year1Students.add(result);
            } else if (tokens[3].trim().equalsIgnoreCase("year-2")) {
                year2Students.add(result);
            } else if (tokens[3].trim().equalsIgnoreCase("year-3")) {
                year3Students.add(result);
            } else if (tokens[3].trim().equalsIgnoreCase("year-4")) {
                year4Students.add(result);
            }
        }
        int patSize = pats.size();
//        int equallyAllocateStudents
        int size = year1Students.size();
        if (size < year2Students.size()) {
            size = year2Students.size();
        }
        if (size < year3Students.size()) {
            size = year3Students.size();
        }
        if (size < year4Students.size()) {
            size = year4Students.size();
        }

        if (size == 0 && remainingStudents.size() > 0) {
            mazeedAutoAllocate();
        }

        if (size > 0) {
            for (int i = 0; i < size; i++) {

                int patId = i % patSize;
                PAT pat = pats.get(patId);

                if (i < year1Students.size()) {
                    if (pat.load.getValue().equalsIgnoreCase("full")) {

                        if ((Integer.parseInt(pat.assignedStudents.getValue())) < 9) {

                            String[] studentyear1data = year1Students.get(i).split(",");
                            String query = "INSERT INTO studentsinformation values(" + '"' + studentyear1data[0] + '"' + "," + '"'
                                    + studentyear1data[1] + '"' + "," + '"' + studentyear1data[2] + '"'
                                    + "," + '"' + studentyear1data[3] + '"' + "," + '"' + studentyear1data[4] + '"'
                                    + "," + '"' + studentyear1data[5] + '"' + "," + '"' + pat.patID.getValue() + '"' + ");";

                            dataBase.executeQuery(query);

                            int assignedStudentsNumber = Integer.parseInt(pat.assignedStudents.getValue());
                            assignedStudentsNumber += 1;
                            pat.assignedStudents.setValue(String.valueOf(assignedStudentsNumber));
                            dataBase.updatePATTable(pat.patID.getValue(), String.valueOf(assignedStudentsNumber));

                            String selectedListItem = listItems.get(patId);
                            selectedListItem = pat.patName.getValue() + "   " + pat.assignedStudents.getValue();
                            listItems.set(patId, selectedListItem);

                        } else {
                            remainingStudents.add(year1Students.get(i));
                        }
                    } else if (pat.load.getValue().equalsIgnoreCase("partial")) {

                        if ((Integer.parseInt(pat.assignedStudents.getValue())) < 5) {

                            String[] studentyear1data = year1Students.get(i).split(",");
                            String query = "INSERT INTO studentsinformation values(" + '"' + studentyear1data[0] + '"' + "," + '"'
                                    + studentyear1data[1] + '"' + "," + '"' + studentyear1data[2] + '"'
                                    + "," + '"' + studentyear1data[3] + '"' + "," + '"' + studentyear1data[4] + '"'
                                    + "," + '"' + studentyear1data[5] + '"' + "," + '"' + pat.patID.getValue() + '"' + ");";

                            dataBase.executeQuery(query);

                            int assignedStudentsNumber = Integer.parseInt(pat.assignedStudents.getValue());
                            assignedStudentsNumber += 1;
                            pat.assignedStudents.setValue(String.valueOf(assignedStudentsNumber));
                            dataBase.updatePATTable(pat.patID.getValue(), String.valueOf(assignedStudentsNumber));

                            String selectedListItem = listItems.get(patId);
                            selectedListItem = pat.patName.getValue() + "   " + pat.assignedStudents.getValue();
                            listItems.set(patId, selectedListItem);

                        } else {
                            remainingStudents.add(year1Students.get(i));
                        }
                    }
                }

                if (i < year2Students.size()) {
                    if (pat.load.getValue().equalsIgnoreCase("full")) {

                        if ((Integer.parseInt(pat.assignedStudents.getValue())) < 9) {

                            String[] studentyear1data = year2Students.get(i).split(",");
                            String query = "INSERT INTO studentsinformation values(" + '"' + studentyear1data[0] + '"' + "," + '"'
                                    + studentyear1data[1] + '"' + "," + '"' + studentyear1data[2] + '"'
                                    + "," + '"' + studentyear1data[3] + '"' + "," + '"' + studentyear1data[4] + '"'
                                    + "," + '"' + studentyear1data[5] + '"' + "," + '"' + pat.patID.getValue() + '"' + ");";

                            dataBase.executeQuery(query);

                            int assignedStudentsNumber = Integer.parseInt(pat.assignedStudents.getValue());
                            assignedStudentsNumber += 1;
                            pat.assignedStudents.setValue(String.valueOf(assignedStudentsNumber));
                            dataBase.updatePATTable(pat.patID.getValue(), String.valueOf(assignedStudentsNumber));

                            String selectedListItem = listItems.get(patId);
                            selectedListItem = pat.patName.getValue() + "   " + pat.assignedStudents.getValue();
                            listItems.set(patId, selectedListItem);

                        } else {
                            remainingStudents.add(year2Students.get(i));
                        }
                    } else if (pat.load.getValue().equalsIgnoreCase("partial")) {

                        if ((Integer.parseInt(pat.assignedStudents.getValue())) < 5) {

                            String[] studentyear1data = year2Students.get(i).split(",");
                            String query = "INSERT INTO studentsinformation values(" + '"' + studentyear1data[0] + '"' + "," + '"'
                                    + studentyear1data[1] + '"' + "," + '"' + studentyear1data[2] + '"'
                                    + "," + '"' + studentyear1data[3] + '"' + "," + '"' + studentyear1data[4] + '"'
                                    + "," + '"' + studentyear1data[5] + '"' + "," + '"' + pat.patID.getValue() + '"' + ");";

                            dataBase.executeQuery(query);

                            int assignedStudentsNumber = Integer.parseInt(pat.assignedStudents.getValue());
                            assignedStudentsNumber += 1;
                            pat.assignedStudents.setValue(String.valueOf(assignedStudentsNumber));
                            dataBase.updatePATTable(pat.patID.getValue(), String.valueOf(assignedStudentsNumber));

                            String selectedListItem = listItems.get(patId);
                            selectedListItem = pat.patName.getValue() + "   " + pat.assignedStudents.getValue();
                            listItems.set(patId, selectedListItem);

                        } else {
                            remainingStudents.add(year2Students.get(i));
                        }
                    }
                }

                if (i < year3Students.size()) {
                    if (pat.load.getValue().equalsIgnoreCase("full")) {

                        if ((Integer.parseInt(pat.assignedStudents.getValue())) < 9) {

                            String[] studentyear1data = year3Students.get(i).split(",");
                            String query = "INSERT INTO studentsinformation values(" + '"' + studentyear1data[0] + '"' + "," + '"'
                                    + studentyear1data[1] + '"' + "," + '"' + studentyear1data[2] + '"'
                                    + "," + '"' + studentyear1data[3] + '"' + "," + '"' + studentyear1data[4] + '"'
                                    + "," + '"' + studentyear1data[5] + '"' + "," + '"' + pat.patID.getValue() + '"' + ");";

                            dataBase.executeQuery(query);

                            int assignedStudentsNumber = Integer.parseInt(pat.assignedStudents.getValue());
                            assignedStudentsNumber += 1;
                            pat.assignedStudents.setValue(String.valueOf(assignedStudentsNumber));
                            dataBase.updatePATTable(pat.patID.getValue(), String.valueOf(assignedStudentsNumber));

                            String selectedListItem = listItems.get(patId);
                            selectedListItem = pat.patName.getValue() + "   " + pat.assignedStudents.getValue();
                            listItems.set(patId, selectedListItem);

                        } else {
                            remainingStudents.add(year3Students.get(i));
                        }
                    } else if (pat.load.getValue().equalsIgnoreCase("partial")) {

                        if ((Integer.parseInt(pat.assignedStudents.getValue())) < 5) {

                            String[] studentyear1data = year3Students.get(i).split(",");
                            String query = "INSERT INTO studentsinformation values(" + '"' + studentyear1data[0] + '"' + "," + '"'
                                    + studentyear1data[1] + '"' + "," + '"' + studentyear1data[2] + '"'
                                    + "," + '"' + studentyear1data[3] + '"' + "," + '"' + studentyear1data[4] + '"'
                                    + "," + '"' + studentyear1data[5] + '"' + "," + '"' + pat.patID.getValue() + '"' + ");";

                            dataBase.executeQuery(query);

                            int assignedStudentsNumber = Integer.parseInt(pat.assignedStudents.getValue());
                            assignedStudentsNumber += 1;
                            pat.assignedStudents.setValue(String.valueOf(assignedStudentsNumber));
                            dataBase.updatePATTable(pat.patID.getValue(), String.valueOf(assignedStudentsNumber));

                            String selectedListItem = listItems.get(patId);
                            selectedListItem = pat.patName.getValue() + "   " + pat.assignedStudents.getValue();
                            listItems.set(patId, selectedListItem);

                        } else {
                            remainingStudents.add(year3Students.get(i));
                        }
                    }
                }

                if (i < year4Students.size()) {
                    if (pat.load.getValue().equalsIgnoreCase("full")) {

                        if ((Integer.parseInt(pat.assignedStudents.getValue())) < 9) {

                            String[] studentyear1data = year4Students.get(i).split(",");
                            String query = "INSERT INTO studentsinformation values(" + '"' + studentyear1data[0] + '"' + "," + '"'
                                    + studentyear1data[1] + '"' + "," + '"' + studentyear1data[2] + '"'
                                    + "," + '"' + studentyear1data[3] + '"' + "," + '"' + studentyear1data[4] + '"'
                                    + "," + '"' + studentyear1data[5] + '"' + "," + '"' + pat.patID.getValue() + '"' + ");";

                            dataBase.executeQuery(query);

                            int assignedStudentsNumber = Integer.parseInt(pat.assignedStudents.getValue());
                            assignedStudentsNumber += 1;
                            pat.assignedStudents.setValue(String.valueOf(assignedStudentsNumber));
                            dataBase.updatePATTable(pat.patID.getValue(), String.valueOf(assignedStudentsNumber));

                            String selectedListItem = listItems.get(patId);
                            selectedListItem = pat.patName.getValue() + "   " + pat.assignedStudents.getValue();
                            listItems.set(patId, selectedListItem);

                        } else {
                            remainingStudents.add(year4Students.get(i));
                        }
                    } else if (pat.load.getValue().equalsIgnoreCase("partial")) {

                        if ((Integer.parseInt(pat.assignedStudents.getValue())) < 5) {

                            String[] studentyear1data = year4Students.get(i).split(",");
                            String query = "INSERT INTO studentsinformation values(" + '"' + studentyear1data[0] + '"' + "," + '"'
                                    + studentyear1data[1] + '"' + "," + '"' + studentyear1data[2] + '"'
                                    + "," + '"' + studentyear1data[3] + '"' + "," + '"' + studentyear1data[4] + '"'
                                    + "," + '"' + studentyear1data[5] + '"' + "," + '"' + pat.patID.getValue() + '"' + ");";

                            dataBase.executeQuery(query);

                            int assignedStudentsNumber = Integer.parseInt(pat.assignedStudents.getValue());
                            assignedStudentsNumber += 1;
                            pat.assignedStudents.setValue(String.valueOf(assignedStudentsNumber));
                            dataBase.updatePATTable(pat.patID.getValue(), String.valueOf(assignedStudentsNumber));

                            String selectedListItem = listItems.get(patId);
                            selectedListItem = pat.patName.getValue() + "   " + pat.assignedStudents.getValue();
                            listItems.set(patId, selectedListItem);

                        } else {
                            remainingStudents.add(year4Students.get(i));
                        }
                    }
                }

            }

            /**
             * Here we need to delete all students that have been assigned to
             * need to refresh the table view and also need to delete the
             * allocated students data from unallocated students table in the
             * database.
             */
            year1Students.clear();
            year2Students.clear();
            year3Students.clear();
            year4Students.clear();
            students.clear();

            for (int i = 0; i < remainingStudents.size(); i++) {
                tokensStudentInformation = remainingStudents.get(i).split(",");
                students.add(new Student(tokensStudentInformation[0], tokensStudentInformation[1],
                        tokensStudentInformation[2], tokensStudentInformation[3], tokensStudentInformation[4], tokensStudentInformation[5]));
            }

            final TreeItem<AssignPAT.Student> root = new RecursiveTreeItem<AssignPAT.Student>(students, RecursiveTreeObject::getChildren);
            tableView.setRoot(root);
            tableView.setShowRoot(false);
            deleteUnallocatedStudentsFromDataBase();

            if (confirmMessage("Some Students are remaining do you want to allocate them automatically\nRecommended: Assign them manually.")) {
                mazeedAutoAllocate();
            }
        }
    }

    /**
     * Deleting all the information from unallocated students table.
     */
    public void deleteUnallocatedStudentsFromDataBase() {

        String uobs = "";
        for (int i = 0; i < students.size(); i++) {
            if (i == 0) {
                uobs = '"' + students.get(i).studentUOB.getValue() + '"';
            } else {
                uobs = uobs + "," + '"' + students.get(i).studentUOB.getValue() + '"';
            }
        }

        String query = "DELETE FROM unallocatedstudents where uob NOT IN (" + uobs + ");";
        dataBase.executeQuery(query);
    }

    
    /**
     * If the user wants to allocate the un assigned the students by studnets then this method will do its duty.
     */
    public void mazeedAutoAllocate() {

        int patSize = pats.size();
        int size = remainingStudents.size();

        for (int i = 0; i < size; i++) {

            int patId = i % patSize;
            PAT pat = pats.get(patId);

            String[] studentallyeardata = remainingStudents.get(i).split(",");
            String query = "INSERT INTO studentsinformation values(" + '"' + studentallyeardata[0] + '"' + "," + '"'
                    + studentallyeardata[1] + '"' + "," + '"' + studentallyeardata[2] + '"'
                    + "," + '"' + studentallyeardata[3] + '"' + "," + '"' + studentallyeardata[4] + '"'
                    + "," + '"' + studentallyeardata[5] + '"' + "," + '"' + pat.patID.getValue() + '"' + ");";

            dataBase.executeQuery(query);
            query = "DELETE FROM unallocatedstudents where uob = " + '"' + studentallyeardata[0] + '"' + ";";
            dataBase.executeQuery(query);

            int assignedStudentsNumber = Integer.parseInt(pat.assignedStudents.getValue());
            assignedStudentsNumber += 1;
            pat.assignedStudents.setValue(String.valueOf(assignedStudentsNumber));

            String selectedListItem = listItems.get(patId);
            selectedListItem = pat.patName.getValue() + "   " + assignedStudentsNumber;
            listItems.set(patId, selectedListItem);

        }

        remainingStudents.clear();
        students.clear();
        final TreeItem<AssignPAT.Student> root = new RecursiveTreeItem<AssignPAT.Student>(students, RecursiveTreeObject::getChildren);
        tableView.setRoot(root);
        tableView.setShowRoot(false);
    }

    /**
     * When some students are allocated then there is a need to delete the students from unallcoated table and put them in it 
     * the allocated tables.
     */
    void delete(int index, int size) {

        for (int i = size - 1; i >= 0; i--) {
            int indices = selectedIndices.get(i);
            String query = "DELETE FROM unallocatedstudents where uob = " + '"' + students.get(indices).studentUOB.getValue() + '"' + ";";
            dataBase.executeQuery(query);
            students.remove(indices);
        }
        final TreeItem<AssignPAT.Student> root = new RecursiveTreeItem<AssignPAT.Student>(students, RecursiveTreeObject::getChildren);
        tableView.setRoot(root);
        tableView.setShowRoot(false);
    }

    /**
     * This class is for students containing students properties.
     */
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

    /**
     * This class is for PATS containing pat attributes.
     */
    private class PAT {

        StringProperty patID;
        StringProperty patName;
        StringProperty emailAddress;
        StringProperty officeNumber;
        StringProperty contactNumber;
        StringProperty dept;
        StringProperty load;
        StringProperty assignedStudents;

        public PAT(String patID, String patName, String emailAddress, String officeNumber,
                String contactNumber, String dept, String load, String assigendStudents) {

            this.patID = new SimpleStringProperty(patID);
            this.patName = new SimpleStringProperty(patName);
            this.emailAddress = new SimpleStringProperty(emailAddress);
            this.dept = new SimpleStringProperty(dept);
            this.officeNumber = new SimpleStringProperty(officeNumber);
            this.contactNumber = new SimpleStringProperty(contactNumber);
            this.load = new SimpleStringProperty(load);
            this.assignedStudents = new SimpleStringProperty(assigendStudents);
        }

        public String getPatName() {
            return patName.toString();
        }

        public String getPATID() {
            return patID.toString();
        }
    }

    public Tab getTab() {
        return tab;
    }
}
