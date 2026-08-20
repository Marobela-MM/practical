/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author 23031424
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HedgeYourBet extends JFrame implements ActionListener {

    // Questions
    private String[] questions = {
        "1. What animal says 'Moo'?",
        "2. What color is the sky on a clear day?",
        "3. How many days are in a week?",
        "4. Which of these is a fruit?",
        "5. Which animal can fly?"
    };

    // Answer choices
    private String[][] options = {
        {"Dog", "Cow", "Cat"},
        {"Green", "Blue", "Red"},
        {"5", "6", "7"},
        {"Apple", "Carrot", "Bread"},
        {"Bird", "Fish", "Dog"}
    };

    // Correct answers:
    // 0 = first answer
    // 1 = second answer
    // 2 = third answer
    private int[] correctAnswers = {
        1,  // Cow
        1,  // Blue
        2,  // 7
        0,  // Apple
        0   // Bird
    };

    private int currentQuestion = 0;
    private int score = 0;

    private JLabel questionLabel;
    private JLabel questionNumberLabel;

    private JCheckBox option1;
    private JCheckBox option2;
    private JCheckBox option3;

    private JButton submitButton;

    public HedgeYourBet() {

        setTitle("Hedge Your Bet");
        setSize(600, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        );

        // Top section
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(2, 1));

        questionNumberLabel = new JLabel();
        questionNumberLabel.setFont(
                new Font("Arial", Font.BOLD, 16)
        );

        questionLabel = new JLabel();
        questionLabel.setFont(
                new Font("Arial", Font.BOLD, 18)
        );

        topPanel.add(questionNumberLabel);
        topPanel.add(questionLabel);

        mainPanel.add(topPanel, BorderLayout.NORTH);

        // Checkboxes
        option1 = new JCheckBox();
        option2 = new JCheckBox();
        option3 = new JCheckBox();

        option1.setFont(new Font("Arial", Font.PLAIN, 16));
        option2.setFont(new Font("Arial", Font.PLAIN, 16));
        option3.setFont(new Font("Arial", Font.PLAIN, 16));

        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(
                new GridLayout(3, 1, 5, 5)
        );

        optionsPanel.add(option1);
        optionsPanel.add(option2);
        optionsPanel.add(option3);

        mainPanel.add(optionsPanel, BorderLayout.CENTER);

        // Submit button
        submitButton = new JButton("Submit Answer");
        submitButton.addActionListener(this);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(submitButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);

        displayQuestion();

        setVisible(true);
    }

    private void displayQuestion() {

        questionNumberLabel.setText(
                "Question " + (currentQuestion + 1) + " of 5"
        );

        questionLabel.setText(
                questions[currentQuestion]
        );

        option1.setText(
                options[currentQuestion][0]
        );

        option2.setText(
                options[currentQuestion][1]
        );

        option3.setText(
                options[currentQuestion][2]
        );

        // Clear previous selections
        option1.setSelected(false);
        option2.setSelected(false);
        option3.setSelected(false);
    }

    private void checkAnswer() {

        int numberSelected = 0;

        if (option1.isSelected()) {
            numberSelected++;
        }

        if (option2.isSelected()) {
            numberSelected++;
        }

        if (option3.isSelected()) {
            numberSelected++;
        }

        boolean correctAnswerSelected = false;

        if (correctAnswers[currentQuestion] == 0
                && option1.isSelected()) {
            correctAnswerSelected = true;
        }

        if (correctAnswers[currentQuestion] == 1
                && option2.isSelected()) {
            correctAnswerSelected = true;
        }

        if (correctAnswers[currentQuestion] == 2
                && option3.isSelected()) {
            correctAnswerSelected = true;
        }

        // Scoring
        if (correctAnswerSelected && numberSelected == 1) {
            score += 5;
        }
        else if (correctAnswerSelected && numberSelected == 3) {
            score += 1;
        }

        currentQuestion++;

        if (currentQuestion < 5) {
            displayQuestion();
        }
        else {
            showFinalResult();
        }
    }

    private void showFinalResult() {

        String message;

        if (score > 21) {
            message = "Fantastic!";
        }
        else if (score > 15) {
            message = "Very good";
        }
        else {
            message = "OK";
        }

        JOptionPane.showMessageDialog(
                this,
                "Your final score is " + score + " out of 25.\n\n"
                        + message,
                "Quiz Complete",
                JOptionPane.INFORMATION_MESSAGE
        );

        submitButton.setEnabled(false);
        option1.setEnabled(false);
        option2.setEnabled(false);
        option3.setEnabled(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == submitButton) {
            checkAnswer();
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new HedgeYourBet();
        });
    }
}
