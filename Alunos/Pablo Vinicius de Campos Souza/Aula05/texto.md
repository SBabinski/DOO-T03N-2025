# Paradigmas Imperativo e Declarativo

Os paradigmas **imperativo** e **declarativo** representam duas abordagens diferentes para programação.

O paradigma **imperativo**, que está presente em linguagens como Java, C e Python, enfatiza o uso de controle de estado explícito, loops e instruções sequenciais na computação. O paradigma **declarativo**, utilizado em linguagens como Prolog, SQL e Haskell concentram-se no problema e deixam o sistema descobrir como resolvê-lo.

## Um Exemplo de Comparação

Suponha que desejamos determinar se um dado número é par.

### Código em Java (Paradigma Imperativo)

```java
public class Paridade {
    public static boolean ePar(int numero) {
        return numero % 2 == 0;
    }

    public static void main(String[] args) {
        System.out.println(ePar(4)); // true
        System.out.println(ePar(7)); // false
    }
}
```

A função `ePar` neste código determina se um número é divisível por dois usando um operador matemático e retorna verdadeiro ou falso. O fluxo do programa adere a um procedimento claro e sequencial e é bem definido.

### Código em Prolog (Paradigma Declarativo)

```prolog
e_par(N) :- N mod 2 =:= 0.

?- e_par(4).
true.

?- e_par(7).
false.
```

Aqui, a regra `e_par` declara que um número é par se o resto da divisão por 2 for zero. Não há controle explícito de fluxo ou retorno de valores como no Java. O interpretador Prolog resolve a consulta verificando se a condição lógica é verdadeira.

## Comparação

**Java (Imperativo):** O programador define explicitamente o fluxo de execução e como a computação deve ser feita.
- Útil quando o controle de fluxo e manipulação de estado são necessários, como:
    - Sistemas embarcados (onde cada instrução conta)
    - Jogos e animações (que dependem de atualização contínua do estado)
    - Algoritmos que exigem eficiência computacional alta (como ordenação e busca otimizadas)

**Prolog (Declarativo):** O programador descreve a relação lógica e deixa o mecanismo de inferência encontrar a resposta.
- Melhor para problemas que envolvem relações lógicas ou grandes quantidades de dados, como:
    - Consultas a bancos de dados (SQL)
    - Inteligência Artificial (Prolog para inferência lógica)
    - Programação funcional (Haskell, Lisp) para cálculos matemáticos complexos sem efeitos colaterais