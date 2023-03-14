package validators;

import Enums.Color;
import Enums.MovieGenre;
import Enums.MpaaRating;
import exceptions.InvalidFieldException;

import java.time.LocalDate;
import java.util.HashMap;

public class AddFromFileValidator {

    /**
     *
     * @param key
     * @param answers
     * @throws InvalidFieldException
     */

    public static void checkAnswers(int key, HashMap<Integer, Object> answers) throws InvalidFieldException{
        switch (key){
            case 0 -> {
                if (!MovieValidator.isValidName((String) answers.get(0))){
                    throw new InvalidFieldException("Недопустимое название фильма");
                }
            }
            case 1 -> {
                if (!CoordsValidator.isValidX(Double.parseDouble((String) answers.get(1)))){
                    throw new InvalidFieldException("Недопустимая координата Х");
                }
            }
            case 2 -> {
                if (!CoordsValidator.isValidY(Float.parseFloat((String) answers.get(2)))){
                    throw new InvalidFieldException("Недопустимая координата Y");
                }
            }

            case 3 -> {
                if (!MovieValidator.isValidOscarsCount(Integer.parseInt((String) answers.get(3)))){
                    throw new InvalidFieldException("Недопустимое кол-во оскаров");
                }
            }

            case 4 -> {
                if (!MovieValidator.isValidLength(Integer.valueOf((String) answers.get(4)))){
                    throw new InvalidFieldException("Недопустимая длина");
                }
            }

            case 5 -> {
                if (!MovieValidator.isValidGenre(MovieGenre.valueOf((String) answers.get(5)))){
                    throw new InvalidFieldException("Недопустимый жанр");
                }
            }

            case 6 -> {
                if (!MovieValidator.isValidRating(MpaaRating.valueOf((String) answers.get(6)))){
                    throw new InvalidFieldException("Недопустимый рейтинг");
                }
            }

            case 7 -> {
                if (!PersonValidator.isValidName((String) answers.get(7))){
                    throw new InvalidFieldException("Недопустимое имя режиссера");
                }
            }

            case 8 -> {
                if (!PersonValidator.isValidBirthday(LocalDate.parse((CharSequence) answers.get(8)))){
                    throw new InvalidFieldException("Недопустимая дата рождения режиссера");
                }
            }

            case 9 -> {
                if (!PersonValidator.isValidPassport((String) answers.get(9))){
                    throw new InvalidFieldException("Недопустимые данные паспорта");
                }
            }

            case 10 -> {
                if (!PersonValidator.isValidHair(Color.valueOf((String) answers.get(10)))){
                    throw new InvalidFieldException("Недопустимый цвет волос");
                }
            }

            case 12 -> {
                if (answers.get(12) == null){
                    throw new InvalidFieldException("Недопустимая локация Y");
                }
            }

            case 13 -> {
                if (answers.get(13) == null){
                    throw new InvalidFieldException("Недопустимая локация Z");
                }
            }
        }
    }
}
