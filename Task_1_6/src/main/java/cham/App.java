package cham;


public class App {
    public static void main( String[] args ) {
        System.out.println(remainder(3, 4) );
        System.out.println(treeArea(7, 4));
        System.out.println(animals(5, 2, 8));
        System.out.println(profitableGamble(0.9, 3, 2));
        System.out.println(operation(24, 26, 2));
        System.out.println(ctoa('A'));
        System.out.println(addUpTo(10));
        System.out.println(nextAdge(8, 10));
        System.out.println(sumOfCubes(new int[]{3, 4, 5}));
        System.out.println(abcmath(42, 5, 10));
    }

    private static int remainder(int dividend, int divider){
        return dividend % divider;
    }

    private static int treeArea(int base, int height){
        return base * height / 2;
    }

    private static int animals(int chickens, int cows, int pigs){
        return chickens * 2 + cows * 4 + pigs * 4;
    }

    private static boolean profitableGamble(double prob, int prize, int pay){
        return (prob * prize) > pay;
    }

    private static String operation(int result, int a, int b){
        if(a + b == result)
            return "added";
        else if(a - b == result)
            return "subtracted";
        else if(a * b == result)
            return "multiplied";
        else if(a / b == result)
            return "divided";

        return "none";
    }

    private static int ctoa(char sumbol){
        return sumbol;
    }

    private static int addUpTo(int number){
        int sum = 0;
        for (int i = 1; i <= number; i++)
            sum += i;

        return sum;
    }

    private static int nextAdge(int first, int second){
        return first + second - 1;
    }

    private static int sumOfCubes(int[] numbers){
        int sum = 0;
        for(int number: numbers)
            sum += number * number * number;

        return sum;
    }

    private static boolean abcmath(int number, int reiterations, int divider){
        for (int i = 0; i < reiterations; i++)
            number += number;

        return (number %  divider) == 0;
    }
}
