# Paradigmas

 **Programação: Imperativo vs Declarativo**

---

## Introdução

Os paradigmas de programação representam diferentes abordagens para a solução de problemas computacionais. 
Dois dos paradigmas mais contrastantes são o imperativo e o declarativo, cada um com sua própria filosofia, metodologia e aplicações. 
Este texto explora as principais características desses paradigmas, comparando-os por meio de implementações concretas em Java (paradigma imperativo) e Prolog (paradigma declarativo).
  
---

## Paradigma Imperativo

O paradigma imperativo baseia-se na manipulação de estado através de sequências de instruções que ditam como uma tarefa deve ser executada. 
O programador define explicitamente cada etapa do processo computacional, modificando variáveis ao longo do tempo.
 
---

< Características principais:

**Baseado em sequências de comandos;**

* 1.Foco em como realizar uma tarefa (passos específicos);

* 2.Uso de variáveis mutáveis e estado compartilhado;

* 3.Controle de fluxo explícito (condicionais, loops);

* 4.Execução sequencial e passo a passo.


Linguagens como **C, Java, Python e C++** são **predominantemente imperativas**, embora possam suportar outros paradigmas.

---

## Implementação em Java (Paradigma Imperativo)

'public class BuscaElemento {
    public static boolean buscarElemento(int[] array, int elementoBuscado) {
        for (int num : array) {
            if (num == elementoBuscado) {
                return true;
            }
        }
        return false;
    }
    
    public static void main(String[] args) {
        int[] numeros = {3, 7, 2, 9, 5, 1};
        int alvo = 5;
        
        if (buscarElemento(numeros, alvo)) {
            System.out.println("Elemento " + alvo + " encontrado no array.");
        } else {
            System.out.println("Elemento " + alvo + " não encontrado no array.");
        }
    }
}'

---

Nesta implementação, o método buscarElemento percorre a lista sequencialmente, verificando se o elemento buscado está presente. 
O controle de fluxo é feito explicitamente por meio do loop for e da estrutura condicional if.

---

## Paradigma Declarativo

O paradigma declarativo foca no que deve ser computado, em vez de como a computação deve ser realizada. 
Em vez de sequências de comandos, o programador define propriedades e relações, permitindo que o sistema descubra automaticamente a solução.

< Características principais:

---

**Especificação de relações e restrições;**

**Foco no resultado desejado, não nos passos;**

* 1.Minimiza efeitos colaterais;

* 2.Independência da ordem de avaliação;

* 3.Maior nível de abstração.

Entre os paradigmas do paradigma declarativo, destacam-se a **programação funcional (Haskell, Lisp) e a programação lógica (Prolog).**

---

## Implementação em Prolog (Paradigma Declarativo)

'% Regra para verificar se um elemento pertence a uma lista
membro(X, [X|_]).
membro(X, [_|Cauda]) :- membro(X, Cauda).'

---

< Explicação

* 1.% Consulta para verificar se 5 está na lista:
% ?- membro(5, [3, 7, 2, 9, 5, 1]).

Nesta implementação, -2.a relação membro/2 define quando um elemento pertence a uma lista. A busca é realizada implicitamente pelo mecanismo de inferência do Prolog, eliminando a necessidade de controle de fluxo manual.

---

# Análise Comparativa

**Ambas as abordagens resolvem o mesmo problema, mas de formas distintas:**

* 1. Java (Imperativo): O programador define explicitamente como a busca é feita, controlando a iteração e o estado do programa.

* 2.Prolog (Declarativo): O programador apenas define as condições para que um elemento pertença a uma lista, e o motor de inferência do Prolog gerencia a execução.

---

##Conclusão

Os paradigmas imperativo e declarativo refletem diferentes abordagens para a resolução de problemas computacionais. 
O paradigma imperativo oferece controle detalhado sobre a execução do programa, sendo ideal para problemas que exigem manipulação explícita de estado. 
Já o paradigma declarativo abstrai a forma de resolução, sendo mais adequado para representação de conhecimento e inferência lógica.

A compreensão desses paradigmas permite ao programador escolher a melhor abordagem para cada situação, promovendo soluções mais eficientes, elegantes e manutenáveis.