package tiy.gui;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;

public class SampleCanvas extends Application {

    final double DEFAULT_SCENE_HEIGHT = 600;
    final double DEFAULT_SCENE_WIDTH = 800;
    int numCircles = 2;
    double strokeSize = 2;

    @Override
    public void start(Stage primaryStage) {
        Group rootGroup = new Group();

//        Scene mainScene = new Scene(rootGroup, 800, 600, Color.BLACK);


        Canvas canvas = new Canvas(DEFAULT_SCENE_WIDTH, DEFAULT_SCENE_HEIGHT);
        canvas.setFocusTraversable(true);

        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();

        graphicsContext.setStroke(Color.color(Math.random(), Math.random(), Math.random()));
        graphicsContext.setLineWidth(2);

//        drawShapes(graphicsContext);

        canvas.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getText().equalsIgnoreCase("a")) {
                    graphicsContext.setStroke(Color.color(Math.random(), Math.random(), Math.random()));
                }
                if (event.getCode() == KeyCode.LEFT) {
                    numCircles -= 2;
                    if (numCircles == 0) {
                        numCircles += 2;
                    }
                }
                if (event.getCode() == KeyCode.RIGHT) {
                numCircles += 2;
                if (numCircles == 100) {
                    numCircles -= 2;
                }
                }
                if (event.getText().equalsIgnoreCase("c")) {
                    graphicsContext.clearRect(0, 0, DEFAULT_SCENE_WIDTH, DEFAULT_SCENE_HEIGHT);
                }
                if (event.getCode() == KeyCode.UP) {
                    strokeSize += 1;
                    if (strokeSize == 20) {
                        strokeSize -= 1;
                    }
            }
                if (event.getCode() == KeyCode.DOWN) {
                    strokeSize -= 1;
                    if (strokeSize == 2) {
                        strokeSize +=1;
                            }
                }
        }
        });

        canvas.setOnMouseMoved(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent e) {
                 if (e.isAltDown() && e.isDragDetect()) {
                     graphicsContext.clearRect(0, 0, DEFAULT_SCENE_WIDTH, DEFAULT_SCENE_HEIGHT);
                     for (int counter = 0; counter < numCircles; counter++) {
                         graphicsContext.setStroke(Color.color(Math.random(), Math.random(), Math.random()));
                         graphicsContext.strokeOval((Math.random() * e.getX()), (Math.random() * e.getY()), strokeSize, strokeSize);
                     }
//                    graphicsContext.clearRect(0, 0, DEFAULT_SCENE_WIDTH, DEFAULT_SCENE_HEIGHT);
                 } else if (e.isControlDown() && e.isDragDetect()) {
//                    graphicsContext.strokeOval(e.getX(), e.getY(), strokeSize, strokeSize);
                     graphicsContext.clearRect(0, 0, DEFAULT_SCENE_WIDTH, DEFAULT_SCENE_HEIGHT);
                     for (int counter = 0; counter < numCircles; counter++) {
                         graphicsContext.setStroke(Color.color(Math.random(), Math.random(), Math.random()));
                         graphicsContext.strokeOval(Math.random() * DEFAULT_SCENE_WIDTH, Math.random() * DEFAULT_SCENE_HEIGHT, strokeSize, strokeSize);
                    }
                 } else if (e.isDragDetect()) {
                     graphicsContext.strokeOval(e.getX(), e.getY(), strokeSize, strokeSize);
                 }

            }
        });

        rootGroup.getChildren().add(canvas);
        Scene scene = new Scene(rootGroup, DEFAULT_SCENE_WIDTH, DEFAULT_SCENE_HEIGHT);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void drawShapes(GraphicsContext gc) {
        gc.setFill(Color.GREEN);
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(5);
        gc.strokeLine(40, 10, 10, 40);
        gc.fillOval(10, 60, 30, 30);
        gc.strokeOval(60, 60, 30, 30);
        gc.fillRoundRect(110, 60, 30, 30, 10, 10);
        gc.strokeRoundRect(160, 60, 30, 30, 10, 10);
        gc.fillArc(10, 110, 30, 30, 45, 240, ArcType.OPEN);
        gc.fillArc(60, 110, 30, 30, 45, 240, ArcType.CHORD);
        gc.fillArc(110, 110, 30, 30, 45, 240, ArcType.ROUND);
        gc.strokeArc(10, 160, 30, 30, 45, 240, ArcType.OPEN);
        gc.strokeArc(60, 160, 30, 30, 45, 240, ArcType.CHORD);
        gc.strokeArc(110, 160, 30, 30, 45, 240, ArcType.ROUND);
        gc.fillPolygon(new double[]{10, 40, 10, 40},
                new double[]{210, 210, 240, 240}, 4);
        gc.strokePolygon(new double[]{60, 90, 60, 90},
                new double[]{210, 210, 240, 240}, 4);
        gc.strokePolyline(new double[]{110, 140, 110, 140},
                new double[]{210, 210, 240, 240}, 4);
    }
}

