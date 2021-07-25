package edu.fiuba.algo3.UI;

import edu.fiuba.algo3.modelo.Juego;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import org.w3c.dom.events.MouseEvent;

import java.io.IOException;

public class JuegoUI {

    // Al tocar un pais se activa un evento de tipo MouseEvent, por lo que el parametro sera un MouseEvent
    // JavaFX usa reflexion para encontrar 'eventHandlers', pero no funciona para MouseEvent ( esta pensado para ActionEvent ? )
    // Ergo, le pongo que es de tipo Event ( Que parece funcionar para lo que requerimos )
    @FXML public void tocarPais(Event mouseEvent) {

        Node node = (Node) mouseEvent.getSource() ;
        String nombrePais = (String) node.getId();

        System.out.println("Tocaste el Pais: "+nombrePais);
    }

}