package sample;

import dao.UserDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;

public class CourseStudentInfoController implements Initializable {
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
    private TableColumn<User, Date> subjectId_column;
    @FXML
    private TableColumn<User, Integer> subjectName_column;
    @FXML
    private TableColumn<User, String> teacher_column;
    @FXML
    private TableColumn<User, String> time_column;


    private ObservableList<User> studentList= FXCollections.observableArrayList();
    private List<User> data;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void refresh()
    {
        data= ;
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
    }
}
