package my.repository;

import my.model.Laptop;
import my.model.support.Brand;
import my.model.support.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class LaptopRepository {
    private static final Map<Integer, Laptop> DB = new TreeMap<>();

    public static Map<Integer, Laptop> connect() {
        fillDB();
        return DB;
    }

    private static void fillDB() { // Утилитный метод заполнения нашей псевдо-бд
        DB.put(1, new Laptop(Brand.LENOVO, Color.BLACK));
        DB.put(2, new Laptop(Brand.LENOVO, Color.SILVER));
        DB.put(3, new Laptop(Brand.LENOVO, Color.BLACK));
        DB.put(4, new Laptop(Brand.MACBOOK, Color.BLACK));
        DB.put(5, new Laptop(Brand.MACBOOK, Color.SILVER));
        DB.put(6, new Laptop(Brand.HP, Color.WHITE));
        DB.put(7, new Laptop(Brand.MACBOOK, Color.BLACK));
        DB.put(8, new Laptop(Brand.LENOVO, Color.WHITE));
    }

    public List<Laptop> getByBrand(Brand brand) {
        List<Laptop> result = new ArrayList<>();
        DB.forEach((key, value) -> {
            if (value.getBrand() == brand) { // В value у нас лежит ноутбук, у которого есть метод getBrand()
                result.add(value); // если брэнд соответствует тому что пришло в аргументе метода на
                // 30 стр - добавлем его в результат
            }
        });

        return result; // возвращаем список, соответствующий запросу по бренду
    }

    public List<Laptop> getByColor(Color color) { // такой же метод, как и поиск по бренду
        List<Laptop> result = new ArrayList<>();
        DB.forEach((key, value) -> {
            if (value.getColor() == color) {
                result.add(value);
            }
        });

        return result; // возвращаем список, соответствующий запросу по цвету
    }
}
