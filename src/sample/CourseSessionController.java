package sample;

import dao.CourseRegisDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class CourseSessionController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;


    @FXML
    private TableView<CourseRegisterInfo> table_list;
    @FXML
    private TableColumn<CourseRegisterInfo, String> semester_column;
    @FXML
    private TableColumn<CourseRegisterInfo, Date> start_column;
    @FXML
    private TableColumn<CourseRegisterInfo, Date> end_column;
    @FXML
    private DatePicker startText;
    @FXML
    private DatePicker endText;
    @FXML
    private Label alert;
    @FXML
    private Label noti;

    private ObservableList<CourseRegisterInfo> registerList= FXCollections.observableArrayList();
    private List<CourseRegisterInfo> data;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        refresh();
    }
    @FXML
    private void refresh()
    {
        registerList.clear();
        data = CourseRegisDAO.getAllRegister();
        registerList.addAll(data);
        semester_column.setCellValueFactory(new PropertyValueFactory<CourseRegisterInfo,String>("semName"));
        start_column.setCellValueFactory(new PropertyValueFactory<CourseRegisterInfo, Date>("start"));
        end_column.setCellValueFactory(new PropertyValueFactory<CourseRegisterInfo,Date>("end"));
        table_list.setItems(registerList);
    }
    @FXML
    private void delete(ActionEvent e)
    {
        CourseRegisterInfo info = table_list.getSelectionModel().getSelectedItem();
        if(info == null)
            return;
        Courseregistrationsession register = new Courseregistrationsession();
        register.setId(info.getId());
        register.setSemester_id(Main.currentSemester.getId());
        register.setStart(info.getStart());
        register.setEnd(info.getEnd());
        CourseRegisDAO.deleteCourseRegister(register);
        refresh();
    }

    @FXML
    private void add(ActionEvent e) {
        alert.setVisible(false);
        noti.setVisible(false);
        data=CourseRegisDAO.getAllRegister();
        LocalDate start = startText.getValue();
        LocalDate end = endText.getValue();
        if(start==null || end==null)
        {
            alert.setVisible(true);
        }
        else{
            Courseregistrationsession courseRegister = new Courseregistrationsession();
            courseRegister.setSemester_id(Main.currentSemester.getId());
            courseRegister.setStart(Date.valueOf(start));
            courseRegister.setEnd(Date.valueOf(end));

            CourseRegisDAO.addCourseRegister(courseRegister);
            noti.setVisible(true);
        }
    }
    @FXML
    public void back(ActionEvent e) throws IOException
    {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("TeacherFeature.fxml"));
        root=loader.load();
        stage=(Stage) ((Node)e.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    @FXML
    public void logOut(ActionEvent e) throws  IOException
    {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("logIn.fxml"));
        root=loader.load();
        stage=(Stage) ((Node)e.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

}
