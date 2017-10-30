package tinkoff.fintech.fintech;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import tinkoff.fintech.fintech.graph.Graph;


public class GraphActivity extends Activity {

    public static void start(Activity activity) {
        activity.startActivity(new Intent(activity, GraphActivity.class));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_graph);
        fullTest();

    }

    private void fullTest() {
        List<Point> items = new ArrayList<>();
        items.add(new Point(-300, 200));
        items.add(new Point(-300, -300));
        items.add(new Point(-100, 0));
        items.add(new Point(0, 0));
        items.add(new Point(50, 50));
        items.add(new Point(500, 50));
        final Graph graph = findViewById(R.id.graphView);
        graph.setPlotColor(Color.RED);
        graph.setItems(items);

        Paint p1 = new Paint();
        p1.setColor(Color.BLUE);
        p1.setStrokeWidth(5);
        graph.setPlotPaint(p1);

        graph.setxLabel("One");
        graph.setyLabel("Two");
        graph.setGridStep(20);
        graph.setLabelStep(5);
    }

    private void negativeOnlyTest() {
        List<Point> items = new ArrayList<>();
        items.add(new Point(0, 0));
        items.add(new Point(-50, -50));
        items.add(new Point(-300, -200));
        final Graph graph = findViewById(R.id.graphView);
        Paint p = new Paint();
        p.setColor(Color.RED);

        graph.setPlotColor(Color.BLUE);
        graph.setPlotPaint(p);
        graph.setItems(items);
    }

    private void positiveOnlyTest() {
        List<Point> items = new ArrayList<>();
        items.add(new Point(0, 0));
        items.add(new Point(50, 50));
        items.add(new Point(100, 200));
        final Graph graph = findViewById(R.id.graphView);
        graph.setPlotColor(Color.RED);
        graph.setItems(items);
    }

    private void scaleOutTest() {
        List<Point> items = new ArrayList<>();
        items.add(new Point(-10, 2));
        items.add(new Point(2, -3));
        items.add(new Point(50, 1));
        final Graph graph = findViewById(R.id.graphView);
        graph.setPlotColor(Color.RED);
        graph.setItems(items);
    }

    private void scaleInTest() {
        List<Point> items = new ArrayList<>();
        items.add(new Point(3, 6));
        items.add(new Point(2, 2));
        items.add(new Point(1, 1));
        final Graph graph = findViewById(R.id.graphView);
        graph.setPlotColor(Color.RED);
        graph.setItems(items);
    }
}
