package com.example.uaspbo;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Kalkulator extends Application { //Baris ini mendeklarasikan kelas bernama Kalkulator yang mewarisi dari kelas Application

    private TextField display;    //baris 14-17 adalah konstruktor
    private String currentInput = "";
    private double operand1 = 0;
    private double operand2 = 0;
    private String operator = "";

    public void start(Stage primaryStage) {
        primaryStage.setTitle("Kalkulator");
        // Create UI elements
        display = new TextField();
        display.setEditable(false);
        display.setAlignment(Pos.CENTER_RIGHT);

        Button btn0 = new Button("0");
        Button btn1 = new Button("1");
        Button btn2 = new Button("2");
        Button btn3 = new Button("3");
        Button btn4 = new Button("4");
        Button btn5 = new Button("5");
        Button btn6 = new Button("6");
        Button btn7 = new Button("7");
        Button btn8 = new Button("8");
        Button btn9 = new Button("9");
        Button btnPoint = new Button(".");
        Button btnPlus = new Button("+");
        Button btnMinus = new Button("-");
        Button btnMultiply = new Button("x");
        Button btnDivide = new Button("/");
        Button btnEqual = new Button("=");
        Button btnClear = new Button("C");

        // pengendali button
        btn0.setOnAction(e -> handleInput("0"));
        btn1.setOnAction(e -> handleInput("1"));
        btn2.setOnAction(e -> handleInput("2"));
        btn3.setOnAction(e -> handleInput("3"));
        btn4.setOnAction(e -> handleInput("4"));
        btn5.setOnAction(e -> handleInput("5"));
        btn6.setOnAction(e -> handleInput("6"));
        btn7.setOnAction(e -> handleInput("7"));
        btn8.setOnAction(e -> handleInput("8"));
        btn9.setOnAction(e -> handleInput("9"));
        btnPoint.setOnAction(e -> handleInput("."));
        btnPlus.setOnAction(e -> handleOperator("+"));
        btnMinus.setOnAction(e -> handleOperator("-"));
        btnMultiply.setOnAction(e -> handleOperator("x"));
        btnDivide.setOnAction(e -> handleOperator("/"));
        btnEqual.setOnAction(e -> handleEqual());
        btnClear.setOnAction(e -> handleClear());

        // ukuran button
        GridPane gridPane = new GridPane();
        gridPane.setHgap(15); //horizontal
        gridPane.setVgap(15); //vertikal
        gridPane.setPadding(new Insets(10));
        gridPane.add(btn7, 0, 0);
        gridPane.add(btn8, 1, 0);
        gridPane.add(btn9, 2, 0);
        gridPane.add(btnPlus, 3, 0);
        gridPane.add(btn4, 0, 1);
        gridPane.add(btn5, 1, 1);
        gridPane.add(btn6, 2, 1);
        gridPane.add(btnMinus, 3, 1);
        gridPane.add(btn1, 0, 2);
        gridPane.add(btn2, 1, 2);
        gridPane.add(btn3, 2, 2);
        gridPane.add(btnMultiply, 3, 2);
        gridPane.add(btn0, 0, 3);
        gridPane.add(btnPoint, 1, 3);
        gridPane.add(btnEqual, 2, 3);
        gridPane.add(btnDivide, 3, 3);
        gridPane.add(btnClear, 0, 4, 2, 1);

        // Create vertical box for display and grid pane
        VBox vBox = new VBox(10);
        vBox.getChildren().addAll(display, gridPane);
        vBox.setAlignment(Pos.CENTER);

        // Create scene and set stage
        Scene scene = new Scene(vBox, 200, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void handleInput(String input) {
        currentInput += input;
        display.setText(currentInput);
    }

    private void handleOperator(String operator) {
        operand1 = Double.parseDouble(currentInput);
        currentInput = "";
        this.operator = operator;
    }

    private void handleEqual() {
        if (operator.isEmpty()) {
            return;
        }

        if (operator.equals("x")|| operator.equals("/")) {
            operand2 = Double.parseDouble(currentInput);
            double tempResult = operand1;
            operand1 = tempResult;
            currentInput = "";
            operator = "";
        } else {
            operand2 = Double.parseDouble(currentInput);
        }
        double result = 0;
        switch (operator) {
            //pengoprasian aritmatika
            case "+":
                result = operand1 + operand2;
                break;
            case "-":
                result = operand1 - operand2;
                break;
            case "x":
                result = operand1 * operand2;
                break;
            case "/":
                if (operand2 == result) {
                    display.setText("Error: Division by zero");
                    return;
                }
                result = operand1 / operand2;
                break;
        }
        display.setText(String.valueOf(result)); //membersihkan tampilan.
        currentInput = "";
        operand1 = result;
        operand2 = result;
        operator = "";
    }

    private void handleClear() { //membersihkan tampilan, masukan pengguna, operan tersimpan, dan operator
        display.setText(""); //membersihkan tampilan.
        currentInput = ""; //mengatur ulang masukan pengguna.
        operand1 = 0; //mengatur ulang operan pertama.
        operator = ""; //mengatur ulang operator.
    }

    public static void main(String[] args) {
        launch(args);
    }
}