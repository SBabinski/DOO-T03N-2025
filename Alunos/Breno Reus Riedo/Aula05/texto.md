# Paradigmas Imperativo e Declarativo


## Paradigma Imperativo

Neste paradigma, o programador especifica detalhadamente os passos que a máquina deve seguir para alcançar um determinado resultado. O foco está no **"como"** realizar as operações, descrevendo a sequência de comandos e mudanças de estado necessárias. Linguagens como **C**, **Java** e **Python** suportam esse estilo de programação.

## Paradigma Declarativo

Por outro lado, o paradigma declarativo concentra-se no **"quê"** deve ser feito, descrevendo o resultado desejado sem especificar os passos exatos para alcançá-lo. O programador define as propriedades e condições que a solução deve satisfazer, deixando que o sistema determine a melhor forma de execução. Exemplo de linguagem declarativa é o **Prolog** no caso de desse exercício para programação lógica.


## Exemplos
### Linguagem Java

Java é uma linguagem de programação que usa o Paradigma Imperativo, esse código em Java criará um arquivo chamado *exemplo.txt*, usando o Paradigma Imperativo, ou seja, a partir de comandos escritos, ele realizará ações especificas e diretas, começando de cima para baixo.

```java
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CriarArquivo {
    public static void main(String[] args) {
        try (FileWriter escritor = new FileWriter(new File("exemplo.txt"))) {
            escritor.write("Conteúdo do arquivo");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```
### Linguagem Prolog

Prolog é uma linguagem de programação que usa o Paradigma Declarativo, diferente do Java, o programador em Prolog descreve os fatos e as regras que representam o domínio do problema. E o sistema se encarrega de deduzir as respostas baseadas nessas informações quando requisitadas pelo programador posteriormente.

```prolog
% Fatos sobre arquivos (nome e tipo)
arquivo('exemplo.txt', 'texto').
arquivo('imagem.jpg', 'imagem').
arquivo('documento.pdf', 'pdf').

% Regras para buscar arquivos por nome ou tipo
buscar_por_nome(Nome, Tipo) :-
    arquivo(Nome, Tipo).

buscar_por_tipo(Tipo, Nome) :-
    arquivo(Nome, Tipo).
```

Nas primeiras linhas, são definidos fatos sobre arquivos, dizendo que um arquivo compoe de um nome e tipo que são fornecidos acima, criando 3 arquivos diferentes.  
Na definição de regras, são criadas 2 regras, a regra buscar\_por\_nome buscará arquivos com o nome fornecido pelo programador na hora da consulta e na regra buscar\_por\_tipo fará o mesmo só que com o tipo fornecido na hora da consulta.

#### Consultando em Prolog

Quando o programador digitar a consulta
```prolog
?- buscar_por_nome('exemplo.txt', Tipo).
```
Ele vai responder
```prolog
Tipo = 'texto'.
```
A linguagem usou por fatos e regras feitas pelo programador para achar qual o arquivo

# Conclusão

Os paradigmas de programação imperativo e declarativo representam abordagens diferentes para resolver problemas de programação:

Imperativo: Foca no "como" realizar as operações, exigindo que o programador forneça todos os detalhes do processo.

Declarativo: Foca no "quê" deve ser feito, deixando o sistema determinar como realizar a tarefa com base nas condições fornecidas.