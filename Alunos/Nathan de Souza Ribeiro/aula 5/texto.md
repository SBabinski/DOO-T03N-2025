# Paradigmas de programação: imperativo e declarativo

os paradigmas de programação definem diferentes abordagens para resolver problemas computacionais. Dois dos principais paradigmas são:

> **imperativo**: Baseado na especificação de sequências de comandos para modificar o estado do programa. Exemplos incluem linguagens como C, Java e Python.

> **declarativo**: Especifica **o que** deve ser feito, sem definir exatamente *como* fazer. Exemplos incluem SQL, Prolog e Haskell.

# Comparação entre java (imperativo) e prolog (eclarativo)

## exemplo de codigo em java (imperativo)
```java
public class Fatorial {
    public static int calcularFatorial(int n) {
        int resultado = 1;
        for (int i = 2; i <= n; i++) {
            resultado *= i;
        }
        return resultado;
    }
    
    public static void main(String[] args) {
        System.out.println(calcularFatorial(5));
    }
}
```

**como funciona:**
- um loop `for` percorre de 2 até `n`, multiplicando os valores sucessivamente.
- o estado da variável `resultado` é atualizado a cada iteração.
- o programa executa comandos sequenciais, alterando o estado do sistema.

# exemplo de codigo em prolog (declarativo)
```prolog
fatorial(0, 1).
fatorial(N, F) :-
    N > 0,
    N1 is N - 1,
    fatorial(N1, F1),
    F is N * F1.
```

**como funciona:**
> define-se um caso base (`fatorial(0,1)`).

> a relação recursiva reduz `N` até o caso base.

> o Prolog usa *unificação* e *backtracking* para resolver a consulta automaticamente.

> diferente de Java, Prolog não modifica estados explicitamente, mas define relações lógicas para encontrar a solução.

# conclusão
 **java (imperativo)**: O programador descreve explicitamente os passos necessários para calcular o fatorial, alterando o estado do programa. **Prolog (declarativo)**: O programador define regras lógicas e o mecanismo da linguagem encontra a solução automaticamente.


