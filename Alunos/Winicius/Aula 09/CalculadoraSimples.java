import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// Classe principal e única
public class CalculadoraSimples extends JFrame {
    private JTextField campoNumero1;
    private JTextField campoNumero2;
    private JLabel resultadoLabel;

    public CalculadoraSimples() {
        setTitle("Calculadora Simples");
        setSize(400, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 2, 10, 10));

        campoNumero1 = new JTextField();
        campoNumero2 = new JTextField();
        resultadoLabel = new JLabel("Resultado: ");

        JButton btnSoma = new JButton("+");
        JButton btnSubtracao = new JButton("-");
        JButton btnMultiplicacao = new JButton("×");
        JButton btnDivisao = new JButton("÷");

        add(new JLabel("Número 1:"));
        add(campoNumero1);
        add(new JLabel("Número 2:"));
        add(campoNumero2);
        add(btnSoma);
        add(btnSubtracao);
        add(btnMultiplicacao);
        add(btnDivisao);
        add(resultadoLabel);

        btnSoma.addActionListener(e -> calcular('+'));
        btnSubtracao.addActionListener(e -> calcular('-'));
        btnMultiplicacao.addActionListener(e -> calcular('*'));
        btnDivisao.addActionListener(e -> calcular('/'));

        setVisible(true);
    }

    private void calcular(char operacao) {
        try {
            double num1 = parseInput(campoNumero1.getText());
            double num2 = parseInput(campoNumero2.getText());
            double resultado = realizarOperacao(num1, num2, operacao);
            resultadoLabel.setText("Resultado: " + resultado);
        } catch (EntradaInvalidaException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro inesperado: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private double parseInput(String texto) throws EntradaInvalidaException {
        try {
            return Double.parseDouble(texto);
        } catch (NumberFormatException e) {
            throw new EntradaInvalidaException("Entrada inválida: '" + texto + "'. Digite apenas números.");
        }
    }

    private double realizarOperacao(double a, double b, char op) throws EntradaInvalidaException {
        switch (op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0) {
                    throw new EntradaInvalidaException("Erro: Divisão por zero.");
                }
                return a / b;
            default:
                throw new EntradaInvalidaException("Operador inválido.");
        }
    }

    // Exceção personalizada como classe interna
    private static class EntradaInvalidaException extends Exception {
        public EntradaInvalidaException(String mensagem) {
            super(mensagem);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CalculadoraSimples());
    }
}
