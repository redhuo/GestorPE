/**
 * Ventana de inicio de la aplicacion, consulta de plan de estudio, consulta de
 * curso y eliminacion de curso y requisito y correquisito de un curso
 * @author WonMi y Jimmy
 */

package aplicacion;

import dao.CursoDao;
import dao.EscuelaDao;
import dao.PlanDeEstudioDao;
import java.util.ArrayList;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.beans.property.StringProperty;
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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.mail.MessagingException;
import modelo.Curso;
import modelo.Escuela;
import modelo.PlanDeEstudio;
import utils.Email;
import utils.PDF;

public class Inicio extends Application {
  CursoDao cursoDao;
  EscuelaDao escuelaDao;
  PlanDeEstudioDao planDeEstudioDao;
  Curso cursoSelect;
  Escuela escuelaSelect;
  PlanDeEstudio planSelect;
  ArrayList<Escuela> escuelas;
  ArrayList<PlanDeEstudio> planesDeEstudio;
  ArrayList<Curso> cursos;
  ArrayList<Curso> requisitos;
  ArrayList<Curso> correquisitos;
  ObservableList<Curso> planCursos;
  ObservableList<PlanDeEstudio> cursoPlanes;
  ObservableList<Curso> cursoRequisitos;
  String curso;
  String escuela;
  int plan;
  
    
  @Override
  public void start(Stage primaryStage) {
    primaryStage.setTitle("Inicio");    
    
    GridPane grid = new GridPane();
    grid.setAlignment(Pos.CENTER);
    grid.setHgap(10);
    grid.setVgap(15);
    grid.setPadding(new Insets(25));
    
    Scene scene = new Scene(grid, 950, 650);
    
    //Carga los recurso de un archivo css
    scene.getStylesheets().add(
    RegistroEscuela.class.getResource("/css/General.css").toExternalForm());
    primaryStage.setScene(scene);
    
    //Seleccionar una escuela o area academica
    Text titulo = new Text("Escuela propietaria:");
    titulo.setFill(Color.WHITE);
    titulo.setFont(Font.font("Arial", FontWeight.BOLD, 16));
    grid.add(titulo, 0, 0, 4, 1);
    //Lista desplegable de escuelas o areas academicas
    ComboBox bxEscuela = new ComboBox();
    grid.add(bxEscuela, 1, 0);  
    
    //Seleccionar un plan de estudio
    Text tituloPlan = new Text("Consultar Plan de Estudio");
    tituloPlan.setFill(Color.WHITE);
    tituloPlan.setFont(Font.font("Arial", FontWeight.BOLD, 16));
    grid.add(tituloPlan, 0, 1);
    Label lblCodigoPlan = new Label("Codigo:");
    //Lista desplegable de codigo de planes de estudio
    ComboBox bxPlan = new ComboBox();
    Label lblFecha = new Label("Vigencia:");
    HBox hbPlan = new HBox(10);
    hbPlan.setAlignment(Pos.CENTER);
    hbPlan.getChildren().addAll(lblCodigoPlan, bxPlan, lblFecha);
    grid.add(hbPlan, 1, 1, 3, 1);
    
    //Tabla de cursos pertenecientes al plan de estudio
    Text lblTablaCursos = new Text("Cursos del plan de estudio");
    lblTablaCursos.setFill(Color.WHITE);
    lblTablaCursos.setFont(Font.font("Arial", FontWeight.BOLD, 14));
    grid.add(lblTablaCursos, 0, 2);
    TableView<Curso> tablaCursos = new TableView();
   // Curso.codigoProperty();
    //Columnas de la tabla de cursos
    TableColumn<Curso,StringProperty> colCodigo = new TableColumn("Codigo");
    colCodigo.setMinWidth(50);
    colCodigo.setCellValueFactory( new PropertyValueFactory<Curso,StringProperty>("codigo"));
    TableColumn<Curso,String> colNombre = new TableColumn("Nombre");
    colNombre.setMinWidth(200);
    colNombre.setCellValueFactory( new PropertyValueFactory<>("nombre"));
    TableColumn colBloque = new TableColumn("Bloque");
    colBloque.setMinWidth(100);
    colBloque.setCellValueFactory( new PropertyValueFactory<Curso,String>("bloque"));
    TableColumn colHoras = new TableColumn("Horas");
    colHoras.setMinWidth(50);
    colHoras.setCellValueFactory( new PropertyValueFactory<Curso,Integer>("horasLectivas"));
    TableColumn colCreditos = new TableColumn("Creditos");
    colCreditos.setMinWidth(50);
    colCreditos.setCellValueFactory( new PropertyValueFactory<Curso,Integer>("creditos"));
    //Agrega las columnas creadas a la tabla de cursos
    tablaCursos.getColumns().addAll(colCodigo, colNombre, 
        colBloque, colHoras, colCreditos);
    //Panel con barra de desplazamiento en la que se situa la tabla de cursos
    ScrollPane spTablaCursos = new ScrollPane();
    spTablaCursos.setHbarPolicy(ScrollBarPolicy.NEVER);
    spTablaCursos.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
    spTablaCursos.setVmax(200);
    spTablaCursos.setHmax(625);
    spTablaCursos.setPrefSize(625, 200);
    spTablaCursos.setContent(tablaCursos);
    
    //Conjunto de botones para las funciones del plan de estudio se ubica en una caja vertical
    Button btnGenerarPdf = new Button("Generar PDF y enviar por correo");
    Button btnEliminarCurso = new Button("Eliminar curso");
    VBox vbBtnConsultar = new VBox(10);
    vbBtnConsultar.setAlignment(Pos.TOP_LEFT);
    vbBtnConsultar.getChildren().addAll(btnGenerarPdf, btnEliminarCurso);

    /**
     * Caja horizontal para el panel de la tabla de cursos y la caja vertical que 
     * contiene el conjunto de botones para las funciones del plan de estudio
     */
    HBox hbTablaCursos = new HBox(10);
    hbTablaCursos.setAlignment(Pos.TOP_LEFT);
    hbTablaCursos.getChildren().addAll(spTablaCursos, vbBtnConsultar);
    grid.add(hbTablaCursos, 0, 3, 4, 1);
    
    //Seleccionar un curso
    Text tituloCurso = new Text("Consultar Cursos");
    tituloCurso.setFill(Color.WHITE);
    tituloCurso.setFont(Font.font("Arial", FontWeight.BOLD, 16));
    grid.add(tituloCurso, 0, 4);
    Label lblCodigoCurso = new Label("Codigo:");
    //Lista desplegable de codigo de cursos
    ComboBox bxCurso = new ComboBox();
    //Boton para eliminar un plan de estudio del curso 
    Button btnEliminarPlan = new Button("Eliminar Curso del Plan");
    //Boton para eliminar un plan de estudio del curso 
    Button btnEliminarRequisito = new Button("Eliminar Requisito");
    HBox hbCurso = new HBox(10);
    hbCurso.setAlignment(Pos.CENTER);
    hbCurso.getChildren().addAll(lblCodigoCurso, bxCurso, btnEliminarPlan, btnEliminarRequisito);
    grid.add(hbCurso, 1, 4, 3, 1);
    
    //Tabla de planes de estudio que presentan el curso
    Text lblTablaPlanes = new Text("Planes de estudio que presentan el curso");
    lblTablaPlanes.setFill(Color.WHITE);
    lblTablaPlanes.setFont(Font.font("Arial", FontWeight.BOLD, 14));
    TableView<PlanDeEstudio> tablaPlanes = new TableView();
    //Columnas de la tabla de planes de estudio
    TableColumn colNumero = new TableColumn("Numero");
    colNumero.setMinWidth(50);
    colNumero.setCellValueFactory( new PropertyValueFactory<PlanDeEstudio,Integer>("numero"));
    TableColumn colEscuela = new TableColumn("Escuela");
    colEscuela.setMinWidth(50);
    colEscuela.setCellValueFactory( new PropertyValueFactory<PlanDeEstudio,String>("escuela"));
    TableColumn colVigencia = new TableColumn("Vigencia");
    colVigencia.setMinWidth(100);
    colVigencia.setCellValueFactory( new PropertyValueFactory<PlanDeEstudio,String>("fechaVigencia"));
    //colBloque.setCellValueFactory( new PropertyValueFactory<>("bloque"));
    //Agrega las columnas creadas a la tabla de planes de estudio
    tablaPlanes.getColumns().addAll(colNumero, colEscuela, colVigencia);
    //Panel con barra de desplazamiento en la que se situa la tabla de planes de estudio
    ScrollPane spTablaPlanes = new ScrollPane();
    spTablaPlanes.setHbarPolicy(ScrollBarPolicy.NEVER);
    spTablaPlanes.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
    spTablaPlanes.setVmax(200);
    spTablaPlanes.setHmax(375);
    spTablaPlanes.setPrefSize(375, 200);
    spTablaPlanes.setContent(tablaPlanes);
    //Caja vertical para agrupar la tabla de planes de estudio y la etiqueta de esta
    VBox vbTablaPlanes = new VBox(10);
    vbTablaPlanes.setAlignment(Pos.TOP_LEFT);
    vbTablaPlanes.getChildren().addAll(lblTablaPlanes, spTablaPlanes);
    
    //Tabla de requisitos y correquisitos
    Text lblTablaReqs = new Text("Requisitos y correquisitos del curso");
    lblTablaReqs.setFill(Color.WHITE);
    lblTablaReqs.setFont(Font.font("Arial", FontWeight.BOLD, 14));
    TableView<Curso> tablaReqs = new TableView();
    TableColumn colNombreR = new TableColumn("Nombre");
    colNombreR.setMinWidth(170);
    colNombreR.setCellValueFactory( new PropertyValueFactory<>("nombre"));
    //Agrega las columnas creadas a la tabla de requisitos y correquisitos
    tablaReqs.getColumns().addAll(colCodigo, colNombreR, colHoras, colCreditos);
    //Panel con barra de desplazamiento en la que se situa la tabla de requisitos y correquisitos
    ScrollPane spTablaReqs = new ScrollPane();
    spTablaReqs.setHbarPolicy(ScrollBarPolicy.NEVER);
    spTablaReqs.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
    spTablaReqs.setVmax(200);
    spTablaReqs.setHmax(500);
    spTablaReqs.setPrefSize(500, 200);
    spTablaReqs.setContent(tablaReqs);
    //Caja vertical para agrupar la tabla de requisitos y correquisitos y la etiqueta de esta
    VBox vbTablaReqs = new VBox(10);
    vbTablaReqs.setAlignment(Pos.TOP_LEFT);
    vbTablaReqs.getChildren().addAll(lblTablaReqs, spTablaReqs);
    
    /**
     * Caja horizontal para agrupar las cajas verticales de las tablas de planes 
     * de estudio y la tabla de requisitos y correquisitos 
     */
    HBox hbTablas = new HBox(10);
    hbTablas.setAlignment(Pos.TOP_LEFT);
    hbTablas.getChildren().addAll(vbTablaPlanes, vbTablaReqs);
    grid.add(hbTablas, 0, 5, 4, 1);
    
    //Conjunto de botones para abrir otras ventanas
    Button btnEscuela = new Button("Registrar Escuela");
    Button btnCurso = new Button("Registrar Curso");
    Button btnPlan = new Button("Registrar Plan de Estudio");
    Button btnCursoReq = new Button("Registrar Requisitos de Curso");
    HBox hbBtn = new HBox(20);
    hbBtn.setAlignment(Pos.CENTER);
    hbBtn.getChildren().addAll(btnEscuela, btnCurso, btnPlan, btnCursoReq);
    grid.add(hbBtn, 0, 6, 4, 1);
    
    //Cargar datos
    escuelaDao = new EscuelaDao();
    planDeEstudioDao = new PlanDeEstudioDao();
    cursoDao = new CursoDao();
    escuelas = escuelaDao.getEscuelas();
    escuelas.forEach((e) -> {
      bxEscuela.getItems().add(e.getNombre());
    });
    
    /**
     * Se activa al seleccionar una escuela de la lista desplagable
     * recoge el codigo de la escuela para cargar los daros en las listas desplegables
     * de planes de estudio y cursos
     */
    bxEscuela.setOnAction((Event ev) -> {
      escuela = bxEscuela.getSelectionModel().getSelectedItem().toString();
      for(Escuela e : escuelas){
        if(e.getNombre() == escuela){
          escuela = e.getCodigo();
        }
      }
      planesDeEstudio = planDeEstudioDao.getPlanesDeEstudioPorEscuela(escuela);
      bxPlan.getItems().clear();
      planesDeEstudio.forEach((p) -> {
        bxPlan.getItems().add(p.getNumero());
      });
      cursos = cursoDao.getCursosPorEscuela(escuela);
      bxCurso.getItems().clear();
      cursos.forEach((c) -> {
        bxCurso.getItems().add(c.getCodigo());
      });
    });
    
    /**
     * Se activa al seleccionar un plan de estudio de la lista desplagable
     * carga los datos en la tabla de cursos pertenecientes al plan
     */
    bxPlan.setOnAction((Event ev) -> {
      tablaCursos.getColumns().clear();
    
      plan = Integer.parseInt(bxPlan.getSelectionModel().getSelectedItem().toString());
      for(PlanDeEstudio p : planesDeEstudio){
        if(p.getNumero() == plan){
          lblFecha.setText("Vigencia: " + p.getFechaVigencia());
        }
      }
      planCursos = cursoDao.getCursosPorPlan(plan);
      tablaCursos.setItems(planCursos);
      tablaCursos.getColumns().clear();
      tablaCursos.getColumns().addAll(colCodigo, colNombre, 
        colBloque, colHoras, colCreditos);
    });
    
    /**
     * Se activa al seleccionar un curso de la lista desplagable 
     */
    bxCurso.setOnAction((Event ev) -> {
      tablaPlanes.getColumns().clear();
      curso = bxCurso.getSelectionModel().getSelectedItem().toString();
      cursoPlanes = planDeEstudioDao.getPlanesDeEstudioPorCurso(curso);
      System.out.println("deploy "+curso);
      requisitos = cursoDao.getCursosRequisitos(curso);
      correquisitos = cursoDao.getCursosCorrequisitos(curso);
      cursoRequisitos = FXCollections.observableArrayList();
      requisitos.forEach((curso) -> {
        cursoRequisitos.add(curso);
      });
      correquisitos.forEach((curso) -> {
        cursoRequisitos.add(curso);
      });
      tablaPlanes.setItems(cursoPlanes);
      System.out.println("el tamano es "+cursoPlanes.size());
      tablaPlanes.getColumns().clear();
      tablaPlanes.getColumns().addAll(colNumero, colEscuela, colVigencia);
      tablaReqs.setItems(cursoRequisitos);
      tablaReqs.getColumns().clear();
      tablaReqs.getColumns().addAll(colCodigo, colNombreR, colHoras, colCreditos);
    });
    
    //Eliminar el curso seleccionado de la tabla de cursos
    btnEliminarCurso.setOnAction((ActionEvent e) -> { 
      int indice = tablaCursos.getSelectionModel().getSelectedIndex();
      if(indice>-1){
        cursoSelect = (Curso) tablaCursos.getSelectionModel().getSelectedItem();
        System.out.println(indice);
        curso = cursoSelect.getCodigo();
        if(planDeEstudioDao.eliminarPlanCurso(plan,curso)){
          planCursos.remove(indice);
        }
      }
    });
    
    //Eliminar plan de estudio de un curso dependiendo
    btnEliminarPlan.setOnAction((ActionEvent e) -> {   
      int indice = tablaPlanes.getSelectionModel().getSelectedIndex();
      if(indice>-1){
        planSelect = (PlanDeEstudio) tablaPlanes.getSelectionModel().getSelectedItem();
        plan = planSelect.getNumero();
        if(planDeEstudioDao.eliminarPlanCurso(plan,curso)){
          cursoPlanes.remove(indice);
        }
      }
    });
    
    //Eliminar requisito o corresquisito de un curso
    btnEliminarRequisito.setOnAction((ActionEvent e) -> { 
      int indice = tablaReqs.getSelectionModel().getSelectedIndex();
      
      if(indice>-1){
        String cursoReqCodigo;
        System.out.println("el error esta aqui "+ tablaReqs.getSelectionModel().getSelectedItem().getCodigo());
        cursoSelect = tablaReqs.getSelectionModel().getSelectedItem();
        cursoReqCodigo = cursoSelect.getCodigo();
        if(cursoDao.eliminarRequisito(cursoReqCodigo, curso)||
            cursoDao.eliminarCorrequisito(cursoReqCodigo, curso)){
          cursoRequisitos.remove(indice);
        }
      }
    });
    
    /**
     * Abre un cuador de dialogo con un espacio para ingresar el correo al que se
     * enviara le PDF generado
     */
    btnGenerarPdf.setOnAction((ActionEvent e) -> {
     // new PDF(Integer.toString(plan)).crearPDF("joss", planCursos);
      TextInputDialog dialog = new TextInputDialog("correo@gmail.com");
      dialog.setTitle("Enviar plan de estudio");
      dialog.setHeaderText("Enviar PDF del plan de estudio");
      dialog.setContentText("Ingrese su correo electronico:");
      Optional<String> result = dialog.showAndWait();
      // Traditional way to get the response value.
      if (result.isPresent()){
        System.out.println("Your name: " + result.get());
        new PDF(Integer.toString(plan)).crearPDF("plandeestudio", planCursos);
        try {
          new Email(result.get(),"plandeestudio.pdf",Integer.toString(plan)).enviar();
        } 
        catch (MessagingException ex) {
          Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
        }        
      }
    });
    
    //Abre la ventana de registro de escuela o area academica
    btnEscuela.setOnAction((ActionEvent e) -> {
      Stage stage = new Stage();
      RegistroEscuela ventana = new RegistroEscuela();
      ventana.start(stage);
      primaryStage.close();
    });
    
    //Abre la ventana de registro de curso
    btnCurso.setOnAction((ActionEvent e) -> {
      Stage stage = new Stage();
      RegistroCurso ventana = new RegistroCurso();
      ventana.start(stage);
    });
    
    //Abre la ventana de registro de plan de estudio
    btnPlan.setOnAction((ActionEvent e) -> {
      Stage stage = new Stage();
      RegistroPlanEstudio ventana = new RegistroPlanEstudio();
      ventana.start(stage);
    });
    
    //Abre la ventana de registro de requisitos y correquisitos de un curso
    btnCursoReq.setOnAction((ActionEvent e) -> {
      Stage stage = new Stage();
      RegistroReqCurso ventana = new RegistroReqCurso();
      ventana.start(stage);
    });
    
    primaryStage.show();
  }

  /**
   * Metdo main para iniciar la aplicacion
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    launch(args);
  }
    
}
