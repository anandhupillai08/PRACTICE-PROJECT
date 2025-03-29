import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Calculator extends JFrame {
    private JTextField display;
    private JTextArea historyArea;

    public Calculator() {
        setTitle("Calculator with History");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        display = new JTextField();
        display.setEditable(false);
        add(display, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4));  // Adjusted to fit the new button

        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", "C", "=", "+",
            "Show History"  // New button for showing history
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.addActionListener(new ButtonClickListener());
            panel.add(button);
        }
        add(panel, BorderLayout.CENTER);

        historyArea = new JTextArea();
        historyArea.setEditable(false);
        add(new JScrollPane(historyArea), BorderLayout.SOUTH);
    }

    private class ButtonClickListener implements ActionListener {
        private String operator = "";
        private double firstNumber = 0;

        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if (command.charAt(0) >= '0' && command.charAt(0) <= '9') {
                display.setText(display.getText() + command);
            } else if (command.equals("C")) {
                display.setText("");
                operator = "";
            } else if (command.equals("=")) {
                if (!display.getText().isEmpty() && !operator.isEmpty()) {
                    double secondNumber = Double.parseDouble(display.getText());
                    double result = calculate(firstNumber, secondNumber, operator);
                    String expression = firstNumber + " " + operator + " " + secondNumber;
                    display.setText(String.valueOf(result));
                    saveHistory(expression, String.valueOf(result));
                }
            } else if (command.equals("Show History")) {
                loadHistory();  // Load and display history when button is clicked
            } else {
                if (!display.getText().isEmpty()) {
                    firstNumber = Double.parseDouble(display.getText());
                    operator = command;
                    display.setText("");
                }
            }
        }

        private double calculate(double num1, double num2, String operator) {
            switch (operator) {
                case "+":
                    return num1 + num2;
                case "-":
                    return num1 - num2;
                case "*":
                    return num1 * num2;
                case "/":
                    return num2 != 0 ? num1 / num2 : 0;
                default:
                    return 0;
            }
        }
    }

    private void saveHistory(String expression, String result) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/calculator_db", "Anandhu", "Student");
             PreparedStatement pstmt = conn.prepareStatement("INSERT INTO calculation_history (expression, result) VALUES (?, ?)")) {
            pstmt.setString(1, expression);
            pstmt.setString(2, result);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void loadHistory() {
        historyArea.setText("");
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/calculator_db", "Anandhu", "Student");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT expression, result, timestamp FROM calculation_history ORDER BY timestamp DESC")) {
            while (rs.next()) {
                historyArea.append(rs.getString("timestamp") + " - " + rs.getString("expression") + " = " + rs.getString("result") + "\n");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Calculator calculator = new Calculator();
            calculator.setVisible(true);
        });
    }
}
