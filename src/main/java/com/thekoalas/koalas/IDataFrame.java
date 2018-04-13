package com.thekoalas.koalas;

import java.util.List;

public interface IDataFrame {

    /**
     * Display the content of all the dataset.
     *
     * @return The content of all the dataset in String format.
     */
    public String display();

    /**
     * Returns the content of the nbLines first lines. If there is not enough
     * lines to display, display the maximum of lines possible.
     *
     *
     * @param nbLines The numbers of lines that you want to display.
     * @return The content of the nbLines first lines, if possible.
     */
    public String head(int nbLines);

    /**
     * Returns the content of the 5 first lines. If there is not enough lines to
     * display, display the maximum of lines possible.
     *
     *
     * @return The content of the 5 first lines, if possible.
     */
    public String head();

    /**
     * Returns the content of the nbLines last lines. If there is not enough
     * lines to display, display the maximum of lines possible.
     *
     *
     * @param nbLines The numbers of lines that you want to display.
     * @return The content of the nbLines last lines, if possible.
     */
    public String tail(int nbLines);

    /**
     * Returns the content of the 5 last lines. If there is not enough lines to
     * display, display the maximum of lines possible.
     *
     *
     * @return The content of the 5 last lines, if possible.
     */
    public String tail();

    /**
     * Returns the content of all the dataset from indexes.
     *
     * If the indexes are invalid (out of range for exemple) : an exeption is raised (IndexOutOfBounds)
     *
     * @param startIndex The first index from where the data should be
     * get.
     * @param endIndex The end of the last line to get. If it is superior than 
     * the IDataFrame size, then only the valid content will be get.
     * @return The content of all the dataset from the index startIndex.
     */
    public IDataFrame getLineSubset(int startIndex, int endIndex);

    /**
     * Returns the content of all lines from the column names specified in
     * columnNames.
     *
     * Invalid names can be provided, no warning will be displayed.
     *
     * @param columnNames The name of the columns which should be get
     * @return the content of all lines from the column names specified in
     * columnNames.
     */
    public IDataFrame getColumnSubset(List<String> columnNames);
    
    /**
     * Returns the content of all lines from the column names specified in
     * columnNames.
     *
     * Invalid names can be provided, no warning will be displayed.
     *
     * @param columnNames The name of the columns which should be displayed
     * @return the content of all lines from the column names specified in
     * columnNames.
     */
    public IDataFrame getColumnSubset(String[] columnNames);
    
    /**
     * Display statistics about the Dataframe 
     * @return The statistics in String format
     */
    public String statistics();
    
     /**
     * 
     * @param name1 The column where the group by has to be made
     * @param names Other optionnal columns
     * @return The resulting GroupBy object
     */
    public GroupBy groupBy(String name1, String... names);
    
    /**
     * 
     * @param colName The column where the group by has to be made
     * @return The resulting GroupBy object
     */
    public GroupBy groupBy(List<String> colName);
    
    /**
     * 
     * @param colName The column where the group by has to be made
     * @return The resulting GroupBy object
     */
    public GroupBy groupBy(String[] colName);
   
    /**
     * Get a single String containing all columns name separated by a \t.
     * @return A single String containing all columns name separated by a \t.
     */
    public String getColNames();
    
    /**
     * Change the data of this IDataFrame. See constructor for any constraint on the
     * data set.
     * @param dataset The new dataset to set.
     */
    public void setDataset(List<Column> dataset);
    
    /**
     * Get the columns of this IDataFrame.
     * @return The columns of this IDataFrame.
     */
    public List<Column> getDataset();
}
