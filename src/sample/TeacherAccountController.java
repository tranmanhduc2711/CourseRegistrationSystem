package sample;

import dao.UserDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;

public class TeacherAccountController  implements Initializable  {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TableView<User> table_list;
    @FXML
    private TableColumn<User, String> name_column;
    @FXML
    private TableColumn<User, String> id_column;
    @FXML
    private TableColumn<User, Date> birthday_column;
    @FXML
    private TableColumn<User, Integer> gender_column;
    @FXML
    private TableColumn<User, String> username_column;

    private ObservableList<User> teacherList= FXCollections.observableArrayList();

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

    @FXML
    private Label alert1;
    @FXML
    private Label alert2;

    @FXML
    private TextField search_bar;
    @FXML
    private Button search_but;
    @FXML
    private Button refresh_but;
    @FXML
    private Button del_but;
    @FXML
    private Button update_but;
    @FXML
    private Tab add_tab;
    @FXML
    private Tab update_tab;
    @FXML
    private Button logout_but;
    private List<User> list_user ;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Refresh();
    }

    public void check_gender(ActionEvent e) {
        if (e.getSource() == male)
            female.setSelected(false);
        else {
            male.setSelected(false);
        }
    }

    public void add_user(ActionEvent event) {
        list_user= UserDAO.getAllUserTeacher();
        if (nameText.getText().isBlank() | idText.getText().isBlank() | userName.getText().isBlank() | passWord.getText().isBlank() | (male.isSelected() == false && female.isSelected() == false)) {
            alert1.setVisible(true);
            alert2.setVisible(false);
        } else {
            alert1.setVisible(false);
            alert2.setVisible(false);
            {
                for (User item : list_user) {
                    if (item.getId().equals(idText) | item.getUsername().equals(userName.getText())) {
                        alert2.setVisible(true);
                        break;
                    }
                }
                User tmp = new User();
                tmp.setName(nameText.getText());
                tmp.setId(idText.getText());
                tmp.setBirthday(Date.valueOf(birthDay.getValue()));
                if (male.isSelected()) {
                    tmp.setGender(1);
                } else if (female.isSelected()) {
                    tmp.setGender(0);
                }
                tmp.setRole(0);
                tmp.setUsername(userName.getText());
                tmp.setPassword(passWord.getText());
                teacherList.add(tmp);
                list_user.add(tmp);
                UserDAO.add_Teacher(tmp);
            }
        }
    }

    public void delete_user(ActionEvent e) {
        list_user= UserDAO.getAllUserTeacher();
        User t = table_list.getSelectionModel().getSelectedItem();
        if (t == null)
            return;
        teacherList.remove(t);
        list_user.remove(t);
        UserDAO.delete_teacher(t);
        Refresh();
        System.out.println("1");
    }

    public void Refresh() {
        if(teacherList != null)
            teacherList.clear();
        list_user= UserDAO.getAllUserTeacher();

        for (int i = 0; i < list_user.size(); i++) {
            teacherList.add(list_user.get(i));
        }
        name_column.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
        id_column.setCellValueFactory(new PropertyValueFactory<User, String>("id"));
        birthday_column.setCellValueFactory(new PropertyValueFactory<User, Date>("birthday"));
        gender_column.setCellValueFactory(new PropertyValueFactory<User, Integer>("gender"));
        username_column.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
        table_list.setItems(teacherList);
        System.out.println("1");
    }

    public void addTabSelected() {
        nameText.clear();
        idText.clear();
        male.setSelected(false);
        female.setSelected(false);
        birthDay.getEditor().clear();
        userName.clear();
        passWord.clear();
    }

    public void UpdateInfo(ActionEvent e) throws IOException {
        User t = table_list.getSelectionModel().getSelectedItem();
        if (t == null)
            return;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("updateTeacherInfo.fxml"));
        loader.load();
        updateTeacherController controller = loader.getController();
        controller.setUpdate(true);
        controller.setTextField(t);
        Scene scene = new Scene(loader.getRoot());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();


    }



    @FXML
    public void searchTeacher(ActionEvent e) {
        String res = search_bar.getText();
        if (res.isEmpty() | res.isBlank()) {
        } else {
            teacherList.clear();
            for (User item : list_user) {
                if (item.getId().contains(res)) {
                    teacherList.add(item);
                }
            }
            name_column.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
            id_column.setCellValueFactory(new PropertyValueFactory<User, String>("id"));
            birthday_column.setCellValueFactory(new PropertyValueFactory<User, Date>("birthday"));
            gender_column.setCellValueFactory(new PropertyValueFactory<User, Integer>("gender"));
            username_column.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
            table_list.setItems(teacherList);
            System.out.println("1");
        }
    }
    @FXML
    public void setLogout_but(ActionEvent e) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("TeacherFeature.fxml"));
        root=loader.load();
        stage=(Stage) ((Node)e.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}
