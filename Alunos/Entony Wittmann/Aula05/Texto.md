# Modelos de Programação: Imperativo e Declarativo

## Inicio:

Os modelos de programação representam visões distintas sobre o ato de criar programas. Dois dos principais modelos vistos na aula teórica de 25/03/2025 foram o **imperativo** e o **declarativo**. O modelo imperativo se concentra em *como* um problema deve ser resolvido, especificando cada passo da sequência de instruções. Em contrapartida, o modelo declarativo enfatiza *o que* deve ser atingido, omitindo os pormenores da execução.

## Comparativo entre Código Imperativo e Declarativo:

Para mostrar a diferença entre esses modelos, serão examinados dois fragmentos de código, um escrito em **Java** (modelo imperativo) e outro em **Prolog** (modelo declarativo). Ambos os fragmentos visam determinar se um número é par.

### Exemplo em Java (Imperativo):

```java
public class Paridade {
   public static boolean ehPar(int numero) {
       return numero % 2 == 0;
   }
   
   public static void main(String[] args) {
       int numero = 4;
       System.out.println("O número " + numero + " é par? " + ehPar(numero));
   }
}
```

### Exemplo em Prolog (Declarativo):

```prolog
eh_par(N) :- N mod 2 =:= 0.
```

## Análise Comparativa

### Java (Imperativo):

No exemplo em Java, é preciso definir uma estrutura clara de controle do fluxo. A função `ehPar` calcula o resto da divisão do número por 2 e devolve verdadeiro se for zero, sinalizando que o número é par. O programa também necessita de um método `main` para invocar essa função e exibir o resultado, evidenciando a natureza imperativa do modelo, onde cada fase da execução é detalhada pelo programador.

### Prolog (Declarativo):

Em contrapartida, no código em Prolog, a regra `eh_par/1` apenas declara a relação matemática para um número ser par, sem dizer como a verificação deve ser efetuada. O motor de inferência do Prolog realiza a verificação de forma automática quando a consulta é feita (`eh_par(4).` retornaria verdadeiro). Esse modelo omite o fluxo de controle, aproximando a execução da lógica matemática.

## Resumindo:

A diferença entre os modelos fica nítida na maneira como se resolve um problema simples. O modelo imperativo exige que o programador especifique de forma clara cada passo necessário para se chegar à resposta. Por outro lado, o modelo declarativo permite indicar a relação desejada, deixando a execução a cargo do sistema. Essa distinção tem impactos relevantes na escolha do modelo para diferentes tipos de programas, dependendo das necessidades de controle e abstração do problema.