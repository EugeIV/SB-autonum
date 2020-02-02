import java.util.*;

public class Main
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<>();
        HashSet<String> set = new HashSet<>();
        TreeSet<String> tree = new TreeSet<>();
        String[] arrayLiters = new String[]{"А", "В", "Е", "К", "М", "Н", "О", "Р", "С", "Т", "У", "Х"};

        //Создание массива регионов в правильном формате
        String[] regions = new String[5];
        for (int i = 0; i < regions.length; i++)
        {
            regions[i] = String.format("%02d", i + 1);
        }

        // Создание списка номеров
        for (String region : regions)
        {
            for (String arrayLiterFirst : arrayLiters)
            {
                for (String arrayLiterSecond : arrayLiters)
                {
                    for (String arrayLiterThird : arrayLiters)
                    {
                        for (int j = 1; j <= 999; j++)
                        {
                            String str = String.format("%03d", j);
                            list.add(arrayLiterFirst + str + arrayLiterSecond + arrayLiterThird + region);
                        }
                    }
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
            if (search2 >= 0) {
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
