package sample;

import com.sun.javafx.charts.Legend;
import com.sun.xml.bind.XmlAccessorFactory;
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
import javafx.util.converter.NumberStringConverter;

import javafx.scene.input.KeyEvent;

import java.io.IOException;
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
    private TableColumn<Subject, String> name_column;
    @FXML
    private TableColumn<Subject, String> id_column;
    @FXML
    private TableColumn<Subject, Integer> credit_column;
    @FXML
    private Tab addTab;
    @FXML
    private Button add_button;
    @FXML
    private TextField nameText;
    @FXML
    private TextField idText;

    @FXML
    private ComboBox<Integer> credit_box;
    @FXML
    private TextField search_bar;

    @FXML
    private Label alert1;
    @FXML
    private Label alert2;
    @FXML
    private Label noti;

    private ObservableList<Subject> subjectList = FXCollections.observableArrayList();
    List<Subject> data;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        credit_box.getItems().addAll(1, 2, 3, 4);
        Refresh();
    }

    @FXML
    public void Refresh() {
        if (subjectList != null)
            subjectList.clear();
        data = SubjectDAO.getAllSubject();

        for (int i = 0; i < data.size(); i++) {
            subjectList.add(data.get(i));
        }
        name_column.setCellValueFactory(new PropertyValueFactory<Subject, String>("name"));
        id_column.setCellValueFactory(new PropertyValueFactory<Subject, String>("id"));
        credit_column.setCellValueFactory(new PropertyValueFactory<Subject, Integer>("credits"));
        table_list.setItems(subjectList);
        System.out.println("1");
    }


    @FXML
    public void add_subject(ActionEvent e) {
        data = SubjectDAO.getAllSubject();
        alert1.setVisible(false);
        alert2.setVisible(false);
        noti.setVisible(false);
        if ((idText.getText().isEmpty()) | idText.getText().isBlank() | nameText.getText().isEmpty()) {
            alert1.setVisible(true);
            alert2.setVisible(false);
        } else {
            alert1.setVisible(false);
            alert2.setVisible(false);
            for (Subject item : data) {
                if (idText.getText().equals(item.getId()) | nameText.getText().equals(item.getName()) | credit_box.getValue() == null) {
                    alert2.setVisible(true);
                } else {
                    Subject newSub = new Subject();
                    newSub.setId(idText.getText());
                    newSub.setName(nameText.getText());
                    newSub.setCredits(credit_box.getValue());
                    subjectList.add(newSub);
                    data.add(newSub);
                    SubjectDAO.add_Subject(newSub);
                }
            }
        }
    }

    @FXML
    public void addTabSelected() {
        nameText.clear();
        idText.clear();
        credit_box.getSelectionModel().clearSelection();
    }

    @FXML
    public void delete_sub(ActionEvent e) {
        data = SubjectDAO.getAllSubject();
        Subject t = table_list.getSelectionModel().getSelectedItem();
        if (t == null)
            return;
        subjectList.remove(t);
        data.remove(t);
        SubjectDAO.delete_sub(t);
        Refresh();
        System.out.println("1");
    }

    @FXML
    public void UpdateInfo(ActionEvent e) throws IOException {
        Subject t = table_list.getSelectionModel().getSelectedItem();
        if (t == null)
            return;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("updateSubjectInfo.fxml"));
        loader.load();
        updateSubjectController controller = loader.getController();
        controller.setUpdate(true);
        controller.setTextField(t);
        Scene scene = new Scene(loader.getRoot());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();


    }
    @FXML
    public void searchSubject(ActionEvent e) {
        String res = search_bar.getText();
        if (res.isEmpty() | res.isBlank()) {
        } else {
            subjectList.clear();
            for (Subject item : data) {
                if (item.getId().contains(res)) {
                    subjectList.add(item);
                }
            }
            name_column.setCellValueFactory(new PropertyValueFactory<Subject, String>("name"));
            id_column.setCellValueFactory(new PropertyValueFactory<Subject, String>("id"));
            credit_column.setCellValueFactory(new PropertyValueFactory<Subject, Integer>("credits"));
            table_list.setItems(subjectList);
            table_list.setItems(subjectList);

        }
    }
    @FXML
    public void logOut(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("logIn.fxml"));
        root = loader.load();
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    @FXML
    public void back(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("TeacherFeature.fxml"));
        root = loader.load();
        stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}
