package HomeworkMain;

public enum ImageSize {
    SMALL("src/test/resources/a1px.jpg"),
    MEDIUM("src/test/resources/a1200px.jpg"),
    HD("src/test/resources/a10000px.jpg"),
    WIDTH_SMALL(1),
    HEIGHT_SMALL(1),
    WIDTH_MEDIUM(1200),
    WIDTH_HD(10000);

    private String title;
    private int val;

    ImageSize(String title) {
        this.title = title;
    }

    ImageSize(int val){
        this.val = val;
    }

    public String getTitle() {
        return title;
    }

    public int getVal() {
        return val;
    }
}
