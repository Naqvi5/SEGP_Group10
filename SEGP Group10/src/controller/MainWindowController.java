    package controller;

    /*
     * To change this license header, choose License Headers in Project Properties.
     * To change this template file, choose Tools | Templates
     * and open the template in the editor.
     */

    import com.jfoenix.controls.JFXDrawer;
    import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import com.jfoenix.transitions.hamburger.HamburgerBasicCloseTransition;
    import java.io.IOException;
    import java.net.URL;
    import java.util.ResourceBundle;
    import javafx.application.Application;
    import static javafx.application.Application.launch;
    import javafx.fxml.FXML;
    import javafx.fxml.FXMLLoader;
    import javafx.fxml.Initializable;
    import javafx.scene.Scene;
    import javafx.scene.input.MouseEvent;
    import javafx.scene.layout.BorderPane;
    import javafx.scene.layout.VBox;
    import javafx.stage.Stage;

    /**
     *
     * @author badar
     */
    public class MainWindowController implements Initializable {

        @FXML
        private JFXHamburger hamburger;

        @FXML
        private BorderPane borderpane;

        @FXML
        private JFXDrawer drawer;

        private DrawerController drawerController;
        private FXMLLoader loader;

        @Override
        public void initialize(URL location, ResourceBundle resources) {

            loader = new FXMLLoader(getClass().getResource("../gui/drawer.fxml"));
            try {
                loader.load();
            } catch (IOException e) {

            }
            drawerController = loader.getController();
            drawer.setSidePane(drawerController.getDrawerPane());

            HamburgerBackArrowBasicTransition burgerClose = new HamburgerBackArrowBasicTransition(hamburger);
            burgerClose.setRate(-1);
            hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
                burgerClose.setRate(burgerClose.getRate() * -1);
                burgerClose.play();

                if (drawer.isShown()) {
                    drawer.close();
                } else {
                    drawer.open();
                }

            });
        }

        public BorderPane getBorderPane() {
            return borderpane;
        }

    }
