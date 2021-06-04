package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CourseSessionController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TableView<Clazz> table_list;
    @FXML
    private TableColumn<Clazz, String> id_column;
    @FXML
    private TableColumn<Clazz, Integer> total_column;
    @FXML
    private TableColumn<Clazz, Integer> male_column;

    private ObservableList<Courseregistrationsession> sessionList= FXCollections.observableArrayList();
    private List<Courseregistrationsession> data;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
//    @FXML
//    private void refresh()
//    {
//        registerList.clear();
//        src = CourseRegisterDAO.getAllRegister();
//        registerList.addAll(src);
//        nameCol.setCellValueFactory(new PropertyValueFactory<CourseRegisterInfo,String>("semName"));
//        yearCol.setCellValueFactory(new PropertyValueFactory<CourseRegisterInfo,Integer>("semYear"));
//        startCol.setCellValueFactory(new PropertyValueFactory<CourseRegisterInfo,Date>("start"));
//        endCol.setCellValueFactory(new PropertyValueFactory<CourseRegisterInfo,Date>("end"));
//        registerTable.setItems(registerList);
//    }
}
