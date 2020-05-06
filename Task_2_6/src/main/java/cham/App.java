package cham;


import java.beans.PropertyEditorSupport;

public class App {

    public static void main( String[] args ) {
        System.out.println( repeat("mice", 5) );
        System.out.println(differenceMaxMin(new int[] {10, 4, 1, 4, -10, -50, 32, 21}));
        System.out.println(isAvgWhole(new int[] {1, 2, 3, 4}));

        int[] numbers = cumulativeSum(new int[] {1, -2, 3});
        for(int number: numbers)
            System.out.print(number + " ");
        System.out.println();

        System.out.println(getDecimalPlaces("43.20"));

        System.out.println(Fibonacci(7));
        System.out.println(isValid("78925"));
        System.out.println(isStrangePair("sparkling", "groups"));
        System.out.println(isPrefix("automation", "auto-"));
        System.out.println(isSuffix("arachnophobia", "-phobia"));
        System.out.println(boxSeq(7));
    }

    private static String repeat(String line, int iteration){
        char[] symbols = line.toCharArray();

        StringBuilder lineBuilder = new StringBuilder();
        for(char symbol: symbols)
            for(int i = 0; i < iteration; i++)
                lineBuilder.append(symbol);

        line = lineBuilder.toString();

        return line;
    }

    private static int differenceMaxMin(int [] numbers){
        if(numbers.length > 0){

            int min = numbers[0];
            int max = numbers[0];

            for (int number: numbers) {
                if(number < min)
                    min = number;

                if(number > max)
                    max = number;
            }

            return max - min;
        }
        return -1;
    }

    private static boolean isAvgWhole(int[] numbers){
        int sum = 0;

        for(int number: numbers)
            sum += number;

        return sum % numbers.length == 0;
    }

    private static int[] cumulativeSum(int[] numbers){
        int[] result = new int[numbers.length];

        for(int i = 0; i < numbers.length; i++){
            result[i] = numbers[i];
            for(int j = 0; j < i; j++)
                result[i] += numbers[j];
        }
        return result;
    }

    private static int getDecimalPlaces(String number){
        boolean element = false;
        int score = 0;
        for(int i = 0; i < number.length(); i++){
            if(element)
                score++;
            if(number.charAt(i) == '.')
                element = true;
        }

        return score;
    }

    private static int Fibonacci(int number){
        int f0 = 0;
        int f1 = 1;
        int buf;

        for (int i = 0; i < number; i++){
            buf = f1;
            f1 = f1 + f0;
            f0 = buf;
        }

        return f1;
    }

    private static boolean isValid(String index){
        if(index.length() != 5)
            return false;

        for(int i = 0; i < index.length(); i++)
            if(index.charAt(i) < '0' || index.charAt(i) > '9')
                return false;


        return true;
    }

    private static boolean isStrangePair(String first, String second){
        return first.charAt(0) == second.charAt(second.length() - 1) &&
                first.charAt(first.length() - 1) == second.charAt(0);
    }

    private static boolean isPrefix(String word, String prefix){
        for(int i = 0; i < prefix.length() - 1; i++)
            if(word.charAt(i) != prefix.charAt(i))
                return false;


        return true;
    }

    private static boolean isSuffix(String word, String suffix){
        for(int i = suffix.length() - 1; i > 0; i--)
            if(word.charAt(word.length() - 1 - suffix.length() + 1 + i) != suffix.charAt(i))
                return false;


        return true;
    }

    private static int boxSeq(int step){
        int result = 0;

        for(int i = 1; i <= step; i++)
            if(i % 2 != 0)
                result += 3;
            else
                result --;

         return result;
    }
}
