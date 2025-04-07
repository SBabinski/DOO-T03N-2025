 # Paradigmas de programação

- Paradigma de Programação é um conceito fundamental na computação que define um estilo ou metodologia de desenvolvimento de software, orientando como os problemas são modelados e resolvidos em uma linguagem de programação. Diferente das linguagens, que são ferramentas, os paradigmas estabelecem princípios e regras sobre a estruturação do código, a organização da lógica e o fluxo de execução.

## Paradigma imperativo

- No paradigma imperativo, como o nome já revela, o desenvolvedor cria uma instrução para que a máquina processe as execuções de uma determinada maneira.

  - **sequência** garante que as instruções sejam processadas na ordem em que foram escritas. Por exemplo, um programa pode começar declarando variáveis e realizando cálculos simples antes de avançar para outras operações.
  
  - **repetições** permitem executar um bloco de código várias vezes sem a necessidade de reescrevê-lo. Isso pode ser feito por meio de laços como **for** e **while**.
  
  - **condições** definem diferentes caminhos para a execução do programa. Com estruturas como **if-else** e **switch-case**, o código pode tomar decisões com base em variáveis ou resultados de operações.


## Paradigma declarativo

- O paradigma declarativo foca em descrever o que deve ser feito, sem especificar como a máquina deve executar as operações. Em vez de seguir uma sequência de comandos detalhados, o programa define regras, expressões ou relações que levam ao resultado desejado.
  
- O foco da resolução está em determinar **o quê** deve ser resolvido, de forma a contrastar no paradigma Imperativo onde é definido em **como**.
  - **Exeplos**
    - **SQL , HTML , Prolog** entre outras. 


## Exeplos:


### Imperativo com Java
```java
public class ExemploImperativo {
    public static void main(String[] args) {
        int a = 5;
        int b = 3;
        int soma = a + b; 

        if (soma > 5) { 
            System.out.println("A soma é maior que 5.");
        } else {
            System.out.println("A soma é menor ou igual a 5.");
        }
    }
}
```

### Declarativo com Prolog
```prolog
% Definição de fatos
pai(joao, maria).
pai(joao, jose).
pai(carlos, ana).

% Regra: X é avô de Y se X for pai de Z e Z for pai de Y
avo(X, Y) :- pai(X, Z), pai(Z, Y).

% Consulta: Quem é pai de Maria?
% ?- pai(X, maria).  
% Resultado: X = joao.
```