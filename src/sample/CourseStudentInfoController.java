package sample;

import dao.StudentDAO;
import dao.UserDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
    private TableColumn<User, String> user_column;
    @FXML
    private TextField search_bar;

    private ObservableList<User> studentList= FXCollections.observableArrayList();
    private List<User> data;
    public static CourseinfoPOJO currentCourse;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        refresh();
    }


    public  void getCourse(CourseinfoPOJO id)
    {
        currentCourse=id;
        refresh();
    }
    @FXML
    public void refresh()
    {
        studentList.clear();
        data = StudentDAO.getAllStudentInCourse(currentCourse.getId());
        studentList.addAll(data);

        name_column.setCellValueFactory(new PropertyValueFactory<User, String>("name"));
        id_column.setCellValueFactory(new PropertyValueFactory<User, String>("id"));
        user_column.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
        table_list.setItems(studentList);
    }
    @FXML
    private void search(ActionEvent e)
    {
        String res = search_bar.getText();
        if(res.isEmpty()){ }
        else{
            studentList.clear();
            data = StudentDAO.getAllStudentInCourseById(Main.currentSemester.getId(),res);
            studentList.addAll(data);
            id_column.setCellValueFactory(new PropertyValueFactory<User,String>("id"));
            name_column.setCellValueFactory(new PropertyValueFactory<User,String>("name"));
            table_list.setItems(studentList);
        }
    }
}
