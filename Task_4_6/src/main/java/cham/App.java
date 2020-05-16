package cham;

import java.util.ArrayList;

public class App {
    public static void main( String[] args ) {
        System.out.println( textCPU(10, 7, "hello my name is Bessie and this is my essay") );
        System.out.println(split("((()))(())()()(()())"));
        System.out.println(toCamelCase("hello_edabit"));
        System.out.println(toSnakeCase("helloEdabit"));
        System.out.println(overTime(new double[]{13.25, 15, 30, 1.5}));
        System.out.println(BMI("154 pounds", "2 meters"));
        System.out.println(bugger(39));
        System.out.println(toStarShorthand("77777geff"));
        System.out.println(doesRhyme("Sam I am!", "Green eggs and ham."));
        System.out.println(trouble(451999277, 1177722899));
        System.out.println(countUniqueBooks("AZYWABBCATTTA", 'A'));
    }

    private static String textCPU(int n, int k, String line){
        String result = "";
        int score = 0;

        for(String word: line.split(" ")){
            if(word.length() <= k - score){
                if(score != 0)
                    result += " ";

                result += word;
                score += result.length();

            }else{
                score  = word.length();
                result += "\n" + word;
            }

        }
        return result;
    }
    private static ArrayList<String> split(String line){
        int score = 0;
        String stek = "";
        ArrayList<String> result = new ArrayList<>();

        for (char sumbol: line.toCharArray()) {
            if(sumbol == '(')
                score ++;
            if(sumbol == ')')
                score--;

            stek += sumbol;

            if(score == 0){
                result.add(stek);
                stek = "";
            }

        }
        return result;
    }
    private static String toCamelCase(String line){
        String result = "";
        boolean check = false;

        for (char sumbol: line.toCharArray())
            if(sumbol == '_')
                check = true;

            else if(check){
                result += (char)(sumbol - 32);
                check = false;

            }else
                result += sumbol;

        return result;
    }

    private static String toSnakeCase(String line){
        String result = "";

        for(char symbol: line.toCharArray())
            if(symbol >='A' && symbol <= 'Z')
                result += "_" + (char)(symbol + 32);
            else
                result += symbol;

        return result;
    }
    private static String overTime(double[] data){
        // 9 to 17 x1, 17 to _ x_
        double standart = 0;
        double overtime = 0;
        if(data[1] > 17){
            if(data[0] < 17)
                standart = 17 - data[0];

            overtime = data[1] - 17;
        }
        else
            standart = data[1] - data[0];

        return "$" + (double)Math.round((standart * data[2] + overtime * data[2] * data[3]) * 100) / 100;
    }
    private static String BMI(String weight, String width){
        String[] wei = weight.split(" ");
        String[] wid = width.split(" ");

        double ves = Integer.parseInt(wei[0]);
        if(wei[1].equals("pounds"))
            ves /= 2.20462;

        double rost = Double.parseDouble(wid[0]);

        if(wid[1].equals("inches"))
            rost /= 39.3701;

        double k = ves / (rost * rost);

        if(k < 18.5)
            return (double)Math.round(k * 10) / 10 + " Underweight";

        else if(k >= 18.5 && k <= 24.9)
            return (double)Math.round(k * 10) / 10 + " Normal weight";

        else if(k >= 25)
            return (double)Math.round(k * 10) / 10 + " Overweight";

        return "";
    }
    private static int bugger(int number){
        int tempNum = 1;

        if(number > 9)
            while(number > 0) {
                tempNum *= number % 10;
                number /= 10;
            }

        else
            return 0;

        return 1 + bugger(tempNum);
    }
    private static String toStarShorthand(String line){
        line += " ";
        int score = 1;
        char oldSymbol = ' ';
        String result = "";
        for(char symbol: line.toCharArray()){
            if(symbol != oldSymbol){
                if(result.equals("")){
                    result += symbol;
                }else if(score == 1){
                    result += symbol;
                }else{
                    result += "*" + score;
                    result += symbol;
                }

                score = 1;
            }else{
                score++;
            }

            oldSymbol = symbol;
        }
        return result;
    }
    private static boolean doesRhyme(String first, String second){
        boolean result = true;
        String symbols = "EYUIOA";
        char c;

        String[] fir = first.split(" ");
        String[] sec = second.split(" ");

        first = fir[fir.length - 1].toUpperCase();
        second = sec[sec.length - 1].toUpperCase();

        if(first.length() > second.length())
            for(int i = 1; i <= second.length(); i++){
                if(result)
                    for (char symbol: symbols.toCharArray()) {
                        c = second.charAt(second.length() - i);

                        if(symbol == c){
                            if(!(c == first.charAt(first.length() - i)))
                                result = false;

                            break;
                        }
                    }
                else
                    i = second.length();

            }
        else
            for(int i = 1; i <= first.length(); i++){
                if(result)
                    for (char symbol: symbols.toCharArray()) {
                        c = first.charAt(first.length() - i);

                        if(symbol == c){
                            if(!(second.charAt(second.length() - i) == c))
                                result = false;

                            break;
                        }
                    }
                else
                    i = first.length();

            }


        return result;
    }
    private static boolean trouble(long first, long second){
        long old1 = first % 10;
        long old2 = first % 100;
        first /= 100;
        boolean result = false;

        while(first > 0){
            if(old1 == old2 && old2== first % 10){
                result = true;
                break;
            }else{
                old1 = old2;
                old2 = first % 10;
                first /= 10;
            }
        }

        if(result){
            old1 = second % 10;
            second /= 10;
            while (second > 0){
                if(old1 == second % 10){
                    return true;
                }else{
                    old1 = second % 10;
                    second /= 10;
                }
            }
        }
        return false;
    }
    private static int countUniqueBooks(String stringSequence, char bookEnd){
        int result = 0;
        boolean open = false;
        String sumbols = "";
        for(char symbol: stringSequence.toCharArray()){
            if(symbol == bookEnd){
                open = !open;
            }else{
                if(open){
                    if(sumbols.indexOf(symbol) == -1){
                        result++;
                        sumbols += symbol;
                    }
                }else{
                    sumbols = "";
                }
            }
        }
        return result;
    }
}
