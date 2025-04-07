# Paradigmas de programação

### Introducao

**O que é**

São basicamente formas de abordar e resolver problemas ao escrever códigos. São conjuntos de regras que guiam os desenvolvedores, os paradigmas ajudam a estabelecer padrões para escrever um código. Existem dois principais paradigmas, Imperativo e descritivo.
O imperativo abrange a programação orientada a objetos (OOP), enquanto o declarativo engloba a programação funcional e lógica.


### Paradigmas - Imperativo e Declarativo 

**Paradigma Imperativo**

É uma forma de programar que se concentra em dar instruções claras sobre como realizar uma tarefa. Como uma receita, tem um passo a passo a ser feito para concluir a receita. Neste estilo, podemos mudar o valor das coisas conforme a necessidade, fazer calculos, tomar decisões e usar loops. Também pode ser utilizado funções para que o codigo seja reutilizavel. 


**Paradigma Descritivo**

Foca em descrever o que deve ser feito, mas não como deve ser feito, não fornece instruções detalhadas. O programador declara as propriedades e relações que o resultado final deve ter. 



###Descrição codigo imperativo - java

Este é um codigo em java que soma todos os valores de 1 a 10.

** Codigo Imperativo - Java **

````
	public class ExemploAula {

     public static void main(String[] args) {
     
     int soma = 0;           // passo 1 : criar a variavel e dar valor 0;
     
     for (int i = 1; i <= 10; i++) {             // passo 2 : criar um loop onde i vai até 10 e para;
     
     soma += i;                // passo 3 : cada volta que o loop faz é feita a soma com o valor anterior ;
     
}

    System.out.println("A soma dos números de 1 a 10 é: " + soma);      // passo 4: apresenta para o usuario a soma dos valores.
    
}
}
 ````

###Descrição do codigo  Descritivo - Prolog 

Este é um codigo em Prolog que também conta os valores de 1 a 10.

**Codigo Descritivo - Prolog**


````
    soma_n(N, S) :
    numlist(1, N, Lista),          //Cria uma lista de números de 1 a n;
    sum_list(Lista, S).            // Soma todos os números da lista;
 

    ?- soma_n(10, Resultado).      // calcula a soma de 1 a 10.
````


## Conclusão 

Podemos perceber a diferença entre os dois, o Imperativo é mais claro pode se perceber o passo a passo pelas linhas do codigo, simples de entender, já o descritivo é mais complexo, não é simples de entender o passo a passo.



## Referências 

[Referências da pesquisa](https://tripleten.com.br/blog/paradigmas-de-programacao-o-que-sao-e-quais-os-principais/)


