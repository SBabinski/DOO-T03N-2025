# Trabalho diferença entre paradigmas

## Definição paradigmas na programação
Os paradigmas de programação são formas de abordar e resolver problemas ao escrever código, funcionando como metodologias ou estilos de programação. Eles não são linguagens de programação, mas sim maneiras de estruturar a lógica e a solução dos problemas por meio do código. Cada linguagem de programação é criada com base em determinados paradigmas e segue um conjunto de regras específicas para sua implementação.


## Paradigma imperativo
O paradigma imperativo é uma forma de programar que se concentra em dar instruções claras sobre como realizar uma tarefa. Pode-se mudar o valor das coisas conforme necessário, tomar decisões e repetir ações diversas vezes.

**Exemplo código com paradigma imperativo:**
````
import java.util.Scanner;

public class Multiplicacao {
    public static void main(String[] args) {
        // Criando o objeto Scanner para entrada do usuário
        Scanner scanner = new Scanner(System.in);

        // Solicitando ao usuário os dois valores
        System.out.print("Digite o primeiro número: ");
        double num1 = scanner.nextDouble();

        System.out.print("Digite o segundo número: ");
        double num2 = scanner.nextDouble();

        // Realizando a multiplicação
        double resultado = num1 * num2;

        // Exibindo o resultado
        System.out.println("O resultado da multiplicação é: " + resultado);

        // Fechando o scanner
        scanner.close();
    }
}
````
**Explicação do código:**

Main(String[] args) → Ponto de entrada do programa. O Java sempre inicia a execução pelo método main.

System.in → Indica que a entrada virá do teclado.

System.out.print → Exibe a mensagem na tela sem pular para a próxima linha.

Double num1 = scanner.nextDouble() → Captura o valor digitado pelo usuário e armazena na variável num1.

Double resultado = num1 * num2  → Multiplica os valores armazenados em num1 e num2 e salva o resultado na variável resultado.

System.out.println() → Exibe o texto na tela e pula para a próxima linha.


Concatenação (+ resultado) → Junta o texto "O resultado da multiplicação é: " com o valor da variável resultado.



## Paradigma declarativo
O paradigma declarativo é um paradigma de programação que foca em descrever o que deve ser alcançado, em vez de como alcançá-lo. Em vez de fornecer uma sequência de instruções detalhadas, o programador declara as propriedades e relações que o resultado final deve ter. 

**Exemplo de código com paradigma declarativo:**
````
% Regra para realizar a multiplicação
multiplicar(Num1, Num2, Resultado) :- 
    Resultado is Num1 * Num2.

% Predicado principal para interação com o usuário
main :- 
    write('Digite o primeiro número: '), 
    read(Num1),
    write('Digite o segundo número: '), 
    read(Num2),
    multiplicar(Num1, Num2, Resultado),
    write('O resultado da multiplicação é: '), 
    write(Resultado), nl.
````

**Explicação do código:**

Multiplicar → Define um predicado com três argumentos: Num1, Num2 e Resultado.

Resultado is Num1 * Num2 → Realiza a multiplicação e unifica (is) o valor em Resultado.

Write → exibe uma mensagem no console.

Read → captura o valor digitado e unifica com Num1.

Multiplicar(Num1, Num2, Resultado)→ prolog verifica a relação e encontra Resultado.

## Conclusão
Os paradigmas de programação influenciam diretamente a forma como os desenvolvedores escrevem e organizam seus códigos. O paradigma imperativo se concentra na execução de instruções passo a passo, enquanto o declarativo foca na definição de regras e relações para que o próprio sistema resolva o problema. Ambos têm suas vantagens e são usados conforme a necessidade do projeto. Compreender essas abordagens permite escolher a melhor estratégia para desenvolver soluções eficientes e organizadas.

