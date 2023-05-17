package data;

public enum CoursesCategoryData {
    Progamming("Программирование");

    private String name;

    CoursesCategoryData(String name) {
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
