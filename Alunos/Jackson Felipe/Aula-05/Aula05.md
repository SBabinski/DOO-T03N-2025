# Aula 5 - Texto sobre Paradigmas

## Introdução

Na programação, um **paradigma** representa um modelo de raciocínio usado para construir soluções. Nesta atividade, vamos abordar dois tipos: o **imperativo** e o **declarativo**, comparando como eles funcionam por meio de exemplos em **Java** e **Prolog**.

## Paradigma Imperativo

No paradigma imperativo, o programador define **como** algo deve ser feito, passo a passo. O código controla diretamente o fluxo de execução usando comandos sequenciais, laços e condições.

### Características:
- Sequência de instruções.
- Uso de variáveis e controle de estado.
- Estruturas como `if`, `for`, `while`.

### Exemplo em Java:

```java
public class Soma {
    public static void main(String[] args) {
        int x = 10;
        int y = 5;
        int total = x + y;

        if (total > 10) {
            System.out.println("Resultado acima de 10.");
        } else {
            System.out.println("Resultado igual ou abaixo de 10.");
        }
    }
}
```

Esse exemplo mostra como o programa executa as instruções em ordem, controla variáveis e toma decisões com base em condições.

## Paradigma Declarativo

Já no paradigma declarativo, o foco está em **o que** se quer alcançar, e não **como** fazer. O código descreve o resultado esperado por meio de regras, fatos ou declarações.

### Características:
- Sem controle explícito do fluxo.
- Uso de expressões, regras e lógica.
- Exemplos: SQL, HTML, Prolog.

### Exemplo em Prolog:

```prolog
% Fatos
mae(maria, joao).
mae(maria, ana).

% Regra
irmao(X, Y) :- mae(M, X), mae(M, Y), X \= Y.

% Consulta:
% ?- irmao(joao, Y).
% Resultado: Y = ana.
```

Neste exemplo, não há comandos de controle. Apenas declaramos fatos e uma regra. O interpretador se encarrega de encontrar a resposta para a consulta.

## Conclusão

Ambos os paradigmas resolvem problemas, mas de maneiras diferentes. O imperativo foca no **como fazer**, enquanto o declarativo se concentra no **o que deve ser feito**. Cada um tem seu uso ideal conforme o tipo de problema.
