public class Text {
    public String name;
    public String value;
    public int pos;

    public Text(String name, String value, int pos) {
        this.pos = pos;
        this.name = name;
        this.value = value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public int getPos() {
        return pos;
    }

    public String toString() {
        return " " + value + " ";
    }

}
