import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Persistencia {

    private static final String PASTA_USUARIOS = "usuarios";

    public static void inicializarSistema() {
        File pasta = new File(PASTA_USUARIOS);
        if (!pasta.exists()) {
            pasta.mkdir();
        }

        if (!new File(PASTA_USUARIOS + "/Joao.json").exists()) {
            Usuario joao = new Usuario("Joao");
            Serie s1 = new Serie("Breaking Bad", "Inglês", List.of("Drama", "Crime"), "Encerrada", "2008-01-20", "2013-09-29", "AMC");
            Serie s2 = new Serie("The Witcher", "Inglês", List.of("Ação", "Fantasia"), "Em andamento", "2019-12-20", "Ainda em exibição", "Netflix");
            joao.adicionar(joao.getFavoritos(), s1);
            joao.adicionar(joao.getAssistidas(), s2);
            salvar(joao);
        }

        if (!new File(PASTA_USUARIOS + "/Maria.json").exists()) {
            Usuario maria = new Usuario("Maria");
            Serie s1 = new Serie("Friends", "Inglês", List.of("Comédia", "Romance"), "Encerrada", "1994-09-22", "2004-05-06", "NBC");
            Serie s2 = new Serie("Stranger Things", "Inglês", List.of("Ficção", "Terror"), "Em andamento", "2016-07-15", "Ainda em exibição", "Netflix");
            maria.adicionar(maria.getFavoritos(), s2);
            maria.adicionar(maria.getParaAssistir(), s1);
            salvar(maria);
        }
    }

    public static void salvar(Usuario usuario) {
        try (PrintWriter out = new PrintWriter(new OutputStreamWriter(
                new FileOutputStream(PASTA_USUARIOS + "/" + usuario.getNome() + ".json"), "UTF-8"))) {
            out.println("{");
            out.println("\"nome\": \"" + usuario.getNome() + "\",");

            out.println("\"favoritos\": [");
            salvarLista(out, usuario.getFavoritos());
            out.println("],");

            out.println("\"assistidas\": [");
            salvarLista(out, usuario.getAssistidas());
            out.println("],");

            out.println("\"paraAssistir\": [");
            salvarLista(out, usuario.getParaAssistir());
            out.println("]");

            out.println("}");
        } catch (Exception e) {
            System.out.println("Erro ao salvar dados.");
        }
    }

    private static void salvarLista(PrintWriter out, List<Serie> lista) {
        for (int i = 0; i < lista.size(); i++) {
            Serie s = lista.get(i);
            out.print("{");
            out.print("\"nome\":\"" + s.getNome() + "\",");
            out.print("\"idioma\":\"" + s.getIdioma() + "\",");
            out.print("\"generos\":\"" + String.join(";", s.getGeneros()) + "\",");
            out.print("\"status\":\"" + s.getStatus() + "\",");
            out.print("\"estreia\":\"" + s.getDataEstreia() + "\",");
            out.print("\"fim\":\"" + s.getDataFim() + "\",");
            out.print("\"emissora\":\"" + s.getEmissora() + "\"");
            out.print("}");
            if (i < lista.size() - 1) {
                out.println(",");
            } else {
                out.println();
            }
        }
    }

    public static Usuario carregarUsuario(String nome) {
        File arquivo = new File(PASTA_USUARIOS + "/" + nome + ".json");
        if (!arquivo.exists()) {
            return null;
        }

        try (BufferedReader in = new BufferedReader(new InputStreamReader(
                new FileInputStream(arquivo), "UTF-8"))) {

            String linha;
            StringBuilder json = new StringBuilder();
            while ((linha = in.readLine()) != null) {
                json.append(linha);
            }

            String texto = json.toString();

            String nomeUsuario = extrair(texto, "\"nome\":\"", "\"");
            Usuario usuario = new Usuario(nomeUsuario);

            carregarLista(texto, "favoritos", usuario.getFavoritos());
            carregarLista(texto, "assistidas", usuario.getAssistidas());
            carregarLista(texto, "paraAssistir", usuario.getParaAssistir());

            return usuario;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void carregarLista(String json, String chave, List<Serie> lista) {
        String bloco = extrairBloco(json, chave);
        if (bloco.isEmpty()) return;

        int pos = 0;
        while (pos < bloco.length()) {
            int ini = bloco.indexOf("{", pos);
            int fim = bloco.indexOf("}", ini);
            if (ini == -1 || fim == -1) break;

            String item = bloco.substring(ini + 1, fim);

            String nome = extrair(item, "\"nome\":\"", "\"");
            String idioma = extrair(item, "\"idioma\":\"", "\"");
            String generosStr = extrair(item, "\"generos\":\"", "\"");
            List<String> generos = generosStr.isEmpty() ? new ArrayList<>() : List.of(generosStr.split(";"));
            String status = extrair(item, "\"status\":\"", "\"");
            String estreia = extrair(item, "\"estreia\":\"", "\"");
            String fimData = extrair(item, "\"fim\":\"", "\"");
            String emissora = extrair(item, "\"emissora\":\"", "\"");

            lista.add(new Serie(nome, idioma, generos, status, estreia, fimData, emissora));

            pos = fim + 1;
        }
    }

    private static String extrairBloco(String json, String chave) {
        String jsonFormatado = json.replace(" ", "").replace("\n", "").replace("\r", "");

        int inicio = jsonFormatado.indexOf("\"" + chave + "\":[");
        if (inicio == -1) {
            System.out.println("Chave não encontrada: " + chave);
            return "";
        }

        inicio = jsonFormatado.indexOf("[", inicio) + 1;
        int contador = 1;
        int pos = inicio;

        while (pos < jsonFormatado.length()) {
            char c = jsonFormatado.charAt(pos);
            if (c == '[') contador++;
            if (c == ']') contador--;

            if (contador == 0) break;
            pos++;
        }

        if (contador != 0) {
            System.out.println("Erro de fechamento de colchetes na chave: " + chave);
            return "";
        }

        return jsonFormatado.substring(inicio, pos).trim();
    }

    private static String extrair(String texto, String inicio, String fim) {
        String jsonFormatado = texto.replace(" ", "").replace("\n", "").replace("\r", "");

        int start = jsonFormatado.indexOf(inicio);
        if (start == -1) return "";
        start += inicio.length();
        int end = jsonFormatado.indexOf(fim, start);
        if (end == -1) return "";
        return jsonFormatado.substring(start, end);
    }

    public static List<String> listarUsuarios() {
        List<String> usuarios = new ArrayList<>();
        File pasta = new File(PASTA_USUARIOS);

        File[] arquivos = pasta.listFiles((dir, name) -> name.toLowerCase().endsWith(".json"));
        if (arquivos != null) {
            for (File arquivo : arquivos) {
                String nome = arquivo.getName().replace(".json", "");
                usuarios.add(nome);
            }
        }

        return usuarios;
    }
}