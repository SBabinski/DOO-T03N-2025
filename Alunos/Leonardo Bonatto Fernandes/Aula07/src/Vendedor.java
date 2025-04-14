public class Vendedor extends Pessoa {
    public String loja;
    
    @Override
    public void apresentar() {
        super.apresentar();
        System.out.println("Loja: " + loja);
    }
}