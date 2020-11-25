/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author wonmi
 */
public class InputBox {
    
  public void start(String titulo, String mensaje) {
    Stage ventana =  new Stage();
    ventana.initModality(Modality.APPLICATION_MODAL);
    
    Label lbl =  new Label();
    lbl.setText(mensaje);
    
    TextField txt = new TextField();
    Button btnAceptar = new Button("Aceptar");
    Button btnCancelar = new Button("Cancelar");
    btnAceptar.setOnAction((ActionEvent event) -> {
        ventana.close();
    });
    btnCancelar.setOnAction((ActionEvent event) -> {
        ventana.close();
    });

    HBox btn = new HBox(10);
    btn.getChildren().addAll(btnCancelar, btnAceptar);
    btn.setAlignment(Pos.CENTER);
    
    VBox pagina = new VBox(10);
    pagina.getChildren().addAll(lbl, txt, btn);
    pagina.setAlignment(Pos.CENTER);

    Scene scene = new Scene(pagina);

    ventana.setTitle(titulo);
    ventana.setMinWidth(260);
    ventana.setScene(scene);
    ventana.show();
  }
    
}
