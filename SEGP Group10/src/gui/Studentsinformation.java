/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

//import com.jfoenix.controls.JFXButton;
//import com.jfoenix.controls.JFXListView;
//import com.jfoenix.controls.JFXPopup;
//import com.jfoenix.controls.JFXTreeTableColumn;
//import com.jfoenix.controls.JFXTreeTableView;
//import com.jfoenix.controls.RecursiveTreeItem;
import   com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXTreeTableView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

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
    private Tab tab;

    private JFXPopup popUpYear1 = new JFXPopup();
    private Label year1;

    private JFXPopup popUpYear2 = new JFXPopup();
    private Label year2;

    private JFXPopup popUpYear3 = new JFXPopup();
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

        JFXButton buttonEEYear4  = new JFXButton("EEE");
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
        
        
//
//        JFXTreeTableColumn<PAT, String> PATId = new JFXTreeTableColumn<>("ID");
//        PATId.setPrefWidth(100);
//        PATId.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<PAT, String>, ObservableValue<String>>() {
//
//            @Override
//            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<PAT, String> param) {
//                return param.getValue().getValue().PATId;
//            }
//        });
//
//        JFXTreeTableColumn<PAT, String> PATName = new JFXTreeTableColumn<>("Name");
//        PATName.setPrefWidth(150);
//        PATName.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<PAT, String>, ObservableValue<String>>() {
//
//            @Override
//            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<PAT, String> param) {
//                return param.getValue().getValue().PATName;
//            }
//        });
//
//        JFXTreeTableColumn<PAT, String> emailAddress = new JFXTreeTableColumn<>("Email");
//        emailAddress.setPrefWidth(200);
//        emailAddress.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<PAT, String>, ObservableValue<String>>() {
//
//            @Override
//            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<PAT, String> param) {
//                return param.getValue().getValue().emailAddress;
//            }
//        });
//
//        JFXTreeTableColumn<PAT, String> PATLoad = new JFXTreeTableColumn<>("Load");
//        PATLoad.setPrefWidth(100);
//        PATLoad.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<PAT, String>, ObservableValue<String>>() {
//
//            @Override
//            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<PAT, String> param) {
//                return param.getValue().getValue().PATLoad;
//            }
//        });
//
//        JFXTreeTableColumn<PAT, String> officeNumber = new JFXTreeTableColumn<>("Office Number");
//        officeNumber.setPrefWidth(150);
//        officeNumber.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<PAT, String>, ObservableValue<String>>() {
//
//            @Override
//            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<PAT, String> param) {
//                return param.getValue().getValue().officeNumber;
//            }
//        });
//
//        JFXTreeTableColumn<PAT, String> personalContact = new JFXTreeTableColumn<>("Personal Contact");
//        personalContact.setPrefWidth(150);
//        personalContact.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<PAT, String>, ObservableValue<String>>() {
//
//            @Override
//            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<PAT, String> param) {
//                return param.getValue().getValue().contactNumber;
//            }
//        });
//
//        JFXTreeTableColumn<PAT, String> dept = new JFXTreeTableColumn<>("Department");
//        dept.setPrefWidth(100);
//        dept.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<PAT, String>, ObservableValue<String>>() {
//
//            @Override
//            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<PAT, String> param) {
//                return param.getValue().getValue().dept;
//            }
//        });
//
//        ObservableList<PAT> PATs = FXCollections.observableArrayList();
//        PATs.add(new PAT("1", "Dr.Adil Raja", "adil@namal.edu.pk", "Full", "04567656554", "03027867564", "CS"));
//        PATs.add(new PAT("2", "Dr.Adil Raja", "adil@namal.edu.pk", "Full", "04567656554", "03027867564", "CS"));
//        PATs.add(new PAT("5", "Dr.Adil Raja", "adil@namal.edu.pk", "Full", "04567656554", "03027867564", "CS"));
//        PATs.add(new PAT("6", "Dr.Adil Raja", "adil@namal.edu.pk", "Full", "04567656554", "03027867564", "CS"));
//        PATs.add(new PAT("8", "Dr.Adil Raja", "adil@namal.edu.pk", "Full", "04567656554", "03027867564", "CS"));
//        PATs.add(new PAT("0", "Dr.Adil Raja", "adil@namal.edu.pk", "Full", "04567656554", "03027867564", "CS"));
//
//        final TreeItem<PAT> root = new RecursiveTreeItem<PAT>(PATs, RecursiveTreeObject::getChildren);
//        treeTable.getColumns().setAll(PATId, PATName, emailAddress, PATLoad, officeNumber, personalContact, dept);
//        treeTable.setRoot(root);
//        treeTable.setShowRoot(false);
//    }
//
//   private class PAT extends RecursiveTreeObject<PAT> {
//
//        StringProperty studenId;
//        StringProperty studenName;
//        StringProperty emailAddress;
//        StringProperty officeNumber;
//        StringProperty contactNumber;
//        StringProperty dept;
//
//        public PAT(String PATId, String PATName, String emailAddress, String load, String officeNumber, String contactNumber,
//                String dept) {
//
//            this.PATId = new SimpleStringProperty(PATId);
//            this.PATName = new SimpleStringProperty(PATName);
//            this.emailAddress = new SimpleStringProperty(emailAddress);
//            this.PATLoad = new SimpleStringProperty(load);
//            this.officeNumber = new SimpleStringProperty(officeNumber);
//            this.contactNumber = new SimpleStringProperty(contactNumber);
//            this.dept = new SimpleStringProperty(dept);
//        }
    }
    public AnchorPane getPane() {

        return anchorPane;
    }
    
    public Tab getTab(){
        System.out.println(tab);
        return tab;
    }
}