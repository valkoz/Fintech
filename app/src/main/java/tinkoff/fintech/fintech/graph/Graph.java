package tinkoff.fintech.fintech.graph;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;

import java.util.List;

public class Graph extends View {
    private int plotColor = Color.RED;
    private float xCords[];
    private float yCords[];

    private int labelStep;
    private int gridStep;
    private Paint plotPaint;
    private Paint axesPaint;
    private Paint textPaint;
    private Paint gridPaint;

    private String xLabel;
    private String yLabel;

    private float canvasWidth;
    private float canvasHeight;
    float maxX;
    float minX;
    float maxY;
    float minY;

    public Graph(Context context) {
        super(context);
        init(null, 0);
    }

    public Graph(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public Graph(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    public void setItems(List<Point> items) {
        xCords = new float[items.size() * 2 - 2];
        yCords = new float[items.size() * 2 - 2];
        int i = 0;
        for (Point item : items) {
            xCords[i] = item.x;
            yCords[i] = item.y;
            i++;
            if (items.indexOf(item) != 0 && items.indexOf(item) != items.size() - 1) {
                xCords[i] = item.x;
                yCords[i] = item.y;
                i++;
            }
        }
        findMinMax();
    }

    private void findMinMax() {
        maxX = xCords[0];
        minX = xCords[0];
        for (float pt : xCords) {
            if (pt > maxX) maxX = pt;
            if (pt < minX) minX = pt;
        }
        maxY = yCords[0];
        minY = yCords[0];
        for (float pt : yCords) {
            if (pt > maxY) maxY = pt;
            if (pt < minY) minY = pt;
        }

    }

    private void init(AttributeSet attrs, int defStyle) {
        plotPaint = new Paint();
        plotPaint.setColor(plotColor);
        plotPaint.setStrokeWidth(3.0f);
        axesPaint = new Paint();
        axesPaint.setColor(Color.BLACK);
        axesPaint.setStrokeWidth(3.0f);
        textPaint = new Paint();
        textPaint.setColor(Color.BLACK);
        textPaint.setStrokeWidth(3.0f);
        textPaint.setTextSize(30f);
        gridPaint = new Paint();
        gridPaint.setColor(Color.GRAY);
        xLabel = "";
        yLabel = "";
        gridStep = 0;
        labelStep = 0;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvasWidth = canvas.getWidth();
        canvasHeight = canvas.getHeight();

        drawAxes(canvas);
        drawScalePoints(canvas);
        drawPlot(canvas);
        drawAxisLabels(canvas);
        if (gridStep != 0) {
            drawGrid(canvas);
        }
    }

    private void drawPlot(Canvas canvas) {
        float plotAlignX = 1.2f * canvasWidth / 10;
        float plotAlignY = 1.2f * canvasHeight / 10;
        float xLength = (maxX - minX) / (canvasWidth * 0.8f);
        float yLength = (maxY - minY) / (canvasHeight * 0.8f);

        for (int i = 0; i < xCords.length - 1; i++) {
            canvas.drawLine((xCords[i] - minX) / xLength + plotAlignX,
                    canvasHeight - (yCords[i] - minY) / yLength - plotAlignY,
                    (xCords[i + 1] - minX) / xLength + plotAlignX,
                    canvasHeight - (yCords[i + 1] - minY) / yLength - plotAlignY,
                    plotPaint);
        }
    }

    private void drawAxes(Canvas canvas) {
        float plotAlignX = canvasWidth / 10;
        float plotAlignY = canvasHeight / 10;

        canvas.drawLine(plotAlignX,
                canvasHeight / 10 - 0.2f * plotAlignY,
                plotAlignX,
                canvasHeight - plotAlignY,
                axesPaint);
        canvas.drawLine(plotAlignX,
                canvasHeight - plotAlignY - 1,
                canvasWidth - 0.8f * plotAlignX,
                canvasHeight - plotAlignY - 1,
                axesPaint);

    }

    private void drawScalePoints(Canvas canvas) {
        float plotAlignX = canvasWidth / 10;
        float plotAlignY = canvasHeight / 10;

        for (int i = 0; i < labelStep; i++) {
            canvas.drawLine(1.2f * plotAlignX + canvasWidth * 0.8f * i / (labelStep - 1),
                    canvasHeight - plotAlignY,
                    1.2f * plotAlignX + canvasWidth * 0.8f * i / (labelStep - 1),
                    canvasHeight - plotAlignY + 10,
                    axesPaint);
            String label = String.valueOf((int) (minX + i * (maxX - minX) / (labelStep - 1)));
            canvas.drawText(label, plotAlignX + canvasWidth * 0.8f * i / (labelStep - 1), 0.95f * canvasHeight, textPaint);
        }

        for (int i = 0; i < labelStep; i++) {
            String label = String.valueOf((int) (minY + i * (maxY - minY) / (labelStep - 1)));
            canvas.drawLine(plotAlignX - 10,
                    canvasHeight - 1.2f * plotAlignY - canvasHeight * 0.8f * i / (labelStep - 1),
                    plotAlignX,
                    canvasHeight - 1.2f * plotAlignY - canvasHeight * 0.8f * i / (labelStep - 1),
                    axesPaint);
            canvas.drawText(label, 0, canvasHeight - plotAlignY - canvasHeight * 0.8f * i / (labelStep - 1), textPaint);
        }
    }

    private void drawAxisLabels(Canvas canvas) {
        canvas.drawText(xLabel, 0.9f * canvasWidth, 0.9f * canvasHeight, textPaint);
        canvas.drawText(yLabel, 0.1f * canvasWidth, 0.1f * canvasHeight - textPaint.getTextSize(), textPaint);
    }

    private void drawGrid(Canvas canvas) {
        float plotAlignX = canvasWidth / 10;
        float plotAlignY = canvasHeight / 10;

        for (int i = 0; i < gridStep; i++) {
            canvas.drawLine(1.2f * plotAlignX + canvasWidth * 0.8f * i / (gridStep - 1),
                    0.8f * plotAlignY,
                    1.2f * plotAlignX + canvasWidth * 0.8f * i / (gridStep - 1),
                    canvasHeight - plotAlignY,
                    gridPaint);
        }

        for (int i = 0; i < gridStep; i++) {
            canvas.drawLine(canvasWidth - 0.8f * plotAlignX,
                    canvasHeight - 1.2f * plotAlignY - canvasHeight * 0.8f * i / (gridStep - 1),
                    plotAlignX,
                    canvasHeight - 1.2f * plotAlignY - canvasHeight * 0.8f * i / (gridStep - 1),
                    gridPaint);
        }
    }

    public void setPlotColor(int color) {
        plotColor = color;
        plotPaint.setColor(color);
    }

    public void setPlotPaint(Paint p) {
        plotPaint = p;
    }

    public void setAxesPaint(Paint p) {
        axesPaint = p;
    }

    public void setGridPaint(Paint p) {
        gridPaint = p;
    }

    public void setTextPaint(Paint p) {
        textPaint = p;
    }

    public Paint getPlotPaint() {
        return plotPaint;
    }

    public Paint getAxesPaint() {
        return axesPaint;
    }

    public Paint getTextPaint() {
        return textPaint;
    }

    public Paint getGridPaint() {
        return gridPaint;
    }

    public String getxLabel() {
        return xLabel;
    }

    public void setxLabel(String xLabel) {
        this.xLabel = xLabel;
    }

    public String getyLabel() {
        return yLabel;
    }

    public void setyLabel(String yLabel) {
        this.yLabel = yLabel;
    }

    public int getGridStep() {
        return gridStep;
    }

    public void setGridStep(int gridStep) {
        this.gridStep = gridStep;
    }

    public int getLabelStep() {
        return labelStep;
    }

    public void setLabelStep(int labelStep) {
        this.labelStep = labelStep;
    }
}
