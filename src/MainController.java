/*
import javafx.beans.property.SimpleStringProperty;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.stage.*;

import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainController {
    //Movies
    @FXML
    private TableView<Movie> movieTable;
    @FXML
    private TableColumn<Movie, String> movIDCol;
    @FXML
    private TableColumn<Movie, String> movNmCol;
    @FXML
    private TableColumn<Movie, String> statCol;
    //Students
    @FXML
    private TableView<Student> studentTable;
    @FXML
    private TableColumn<Student, String> stuIDCol;
    @FXML
    private TableColumn<Student, String> stuNmCol;
    @FXML
    private TableColumn<Student, String> sklNmCol;
    @FXML
    private TableColumn<Student, String> sklGrdCol;
    //Members
    @FXML
    private TableView<ExternalMember> memberTable;
    @FXML
    private TableColumn<ExternalMember, String> membIDCol;
    @FXML
    private TableColumn<ExternalMember, String> membNmCol;
    @FXML
    private TableColumn<ExternalMember, String> jobCol;
    @FXML
    private TableColumn<ExternalMember, String> orgNmCol;

    public static class LoadJson {
        private static final Gson gson = new Gson();
        public static <T> List<T> loadJSONList(String path, Type type) throws Exception {
            FileReader reader = new FileReader(path);
            return gson.fromJson(reader, type);
        }
    }

        @FXML
        public void initialize() throws Exception {
        Type movieListType = new TypeToken<List<Movie>>(){}.getType();
        List<Movie> movies = LoadJson.loadJSONList("data/Movies.json", movieListType);
        if (movies != null) {
            DataRelation.movies.setAll(movies);
        }
        Type studentListType = new TypeToken<List<Student>>(){}.getType();
        List<Student> students = LoadJson.loadJSONList("data/Students.json", studentListType);
        if (students != null) {
            DataRelation.students.setAll(students);
        }
        Type memberListType = new TypeToken<List<ExternalMember>>(){}.getType();
        List<ExternalMember> members = LoadJson.loadJSONList("data/Members.json", memberListType);
        if (members != null) {
            DataRelation.external_members.setAll(members);
        }
            // Movies Table
            movIDCol.setCellValueFactory(cell -> new SimpleStringProperty(String.valueOf(cell.getValue().movieID)));
            movNmCol.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().name));
            statCol.setCellValueFactory(cell ->
                    new SimpleStringProperty(Main.isAvailable(cell.getValue().movieID, new ArrayList<>(DataRelation.movie_rentals)) ? "Available" : "Rented")
            );
            movieTable.setItems(DataRelation.movies);

            // Students Table
            stuIDCol.setCellValueFactory(cell -> new SimpleStringProperty(String.valueOf(cell.getValue().customerID)));
            stuNmCol.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().name));
            sklNmCol.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().schoolName));
            sklGrdCol.setCellValueFactory(cell -> new SimpleStringProperty(String.valueOf(cell.getValue().grade)));
            studentTable.setItems(DataRelation.students);

            // Members Table
            membIDCol.setCellValueFactory(cell -> new SimpleStringProperty(String.valueOf(cell.getValue().customerID)));
            membNmCol.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().name));
            jobCol.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().job));
            orgNmCol.setCellValueFactory(cell -> new SimpleStringProperty(cell.getValue().org_name));
            memberTable.setItems(DataRelation.external_members);
            movieTable.refresh();
            studentTable.refresh();
            memberTable.refresh();
        }


        @FXML
        private void openAddMovies() {

            Stage dialog = new Stage();
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.setTitle("Add a Movie");


            Label nameLabel = new Label("Movie Name:");
            TextField nameField = new TextField();


            VBox layout = getVBox(nameField, dialog, nameLabel);
            layout.setPadding(new Insets(10));

            Scene scene = new Scene(layout, 300, 200);
            dialog.setScene(scene);
            dialog.showAndWait();
        }

    private static VBox getVBox(TextField nameField, Stage dialog, Label nameLabel) {
        Label idLabel = new Label("Movie ID:");
        TextField idField = new TextField();

        Button addButton = getButton("Add Movie", nameField, idField, "Movie added:", dialog);

        return new VBox(10, nameLabel, nameField, idLabel, idField, addButton);
    }

    @FXML
        private void openAddStudents() {
            Stage dialog = new Stage();
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.setTitle("Add a Student");
            dialog.showAndWait();
            Label nameLabel = new Label("Student Name:");
            TextField nameField = new TextField();
            Label idLabel = new Label("Student ID:");
            TextField idField = new TextField();

        Button addButton = getButton("Add Student", nameField, idField, "Student added:", dialog);
        VBox layout = new VBox(10, nameLabel, nameField, idLabel, idField, addButton);

        layout.setPadding(new Insets(10));
            Scene scene = new Scene(layout, 300, 200);
            dialog.setScene(scene);
            dialog.showAndWait();
        }

    private static Button getButton(String Add_Student, TextField nameField, TextField idField, String x, Stage dialog) {
        Button addButton = new Button(Add_Student);
        addButton.setOnAction(e-> {
                    String studentName = nameField.getText();
                    String studentID = idField.getText();
                    System.out.println(x);
                    System.out.println("Name: " + studentName);
                    System.out.println("ID: " + studentID);
                    dialog.close();
                    nameField.clear();
                    idField.clear();
                }
        );
        return addButton;
    }

    @FXML
        private void openAddMembers() {

            Stage dialog = new Stage();
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.setTitle("Add a Member");
            dialog.showAndWait();
            Label nameLabel = new Label("Member Name:");
            TextField nameField = new TextField();
            VBox layout = getBox(nameField, dialog, nameLabel);
            layout.setPadding(new Insets(10));
            Scene scene = new Scene(layout, 300, 200);
            dialog.setScene(scene);
            dialog.showAndWait();
        }

    private static VBox getBox(TextField nameField, Stage dialog, Label nameLabel) {
        Label idLabel = new Label("Member ID:");
        TextField idField = new TextField();
        Button addButton = getButton("Add Member", nameField, idField, "Member added:", dialog);
        return new VBox(10, nameLabel, nameField, idLabel, idField, addButton);
    }
}
 */

