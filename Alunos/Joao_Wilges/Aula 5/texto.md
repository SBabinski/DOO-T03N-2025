# Paradigmas de programação
## Imperativo
> Define que as instruções passadas ao computador, devem especificar o passo a passo das ações em que serão executadas.
- O foco do paradigma é determinar o modo em que as instruções devem ser executadas. Pode ser estruturado por 3 componentes:
  - Sequencia: Instruções que são codificadas na sequencia a serem executadas.
  - Condições: If/Else, Switch, Case.
  - Repetições: For, While, Recursividade.
- **Destaca-se neste paradigma a capacidade de modularização ou criação de subrotinas**
- Este paradigma se popularizou numa época a se opor ao uso do _goto_ onde a programação estruturada dominava a forma se programar até a década de 90, onde se tornou popular a programação OO (Orientada a Objeto)

## Declarativo
> Determina que as instruções passadas ao computador devem especificar a sequencia lógica e o resultado esperado, sem determinar o fluxo de controle.
- O foco da resolução está em determinar "*o quê*" deve ser resolvido, de forma a contrastar no paradigma **Imperativo** onde é definido "_o como_".
  - Historicamente, este paradigma não tem tanto destaque quanto o **imperativo** porém diversas linguagem foram baseadas no seu conceito, como SQL, HTML, JS, Erlang, LISP.



# Exemplos de código

### Imperativo com Java:
```java
public class Main {
    public static void main(String[] args) {
        int numero = 10;
        
        if(numero > 0) {
            System.out.println("O número é positivo.");
        } else {
            System.out.println("O número é negativo.");
        }
    }
}
```

### Declarativo com Prolog

```prolog
prerequisite(cse142,cse143).

prerequisite(cse143,cse311).

prerequisite(cse311,cse312).

prerequisite(cse143,cse331).

prerequisite(cse143,cse341).

/* take_before(A,B) succeeds if you must take A before B */
take_before(X,Z) :- prerequisite(X,Z).
take_before(X,Z) :- prerequisite(X,Y),
                    take_before(Y,Z).
```