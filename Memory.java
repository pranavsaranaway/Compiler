import java.util.ArrayList;

public class Memory {

    private ArrayList<Number> intMemory = new ArrayList();

    private ArrayList<Text> stringMemory = new ArrayList<>();

    private ArrayList<computations> mathMemory = new ArrayList<>();

    private ArrayList<compare> compareMemory = new ArrayList<>();
    public void setMathMemory(computations a ){
        mathMemory.add(a);
    }
    public void setCompareMemory(compare a){
        compareMemory.add(a);
    }
    public void setIntMemory(Number a) {
        intMemory.add(a);
    }

    public void setStringMemory(Text a) {
        stringMemory.add(a);
    }

    public Number getInt(int index) {
        return intMemory.get(index);
    }

    public Text getString(int index) {
        return stringMemory.get(index);
    }

    public computations getMath(int index){return mathMemory.get(index);}

    public compare getCompare (int index){return compareMemory.get(index);}

    public ArrayList<Number> getInt() {
        return intMemory;
    }

    public ArrayList<Text> getString() {
        return stringMemory;
    }
}
