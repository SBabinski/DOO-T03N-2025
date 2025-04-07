# Paradigma imperativo

O **paradigma imperativo** é um estilo de programação que se baseia em instruções sequenciais e explícitas para guiar o computador na execução de tarefas. Ele reflete a maneira tradicional de pensar em algoritmos, onde o programador define passo a passo como um problema deve ser resolvido, controlando diretamente o fluxo de execução e o estado do programa. 

**Java**
```
public class ExemploImperativo {
    public static void main(String[] args) {
    
        int num1 = 10;
        int num2 = 5;
        int soma = 0;
        soma = num1 + num2;
        System.out.println("A soma é: " + soma);
    }
} 
```
1. Public class ExemploImperativo{ (Criado uma classe publica chamada: ExemploImperativo).
2. public static void main(String[] args) { (inicia o codigo).
3. int num1 = 10; (variavel chamada num1  do tipo inteiro recebe 10).
4. int num 2 = 5; (variavel chamada num2  do tipo inteiro recebe 5).
5. Soma = num1 + num2; (Variavel Soma recebe a soma do valor de num1 e num2).
6. System.out.println("A soma é: " + soma); (Mostra a soma para o usuario).
7. (Fim do codigo).

# Paradigma declarativo

O **paradigma declarativo** é um estilo de programação que descreve o objetivo ou resultado desejado, sem especificar passo a passo como alcançá-lo. Em vez de detalhar instruções sequenciais, o programador define o que deve ser feito, delegando a implementação ao sistema.

**Prolog**
```
mother_child(trude, sally).

father_child(tom, sally).
father_child(tom, erica).
father_child(mike, tom).

sibling(X, Y)      :- parent_child(Z, X), parent_child(Z, Y), not(X = Y).

parent_child(X, Y) :- father_child(X, Y).
parent_child(X, Y) :- mother_child(X, Y).
```
1. mother_child(trude, sally) (Trude é mãe de Sally).
2. father_child(tom, sally) (Tom é pai de Sally).
3. father_child(mike, tom) (Tom é pai de Erica).
4. father_child(mike, tom) (Mike é pai de Tom).
5. sibling(X, Y) :- (X e Y são irmãos se).
6. parent_child(Z, X), parent_child(Z, Y)  (têm um pai/mãe em comum com Z).
7. not(X = Y) (verifica se X ≠ Y são parentes).
8. parent_child(X, Y) :- father_child(X, Y) (X é pai de Y se for pai). 
9. parent_child(X, Y) :- mother_child(X, Y) (X é mãe de Y se for mãe).

## Comparação

Podemos concluir que o Java sendo **paradigma imperativo** tende a ser mais estruturado, assim buscando trazer e seguir uma ordem baseada na resolução de uma tarefa (no caso do exemplo era para somar dois valores dentro de duas variaveis do tipo inteiro e mostrar ao usuario o resultado da soma), enquanto Prolog sendo **paradigma declarativo** tende a penas a disser o que deve ocorrer sem explicar quais etapas devem ser feitas para chegar naquele resultado (o objetico era encontrar parentes por parte de mãe e/ou pai).

  

