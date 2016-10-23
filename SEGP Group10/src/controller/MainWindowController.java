    package controller;

    /*
     * To change this license header, choose License Headers in Project Properties.
     * To change this template file, choose Tools | Templates
     * and open the template in the editor.
     */

import Drawer.DrawerController;
    import com.jfoenix.controls.JFXDrawer;
    import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTabPane;
    import com.jfoenix.transitions.hamburger.HamburgerBasicCloseTransition;
    import java.io.IOException;
    import java.net.URL;
    import java.util.ResourceBundle;
    import javafx.fxml.FXML;
    import javafx.fxml.FXMLLoader;
    import javafx.fxml.Initializable;
    import javafx.scene.input.MouseEvent;
    import javafx.scene.layout.BorderPane;

    /**
     *
     * @author badar
     */
    public class MainWindowController implements Initializable {

        @FXML
        private JFXHamburger hamburger;

        @FXML
        private BorderPane borderPane;

        @FXML
        private JFXDrawer drawer;
        
        @FXML
        private JFXTabPane tabPane;

        private DrawerController drawerController;
        private FXMLLoader loader;

        @Override
        public void initialize(URL location, ResourceBundle resources) {

            loader = new FXMLLoader(getClass().getResource("../Drawer/drawer.fxml"));
            try {
                loader.load();
            } catch (IOException e) {

            }
            drawerController = loader.getController();
            drawerController.setTabPane(tabPane);
            drawer.setSidePane(drawerController.getDrawerPane());

            HamburgerBasicCloseTransition burgerClose = new HamburgerBasicCloseTransition(hamburger);
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
            return borderPane;
        }

    }
