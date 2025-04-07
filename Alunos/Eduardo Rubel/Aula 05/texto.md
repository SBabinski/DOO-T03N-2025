# Paradigmas de Programação

## Imperativo

 O programador diz como o computador deve fazer algo, com controle explícito do fluxo de execução (exemplo: Java).

## Declarativo

 O programador descreve o que precisa ser feito, e o computador decide como fazer (exemplo: Prolog).

**Imperativo**

```
public class Paridade {
    public static void main(String[] args) {
        int numero = 6;
        if (numero % 2 == 0) {
            System.out.println("O número é par.");
        } else {
            System.out.println("O número é ímpar.");
        }
    }
}
```

**Declarativo**

```
par(Numero) :- 0 is Numero mod 2.
```

## Diferenças Principais

**Java**

Você controla o passo a passo da execução.

**Prolog** 

Você descreve a regra, e o sistema se encarrega de encontrar a solução.

**Resumo**

Em resumo, o imperativo exige que você controle o como fazer, enquanto o declarativo foca no o quê deve ser feito e deixa o resto para a máquina.


A grande diferença entre os dois paradigmas é como a solução é abordada: no paradigma imperativo, o programador tem que ser mais detalhista e controlar a execução,
enquanto no paradigma declarativo, o programador apenas descreve o problema e deixa que o sistema descubra a solução.