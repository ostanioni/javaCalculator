import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.DataFormatException;

public class Calculator {
    static Map<String,Integer> RomeNumbers = new HashMap<String,Integer>(){{
        put(   "I",  1);
        put(  "II",  2);
        put( "III",  3);
        put(  "IV",  4);
        put(   "V",  5);
        put(  "VI",  6);
        put( "VII",  7);
        put("VIII",  8);
        put(  "IX",  9);
        put(   "X",  10);
    }};
    private static int toArabicNumber(String romeNumber){
        return RomeNumbers.get(romeNumber);
    }
    private static boolean isValidNumbers(String[] numbers) throws DataFormatException {
        if (numbers.length == 2){
            numbers[0] = numbers[0].trim();
            numbers[1] = numbers[1].trim();
        } else {
            return false;
        }
        if ( ( isRomeNumber(numbers[0]) & isArabicNumber(numbers[1]) ) |
                ( isArabicNumber(numbers[0]) & isRomeNumber(numbers[1]) ) ){
            throw new DataFormatException("Неверный формат данных: использованы римские и арабские формы записи!");
        }
        boolean isValid =
                 ( isRomeNumber(numbers[0]) & isRomeNumber(numbers[1]) ) |
                 ( isArabicNumber(numbers[0]) & isArabicNumber(numbers[1]) );
        return isValid;
    }
    private static boolean isRomeNumber(String str){
        return RomeNumbers.containsKey(str);
    }
    private static boolean isArabicNumber(String str){
        return RomeNumbers.containsValue(str);
    }
    private static int[] splitLine(String line) throws DataFormatException, NumberFormatException {
        String[] stringNumbers = line.split("[+]|[-]|[*]|[/]");
        try {
            if( stringNumbers.length == 1 ){
                throw new DataFormatException("Неверный формат данных...");
            }
            if ( isValidNumbers(stringNumbers) ){
                if( isRomeNumber(stringNumbers[0]) ) {
                    int numbers[] = { toArabicNumber(stringNumbers[0]), toArabicNumber(stringNumbers[1])};
                    return numbers;
                }
                int numbers[] = { Integer.parseInt(stringNumbers[0]), Integer.parseInt(stringNumbers[1])};
                return numbers;
            }

        } catch(DataFormatException e){
            StdOut.println(e.getMessage());
        }
        int numbers[] = { Integer.parseInt(stringNumbers[0]), Integer.parseInt(stringNumbers[1])};
        return numbers;
    }
    private static char getOperation(String line){
        if ( line.indexOf("+") > 0 ) {
            return '+';
        }
        if ( line.indexOf("-") > 0 ) {
            return '-';
        }
        if ( line.indexOf("*") > 0 ) {
            return '*';
        }
        return '/';
    }
    public static void main(String[] args){
        while ( true ){

            StdOut.println("Введите данные, которые необходимо вычислить (для выхода введите \"exit\"):");
            String line = StdIn.readLine().trim();

            if ( line.equals("exit") ) break;

            try {
                int[] numbers = splitLine(line);
                int firstNumber = numbers[0],
                        secondNumber = numbers[1];
                char operation = getOperation(line);

                switch (operation){
                    case '+': StdOut.println( "Сумма чисел равна: " + (firstNumber + secondNumber) ); break;
                    case '-': StdOut.println( "Разность чисел равна: " + (firstNumber - secondNumber) ); break;
                    case '*': StdOut.println( "Произведение чисел равно: " + (firstNumber * secondNumber) ); break;
                    case '/': StdOut.println( "Частное от деления чисел равно: " + ((double)firstNumber / (double)secondNumber) ); break;
                }
            } catch (DataFormatException e){
                StdOut.println(e.getMessage());
            } catch (NumberFormatException e){
                StdOut.println(e.getMessage());
            } finally {
                // if ( line.equals("exit") ) break;
            }
        }

    }
}
