package HomeworkMain;

public enum ImageFormat {
    JPG("src/test/resources/500kb.jpg"),
    PNG("src/test/resources/PNG2.png"),
    GIF("src/test/resources/GIF.gif"),
    TIFF("src/test/resources/TIFF.tiff"),
    TXT("src/test/resources/text.txt"),

    TYPE_JPG("image/jpeg"),
    TYPE_PNG("image/png"),
    TYPE_GIF("image/gif"),
    TYPE_TIFF("image/jpeg");

    private final String title;

    ImageFormat(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
