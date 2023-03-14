package functionalClasses;

import java.util.HashMap;

public class CommandList {
    private static HashMap<String, String> commandList = new HashMap<>();

    public static HashMap<String, String> fillCommandList(){
        commandList.put("help", "Вывести справку по доступным командам");
        commandList.put("info", "Вывести в стандартный поток вывода информацию о коллекции");
        commandList.put("show", "Вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
        commandList.put("add {element}", "добавить новый элемент в коллекцию");
        commandList.put("update id {element}", "обновить значение элемента коллекции, id которого равен заданному");
        commandList.put("remove_by_id id", "удалить элемент из коллекции по его id");
        commandList.put("clear", "очистить коллекцию");
        commandList.put("save", "сохранить коллекцию в файл");
        commandList.put("execute_script file_name", "считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.");
        commandList.put("exit", "завершить программу (без сохранения в файл)");
        commandList.put("add_if_min {element}", "добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции");
        commandList.put("remove_greater {element}", " удалить из коллекции все элементы, превышающие заданный");
        commandList.put("history", "вывести последние 8 команд (без их аргументов)");
        commandList.put("remove_any_by_oscars_count oscarsCount", "удалить из коллекции один элемент, значение поля oscarsCount которого эквивалентно заданному");
        commandList.put("filter_by_genre genre", "вывести элементы, значение поля genre которых равно заданному");
        commandList.put("print_descending", "вывести элементы коллекции в порядке убывания");

        return commandList;
    }
}
