package sample;

import dao.CLassDAO;
import dao.UserDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class updateStudentTableController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TextField nameText;
    @FXML
    private TextField idText;
    @FXML
    private DatePicker birthDay;
    @FXML
    private CheckBox male;
    @FXML
    private CheckBox female;
    @FXML
    private TextField userName;
    @FXML
    private TextField passWord;
    @FXML
    private Label alert1;
    @FXML
    private Label noti;
    private User user;

    private Clazz curClazz;

    public void setUpdate(Clazz tmp)
    {
        curClazz=tmp;
    }
    private int gen;
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
            gen=1;
        }
        else {
            female.setSelected(true);
            male.setSelected(false);
            gen=0;
        }
    }


    public void Submit(ActionEvent e)
    {
        alert1.setVisible(false);
        String id = idText.getText();
        String name = nameText.getText();
        String username = userName.getText();
        String password = passWord.getText();
        LocalDate date =birthDay.getValue();
        if (id.isEmpty() || name.isEmpty() || username.isEmpty() || password.isEmpty() || date == null) {
           alert1.setVisible(true);
        } else {
            user = new User();
            user.setId(id);
            user.setRole(1);
            user.setName(name);
            user.setBirthday(Date.valueOf(date));
            user.setUsername(username);
            user.setPassword(password);
            if(male.isSelected()) {
                female.setSelected(false);
                user.setGender(1);
                if(gen==0) {
                    curClazz.setMale(curClazz.getMale() + 1);
                    curClazz.setFemale((curClazz.getFemale()-1));
                }
            }
            else {
                male.setSelected(false);
                user.setGender(0);
                if(gen==1)
                {
                curClazz.setFemale((curClazz.getFemale()+1));
                curClazz.setMale(curClazz.getMale() - 1);
                }
            }
        }
            UserDAO.updateUser(user);
            CLassDAO.updateClass(curClazz);
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            stage.close();

    }
    public void check_gender(ActionEvent e) {
        if (e.getSource() == male)
            female.setSelected(false);
        else {
            male.setSelected(false);
        }
    }
    private boolean findUser(User user) {
        List<User> list = UserDAO.getAllUserTeacher();
        for (User i : list)
            if (i.getId().compareTo(user.getId())==0)
                return true;
        return false;
    }
}
