package controller;

/**
 * *****************************************************************************
 * To change this license header, choose License Headers in Project Properties.*
 * To change this template file, choose Tools | Templates * and open the
 * template in the editor. *
 * *****************************************************************************
 */
import java.io.IOException;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

/**
 *
 * @author badar
 */
public class MainController extends Application {

    private MainWindowController mainWindowController; //controller of main window
    private FXMLLoader loader; // FXML Loader use for loading the fxml files and getting the controller
    private LogIn.LogInController logInController; //Controller for login

    @Override
    public void start(Stage stage) throws Exception {

        loader = new FXMLLoader(getClass().getResource("../LogIn/LogIn.fxml"));
        loader.load();
        logInController = loader.getController();
        Scene sceneLogIn = new Scene(logInController.gridPane);
        sceneLogIn.getStylesheets().add("/controller/style.css");
        final Stage logInStage = new Stage();
        logInStage.setScene(sceneLogIn);
        logInStage.show();

        /**
         * ***********************************************************
         * when the login button is clicked call goes to this method.*
         * ***********************************************************
         */
        logInController.loginButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent e) {

                boolean logIn = logInController.login();
                if (logIn) {

                    logInStage.close();
                    loader = new FXMLLoader(getClass().getResource("../controller/main.fxml"));

                    try {
                        loader.load();
                    } catch (IOException exception) {
                        exception.printStackTrace();
                    }
                    mainWindowController = loader.getController();

                    BorderPane borderPane = mainWindowController.getBorderPane();

                    Parent root = null;
                    try {
                        root = FXMLLoader.load(getClass().getResource("../controller/DashBoard.fxml"));
                    } catch (IOException exception) {
                        exception.printStackTrace();
                    }
                    borderPane.setCenter(root);
//                    borderPane.setStyle("-fx-background-image: url(\"/icons/Register.png\");-fx-background-size: 900, 1700;-fx-background-repeat: repeat;");
                    Scene scene = new Scene(borderPane);
                    scene.getStylesheets().add("/controller/style.css");
                    stage.setScene(scene);
                    stage.show();
                    mainWindowController.openDrawer();

                    /**
                     * **********************************************************
                     * When logout button is clicked call goes to this method. *
                     * *
                     * **********************************************************
                     */
                    mainWindowController.drawerController.logoutBt.setOnMouseClicked(new EventHandler<MouseEvent>() {

                        @Override
                        public void handle(MouseEvent event) {

                            stage.close();
                            logInStage.show();
                        }
                    });

                } else {

                    ButtonType ok = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                    ButtonType cancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
                    Alert alert = new Alert(Alert.AlertType.WARNING, null, ok, cancel);

                    alert.setTitle("Warning");
                    alert.setHeaderText("Look, an Information Dialog");
                    alert.setContentText("User Name or Password is InCorrect");
                    alert.showAndWait();

                }
            }
        });
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
