package Alunos.Adriel.Aula09;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class InvalidExpressionException extends Exception {
    public InvalidExpressionException(String message) {
        super(message);
    }
}

public class SmartphoneCalculator extends JFrame {
    private JTextField display;
    private String currentInput = "";

    public SmartphoneCalculator() {
        configureFrame();
        initDisplay();
        addButtons();
        styleComponents();
    }

    private void configureFrame() {
        setTitle("Calculadora");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(350, 500);
        setLayout(new BorderLayout(5, 5));
    }

    private void initDisplay() {
        display = new JTextField();
        display.setFont(new Font("Arial", Font.PLAIN, 40));
        display.setEditable(false);
        display.setHorizontalAlignment(JTextField.RIGHT);
        add(display, BorderLayout.NORTH);
    }

    private void addButtons() {
        JPanel buttonPanel = new JPanel(new GridLayout(5, 4, 5, 5));
        String[] buttons = {
            "C", "(", ")", "/",
            "7", "8", "9", "*",
            "4", "5", "6", "-",
            "1", "2", "3", "+",
            "0", ".", "=", "DEL" // Alterado para "DEL"
        };

        for (String text : buttons) {
            buttonPanel.add(createButton(text));
        }
        add(buttonPanel, BorderLayout.CENTER);
    }

    private JButton createButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Arial", Font.PLAIN, 24));
        btn.setFocusPainted(false);
        
        // Configuração de cores
        if (text.matches("[0-9.]")) {
            btn.setBackground(new Color(240, 240, 240));
        } else if (text.equals("=")) {
            btn.setBackground(new Color(255, 159, 0));
            btn.setForeground(Color.WHITE);
        } else {
            btn.setBackground(new Color(218, 218, 218));
        }

        btn.addActionListener(new ButtonClickListener());
        return btn;
    }

    private void styleComponents() {
        getContentPane().setBackground(Color.DARK_GRAY);
        display.setBackground(Color.WHITE);
    }

    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = ((JButton) e.getSource()).getText();

            try {
                handleCommand(command);
                display.setText(currentInput);
            } catch (InvalidExpressionException ex) {
                display.setText("Erro: " + ex.getMessage());
                currentInput = "";
            }
        }

        private void handleCommand(String command) throws InvalidExpressionException {
            switch (command) {
                case "=":
                    calculateResult();
                    break;
                case "C":
                    currentInput = "";
                    break;
                case "DEL":
                    deleteLastChar();
                    break;
                default:
                    currentInput += command;
            }
        }

        private void deleteLastChar() {
            if (!currentInput.isEmpty()) {
                currentInput = currentInput.substring(0, currentInput.length() - 1);
            }
        }

        private void calculateResult() throws InvalidExpressionException {
            try {
                double result = evaluateExpression(currentInput);
                currentInput = String.format("%.2f", result).replace(",", ".");
            } catch (NumberFormatException | ArithmeticException ex) {
                throw new InvalidExpressionException("Operação inválida");
            }
        }

        private double evaluateExpression(String expression) {
            return new ArithmeticParser().parse(expression);
        }
    }

    // Classe para cálculo manual das operações
    private static class ArithmeticParser {
        public double parse(String expression) {
            expression = expression.replaceAll("\\s+", "");
            return evaluateAddSubtract(expression);
        }

        private double evaluateAddSubtract(String expr) {
            String[] parts = expr.split("(?=[+-])");
            double result = evaluateMultiplyDivide(parts[0]);
            for (int i = 1; i < parts.length; i++) {
                result += evaluateMultiplyDivide(parts[i]);
            }
            return result;
        }

        private double evaluateMultiplyDivide(String expr) {
            String[] parts = expr.split("(?=[*/])");
            double result = Double.parseDouble(parts[0]);
            for (int i = 1; i < parts.length; i++) {
                if (parts[i].startsWith("*")) {
                    result *= Double.parseDouble(parts[i].substring(1));
                } else if (parts[i].startsWith("/")) {
                    double divisor = Double.parseDouble(parts[i].substring(1));
                    if (divisor == 0) throw new ArithmeticException("Divisão por zero");
                    result /= divisor;
                }
            }
            return result;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SmartphoneCalculator calc = new SmartphoneCalculator();
            calc.setVisible(true);
        });
    }
}