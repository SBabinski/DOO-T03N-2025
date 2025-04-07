# Paradigmas de Programação: Imperativo vs. Declarativo

Os paradigmas de programação definem diferentes abordagens para a construção de software, influenciando diretamente a forma como os desenvolvedores estruturam e escrevem seus códigos. Dois dos principais paradigmas são o **imperativo** e o **declarativo**, cada um com características distintas.

## Paradigma Imperativo

No paradigma imperativo, o foco está em descrever **como** um programa deve realizar suas operações, especificando uma sequência detalhada de instruções que alteram o estado do sistema. Essa abordagem é comparável a seguir uma receita culinária, onde cada passo é claramente definido para alcançar o resultado desejado. Linguagens como C, Java e Python são exemplos que adotam esse paradigma.

## Paradigma Declarativo

Por outro lado, o paradigma declarativo concentra-se em **o que** o programa deve realizar, sem especificar detalhadamente o processo para atingir esse resultado. Aqui, o desenvolvedor define o objetivo, e a linguagem de programação se encarrega de determinar a melhor forma de alcançá-lo. Linguagens como SQL, Prolog e Haskell exemplificam essa abordagem.

## Comparação de Códigos: Java vs. Prolog

Para ilustrar as diferenças entre os paradigmas imperativo e declarativo, consideremos o problema clássico da impressão dos versos da música "99 Garrafas de Cerveja". A seguir, apresentamos implementações em Java (paradigma imperativo) e Prolog (paradigma declarativo).

### Implementação em Java (Imperativo)

```java
public class BottlesOfBeer {
    public static void main(String[] args) {
        for (int i = 99; i > 0; i--) {
            System.out.println(i + " garrafas de cerveja na parede, " + i + " garrafas de cerveja.");
            System.out.println("Pegue uma e passe adiante, " + (i - 1) + " garrafas de cerveja na parede.\n");
        }
        System.out.println("Não há mais garrafas de cerveja na parede, não há mais garrafas de cerveja.");
        System.out.println("Vá até a loja e compre mais, 99 garrafas de cerveja na parede.");
    }
}
```

**Descrição do Funcionamento:**

Nesta implementação, utilizamos um loop `for` que inicia em 99 e decrementa até 1. A cada iteração, o programa imprime os versos correspondentes, atualizando o número de garrafas conforme necessário. Ao final do loop, uma mensagem especial é exibida quando não há mais garrafas. Essa abordagem detalha explicitamente cada passo necessário para alcançar o resultado desejado, característica típica do paradigma imperativo.

### Implementação em Prolog (Declarativo)

```prolog
bottles(0) :-
    write('Não há mais garrafas de cerveja na parede, não há mais garrafas de cerveja.'), nl,
    write('Vá até a loja e compre mais, 99 garrafas de cerveja na parede.'), nl.
bottles(N) :-
    N > 0,
    write(N), write(' garrafas de cerveja na parede, '), write(N), write(' garrafas de cerveja.'), nl,
    write('Pegue uma e passe adiante, '), M is N - 1, write(M), write(' garrafas de cerveja na parede.'), nl, nl,
    bottles(M).
```

**Descrição do Funcionamento:**

Em Prolog, definimos regras recursivas para representar os versos da música. A regra `bottles/1` lida com o caso base quando não há mais garrafas (`N = 0`) e com o caso recursivo para `N > 0`, imprimindo os versos correspondentes e chamando recursivamente a si mesma com `N - 1`. Essa abordagem enfatiza a definição do que deve ser feito (as regras e relações), deixando para o interpretador Prolog a responsabilidade de determinar como executar essas regras, alinhando-se ao paradigma declarativo.

## Conclusão

A escolha entre os paradigmas imperativo e declarativo depende das necessidades específicas do problema a ser resolvido e das preferências do desenvolvedor. Enquanto o paradigma imperativo oferece controle detalhado sobre o fluxo de execução, o paradigma declarativo permite uma expressão mais direta das intenções do programador, delegando ao sistema a responsabilidade de determinar a melhor forma de alcançar o resultado desejado.
