package sample;

import dao.CLassDAO;
import dao.UserDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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

public class ClassTableController implements Initializable {
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
    @FXML
    private TableColumn<Clazz, Integer> female_column;
    @FXML
    private TextField search_bar;
    @FXML
    private Tab add_tab;
    @FXML
    private TextField idText;
    @FXML
    private TextField totalText;
    @FXML
    private TextField maleText;
    @FXML
    private TextField femaleText;

    private ObservableList<Clazz> classList= FXCollections.observableArrayList();
    List<Clazz> data;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Refresh();
    }
    public void Refresh() {
        if(classList != null)
            classList.clear();
        data= CLassDAO.getAllClass();

        for (int i = 0; i < data.size(); i++) {
            classList.add(data.get(i));
        }

        id_column.setCellValueFactory(new PropertyValueFactory<Clazz, String>("id"));
        total_column.setCellValueFactory(new PropertyValueFactory<Clazz, Integer>("total"));
        male_column.setCellValueFactory(new PropertyValueFactory<Clazz, Integer>("male"));
       female_column.setCellValueFactory(new PropertyValueFactory<Clazz, Integer>("female"));
        table_list.setItems(classList);
    }
    public void delete_CLass(ActionEvent e) {
        data= CLassDAO.getAllClass();
        Clazz t = table_list.getSelectionModel().getSelectedItem();
        if (t == null)
            return;
        classList.remove(t);
        data.remove(t);
        CLassDAO.delete_Class(t);
        Refresh();
        System.out.println("1");
    }
    public void addTabSelected() {
        idText.clear();
        totalText.clear();
        maleText.clear();
        femaleText.clear();
    }

    public void showClassInfo(ActionEvent e) throws IOException {
        Clazz t = table_list.getSelectionModel().getSelectedItem();
        if (t == null)
            return;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("classInfoTable.fxml"));
        loader.load();
        ClassInfoTableController controller = loader.getController();
        Scene scene = new Scene(loader.getRoot());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
}
