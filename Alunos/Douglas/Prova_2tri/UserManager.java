package fag;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class UserManager {
    private static final String USER_FILE = "user.json";
    private static ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    public static User loadUser() {
        File file = new File(USER_FILE);

        if (file.exists()) {
            try {
                return mapper.readValue(file, User.class);
            } catch (IOException e) {
                System.out.println("Erro ao ler o arquivo de usuário.");
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void saveUser(User user) {
        try {
            mapper.writeValue(new File(USER_FILE), user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static User configUser(Scanner scanner) {
        User extUser = loadUser();

        if (extUser != null) {
            System.out.println("Usuário atual: " + extUser.getName());

            String resposta = "";
            while (true) {
                System.out.print("Deseja continuar com esse usuário? (S/N): ");
                resposta = scanner.nextLine().trim();

                if (resposta.equalsIgnoreCase("S")) {
                    return extUser;
                } else if (resposta.equalsIgnoreCase("N")) {
                    break;
                } else {
                    System.out.println("Resposta inválida! Digite apenas 'S' ou 'N'.");
                }
            }
        }

        String nome = "";
        while (nome.trim().isEmpty()) {
            System.out.print("Digite seu nome ou apelido: ");
            nome = scanner.nextLine().trim();

            if (nome.isEmpty()) {
                System.out.println("Nome inválido! Por favor, digite um nome não vazio.");
            }
        }

        User newUser = new User(nome);
        saveUser(newUser);

        return newUser;
    }
}