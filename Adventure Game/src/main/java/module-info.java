module com.example.adventuregame {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.rmi;


    opens com.example.adventuregame to javafx.fxml;
    exports com.example.adventuregame;
}