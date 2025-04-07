package Aula04;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RegistroDeVendas {
    private final List<Venda> vendas;

    public RegistroDeVendas() {
        this.vendas = new ArrayList<>();
    }

    public String impressaoRegistroDeVendas(LocalDate data) {
        StringBuilder builder = new StringBuilder();

        builder.append("-------------REGISTRO DE VENDAS-------------\n");
        if (data != null) {
            builder.append(String.format("----------------(%s)----------------%n", LocalDateUtil.localDateParaString(data)));
        }
        builder.append(String.format("Quantidade de vendas:      %d %n", calcularQuantidadeDeVendasPorData(data)));
        builder.append(String.format("Plantas vendidas:          %d %n", calcularQuantidadePlantasVendidas(data)));
        builder.append(String.format("Total em vendas:        R$ %.2f %n", calcularValorTotalVendas(data)));
        builder.append(String.format("Total em descontos:     R$ %.2f %n", calcularValorTotalDescontos(data)));

        return builder.toString();
    }

    public void adicionarVenda(Venda venda) {
        vendas.add(venda);
    }

    public List<Venda> pesquisarVendasPorData(LocalDate data) {
        return vendas.stream()
                .filter(venda -> venda.getData().equals(data))
                .toList();
    }

    public Venda pesquisarVendaPorId(int id) {
        return vendas.stream()
                .filter(venda -> venda.getId() == id)
                .findFirst().orElse(null);
    }

    public long calcularQuantidadeDeVendasPorData(LocalDate data) {
        return pesquisarVendasPorData(data).size();
    }

    public int calcularQuantidadePlantasVendidas(LocalDate data) {
        return vendas.stream()
                .filter(venda -> data == null || venda.getData().equals(data)) // filtra por data apenas se ela não for nula
                .mapToInt(Venda::getQuantidade)
                .sum();
    }

    public double calcularValorTotalVendas(LocalDate data) {
        return vendas.stream()
                .filter(venda -> data == null || venda.getData().equals(data)) // filtra por data apenas se ela não for nula
                .mapToDouble(Venda::getValorTotal)
                .sum();
    }

    public double calcularValorTotalDescontos(LocalDate data) {
        return vendas.stream()
                .filter(venda -> data == null || venda.getData().equals(data)) // filtra por data apenas se ela não for nula
                .mapToDouble(Venda::getValorDesconto)
                .sum();
    }

    // --------------Getters e Setters--------------
    public List<Venda> getVendas() {
        return vendas;
    }
}
