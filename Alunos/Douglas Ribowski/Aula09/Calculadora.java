import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ErroDeCalculoException extends Exception {
    public ErroDeCalculoException(String mensagem) {
        super(mensagem);
    }
}

class CalculadoraLogica {
    public static double calcular(double num1, double num2, String operacao) throws ErroDeCalculoException {
        switch (operacao) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "×":
                return num1 * num2;
            case "÷":
                if (num2 == 0) {
                    throw new ErroDeCalculoException("Divisão por zero não é permitida.");
                }
                return num1 / num2;
            default:
                throw new ErroDeCalculoException("Operação inválida.");
        }
    }

    public static double converterEntrada(String texto) throws ErroDeCalculoException {
        try {
            return Double.parseDouble(texto.replace(",", "."));
        } catch (NumberFormatException e) {
            throw new ErroDeCalculoException("Digite apenas números válidos.");
        }
    }
}

public class Calculadora extends JFrame {
    private JTextField campoNumero1, campoNumero2, campoResultado;

    public Calculadora() {
        super("Calculadora");

        setLayout(new BorderLayout(10, 10));
        setSize(400, 280);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel painelCampos = new JPanel(new GridLayout(3, 2, 10, 10));
        painelCampos.setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 20));
        painelCampos.setBackground(new Color(250, 250, 250));

        campoNumero1 = new JTextField();
        campoNumero2 = new JTextField();
        campoResultado = new JTextField();
        campoResultado.setEditable(false);
        campoResultado.setBackground(Color.WHITE);

        painelCampos.add(new JLabel("Número 1:"));
        painelCampos.add(campoNumero1);
        painelCampos.add(new JLabel("Número 2:"));
        painelCampos.add(campoNumero2);
        painelCampos.add(new JLabel("Resultado:"));
        painelCampos.add(campoResultado);

        JPanel painelBotoes = new JPanel(new GridLayout(1, 4, 10, 10));
        painelBotoes.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

        String[] operacoes = {"+", "-", "×", "÷"};
        for (String op : operacoes) {
            JButton botao = new JButton(op);
            botao.setFont(new Font("Arial", Font.BOLD, 16));
            botao.setBackground(new Color(210, 230, 255));
            botao.addActionListener(new OperacaoListener(op));
            painelBotoes.add(botao);
        }

        add(painelCampos, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);
    }

    private class OperacaoListener implements ActionListener {
        private String operacao;

        public OperacaoListener(String operacao) {
            this.operacao = operacao;
        }

        public void actionPerformed(ActionEvent e) {
            try {
                double num1 = CalculadoraLogica.converterEntrada(campoNumero1.getText());
                double num2 = CalculadoraLogica.converterEntrada(campoNumero2.getText());
                double resultado = CalculadoraLogica.calcular(num1, num2, operacao);

                campoResultado.setText(String.format("%.2f", resultado));
            } catch (ErroDeCalculoException ex) {
                JOptionPane.showMessageDialog(
                        Calculadora.this,
                        ex.getMessage(),
                        "Erro",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Calculadora calc = new Calculadora();
            calc.setVisible(true);
        });
    }
}
