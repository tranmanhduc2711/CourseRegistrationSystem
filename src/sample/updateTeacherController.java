package sample;

import dao.UserDAO;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;


import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import javafx.event.ActionEvent;

public class updateTeacherController {
    @FXML
    private TextField nameText;
    @FXML
    private TextField idText;
    @FXML
    private DatePicker birthDay;
    @FXML
    private TextField userName;
    @FXML
    private TextField passWord;
    @FXML
    private CheckBox male;
    @FXML
    private CheckBox female;

    private User user;
    private boolean update = false;

    public void setUpdate(boolean u)
    {
        update = u;
    }
    public void setTextField(User user)
    {
        idText.setDisable(true);
        idText.setText(user.getId());
        if(user.getName() != null)
            nameText.setText(user.getName());
        if(user.getBirthday() != null)
            birthDay.setValue(user.getBirthday().toLocalDate());
        if(user.getUsername() != null)
            userName.setText(user.getUsername());
        if(user.getPassword() != null)
            passWord.setText(user.getPassword());
        if(user.getGender()==1) {
            male.setSelected(true);
            female.setSelected(false);
        }
        else {
            female.setSelected(true);
            male.setSelected(false);
        }
    }


    public void Submit(ActionEvent e)
    {
        String id = idText.getText();
        String name = nameText.getText();
        String username = userName.getText();
        String password = passWord.getText();
        LocalDate date =birthDay.getValue();
        if (id.isEmpty() || name.isEmpty() || username.isEmpty() || password.isEmpty() || date == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Hãy điền tất cả thông tin");
            alert.showAndWait();
        } else {
            user = new User();
            user.setId(id);
            user.setRole(0);
            user.setName(name);
            user.setBirthday(Date.valueOf(date));
            user.setUsername(username);
            user.setPassword(password);
            if(male.isSelected()) {
                female.setSelected(false);
                user.setGender(1);
            }
            else {
                male.setSelected(false);
                user.setGender(0);
            }
        }
        if(!update) {
            if(findUser(user))
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Mã đã tồn tại!");
                alert.showAndWait();
            }else {
                UserDAO.add_Teacher(user);
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.close();
            }
        }
        else
        {
            UserDAO.updateUser(user);
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            stage.close();
        }
    }

    private boolean findUser(User user) {
        List<User> list = UserDAO.getAllUser();
        for (User i : list)
            if (i.getId().compareTo(user.getId())==0)
                return true;
        return false;
    }



}
