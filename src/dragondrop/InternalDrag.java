/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dragondrop;

import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class InternalDrag extends Pane {
    public InternalDrag() {
        Text source = new Text("START DRAG HERE");
        source.relocate(50.0, 50.0);
        source.setFont(new Font(48.0));
        
        source.setOnDragDetected(
            (MouseEvent event) -> {
                Dragboard db = source.startDragAndDrop(TransferMode.ANY);
                
                ClipboardContent content = new ClipboardContent();
                content.putString(source.getText());
                db.setContent(content);
                event.consume();
            }
        );
        source.setOnDragDone(
            (DragEvent event) -> {
                if (event.getTransferMode() == TransferMode.MOVE) {
                    source.setText("YOU HAVE DRUG");
                }
                event.consume();
            }
        );
        
        Text target = new Text("DRAG HERE");
        target.relocate(200.0, 125.0);
        target.setFont(new Font(48.0));
        
        target.setOnDragOver(
            (DragEvent event) -> {
                if (event.getDragboard().hasString()) {
                    event.acceptTransferModes(TransferMode.MOVE);
                }
                event.consume();
            }
        );
        
        target.setOnDragDropped(
            (DragEvent event) -> {
                Dragboard db = event.getDragboard();
                boolean success = false;
                
                if (db.hasString()) {
                    System.out.println(db.getString());
                    success = true;
                }
                
                event.setDropCompleted(success);
                event.consume();
            }
        );
        
        target.setOnDragEntered(
            (DragEvent event)  -> {
                if (event.getGestureSource() != target && event.getDragboard().hasString()) {
                    target.setFill(Color.GREEN);
                }
                event.consume();
            }
        );
        
        target.setOnDragExited(
            (DragEvent event) -> {
                target.setFill(Color.BLACK);
                event.consume();
            }
        );
        this.getChildren().addAll(source, target);
    }
}