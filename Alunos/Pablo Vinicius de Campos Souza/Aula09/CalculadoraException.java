package Aula09;

public class CalculadoraException extends Exception {

    public CalculadoraException(String mensagem) {
        super(mensagem);
    }

    public static void verificarDivisaoPorZero(double divisor) throws CalculadoraException {
        if (divisor == 0) {
            throw new CalculadoraException("Erro: Divisão por zero não é permitida!");
        }
    }

    public static double verificarEntradaNumerica(String texto) throws CalculadoraException {
        try {
            return Double.parseDouble(texto);
        } catch (NumberFormatException e) {
            throw new CalculadoraException("Erro: Entrada inválida! Por favor, digite apenas números.");
        }
    }
}