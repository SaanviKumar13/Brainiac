package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CategorySelectorApp extends JFrame {
    private JComboBox<String> categoryComboBox;
    private JTextField apiUrlTextField;
    Category[] Categories = {
            new Category("General Knowledge", 9),
            new Category("Books", 10),
            new Category("Films", 11),
            new Category("Music", 12),
            new Category("Musicals and Theaters", 13),
            new Category("Television", 14),
            new Category("Video Games", 15),
            new Category("Board Games", 16),
            new Category("Science and Nature", 17),
            new Category("Computer", 18),
            new Category("Mathematics", 19),
            new Category("Mythology", 20),
            new Category("Sports", 21),
            new Category("Geography", 22),
            new Category("History", 23),
            new Category("Politics", 24),
            new Category("Celebrities", 26),
            new Category("Animals", 27),
            new Category("Vehicles", 28),
            new Category("Comics", 29),
            new Category("Gadgets", 30),
            new Category("Japanese Anime", 31),
            new Category("Cartoon and Animations", 32)
    };

    public CategorySelectorApp() {
        setTitle("Category Selector");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel categoryPanel = new JPanel();
        categoryComboBox = new JComboBox<>();
        for (Category category : Categories) {
            categoryComboBox.addItem(category.category);
        }
        categoryPanel.add(categoryComboBox);

        // ... Rest of the GUI setup (API URL text field, buttons, etc.)

        add(categoryPanel, BorderLayout.NORTH);

        // Register an ActionListener for the JComboBox to update the API URL
        categoryComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateApiUrl();
            }
        });
    }

    private void updateApiUrl() {
        // Get the selected category
        String selectedCategory = (String) categoryComboBox.getSelectedItem();

        // Find the corresponding value (e.g., "Books" -> 10) from the Categories object
        int selectedValue = -1;
        for (Category category : Categories) {
            if (category.category.equals(selectedCategory)) {
                selectedValue = category.value;
                break;
            }
        }

        if (selectedValue != -1) {
            // Update the API URL based on the selected category value
            String apiUrl = "https://opentdb.com/api.php?amount=10&category=" + selectedValue
                    + "amount=5&type=multiple";
            apiUrlTextField.setText(apiUrl);
        }
    }

    public static void main(String[] args) {
        new CategorySelectorApp();
    }
}
