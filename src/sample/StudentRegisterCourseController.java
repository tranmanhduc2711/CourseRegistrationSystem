package sample;

import dao.CourseDAO;
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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class StudentRegisterCourseController implements Initializable {
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
    @FXML
    private TableColumn<CourseinfoPOJO, CheckBox> selectCol;
    @FXML
    private Label alert;

    private User currentStudent;

    private List<CoursePOJO> allCourse;
    ObservableList<CourseinfoPOJO> courseList= FXCollections.observableArrayList();
    List<CourseinfoPOJO> data;
    List<CourseinfoPOJO> isRegisted;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//
        refresh();
    }
    @FXML
    private void refresh()
    {
        courseList.clear();
        System.out.println(Main.currentSemester.getId());
        data = CourseDAO.getAllCourseInSem(Main.currentSemester.getId());
        if(isRegisted != null)
        {
            for (CourseinfoPOJO i : data) {
                for (CourseinfoPOJO j : isRegisted)
                    if (i.getCourseId().equals(j.getCourseId())) {
                        i.getSelect().setSelected(true);
                        i.getSelect().setDisable(true);
                    }
                courseList.add(i);
            }
        }
        courseList.addAll(data);
        subID_column.setCellValueFactory(new PropertyValueFactory<CourseinfoPOJO,String>("subject_id"));
        subName_column.setCellValueFactory(new PropertyValueFactory<CourseinfoPOJO,String>("subject_name"));
        credit_column.setCellValueFactory(new PropertyValueFactory<CourseinfoPOJO,Integer>("credits"));
        teacher_column.setCellValueFactory(new PropertyValueFactory<CourseinfoPOJO,String>("teacher"));
        room_column.setCellValueFactory(new PropertyValueFactory<CourseinfoPOJO,String>("room"));
        dayOfWeek_column.setCellValueFactory(new PropertyValueFactory<CourseinfoPOJO,String>("dayOfWeek"));
        session_column.setCellValueFactory(new PropertyValueFactory<CourseinfoPOJO,String>("session_id"));
        max_column.setCellValueFactory(new PropertyValueFactory<CourseinfoPOJO,Integer>("max"));
        selectCol.setCellValueFactory(new  PropertyValueFactory<CourseinfoPOJO,CheckBox>("select"));
        table_list.setItems(courseList);
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
    public void setUser(User user)
    {
        currentStudent=user;
        isRegisted = CourseDAO.getAllCourseOfStudent(Main.currentSemester.getId(),currentStudent.getId());
        allCourse = CourseDAO.getAllInSem(Main.currentSemester.getId());

    }

    @FXML
    public void register(ActionEvent e)
    {
        List<CourseinfoPOJO> selected = getSelected();
//        for(int i =0;i<selected.size()-1;i++){
//            //Kiểm tra các học phần đc chọn hợp lệ
//            for(int j = i+1;j<selected.size();j++)
//            {
//                if(selected.get(i).getSubject_id().compareTo(selected.get(j).getSubject_id())==0 || (selected.get(i).getDayOfWeek().compareTo(selected.get(j).getDayOfWeek())==0 && selected.get(i).getSession_id().compareTo(selected.get(j).getSession_id())==0))
//                {
//                    Alert alert = new Alert(Alert.AlertType.ERROR);
//                    alert.setHeaderText(null);
//                    alert.setContentText("Học phần không hợp lệ!");
//                    alert.showAndWait();
//                    return;
//                }
//            }
//            //Kiểm tra học phần đã đk và hp đc chọn hợp lệ
//            for(CourseinfoPOJO j : isRegisted)
//            {
//                if(selected.get(i).getSubject_id().compareTo(j.getSubject_id())==0 || (selected.get(i).getDayOfWeek().compareTo(j.getDayOfWeek())==0 && selected.get(i).getSession_id().compareTo(j.getSession_id())==0))
//                {
//                    Alert alert = new Alert(Alert.AlertType.ERROR);
//                    alert.setHeaderText(null);
//                    alert.setContentText("Học phần không hợp lệ!");
//                    alert.showAndWait();
//                    return;
//                }
//            }
//        }
        for(CourseinfoPOJO i : selected)
        {
            isRegisted.add(i);
            StudentInCourse attend = new StudentInCourse();
            attend.setStudent_id(currentStudent.getId());
            attend.setCourse_id(i.getCourseId());
            CourseDAO.addAttend(attend);
        }

    }

    @FXML
    public void back(ActionEvent e) throws IOException {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("StudentFeatureTable.fxml"));
        root=loader.load();
        stage=(Stage) ((Node)e.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}
