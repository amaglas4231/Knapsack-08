package UI;

import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;
import javafx.stage.Stage;
import GeneticA.*;
import java.util.ArrayList;

import Core.UserVariables;

// alt shift F to format

public class DSParcelMaxVisual extends Application {

    private static final float WIDTH = 800;
    private static final float HEIGHT = 500;
    private double anchorX, anchorY;
    private double anchorAngleX = 0;
    private double anchorAngleY = 0;
    private final DoubleProperty angleX = new SimpleDoubleProperty(0);
    private final DoubleProperty angleY = new SimpleDoubleProperty(0);
    public static boolean pentobool = false;

    @Override
    public void start(Stage primaryStage) throws Exception {

        // Max Values Parcel

        depthSearch.InitialiseParcels();
        ArrayList<Integer> Arr = new ArrayList<Integer>();
        Arr.add(22); // C
        Arr.add(10); // B
        Arr.add(30); // A
        depthSearch.depthSearching(Arr);

        int[][][] result = copyArray(UserVariables.container);

        SmartGroup group = new SmartGroup();

        PhongMaterial red = new PhongMaterial();
        red.setDiffuseColor(Color.RED);

        PhongMaterial green = new PhongMaterial();
        green.setDiffuseColor(Color.GREEN);

        PhongMaterial blue = new PhongMaterial();
        blue.setDiffuseColor(Color.BLUE);

        PhongMaterial white = new PhongMaterial();
        white.setDiffuseColor(Color.WHITE);

        int xIndex = 0;
        for (int i = 0; i < 33; i++) {

            int yIndex = 0;

            for (int j = 0; j < 5; j++) {

                int zIndex = 0;

                for (int k = 0; k < 8; k++) {

                    Box box = new Box(5, 5, 5);

                    if (result[i][j][k] == 0) {
                        box.setMaterial(white);
                    } else if (result[i][j][k] == 1) {
                        box.setMaterial(blue);

                    } else if (result[i][j][k] == 2) {
                        box.setMaterial(green);

                    } else if (result[i][j][k] == 3) {
                        box.setMaterial(red);

                    }

                    box.translateZProperty().set(zIndex);
                    box.translateYProperty().set(yIndex);
                    box.translateXProperty().set(xIndex);
                    group.getChildren().add(box);

                    zIndex += 6;
                }
                yIndex += 6;
            }
            xIndex += 6;
        }

        Camera camera = new PerspectiveCamera();

        Scene scene = new Scene(group, WIDTH, HEIGHT, true);
        scene.setFill(Color.SILVER);
        scene.setCamera(camera);

        group.translateXProperty().set(WIDTH / 2);
        group.translateYProperty().set(HEIGHT / 2);
        group.translateZProperty().set(-600);

        initMouseControl(group, scene, primaryStage);

        primaryStage.setTitle("3D Knapsack");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public int[][][] copyArray(int[][][] array) {
        int[][][] newArray = new int[array.length][array[0].length][array[0][0].length];

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                for (int k = 0; k < array[i][j].length; k++) {
                    newArray[i][j][k] = array[i][j][k];

                }

            }

        }
        return newArray;
    }

    private void initMouseControl(SmartGroup group, Scene scene, Stage stage) {
        Rotate xRotate;
        Rotate yRotate;
        group.getTransforms().addAll(
                xRotate = new Rotate(0, Rotate.X_AXIS),
                yRotate = new Rotate(0, Rotate.Y_AXIS));
        xRotate.angleProperty().bind(angleX);
        yRotate.angleProperty().bind(angleY);

        scene.setOnMousePressed(event -> {
            anchorX = event.getSceneX();
            anchorY = event.getSceneY();
            anchorAngleX = angleX.get();
            anchorAngleY = angleY.get();
        });

        scene.setOnMouseDragged(event -> {
            angleX.set(anchorAngleX - (anchorY - event.getSceneY()));
            angleY.set(anchorAngleY + anchorX - event.getSceneX());
        });

        stage.addEventHandler(ScrollEvent.SCROLL, event -> {
            double movement = event.getDeltaY();
            group.translateZProperty().set(group.getTranslateZ() - movement);
        });
    }

    public static void main(String[] args) {

        launch();
    }

    public static void bigMain() {

        launch();
    }

    class SmartGroup extends Group {

        Rotate r;
        Transform t = new Rotate();

        void rotateByX(int ang) {
            r = new Rotate(ang, Rotate.X_AXIS);
            t = t.createConcatenation(r);
            this.getTransforms().clear();
            this.getTransforms().addAll(t);

        }

        void rotateByY(int ang) {
            r = new Rotate(ang, Rotate.Y_AXIS);
            t = t.createConcatenation(r);
            this.getTransforms().clear();
            this.getTransforms().addAll(t);

        }
    }
}
