Paradigmas Imperativo e Declarativo
Paradigma Imperativo
O paradigma imperativo foca no como fazer algo. O programador descreve a sequência de passos necessários para atingir o objetivo, manipulando o estado do programa. Linguagens como Java seguem esse paradigma.

Exemplo em Java:
java
Copiar
Editar
public class Fatorial {
    public static void main(String[] args) {
        int n = 5, resultado = 1;
        for (int i = 1; i <= n; i++) {
            resultado *= i;
        }
        System.out.println("Fatorial: " + resultado);
    }
}
No código, o programador especifica como o cálculo do fatorial será realizado, usando um laço para multiplicar os números.

Paradigma Declarativo
O paradigma declarativo foca no o que deve ser feito, sem especificar como. O programador descreve as condições ou relações, e o sistema resolve o problema. Prolog é um exemplo de linguagem declarativa.

Exemplo em Prolog:
prolog
Copiar
Editar
fatorial(0, 1).
fatorial(N, F) :- N > 0, N1 is N - 1, fatorial(N1, F1), F is N * F1.
?- fatorial(5, Resultado).
Neste caso, o programador define a lógica de cálculo do fatorial sem especificar a sequência de operações. O sistema encontra a solução com base nas regras fornecidas.

Comparação
Imperativo: Foco no como resolver o problema, detalhando cada passo.

Declarativo: Foco no o que deve ser feito, com o sistema decidindo como alcançar a solução.

Ambos os paradigmas têm suas vantagens: o imperativo oferece controle total sobre a execução, enquanto o declarativo simplifica a lógica do problema, deixando os detalhes para o sistema.