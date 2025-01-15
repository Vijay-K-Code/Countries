/*
 * Name: Vijay Kannan
 * Date: January 14, 2025
 * Project: GeoGame
 * Description: This program is a graphical geography game that displays images of countries.
 *              Users can click buttons to cycle through different countries, review information,
 *              and take a quiz to guess the country's name.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main extends JFrame {
    // GUI components for the game interface
    private JLabel imageLabel;           // Displays the country image
    private JLabel outputLabel;          // Displays game messages and feedback
    private JButton nextButton;          // Button to move to the next country
    private JButton reviewButton;        // Button to review information about the current country
    private JButton quizButton;          // Button to start a quiz for the current country

    // Array to store 10 Country objects
    private Country[] countryArray = new Country[10];
    private int index = 0;               // Keeps track of the current country being displayed

    // Constructor to set up the GUI and load country data
    public Main() {
        // Setup window title and layout
        setTitle("GeoGame");
        setLayout(new BorderLayout());

        // Create and add the image label to the center of the window
        imageLabel = new JLabel();
        add(imageLabel, BorderLayout.CENTER);

        // Create and add the output label to the bottom of the window
        outputLabel = new JLabel("Welcome to GeoGame!", SwingConstants.CENTER);
        add(outputLabel, BorderLayout.SOUTH);

        // Create the button panel and add buttons to it
        JPanel buttonPanel = new JPanel();
        nextButton = new JButton("Next");
        reviewButton = new JButton("Review");
        quizButton = new JButton("Quiz");
        buttonPanel.add(nextButton);
        buttonPanel.add(reviewButton);
        buttonPanel.add(quizButton);
        add(buttonPanel, BorderLayout.NORTH);

        // Load the country data from a CSV file
        loadCountries();

        // Display the first country
        showCountry();

        // Set up button click actions
        nextButton.addActionListener(e -> nextButtonClick());
        reviewButton.addActionListener(e -> reviewButtonClick());
        quizButton.addActionListener(e -> quizButtonClick());

        // Configure the window size and close operation
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    // Method to load countries from a CSV file into the countryArray
    private void loadCountries() {
        try {
            // Open the CSV file using a Scanner
            Scanner scan = new Scanner(new File("countries-data.csv"));

            // Read each line from the file and create a Country object
            for (int i = 0; i < countryArray.length; i++) {
                String input = scan.nextLine();
                String[] data = input.split(",");

                // Create a new Country object and store it in the array
                countryArray[i] = new Country(data[0], data[1], data[2], data[3]);
            }

            // Close the Scanner
            scan.close();
        } catch (FileNotFoundException e) {
            // Handle the case where the file is not found
            System.out.println("Error: countries-data.csv file not found.");
        }
    }

    // Method to display the current country's image
    private void showCountry() {
        Country c = countryArray[index];
        String imageFile = c.getImageFile();

        // Load the image file and set it as the icon for the imageLabel
        ImageIcon icon = new ImageIcon("images/" + imageFile);
        imageLabel.setIcon(icon);
    }

    // Action to perform when the Next button is clicked
    private void nextButtonClick() {
        // Move to the next country in the array
        index++;
        if (index >= countryArray.length) {
            index = 0;  // Wrap around to the first country if the end is reached
        }
        outputLabel.setText("");  // Clear the output label
        showCountry();  // Show the next country's image
    }

    // Action to perform when the Review button is clicked
    private void reviewButtonClick() {
        // Get the current country and display its information
        Country c = countryArray[index];
        String text = c.toString();
        outputLabel.setText(text);
        System.out.println(text);  // Print the information to the console for debugging
    }

    // Action to perform when the Quiz button is clicked
    private void quizButtonClick() {
        outputLabel.setText("");  // Clear the output label
        Country c = countryArray[index];
        
        // Prompt the user to guess the country's name
        System.out.println("What country is this?");
        Scanner scan = new Scanner(System.in);
        String answer = scan.nextLine();

        // Check if the user's answer is correct
        if (answer.equalsIgnoreCase(c.getName())) {
            outputLabel.setText("Correct!");
        } else {
            outputLabel.setText("Incorrect! The correct answer is " + c.getName());
        }
    }

    // Main method to start the program
    public static void main(String[] args) {
        new Main();  // Create an instance of Main to start the game
    }
}
