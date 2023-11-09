package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

public class LeaderboardPage extends JFrame {
    private JTable leaderboardTable;

    public LeaderboardPage() {
        setTitle("Leaderboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        // Create a panel for better layout control
        JPanel panel = new JPanel(new BorderLayout());

        // Add a title label
        JLabel titleLabel = new JLabel("Leaderboard");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setOpaque(true);
        titleLabel.setBackground(new Color(193, 211, 169)); // Background color
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0)); // Padding
        panel.add(titleLabel, BorderLayout.NORTH);

        // Define column names
        String[] columnNames = { "Username", "Score" };

        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        // Fetch data from the 'Leaderboard' table in your database
        Vector<Vector<String>> data = fetchDataFromDatabase();

        if (data != null) {
            for (Vector<String> row : data) {
                model.addRow(row);
            }

            leaderboardTable = new JTable(model);
            leaderboardTable.setFillsViewportHeight(true);

            // Customize table appearance
            leaderboardTable.setFont(new Font("Arial", Font.PLAIN, 14));
            leaderboardTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));

            // Add padding and margin to the table
            leaderboardTable.setIntercellSpacing(new Dimension(10, 10)); // Margin
            leaderboardTable.setRowHeight(30); // Row height

            JScrollPane scrollPane = new JScrollPane(leaderboardTable);
            scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Margin
            panel.add(scrollPane, BorderLayout.CENTER);

            panel.setBackground(Color.WHITE); // Background color
            getContentPane().add(panel);

            setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "No leaderboard data found.");
            // Handle the case when no data is available.
        }
    }

    private Vector<Vector<String>> fetchDataFromDatabase() {
        // Define your database connection details
        String dbUrl = "jdbc:mysql://localhost:3306/brainiac?user=root&password=1234"; // Add your password
        Vector<Vector<String>> data = new Vector<>();

        try {
            // Connect to the database
            Connection connection = DriverManager.getConnection(dbUrl);
            Statement statement = connection.createStatement();

            // Execute a query to fetch data from the 'Leaderboard' table
            String query = "SELECT username, score FROM leaderboard ORDER BY score DESC";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Vector<String> row = new Vector<>();
                row.add(resultSet.getString("username")); // Correct the case of column name
                row.add(String.valueOf(resultSet.getInt("score")));
                data.add(row);
            }

            // Close the database connection
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LeaderboardPage();
        });
    }
}
