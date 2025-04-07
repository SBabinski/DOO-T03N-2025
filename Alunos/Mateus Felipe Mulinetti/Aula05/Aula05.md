# Paradigmas: Imperativo vs Declarativo


Introdução

Paradigmas de programação é uma abordagem única usada para resolver problemas computacionais. Dois dos principais paradigmas são o *imperativo  e declarativo*.

 Paradigma Imperativo
O paradigma imperativo é a abordagem com instruções sequenciais que agem impressivas em um estado do programa. Ele se concentra em *como* fazer algo; isto é, os comandos que alteram variáveis e estruturas de controle como loops e condicionais. Alguns exemplos dessas linguagens são *Java, C e Python*.

 Paradigma Declarativo
Por outro lado, o paradigma declarativo descreve **o que** deve ser feito, sem definir claramente a *sequência* de execução. Sua ênfase está mais na lógica do que no que a sequência de comandos é. Por exemplo, linguagens como *Prolog, SQL e Haskell* seguem esse paradigma.


Comparação Java Imprima e Prolog Declare

 

 Código em Java (Imperativo)
```java
public class HeroiOuVilao {
    public static String determinarPapel(String acao) {
        if (acao.equals("salvar")) {
            return "Herói";
        } else if (acao.equals("destruir")) {
            return "Vilão";
        } else {
            return "Neutro";
        }
    }
    
    public static void main(String[] args) {
        String acao = "salvar";
        System.out.println("O personagem é: " + determinarPapel(acao));
    }
}
```
 Explicação:
O código em Java segue o paradigma imperativo:
1. Define uma função determinarPapeL que recebe uma ação e verifica se ela corresponde a "salvar" (herói) ou "destruir" (vilão).
2. No método main, atribuímos uma ação a uma variável e chamamos a função.
3. O resultado é impresso na tela.
````
 Código em Prolog (Declarativo)

heroi_ou_vilao(salvar, heroi).
heroi_ou_vilao(destruir, vilao).
heroi_ou_vilao(_, neutro).
````

 Explicação:
1. Define diretamente os fatos que determinam o papel de um personagem com base em sua ação.
2. Se consultarmos heroi_ou_vilao(salvar, X)., Prolog responderá X = heroi.
3. Não há fluxo de controle explícito; apenas uma definição de regras lógicas.

 Conclusão

A diferença mais importante dos paradigmas imperativo e declarativo reside em seus métodos de resolução para um problema. Nos códigos Java, o programador especifica campos para obter diagnostico se o personagem analisado é um herói ou um monstro, no código Prolog, o programa especifica muito menos, declara as regras sobre definir essa relação. Essa diferença reflete como fazer a função ou seja, a diferença há uma grande diferença na interpretação que a pessoa terá ao ler o código.