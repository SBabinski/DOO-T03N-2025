# O Que é Paradigma
Os paradigmas na programação são formas de resolver problemas, guiar e dizer o que não se deve fazer na programação. 
Cada linguagem de programação deve seguir um conjunto de paradigma, para dessa forma, seguir um padrão.
Os paradigmas de programação ajudam a estabelecer padrões para escrever código. 
Quando um especialista muda para uma linguagem de programação que segue um paradigma com o qual está familiarizado, 
é mais fácil para ele superar as diferenças entre as linguagens.

## Paradigma Imperativo
O paradigma imperativo é uma forma de programar que se concentra em dar instruções claras sobre como realizar uma tarefa. 
Para você entender melhor, é como se fosse uma receita de cozinha, onde você segue passos específicos para preparar aquele 
prato de doce ou salgado que você gosta. 
Neste estilo de programação, podemos mudar o valor das coisas conforme precisarmos, sendo esses valores mutáveis, 
as variáveis, podemos criar loops, condicionais,  entre outras funções que podem ser atribuídas.

**Exemplo** 

>int somar(int a, int b) {
>
>   return a + b;
>
>}
>int multiplicar(int numero, int multiplicador) {
>
>    int resultado = 0;
>
>    for (int i = 0; i < multiplicador; i++) {
>
>        resultado += numero;
>
>    }
>
>    return resultado;
>
>}

Acima temos um exemplo de duas funções em java, onde na primeira função retorna o resultado de uma soma, e na segunda o resultado de uma multiplicação. Algo bem visivel no exemplo é o uso de variáveis, como maior exemplo temos o resultado, onde é usado em ambas as funções e mesmo assim possui resultados diversos.

## Paradigma Declarativo 
O paradigma declarativo é um paradigma de programação que foca em descrever o que deve ser alcançado, em vez de como alcançá-lo. Em vez de fornecer uma sequência de instruções detalhadas, o programador declara as propriedades e relações que o resultado final deve ter. 

**Exemplo** 

>   Caso base: A soma de uma lista vazia é 0. 
>
>   soma_números_ímpares([], 0). 
> 
>   Caso recursivo: se a cabeça da lista for ímpar, adicione-a à soma da cauda. 
>
>   soma_números_ímpares([H|T], Soma) :- 
>
>   H rem 2 =:= 1, % Verifique se H é ímpar 
>
>   sum_odd_numbers(T, TailSum), % Chamada recursiva na cauda 
>
>   A soma é H + TailSum. % Soma H com a soma da cauda. 
> 
>   Caso recursivo: se o cabeçalho da lista for par, pule-o. 
>
>   soma_números_ímpares([H|T], Soma) :- 
>
>   H rem 2 =:= 0, % Verifique se H é par  
>
>   sum_odd_numbers(T, Sum). % Continuar com a cauda. 

Acima temos um código em Prolog, esse código é de soma de números ímpares de uma lista de números esse estilo segui o paradigma declarativo, onde nos é mostrado um código bem distinto do Java.

## Paradigma Imperativo E Declarativo
Os paradigmas de programação, imperativo e declarativo, representam abordagens distintas para a resolução de problemas. No paradigma imperativo, o foco está em descrever explicitamente os passos necessários para atingir um resultado. Isso envolve a definição de variáveis, estruturas de controle de fluxo (como loops e condicionais) e a manipulação direta do estado do programa. Por outro lado, o paradigma declarativo se concentra em descrever o que deve ser alcançado, sem entrar em detalhes sobre como atingir esse objetivo. Essa abordagem confia mais na lógica e nas relações entre os elementos do problema.


