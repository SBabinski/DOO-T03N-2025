# Paradigmas Imperativo e Declarativo

Na programação, os paradigmas de programação são abordagens que determinam a forma como os programadores escrevem e organizam seu código para atingir determinados objetivos. Entre os paradigmas mais tradicionais, destacam-se o imperativo e o declarativo, os dois com características e enfoques distintos na resolução de problemas computacionais.

## Paradigma Imperativo

O paradigma imperativo é baseado na ideia de instruções sequenciais que o computador deve seguir para alcançar um objetivo. Neste modelo, é o programador define como a solução deve ser obtida, ou seja, é uma lista de passo a passo. Os comandos são dados ao computador com um foco explícito nas mudanças de estado do sistema.

## Paradigma Declarativo

Já no paradigma declarativo, o programador só diz o que quer que o computador faça, sem se preocupar com os detalhes de como fazer. É mais abstrato. O Prolog, por exemplo, é uma linguagem declarativa que foca em descrever relações entre dados e deixa o computador decidir como resolver o problema.

**Exemplo em Java (Imperativo):**

```java
public class Soma {
    public static int soma(int n) {
        int resultado = 0;
        for (int i = 1; i <= n; i++) {
            resultado += i;
        }
        return resultado;
    }

    public static void main(String[] args) {
        int n = 10;
        System.out.println("Soma de 1 a " + n + ": " + soma(n));
    }
}
```

No java, o programador escreve um passo a passo para somar os números, usando um laço que vai de 1 até o número que ele escolheu, somando cada valor.

**Exemplo em Prolog (Declarativo):**

soma(0, 0).
soma(N, Resultado) :-
    N > 0,
    N1 is N - 1,
    soma(N1, Resto),
    Resultado é Resto + N

No Prolog, o programador só diz que a soma de 0 é 0 e que a soma de qualquer outro número é a soma do número anterior mais o próprio número. O computador se encarrega de descobrir como fazer a soma de forma automática.
