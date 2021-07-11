package HomeWork;

public enum ImageSize {
    SMALL("src/test/resources/a1px.jpg"),
    MEDIUM("src/test/resources/a1200px.jpg"),
    HD("src/test/resources/a10000px.jpg");
    private final String title;

    ImageSize(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
