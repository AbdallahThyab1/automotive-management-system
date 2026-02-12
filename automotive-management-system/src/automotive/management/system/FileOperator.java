package automotive.management.system;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public interface FileOperator {

    default <T> void printContent(T[] t) {
        if (t == null || t.length == 0) {
            System.out.println("Array is null or empty");
            return;
        }
        System.err.println("Array content : ");
        for (T K : t) {
            System.err.println(K);
        }
    }

    public ArrayList<String> readFile(String path);

    default void saveFile(ArrayList<String> content, String path) {
        if (content == null || content.isEmpty()) {
            System.err.println("Nothing to save , the list is empty");
            return;
        }
        try {
            PrintWriter pw = new PrintWriter(new File(path));
            for (String line : content) {
                pw.println(line);
            }
            System.out.println("File Saved " + path);
        } catch (IOException e) {
            System.out.println("Error writing file");
        }
    }

    default String[] convertArrayListToArray(ArrayList<String> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        String[] newArray = new String[list.size()];
        if (newArray == null) {
            return null;
        }
        for (int i = 0; i < newArray.length; i++) {
            newArray[i] = list.get(i);
        }
        return newArray;
    }
}

