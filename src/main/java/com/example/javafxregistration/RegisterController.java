package com.example.javafxregistration;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RegisterController {

    @FXML
    private TextField fullNameField;

    @FXML
    private TextField emailIdField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button submitButton;

    @FXML
    public void listAll() throws SQLException
    {
        JdbcDao jdbcDao = new JdbcDao();
        ListView<String> list = new ListView<>();
        ObservableList<String> items = FXCollections.observableArrayList(jdbcDao.fetchAllUser());
        list.setItems(items);

        HBox hbox = new HBox(list);

        Scene scene = new Scene(hbox, 500, 520);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void register(ActionEvent event) throws SQLException {

        Window owner = submitButton.getScene().getWindow();

        System.out.println(fullNameField.getText());
        System.out.println(emailIdField.getText());
        System.out.println(passwordField.getText());
        if (fullNameField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter your name");
            return;
        }

        if (emailIdField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter your email id");
            return;
        }
        if (passwordField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter a password");
            return;
        }

        String fullName = fullNameField.getText();
        String emailId = emailIdField.getText();
        String password = passwordField.getText();

        JdbcDao jdbcDao = new JdbcDao();
        jdbcDao.insertRecord(fullName, emailId, password);

        showAlert(Alert.AlertType.CONFIRMATION, owner, "Registration Successful!",
                "Welcome " + fullNameField.getText());
    }

    @FXML
    public void deleteDataByName() throws SQLException {

Label label = new Label();
        HBox hbox = new HBox(label);


TextField textField = new TextField();
label.setText("Enter Name");
textField.setText(label.getText());
        Scene scene = new Scene(hbox, 500, 520);

//        Scene scene = new Scene(label);
       Stage stage = new Stage();
      stage.setScene(scene);
stage.show();

        String text = fullNameField.getText();
        JdbcDao jdbcDao = new JdbcDao();
        jdbcDao.deleteDataByName(text);

    }









    private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
}