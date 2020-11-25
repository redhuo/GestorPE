/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author wonmi
 */
public class RegistroReqCurso {
  String escuela;
  String curso;
  String requisito;
  String correquisito;
  
  public void start(Stage primaryStage) {
    primaryStage.setTitle("Registro de Requisitos");        
    primaryStage.show();
    
    GridPane grid = new GridPane();
    grid.setAlignment(Pos.CENTER);
    grid.setHgap(10);
    grid.setVgap(15);
    grid.setPadding(new Insets(25));
    
    Scene scene = new Scene(grid, 430, 350);
    
    scene.getStylesheets().add(
        RegistroEscuela.class.getResource("/css/General.css").toExternalForm());
    primaryStage.setScene(scene);
    
    Text titulo = new Text("Registro de requisitos y correquisitos de curso");
    titulo.setFill(Color.WHITE);
    titulo.setFont(Font.font("Arial", FontWeight.BOLD, 16));
    grid.add(titulo, 0, 0, 2, 1);

    Label lblEscuela = new Label("Escuela propietaria del curso:");
    grid.add(lblEscuela, 0, 1);
    
    ComboBox bxEscuela = new ComboBox();
    grid.add(bxEscuela, 1, 1);    
    bxEscuela.setOnAction((Event ev) -> {
      escuela = bxEscuela.getSelectionModel().getSelectedItem().toString();    
    });
    
    Label lblCurso = new Label("Codigo del curso:");
    grid.add(lblCurso, 0, 2);
    
    ComboBox bxCurso = new ComboBox();
    grid.add(bxCurso, 1, 2);
    bxCurso.setOnAction((Event ev) -> {
      curso = bxEscuela.getSelectionModel().getSelectedItem().toString();    
    });
    
    Label lblCurso1 = new Label("Codigo del curso:");
    Label lblCurso2 = new Label("Codigo del curso:");
    
    Text tituloRequisito = new Text("Requisitos del curso");
    tituloRequisito.setFill(Color.WHITE);
    tituloRequisito.setFont(Font.font("Arial", FontWeight.BOLD, 14));
    ComboBox bxRequisito = new ComboBox();
    Button btnRegistrarReq = new Button("Registrar");
    VBox vbRequisito = new VBox(10);
    vbRequisito.setAlignment(Pos.BASELINE_CENTER);
    vbRequisito.getChildren().addAll(tituloRequisito,lblCurso1,bxRequisito,btnRegistrarReq);
    grid.add(vbRequisito, 0, 3);
    
    Text tituloCorrequisito = new Text("Correquisitos del curso");
    tituloCorrequisito.setFill(Color.WHITE);
    tituloCorrequisito.setFont(Font.font("Arial", FontWeight.BOLD, 14));
    ComboBox bxCorrequisito = new ComboBox();
    Button btnRegistrarCo = new Button("Registrar");
    VBox vbCorrequisito = new VBox(10);
    vbCorrequisito.setAlignment(Pos.BASELINE_CENTER);
    vbCorrequisito.getChildren().addAll(tituloCorrequisito,lblCurso2,bxCorrequisito,btnRegistrarCo);
    grid.add(vbCorrequisito, 1, 3);
        
    final Text lblError = new Text();
    lblError.setFill(Color.FIREBRICK);
    lblError.setFont(Font.font("Arial", FontWeight.BOLD, 12));
    Button btnCerrar = new Button("Cerrar");
    VBox vbBtn = new VBox(10);
    vbBtn.setAlignment(Pos.BASELINE_CENTER);
    vbBtn.getChildren().addAll(lblError, btnCerrar);
    grid.add(vbBtn, 0, 4, 2, 1);
    
    btnRegistrarReq.setOnAction((ActionEvent e) -> {        
      requisito = bxRequisito.getSelectionModel().getSelectedItem().toString();
    });
    
    btnRegistrarCo.setOnAction((ActionEvent e) -> {        
      correquisito = bxCorrequisito.getSelectionModel().getSelectedItem().toString();
    });
    
    btnCerrar.setOnAction((ActionEvent e) -> {        
      primaryStage.close();
    });    
    
    primaryStage.show();
  }
    
}
