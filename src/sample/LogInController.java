package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import dao.UserDAO;
import javafx.scene.control.TextField;
import sample.User;
import java.util.List;
import java.awt.*;

public class LogInController {
   @FXML private TextField user_login;
    @FXML private PasswordField user_password;
    @FXML private Label alert_login;
    public  boolean onSubmit()
    {
        List<User> list_user=UserDAO.getAllUser();
        for(User item: list_user) {
            if (user_login.getText().equals(item.getUsername()) && user_password.getText().equals(item.getPassword()))
                alert_login.setVisible(true);
        }
    }
}
