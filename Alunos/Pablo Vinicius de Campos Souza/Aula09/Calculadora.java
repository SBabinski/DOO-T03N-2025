package Aula09;

import javax.swing.*;
import java.awt.*;

/**
 * Calculadora simples com interface gráfica usando Java Swing
 * e tratamento de exceções personalizado.
 */
public class Calculadora extends JFrame {

    public enum OperacaoMatematica {
        SOMA,
        SUBTRACAO,
        MULTIPLICACAO,
        DIVISAO
    }

    private JTextField campoNumero1;
    private JTextField campoNumero2;
    private JTextField campoResultado;
    private JButton botaoSoma;
    private JButton botaoSubtracao;
    private JButton botaoMultiplicacao;
    private JButton botaoDivisao;
    private JButton botaoLimpar;

    public Calculadora() {
        // Configuração básica da janela
        super("Calculadora");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        inicializarComponentes();
        organizarLayout();
        configurarEventos();
        setVisible(true);
    }

    private void inicializarComponentes() {
        campoNumero1 = new JTextField(10);
        campoNumero2 = new JTextField(10);
        campoResultado = new JTextField(10);
        campoResultado.setEditable(false);

        botaoSoma = new JButton("+");
        botaoSubtracao = new JButton("-");
        botaoMultiplicacao = new JButton("×");
        botaoDivisao = new JButton("÷");
        botaoLimpar = new JButton("Limpar");
    }

    private void organizarLayout() {
        setLayout(new BorderLayout());

        // Painel para os campos de entrada
        JPanel painelEntrada = new JPanel(new GridLayout(3, 2, 10, 10));
        painelEntrada.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        painelEntrada.add(new JLabel("Número 1:"));
        painelEntrada.add(campoNumero1);
        painelEntrada.add(new JLabel("Número 2:"));
        painelEntrada.add(campoNumero2);
        painelEntrada.add(new JLabel("Resultado:"));
        painelEntrada.add(campoResultado);

        // Painel para os botões de operação
        JPanel painelOperacoes = new JPanel(new GridLayout(1, 4, 5, 5));
        painelOperacoes.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

        painelOperacoes.add(botaoSoma);
        painelOperacoes.add(botaoSubtracao);
        painelOperacoes.add(botaoMultiplicacao);
        painelOperacoes.add(botaoDivisao);

        // Painel para o botão limpar
        JPanel painelLimpar = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelLimpar.add(botaoLimpar);

        // Adicionar painéis ao frame
        add(painelEntrada, BorderLayout.NORTH);
        add(painelOperacoes, BorderLayout.CENTER);
        add(painelLimpar, BorderLayout.SOUTH);
    }

    private void configurarEventos() {
        botaoSoma.addActionListener(e -> realizarOperacao(OperacaoMatematica.SOMA));

        botaoSubtracao.addActionListener(e -> realizarOperacao(OperacaoMatematica.SUBTRACAO));

        botaoMultiplicacao.addActionListener(e -> realizarOperacao(OperacaoMatematica.MULTIPLICACAO));

        botaoDivisao.addActionListener(e -> realizarOperacao(OperacaoMatematica.DIVISAO));

        botaoLimpar.addActionListener(e -> {
            campoNumero1.setText("");
            campoNumero2.setText("");
            campoResultado.setText("");
        });
    }

    private void realizarOperacao(OperacaoMatematica operacao) {
        try {
            // Verificar e converter as entradas
            double numero1 = CalculadoraException.verificarEntradaNumerica(campoNumero1.getText());
            double numero2 = CalculadoraException.verificarEntradaNumerica(campoNumero2.getText());

            double resultado = switch (operacao) {
                case SOMA -> numero1 + numero2;
                case SUBTRACAO -> numero1 - numero2;
                case MULTIPLICACAO -> numero1 * numero2;
                case DIVISAO -> {
                    // Verificar divisão por zero
                    CalculadoraException.verificarDivisaoPorZero(numero2);
                    yield numero1 / numero2;
                }
            };

            // Exibir o resultado
            campoResultado.setText(String.valueOf(resultado));

        } catch (CalculadoraException ex) {
            // Exibir mensagem de erro amigável
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            campoResultado.setText("Erro");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Calculadora::new);
    }
}
