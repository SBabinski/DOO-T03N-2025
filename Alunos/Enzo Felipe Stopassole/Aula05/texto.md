# Paradigmas Imperativo e Declarativo

## Introdução

Existem diferentes formas de programar, chamadas de **paradigmas de programação**. Dois dos mais conhecidos são o **imperativo** e o **declarativo**. Eles têm formas diferentes de resolver problemas e interagir com o computador.

- **Paradigma Imperativo**: Foca em *como* resolver o problema, ou seja, descreve os passos que o computador deve seguir para chegar à solução. Linguagens como Java, C e Python são baseadas nesse estilo, onde o programador diz o que o computador deve fazer em cada etapa.
  
- **Paradigma Declarativo**: Foca no *quê* deve ser feito, ou seja, o programador descreve o resultado desejado, e o computador decide como atingir esse resultado. Linguagens como Prolog e SQL usam esse estilo, onde o foco está em expressar o que se quer, e o sistema se encarrega de como fazer.

## Comparação de Código

Abaixo, vamos comparar dois trechos de código que resolvem o mesmo problema: encontrar o maior número em uma lista. O primeiro código é em **Java**, que usa o estilo imperativo, e o segundo é em **Prolog**, que usa o estilo declarativo.

### Código em Java (Paradigma Imperativo)

```java
public class MaiorNumero {
    public static void main(String[] args) {
        int[] numeros = {3, 5, 7, 2, 8, 1};
        int maior = numeros[0];

        for (int i = 1; i < numeros.length; i++) {
            if (numeros[i] > maior) {
                maior = numeros[i];
            }
        }

        System.out.println("O maior número é: " + maior);
    }
}
