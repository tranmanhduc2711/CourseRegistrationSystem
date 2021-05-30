package sample;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.sql.Date;

public class SemesterTableController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TableView<Semester> semester_list;
    @FXML
    private TableColumn<Semester, String> name_column;
    @FXML
    private TableColumn<Semester, Integer> year_column;
    @FXML
    private TableColumn<Semester, Date> start_column;
    @FXML
    private TableColumn<Semester, Date> end_column;

}
