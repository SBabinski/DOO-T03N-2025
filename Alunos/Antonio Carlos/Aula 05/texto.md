
# Paradigmas de Programação: Imperativo vs. Declarativo

Os paradigmas de programação imperativo e declarativo, dois modelos fundamentalmente distintos para descrever a lógica de um programa.  A programação **imperativa** foca em *como* alcançar um resultado, especificando passo a passo as instruções que o computador deve executar. Em contraste, a programação **declarativa** concentra-se em *o que* se deseja obter, descrevendo o resultado esperado sem detalhar o processo exato de execução.

## Comparação entre Java (Imperativo) e Prolog (Declarativo)

Para ilustrar a diferença entre os paradigmas, vamos analisar dois trechos de código com o objetivo de verificar se um número pertence à sequência de Fibonacci.

**Java (Imperativo):**

```java
public class Fibonacci {
    public static boolean isFibonacci(int n) {
        if (n < 0) return false;
        if (n <= 1) return true;

        int a = 0;
        int b = 1;
        while (b < n) {
            int temp = b;
            b = a + b;
            a = temp;
        }
        return b == n;
    }

    public static void main(String[] args) {
        System.out.println(isFibonacci(8)); // Output: true
        System.out.println(isFibonacci(10)); // Output: false
    }
}
```
O código Java demonstra a abordagem imperativa. Ele define um método isFibonacci que recebe um inteiro como entrada e retorna um booleano. A lógica envolve inicializar variáveis, iterar em um laço while, atualizar os valores das variáveis em cada iteração e, finalmente, comparar o valor calculado com a entrada. O código descreve explicitamente como calcular os números de Fibonacci e verificar a pertinência.

**Prolog (Declarativo):**

```Prolog
fibonacci(0).
fibonacci(1).
fibonacci(N) :-
    N > 1,
    N1 is N - 1,
    N2 is N - 2,
    fibonacci(N1),
    fibonacci(N2).

is_fibonacci(N) :- fibonacci(N).
```

O código Prolog, por sua vez, adota a abordagem declarativa. Ele define fatos sobre a sequência de Fibonacci (0 e 1 pertencem à sequência) e regras para determinar outros números da sequência. A regra fibonacci(N) define que N pertence à sequência se N for maior que 1 e N-1 e N-2 também pertencerem à sequência. O predicado is_fibonacci simplesmente verifica se N satisfaz a definição de fibonacci. O código descreve o que caracteriza um número de Fibonacci, deixando para o interpretador Prolog a tarefa de encontrar a solução.

## Conclusão

A comparação entre os dois exemplos destaca a diferença fundamental entre os paradigmas imperativo e declarativo. Enquanto o código Java especifica o processo passo a passo, o código Prolog define as relações e deixa a execução para o sistema. A escolha do paradigma depende do problema em questão e das preferências do programador. Para problemas que envolvem lógica complexa e relações entre dados, a abordagem declarativa pode ser mais concisa e expressiva.


