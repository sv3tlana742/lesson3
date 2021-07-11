package HomeWork;

public enum ImageFormat {
    JPG("src/test/resources/500kb.jpg"),
    GIF("src/test/resources/GIF.gif"),
    PNG("src/test/resources/PNG2.png"),
    TIFF("src/test/resources/TIFF.tiff"),
    TXT("src/test/resources/text.txt"),
    MP4_IMAGE("src/test/resources/NotImage.mp4");

    private final String title;

    ImageFormat(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
