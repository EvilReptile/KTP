package cham;

public class App {

    public static void main( String[] args ) {
        System.out.println(solutions(1, 0, 0));
        System.out.println(findZip("all zip files are zipped"));
        System.out.println(checkPerfect(496));
        System.out.println(flipEndChars("Cat, dog, and mouse."));
        System.out.println(isValidHexCode("#EAECEE"));
        System.out.println(same(new int[] {1, 3, 4, 4, 4}, new int[] {2, 5, 7}));
        System.out.println(isKaprekar(297));
        System.out.println(longestZero("01100001011000"));
        System.out.println(nextPrime(24));
        System.out.println(rightTriangle(145, 105, 100));
    }

    private static int solutions(int a, int b, int c){
        if(a == 0)
            if(b == 0)
                return 0;
            else
                return 1;


        if(b == 0 && ((a > 0 && c > 0) || (a < 0 && c < 0)))
            return 0;

        int D = b * b - 4 * a * c;

        if(D == 0)
            return 1;

        else if(D > 0)
            return 2;

        else if(D < 0)
            return 0;

        return -1;
    }

    private static int findZip(String line){
        int number = line.indexOf("zip");

        String newLine = "";

        for(int i = number + 3; i < line.length(); i++)
            newLine += line.charAt(i);

        return newLine.indexOf("zip") + number + 3;
    }

    private static boolean checkPerfect(int number){
        if(number == 0)
            return false;

        int sum = 0;
        for(int i = 1; i < number; i++)
            if(number % i == 0)
                sum += i;

        if(sum == number)
            return true;
        else
            return false;
    }

    private static String flipEndChars(String line){
        if(line.length() <= 2)
            return "Incompatible.";

        else if(line.charAt(0) == line.charAt(line.length() - 1))
            return "Two's a pair.";

        else{
            char symbol = line.charAt(0);
            String newLine = "";
            newLine += line.charAt(line.length() - 1);

            for(int i = 1; i < line.length() - 1; i++)
                newLine += line.charAt(i);

            newLine += symbol;
            return newLine;
        }
    }

    private static boolean isValidHexCode(String hex){
        if(hex.length() != 7)
            return false;

        if(hex.charAt(0) != '#')
            return false;

        for(int i = 1; i < hex.length(); i++)
            if(!((hex.charAt(i) >= '0' && hex.charAt(i) <= '9')
                    || (hex.charAt(i) >= 'A' && hex.charAt(i) <= 'F')
                    || (hex.charAt(i) >= 'a' && hex.charAt(i) <= 'f')))
                return false;


        return true;
    }

    private static boolean same(int[] first, int[] second){
        int arr1 = 0;
        int arr2 = 0;
        boolean res;

        for(int i = 0; i < first.length; i++){
            res = false;
            for(int j = 0; j < i; j++)
                if(first[i] == first[j])
                    res = true;


            if(!res)
                arr1 ++;
        }

        for(int i = 0; i < second.length; i++){
            res = false;
            for(int j = 0; j < i; j++)
                if(second[i] == second[j])
                    res = true;

            if(!res)
                arr2 ++;
        }

        if(arr1 == arr2)
            return true;
        else
            return false;
    }

    private static boolean isKaprekar(int number){
        int num = number * number;

        String line = "" + num;

        int left = 0;
        int right = 0;

        for(int i = 0; i < line.length() / 2; i++)
            left = left * 10 + Integer.parseInt("" + line.charAt(i));


        for(int i = line.length() / 2; i < line.length(); i++)
            right = right * 10 + Integer.parseInt("" + line.charAt(i));;

        if(left + right == number)
            return true;
        else
            return false;
    }

    private static String longestZero(String line){
        String out = "";
        String res = "";

        for(char symbol: line.toCharArray())
            if(symbol == '1') {
                if (out.length() < res.length())
                    out = res;

                res = "";
            }else
                res += symbol;


        return out;
    }

    private static int nextPrime(int number){
        boolean result = false;

        while(!result){
            result = true;

            for(int i = 2; i < number; i++)
                if(number % i == 0){
                    result = false;
                    break;
                }

            if(!result)
                number++;
        }

        return number;
    }

    private static boolean rightTriangle(int x, int y, int z){

        if(x > y && x > z && x * x == y * y + z * z)
            return true;

        else if(y > x && y > z && y * y == x * x + z * z)
            return true;

        else if(z > x && z > y && z * z == y * y + x * x)
            return true;

        return false;
    }
}
