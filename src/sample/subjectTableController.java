package sample;

import com.sun.xml.bind.XmlAccessorFactory;
import dao.SubjectDAO;
import dao.UserDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Date;
import java.sql.Ref;
import java.util.List;
import java.util.ResourceBundle;

public class subjectTableController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TableView<Subject> table_list;
    @FXML
    private TableColumn<Subject,String>  name_column;
    @FXML
    private TableColumn<Subject,String> id_column;
    @FXML
    private TableColumn<Subject,Integer> credit_column;
    @FXML
    private Tab addTab;
    @FXML
    private Button add_button;
    @FXML
    private TextField nameText;
    @FXML
    private TextField idText;
    @FXML
    private TextField creditText;
    @FXML
    private Label alert1;
    @FXML
    private Label alert2;
    @FXML
    private Label noti;
    private ObservableList<Subject> subjectList= FXCollections.observableArrayList();
    List<Subject> data;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Refresh();
    }
    @FXML
    public void Refresh() {
        if(subjectList != null)
           subjectList.clear();
        data= SubjectDAO.getAllSubject();

        for (int i = 0; i < data.size(); i++) {
           subjectList.add(data.get(i));
        }
        name_column.setCellValueFactory(new PropertyValueFactory<Subject, String>("name"));
        id_column.setCellValueFactory(new PropertyValueFactory<Subject, String>("id"));
        credit_column.setCellValueFactory(new PropertyValueFactory<Subject, Integer>("credit_column"));
        table_list.setItems(subjectList);
        System.out.println("1");
    }
    @FXML
    public void add_subject(ActionEvent e)
    {
        if((idText.getText().isEmpty() | idText.getText().isBlank() | nameText.getText().isEmpty() | creditText.getText().isEmpty()))
        {
            alert1.setVisible(true);
            alert2.setVisible(false);
        }
        else{

        }
    }
    @FXML
    public void addTabSelected() {
        nameText.clear();
        idText.clear();
        creditText.clear();
    }
}
