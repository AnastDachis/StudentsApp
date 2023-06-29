
public class Lesson {

    private String id, name;

    public Lesson() {
        id = "";
        name = "";
    }

    public Lesson(String l, String n) {
        id = l;
        name = n;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String toString() {
        String s = id + " , " + name;
        return s;
    }

}
