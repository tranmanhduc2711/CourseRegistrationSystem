package sample;

import dao.UserDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;


import java.io.IOException;
import java.util.List;

public class LogInController {
    @FXML
    private TextField user_login;
    @FXML
    private PasswordField user_password;
    @FXML
    private Label alert_login;
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void logIn(ActionEvent event) throws IOException {
        List<User> teacher_user = UserDAO.getAllUserTeacher();
         List<User> student_user =UserDAO.getAllUserStudent();
        alert_login.setVisible(false);
        for (User item : teacher_user) {
            if (user_login.getText().equals(item.getUsername()) && user_password.getText().equals(item.getPassword())) {

                FXMLLoader loader= new FXMLLoader(getClass().getResource("TeacherFeature.fxml"));
                root=loader.load();
                stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
                scene=new Scene(root);
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();

            }
            alert_login.setVisible(true);
        }
        for (User item : student_user) {
            if (user_login.getText().equals(item.getUsername()) && user_password.getText().equals(item.getPassword())) {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("StudentFeatureTable.fxml"));
                root=loader.load();
                stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
                StudentFeatureController controller = loader.getController();
                controller.setUser(item);
                scene = new Scene(root);
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();

            }
            alert_login.setVisible(true);
        }
    }



}
