# IMPERATIVO

O paradigma imperativo está focado no como fazer algo. Ou seja, o programador especifica, passo a passo, como o computador deve executar as operações para alcançar o resultado desejado. Neste paradigma, o controle do fluxo do programa é claro e orientado a sequência de comandos e modificações de estado.

A linguagem que normalmente é utilizado no paradigma imperativo é o Java, onde o código descreve as instruções que devem ser executadas de forma sequencial. Em Java, precisasse detalhar o processo de execução, especificando variáveis, estruturas de controle e manipulação de dados.
# DECLARATIVO
O paradigma declarativo é focado no que fazer e não como fazer. No caso o programador descreve o que ele quer, ou como sobre os pontos que devem ter no projeto, e a linguagem de programação determina qual vai ser a melhor maneira de alcançar os objetivos. Em linguagens declarativas o foco é a expressão do problema.
	

# JAVA VS PROLOG 

## Java(Paradigma imperativo);
```java
public class Paridade {
    public static void main(String[] args) {
        int numero = 4; // Exemplo de número a ser verificado
        if (numero % 2 == 0) {
            System.out.println("O número é par");
        } else {
            System.out.println("O número é ímpar");
        }
    }
}
```
Este exemplo em Java, a solução é dada de maneira imperativa. O código descreve explicitamente como verificar se um número é par: verifica-se o resto da divisão do número por 2 e, com base nisso, imprime-se a mensagem correspondente. O controle é feito pelo if-else.
## Prolog(Paradigma declarativo)

```prolog 
par(Numero) :- Numero mod 2 =:= 0.
```
Em prolog, o código é muito mais conciso e expressivo. Aqui, o programador não descreve como verificar se o número é par, mas apenas o que significa um número ser par: ele é divisível por 2 sem deixar resto. O Prolog, então, usa inferência lógica para determinar se o número satisfaz essa condição. O sistema irá automaticamente tentar encontrar a solução com base nas regras e fatos definidos.
