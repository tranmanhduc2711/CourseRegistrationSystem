package sample;

import dao.SemesterDAO;
import dao.SubjectDAO;
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

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;

public class SemesterTableController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TableView<Semester> table_list;
    @FXML
    private TableColumn<Semester, String> name_column;
    @FXML
    private TableColumn<Semester, Integer> year_column;
    @FXML
    private TableColumn<Semester, Date> start_column;
    @FXML
    private TableColumn<Semester, Date> end_column;
    @FXML
    private Label alert;
    @FXML
    private TextField nameText;
    @FXML
    private TextField yearText;
    @FXML
    private DatePicker start_date;
    @FXML
    private DatePicker end_date;
    @FXML
    private Label alert1;
    @FXML
    private Label noti;
    private ObservableList<Semester> semesterList = FXCollections.observableArrayList();
    List<Semester> data;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        refresh();
    }
    @FXML
    public void refresh() {
        if (semesterList != null)
            semesterList.clear();
        data = SemesterDAO.getAllSemester();

        for (int i = 0; i < data.size(); i++) {
            semesterList.add(data.get(i));
        }
        name_column.setCellValueFactory(new PropertyValueFactory<Semester, String>("name"));
        year_column.setCellValueFactory(new PropertyValueFactory<Semester, Integer>("year"));
        start_column.setCellValueFactory(new PropertyValueFactory<Semester,Date>("start"));
        end_column.setCellValueFactory(new PropertyValueFactory<Semester, Date>("end"));
        table_list.setItems(semesterList);
    }

    @FXML
    public void add_semester(ActionEvent e)
    {
        data=SemesterDAO.getAllSemester();
        alert.setVisible(false);
        alert1.setVisible(false);
        noti.setVisible(false);
        if(nameText.getText().isEmpty() | yearText.getText().isEmpty() | start_date.getValue()==null)
        {
            alert1.setVisible(true);
        }
        else{
            alert1.setVisible(false);
            alert.setVisible(false);
            noti.setVisible(false);
            for(Semester item:data)
            {
                if(item.getName().equals(nameText.getText()) && item.getYear().equals(yearText.getText()))
                {
                    alert.setVisible(true);
                    return;
                }
            }
            Semester newSem=new Semester();
            newSem.setName(nameText.getText());
            newSem.setYear(Integer.parseInt(yearText.getText()));
            newSem.setStart(Date.valueOf(start_date.getValue()));
            newSem.setEnd(Date.valueOf(end_date.getValue()));
            semesterList.add(newSem);
            data.add(newSem);
            SemesterDAO.add_Semester(newSem);
        }
    }
    @FXML
    public void addTabSelected()
    {
        nameText.clear();
        yearText.clear();
        start_date.getEditor().clear();
        end_date.getEditor().clear();
    }
    @FXML
    public void delete_sem()
    {
        data= SemesterDAO.getAllSemester();
       Semester t = table_list.getSelectionModel().getSelectedItem();
        if (t == null)
            return;
        semesterList.remove(t);
        data.remove(t);
        SemesterDAO.delete_sem(t);
        refresh();
    }
    @FXML
    public void setSemester() throws IOException
    {
        Semester t = table_list.getSelectionModel().getSelectedItem();;
        Main.currentSemester=t;
        System.out.print(Main.currentSemester.getId());
        SemesterDAO.writeFile(Main.currentSemester.getId());

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
