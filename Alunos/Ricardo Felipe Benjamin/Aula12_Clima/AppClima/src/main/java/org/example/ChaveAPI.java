package org.example;

import java.io.File;
import java.util.Scanner;

public class ChaveAPI {

    public static String carregarChave(String caminhoArquivo) {
        try {
            File arquivo = new File(caminhoArquivo);
            Scanner leitor = new Scanner(arquivo);
            String chave = leitor.nextLine().trim();
            leitor.close();
            return chave;
        } catch (Exception e) {
            System.out.println("Erro ao carregar a chave da API: " + e.getMessage());
            return null;
        }
    }
}
