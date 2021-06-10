package sample;

import dao.CourseDAO;
import dao.StudentDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class StudentRegistedListController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

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

    private User currentStudent;
    private List<CoursePOJO> allCourse;
    private List<StudentInCourse> allAttend;
    ObservableList<CourseinfoPOJO> courseList= FXCollections.observableArrayList();
    List<CourseinfoPOJO> isRegisted;

    @Override
    public  void initialize(URL url, ResourceBundle resourceBundle) {
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
    private void refresh()
    {
        courseList.clear();
        courseList.addAll(isRegisted);
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
    public void setUserCurrent(User user)
    {
        currentStudent=user;
        isRegisted = CourseDAO.getAllCourseOfStudent(Main.currentSemester.getId(),currentStudent.getId());
        allCourse = CourseDAO.getAllInSem(Main.currentSemester.getId());
        allAttend = StudentDAO.getAllCourseOfStudent(user.getId());
    }

    private List<CourseinfoPOJO> getSelected()
    {
        List<CourseinfoPOJO> selectedCourse = new ArrayList<CourseinfoPOJO>();
        for(CourseinfoPOJO i : courseList) {
            if (i.getSelect().isSelected() && !i.getSelect().isDisable())
                selectedCourse.add(i);
        }
        return selectedCourse;
    }
    @FXML
    private void delete(ActionEvent e)
    {
        List<CourseinfoPOJO> selected = getSelected();

            for(CourseinfoPOJO i : selected)
            {
                for(StudentInCourse j : allAttend)
                {
                    if(i.getCourseId().equals(j.getId()))
                    {
                        CourseDAO.deleteAttend(j);
                    }
                }
                isRegisted.remove(i);
            }

        refresh();
    }
}
