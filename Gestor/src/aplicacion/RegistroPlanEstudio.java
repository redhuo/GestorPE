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
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author wonmi
 */
public class RegistroPlanEstudio {
  String escuela;
  String codigo;
  String fecha;
  String curso;
  String bloque;
    
  public void start(Stage primaryStage) {
    primaryStage.setTitle("Registro de Plan de Estudio");  
    
    GridPane grid = new GridPane();
    grid.setAlignment(Pos.CENTER);
    grid.setHgap(10);
    grid.setVgap(15);
    grid.setPadding(new Insets(25));
    
    Scene scene = new Scene(grid, 400, 370);
    
    scene.getStylesheets().add(
        RegistroEscuela.class.getResource("/css/General.css").toExternalForm());
    primaryStage.setScene(scene);
    
    Text titulo = new Text("Registro de Plan de Estudio");
    titulo.setFill(Color.WHITE);
    titulo.setFont(Font.font("Arial", FontWeight.BOLD, 16));
    grid.add(titulo, 0, 0, 2, 1);

    Label lblEscuela = new Label("Escuela propietaria del plan:");
    grid.add(lblEscuela, 0, 1);
    
    ComboBox bxEscuela = new ComboBox();
    grid.add(bxEscuela, 1, 1);    
    bxEscuela.setOnAction((Event ev) -> {
      escuela = bxEscuela.getSelectionModel().getSelectedItem().toString();    
    });

    Label lblCodigo = new Label("Codigo:");
    grid.add(lblCodigo, 0, 2);
    
    TextField txtCodigo = new TextField();
    grid.add(txtCodigo, 1, 2);
    
    Label lblFecha = new Label("Vigencia:");
    grid.add(lblFecha, 0, 3);
    
    TextField txtFecha = new TextField();
    txtFecha.setPromptText("dd/mm/yyyy");
    grid.add(txtFecha, 1, 3);
    
    Label lblCurso = new Label("Codigo del curso:");
    grid.add(lblCurso, 0, 5);
    
    TextField txtCurso = new TextField();
    grid.add(txtCurso, 1, 5);
    
    Label lblBloque = new Label("Bloque:");
    grid.add(lblBloque, 0, 6);
    
    ComboBox bxBloque = new ComboBox();
    grid.add(bxBloque, 1, 6);
    bxBloque.getItems().addAll("I Semestre", "II Semestre");
    
    Button btnRegistrar = new Button("Registrar curso al plan de estudio");
    HBox hbBtnRegistrar = new HBox(10);
    hbBtnRegistrar.setAlignment(Pos.CENTER);
    hbBtnRegistrar.getChildren().add(btnRegistrar);
    grid.add(hbBtnRegistrar, 0, 7, 2, 1);
    
    final Text lblError = new Text();
    lblError.setFill(Color.FIREBRICK);
    lblError.setFont(Font.font("Arial", FontWeight.BOLD, 12));
    grid.add(lblError, 1, 8); 
    
    Button btnCerrar = new Button("Cerrar");
    Button btnLimpiar = new Button("Limpiar campos");
    HBox hbBtn = new HBox(10);
    hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
    hbBtn.getChildren().addAll(btnLimpiar, btnCerrar);
    grid.add(hbBtn, 1, 9);
    
    btnLimpiar.setOnAction((ActionEvent e) -> { 
      txtCodigo.clear();
      txtFecha.clear();
      txtCurso.clear();
    });
    
    btnRegistrar.setOnAction((ActionEvent e) -> {        
      lblError.setText("Error en los datos ingresados");
    });      
    
    btnCerrar.setOnAction((ActionEvent e) -> {        
      primaryStage.close();
    }); 
    
    primaryStage.show();
  }

}
