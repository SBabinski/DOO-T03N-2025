public class Plantas {


    public int quantidade;
    public float valor;
    public float desconto;

    public Plantas(int quantidade, float valor, float desconto) {
        this.quantidade = quantidade;
        this.valor = valor;
        this.desconto = desconto;
    }
    @Override
    public String toString() {
        return ("com a quantidade de plantas vendidas de: " + quantidade +
                " com o valor de : " + valor + " " + (desconto == 0 ? "Sem desconto aplicado" : "Com desconto de " + desconto));

    }

}
