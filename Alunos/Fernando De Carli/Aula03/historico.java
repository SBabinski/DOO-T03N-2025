public class historico {
    private int quant;
    private double valorUnitario;
    private double valorTotal;
    private double desconto;
    private double precoFinal;

    public historico(int quant, double valorUnitario) {
        this.quant = quant;
        this.valorUnitario = valorUnitario;
        calculaValores();
    }
    public void calculaValores(){
        this.valorTotal = quant * valorUnitario;
        if (quant >= 10){
            this.desconto = valorTotal * 0.05;
        }else{
            this.desconto = 0;
        }
        this.precoFinal = valorTotal - desconto;
    }
    public void imprimir(){
        System.out.println("Quantidade: " + quant);
        System.out.println("Valor unitario: " + valorUnitario);
        System.out.println("Valor total: " + valorTotal);
        System.out.println("Desconto: " + desconto);
        System.out.println("Valor final: " + precoFinal);
        System.out.println("---------------------");
    }
}