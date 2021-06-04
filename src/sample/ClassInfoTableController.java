package sample;

import dao.CLassDAO;
import dao.StudentDAO;
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

import javax.security.auth.callback.Callback;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;

public class ClassInfoTableController implements Initializable {
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
    private TableColumn<User, String> gender_column;
    @FXML
    private TableColumn<User, String> username_column;
    @FXML
    private TableColumn<User, String> pass_column;
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
    private Label alert2;
    @FXML
    private Label noti;
    @FXML
    private TextField search_bar;
    @FXML
    private Button search_but;
    @FXML
    private Button update_but;

    private ObservableList<User> studentList= FXCollections.observableArrayList();
    private List<User> data;
    private Clazz curClass;
    private List<Classinfo> infoList ;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id_column.setCellValueFactory(new PropertyValueFactory<User,String>("id"));
        name_column.setCellValueFactory(new PropertyValueFactory<User,String>("name"));
        birthday_column.setCellValueFactory(new PropertyValueFactory<User,Date>("birthday"));
        username_column.setCellValueFactory(new PropertyValueFactory<User,String>("username"));
        pass_column.setCellValueFactory(new PropertyValueFactory<User,String>("password"));
        table_list.setItems(studentList);
    }
    @FXML


    public void setUsernameText(Clazz clazz)
    {
        curClass=clazz;
        refresh();
    }
    @FXML
    private void refresh()
    {
        studentList.clear();
        data = StudentDAO.getAllStudentInCLass(curClass.getId());
        studentList.addAll(data);
        id_column.setCellValueFactory(new PropertyValueFactory<User,String>("id"));
        name_column.setCellValueFactory(new PropertyValueFactory<User,String>("name"));
        birthday_column.setCellValueFactory(new PropertyValueFactory<User,Date>("birthday"));
        username_column.setCellValueFactory(new PropertyValueFactory<User,String>("username"));
        pass_column.setCellValueFactory(new PropertyValueFactory<User,String>("password"));
        table_list.setItems(studentList);
    }
    @FXML
    private void delete(ActionEvent e)
    {
        infoList=CLassDAO.getAllInfoClass();
        data= UserDAO.getAllUserStudent();
        User t = table_list.getSelectionModel().getSelectedItem();
        if (t == null)
            return;
       Classinfo info = new Classinfo();
        for(Classinfo i : infoList)
            if(i.getClassId().compareTo(curClass.getId())==0 && i.getUserId().compareTo(t.getId())==0)
                info=i;
        curClass.setTotal(curClass.getTotal()-1);
        if(t.getGender()==0)
            curClass.setFemale(curClass.getFemale()-1);
        else
            curClass.setMale(curClass.getMale()-1);

        CLassDAO.updateClass(curClass);
        CLassDAO.deleteStudentInClass(info);
        UserDAO.delete_teacher(t);
        refresh();
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
    @FXML
    public void add_student(ActionEvent e)
    {
        data= UserDAO.getAllUserStudent();
        if (nameText.getText().isBlank() | idText.getText().isBlank() | userName.getText().isBlank() | passWord.getText().isBlank() | (male.isSelected() == false && female.isSelected() == false)) {
            alert1.setVisible(true);
            alert2.setVisible(false);
        } else {
            alert1.setVisible(false);
            alert2.setVisible(false);
            {
                for (User item : data) {
                    if (item.getId().equals(idText) | item.getUsername().equals(userName.getText())) {
                        alert2.setVisible(true);
                        break;
                    }
                }
                User tmp = new User();
                Classinfo classinfo= new Classinfo();
                tmp.setId(idText.getText());
                classinfo.setClassId(curClass.getId());
                classinfo.setUserId(idText.getText());
                tmp.setName(nameText.getText());
                tmp.setId(idText.getText());
                tmp.setBirthday(Date.valueOf(birthDay.getValue()));
                if (male.isSelected()) {
                    tmp.setGender(1);
                    curClass.setMale(curClass.getMale()+1);
                } else if (female.isSelected()) {
                    tmp.setGender(0);
                    curClass.setFemale(curClass.getFemale()+1);
                }
                tmp.setRole(1);
                tmp.setUsername(userName.getText());
                tmp.setPassword(passWord.getText());
                studentList.add(tmp);
                data.add(tmp);
                UserDAO.add_Teacher(tmp);


                curClass.setTotal(curClass.getTotal()+1);
                CLassDAO.updateClass(curClass);
                CLassDAO.addStudentInClass(classinfo);
            }
        }
    }
    public void check_gender(ActionEvent e) {
        if (e.getSource() == male)
            female.setSelected(false);
        else {
            male.setSelected(false);
        }
    }
    @FXML
    public void back(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("TeacherFeature.fxml"));
        root = loader.load();
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    @FXML
    public void logOut(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("logIn.fxml"));
        root = loader.load();
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    @FXML
    public void search_student(ActionEvent e)
    {
       data = StudentDAO.getAllStudentInCLass(curClass.getId());
        String res = search_bar.getText();
        if (res.isEmpty() | res.isBlank()) {
        } else {
            studentList.clear();
            for (User item : data) {
                if (item.getId().contains(res)) {
                    studentList.add(item);
                }
            }
            id_column.setCellValueFactory(new PropertyValueFactory<User,String>("id"));
            name_column.setCellValueFactory(new PropertyValueFactory<User,String>("name"));
            birthday_column.setCellValueFactory(new PropertyValueFactory<User,Date>("birthday"));
            username_column.setCellValueFactory(new PropertyValueFactory<User,String>("username"));
            pass_column.setCellValueFactory(new PropertyValueFactory<User,String>("password"));
            table_list.setItems(studentList);
    }
    }
    @FXML
    private void update(ActionEvent e) throws IOException {
        User t = table_list.getSelectionModel().getSelectedItem();
        if (t == null)
            return;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("updateStudentTable.fxml"));
        loader.load();
        updateStudentTableController controller = loader.getController();
        controller.setUpdate(true);
        controller.setTextField(t);
        Scene scene = new Scene(loader.getRoot());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
}
