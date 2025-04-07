# Trabalho: diferença entre paradigmas

## O que são paradigmas de programação?
Os paradigmas de programação são basicamente formas de abordar e resolver problemas ao escrever código. Eles são como conjuntos de regras que guiam os desenvolvedores. Cada linguagem de programação é criada com base nessas regras. Cada uma tem seu próprio conjunto de paradigmas que os desenvolvedores seguem ao escrever o código.

Existem dois principais paradigmas: o imperativo e o declarativo. Cada um deles inclui subtipos. O imperativo abrange a programação estruturada e a orientada a objetos (OOP), enquanto o declarativo engloba a programação funcional e lógica.


## Paradigma Imperativo
O paradigma imperativo é uma forma de programar que se concentra em dar instruções claras sobre como realizar uma tarefa. Para você entender melhor, é como se fosse uma receita de cozinha, onde você segue passos específicos para preparar aquele prato de doce ou salgado que você gosta. 

## Paradigma Declarativo
O paradigma declarativo é uma um paradigma de programação que foca em descrever o que deve ser alcançado, em vez de como alcançá-lo. Em vez de fornecer uma sequência de instruções detalhadas, o programador declara as propriedades e relações que o resultado final deve ter. 

## Código em Java com paradigma imperativo:
 O código a seguir irá realizar a soma de valores:

````
import java.util.Scanner;

public class SomaImperativa { 

    public static void main(String[] args) {
        // Criando um scanner para entrada do usuário
        Scanner scanner = new Scanner(System.in);
        
        // Pedindo ao usuário que insira dois números
        System.out.print("Digite o primeiro número: ");
        int numero1 = scanner.nextInt();
        
        System.out.print("Digite o segundo número: ");
        int numero2 = scanner.nextInt();
        
        // Somando os números
        int soma = numero1 + numero2;
        
        // Exibindo o resultado
        System.out.println("A soma dos números é: " + soma);
        
        // Fechando o scanner
        scanner.close();
    }
}
````
## Explicação do Código em Java (Paradigma Imperativo)

** Linhas: **

- ``public static void main(String[] args) { ``

Declara o método principal main, onde o programa começa a execução.

- ``Scanner scanner = new Scanner(System.in);``

Cria um objeto Scanner para capturar dados digitados pelo usuário.

- ``System.out.print("Digite o primeiro número: ");``
- ``int numero1 = scanner.nextInt();``

 Exibe a mensagem "Digite o primeiro número: " e armazena o valor digitado em numero1.

- ``System.out.print("Digite o segundo número: ");``
- ``int numero2 = scanner.nextInt();``

Exibe "Digite o segundo número: " e armazena o valor digitado em numero2.

- ``int soma = numero1 + numero2;``

Realiza a soma dos dois números e armazena o resultado na variável soma.

- ``System.out.println("A soma dos números é: " + soma);``

Exibe o resultado da soma na tela.

- ``scanner.close();``

Fecha o objeto Scanner para liberar recursos do sistema.


## Código em prolog com paradigma declarativo:
 O código a seguir irá realizar a soma de valores:
 
````
% Definição de uma regra para soma
soma(X, Y, Resultado) :- Resultado is X + Y.

% Regra principal para iniciar o programa
main :-
    write('Digite o primeiro número: '),
    read(X),
    write('Digite o segundo número: '),
    read(Y),
    soma(X, Y, Resultado),
    write('A soma dos números é: '),
    write(Resultado), nl.
````

## Explicação do Código em Prolog (Paradigma declarativo)

** Linhas: **

-`` % Definição de uma regra para soma``
- ``soma(X, Y, Resultado) :- Resultado is X + Y.``

Define a regra soma/3, que descreve a relação entre X, Y e Resultado.
O operador is realiza a operação matemática.

-`` % Regra principal para iniciar o programa``
-``main :-``

Define o predicado main, que funciona como o ponto de entrada do programa.

- ``write('Digite o primeiro número: '),``

Exibe a mensagem "Digite o primeiro número: " no terminal.

- ``read(X),``

Lê um número digitado pelo usuário e o armazena na variável X.

- ``write('Digite o segundo número: '),``
- ``read(Y),``

Exibe "Digite o segundo número: ", lê o valor digitado e o armazena na variável Y.

- ``soma(X, Y, Resultado),``

Chama a regra soma/3, passando X e Y como entrada e obtendo Resultado.

- ``write('A soma dos números é: '),``
- ``write(Resultado), nl.``

Exibe a mensagem "A soma dos números é: " e imprime Resultado.


## Conclusão
 
 Os paradigmas de programação desempenham um papel essencial no desenvolvimento de software, oferecendo diferentes abordagens para resolver problemas computacionais. O paradigma imperativo se concentra em descrever passo a passo como uma tarefa deve ser realizada, enquanto o paradigma declarativo descreve o que deve ser alcançado, delegando a lógica da execução ao interpretador ou compilador.


