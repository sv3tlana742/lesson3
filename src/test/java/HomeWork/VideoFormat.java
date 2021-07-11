package HomeWork;

public enum VideoFormat {
    MP4("src/test/resources/NotImage.mp4"),
    AVI("src/test/resources/AVI-DIVX3.avi"),
    M4V("src/test/resources/MP4-H264.m4v"),
    TXT("src/test/resources/text.txt"),
    MKV("src/test/resources/MKV-H264.mkv");

    private final String title;

    VideoFormat(String title) {
        this.title = title;
    }

    public String getTitle(){
        return title;
    }
}
