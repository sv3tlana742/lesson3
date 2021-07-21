package HomeworkMain;

public enum ImageWeight {
    MIDDLE("src/test/resources/500kb.jpg"),
    BIG("src/test/resources/1mb.jpg"),
    MAX_SIZE("src/test/resources/10mb.jpg"),
    OVERSIZE("src/test/resources/10674MB.jpg");

    private final String title;

    ImageWeight(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
