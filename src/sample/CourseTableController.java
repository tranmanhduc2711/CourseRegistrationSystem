package sample;

import dao.CourseDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CourseTableController implements Initializable {
    @FXML
    private TableView<CourseinfoPOJO> table_list;
    @FXML
    private TableColumn<CourseinfoPOJO, String> subID_column;
    @FXML
    private TableColumn<CourseinfoPOJO, String> subName_column;
    @FXML
    private TableColumn<CourseinfoPOJO, Integer> credit_column;
    @FXML
    private TableColumn<CourseinfoPOJO, String> teacher_column;
    @FXML
    private TableColumn<CourseinfoPOJO, String> room_column;
    @FXML
    private TableColumn<CourseinfoPOJO, String> dayOfWeek_column;
    @FXML
    private TableColumn<CourseinfoPOJO, String> session_column;
    @FXML
    private TableColumn<CourseinfoPOJO, Integer> max_column;
    @FXML
    private TextField sub_idText;
    @FXML
    private TextField subName_Text;
    @FXML
    private TextField creditText;
    @FXML
    private TextField teacherText;
    @FXML
    private TextField roomText;
    @FXML
    private TextField dowText;
    @FXML
    private TextField sessionText;
    @FXML
    private TextField maxText;

    private ObservableList<CourseinfoPOJO> courseList= FXCollections.observableArrayList();
    private List<CourseinfoPOJO> data;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        subID_column.setCellValueFactory(new PropertyValueFactory<CourseinfoPOJO,String>("subjectId"));
        subName_column.setCellValueFactory(new PropertyValueFactory<CourseinfoPOJO,String>("subjectName"));
        credit_column.setCellValueFactory(new PropertyValueFactory<CourseinfoPOJO,Integer>("credits"));
        teacher_column.setCellValueFactory(new PropertyValueFactory<CourseinfoPOJO,String>("teacher"));
        room_column.setCellValueFactory(new PropertyValueFactory<CourseinfoPOJO,String>("room"));
        dayOfWeek_column.setCellValueFactory(new PropertyValueFactory<CourseinfoPOJO,String>("dayOfWeek"));
        session_column.setCellValueFactory(new PropertyValueFactory<CourseinfoPOJO,String>("session"));
        max_column.setCellValueFactory(new PropertyValueFactory<CourseinfoPOJO,Integer>("maxSlot"));
        table_list.setItems(courseList);
    }

    @FXML
    private void refresh()
    {
        courseList.clear();
        data = CourseDAO.getAllCourseInSem(Main.currentSemester.getId());
        for(CourseinfoPOJO i : data)
            courseList.add(i);
        subID_column.setCellValueFactory(new PropertyValueFactory<CourseinfoPOJO,String>("subjectId"));
        subName_column.setCellValueFactory(new PropertyValueFactory<CourseinfoPOJO,String>("subjectName"));
        credit_column.setCellValueFactory(new PropertyValueFactory<CourseinfoPOJO,Integer>("credits"));
        teacher_column.setCellValueFactory(new PropertyValueFactory<CourseinfoPOJO,String>("teacher"));
        room_column.setCellValueFactory(new PropertyValueFactory<CourseinfoPOJO,String>("room"));
        dayOfWeek_column.setCellValueFactory(new PropertyValueFactory<CourseinfoPOJO,String>("dayOfWeek"));
        session_column.setCellValueFactory(new PropertyValueFactory<CourseinfoPOJO,String>("session"));
        max_column.setCellValueFactory(new PropertyValueFactory<CourseinfoPOJO,Integer>("maxSlot"));
        table_list.setItems(courseList);
    }
}
