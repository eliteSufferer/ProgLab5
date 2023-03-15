package functionalClasses;

import Enums.Color;
import Enums.MovieGenre;
import Enums.MpaaRating;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**

 The Settings class represents the settings for creating a new Movie object.
 It contains a list of {@link SetValues} objects that specify the data type,
 prompt message, and optional parameters for each movie attribute.
 */
public class Settings {
    private static ArrayList<SetValues> settings = new ArrayList<>();

    /**
     Fills the settings list with default {@link SetValues} objects for each movie attribute.
     @return the list of {@link SetValues} objects representing the settings for creating a new Movie object.
     */
    public static ArrayList<SetValues> fillSettings(){
        settings.add(new SetValues(0, "String", true, "Название фильма: "));
        settings.add(new SetValues(1, "double", true, "Координата X (можно дробное, больше -201): "));
        settings.add(new SetValues(2, "float", true, "Координата Y (тоже можно дробное, больше -838): "));
        settings.add(new SetValues(3, "int", true, "Количество Оскаров у фильма (больше 0): "));
        settings.add(new SetValues(4, "Integer", true, "Длина (целое, больше 0): "));
        settings.add(new SetValues(5, "MovieGenre", true, "Жанр фильма: " + Arrays.asList(MovieGenre.values())));
        settings.add(new SetValues(6, "MpaaRating", false, "Рейтинг фильма: "  + Arrays.asList(MpaaRating.values())));

        settings.add(new SetValues(8, "LocalDate", false, "Дата рождения: "));
        settings.add(new SetValues(9, "String", false, "Данные паспорта (строка не короче 10 симв.): "));
        settings.add(new SetValues(10, "Color", false, "Цвет волос: " + Arrays.asList(Color.values())));
        settings.add(new SetValues(11, "float", true, "Местоположение режиссера. Координата Х: "));
        settings.add(new SetValues(12, "Integer", true, "Координата Y: "));
        settings.add(new SetValues(13, "Float", true, "Координата Z: "));
        settings.add(new SetValues(14, "String", false, "Название локации: "));

        return settings;
    }
}
