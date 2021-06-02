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
    private TableColumn<User, Integer> gender_column;
    @FXML
    private TableColumn<User, String> username_column;
    @FXML
    private TableColumn<User, String> pass_column;
    private ObservableList<User> studentList= FXCollections.observableArrayList();
    private List<User> data;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        refresh();
    }
    @FXML
    private void refresh()
    {
        studentList.clear();
        data = UserDAO.getAllUserStudent();
        studentList.addAll(data);
        id_column.setCellValueFactory(new PropertyValueFactory<User,String>("id"));
        name_column.setCellValueFactory(new PropertyValueFactory<User,String>("name"));
        birthday_column.setCellValueFactory(new PropertyValueFactory<User,Date>("birthday"));
        username_column.setCellValueFactory(new PropertyValueFactory<User,String>("username"));
        pass_column.setCellValueFactory(new PropertyValueFactory<User,String>("password"));
        table_list.setItems(studentList);
    }
}
