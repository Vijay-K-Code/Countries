import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main extends JFrame {
    // GUI components
    private JLabel imageLabel;
    private JLabel outputLabel;
    private JButton nextButton;
    private JButton reviewButton;
    private JButton quizButton;

    // Array of 10 Country objects
    private Country[] countryArray = new Country[10];
    private int index = 0;

    // Constructor
    public Main() {
        // Setup GUI
        setTitle("GeoGame");
        setLayout(new BorderLayout());

        imageLabel = new JLabel();
        add(imageLabel, BorderLayout.CENTER);

        outputLabel = new JLabel("Welcome to GeoGame!", SwingConstants.CENTER);
        add(outputLabel, BorderLayout.SOUTH);

        JPanel buttonPanel = new JPanel();
        nextButton = new JButton("Next");
        reviewButton = new JButton("Review");
        quizButton = new JButton("Quiz");
        buttonPanel.add(nextButton);
        buttonPanel.add(reviewButton);
        buttonPanel.add(quizButton);
        add(buttonPanel, BorderLayout.NORTH);

        // Load the country data
        loadCountries();

        // Show the first country
        showCountry();

        // Button action listeners
        nextButton.addActionListener(e -> nextButtonClick());
        reviewButton.addActionListener(e -> reviewButtonClick());
        quizButton.addActionListener(e -> quizButtonClick());

        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    // Method to load countries from CSV
    private void loadCountries() {
        try {
            Scanner scan = new Scanner(new File("countries-data.csv"));
            for (int i = 0; i < countryArray.length; i++) {
                String input = scan.nextLine();
                String[] data = input.split(",");
                System.out.println("Read in " + data[0]);
                countryArray[i] = new Country(data[0], data[1], data[2], data[3]);
            }
            scan.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
    }

    // Method to show the current country
    private void showCountry() {
        Country c = countryArray[index];
        String imageFile = c.getImageFile();
        ImageIcon icon = new ImageIcon("images/" + imageFile);
        imageLabel.setIcon(icon);
    }

    // Method for Next button click
    private void nextButtonClick() {
        index++;
        if (index >= countryArray.length) {
            index = 0;
        }
        outputLabel.setText("");
        showCountry();
    }

    // Method for Review button click
    private void reviewButtonClick() {
        Country c = countryArray[index];
        String text = c.toString();
        outputLabel.setText(text);
        System.out.println(text);
    }

    // Method for Quiz button click
    private void quizButtonClick() {
        outputLabel.setText("");
        Country c = countryArray[index];
        System.out.println("What country is this?");
        Scanner scan = new Scanner(System.in);
        String answer = scan.nextLine();

        if (answer.equalsIgnoreCase(c.getName())) {
            outputLabel.setText("Correct!");
        } else {
            outputLabel.setText("Incorrect! The correct answer is " + c.getName());
        }
    }

    // Main method
    public static void main(String[] args) {
        new Main();
    }
}
