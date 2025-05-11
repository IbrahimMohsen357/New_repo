package com.example.test;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;

public class HelloController {

    @FXML
    Button btn1;
    public void btn (ActionEvent event) {
        String text = btn1.getText();
        System.out.println(text);
    }


    public void print(MouseEvent event) {
        System.out.println("Hello World");

    }

}