module com.example.advanced_tic_tac_toe_game {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.advanced_tic_tac_toe_game to javafx.fxml;
    exports com.example.advanced_tic_tac_toe_game;
}