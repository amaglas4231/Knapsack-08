package UI;

import javafx.scene.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.*;

public class Shape {

    Group Shape = new Group();
    Box box;

    public Node Lpent() {

        int yIndex = 0;
        int xIndex = 0;

        PhongMaterial blue = new PhongMaterial();
        blue.setDiffuseColor(Color.BLUE);

        for (int i = 0; i < 4; i++) {

            box = new Box(5, 5, 5);

            box.setMaterial(blue);
            box.translateYProperty().set(yIndex);
            Shape.getChildren().add(box);
            yIndex += 6;
        }

        xIndex = 6;
        yIndex = 0;

        for (int j = 0; j < 1; j++) {

            box = new Box(5, 5, 5);

            box.setMaterial(blue);

            box.translateXProperty().set(xIndex);
            box.translateYProperty().set(yIndex);

            Shape.getChildren().add(box);
            yIndex = 6;
        }
        return Shape;
    }

    public Node Ppent() {

        int yIndex = 0;
        int xIndex = 0;

        PhongMaterial yellow = new PhongMaterial();
        yellow.setDiffuseColor(Color.YELLOW);

        for (int i = 0; i < 3; i++) {

            box = new Box(5, 5, 5);

            box.setMaterial(yellow);

            box.translateYProperty().set(yIndex);

            Shape.getChildren().add(box);

            yIndex += 6;
        }

        xIndex = 6;
        yIndex = 0;

        for (int j = 0; j < 2; j++) {

            box = new Box(5, 5, 5);

            box.setMaterial(yellow);

            box.translateXProperty().set(xIndex);
            box.translateYProperty().set(yIndex);

            Shape.getChildren().add(box);

            yIndex += 6;
        }

        return Shape;
    }

    public Node Tpent() {

        int yIndex = 0;
        int xIndex = 0;

        PhongMaterial red = new PhongMaterial();
        red.setDiffuseColor(Color.RED);

        for (int i = 0; i < 3; i++) {

            box = new Box(5, 5, 5);

            box.setMaterial(red);

            box.translateYProperty().set(yIndex);

            Shape.getChildren().add(box);

            yIndex += 6;
        }

        xIndex = 6;
        yIndex = 0;

        for (int j = 0; j < 1; j++) {

            box = new Box(5, 5, 5);

            box.setMaterial(red);
            box.translateXProperty().set(xIndex);
            box.translateYProperty().set(yIndex);

            Shape.getChildren().add(box);

            yIndex += 6;
        }

        xIndex = -6;
        yIndex = 0;

        for (int k = 0; k < 1; k++) {

            box = new Box(5, 5, 5);

            box.setMaterial(red);
            box.translateXProperty().set(xIndex);
            box.translateYProperty().set(yIndex);

            Shape.getChildren().add(box);

            yIndex += 6;
        }

        return Shape;
    }
}