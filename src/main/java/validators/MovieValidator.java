package validators;

import Enums.Color;
import Enums.MovieGenre;
import Enums.MpaaRating;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MovieValidator {
    public static boolean isValidName(String name){
        return name != null;
    }

    public static boolean isValidOscarsCount(int oscarsCount){
        return oscarsCount > 0;
    }

    public static boolean isValidLength(Integer length){
        return length > 0;
    }

    public static boolean isValidCreationDate(LocalDate creationDate){
        return creationDate.toString().matches("^\\d{4}-\\d{2}-\\d{2}$");
    }

    public static boolean isValidRating(MpaaRating mpaaRating) {
        MpaaRating[] validRatings = MpaaRating.values();
        for (MpaaRating mpr: validRatings){
            if (mpr == mpaaRating) {
                return true;
            }
        }
        return false;
    }

    public static boolean isValidGenre(MovieGenre movieGenre) {
        MovieGenre[] validGenres = MovieGenre.values();
        for (MovieGenre g: validGenres){
            if (g == movieGenre) {
                return true;
            }
        }
        return false;
    }
}
