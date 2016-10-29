/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Setting;

//import com.jfoenix.controls.JFXButton;
//import com.jfoenix.controls.JFXListView;
//import com.jfoenix.controls.JFXPopup;
//import com.jfoenix.controls.JFXTreeTableColumn;
//import com.jfoenix.controls.JFXTreeTableView;
//import com.jfoenix.controls.RecursiveTreeItem;
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
    private BorderPane borderPane;

    @FXML
    private JFXListView<String> listView;

    @FXML
    private JFXTreeTableView tableView;

    @FXML
    private JFXRadioButton studentListButton;

    @FXML
    private JFXRadioButton patListButton;

    @FXML
    private JFXTextField filePathField;

    @FXML
    private JFXTextField searchTextField;
    
    @FXML
    private Tab tab;

    private DataBase dataBase = new DataBase();
    private ObservableList<String> listItems = FXCollections.observableArrayList();
    private ObservableList<Student> students = FXCollections.observableArrayList();
    private ObservableList<Student> assignedStudents = FXCollections.observableArrayList();
    private ObservableList<PAT> pats = FXCollections.observableArrayList();
    private ObservableList<PAT> allocatedPATs = FXCollections.observableArrayList();
    private ObservableList<Student> selectedIndices = FXCollections.observableArrayList();
    private ArrayList<String> year1Students = new ArrayList<>();
    private ArrayList<String> year2Students = new ArrayList<>();
    private ArrayList<String> year3Students = new ArrayList<>();
    private ArrayList<String> year4Students = new ArrayList<>();

    String[] tokensPATInformation;
    String[] tokensStudentInformation;

    /**
     * Alert Boxes Controller reference variables
     */
    private FXMLLoader loader;
    private ConfirmationController confirmationController;

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

        JFXTreeTableColumn<AssignPAT.Student, String> studentName = new JFXTreeTableColumn<>("Name");
        studentName.setPrefWidth(150);
        studentName.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<AssignPAT.Student, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<AssignPAT.Student, String> param) {
                return param.getValue().getValue().studentName;
            }
        });

        JFXTreeTableColumn<AssignPAT.Student, String> emailAddress = new JFXTreeTableColumn<>("Email");
        emailAddress.setPrefWidth(200);
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
        personalContact.setPrefWidth(150);
        personalContact.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<AssignPAT.Student, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<AssignPAT.Student, String> param) {
                return param.getValue().getValue().contactNumber;
            }
        });

        JFXTreeTableColumn<AssignPAT.Student, String> dept = new JFXTreeTableColumn<>("Dept");
        dept.setPrefWidth(150);
        dept.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<AssignPAT.Student, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<AssignPAT.Student, String> param) {
                return param.getValue().getValue().dept;
            }
        });

        tableView.getColumns().setAll(studentUOB, studentName, emailAddress, studentYear, personalContact, dept);
//        patListButton.setSelected(true);
//        patListButton.  

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

        loader = loader = new FXMLLoader(getClass().getResource("../alertbox/ConfirmationDialog.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        confirmationController = loader.getController();

    }

    boolean vaue = false;

    public BorderPane getBorderPane() {
        return borderPane;
    }

    @FXML
    public void listViewOnClicked() {

    }

    @FXML
    private void browseButtonOnClicked() {

        Stage primaryStage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("Excel Files", "*.xlsm", "*.xlsx", "*.xlt", "*.xlv", "*.ods", "*.csv"));
        File selectedFile = fileChooser.showOpenDialog(primaryStage);
        if (selectedFile.getAbsolutePath() != null) {
            filePathField.setText(selectedFile.getAbsolutePath());
        }
//        System.out.println(selectedFile.getAbsolutePath());

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

                deleteQuery = "TRUNCATE studentsinformation;";
                dataBase.executeQuery(deleteQuery);
                deleteQuery = "TRUNCATE unallocatedstudents;";
                dataBase.executeQuery(deleteQuery);

                students.clear();

            } else if (studentListButton.isSelected()) {

                String deleteQuery = "TRUNCATE studentsinformation;";
                dataBase.executeQuery(deleteQuery);
                deleteQuery = "TRUNCATE unallocatedstudents;";
                dataBase.executeQuery(deleteQuery);

                students.clear();

            }

            while (input.hasNextLine()) {

                if (patListButton.isSelected()) {

                    /**
                     * Here the under given query will erase of previous data.
                     * Delete Everything from pats information table.
                     */
                    tokensPATInformation = input.nextLine().trim().split(",");
                    listItems.add(tokensPATInformation[1]);

                    pats.add(new PAT(tokensPATInformation[0], tokensPATInformation[1], tokensPATInformation[2], tokensPATInformation[3], tokensPATInformation[4], tokensPATInformation[5],
                            tokensPATInformation[6]));

                    String query = "INSERT INTO patsinformation values(" + '"' + tokensPATInformation[0] + '"'
                            + "," + '"' + tokensPATInformation[1] + '"' + "," + '"' + tokensPATInformation[2] + '"'
                            + "," + '"' + tokensPATInformation[3] + '"' + "," + '"' + tokensPATInformation[4] + '"'
                            + "," + '"' + tokensPATInformation[5] + '"' + "," + '"'
                            + tokensPATInformation[6] + '"' + ");";
                    dataBase.executeQuery(query);

                }

                if (studentListButton.isSelected()) {

                    String deleteQuery = "TRUNCATE studentsinformation;";
                    dataBase.executeQuery(deleteQuery);

                    deleteQuery = "TRUNCATE unallocatedstudents;";
                    dataBase.executeQuery(deleteQuery);

                    /**
                     * Here the under given query will erase of previous data.
                     * Delete Everything from students information and
                     * unallocated students table.
                     */
                    String result = input.nextLine();
                    tokensStudentInformation = result.trim().split(",");

                    /**
                     * getting the data of each year student seperately to get
                     * hnadle the data easily. And to avoid using of loops again
                     * and again to find the data of a single year student. It
                     * is done because if auto allocattion is allowed then the
                     * system may able to select different students.
                     */
                    if (tokensStudentInformation[3].trim().equalsIgnoreCase("Year-1")) {
                        year1Students.add(result);
                    } else if (tokensStudentInformation[3].trim().equalsIgnoreCase("year-2")) {
                        year2Students.add(result);
                    } else if (tokensStudentInformation[3].trim().equalsIgnoreCase("year-3")) {
                        year3Students.add(result);
                    } else if (tokensStudentInformation[3].trim().equalsIgnoreCase("year-4")) {
                        year4Students.add(result);
                    }

                    students.add(new Student(tokensStudentInformation[0], tokensStudentInformation[1],
                            tokensStudentInformation[2], tokensStudentInformation[3], tokensStudentInformation[4],
                            tokensStudentInformation[5]));

                    String query = "INSERT INTO unallocatedstudents values(" + '"' + tokensStudentInformation[0] + '"'
                            + "," + '"' + tokensStudentInformation[1] + '"' + "," + '"' + tokensStudentInformation[2] + '"'
                            + "," + '"' + tokensStudentInformation[3] + '"' + "," + '"' + tokensStudentInformation[4] + '"'
                            + "," + '"' + tokensStudentInformation[5] + '"' + ");";
                    dataBase.executeQuery(query);
                }
                final TreeItem<AssignPAT.Student> root = new RecursiveTreeItem<AssignPAT.Student>(students, RecursiveTreeObject::getChildren);

                tableView.setRoot(root);
                tableView.setShowRoot(false);
                tableView.getSelectionModel().setSelectionMode(
                        SelectionMode.MULTIPLE
                );
                listView.setItems(listItems);

            }
        }
    }

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

    @FXML
    public void manualAllocationClicked() {

        /**
         * Getting selected pat from the list of pats.
         */
        int index = listView.getSelectionModel().getSelectedIndex();
        PAT pat = pats.get(index);
        String patID = pat.patID.getValue();
        String patLoad = pat.load.getValue().toString();

        /**
         * Here need to get the selected rows from the unallocated students
         * table.
         */
        selectedIndices = tableView.getSelectionModel().getSelectedIndices();
        ObservableList<Integer> indexes = tableView.getSelectionModel().getSelectedIndices();
        Student[] studentInformation = new Student[selectedIndices.size()];

        boolean assignmentAllowed = false;
        if (patLoad.equalsIgnoreCase("Full")) {
            if ((pat.assignedStudents + selectedIndices.size()) > 8) {
                assignmentAllowed = false;
                assignmentAllowed = confirmMessage("This teacher is already full of load\nDo you want to proceed fuether?");
            } else {
                assignmentAllowed = true;
            }
        } else if (patLoad.equalsIgnoreCase("Partial")) {
            if ((pat.assignedStudents + selectedIndices.size()) > 4) {
                assignmentAllowed = false;
                assignmentAllowed = confirmMessage("This teacher is already full of load\nDo you want to proceed fuether?");
            } else {
                assignmentAllowed = true;
            }
        }

        if (assignmentAllowed) {

            /**
             * Need to create an array to remmber the UOB's of students
             */
            /**
             * Getting the selected indices from table.
             */
            for (int i = 0; i < selectedIndices.size(); i++) {
                studentInformation[i] = students.get(i);
            }

            /**
             * Inserting the students information in to the allocated students
             * table.
             */
            for (int i = 0; i < studentInformation.length; i++) {
                String query = "INSERT INTO studentsinformation values(" + '"' + studentInformation[i].studentUOB.getValue() + '"'
                        + "," + '"' + studentInformation[i].studentName.getValue() + '"' + "," + '"' + studentInformation[i].emailAddress.getValue() + '"'
                        + "," + '"' + studentInformation[i].studentYear.getValue() + '"' + "," + '"' + studentInformation[i].contactNumber.getValue() + '"'
                        + "," + '"' + studentInformation[i].dept.getValue() + '"' + "," + '"' + patID + '"' + ");";
                dataBase.executeQuery(query);
                pat.assignedStudents++;
            }

            String selectedListItem = listItems.get(index);
            selectedListItem = pat.patName.getValue() + "   " + pat.assignedStudents;
            listItems.set(index, selectedListItem);

            /**
             * After deleting the assigning the user selected students to A PAT
             * then there is a need to delete those students from unallocated
             * students tables and insert them to allocated students table. To
             * add students the above query will execute but to delete the data
             * form the table call goes to delete method. And then need to
             * refresh the table.
             */
            delete(indexes.get(0), indexes.size());
            final TreeItem<AssignPAT.Student> root = new RecursiveTreeItem<AssignPAT.Student>(students, RecursiveTreeObject::getChildren);
            tableView.setRoot(root);
            tableView.setShowRoot(false);

        }
    }

    @FXML
    public void autoAllocatesClicked() {

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

        ArrayList<String> remainingStudents = new ArrayList<>();
        for (int i = 0; i < size; i++) {

            int patId = i % patSize;
            PAT pat = pats.get(patId);

            if (i < year1Students.size()) {
                if (pat.load.getValue().equalsIgnoreCase("full")) {

                    if (pat.assignedStudents < 9) {

                        String[] studentyear1data = year1Students.get(i).split(",");
                        String query = "INSERT INTO studentsinformation values(" + '"' + studentyear1data[0] + '"' + "," + '"'
                                + studentyear1data[1] + '"' + "," + '"' + studentyear1data[2] + '"'
                                + "," + '"' + studentyear1data[3] + '"' + "," + '"' + studentyear1data[4] + '"'
                                + "," + '"' + studentyear1data[5] + '"' + "," + '"' + pat.patID.getValue() + '"' + ");";

                        dataBase.executeQuery(query);
                        String selectedListItem = listItems.get(patId);
                        selectedListItem = pat.patName.getValue() + "   " + pat.assignedStudents;
                        listItems.set(patId, selectedListItem);
                        pat.assignedStudents++;

                    } else {
                        remainingStudents.add(year1Students.get(i));
                    }
                } else if (pat.load.getValue().equalsIgnoreCase("partial")) {

                    if (pat.assignedStudents < 5) {

                        String[] studentyear1data = year1Students.get(i).split(",");
                        String query = "INSERT INTO studentsinformation values(" + '"' + studentyear1data[0] + '"' + "," + '"'
                                + studentyear1data[1] + '"' + "," + '"' + studentyear1data[2] + '"'
                                + "," + '"' + studentyear1data[3] + '"' + "," + '"' + studentyear1data[4] + '"'
                                + "," + '"' + studentyear1data[5] + '"' + "," + '"' + pat.patID.getValue() + '"' + ");";

                        dataBase.executeQuery(query);
                        String selectedListItem = listItems.get(patId);
                        selectedListItem = pat.patName.getValue() + "   " + pat.assignedStudents;
                        listItems.set(patId, selectedListItem);
                        pat.assignedStudents++;
                    } else {
                        remainingStudents.add(year1Students.get(i));
                    }
                }
            }

            if (i < year2Students.size()) {
                if (pat.load.getValue().equalsIgnoreCase("full")) {

                    if (pat.assignedStudents < 9) {

                        String[] studentyear1data = year2Students.get(i).split(",");
                        String query = "INSERT INTO studentsinformation values(" + '"' + studentyear1data[0] + '"' + "," + '"'
                                + studentyear1data[1] + '"' + "," + '"' + studentyear1data[2] + '"'
                                + "," + '"' + studentyear1data[3] + '"' + "," + '"' + studentyear1data[4] + '"'
                                + "," + '"' + studentyear1data[5] + '"' + "," + '"' + pat.patID.getValue() + '"' + ");";

//                        System.out.println(query);
                        dataBase.executeQuery(query);
                        String selectedListItem = listItems.get(patId);
                        selectedListItem = pat.patName.getValue() + "   " + pat.assignedStudents;
                        listItems.set(patId, selectedListItem);
                        pat.assignedStudents++;
                    } else {
                        remainingStudents.add(year2Students.get(i));
                    }
                } else if (pat.load.getValue().equalsIgnoreCase("partial")) {

                    if (pat.assignedStudents < 5) {

                        String[] studentyear1data = year2Students.get(i).split(",");
                        String query = "INSERT INTO studentsinformation values(" + '"' + studentyear1data[0] + '"' + "," + '"'
                                + studentyear1data[1] + '"' + "," + '"' + studentyear1data[2] + '"'
                                + "," + '"' + studentyear1data[3] + '"' + "," + '"' + studentyear1data[4] + '"'
                                + "," + '"' + studentyear1data[5] + '"' + "," + '"' + pat.patID.getValue() + '"' + ");";

                        dataBase.executeQuery(query);
                        String selectedListItem = listItems.get(patId);
                        selectedListItem = pat.patName.getValue() + "   " + pat.assignedStudents;
                        listItems.set(patId, selectedListItem);
                        pat.assignedStudents++;
                    } else {
                        remainingStudents.add(year2Students.get(i));
                    }
                }
            }

            if (i < year3Students.size()) {
                if (pat.load.getValue().equalsIgnoreCase("full")) {

                    if (pat.assignedStudents < 9) {

                        String[] studentyear1data = year3Students.get(i).split(",");
                        String query = "INSERT INTO studentsinformation values(" + '"' + studentyear1data[0] + '"' + "," + '"'
                                + studentyear1data[1] + '"' + "," + '"' + studentyear1data[2] + '"'
                                + "," + '"' + studentyear1data[3] + '"' + "," + '"' + studentyear1data[4] + '"'
                                + "," + '"' + studentyear1data[5] + '"' + "," + '"' + pat.patID.getValue() + '"' + ");";

                        dataBase.executeQuery(query);
                        String selectedListItem = listItems.get(patId);
                        selectedListItem = pat.patName.getValue() + "   " + pat.assignedStudents;
                        listItems.set(patId, selectedListItem);
                        pat.assignedStudents++;
                    } else {
                        remainingStudents.add(year3Students.get(i));
                    }
                } else if (pat.load.getValue().equalsIgnoreCase("partial")) {

                    if (pat.assignedStudents < 5) {

                        String[] studentyear1data = year3Students.get(i).split(",");
                        String query = "INSERT INTO studentsinformation values(" + '"' + studentyear1data[0] + '"' + "," + '"'
                                + studentyear1data[1] + '"' + "," + '"' + studentyear1data[2] + '"'
                                + "," + '"' + studentyear1data[3] + '"' + "," + '"' + studentyear1data[4] + '"'
                                + "," + '"' + studentyear1data[5] + '"' + "," + '"' + pat.patID.getValue() + '"' + ");";

                        dataBase.executeQuery(query);
                        String selectedListItem = listItems.get(patId);
                        selectedListItem = pat.patName.getValue() + "   " + pat.assignedStudents;
                        listItems.set(patId, selectedListItem);
                        pat.assignedStudents++;
                    } else {
                        remainingStudents.add(year3Students.get(i));
                    }
                }
            }

            if (i < year4Students.size()) {
                if (pat.load.getValue().equalsIgnoreCase("full")) {

                    if (pat.assignedStudents < 9) {

                        String[] studentyear1data = year4Students.get(i).split(",");
                        String query = "INSERT INTO studentsinformation values(" + '"' + studentyear1data[0] + '"' + "," + '"'
                                + studentyear1data[1] + '"' + "," + '"' + studentyear1data[2] + '"'
                                + "," + '"' + studentyear1data[3] + '"' + "," + '"' + studentyear1data[4] + '"'
                                + "," + '"' + studentyear1data[5] + '"' + "," + '"' + pat.patID.getValue() + '"' + ");";

                        dataBase.executeQuery(query);
                        String selectedListItem = listItems.get(patId);
                        selectedListItem = pat.patName.getValue() + "   " + pat.assignedStudents;
                        listItems.set(patId, selectedListItem);
                        pat.assignedStudents++;
                    } else {
                        remainingStudents.add(year4Students.get(i));
                    }
                } else if (pat.load.getValue().equalsIgnoreCase("partial")) {

                    if (pat.assignedStudents < 5) {

                        String[] studentyear1data = year4Students.get(i).split(",");
                        String query = "INSERT INTO studentsinformation values(" + '"' + studentyear1data[0] + '"' + "," + '"'
                                + studentyear1data[1] + '"' + "," + '"' + studentyear1data[2] + '"'
                                + "," + '"' + studentyear1data[3] + '"' + "," + '"' + studentyear1data[4] + '"'
                                + "," + '"' + studentyear1data[5] + '"' + "," + '"' + pat.patID.getValue() + '"' + ");";

                        dataBase.executeQuery(query);
                        String selectedListItem = listItems.get(patId);
                        selectedListItem = pat.patName.getValue() + "   " + pat.assignedStudents;
                        listItems.set(patId, selectedListItem);
                        pat.assignedStudents++;
                    } else {
                        remainingStudents.add(year4Students.get(i));
                    }
                }
            }

        }

        /**
         * Here we need to delete all students that have been assigned to need
         * to refresh the table view and also need to delete the allocated
         * students data from unallocated students table in the database.
         */
        students.clear();
        for (int i = 0; i < remainingStudents.size(); i++) {
            tokensStudentInformation = remainingStudents.get(i).split(",");
            students.add(new Student(tokensStudentInformation[0], tokensStudentInformation[1],
                    tokensStudentInformation[2], tokensStudentInformation[3], tokensStudentInformation[4], tokensStudentInformation[4]));
        }

        final TreeItem<AssignPAT.Student> root = new RecursiveTreeItem<AssignPAT.Student>(students, RecursiveTreeObject::getChildren);
        tableView.setRoot(root);
        tableView.setShowRoot(false);
        deleteUnallocatedStudentsFromDataBase();

        if (confirmMessage("Some Students are remaining do you want to allocate them automatically\nRecommended: Assign them manually.")) {
            mazeedAutoAllocate(remainingStudents);
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
        System.out.println(query);
        dataBase.executeQuery(query);
    }

    public void mazeedAutoAllocate(ArrayList<String> remainingStudents) {

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

            String selectedListItem = listItems.get(patId);
            selectedListItem = pat.patName.getValue() + "   " + pat.assignedStudents;
            listItems.set(patId, selectedListItem);
            pat.assignedStudents++;
        }

        students.clear();
        final TreeItem<AssignPAT.Student> root = new RecursiveTreeItem<AssignPAT.Student>(students, RecursiveTreeObject::getChildren);
        tableView.setRoot(root);
        tableView.setShowRoot(false);
    }

    void delete(int index, int size) {

        for (int i = 0; i < size; i++) {
            String query = "DELETE FROM unallocatedstudents where uob = " + '"' + students.get(i).studentUOB.getValue() + '"' + ";";
            dataBase.executeQuery(query);
            students.remove(index);
        }
        final TreeItem<AssignPAT.Student> root = new RecursiveTreeItem<AssignPAT.Student>(students, RecursiveTreeObject::getChildren);
        tableView.setRoot(root);
        tableView.setShowRoot(false);
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

    private class PAT {

        StringProperty patID;
        StringProperty patName;
        StringProperty emailAddress;
        StringProperty officeNumber;
        StringProperty contactNumber;
        StringProperty dept;
        StringProperty load;
        int assignedStudents;

        public PAT(String patID, String patName, String emailAddress, String officeNumber,
                String contactNumber, String dept, String load) {

            this.patID = new SimpleStringProperty(patID);
            this.patName = new SimpleStringProperty(patName);
            this.emailAddress = new SimpleStringProperty(emailAddress);
            this.dept = new SimpleStringProperty(dept);
            this.officeNumber = new SimpleStringProperty(officeNumber);
            this.contactNumber = new SimpleStringProperty(contactNumber);
            this.load = new SimpleStringProperty(load);
        }

        public String getPatName() {
            return patName.toString();
        }

        public String getPATID() {
            return patID.toString();
        }
    }
    
    public Tab getTab(){
        return tab;
    }
}
