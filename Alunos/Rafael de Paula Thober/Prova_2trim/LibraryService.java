import java.util.*;
import java.io.*;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

public class LibraryService {

    private static final String LIBRARY_FILE = "library.json";
    private final Map<String, UserLibrary> userLibraries = new HashMap<>();
    private final TvMazeClient client = new TvMazeClient();

    // Carrega os dados do arquivo ao iniciar
    public LibraryService() {
        loadLibrary();
    }

    // Representação das listas de cada usuário
    private static class UserLibrary {
        Set<Integer> FAVORITES = new HashSet<>();
        Set<Integer> WATCHED = new HashSet<>();
        Set<Integer> TO_WATCH = new HashSet<>();
    }

    private UserLibrary getUserLibrary(String nickname) {
        return userLibraries.computeIfAbsent(nickname, k -> new UserLibrary());
    }

    public Set<Integer> getList(String nickname, ListName listName) {
        UserLibrary ul = getUserLibrary(nickname);
        return switch (listName) {
            case FAVORITES -> ul.FAVORITES;
            case WATCHED -> ul.WATCHED;
            case TO_WATCH -> ul.TO_WATCH;
        };
    }

    public void addToList(String nickname, int showId, ListName listName) {
        getList(nickname, listName).add(showId);
        saveLibrary();
    }

    public void removeFromList(String nickname, int showId, ListName listName) {
        getList(nickname, listName).remove(showId);
        saveLibrary();
    }

    public boolean isInList(String nickname, int showId, ListName listName) {
        return getList(nickname, listName).contains(showId);
    }

    public List<Show> getFullList(String nickname, ListName listName) {
        Set<Integer> ids = getList(nickname, listName);
        List<Show> shows = new ArrayList<>();
        for (int id : ids) {
            Show show = client.getShowById(id);
            if (show != null) {
                shows.add(show);
            }
        }
        return shows;
    }

    // Métodos de ordenação (inalterados)
    public List<Show> sortByName(List<Show> list) {
        list.sort(Comparator.comparing(Show::getName, String.CASE_INSENSITIVE_ORDER));
        return list;
    }
    public List<Show> sortByRating(List<Show> list) {
        list.sort(Comparator.comparingDouble(Show::getRating).reversed());
        return list;
    }
    public List<Show> sortByStatus(List<Show> list) {
        list.sort(Comparator.comparing(Show::getStatus));
        return list;
    }
    public List<Show> sortByPremiered(List<Show> list) {
        list.sort(Comparator.comparing(Show::getPremiered, Comparator.nullsLast(String::compareTo)));
        return list;
    }

    // Persistência: salvar no arquivo JSON
    private void saveLibrary() {
        try (Writer writer = new FileWriter(LIBRARY_FILE)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(userLibraries, writer);
        } catch (IOException e) {
            System.out.println("[Erro] Não foi possível salvar library.json: " + e.getMessage());
        }
    }

    // Persistência: carregar do arquivo JSON
    private void loadLibrary() {
        File file = new File(LIBRARY_FILE);
        if (!file.exists()) return;
        try (Reader reader = new FileReader(LIBRARY_FILE)) {
            Gson gson = new Gson();
            Map<String, UserLibrary> loaded = gson.fromJson(reader, new TypeToken<Map<String, UserLibrary>>(){}.getType());
            if (loaded != null) {
                userLibraries.putAll(loaded);
            }
        } catch (IOException e) {
            System.out.println("[Erro] Não foi possível carregar library.json: " + e.getMessage());
        }
    }
}