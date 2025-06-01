import java.awt.*;
import javax.swing.*;


class EntradaInvalidaException extends Exception {
    public EntradaInvalidaException(String mensagem) {
        super(mensagem);
    }
}

public class CalculadoraSwing extends JFrame {
    private final JTextField campoNumero1;
    private final JTextField campoNumero2;
    private final JLabel labelResultado;

    public CalculadoraSwing() {
        setTitle("Calculadora Simples");
        setSize(400, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 2, 10, 10));

     
        campoNumero1 = new JTextField();
        campoNumero2 = new JTextField();
        labelResultado = new JLabel("Resultado: ", JLabel.CENTER);

        JButton botaoSoma = new JButton("+");
        JButton botaoSubtrai = new JButton("-");
        JButton botaoMultiplica = new JButton("×");
        JButton botaoDivide = new JButton("÷");


        add(new JLabel("Número 1:"));
        add(campoNumero1);
        add(new JLabel("Número 2:"));
        add(campoNumero2);

        add(botaoSoma);
        add(botaoSubtrai);
        add(botaoMultiplica);
        add(botaoDivide);

        add(labelResultado);


        botaoSoma.addActionListener(e -> calcular('+'));
        botaoSubtrai.addActionListener(e -> calcular('-'));
        botaoMultiplica.addActionListener(e -> calcular('*'));
        botaoDivide.addActionListener(e -> calcular('/'));
    }

    private void calcular(char operacao) {
        try {
            double num1 = lerNumero(campoNumero1.getText());
            double num2 = lerNumero(campoNumero2.getText());

            double resultado;

            switch (operacao) {
                case '+' -> resultado = num1 + num2;
                case '-' -> resultado = num1 - num2;
                case '*' -> resultado = num1 * num2;
                case '/' -> {
                    if (num2 == 0) {
                        throw new EntradaInvalidaException("Erro: divisão por zero não é permitida.");
                    }
                    resultado = num1 / num2;
                }
                default -> throw new EntradaInvalidaException("Operação inválida.");
            }

            labelResultado.setText("Resultado: " + resultado);

        } catch (EntradaInvalidaException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, insira apenas números válidos.", "Erro de entrada", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Ocorreu um erro inesperado.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private double lerNumero(String texto) throws EntradaInvalidaException {
        try {
            return Double.parseDouble(texto);
        } catch (NumberFormatException e) {
            throw new EntradaInvalidaException("Entrada inválida: '" + texto + "' não é um número.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CalculadoraSwing calc = new CalculadoraSwing();
            calc.setVisible(true);
        });
    }
}
