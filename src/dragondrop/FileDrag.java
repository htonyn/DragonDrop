/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dragondrop;

import java.io.File;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class FileDrag extends Pane {
    public FileDrag() {
        Rectangle rekt = new Rectangle();
        rekt.relocate(50, 50);
        rekt.setWidth(300.0);
        rekt.setHeight(100.0);
        rekt.setFill(Color.LIGHTGREEN);
        
        rekt.setOnDragEntered(
            (DragEvent event) -> {
                if(event.getSource() == rekt && event.getDragboard().hasFiles()) {
                    rekt.setFill(Color.DARKGREEN);
                }
                
                event.consume();
            }
        );
        
        rekt.setOnDragExited(
            (DragEvent event) -> {
                rekt.setFill(Color.LIGHTGREEN);
                event.consume();
            }
        );
        
        rekt.setOnDragOver(
            (DragEvent event) -> {
                if(event.getDragboard().hasFiles()) {
                    for (File f: event.getDragboard().getFiles()) {
                        event.acceptTransferModes(TransferMode.COPY);
                    }
                }
            }
        );
        
        rekt.setOnDragDropped(
            (DragEvent event) -> {
                if(event.getDragboard().hasFiles()) {
                    for (File f: event.getDragboard().getFiles()) {
                        System.out.println(f.getPath());
                    }
                    event.setDropCompleted(true);
                }
                event.consume();
            }
        );
        
        this.getChildren().addAll(rekt);
    }
}
