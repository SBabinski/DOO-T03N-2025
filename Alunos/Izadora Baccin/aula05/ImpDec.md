# **Paradigmas de Programação: Imperativo vs Declarativo**

 Os paradigmas de programação definem diferentes formas de estruturar e organizar código para resolver problemas.

- **Paradigma Imperativo**: Ele foca em como a computação deve ser realizada, utilizando sequências de instruções que modificam o estado do programa, o paradigma imperativo tem como o foco principal o como fazer e não o que fazer.
- **Paradigma Declarativo**: Ele descreve o que deve ser feito sem especificar exatamente como, deixando que o sistema resolva a execução, ao nível computacional, estaríamos escrevendo um algoritmo de forma declarativa, onde passaríamos um passo a passo do que fazer para que o computador possa executar, porém, com um nível maior de abstração.

## **Exemplo: Escovar os Dentes**

 A seguir, um código escrito em **Java (imperativo)** e outro em **Prolog (declarativo)**, ambos representando a tarefa de escovar os dentes.

### **Código Java (Paradigma Imperativo)**
```java
public class EscovarDentes {
	public static void main(String[] args) {
    	pegarEscova();
    	aplicarPasta();
    	escovar();
    	enxaguarBoca();
	}

	static void pegarEscova() {
    	System.out.println("Pegando a escova de dentes.");
	}

	static void aplicarPasta() {
    	System.out.println("Aplicando pasta de dentes.");
	}

	static void escovar() {
    	System.out.println("Escovando os dentes.");
	}

	static void enxaguarBoca() {
    	System.out.println("Enxaguando a boca.");
	}
}
#### Agora em **Prolog (declarativo)**

escovar_dentes :-
	pegar_escova,
	adicionar_pasta,
	escovar,
	enxaguar_boca.

pegar_escova :- write('Pegando a escova de dentes.'), nl.
adicionar_pasta :- write('Aplicando pasta de dentes.'), nl.
escovar :- write('Escovando os dentes.'), nl.
enxaguar_boca :- write('Enxaguando a boca.'), nl.

##### **Comparação entre os Paradigmas**
-**Java (Imperativo):** Segue uma sequência clara de passos para realizar a tarefa, cada função representa uma ação específica, e a execução depende da ordem das chamadas.
-**Prolog (Declarativo):** Define regras e fatos sobre como escovar os dentes, permitindo que o sistema resolva a execução com base nas relações estabelecidas.


- Enquanto **Java** exige uma estrutura explícita com controle detalhado do fluxo, **Prolog** foca na descrição lógica do problema, deixando a execução para o interpretador.


