package cham;

import java.util.ArrayList;

public class App {
    public static void main( String[] args ){
        System.out.println(bell(9));
        System.out.println(translateWord("Button"));
        System.out.println(translateSentence("Do you think it is going to rain today?"));
        System.out.println(validColor("rgb(0,,0)"));
        System.out.println(stripUrlParams("https://edabit.com?a=1&b=2&a=2"));
        System.out.println(stripUrlParams("https://edabit.com?a=1&b=2&a=2", new String[]{"b"}));

        String[] arr = getHashTags("Visualizing Science");
        for(String word: arr)
            System.out.print(word + " ");
        System.out.println();

        System.out.println(ulam(206));
        System.out.println(longestNonRepeatingSubstring("abcabcdbcdef"));
        System.out.println(convertToRoman(16));
        System.out.println(formula("16 * 10 = 160 = 40 + 120"));
        System.out.println(palindromedescendant("123312"));
    }

    private static int bell(int number){
        return bell(number, number);
    }

    //Рекурсивная функция сложения чисел стирлинга
    private static int bell( int x, int y){
        if(x != 1)
            return sterling(x, y) + bell(x - 1, y);
        else
            return sterling(x, y);
    }

    //Рекурсивная функция вычисления числа стирлинга на месте Х, У в матрицуе
    private static int sterling(int x, int y){
        if(x == y)
            return 1;

        if(x == 1)
            return 1;

        return sterling(x - 1, y - 1) + x * sterling(x, y - 1);
    }

    private static String translateWord(String word){
        String accept = "eyuioa";
        if(accept.indexOf(word.charAt(0)) != -1)
            return word + "yay";
        else {
            if(word.charAt(0) > 'Z')
                return word.substring(1) + word.charAt(0) + "ay";
            else{
                return word.substring(1, 2).toUpperCase() + word.substring(2) + word.substring(0, 1).toLowerCase() + "ay";
            }
        }

    }

    private static String translateSentence(String line){
        String[] words = line.split(" ");
        line = "";

        for(String word: words)
            if((word.charAt(word.length() - 1) >= 'A' && word.charAt(word.length() - 1) <= 'Z')
                    || (word.charAt(word.length() - 1) >= 'a' && word.charAt(word.length() - 1) <= 'z'))
                line += translateWord(word) + ' ';
            else
                line += translateWord(word.substring(0, word.length() - 1)) + word.substring(word.length() - 1) + " ";

        return line;
    }

    private static boolean validColor(String line){
        if(!line.substring(0, 3).equals("rgb"))
            return false;

        if(line.charAt(3) == 'a'){
            String[] numbers = line.substring(5, line.length() - 1).split(",");

            if(Integer.parseInt(numbers[0]) < 256)
                if(Integer.parseInt(numbers[1]) < 256)
                    if(Integer.parseInt(numbers[2]) < 256)
                        return Double.parseDouble(numbers[3]) <= 1;
        }

        return false;
    }

    private static String stripUrlParams(String url){
        boolean result = false;
        String param = "";
        String line = "";

        for(char symbol: url.toCharArray())
            if(symbol == '?')
                result = true;
            else
                if(result)
                    param += symbol;
                else
                    line += symbol;

        String[] params = param.split("&");

        if(params.length > 0)
            line += "?";

        for(int i = 0; i < params.length; i++){
            result = true;
            for(int j = i + 1; j< params.length; j++)
                if(params[i].split("=")[0].equals(params[j].split("=")[0]))
                    result = false;


            if(result){
                line += params[i] ;
                if(i < params.length - 2)
                    line += "&";
            }

        }
        return line;
    }

    private static String stripUrlParams(String url, String[] deleteParams){
        boolean result = false;
        String param = "";
        String line = "";

        for(char symbol: url.toCharArray())
            if(symbol == '?')
                result = true;
            else
                if(result)
                    param += symbol;
                else
                    line += symbol;

        String[] params = param.split("&");
        if(params.length > 0)
            line += "?";

        for(int i = 0; i < params.length; i++){
            result = true;
            for(int j = i + 1; j< params.length; j++)
                if(params[i].split("=")[0].equals(params[j].split("=")[0]))
                    result = false;

            if(result){
                for(String par: deleteParams)
                    if(par.equals(params[i].split("=")[0]))
                        result = false;

                if(result) {
                    line += params[i];
                    if (i < params.length - 2)
                        line += "&";
                }
            }

        }
        return line;
    }

    private static String[] getHashTags(String line){
        String[] words = line.split(" ");
        String[] result = {"", "", ""};

        for(String word: words)
            if(result[0].length() - 1 < word.length()){
                result[2] = result[1];
                result[1] = result[0];
                result[0] = "#" + word.toLowerCase();
            }else if(result[1].length() - 1 < word.length()){
                result[2] = result[1];
                result[1] = "#" + word.toLowerCase();
            }else if(result[2].length() - 1 < word.length())
                result[2] = "#" + word.toLowerCase();

        return result;
    }

    private static int ulam(int number){
        int[] numbers = new int[number];

        numbers[0] = 1;
        numbers[1] = 2;

        for(int i = 2; i < numbers.length; i++){
            boolean skip = false;
            int newElement = numbers[i - 1] + 1;

            while (!skip){
                int found = 0;
                for(int j = 0; j < i; j++){
                    int score = newElement - numbers[j];
                    for(int g  = j + 1; g < i; g++)
                        if(numbers[g] == score)
                            found++;
                }

                if(found == 1) {
                    numbers[i] = newElement;
                    skip = true;
                }else
                    newElement++;
            }
        }

        return numbers[numbers.length - 1];
    }

    private static String longestNonRepeatingSubstring(String word){
        String result = "";
        String output = "";

        for(char symbol: word.toCharArray())
            if(result.indexOf(symbol) == -1)
                result += symbol;
            else{
                if(output.length() < result.length())
                    output = result;

                result = "" + symbol;
            }

        if(output.length() < result.length())
            output = result;

        return output;
    }

    private static String convertToRoman(int number){
        String result = "";

        int thousand = number / 1000;
        number %= 1000;

        int hundred = number / 100;
        number %= 100;

        int ten = number / 10;
        number %= 10;

        for(int i = 0; i < thousand; i++)
            result += "M";

        if(hundred == 9)
            result += "CM";

        else if(hundred == 4)
            result += "CD";

        else{
            if(hundred >= 5){
                result += "D";
                hundred -= 5;
            }

            for(int i = 0; i < hundred; i++)
                result += "C";
        }

        if(ten == 9)
            result += "XC";

        else if(ten == 4)
            result += "XL";

        else{
            if(ten >= 5){
                result += "L";
                ten -= 5;
            }

            for(int i = 0; i < ten; i++)
                result += "X";
        }

        if(number == 9)
            result += "IX";

        else if(number == 4)
            result += "IV";

        else{
            if(number >= 5){
                result += "V";
                number -= 5;
            }

            for(int i = 0; i < number; i++)
                result += "I";
        }

        return result;
    }

    private static boolean formula(String line){
        line = " " + line + " ";
        String[] parts = line.split("=");
        boolean result = true;
        int controlResult = 0;

        for(int i = 0; i < parts.length; i++){
            String[] element = parts[i].split(" ");
            int res = 0;

            if(element.length > 3) {
                if (element[2].equals("+"))
                    res = Integer.parseInt(element[1]) + Integer.parseInt(element[3]);

                else if (element[2].equals("-"))
                    res = Integer.parseInt(element[1]) - Integer.parseInt(element[3]);

                else if (element[2].equals("*"))
                    res = Integer.parseInt(element[1]) * Integer.parseInt(element[3]);

                else if (element[2].equals("/"))
                    res = Integer.parseInt(element[1]) / Integer.parseInt(element[3]);
            }else
                res = Integer.parseInt(element[1]);


            if(i == 0)
                controlResult = res;
            else
                if(controlResult != res)
                    return false;
        }

        return true;
    }

    private static boolean palindromedescendant(String number){
        if(number.length() == 1)
            return true;

        while(number.length() >= 2){
            String res = "";
            boolean result = true;

            for(int i = 0; i < number.length() / 2; i++)
                if(number.charAt(i) != number.charAt(number.length() - i - 1))
                    result = false;

            if(result)
                return true;

            for(int i = 0; i < number.length(); i += 2)
                res += (Integer.parseInt("" + number.charAt(i)) + Integer.parseInt("" + number.charAt(i + 1)));

            number = res;
        }
        return false;
    }
}
