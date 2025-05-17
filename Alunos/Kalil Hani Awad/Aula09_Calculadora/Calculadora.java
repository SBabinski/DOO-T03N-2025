import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static JTextField campoNumero = new JTextField();
    public static int passo = 1;
    public static Double valor1 = null;
    public static Double valor2 = null;
    public static JFrame janela;

    public static void main(String[] args) {

        janela = new JFrame("Calculadora");
        janela.setLayout(null);
        janela.setBounds(100, 100, 400, 400);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.GRAY);
        panel.setBounds(0, 0, 400, 400);
        janela.add(panel);

        campoNumero.setBounds(25, 20, 350, 40);
        campoNumero.setHorizontalAlignment(SwingConstants.RIGHT);
        panel.add(campoNumero);

        JButton btnCapturar = new JButton("Enviar");
        btnCapturar.setBounds(150, 80, 100, 40);
        btnCapturar.setBackground(Color.WHITE);
        panel.add(btnCapturar);

        JButton Zer   = new JButton("C");
        JButton Soma  = new JButton("+");
        JButton Div   = new JButton("/");
        JButton Menos = new JButton("-");
        JButton Vezes = new JButton("x");

        Zer.setBounds(25, 70, 60, 30);
        Soma.setBounds(60, 150, 120, 60);
        Div.setBounds(220, 150, 120, 60);
        Menos.setBounds(60, 240, 120, 60);
        Vezes.setBounds(220, 240, 120, 60);

        Zer.setBackground(Color.WHITE);
        Soma.setBackground(Color.WHITE);
        Div.setBackground(Color.WHITE);
        Menos.setBackground(Color.WHITE);
        Vezes.setBackground(Color.WHITE);

        Font fonteBotoes = new Font("Arial", Font.BOLD, 18);
        btnCapturar.setFont(fonteBotoes);
        Zer.setFont(fonteBotoes);
        Soma.setFont(fonteBotoes);
        Div.setFont(fonteBotoes);
        Menos.setFont(fonteBotoes);
        Vezes.setFont(fonteBotoes);

        panel.add(Zer);
        panel.add(Soma);
        panel.add(Div);
        panel.add(Menos);
        panel.add(Vezes);

        btnCapturar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String texto = campoNumero.getText().trim();
                if (texto.isEmpty()) {
                    JOptionPane.showMessageDialog(
                            janela,
                            "O campo está vazio. Por favor, digite um número.",
                            "Campo vazio",
                            JOptionPane.WARNING_MESSAGE
                    );
                    return;
                }
                try {
                    Double valor = Double.parseDouble(texto);
                    if (passo == 1) {
                        valor1 = valor;
                        JOptionPane.showMessageDialog(janela, "Primeiro valor enviado: " + valor1);
                        passo = 2;
                    } else {
                        valor2 = valor;
                        JOptionPane.showMessageDialog(janela, "Segundo valor capturado: " + valor2);
                        passo = 1;
                    }
                    campoNumero.setText("");
                } catch (NumberFormatException ex) {
                    String sobra = texto.replaceAll("[0-9\\.]", "");
                    if (!sobra.isEmpty()) {
                        JOptionPane.showMessageDialog(
                                janela,
                                "Símbolo inválido detectado: \"" + sobra + "\".\nUse apenas dígitos e ponto decimal.",
                                "Símbolo inválido",
                                JOptionPane.ERROR_MESSAGE
                        );
                    } else {
                        JOptionPane.showMessageDialog(
                                janela,
                                "Número mal formatado ou fora de alcance.",
                                "Número inválido",
                                JOptionPane.ERROR_MESSAGE
                        );
                    }
                }
            }
        });


        Soma.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Double result = valor1 + valor2;
                    campoNumero.setText(valor1 + " + " + valor2 + " = " + result);
                    valor1 = null;
                    valor2 = null;
                } catch (Exception ex) {
                    if (valor1 == null && valor2 == null) {
                        JOptionPane.showMessageDialog(
                                janela,
                                "Envie 2 valores antes de clicar nos botões (+, /, -, x).",
                                "Faltam 2 valores para o cálculo",
                                JOptionPane.ERROR_MESSAGE
                        );
                    } else if (valor2 == null) {
                        JOptionPane.showMessageDialog(
                                janela,
                                "Envie mais 1 valor antes de clicar nos botões (+, /, -, x).",
                                "Falta 1 valor para o cálculo",
                                JOptionPane.ERROR_MESSAGE
                        );
                    }
                }
            }
        });

        Div.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Double result = valor1 + valor2;
                    campoNumero.setText(valor1 + " / " + valor2 + " = " + "Erro!!!");
                    if(valor1 == 0 && valor2 == 0){
                        throw new DivisaoPorZeroException(janela,campoNumero);
                    }
                } catch (Exception ex) {
                    if (valor1 == null) {
                        JOptionPane.showMessageDialog(
                                janela,
                                "Envie 2 valores antes de clicar nos botões (+, /, -, x).",
                                "Faltam 2 valores para o cálculo",
                                JOptionPane.ERROR_MESSAGE
                        );
                    } else if (valor2 == null) {
                        JOptionPane.showMessageDialog(
                                janela,
                                "Envie mais 1 valor antes de clicar nos botões (+, /, -, x).",
                                "Falta 1 valor para o cálculo",
                                JOptionPane.ERROR_MESSAGE
                        );
                    }
                }
            }
        });

        Menos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Double result = valor1 - valor2;
                    campoNumero.setText(valor1 + " - " + valor2 + " = " + result);
                    valor1 = null;
                    valor2 = null;
                } catch (Exception ex) {
                    if (valor1 == null && valor2 == null) {
                        JOptionPane.showMessageDialog(
                                janela,
                                "Envie 2 valores antes de clicar nos botões (+, /, -, x).",
                                "Faltam 2 valores para o cálculo",
                                JOptionPane.ERROR_MESSAGE
                        );
                    } else if (valor2 == null) {
                        JOptionPane.showMessageDialog(
                                janela,
                                "Envie mais 1 valor antes de clicar nos botões (+, /, -, x).",
                                "Falta 1 valor para o cálculo",
                                JOptionPane.ERROR_MESSAGE
                        );
                    }
                }
            }
        });

        Vezes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Double result = valor1 * valor2;
                    campoNumero.setText(valor1 + " × " + valor2 + " = " + result);
                    valor1 = null;
                    valor2 = null;
                } catch (Exception ex) {
                    if (valor1 == null && valor2 == null) {
                        JOptionPane.showMessageDialog(
                                janela,
                                "Envie 2 valores antes de clicar nos botões (+, /, -, x).",
                                "Faltam 2 valores para o cálculo",
                                JOptionPane.ERROR_MESSAGE
                        );
                    } else if (valor2 == null) {
                        JOptionPane.showMessageDialog(
                                janela,
                                "Envie mais 1 valor antes de clicar nos botões (+, /, -, x).",
                                "Falta 1 valor para o cálculo",
                                JOptionPane.ERROR_MESSAGE
                        );

                    }
                }
            }
        });

        Zer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                campoNumero.setText("");
            }
        });

        janela.setVisible(true);
    }
}
