package com.routes.main.model.jxmap;

import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.painter.Painter;
import org.jxmapviewer.viewer.GeoPosition;

import java.awt.*;
import java.awt.geom.Path2D;
import java.util.List;

public class RoutePainter implements Painter<JXMapViewer> {
    private final List<GeoPosition> track;

    public RoutePainter(List<GeoPosition> track) {
        this.track = track;
    }

    @Override
    public void paint(Graphics2D g, JXMapViewer map, int width, int height) {
        g = (Graphics2D) g.create();

        // convert geo-coordinates to world bitmap
        List<Point> trackPoints = (List<Point>) map.getTileFactory().geoToPixel((GeoPosition) track, map.getZoom());

        g.setColor(Color.RED);
        g.setStroke(new BasicStroke(2));

        Path2D path = new Path2D.Double();
        boolean first = true;

        for (Point point : trackPoints) {
            if (first) {
                path.moveTo(point.getX(), point.getY());
                first = false;
            } else {
                path.lineTo(point.getX(), point.getY());
            }
        }

        g.draw(path);
        g.dispose();
    }
}
