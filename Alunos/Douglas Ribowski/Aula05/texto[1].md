Paradigmas de Programação: Imperativo vs Declarativo

#Paradigma Imperativo:

O paradigma imperativo é um modelo de programação baseado em comandos sequenciais que modificam o estado do programa. 
Nele, o programador descreve como o computador deve realizar uma tarefa, passo a passo, controlando 
o fluxo de execução por meio de estruturas como laços, condicionais e variáveis.

Java:

```markdown
public class ExemploImperativo {
    public static void main(String[] args) {
        int a = 10;
        int b = 5;
        int resultado = a + b; 
        System.out.println("Resultado da soma: " + resultado);
    }
}
```
___________________________________________________________________________________________________________
📝Explicar:

* public class ExemploImperativo → Define a classe principal do programa.

* main → Ponto de entrada do programa.

* int a = 10; e int b = 5; → Declaração e inicialização de variáveis.

* resultado = a + b; → Soma os valores.

* System.out.println(...) → Exibe o resultado para o usuário.

____________________________________________________________________________________________________________

#Paradigma Declarativo:

Diferente do imperativo, o paradigma declarativo foca em o que deve ser feito, e não em como fazer.
O programador descreve as relações lógicas e os resultados esperados, e o sistema se encarrega de encontrar o caminho para a solução.

Prolog:
```markdown

% Relações de parentesco
mae(trude, sally).
pai(tom, sally).
pai(tom, erica).
pai(mike, tom).

% Regras de inferência
irmaos(X, Y) :- genitor(Z, X), genitor(Z, Y), X \= Y.
genitor(X, Y) :- pai(X, Y).
genitor(X, Y) :- mae(X, Y).
```
____________________________________________________________________________________________________________

📝Explicar:

* mae(trude, sally). → Trude é mãe de Sally.

* pai(tom, sally). → Tom é pai de Sally.

* pai(mike, tom). → Mike é pai de Tom.

* irmaos(X, Y) → X e Y são irmãos se tiverem o mesmo genitor (pai ou mãe) e forem diferentes entre si.

* genitor(...) → Abstração que unifica pai e mãe como um mesmo tipo de relação.

_____________________________________________________________________________________________________________

🔍 Comparação entre os paradigmas:

No paradigma imperativo, o programador descreve passo a passo como o programa deve funcionar, controlando o fluxo e o estado. É como dar instruções detalhadas para resolver uma tarefa.

Já no paradigma declarativo, o foco está em dizer o que se quer, e não como fazer. O sistema interpreta as regras e chega ao resultado por conta própria.
______________________________________________________________________________________________________________

✅ Conclusão:

Enquanto o paradigma imperativo exige que o programador controle cada etapa da execução, o paradigma declarativo permite apenas declarar o que se espera como resultado, deixando os detalhes da execução por conta do sistema.
Ambos têm suas vantagens, e a escolha entre eles depende do tipo de problema que se quer resolver e da linguagem utilizada.