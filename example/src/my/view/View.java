package my.view;

import my.model.Laptop;
import my.model.support.Brand;
import my.model.support.Color;
import my.repository.LaptopRepository;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static my.repository.LaptopRepository.connect;

public class View {
    // инициализируем бд, вызвав статический метод репозитория, при этом создаем поле класса,
    // которое видно во всех методах этого класса
    private static final Map<Integer, Laptop> DB = connect();
    // для всего класса создаю поле Scanner, java закроет его автоматически, когда нужно
    private static final Scanner scanner = new Scanner(System.in);

    // делаем инъекцию репозитория ноутбуков
    private static final LaptopRepository repository = new LaptopRepository();

    public static void run() {
        while (true) {
            String choice = actionChoice();
            switch (choice) {
                case "0":
                    System.exit(0);
                    break;
                case "repeat":
                    break;
                case "1":
                    System.out.println(DB.values()); // выводим все ноутбуки
                    break;
                case "2":
                    String filter = filterChoice();
                    returnFilteredLaptops(filter);
                    break;
                case "3":
                    filter = filterChoice();
                    returnFilteredLaptops(filter);
            }
        }
    }

    // метод, который вернет выбранное пользователем действие
    private static String actionChoice() {
        System.out.println("Enter command: ");
        System.out.println("1 - show all laptops; ");
        System.out.println("2 - filter laptops");
        System.out.println("0 - quit");
        String choice = scanner.next();
        if (userChoiceIsIncorrect(choice)) {
            return "repeat";
        }
        return choice;
    }

    // проверка на правильность введенных пользователем данных
    private static boolean userChoiceIsIncorrect(String choice) {
        choice = choice.trim(); // метод, который уберет пробелы по краям (на всякий случай)
        if (choice.equals("0")) return false;
        if (choice.equals("1")) return false;
        return !choice.equals("2");
    }

    // метод, который вернет выбранный пользователем фильтр для вывода ноутбуков:
    private static String filterChoice() {
        System.out.println("Enter filter: ");
        System.out.println("1 - filter laptops by brand;");
        System.out.println("2 - filter laptops by color;");
        System.out.println("0 - quit");
        String choice = scanner.next();
        if (userChoiceIsIncorrect(choice)) {
            return "repeat";
        }
        return choice;
    }

    private static void returnFilteredLaptops(String filter) {
        switch (filter) {
            case "0" -> {
                System.out.println("By!");
                System.exit(0);
            }
            case "1" -> {
                System.out.println("Enter brand: ");
                Brand brand = Brand.valueOf(scanner.next().toUpperCase()); // получаем enum из строки, которую всегда

                // переводим в строку, состоящую из больших букв (toUpperCase)
                List<Laptop> laptops = repository.getByBrand(brand);
                System.out.println(laptops);
            }
            case "2" -> {
                System.out.println("Enter color: ");
                Color color = Color.valueOf(scanner.next().toUpperCase());// получаем enum из строки, которую всегда

                // переводим в строку, состоящую из больших букв (toUpperCase)
                List<Laptop> laptops = repository.getByColor(color);
                System.out.println(laptops);
            }
            default -> System.out.println("Invalid filter parameters entered");
        }

    }
}
