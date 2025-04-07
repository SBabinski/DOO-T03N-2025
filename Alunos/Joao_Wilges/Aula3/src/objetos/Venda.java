package objetos;


public class Venda {
    
    private Integer quantidade_vendida;
    private Float valor_venda;
    private Integer porcent_desc_aplicado;

    public Venda(Integer quantidade_vendida,Float valor_venda,Integer porcent_desc_aplicado){
        this.quantidade_vendida = quantidade_vendida;
        this.valor_venda = valor_venda;
        this.porcent_desc_aplicado = porcent_desc_aplicado;
    }

    @Override
    public String toString() {
        return "Venda: Quantidade: " + quantidade_vendida +" | Valor: " + valor_venda + " | Porcentagem Desconto: "+ porcent_desc_aplicado;
    }

}
