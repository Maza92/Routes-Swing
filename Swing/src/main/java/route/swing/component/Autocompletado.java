package route.swing.component;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import org.json.JSONArray;
import org.json.JSONObject;

public class Autocompletado extends JFrame {
    private JTextField searchField;
    private JList<String> suggestionsList;
    private DefaultListModel<String> listModel;

    public Autocompletado() {
        setTitle("Autocompletado con OpenStreetMap");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        searchField = new JTextField(20);
        searchField.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                updateSuggestions();
            }

            public void removeUpdate(DocumentEvent e) {
                updateSuggestions();
            }

            public void changedUpdate(DocumentEvent e) {
                updateSuggestions();
            }
        });

        listModel = new DefaultListModel<>();
        suggestionsList = new JList<>(listModel);
        suggestionsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        suggestionsList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int index = suggestionsList.locationToIndex(e.getPoint());
                    if (index >= 0) {
                        String selectedSuggestion = listModel.getElementAt(index);
                        searchField.setText(selectedSuggestion);
                    }
                }
            }
        });

        add(searchField, BorderLayout.NORTH);
        add(new JScrollPane(suggestionsList), BorderLayout.CENTER);

        setSize(400, 300);
        setVisible(true);
    }

    private void updateSuggestions() {
        String query = searchField.getText();
        if (query.isEmpty()) {
            return;
        }

        SwingWorker<Void, String> worker = new SwingWorker<Void, String>() {
            @Override
            protected Void doInBackground() throws Exception {
                String encodedQuery = URLEncoder.encode(query, StandardCharsets.UTF_8.toString());
                String url = "https://nominatim.openstreetmap.org/search?q=" + encodedQuery + "&format=json&addressdetails=1&countrycodes=PE";
                HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
                conn.setRequestMethod("GET");
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                JSONArray jsonArray = new JSONArray(response.toString());
                listModel.clear();

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String displayName = jsonObject.getString("display_name");
                    publish(displayName);
                }

                return null;
            }

            @Override
            protected void process(java.util.List<String> chunks) {
                for (String suggestion : chunks) {
                    listModel.addElement(suggestion);
                }
            }
        };
        worker.execute();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Autocompletado());
    }
}
