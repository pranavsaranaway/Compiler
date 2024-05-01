import java.util.ArrayList;

public class computations {

    private String type;
    private int index;
    private ArrayList<Integer> values = new ArrayList<>();

    public computations (String type, ArrayList<Integer> values, int index){
        this.type = type;
        this.values =  values;
        this.index = index;
    }
    public int compute(){
        Integer result = 0;
        if (type.equals("+")){
            for (int i = 0; i < values.size(); i++){
                result += values.get(i);
            }
            return result;
        }
        if (type.equals("-")){
            result = values.get(0);
            for (int i = 1; i < values.size(); i++){
                result -= values.get(i);
            }
            return result;
        }
        if (type.equals("*")){
            result = values.get(0);
            for (int i = 1; i < values.size(); i++){
                result*= values.get(i);
            }
            return result;
        }
        if (type.equals("/")){
            result = values.get(0);
            for (int i = 1; i < values.size(); i++){
                result /= values.get(i);
            }
            return result;
        }
        if (type.equals("%")){
            result = values.get(0);
            for (int i = 1; i < values.size(); i++){
                result %= values.get(i);
            }
            return result;
        }
        return 0;
    }
}
