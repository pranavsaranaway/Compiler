//Pranav Saran
//Period 4

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    public static boolean containsString(String mainString, String searchString) {
        return mainString.contains(searchString);
    }

    public static void main(String[] args) throws FileNotFoundException {

        File file = new File("C:\\Users\\Orange\\Desktop\\AP CSA\\Compiler\\src\\Compiler.txt");
        Scanner scan = new Scanner(file);
        Scanner sc = new Scanner(System.in);
        Memory m = new Memory(); //memory obj to store vlaues
        int temp = 0;
        int intIndex = 0;
        int strIndex = 0;
        int compIndex = 0;
        int compareIndex = 0;


        while (scan.hasNextLine()) { //Check if there is a follow up line
            String code = scan.nextLine(); //set the current line of code as a string

            //check for strings
            if (containsString(code, "quack:") && code.startsWith("quack:")) {
                if (!code.substring(7).contains(",")) {
                    System.out.println(code.substring(7));
                } else {

                    int firstCommaIndex = code.indexOf(",");
                    int secondCommaIndex = code.indexOf(",", firstCommaIndex + 1);
                    System.out.print(code.substring(7, firstCommaIndex));
                    if (firstCommaIndex != -1 && secondCommaIndex != -1) {
                        String textBetweenCommas = code.substring(firstCommaIndex + 1, secondCommaIndex).trim();
                        for (int i = 0; i < m.getInt().size(); i++) {
                            if (m.getInt(i).getName().equals(textBetweenCommas)) {
                                System.out.print(m.getInt(i).toString());
                                break;
                            }
                        }
                        for (int i = 0; i < m.getString().size(); i++) {
                            if (m.getString(i).getName().equals(textBetweenCommas)) {
                                System.out.print(m.getString(i).toString());
                                break;
                            }
                        }

                    }
                    System.out.print(code.substring(secondCommaIndex + 1));
                }
                System.out.println();
            } else if (containsString(code, "quack:")&&!(code.contains("$loop"))) {
                System.out.println("**compiler error - print statement**");
                System.out.println();
            } else {
                //no blank case
            }
            //check for int
            if (containsString(code, "ivar:") && code.startsWith("ivar:") && !(code.contains("(waddle)"))) {
                //get variable name
                String tempCode = code.substring(5);
                String result = tempCode.replaceAll("\\d", "");
                //Check if var alr exists:
                boolean exist = false;
                int pos = 0;
                for (int i = 0; i < m.getInt().size(); i++) {
                    if (m.getInt(i).getName() == result) {
                        exist = true;
                        pos = i;
                    }
                }
                if (exist) {
                    String numbers = code.replaceAll("[^0-9]", "");
                    temp = Integer.parseInt(numbers);
                    m.getInt(pos).setValue(temp);
                } else {
                    //get int value
                    String numbers = code.replaceAll("[^0-9]", "");
                    temp = Integer.parseInt(numbers);
                    Number num = new Number(temp, intIndex, result);
                    intIndex++;
                    m.setIntMemory(num);
                }
            } else if (containsString(code, "ivar:") && !(code.contains("(waddle)"))) {
                System.out.println("**compiler error - variable assignment**");
                System.out.println();
            } else {
                //no blank case
            }
            //Line comments
            if (containsString(code, "#") && code.startsWith("#")) {
                //ignore the line
                continue;
            }
            //String variables
            if (containsString(code, "svar:") && code.startsWith("svar:") && !(code.contains("(waddle)"))) {
                //get variable name
                String tempCode = code.substring(4);
                //get value
                int colonIndex = tempCode.indexOf(":");
                int hyphenIndex = tempCode.indexOf(".");
                String varName = " ";

                if (colonIndex != -1 && hyphenIndex != -1 && colonIndex < hyphenIndex) {
                    varName = tempCode.substring(colonIndex + 1, hyphenIndex);
                }
                String value = tempCode.substring(hyphenIndex + 1);
                Text txt = new Text(varName, value, strIndex);
                strIndex++;
                m.setStringMemory(txt);
            } else if (containsString(code, "svar:") && !(code.contains("(waddle)"))&&!(code.contains("$loop"))) {
                System.out.println("**String assignment error**");
                System.out.println();
            } else {
                //blank case
            }

            //math computations

            if (containsString(code, "+")) {
                // Find the index of the '+' operator
                int operatorIndex = code.indexOf('+');
                // Find the index of the '=' sign
                int equalsIndex = code.indexOf('=');
                // Extract the numbers
                int firstNumber = Integer.parseInt(code.substring(0, operatorIndex));
                int secondNumber = Integer.parseInt(code.substring(operatorIndex + 1, equalsIndex));
                ArrayList<Integer> list = new ArrayList<>();
                list.add(firstNumber);
                list.add(secondNumber);
                computations math = new computations("+", list, compIndex);
                m.setMathMemory(math);
                System.out.println(m.getMath(compIndex).compute());
                compIndex++;
            }
            if (containsString(code, "-")) {
                // Find the index of the '-' operator
                int operatorIndex = code.indexOf('-');
                // Find the index of the '=' sign
                int equalsIndex = code.indexOf('=');
                // Extract the numbers
                int firstNumber = Integer.parseInt(code.substring(0, operatorIndex));
                int secondNumber = Integer.parseInt(code.substring(operatorIndex + 1, equalsIndex));
                ArrayList<Integer> list = new ArrayList<>();
                list.add(firstNumber);
                list.add(secondNumber);
                computations math = new computations("-", list, compIndex);
                m.setMathMemory(math);
                System.out.println(m.getMath(compIndex).compute());
                compIndex++;
            }
            if (containsString(code, "*")) {
                code = code.replace("=", "");
                // Split the string at the "*" character
                String[] values = code.split("\\*");
                // Extract the individual values
                int value1 = Integer.parseInt(values[0]);
                int value2 = Integer.parseInt(values[1]);
                ArrayList<Integer> list = new ArrayList<>();
                list.add(value1);
                list.add(value2);
                computations math = new computations("*", list, compIndex);
                m.setMathMemory(math);
                System.out.println(m.getMath(compIndex).compute());
                compIndex++;
            }
            if (containsString(code, "/")) {
                code = code.replace("=", "");
                // Split the string at the "*" character
                String[] values = code.split("\\/");
                // Extract the individual values
                int value1 = Integer.parseInt(values[0]);
                int value2 = Integer.parseInt(values[1]);
                ArrayList<Integer> list = new ArrayList<>();
                list.add(value1);
                list.add(value2);
                computations math = new computations("/", list, compIndex);
                m.setMathMemory(math);
                System.out.println(m.getMath(compIndex).compute());
                compIndex++;
            }
            if (containsString(code, "%")) {
                code = code.replace("=", "");
                // Split the string at the "*" character
                String[] values = code.split("\\%");
                // Extract the individual values
                int value1 = Integer.parseInt(values[0]);
                int value2 = Integer.parseInt(values[1]);
                ArrayList<Integer> list = new ArrayList<>();
                list.add(value1);
                list.add(value2);
                computations math = new computations("%", list, compIndex);
                m.setMathMemory(math);
                System.out.println(m.getMath(compIndex).compute());
                compIndex++;
            }
            //Conditionals
            if (containsString(code, "if") && code.startsWith("if")) {
                // Extract the values
                String[] values = code.substring(3).trim().split("\\s+");
                // Store the values in separate variables
                String val1 = values[0];
                String operator = values[1];
                String val2 = values[2];

                int ival1 = 0;
                int ival2 = 0;
                boolean isInt = false;
                //determine int or string
                for (int i = 0; i < m.getInt().size(); i++) {
                    if (m.getInt(i).getName().equals(val1)) {
                        isInt = true;
                        ival1 = m.getInt(i).getValue();
                        break;
                    }
                }
                for (int i = 0; i < m.getInt().size(); i++) {
                    if (m.getInt(i).getName().equals(val2)) {
                        isInt = true;
                        ival2 = m.getInt(i).getValue();
                        break;

                    }
                }
                if (isInt) {
                    compare i = new compare(ival1, ival2, operator, compareIndex);
                    m.setCompareMemory(i);
                    compareIndex++;
                } else {
                    compare s = new compare(val1, val2, operator, compareIndex);
                    m.setCompareMemory(s);
                    compareIndex++;
                }
                if (m.getCompare(compareIndex - 1).compute()) {
                    String delimiter = "then";
                    int index = code.indexOf(delimiter);
                    if (index != -1) {
                        String value = code.substring(index + delimiter.length()).trim();
                        if (containsString(value, "quack:") && value.startsWith("quack:")) {
                            System.out.println(value.substring(7));
                        }
                    }
                }
            }
            //user input
            if (code.contains("svar:") && code.startsWith("svar:") && code.contains("(waddle)")) {
                //get variable name
                String tempCode = code.substring(4);
                //get value
                int colonIndex = tempCode.indexOf(":");
                int hyphenIndex = tempCode.indexOf(".");
                String varName = " ";

                if (colonIndex != -1 && hyphenIndex != -1 && colonIndex < hyphenIndex) {
                    varName = tempCode.substring(colonIndex + 1, hyphenIndex);
                }

                Text txt = new Text(varName, "", strIndex);
                txt.setValue(sc.nextLine());
                strIndex++;
                m.setStringMemory(txt);
            }
            if (containsString(code, "ivar:") && code.startsWith("ivar:") && (code.contains("(waddle)"))) {
                int startIndex = code.indexOf("ivar:") + "ivar:".length();
                int endIndex = code.indexOf("(waddle)");
                String value = code.substring(startIndex, endIndex);
                temp = sc.nextInt();
                sc.nextLine();
                Number num = new Number(temp, intIndex, value);
                num.setValue(temp);
                intIndex++;
                m.setIntMemory(num);
            }
            //loops
            if (containsString(code, "$loop")&& code.startsWith("$loop")){
                int number = 0;
                int startIndex = code.indexOf("$loop(") + "$loop(".length();
                int endIndex = code.indexOf(")");

                if (startIndex != -1 && endIndex != -1) {
                    String numberString = code.substring(startIndex, endIndex);
                    number = Integer.parseInt(numberString);
                }

                String searchWord = "do";
                int sIndex = code.indexOf(searchWord) + searchWord.length();
                String action = code.substring(sIndex).trim();
                if (containsString(action,"quack:")&&action.startsWith("quack:")){
                    for (int i = 0; i < number; i++){
                        System.out.println(action.substring(6));
                    }
                }
            }
        }
    }
}

