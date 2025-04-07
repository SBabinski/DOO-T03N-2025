# Paradigmas de Programação: Imperativo vs. Declarativo

Quando programamos, podemos seguir diferentes abordagens para resolver um problema. Dois dos paradigmas mais comuns são o **imperativo** e o **declarativo**, que foram discutidos na aula do dia 25/03/2025.

## Paradigma Imperativo

O paradigma imperativo foca em **dizer ao computador exatamente o que fazer, passo a passo**. Ele se baseia na mudança de estado e no uso de instruções sequenciais, como loops e condicionais. É o modelo mais intuitivo para quem está começando a programar, já que se parece muito com a forma como explicamos tarefas do dia a dia. Linguagens como **Java, C e Python** seguem esse estilo.

## Paradigma Declarativo

Já o paradigma declarativo é mais focado em **descrever o que queremos alcançar, sem especificar como chegar lá**. Em vez de detalhar cada passo, definimos regras e deixamos que o sistema resolva a execução. Isso pode parecer menos intuitivo no início, mas é muito útil para problemas complexos. Linguagens como **Prolog, SQL e Haskell** utilizam essa abordagem.

## Comparação entre Implementações em Java e Prolog

Vamos comparar dois pequenos programas que resolvem o mesmo problema: calcular o dobro de um número. O código em Java segue o estilo imperativo, enquanto o de Prolog usa a abordagem declarativa.

### Implementação em Java (Paradigma Imperativo)

```java
public class Dobro {
    public static int calcularDobro(int n) {
        return n * 2;
    }
    
    public static void main(String[] args) {
        int numero = 5;
        System.out.println("O dobro de " + numero + " é " + calcularDobro(numero));
    }
}
```

### Implementação em Prolog (Paradigma Declarativo)

```prolog
dobro(N, D) :- D is N * 2.
```

### Diferença entre as abordagens

- **Java (Imperativo)**: Criamos uma função que recebe um número, faz a conta e retorna o resultado. Depois chamamos essa função explicitamente.
- **Prolog (Declarativo)**: Apenas declaramos a relação entre os números, e o Prolog faz o cálculo automaticamente quando consultamos `dobro(5, X)`, por exemplo.

No fim das contas, a escolha do paradigma depende do problema que estamos tentando resolver. Para tarefas mais diretas, como manipular listas ou processar dados passo a passo, o imperativo funciona muito bem. Já para problemas lógicos ou consultas a bancos de dados, o declarativo pode ser mais eficiente e intuitivo.
