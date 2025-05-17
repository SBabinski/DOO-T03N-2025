import javax.swing.*;

public class DivisaoPorZeroException extends RuntimeException {
    public DivisaoPorZeroException(JFrame janela, JTextField campoNumero) {
        JOptionPane.showMessageDialog(
                janela,
                "ERRO!!!",
                "Divisão de 0/0",
                JOptionPane.ERROR_MESSAGE
        );
        campoNumero.setText("0 / 0 = ERRO!!!");
    }
}
