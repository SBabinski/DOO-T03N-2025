# Paradigmas de Programação: Imperativo vs. Declarativo

A programação pode ser categorizada em diversos paradigmas, sendo dois dos principais o **imperativo** e o **declarativo**. Ambos possuem características distintas e são aplicáveis a diferentes contextos de desenvolvimento de software.

## Paradigma Imperativo
O paradigma **imperativo** se baseia na definição explícita de **sequências de comandos** que alteram o estado do programa. Linguagens como **Java, C e Python** seguem esse modelo, onde o programador especifica passo a passo como um problema deve ser resolvido. Esse paradigma é semelhante a dar instruções detalhadas a um computador sobre como realizar determinada tarefa.

### Exemplo em Java
A seguir, um exemplo de código em **Java** que calcula o fatorial de um número de maneira imperativa:

```java
public class Fatorial {
    public static int calcularFatorial(int n) {
        int resultado = 1;
        for (int i = 1; i <= n; i++) {
            resultado *= i;
        }
        return resultado;
    }

    public static void main(String[] args) {
        System.out.println(calcularFatorial(5)); 
    }
}
```

Neste código:
- O programa manipula variáveis (`resultado` e `i`) para calcular o fatorial.
- O laço `for` percorre os números de `1` até `n`, multiplicando o valor acumulado.
- Há um controle explícito do fluxo de execução.

## Paradigma Declarativo
Diferentemente do imperativo, o paradigma **declarativo** foca na **descrição do que deve ser feito**, sem detalhar exatamente como. Esse estilo é comum em linguagens como **Prolog, SQL e Haskell**. A lógica do programa é baseada em regras e relações, deixando a resolução do problema para o motor de inferência da linguagem.

### Exemplo em Prolog
Agora, veja a mesma funcionalidade implementada em **Prolog**:

```prolog
fatorial(0, 1).
fatorial(N, F) :-
    N > 0,
    N1 is N - 1,
    fatorial(N1, F1),
    F is N * F1.
```

Neste código:
- Utiliza-se **recursão** para definir a relação entre `N` e seu fatorial `F`.
- O primeiro fato (`fatorial(0, 1)`) define que o fatorial de `0` é `1`.
- A regra recursiva indica que o fatorial de `N` depende do fatorial de `N-1`.
- O Prolog resolve essa relação automaticamente, sem que o programador controle a execução passo a passo.

## Comparação entre os Paradigmas
| Característica  | Imperativo (Java) | Declarativo (Prolog) |
|---------------|----------------|----------------|
| Controle de fluxo | Explícito (laços e variáveis) | Implícito (regras e recursão) |
| Como funciona? | Passo a passo, modificando estados | Descreve relações, deixando a inferência para o interpretador |
| Facilidade de depuração | Maior controle, mas mais suscetível a erros lógicos | Mais abstrato, podendo ser difícil de entender |
| Aplicabilidade | Sistemas interativos, aplicações empresariais | Inteligência artificial, bancos de dados lógicos |

## Conclusão
Os paradigmas **imperativo** e **declarativo** oferecem abordagens diferentes para resolver problemas computacionais. Enquanto o imperativo fornece controle detalhado sobre o fluxo do programa, o declarativo se concentra mais na definição do que deve ser feito. A escolha entre eles depende do contexto e dos requisitos do projeto.
