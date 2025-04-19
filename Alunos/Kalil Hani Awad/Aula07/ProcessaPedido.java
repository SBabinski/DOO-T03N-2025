public class ProcessaPedido extends Pedido{

    public ProcessaPedido(Pedido pedido) {
        super(
                pedido.getId(),
                pedido.getNome(),
                pedido.getTipo(),
                pedido.getValor(),
                pedido.getQuantidade(),
                pedido.getDia(),
                pedido.getMes(),
                pedido.getAno(),
                pedido.getDatacriacao(),
                pedido.getDataPagamento(),
                pedido.getDataVencimentoReserva()
        );
    }

    public void processar(){
       System.out.println("Data_de_hoje:"+getDia()+"/"+getMes()+"/"+getAno());
       System.out.println("Produção_do_produto:"+getDatacriacao()+"," +"Data_de_vencimento:"+getDataVencimentoReserva());
       System.out.println("O produto esta vencido? Click 1 para sim e 2 para não");
       int escolha = 0;
       if(escolha == 1){
           System.out.println("O produto esta vencido, não pode ser comprado");
    }else if (escolha == 2){
           System.out.println("Produto comprado");
       }else{
           System.out.println("Não existe esta opção");
       }
    }

    }



