/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package route.swing.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.event.MouseInputListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.input.PanMouseInputListener;
import org.jxmapviewer.input.ZoomMouseWheelListenerCursor;
import org.jxmapviewer.painter.CompoundPainter;
import org.jxmapviewer.painter.Painter;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.DefaultWaypoint;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.TileFactoryInfo;
import org.jxmapviewer.viewer.WaypointPainter;
import route.swing.http.HttpClient;
import route.swing.http.HttpClientLlamaApi;
import route.swing.model.Horary;
import route.swing.model.Route;
import route.swing.model.jxmap.Region;
import route.swing.model.jxmap.RoutingData;
import route.swing.service.Haversine;
import route.swing.service.OpenStreetMapService;
import route.swing.service.RoutingService;
import route.swing.service.WheatherService;
import route.swing.util.JsonUtil;

/**
 *
 * @author Luis
 */
public class TimeTable extends javax.swing.JFrame {

    /**
     * Creates new form TimeTable
     */
    private List<RoutingData> routingData = new ArrayList<>();

    List<Route> routes;
    HttpClient client;
    JsonUtil jsonUtil;
    private OpenStreetMapService mapApi;
    private HttpClientLlamaApi llama;
    String[] header = {"Dia", "Fin", "Inicio"};
    DefaultTableModel model = new DefaultTableModel(header, 0);

    public TimeTable() throws IOException {
        initComponents();
        setResizable(false);
        setLocationRelativeTo(null);
        customTable();
        
        InicioField.setBorder(BorderFactory.createCompoundBorder(InicioField.getBorder(),
                BorderFactory.createEmptyBorder(5, 15, 5, 5)));

        DestinoField.setBorder(BorderFactory.createCompoundBorder(DestinoField.getBorder(),
                BorderFactory.createEmptyBorder(5, 15, 5, 5)));
        
        HoraryName.setBorder(BorderFactory.createCompoundBorder(DestinoField.getBorder(),
                BorderFactory.createEmptyBorder(5, 15, 5, 5)));

        llama = new HttpClientLlamaApi();
        mapApi = new OpenStreetMapService();

        client = new HttpClient();
        jsonUtil = new JsonUtil();

        routes = jsonUtil.fromJsonToList(
                client.get("/api/routes"),
                Route.class
        );

    }

    public void graphicRouteInPanel(Route route) {
        setupMap();
        drawWaypoints(route);
        drawRoute(route);
        addInteractivity();
        centerMap(route);
    }

    private void setupMap() {
        TileFactoryInfo info = new OSMTileFactoryInfo();
        DefaultTileFactory tileFactory = new DefaultTileFactory(info);
        jXMapViewer.setTileFactory(tileFactory);
        tileFactory.setThreadPoolSize(8);
    }

    private void drawWaypoints(Route route) {
        List<GeoPosition> waypointPositions = new ArrayList<>();
        waypointPositions.add(new GeoPosition(route.getStart().getLatitud(), route.getStart().getLongitud()));
        waypointPositions.add(new GeoPosition(route.getEnd().getLatitud(), route.getEnd().getLongitud()));

        List<DefaultWaypoint> waypoints = new ArrayList<>();
        for (GeoPosition position : waypointPositions) {
            waypoints.add(new DefaultWaypoint(position));
        }

        WaypointPainter<DefaultWaypoint> waypointPainter = new WaypointPainter<>();
        waypointPainter.setWaypoints(new HashSet<>(waypoints));

        List<Painter<JXMapViewer>> painters = new ArrayList<>();
        painters.add(waypointPainter);

        CompoundPainter<JXMapViewer> painter = new CompoundPainter<>(painters);
        jXMapViewer.setOverlayPainter(painter);
    }

    private void drawRoute(Route route) {
        routingData = RoutingService.getInstance().routing(
                route.getStart().getLatitud(), route.getStart().getLongitud(),
                route.getEnd().getLatitud(), route.getEnd().getLongitud()
        );
        jXMapViewer.setRoutingData(routingData);
    }

    private void addInteractivity() {
        MouseInputListener mia = new PanMouseInputListener(jXMapViewer);
        jXMapViewer.addMouseListener(mia);
        jXMapViewer.addMouseMotionListener(mia);
        jXMapViewer.addMouseWheelListener(new ZoomMouseWheelListenerCursor(jXMapViewer));
    }

    private void centerMap(Route route) {
        Region result = middlePoint(route.getStart(), route.getEnd());
        jXMapViewer.setZoom(8);
        jXMapViewer.setAddressLocation(new GeoPosition(result.getLatitud(), result.getLongitud()));
    }

    private void drawSingleWaypoint(double lat, double lon) {
        List<GeoPosition> waypointPositions = new ArrayList<>();
        waypointPositions.add(new GeoPosition(lat, lon));

        List<DefaultWaypoint> waypoints = new ArrayList<>();
        for (GeoPosition position : waypointPositions) {
            waypoints.add(new DefaultWaypoint(position));
        }

        WaypointPainter<DefaultWaypoint> waypointPainter = new WaypointPainter<>();
        waypointPainter.setWaypoints(new HashSet<>(waypoints));

        List<Painter<JXMapViewer>> painters = new ArrayList<>();
        painters.add(waypointPainter);

        CompoundPainter<JXMapViewer> painter = new CompoundPainter<>(painters);
        jXMapViewer.setOverlayPainter(painter);

        jXMapViewer.setZoom(8);
        jXMapViewer.setAddressLocation(new GeoPosition(lat, lon));
    }

    private Region middlePoint(Region zone1, Region zone2) {
        double latMedia = (zone1.getLatitud() + zone2.getLatitud()) / 2;
        double lonMedia = (zone1.getLongitud() + zone2.getLongitud()) / 2;
        return new Region("medio", latMedia, lonMedia);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        HoraryName = new javax.swing.JTextField();
        InicioField = new javax.swing.JTextField();
        DestinoField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        datePicker1 = new com.github.lgooddatepicker.components.DatePicker();
        timePicker1 = new com.github.lgooddatepicker.components.TimePicker();
        jScrollPane1 = new javax.swing.JScrollPane();
        TableTIme = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        GuardarHorario = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jXMapViewer = new route.swing.model.jxmap.JXMapViewerCustom();
        jLabel12 = new javax.swing.JLabel();
        RouteDistanceLabel = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        RouteWheaterLabel = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        RouteTimeLabel = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        transporte = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        paradas = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("");
        jLabel1.setFont(new java.awt.Font("Symbols Nerd Font Mono", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel1MousePressed(evt);
            }
        });
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 50, 50));

        jLabel3.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("Horarios");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, 160, 50));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1050, 50));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("Calcular ruta");
        jLabel2.setFont(new java.awt.Font("Arial", 1, 30)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 300, 50));

        HoraryName.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        HoraryName.setText("Nombre de horario");
        HoraryName.setBackground(new java.awt.Color(255, 255, 255));
        HoraryName.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        HoraryName.setForeground(new java.awt.Color(204, 204, 204));
        HoraryName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                HoraryNameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                HoraryNameFocusLost(evt);
            }
        });
        jPanel1.add(HoraryName, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 440, 440, 40));

        InicioField.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        InicioField.setText("Inicio");
        InicioField.setBackground(new java.awt.Color(255, 255, 255));
        InicioField.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        InicioField.setForeground(new java.awt.Color(204, 204, 204));
        InicioField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                InicioFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                InicioFieldFocusLost(evt);
            }
        });
        jPanel1.add(InicioField, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 180, 50));

        DestinoField.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        DestinoField.setText("Destino");
        DestinoField.setBackground(new java.awt.Color(255, 255, 255));
        DestinoField.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        DestinoField.setForeground(new java.awt.Color(204, 204, 204));
        DestinoField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                DestinoFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                DestinoFieldFocusLost(evt);
            }
        });
        jPanel1.add(DestinoField, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, 180, 50));

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Buscar");
        jLabel4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel4MousePressed(evt);
            }
        });
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
        jPanel1.add(datePicker1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 130, 190, 50));
        jPanel1.add(timePicker1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 200, 190, 50));

        TableTIme.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        TableTIme.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Dia", "Fin", "Inicio"
            }
        ));
        TableTIme.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setViewportView(TableTIme);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, 380, 280));

        jPanel4.setBackground(new java.awt.Color(85, 110, 230));

        GuardarHorario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        GuardarHorario.setText("Guardar");
        GuardarHorario.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        GuardarHorario.setForeground(new java.awt.Color(255, 255, 255));
        GuardarHorario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                GuardarHorarioMousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(GuardarHorario, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(GuardarHorario, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 440, 140, 40));

        jPanel3.setBackground(new java.awt.Color(85, 110, 230));

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Buscar");
        jLabel5.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel5MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, 380, 50));

        javax.swing.GroupLayout jXMapViewerLayout = new javax.swing.GroupLayout(jXMapViewer);
        jXMapViewer.setLayout(jXMapViewerLayout);
        jXMapViewerLayout.setHorizontalGroup(
            jXMapViewerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 590, Short.MAX_VALUE)
        );
        jXMapViewerLayout.setVerticalGroup(
            jXMapViewerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 360, Short.MAX_VALUE)
        );

        jPanel1.add(jXMapViewer, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 70, 590, 360));

        jLabel12.setText("Distancia Total");
        jLabel12.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 500, -1, -1));

        RouteDistanceLabel.setText("Distance");
        RouteDistanceLabel.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        RouteDistanceLabel.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(RouteDistanceLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 530, 130, 20));

        jLabel7.setText("Clima");
        jLabel7.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 580, -1, -1));

        RouteWheaterLabel.setText("Wheater");
        RouteWheaterLabel.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        RouteWheaterLabel.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(RouteWheaterLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 610, 130, 20));

        jLabel6.setText("Tiempo Estimado");
        jLabel6.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 500, -1, -1));

        RouteTimeLabel.setText("Time");
        RouteTimeLabel.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        RouteTimeLabel.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(RouteTimeLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 530, 130, 20));

        jLabel9.setText("Transporte");
        jLabel9.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 580, -1, -1));

        transporte.setText("Transport");
        transporte.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        transporte.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(transporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 610, 130, 20));

        jLabel8.setText("Paradas");
        jLabel8.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 500, -1, -1));

        paradas.setText("P");
        paradas.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        paradas.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(paradas, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 530, 130, 20));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 630, 30, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void InicioFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_InicioFieldFocusGained
        // TODO add your handling code here:
        if (InicioField.getText().equals("Inicio")) {
            InicioField.setText("");
            InicioField.setForeground(Color.black);
        }
    }//GEN-LAST:event_InicioFieldFocusGained

    private void InicioFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_InicioFieldFocusLost
        // TODO add your handling code here:
        if (InicioField.getText().equals("")) {
            InicioField.setText("Inicio");
            InicioField.setForeground(Color.decode("#CCCCCC"));
        }
    }//GEN-LAST:event_InicioFieldFocusLost

    private void DestinoFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_DestinoFieldFocusGained
        // TODO add your handling code here:
        if (DestinoField.getText().equals("Destino")) {
            DestinoField.setText("");
            DestinoField.setForeground(Color.black);
        }
    }//GEN-LAST:event_DestinoFieldFocusGained

    private void DestinoFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_DestinoFieldFocusLost
        // TODO add your handling code here:
        if (DestinoField.getText().equals("")) {
            DestinoField.setText("Destino");
            DestinoField.setForeground(Color.decode("#CCCCCC"));
        }
    }//GEN-LAST:event_DestinoFieldFocusLost

    private void jLabel4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MousePressed

    }//GEN-LAST:event_jLabel4MousePressed

    private void HoraryNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_HoraryNameFocusGained
        // TODO add your handling code here:
        if (HoraryName.getText().equals("Nombre de horario")) {
            HoraryName.setText("");
            HoraryName.setForeground(Color.black);
        }
    }//GEN-LAST:event_HoraryNameFocusGained

    private void HoraryNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_HoraryNameFocusLost
        // TODO add your handling code here:
        if (HoraryName.getText().equals("")) {
            HoraryName.setText("Nombre de horario");
            HoraryName.setForeground(Color.decode("#CCCCCC"));
        }
    }//GEN-LAST:event_HoraryNameFocusLost

    private void jLabel5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MousePressed

        try {
            Region start = mapApi.getCoordinatesByAddress(InicioField.getText());
            Region end = mapApi.getCoordinatesByAddress(DestinoField.getText());

            Route route = new Route(start, end);

            graphicRouteInPanel(route);
            dataSet(route);

            String routesString = "";

//            int count = 0;
//            for (Route r : routes) {
//                routesString += r.toString();
//                count++;
//                if (count > 200) {
//                    break;
//                }
//            }
            
            for (int i = 150; i < 200; i++) {
                routesString += routes.get(i).toString();
            }
            System.out.println(routesString);

            String recommendation = (llama.sendPrompt("Según esta ruta de Perú (" + InicioField.getText() + " hacia " + DestinoField.getText() + "), ¿qué ruta pública recomiendas? Por favor, devuelve solo el código de la ruta, solo el codigo nada mas. Estas son las opciones de rutas: " + routesString));

            System.out.println(recommendation);
            if (recommendation.length() != 4 && !recommendation.matches("\\d+")) {
                System.out.println("Prompt error: " + recommendation);
                return;
            }

            Long idRecom = null;
            for (Route r : routes) {
                if (recommendation.equals(r.getName())) {
                    idRecom = r.getId();
                }
            }

            List<Horary> horaries = jsonUtil.fromJsonToList(
                    client.get("/api/horary/route/" + idRecom), Horary.class);

            if (horaries.isEmpty()) {
                System.out.println("Lista vacia");
                return;
            }

            for (Horary horary : horaries) {
                Object[] row = {horary.getDia(), horary.getHoraInicio(), horary.getHoraFin()};
                model.addRow(row);
            }

            TableTIme.setModel(model);
            transporte.setText("C");
            paradas.setText("142 aprox");
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(TimeTable.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TimeTable.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_jLabel5MousePressed

    private void GuardarHorarioMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GuardarHorarioMousePressed
        // TODO add your handling code here:


        DefaultTableModel unsetModel = (DefaultTableModel) TableTIme.getModel();

        unsetModel.setRowCount(0);
        TableTIme.setModel(unsetModel);
    }//GEN-LAST:event_GuardarHorarioMousePressed

    private void jLabel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MousePressed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jLabel1MousePressed

    public void dataSet(Route route) throws IOException {
        double distance = Haversine.round(Haversine.calculateDistance(
                route.getStart().getLatitud(),
                route.getStart().getLongitud(),
                route.getEnd().getLatitud(),
                route.getEnd().getLongitud()
        ));

        RouteDistanceLabel.setText(
                String.valueOf(distance)
                + " km"
        );

        RouteTimeLabel.setText(Haversine.calcTime(distance, 20));

        RouteWheaterLabel.setText(String.valueOf(new WheatherService().getActualTemp()) + " °");

//        RouteDetalleRoute.setText(
//                "<html>"
//                + HttpClientLlamaApi.sendPrompt("Simula una descripcion generica muy corta sobre esta ruta de Perú sobre su dificultad teniendo en cuenta el trafico general de Perú y si se puede llegar a tener un incoveniente o algo por el estilo, la ruta que te doy se compone de un codigo (no hace falta que menciones el codigo ni los lugares), un lugar de inicio y un lugar de fin, dame solo la descripcion, esta es la ruta: "
//                        + route.toString()).replaceAll("^\"|\"$", "")
//                + "</html>"
//        );
    }

    private void customTable() {
        // Eliminar bordes del encabezado y las celdas
        TableTIme.setShowGrid(false);
        TableTIme.setIntercellSpacing(new Dimension(0, 0));

        // Renderizador de celdas personalizado
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {
                Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                // Alternar colores de filas
                if (row % 2 == 0) {
                    cell.setBackground(new Color(220, 220, 220)); // Color gris claro
                } else {
                    cell.setBackground(Color.WHITE);
                }

                // Cambiar fuente
                cell.setFont(new Font("Arial", Font.PLAIN, 14));

                // Eliminar bordes de la celda
                ((JComponent) cell).setBorder(null);

                return cell;
            }
        };

        // Aplicar el renderizador personalizado a todas las columnas
        for (int i = 0; i < TableTIme.getColumnCount(); i++) {
            TableTIme.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
        }

        // Personalizar el encabezado de la tabla
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {
                JLabel headerLabel = new JLabel(value.toString(), JLabel.LEFT);
                headerLabel.setFont(new Font("Arial", Font.PLAIN, 14));
                headerLabel.setOpaque(true);
                headerLabel.setBackground(Color.WHITE);
                headerLabel.setBorder(null);
                return headerLabel;
            }
        };

        TableTIme.getTableHeader().setDefaultRenderer(headerRenderer);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TimeTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TimeTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TimeTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TimeTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new TimeTable().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(TimeTable.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField DestinoField;
    private javax.swing.JLabel GuardarHorario;
    private javax.swing.JTextField HoraryName;
    private javax.swing.JTextField InicioField;
    private javax.swing.JLabel RouteDistanceLabel;
    private javax.swing.JLabel RouteTimeLabel;
    private javax.swing.JLabel RouteWheaterLabel;
    private javax.swing.JTable TableTIme;
    private com.github.lgooddatepicker.components.DatePicker datePicker1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private route.swing.model.jxmap.JXMapViewerCustom jXMapViewer;
    private javax.swing.JLabel paradas;
    private com.github.lgooddatepicker.components.TimePicker timePicker1;
    private javax.swing.JLabel transporte;
    // End of variables declaration//GEN-END:variables
}
