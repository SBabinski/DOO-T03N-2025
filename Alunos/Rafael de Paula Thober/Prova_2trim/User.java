import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class User {
    private String nickname;
    private Map<ListName, Set<Integer>> lists;

    public User(String nickname) {
        this.nickname = nickname;
        this.lists = new EnumMap<>(ListName.class);
        for (ListName list : ListName.values()) {
            lists.put(list, new HashSet<>());
        }
    }

    public String getNickname() {
        return nickname;
    }

    public boolean addToList(ListName list, int showId) {
        return lists.get(list).add(showId);
    }

    public boolean removeFromList(ListName list, int showId) {
        return lists.get(list).remove(showId);
    }

    public Set<Integer> getList(ListName list) {
        return lists.get(list);
    }

    // Métodos auxiliares para ConsoleApp
    public Set<Integer> getFavorites() {
        return getList(ListName.FAVORITES);
    }

    public Set<Integer> getWatched() {
        return getList(ListName.WATCHED);
    }

    public Set<Integer> getToWatch() {
        return getList(ListName.TO_WATCH);
    }

    public boolean isInList(int showId, ListName listName) {
    return lists.get(listName).contains(showId);
    } 

    public Map<ListName, Set<Integer>> getAllLists() {
        return lists;
    }
}
