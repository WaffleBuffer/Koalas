/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thekoalas.koalas;

import java.util.List;

/**
 *
 * @author zhangna
 */
public interface IDataFrame {

    /**
     * Returns the content of all the dataset.
     *
     * @return The content of all the dataset in String format.
     */
    public String getAll();

    /**
     * Returns the content of the nbLines first lines.
     * If there is not enough lines to display, display the maximum of lines possible.
     *
     *
     * @param nbLines The numbers of lines that you want to display.
     * @return The content of the nbLines first lines.
     */
    public String getNFirstLines(int nbLines);

    /**
     * Returns the content of the nbLines last lines.
     *If there is not enough lines to display, display the maximum of lines possible.
     * 
     * @param nbLines The numbers of lines that you want to display.
     * @return The content of the nbLines first lines.
     */
    public String getNLastLines(int nbLines);

    /**
     * Returns the content of all the dataset from the index startIndex
     * 
     * If the index is out of range : an exeption is raised (IndexOutOfBounds)
     *
     * @param startIndex The first index from where the data should be
     * displayed.
     * @return The content of all the dataset from the index startIndex.
     */
    public String getLineSubset(int startIndex);

    /**
     * Returns the content of all lines from the row names specified in
     * rowNames.
     * 
     * Invalid names can be provided, no warning will be displayed.
     *
     * @param rowNames The name of the columns which should be displayed
     * @return the content of all lines from the row names specified in
     * rowNames.
     */
    public String getRowSubset(List<String> rowNames);

}
