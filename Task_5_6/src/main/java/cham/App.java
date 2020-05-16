package cham;

import javax.annotation.processing.SupportedSourceVersion;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class App {
    public static void main( String[] args ) {
        int[] out = encrypt("Hello");
        System.out.print("[");
        for(int i = 0; i < out.length; i++)
            if(i < out.length - 1)
                System.out.print(out[i] + ", ");
            else
                System.out.print(out[i]);

        System.out.println("]");

        System.out.println(decrypt(new int[]{72, 33, -73, 84, -12, -3, 13, -13, -68}));

        System.out.println(canMove("Queen", "C4", "D6"));

        System.out.println(canComplete("butl", "beautiful"));

        System.out.println(sumDigProd(new int[]{1, 2, 3, 4, 5, 6}));

        System.out.println(sameVowelGroup(new String[]{"hoops", "chuff", "bot", "bottom"}));

        System.out.println(validateCard(1234567890123452l));

        System.out.println(numToEng((short) 126));

        System.out.println(numToRus((short) 126));

        System.out.println(getSha256Hash("password123"));

        System.out.println(correctTitle("jOn SnoW, kINg IN thE noRth."));

        System.out.println(hexLattice(67));
    }

    private static int[] encrypt(String line){
        int[] result = new int[line.length()];

        result[0] = line.charAt(0);

        for (int i = 1; i < line.length(); i++)
            result[i] = (line.charAt(i) - line.charAt(i - 1));

        return result;
    }

    private static String decrypt(int[] mas){
        String result = "" + (char)mas[0];

        for(int i = 1; i < mas.length; i++)
            result += (char)(result.charAt(result.length() - 1) + mas[i]);

        return result;
    }

    private static boolean canMove(String figure, String start, String end){
        if(start.charAt(0) <= 'H' && start.charAt(1) <= '8' && end.charAt(0) <= 'H' && end.charAt(1) <= '8')
            return false;

        int width = Math.abs(start.charAt(0) - end.charAt(0));
        int height = Math.abs(start.charAt(1) - end.charAt(1));

        if(figure.equals("Pawn"))
            if(start.charAt(1) == '2' || start.charAt(1) == '7')
                if(start.charAt(0) == end.charAt(0)) {
                    if (start.charAt(1) == '7' && end.charAt(1) == '5')
                        return true;
                    else if (start.charAt(1) == '2' && end.charAt(1) == '4')
                        return true;

                    if (Math.abs(start.charAt(1) - end.charAt(1)) == 1)
                        return true;
                }else
                    if(width == 1 && Math.abs(start.charAt(1) - end.charAt(1)) == 1)
                        return true;

        if(figure.equals("Rook"))
            if(start.charAt(0) == end.charAt(0) || start.charAt(1) == end.charAt(1))
                return true;

        if(figure.equals("Horse"))
            if((width == 2 && height == 1) || (width == 1 && height == 2))
                return true;

        if(figure.equals("Bishop"))
            if(width == height)
                return true;

        if(figure.equals("Queen")){
            if(width == height)
                return true;
            if(start.charAt(0) == end.charAt(0) || start.charAt(1) == end.charAt(1))
                return true;
        }
        if(figure.equals("King"))
            if(width <= 1 && height <= 1)
                return true;

        return false;
    }

    private static boolean canComplete(String first, String second){
        int score = 0;

        for(char symbol: second.toCharArray())
            if(symbol == first.charAt(score))
                score ++;

        return score == first.length();
    }

    private static int sumDigProd(int[] mas){
        int sum = 0;
        int result = 1;

        for(int i: mas)
            sum += i;

        while(sum > 9){
            while(sum > 0){
                result *= sum % 10;
                sum /= 10;
            }

            sum = result;
            result = 1;
        }

        return sum;
    }

    private static ArrayList<String> sameVowelGroup(String[] mas){
        ArrayList<String> output = new ArrayList<>();
        String consonants = "qwrtpsdfghjklzxcvbnm";
        boolean result = true;
        String acceptVowels = "";

        for(char symbol: mas[0].toCharArray())
            if(consonants.indexOf(symbol) == -1)
                acceptVowels += symbol;

        output.add(mas[0]);

        for(int i = 1; i < mas.length; i++){
            for(char symbol: mas[i].toCharArray())
                if(consonants.indexOf(symbol) == -1)
                    if(acceptVowels.indexOf(symbol) == -1)
                        result = false;

            if(result)
                output.add(mas[i]);

            result = true;
        }
        return output;
    }

    private static boolean validateCard(long cardNumber){
        if(cardNumber <= 9999999999999l)
            return false;

        short controlSum = (short)(cardNumber % 10);
        cardNumber /= 10;
        boolean even = false;
        short sum = 0;

        while(cardNumber > 0){
            short num = (short)(cardNumber % 10);
            cardNumber /= 10;

            if(!even){
                num *= 2;
                num = (short) (num / 10 + num % 10);
            }

            sum += num;
            even = !even;
        }

        if(10 - sum % 10 == controlSum)
            return true;
        else
            return false;
    }

    private static String numToEng(short num){
        if(num == 0)
            return "zero";

        boolean tn = false;

        short one = (short)(num % 10);

        num /= 10;
        short ten = (short)(num % 10);

        num /= 10;

        String result = "";

        switch (num){
            case 1:
                result += "one hundred ";
                break;
            case 2:
                result += "two hundred ";
                break;
            case 3:
                result += "three hundred ";
                break;
            case 4:
                result += "four hundred ";
                break;
            case 5:
                result += "five hundred ";
                break;
            case 6:
                result += "six hundred ";
                break;
            case 7:
                result += "seven hundred ";
                break;
            case 8:
                result += "eight hundred ";
                break;
            case 9:
                result += "nine hundred ";
                break;
        }

        switch (ten){
            case 1:
                tn = true;
                break;
            case 2:
                result += "twenty ";
                break;
            case 3:
                result += "thirty ";
                break;
            case 4:
                result += "fourty ";
                break;
            case 5:
                result += "fivety ";
                break;
            case 6:
                result += "sixty ";
                break;
            case 7:
                result += "seventy ";
                break;
            case 8:
                result += "eighty ";
                break;
            case 9:
                result += "ninety ";
                break;
        }

        switch (one){
            case 1:
                if(tn)
                    result += "eleven";
                else
                    result += "one";
                break;
            case 2:
                if(tn)
                    result += "twelve";
                else
                    result += "two";
                break;
            case 3:
                if(tn)
                    result += "thirteen";
                else
                    result += "three";
                break;
            case 4:
                if(tn)
                    result += "fourteen";
                else
                    result += "four";
                break;
            case 5:
                if(tn)
                    result += "fifteen";
                else
                    result += "five";
                break;
            case 6:
                if(tn)
                    result += "sixteen";
                else
                    result += "six";
                break;
            case 7:
                if(tn)
                    result += "seventeen";
                else
                    result += "seven";
                break;
            case 8:
                if(tn)
                    result += "eighteen";
                else
                    result += "eight";
                break;
            case 9:
                if(tn)
                    result += "nineteen";
                else
                    result += "nine";
                break;
        }

        return result;
    }

    private static String numToRus(short num){
        if(num == 0)
            return "ноль";

        boolean tn = false;

        short one = (short)(num % 10);

        num /= 10;
        short ten = (short)(num % 10);

        num /= 10;

        String result = "";

        switch (num){
            case 1:
                result += "сто ";
                break;
            case 2:
                result += "двести ";
                break;
            case 3:
                result += "тристо ";
                break;
            case 4:
                result += "четыресто ";
                break;
            case 5:
                result += "пятосот ";
                break;
            case 6:
                result += "шестьсот ";
                break;
            case 7:
                result += "семьсот ";
                break;
            case 8:
                result += "восемьсот ";
                break;
            case 9:
                result += "девятьсот ";
                break;
        }

        switch (ten){
            case 1:
                tn = true;
                break;
            case 2:
                result += "двадцать ";
                break;
            case 3:
                result += "тридцать ";
                break;
            case 4:
                result += "сорок ";
                break;
            case 5:
                result += "пятьдесят ";
                break;
            case 6:
                result += "шестьдесят ";
                break;
            case 7:
                result += "семьдесят ";
                break;
            case 8:
                result += "восемьдесят ";
                break;
            case 9:
                result += "девятьсот ";
                break;
        }

        switch (one){
            case 1:
                if(tn)
                    result += "одинадцать";
                else
                    result += "один";
                break;
            case 2:
                if(tn)
                    result += "двенадцать";
                else
                    result += "два";
                break;
            case 3:
                if(tn)
                    result += "тринадцать";
                else
                    result += "три";
                break;
            case 4:
                if(tn)
                    result += "четырнадцать";
                else
                    result += "четыре";
                break;
            case 5:
                if(tn)
                    result += "пятнадцать";
                else
                    result += "пять";
                break;
            case 6:
                if(tn)
                    result += "шестнадцать";
                else
                    result += "шесть";
                break;
            case 7:
                if(tn)
                    result += "семнадцать";
                else
                    result += "семь";
                break;
            case 8:
                if(tn)
                    result += "восемнадцать";
                else
                    result += "восемь";
                break;
            case 9:
                if(tn)
                    result += "девятнадцать";
                else
                    result += "девять";
                break;
        }

        return result;
    }

    private static String getSha256Hash(String pass){
        StringBuilder result = new StringBuilder();

        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashCode = md.digest(pass.getBytes());

            for (byte b : hashCode)
                result.append(String.format("%02x", b));

        }catch (NoSuchAlgorithmException ex){
            ex.getMessage();
        }

        return result.toString();
    }

    private static String correctTitle(String line){
        line = line.toLowerCase();
        String[] words = line.split(" ");

        line = "";

        for (String word: words){
            if(word.equals("and") || word.equals("the") || word.equals("of") || word.equals("in"))
                line += word;

            else if(word.indexOf('-') != -1){
                int num = word.indexOf('-');
                line += word.substring(0, 1).toUpperCase() + word.substring(1, num + 1)
                        + word.substring(num + 1, num + 2).toUpperCase() + word.substring(num + 2);

            }else
                line += word.substring(0, 1).toUpperCase() + word.substring(1);

            line += " ";
        }

        return line;
    }

    private static String hexLattice(int number){
        int score = 0;
        int num = 1;

        while(num < number){
            num += 6 * score;
            score ++;
        }

        String result = "";

        for(int i = 0; i < score; i++){
            for(int j = 0; j < score - i - 1; j++)
                result += ' ';

            for(int j = 0; j < score + i; j++)
                result += "o ";

            result += '\n';
        }

        for(int i = score - 2; i >= 0; i--){
            for(int j = 0; j < score - i - 1; j++)
                result += ' ';

            for(int j = 0; j < score + i; j++)
                result += "o ";

            result += '\n';
        }

        return result;
    }
}

