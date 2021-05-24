import dao.UserDAO;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import org.w3c.dom.Text;
import sample.User;

import java.awt.*;
import java.util.List;

public class handleLogIn {
    @FXML TextField username;
    @FXML PasswordField password;
    @FXML Label alert;



    @FXML  boolean onSubmit()
    {
        String user= username.getText();
        String pass= password.getText();
        List<User> us= UserDAO.getAllUser();
        for(User item:us)
        {
            if(user.equals(item.getUsername()) && pass.equals(item.getPassword()))
            {
                alert.setVisible(true);
                return true;
            }
        }
        return false;
    }
}
