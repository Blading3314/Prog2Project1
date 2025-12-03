
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.ArrayList;

public class DataRelation {
    public static ObservableList<Movie> movies = FXCollections.observableArrayList();
    public static ObservableList<Student> students = FXCollections.observableArrayList();
    public static ObservableList<ExternalMember> external_members = FXCollections.observableArrayList();
    public static ObservableList<MovieRental> movie_rentals = FXCollections.observableArrayList();
}

