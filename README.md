# Калькулятор на Java
В качестве дополнительной библиотеки использована библиотека algs4.jar из книги Р.Сэджвика и К. Уайна "Фундаментальные алгоритмы на Java". Библиотека находится в корне проекта.
Разделять данную задачу на нексколько классов счел излишним. Реализация через один класс и статические методы.
В качестве среды разработки использована IntelliJ IDEA 2020.2 (Community Edition).
```java
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.DataFormatException;

public class Calculator {
    private static Map<String, String> mapRomanArabNumbers = new HashMap<String,String>(){{
        put(   "I",  "1");
        put(  "II",  "2");
        put( "III",  "3");
        put(  "IV",  "4");
        put(   "V",  "5");
        put(  "VI",  "6");
        put( "VII",  "7");
        put("VIII",  "8");
        put(  "IX",  "9");
        put(   "X",  "10");
    }};
    private static int toArabNumber(String romanNumber){
        return Integer.parseInt( mapRomanArabNumbers.get(romanNumber) );
    }
    private static boolean isValidInput(String[] inputData) throws DataFormatException {
        if ( ( isRomanNumber( inputData[0] ) & isArabNumber(inputData[1]) ) |
             ( isArabNumber(inputData[0]) & isRomanNumber(inputData[1]) ) ){
            throw new DataFormatException("Неверный формат данных: использованы римские и арабские формы записи!");
        }
        boolean isValid = ( isRomanNumber(inputData[0]) & isRomanNumber(inputData[1]) ) |
                          ( isArabNumber(inputData[0]) & isArabNumber(inputData[1]) );
        return isValid;
    }
    private static boolean isRomanNumber(String str){
        return mapRomanArabNumbers.containsKey(str);
    }
    private static boolean isArabNumber(String str){
        return mapRomanArabNumbers.containsValue(str);
    }
    private static int[] splitLine(String line) throws DataFormatException, NumberFormatException, NullPointerException {
        String[] inputData = line.split("[+]|[-]|[*]|[/]");
        if ( inputData.length == 2 ) {
            inputData[0] = inputData[0].trim();
            inputData[1] = inputData[1].trim();
        } else {
            throw new DataFormatException("Неверный формат данных");
        }
        try {
            if ( isValidInput(inputData) ){
                if( isRomanNumber( inputData[0]) ) {
                    int[] inputNumbers = { toArabNumber( inputData[0] ), toArabNumber( inputData[1] ) };
                    return inputNumbers;
                }
                int[] inputNumbers = { Integer.parseInt( inputData[0] ), Integer.parseInt( inputData[1] ) };
                return inputNumbers;
            }
        }catch(DataFormatException e){
            StdOut.println(e.getMessage());
        }
        return null;
    }
    private static char getOperation(String line){
        if ( line.indexOf("+") > 0 ) return '+';
        if ( line.indexOf("-") > 0 ) return '-';
        if ( line.indexOf("*") > 0 ) return '*';
        return '/';
    }
    public static void main(String[] args){
        while ( true ){
            StdOut.println("Введите данные, которые необходимо вычислить (для выхода введите \"exit\"):");
            String line = StdIn.readLine().trim();

            if ( line.equals("exit") ) break;

            try {
                int[] inputNumbers = splitLine(line);
                int firstNumber = inputNumbers[0],
                    secondNumber = inputNumbers[1];
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
            } catch (NullPointerException e){
                StdOut.println(e.getMessage());
            } finally {
                /* StdOut.println("Введите данные в формате 4 + 6, VII - V, 9 * 10. " +
                        "\nИспользовать римские или арабские числа от 1 до 10." +
                        "\nНе использовать римские и арабские цифры вместе.");
                 */
            }
        }
    }
}
```
