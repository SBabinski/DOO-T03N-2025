import javax.swing.*;
import java.awt.*;

public class Calculadora extends JFrame {
    private JTextField campoNumero1;
    private JTextField campoNumero2;
    private JLabel resultadoLabel;

    public Calculadora() {
        setTitle("Calculadora Simples");
        setSize(400, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(5, 2, 10, 10));

        campoNumero1 = new JTextField();
        campoNumero2 = new JTextField();
        resultadoLabel = new JLabel("Resultado: ");
        JButton botaoSomar = new JButton("+");
        JButton botaoSubtrair = new JButton("-");
        JButton botaoMultiplicar = new JButton("×");
        JButton botaoDividir = new JButton("÷");

        painel.add(new JLabel("Número 1:"));
        painel.add(campoNumero1);
        painel.add(new JLabel("Número 2:"));
        painel.add(campoNumero2);
        painel.add(botaoSomar);
        painel.add(botaoSubtrair);
        painel.add(botaoMultiplicar);
        painel.add(botaoDividir);
        painel.add(resultadoLabel);
        
        add(painel);

        botaoSomar.addActionListener(e -> realizarOperacao("+"));
        botaoSubtrair.addActionListener(e -> realizarOperacao("-"));
        botaoMultiplicar.addActionListener(e -> realizarOperacao("*"));
        botaoDividir.addActionListener(e -> realizarOperacao("/"));
    }

    private void realizarOperacao(String operacao) {
        try {
            double num1 = parseInput(campoNumero1.getText());
            double num2 = parseInput(campoNumero2.getText());

            double resultado;

            switch (operacao) {
                case "+":
                    resultado = num1 + num2;
                    break;
                case "-":
                    resultado = num1 - num2;
                    break;
                case "*":
                    resultado = num1 * num2;
                    break;
                case "/":
                    if (num2 == 0) {
                        throw new InvalidInputException("Divisão por zero não é permitida.");
                    }
                    resultado = num1 / num2;
                    break;
                default:
                    throw new InvalidInputException("Operação desconhecida.");
            }

            resultadoLabel.setText("Resultado: " + resultado);
        } catch (InvalidInputException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro inesperado: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private double parseInput(String input) throws InvalidInputException {
        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException ex) {
            throw new InvalidInputException("Entrada inválida. Digite apenas números.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Calculadora calculadora = new Calculadora();
            calculadora.setVisible(true);
        });
    }
}
