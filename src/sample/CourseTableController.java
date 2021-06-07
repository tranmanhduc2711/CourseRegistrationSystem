package sample;

import dao.CourseDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
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
    @FXML
    private Label alert;
    @FXML
    private Label noti;

    private ObservableList<CourseinfoPOJO> courseList= FXCollections.observableArrayList();
    private List<CourseinfoPOJO> data;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       refresh();
    }

    @FXML
    private void refresh()
    {
        courseList.clear();
        data = CourseDAO.getAllCourseInSem(Main.currentSemester.getId());
        for(CourseinfoPOJO i : data)
            courseList.add(i);
        subID_column.setCellValueFactory(new PropertyValueFactory<CourseinfoPOJO,String>("subject_id"));
        subName_column.setCellValueFactory(new PropertyValueFactory<CourseinfoPOJO,String>("subject_name"));
        credit_column.setCellValueFactory(new PropertyValueFactory<CourseinfoPOJO,Integer>("credits"));
        teacher_column.setCellValueFactory(new PropertyValueFactory<CourseinfoPOJO,String>("teacher"));
        room_column.setCellValueFactory(new PropertyValueFactory<CourseinfoPOJO,String>("room"));
        dayOfWeek_column.setCellValueFactory(new PropertyValueFactory<CourseinfoPOJO,String>("dayOfWeek"));
        session_column.setCellValueFactory(new PropertyValueFactory<CourseinfoPOJO,String>("session_id"));
        max_column.setCellValueFactory(new PropertyValueFactory<CourseinfoPOJO,Integer>("max"));
        table_list.setItems(courseList);
    }

    @FXML
    private void add(ActionEvent e)
    {
        alert.setVisible(false);
        noti.setVisible(false);
        if(sub_idText.getText() == null || teacherText.getText()==null || roomText.getText()==null || dowText.getText()==null || maxText.getText()==null || sessionText.getText()==null)
        {
           alert.setVisible(true);
        }
        else
        {
            CoursePOJO course = new CoursePOJO();
            course.setSubjectId(sub_idText.getText());
            course.setTeacher(teacherText.getText());
            course.setRoom(roomText.getText());
            course.setMax(Integer.parseInt(maxText.getText()));
            course.setDayOfWeek(dowText.getText());
            course.setSessionId(sessionText.getText());
            course.setSemester(Main.currentSemester.getId());
            noti.setVisible(true);
            CourseDAO.addCourse(course);
        }
    }
}
