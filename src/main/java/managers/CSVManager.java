package managers;


import model.CsvEntry;
import util.Util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

/**
 * Manages writing data to CSV file.
 */
public class CSVManager {

    private static final String SEPARATOR = ", ";
    private static final String NEW_LINE = "\n";
    private static final String CSV_EXTENSION = ".csv";
    private static final String DIR_SEPARATOR = "/";
    private static final char SLASH =  '/';
    private static final char BACKSLASH = '\\';

    private static final String EMPTY_LIST = "No entries to add to a file.";
    private static final String NO_DIRECTORY = " directory does not exist.";
    private static final String NO_FILE_NAME_MSG = "File with no name cannot be saved.";
    private static final String COULD_NOT_SAVE = "Could not save file.";
    private static final String COULD_NOT_ADD = "Could not add entry: ";

    /**
     * Writes entries to csv file.
     * @param elements List of elements to put in csv file.
     * @param name Name of the file
     * @return path to the csv file with elements data.
     */
    public static String writeCitiesToCSV(List<? extends CsvEntry> elements, String name, String parentPath) {
        if(Util.isNullOrEmpty(elements))
        {
            System.out.println(EMPTY_LIST);
            return null;
        }
        if(Util.isNullOrEmpty(name))
        {
            System.out.println(NO_FILE_NAME_MSG);
            return null;
        }
        if(!new File(parentPath).exists())
        {
            System.out.println(parentPath + NO_DIRECTORY);
            return null;
        }
        try {
            String csvFile = parentPath + DIR_SEPARATOR + name + CSV_EXTENSION;
            FileWriter writer = new FileWriter(csvFile);
            for (CsvEntry element : elements) {
                writeEntry(writer, element.prepareCsvEntry());
            }
            writer.flush();
            writer.close();
            return csvFile.replace(BACKSLASH,SLASH);
        } catch (IOException e) {
            System.out.println(COULD_NOT_SAVE);
            e.printStackTrace();
        }
        return null;
    }


    /**
     * Writes an entry to csv file.
     * @param writer writer used to put data into file
     * @param data data to put in csv file
     */
    private static void writeEntry(Writer writer, List<String> data) {
        String input = Util.join(data, SEPARATOR);
        input += NEW_LINE;
        try {
            writer.append(input);
        } catch (IOException e) {
            System.out.println(COULD_NOT_ADD + input);
            e.printStackTrace();
        }
    }
}
