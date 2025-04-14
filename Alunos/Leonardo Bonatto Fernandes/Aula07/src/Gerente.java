public class Gerente extends Vendedor {
    public double salarioBase;
    public double[] salariosRecebidos = new double[3];
    
    public double calcularMedia() {
        return (salariosRecebidos[0] + salariosRecebidos[1] + salariosRecebidos[2]) / 3;
    }
    
    public double calcularBonus() {
        return salarioBase * 0.35;
    }
}