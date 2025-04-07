# Paradigma da Programação

   O paradigma da programação, é o meio de definição para modelar cada código, sua estrutura e forma aplicada, onde o programador utiliza-se de alguns princípios, já definidos, da escrita de linguagem para organização do código e melhor desenvolvimento dos problemas para resolução mais simples. Suas abordagens mais utilizadas nesse meio são: **Paradigma Declarativo e Paradigma Imperativo**.

## Paradigma Declarativo e Imperativo

### Paradigma Declarativo

   Paradigma Declarativo, diz respeito a lógica e seu foco na mesma, o principal objetivo a ser atingido com esse paradigma, é o **resultado** que ele trás. seu principal objetivo é ***"como"*** fazer. 

### Paradigma Imperativo

   Ao contrário do declarativo, o principal objetivo é ***"o que"*** fazer, onde existe uma sequeência na qual os códigos devem ser descritos, ao decorrer das problemáticas apresentadas.
   

---


 # Java e Prolog: Diferença de códigos e suas repectivas funções
   Será realizado a implementação para verificar se um número é **par** ou **ímpar**, em dois diferentes paradigmas e com duas linguagens diferentes.

 ## Código em Java, utilizando do paradgima imperativo aplicado:

 Explicação: 
    - Usamos o método **par(int num)**, para retornar **true** se **num % 2 == 0**, representando o **par** e **false** caso seja **ímpar**;
    - Em **public static void main** colocamos a pré-condição de **int num = 10;** como função e será repassado pelo **if** para imprimir a resposta;
    - Como esse código ja define suas funções explicitamente, ele esta dentro do **Paradigma Imperativo**.

 ## Código em Prolog, onde foi utilizado o paradigma declarativo:

 Explicação:
   - Usamos **(?- par(X) e ?- ímpar(X))**, sem definir a ordem de execução.
   - Quando consultamos **par(10).**, o Prolog verifica se a regra é verdadeira e responde **true** ou **false**.
   - O programador não precisa definir como a verificação será feita, apenas estabelece as relações lógicas.
   - Esse código pertence ao **Paradigma Declarativo**, pois descreve **o problema** e não **o processo de resolução**.


---

```Java
public class ParOuImpar {
    public static boolean par(int num) {
        return num % 2 == 0;
    }

    public static void main(String[] args) {
        int num = 10;

        if (par(num)) {
            System.out.println(num + " é par.");
        } else {
            System.out.println(num + " é ímpar.");
        }
    }
}

--- 

```Prolog
 par(X) :- X mod 2 =:= 0.
 impar(X) :- X mod 2 =\= 0.

 % ?- par(10).   -> true
 % ?- impar(7).  -> true
 % ?- par(5).    -> false
 % ?- impar(8).  -> false

---