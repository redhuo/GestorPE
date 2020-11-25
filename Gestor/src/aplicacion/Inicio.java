/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
public class Inicio extends Application {
  String escuela;
  String plan;
    
  @Override
  public void start(Stage primaryStage) {
    primaryStage.setTitle("Inicio");    
    
    GridPane grid = new GridPane();
    grid.setAlignment(Pos.CENTER);
    grid.setHgap(10);
    grid.setVgap(15);
    grid.setPadding(new Insets(25));
    
    Scene scene = new Scene(grid, 700, 650);
    
    scene.getStylesheets().add(
        RegistroEscuela.class.getResource("/css/General.css").toExternalForm());
    primaryStage.setScene(scene);
    
    Text titulo = new Text("Consultar Plan de Estudio");
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

    Label lblCodigo = new Label("Codigo:");
    grid.add(lblCodigo, 2, 1);
    
    ComboBox bxPlan = new ComboBox();
    grid.add(bxPlan, 3, 1); 
    bxPlan.setOnAction((Event ev) -> {
      plan = bxEscuela.getSelectionModel().getSelectedItem().toString();    
    });
    
    Label lblFecha = new Label("Vigencia:");
    TextField txtFecha = new TextField();
    txtFecha.setPromptText("dd/mm/yyyy");
    HBox hbFecha = new HBox(10);
    hbFecha.setAlignment(Pos.CENTER);
    hbFecha.getChildren().addAll(lblFecha, txtFecha);
    grid.add(hbFecha, 0, 2, 4, 1);
    
    Button btnGenerarPdf = new Button("Generar PDF y enviar por correo");
    Button btnConsultar = new Button("Consultar");
    HBox hbBtnConsultar = new HBox(10);
    hbBtnConsultar.setAlignment(Pos.BOTTOM_RIGHT);
    hbBtnConsultar.getChildren().addAll(btnConsultar, btnGenerarPdf);
    grid.add(hbBtnConsultar, 3, 3);
    
    Text lblTabla = new Text("Cursos del plan de estudio");
    lblTabla.setFill(Color.WHITE);
    lblTabla.setFont(Font.font("Arial", FontWeight.BOLD, 14));
    grid.add(lblTabla, 0, 4);
    
    TableView tabla = new TableView();
    TableColumn colCodigo = new TableColumn("Codigo");
    colCodigo.setMinWidth(100);
    colCodigo.setCellValueFactory( new PropertyValueFactory<>("codigo"));
    TableColumn colNombre = new TableColumn("Nombre");
    colNombre.setMinWidth(275);
    colNombre.setCellValueFactory( new PropertyValueFactory<>("nombre"));
    TableColumn colBloque = new TableColumn("Bloque");
    colBloque.setMinWidth(100);
    colBloque.setCellValueFactory( new PropertyValueFactory<>("bloque"));
    TableColumn colHoras = new TableColumn("Horas");
    colHoras.setMinWidth(50);
    colHoras.setCellValueFactory( new PropertyValueFactory<>("horas"));
    TableColumn colCreditos = new TableColumn("Creditos");
    colCreditos.setMinWidth(50);
    colCreditos.setCellValueFactory( new PropertyValueFactory<>("creditos"));
    tabla.getColumns().addAll(colCodigo, colNombre, colBloque, colHoras, colCreditos);
    
    ScrollPane sp = new ScrollPane();
    sp.setHbarPolicy(ScrollBarPolicy.NEVER);
    sp.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
    sp.setVmax(200);
    sp.setHmax(600);
    sp.setPrefSize(600, 200);
    sp.setContent(tabla);
    grid.add(sp, 0, 5, 4, 1);
    
    Button btnEliminarCursoPlan = new Button("Eliminar curso");
    grid.add(btnEliminarCursoPlan, 3, 6);
    
    Text tituloCurso = new Text("Consultar Cursos");
    tituloCurso.setFill(Color.WHITE);
    tituloCurso.setFont(Font.font("Arial", FontWeight.BOLD, 16));
    grid.add(tituloCurso, 0, 7);
    
    ListView<String> lstCursos = new ListView<>();
    lstCursos.setPrefWidth(150);
    lstCursos.setPrefHeight(100);
    ListView<String> lstPlan = new ListView<>();
    lstPlan.setPrefWidth(200);
    lstPlan.setPrefHeight(100);    
    ListView<String> lstRequisitos = new ListView<>();
    lstRequisitos.setPrefWidth(150);
    lstRequisitos.setPrefHeight(100);    
    ListView<String> lstCorrequisitos = new ListView<>();
    lstCorrequisitos.setPrefWidth(150);
    lstCorrequisitos.setPrefHeight(100);
    HBox hbLists = new HBox(10);
    hbLists.setAlignment(Pos.CENTER);
    hbLists.getChildren().addAll(lstCursos, lstPlan, lstRequisitos, lstCorrequisitos);
    grid.add(hbLists, 0, 8, 4, 1);
    
    ObservableList<String> itmsPlan = 
        FXCollections.observableArrayList ("Planes de estudio:");
    lstPlan.setItems(itmsPlan);
    ObservableList<String> itmsRequisitos = 
        FXCollections.observableArrayList ("Requisitos:");
    lstRequisitos.setItems(itmsRequisitos);
    ObservableList<String> itmsCorrequisitos =
        FXCollections.observableArrayList ("Correquisitos:");
    lstCorrequisitos.setItems(itmsCorrequisitos);
    
    Button btnEliminar = new Button("Eliminar");
    grid.add(btnEliminar, 3, 9);
    
    Button btnEscuela = new Button("Registrar Escuela");
    Button btnCurso = new Button("Registrar Curso");
    Button btnPlan = new Button("Registrar Plan");
    Button btnCursoReq = new Button("Registrar Requisitos de Curso");
    HBox hbBtn = new HBox(20);
    hbBtn.setAlignment(Pos.CENTER);
    hbBtn.getChildren().addAll(btnEscuela, btnCurso, btnPlan, btnCursoReq);
    grid.add(hbBtn, 0, 11, 4, 1);
    
    btnGenerarPdf.setOnAction((ActionEvent e) -> {
        Stage stage = new Stage();
        InputBox ventana = new InputBox();
        ventana.start("Enviar correo","Ingrese su correo electronico");
    });
    
    btnEscuela.setOnAction((ActionEvent e) -> {
        Stage stage = new Stage();
        RegistroEscuela ventana = new RegistroEscuela();
        ventana.start(stage);
    });
    
    btnCurso.setOnAction((ActionEvent e) -> {
        Stage stage = new Stage();
        RegistroCurso ventana = new RegistroCurso();
        ventana.start(stage);
    });
    
    btnPlan.setOnAction((ActionEvent e) -> {
        Stage stage = new Stage();
        RegistroPlanEstudio ventana = new RegistroPlanEstudio();
        ventana.start(stage);
    });
    
    btnCursoReq.setOnAction((ActionEvent e) -> {
        Stage stage = new Stage();
        RegistroReqCurso ventana = new RegistroReqCurso();
        ventana.start(stage);
    });
    
    primaryStage.show();
    /*
    Button btnEliminarCurso = new Button("Eliminar curso");
    Button btnEliminarPlan = new Button("Eliminar plan");
    Button btnEliminarRequisito = new Button("Eliminar requisito");
    Button btnEliminarCorrequisito = new Button("Eliminar correquisito");
    HBox hbBtnEliminar = new HBox(20);
    hbBtnEliminar.setAlignment(Pos.CENTER);
    hbBtnEliminar.getChildren().add(btnEliminarCurso);
    hbBtnEliminar.getChildren().add(btnEliminarPlan);
    hbBtnEliminar.getChildren().add(btnEliminarRequisito);
    hbBtnEliminar.getChildren().add(btnEliminarCorrequisito);
    grid.add(hbBtnEliminar, 0, 9, 4, 1);
    
    
    
    final Text lblError = new Text();
    lblError.setFill(Color.FIREBRICK);
    lblError.setFont(Font.font("Arial", FontWeight.BOLD, 12));
    grid.add(lblError, 3, 9);*/
    
    btnEliminarCursoPlan.setOnAction((ActionEvent e) -> {        
    });
  }


  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    launch(args);
  }
    
}
