public class compare {

    public String svar1;
    public String svar2;
    public int ivar1;
    public int ivar2;
    public String operator;
    int index;


    public compare (String svar1, String svar2, String operator, int index){
        this.svar1 = svar1;
        this.svar2 = svar2;
        this.operator = operator;
        this.index = index;
    }
    public compare(int ivar1, int ivar2, String operator, int index){
        this.ivar1 = ivar1;
        this.ivar2 =ivar2;
        this.operator = operator;
        this.index = index;
    }
    public boolean compute(){
        if (operator.equals(">")){
            if (ivar1 > ivar2)
                return true;
        }
        if (operator.equals("<")){
            if (ivar1 < ivar2)
                return true;
        }
        if (operator.equals("==")){
            if (ivar1 == ivar2)
                return true;
            if (svar1.equals(svar2))
                return true;
        }
        return false;
    }
}
