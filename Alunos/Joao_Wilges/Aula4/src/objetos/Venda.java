package objetos;


public class Venda {

    private Integer data_mes;
    private Integer data_dia;
    private Integer quantidade_vendida;
    private Float valor_venda;
    private Integer porcent_desc_aplicado;

    public Venda(Integer quantidade_vendida,Float valor_venda,Integer porcent_desc_aplicado, Integer data_mes, Integer data_dia){
        this.quantidade_vendida = quantidade_vendida;
        this.valor_venda = valor_venda;
        this.porcent_desc_aplicado = porcent_desc_aplicado;
        this.data_mes = data_mes;
        this.data_dia = data_dia;
    }

    @Override
    public String toString() {
        return "Venda: Quantidade: " + quantidade_vendida +" | Valor: " + valor_venda + " | Porcentagem Desconto: "+ porcent_desc_aplicado;
    }

    public Integer getDataDia() {
        return data_dia;
    }
    public Integer getDataMes() {
        return data_mes;
    }
    public Float getValorVenda(){
        return valor_venda;
    }
}
