package model;

import java.util.List;

/**
 * Interface for objects that can be added to csv files.
 */
public interface CsvEntry {

    /**
     * Prepares entry line to csv file as List of Strings.
     *
     * @return List of parameters for csv file entry line.
     */
    List<String> prepareCsvEntry();
}
