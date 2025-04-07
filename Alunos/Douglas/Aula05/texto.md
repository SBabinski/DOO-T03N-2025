# Paradigmas de Programação: Imperativo e Declarativo

A programação pode ser tratada sob diversos paradigmas, cada um proporcionando benefícios e desvantagens de acordo com o contexto de aplicação. O paradigma imperativo e o declarativo são amplamente empregados.

## Paradigma Imperativo

O paradigma imperativo é definido por comandos sequenciais que orientam o computador a executar uma tarefa passo a passo. A ênfase recai sobre o procedimento, empregando comandos que alteram o estado do programa por meio de variáveis e estruturas de controle, como loops e condicional.

### Exemplo em Java
```java
public class Fatorial {
    public static int calcularFatorial(int n) {
        int resultado = 1;
        for (int i = 1; i <= n; i++) {
            resultado *= i;
        }
        return resultado;
    }

    public static void main(String[] args) {
        int numero = 5;
        System.out.println("Fatorial de " + numero + ": " + calcularFatorial(numero));
    }
}
```

Neste exemplo, o cálculo fatorial é realizado de maneira iterativa, utilizando um loop for para acumular o resultado. O foco está na sequência de operações realizadas.

## Paradigma Declarativo

O paradigma declarativo foca no o que fazer em vez de como fazer. O programador descreve o resultado desejado, e o sistema se encarrega da execução.

### Exemplo em Prolog

```prolog
fatorial(0, 1).
fatorial(N, F) :-
    N > 0,
    N1 is N - 1,
    fatorial(N1, F1),
    F is N * F1.

?- fatorial(5, Resultado).
```

Neste exemplo, o cálculo do fatorial é realizado de forma recursiva, descrevendo relações entre valores em vez de detalhar as operações. O Prolog busca resolver a expressão com base nas regras definidas.

## Comparação

A distinção fundamental entre os paradigmas reside na forma de abordagem. O código em Java especifica as etapas necessárias para efetuar o cálculo, enquanto o código em Prolog estabelece relações e atribui ao sistema a tarefa de solucionar o problema. O paradigma imperativo costuma ser mais minucioso e claro, ao passo que o paradigma declarativo favorece uma maior abstração e clareza na definição da lógica.

Cada paradigma possui vantagens específicas: o imperativo é mais natural para problemas que exigem controle explícito do fluxo de execução, enquanto o declarativo é poderoso para problemas lógicos e de raciocínio complexo. A escolha depende do contexto e das necessidades do projeto.