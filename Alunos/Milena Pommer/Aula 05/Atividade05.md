# Paradigmas de Programação

## Introdução
	Paradigmas de programação são formas de solucionar problemas ao escrever um código. Trata-se de uma composição de regras, sendo que cada linguagem tem seu próprio conjunto desses paradigmas para guiar seus desenvolvedores. Estes, trazem como guia, regras e padronizações, como situações que devem ou não ocorrer dentro de um programa. 
	Para Robert Martin, consultor renomado de desenvolvimento, traz a seguinte definição: “Paradigmas são como limites nas formas como as linguagens são usadas, orientando um estilo particular de programação”.
	Existem alguns tipos de paradigmas existentes e utilizados no mundo da programação, porém, os dois mais utilizados são: o imperativo e o declarativo, onde cada um traz seus subtipos: programação estruturada e a orientada a objetos, e a programação funcional e lógica, respectivamente.

## Paradigma Imperativo
	O paradigma imperativo utiliza de instruções claras sobre a realização de tarefas, como um manual de instruções para montagem de um móvel ou uma receita de bolo, por exemplo. Em todas as situações, passos específicos são seguidos para realizar aquela determinada tarefa. 
	Neste estilo de programação, são utilizados comandos simples, como “atribuir um valor a uma variável” ou “fazer uma conta matemática”; É permitido mudar o valor das coisas conforme necessário, como o volume de uma TV, por exemplo; Também é possível tomar decisões, utilizando do “if/else”, ou seja, “se isso acontecer, faça aquilo”, ou repetir ações, como em um loop “for”, por exemplo; Além disso, os comandos de um código podem ser organizações em funções, para que possam ser reutilizados sempre que necessário, um exemplo de aplicação seria o “mexa bem” em uma receita.
	Utilizando desses passos, resulta-se em um código bem organizado e simples de ser entendido, fazendo com que o paradigma imperativo seja muito utilizado e ótimo para tarefas práticas, como lidar com dados e interagir com sistemas.
	
## Exemplo de aplicação - Paradigma Imperativo em Java 
	O Código a seguir se trata de um exemplo de aplicação do Paradigma Imperativo na linguagem Java, onde é realizada a soma dos valores de 1 a 5:
````
public class SomaValores {

	public static void main(String[] args) {
     
     	int soma = 0;                    // Criação de variável de valor 0.
     
     	for (int i = 1; i <= 5; i++) {  // Criação de loop (i até 5).
     
     	soma += i;                      // Soma-se o valor anterior com o total a cada volta do for.
		}

    System.out.println("A soma dos números de 1 a 5 é: " + soma); // Imprime o resultado da soma na tela.
	}
}
````

## Paradigma Declarativo
	O paradigma declarativo foca na descrição do que deve ser alcançado no código, diferente do imperativo, que foca na instrução de como alcançar aquele resultado. O programador declara as propriedades e relações que o resultado deve apresentar. A complexidade de como as operações são realizadas fica oculta.
	Por exemplo, utilizando um banco de dados para encontrar os clientes de uma determinada cidade, ao invés de escrever um algoritmo passo a passo que percorre cada registro, o desenvolvedor simplesmente utilizará uma consulta SQL que descreve o que deseja. Sendo muito importante quando o foco é o resultado, e não o seu processo.

## Exemplo de aplicação - Paradigma descritivo em Prolog
	O Código a seguir se trata de um exemplo de aplicação do Paradigma Descritivo na linguagem Prolog, onde é realizada a soma dos valores de 1 a 5:
````
    soma_n(N, S) :
    
    numlist(1, N, Lista),          // Criação de lista de números de 1 a n.
    
    sum_list(Lista, S).            // Soma os valores da lista.
    
    ?- soma_n(5, Resultado).      // Realiza o cálculo da soma de 1 a 5.
````

## Diferenças entre Paradigma Imperativo e Paradigma Declarativo
	Os paradigmas utilizam de abordagens distintas na resolução de problemas dentro do desenvolvimento de um código. No Imperativo, o foco se encontra na descrição dos passos necessários para atingir um resultado, utilizando de variáveis, estruturas como loops e condicionais e a manipulação direta do estado do programa, muito utilizado nas linguagens Python e Java. Já o Declarativo, foca na descrição do que deve ser alcançado, sem especificar seu passo a passo, utilizando da lógica e relações entre elementos do problema, geralmente utilizado nas linguagens SQL e Prolog.

## Escolha do Paradigma
	Para definir um paradigma, deve-se analisar o problema a ser resolvido e verificar qual o foco da questão: Seu processo ou seu resultado, além da linguagem utilizada, sendo que, em alguns casos, pode ser necessário uma combinação de ambos os paradigmas descritos. 

## Referências
[Referência 01](https://programae.org.br/programacao/quais-os-3-principais-paradigmas-da-programacao/)
[Referência 02](https://tripleten.com.br/blog/paradigmas-de-programacao-o-que-sao-e-quais-os-principais/)