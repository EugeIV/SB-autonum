import java.util.*;

/*
Нужна помощь:
Код получился очень массивным, всяко есть возможность минимизировать код генерирования госномеров.
Не могу до конца разобраться с генерированием госномеров. Как сгенерировать номера в формате А000ВЕ.
Есть косяк при генерировании номеров, если вводить, например, А333АВ54, то его нет в списке, хотя должен быть.
И в принципе генерирования формата с начальной буквой А не произошло.
 */


public class Main
{
    public static void main(String[] args)
    {
        // A333AA197
        // А, В, Е, К, М, Н, О, Р, С, Т, У, Х

        Scanner scanner = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<>();
        HashSet<String> set = new HashSet<>();
        TreeSet<String> tree = new TreeSet<>();
        String[] arrayLiters = new String[]{"А", "В", "Е", "К", "М", "Н", "О", "Р", "С", "Т", "У", "Х"};

        //Создание массива регионов и преобразование их в строку правильного формата
        String[] regions = new String[199];
        for (int i = 0; i < regions.length; i++)
        {
            if (i >= 0 && i < 9)
            {
                String k = Integer.toString(i + 1);
                regions[i] = "0" + k;
            }
            else
            {
                regions[i] = Integer.toString(i + 1);
            }
        }


        //Добавляем в список номера с одинаковыми буквами
        for (int m = 0; m < regions.length; m++)
        {
            for (int i = 0; i < arrayLiters.length; i++)
            {
                for (int j = 1; j <= 999; j++)
                {
                    if (j >= 1 && j < 10)
                    {
                        String str00 = Integer.toString(j);
                        list.add(arrayLiters[i] + "00" + str00 + arrayLiters[i] + arrayLiters[i] + regions[m]);
                    }
                    else if (j >= 10 && j < 100)
                    {
                        String str0 = Integer.toString(j);
                        list.add(arrayLiters[i] + "0" + str0 + arrayLiters[i] + arrayLiters[i] + regions[m]);
                    }
                    else
                    {
                        String str = Integer.toString(j);
                        list.add(arrayLiters[i] + str + arrayLiters[i] + arrayLiters[i] + regions[m]);
                    }
                }
            }
        }

        //Добавляем в список номера типа A000AB
        for (int m = 0; m < regions.length; m++)
        {
            for (int i = 0; i < arrayLiters.length - 1; i++)
            {
                for (int j = 1; j <= 9; j++)
                {
                    String k = Integer.toString(j);
                    list.add(arrayLiters[i] + k + k + k + arrayLiters[i] + arrayLiters[i + 1] + regions[m]);
                }
            }
        }

        //Добавляем в список номера типа A000BA
        for (int m = 0; m < regions.length; m++)
        {
            for (int i = 0; i < arrayLiters.length - 1; i++)
            {
                for (int j = 1; j <= 9; j++)
                {
                    String k = Integer.toString(j);
                    list.add(arrayLiters[i] + k + k + k + arrayLiters[i + 1] + arrayLiters[i] + regions[m]);
                }
            }
        }

        //Добавляем в список номера типа B000AA
        for (int m = 0; m < regions.length; m++)
        {
            for (int i = 0; i < arrayLiters.length - 1; i++)
            {
                for (int j = 1; j <= 9; j++)
                {
                    String k = Integer.toString(j);
                    list.add(arrayLiters[i + 1] + k + k + k + arrayLiters[i] + arrayLiters[i] + regions[m]);
                }
            }
        }

        set.addAll(list);
        tree.addAll(list);

        for (;;) {
            String search = scanner.nextLine();

            // Прямой перебор
            long start1 = System.nanoTime();
            boolean search1 = list.contains(search);
            long duration1 = System.nanoTime() - start1;
            if (search1) {
                System.out.println("Поиск перебором: номер найден, время поиска: " + duration1 + " нс");
            } else {
                System.out.println("Поиск перебором: номер не найден, время поиска: " + duration1 + " нс");
            }

            // Бинарный поиск
            Collections.sort(list);
            long start2 = System.nanoTime();
            int search2 = Collections.binarySearch(list, search);
            long duration2 = System.nanoTime() - start2;
            if (search2 > 0) {
                System.out.println("Бинарный поиск: номер найден, время поиска: " + duration2 + " нс");
            } else {
                System.out.println("Бинарный поиск: номер не найден, время поиска: " + duration2 + " нс");
            }

            // TreeSet
            long start3 = System.nanoTime();
            boolean search3 = tree.contains(search);
            long duration3 = System.nanoTime() - start3;
            if (search3) {
                System.out.println("Поиск в HashSet: номер найден, время поиска: " + duration3 + " нс");
            } else {
                System.out.println("Поиск в HashSet: номер не найден, время поиска: " + duration3 + " нс");
            }

            // HashSet
            long start4 = System.nanoTime();
            boolean search4 = set.contains(search);
            long duration4 = System.nanoTime() - start4;
            if (search4) {
                System.out.println("Поиск в TreeSet: номер найден, время поиска: " + duration4 + " нс");
            } else {
                System.out.println("Поиск в TreeSet: номер не найден, время поиска: " + duration4 + " нс");
            }
        }
    }
}
