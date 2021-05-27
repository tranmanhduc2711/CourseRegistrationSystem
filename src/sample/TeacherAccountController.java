package sample;

import dao.UserDAO;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TeacherAccountController implements Initializable {
    @FXML
    private TableView<User> table_list;
    @FXML
    private TableColumn<User,String> name_column;
    @FXML
    private TableColumn<User,String> id_column;
    @FXML
    private TableColumn<User, Date> birthday_column;
    @FXML
    private TableColumn<User,Integer> gender_column;
    @FXML
    private TableColumn<User,String> username_column;

    private ObservableList<User> teacherList;

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
    private Button add_button;

    @FXML private Label alert1;
    @FXML private Label alert2;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String d="2015-03-31";
        Date date= Date.valueOf(d);
        teacherList = FXCollections.observableArrayList(new User("ho va ten","19CQ01",date,1,1,"tlb","abc123"));
        name_column.setCellValueFactory(new PropertyValueFactory<User,String>("name"));
        id_column.setCellValueFactory(new PropertyValueFactory<User,String>("id"));
        birthday_column.setCellValueFactory(new PropertyValueFactory<User,Date>("birthday"));
        gender_column.setCellValueFactory(new PropertyValueFactory<User,Integer>("gender"));
        username_column.setCellValueFactory(new PropertyValueFactory<User,String>("username"));
        table_list.setItems(teacherList);

    }
    public void check_gender(ActionEvent e)
    {
        if(e.getSource()==male)
            female.setSelected(false);
        else
        {
            male.setSelected(false);
        }
    }
    private ArrayList<User> list_user= (ArrayList<User>) UserDAO.getAllUserTeacher();
    public void add_user(ActionEvent event){
        if(nameText.getText().isBlank() | idText.getText().isBlank() | userName.getText().isBlank() | passWord.getText().isBlank()| (male.isSelected()==false && female.isSelected()==false))
        {
            alert1.setVisible(true);
        }
        else{
            alert1.setVisible(false);
            alert2.setVisible(false);
            {
                for(User item:list_user)
                {
                    if(item.getId().equals(idText) | item.getUsername().equals(userName.getText()))
                    {
                        alert2.setVisible(true);
                        break;
                    }
                }
                User tmp=new User();
                tmp.setName(nameText.getText());
                tmp.setId(idText.getText());
                tmp.setBirthday(Date.valueOf(birthDay.getValue()));
                if(male.isSelected())
                {
                    tmp.setGender(1);
                }
                else if(female.isSelected())
                {
                    tmp.setGender(0);
                }
                tmp.setUsername(userName.getText());
                tmp.setPassword(passWord.getText());
                list_user.add(tmp);
            }
        }
    }

    public void delete_user(ActionEvent e)
    {

    }
}
