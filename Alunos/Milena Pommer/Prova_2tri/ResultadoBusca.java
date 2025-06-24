package com.trabalhotvmaze.series;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/** 
 * Essa classe funciona como molde pra resultado individual de busca da API.
 * Como se fosse uma caixa com outra caixa dentro chamada "show". Dentro de "show estão as informações da série buscada.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
// Ignora informações desnecessárias do Json pra essa classe.
public class ResultadoBusca {
    @JsonProperty("show")
    private Serie show;
    // O "show" é um objeto do tipo Serie. O tradutor busca dentro do "show" a série pesquisada.
    //Getter pra acessar o objeto Serie.
    public Serie getShow() {
        return show;
    }
}