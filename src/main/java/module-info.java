module com.example.goodcalculator {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.goodcalculator to javafx.fxml;
    exports com.example.goodcalculator;
}