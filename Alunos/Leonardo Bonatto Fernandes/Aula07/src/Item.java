public class Item {
    public int id;
    public String nome;
    public String tipo;
    public double valor;
    
    public void mostrarItem() {
        System.out.println(id + " - " + nome + " (" + tipo + "): R$" + valor);
    }
}