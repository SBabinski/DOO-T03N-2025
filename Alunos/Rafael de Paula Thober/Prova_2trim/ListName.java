public enum ListName {
    FAVORITES("Favoritos"),
    WATCHED("Já assistidas"),
    TO_WATCH("Quero assistir");

    private final String label;

    ListName(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
