
public class Number {
    private int value;
    private int memPos;
    private String name;


    public Number (int numer, int memPos, String name){
        this.value = numer;
        this.memPos = memPos;
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public int getValue(){
        return value;
    }
    public void setValue(int a){
        this.value = 0;
        this.value =a;
    }

    @Override
    public String toString() {
        return " " + value + " ";
    }
}
