package edu.fiuba.algo3;

import edu.fiuba.algo3.interfaz.pantallas.ControladorPantallaInicial;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

/**
 * JavaFX App
 */
public class App extends Application {

    private static MediaPlayer reproductor;
    String version = "0.9.9";

    private static Stage appStage;
    private static Popup popup;
    private static Popup popupWindow;
    private static VBox popupTray;

    @Override
    public void start(Stage stage) throws IOException {

        stage.setTitle("TEG "+version);
        Scene scene = new Scene(new Label("Cargando..."), 854, 480);
        stage.setScene(scene);
        inicializarEscala(stage, scene);

        appStage = stage;
        popup = new Popup();
        popup.setAutoHide(true);
        popupTray = new VBox();
        popup.getContent().add( popupTray );

        new ControladorPantallaInicial(scene);
    }

    private void inicializarEscala(Stage stage, Scene scene) {
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("styles-dark.css")).toExternalForm());
        stage.sizeToScene();
        stage.show();
        stage.setMinWidth(stage.getWidth());
        stage.setMinHeight(stage.getHeight());

        stage.setWidth(1200);
        stage.setHeight(800);
    }

    public static void main(String[] args) {
        launch();
    }

    public static void notificacion(String mensaje){

        if( Objects.isNull(appStage) ) return;

        HBox msjBox = new HBox( new Label(mensaje) );
        msjBox.getStyleClass().add("popupMessage");
        msjBox.setPadding( new Insets(10) );
        popupTray.getChildren().clear();// TODO - Para stackear: Reemplazar x una llamada que elimine
        popupTray.getChildren().add( msjBox );
        popup.show(appStage,appStage.getX()+30,appStage.getY()+100);

    }

    public static void popUpWindow( VBox window ) {

        if( Objects.isNull(appStage) ) return;

        popupWindow = new Popup();
        popupWindow.setAutoHide(true);
        popupWindow.getContent().add(window);

        // TODO - Centrar en _root
        double offsetX = appStage.getX() + 15 ;
        double offsetY = appStage.getY() + appStage.getHeight() - 223;

        popupWindow.show(appStage,offsetX,offsetY);
    }

    public static void clearPopUps(){
        popup.hide();
        popup.getContent().clear();
        popupWindow.hide();
        popupWindow.getContent().clear();
    }

    public static MediaPlayer sonido(String sonido) {

        String url = "sonidos/"+sonido+".mp3";
        try {
            Media sound = new Media(new File(url).toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.play();
            return mediaPlayer;
        } catch (Exception e ) {
            System.out.println("Cannot play: "+url);
        }
        return null;
    }

    public static void cancion(String cancion) {
        MediaPlayer reproductor = sonido(cancion);

        if (reproductor == null) return;

        reproductor.setVolume(0.2);
        reproductor.setCycleCount(MediaPlayer.INDEFINITE);
        reproductor.play();
        App.reproductor = reproductor;

    }

    public static void detenerCancion() { App.reproductor.pause(); }

    public static void reproducirCancion() { App.reproductor.play(); }
}