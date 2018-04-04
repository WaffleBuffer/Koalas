/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thekoalas.koalas;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author zhangna
 */
public class DataFrame implements IDataFrame {

    private List<Column> dataset;

    private DataFrame() {

    }

    public DataFrame(List<Column> columns) {

        setDataset(columns);
    }

    public List<Column> getDataset() {
        return dataset;
    }

    private void setDataset(List<Column> dataset) throws NoColumnsException {

        if (dataset.isEmpty()) {
            throw new NoColumnsException();
        }
        //All columns of the same size
        int expectedSize = dataset.get(0).getData().size();
        boolean allSame = true;
        HashSet<String> map = new HashSet();
        map.add(dataset.get(0).getName());
        boolean alreadyDefined = false;

        for (int i = 1; i < dataset.size() && allSame && !alreadyDefined; i++) {
            if (dataset.get(i).getData().size() != expectedSize) {
                allSame = false;
            }
            if (map.contains(dataset.get(i).getName())) {
                alreadyDefined = true;
            } else {
                map.add(dataset.get(i).getName());
            }
        }
        if (!allSame) {
            throw new ColumnsNotSameSizeException();
        }
        if (alreadyDefined) {
            throw new NameAlreadyDefinedException();
        }

        this.dataset = dataset;
    }

    public DataFrame(List<String> names, List<List<? extends Comparable>> columns) throws NotAsMuchNamesAsColumnsException, NoColumnsException, ColumnsNotSameSizeException {

        boolean allEqual = true;

        if (names.size() != columns.size()) {
            System.out.println("The number of names is not equal to the number of values column. Aborting");
            throw new NotAsMuchNamesAsColumnsException();
        }

        if (columns.isEmpty()) {
            System.out.println("Trying to create an empty dataset. Aborting");
            throw new NoColumnsException();
        }

        int sizeExpected = columns.get(0).size();
        HashSet<String> map = new HashSet();
        boolean alreadyDefined = false;

        for (int i = 0; i < columns.size() && allEqual && !alreadyDefined; i++) {
            allEqual = (columns.get(i).size() == sizeExpected);
            if (map.contains(names.get(i))) {
                throw new NameAlreadyDefinedException();
            } else {
                map.add(names.get(i));
            }
        }

        if (!allEqual) {
            System.out.println("The number of data is not the same in all columns. Aborting");
            throw new ColumnsNotSameSizeException();
        }
        if (alreadyDefined) {
            throw new NameAlreadyDefinedException();
        }

        this.dataset = new ArrayList<>();
        for (int i = 0; i < names.size(); i++) {
            Column cols = new Column(names.get(i), columns.get(i));
            this.dataset.add(cols);
        }
    }

    public DataFrame(String filename) {
        System.out.println("TODO : parse a CSV file");

    }

    public String getColNames() {
        String ret = "";
        for (Column dataset1 : this.dataset) {
            ret += dataset1.getName() + "\t";
        }
        ret += "\n";

        return ret;
    }

    @Override
    public String display() {

        if (this.dataset.isEmpty()) {
            return "Trying to print an empty dataset";
        }

        //Displaying the column names
        String ret = getColNames();

        for (int i = 0; i < this.dataset.get(0).getData().size(); i++) {
            for (int j = 0; j < this.dataset.size(); j++) {
                ret += this.dataset.get(j).getData().get(i) + "\t";
            }
            ret += "\n";
        }

        return ret;
    }

    @Override
    public String head(int nbLines) {
        int linesPossible = this.dataset.get(0).getData().size();
        if (linesPossible < nbLines) {
            System.out.println("Impossible d'afficher " + nbLines);
            return display();
        }
        String ret = getColNames();
        for (int i = 0; i < nbLines; i++) {
            for (int j = 0; j < this.dataset.size(); j++) {
                ret += this.dataset.get(j).getData().get(i) + "\t";
            }
            ret += "\n";
        }
        return ret;
    }

    @Override
    public String head() {
        return head(5);
    }

    @Override
    public String tail(int nbLines) {
        int linesPossible = this.dataset.get(0).getData().size();
        if (linesPossible < nbLines) {
            System.out.println("Impossible d'afficher " + nbLines);
            return display();
        }
        String ret = getColNames();
        int startIndex = linesPossible - nbLines;
        for (int i = startIndex; i < this.dataset.get(0).getData().size(); i++) {
            for (int j = 0; j < this.dataset.size(); j++) {
                ret += this.dataset.get(j).getData().get(i) + "\t";
            }
            ret += "\n";
        }
        return ret;
    }

    @Override
    public String tail() {
        return tail(5);
    }

    @Override
    public DataFrame getLineSubset(int startIndex) {
        int linesPossible = this.dataset.get(0).getData().size();
        if (startIndex < 0 || startIndex >= linesPossible) {
            return this;
        }

        List<Column> cols = new ArrayList<>();

        for (int i = 0; i < this.dataset.size(); i++) {
            String name = this.dataset.get(i).getName();
            ArrayList list = (ArrayList) ((ArrayList) this.dataset.get(i).getData()).clone();
            for (int j = 0; j < startIndex; j++) {
                list.remove(0);
            }
            Column toAdd = new Column(name, list);
            cols.add(toAdd);
        }

        return new DataFrame(cols);

    }

    @Override
    public DataFrame getColumnSubset(List<String> columnNames) {
        ArrayList<Column> subset = new ArrayList<>();

        while (!columnNames.isEmpty()) {
            String name = columnNames.get(0);
            boolean found = false;
            for (int j = 0; j < this.dataset.size() && !found; j++) {
                Column col = this.dataset.get(j);
                if (col.getName().equals(name)) {
                    subset.add(col);
                    found = true;
                }

            }
            ArrayList<String> toRemove = new ArrayList<>();
            toRemove.add(name);
            columnNames.removeAll(toRemove);
        }

        return new DataFrame(subset);

    }

    @Override
    public String toString() {
        return display();
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DataFrame other = (DataFrame) obj;
        if (!Objects.equals(this.dataset, other.dataset)) {
            return false;
        }
        return true;
    }

    @Override
    public String statistics() {
        String retour = "";
        for (int i = 0; i < this.dataset.size(); i++) {
            List<Comparable> values = this.dataset.get(i).getData();
            Comparable min = values.get(0);
            Comparable max = values.get(0);
            for (int j = 1; j < values.size(); j++) {
                if (values.get(j).compareTo(min) < 0) {
                    min = values.get(j);
                }
                if (values.get(j).compareTo(max) > 0) {

                    max = values.get(j);
                }
            }
            
            String sum = "(invalid type to sum)";
            String mean = "(invlid type for mean)";
            
            if(!values.isEmpty()) {
                if(values.get(0) instanceof Number) {
                    Double sumRes = sumNbr(this.dataset.get(i));
                    sum = Double.toString(sumRes);
                    Double meanRes = sumRes / this.dataset.get(i).getData().size();
                    mean = Double.toString(meanRes);
                }
                else if(values.get(0) instanceof String) {
                    sum = sumStr(this.dataset.get(i));
                }
            }

            retour += "Colonne " + this.dataset.get(i).getName() + " : minimum is " + min + " maximum is " + max + " sum is " + sum + " mean is " + mean + "\n";
            //System.out.println("Colonne " + this.dataset.get(i).getName() + " : minimum is  " + min + " maximum is " + max + " sum is " + sum + " mean is " + sum / this.dataset.get(i).getData().size());
        }

        return retour;
    }
    
    public double sumNbr(Column<? extends Number> col) {
        double sum = 0;
        
        ArrayList<Number> list = (ArrayList<Number>) col.getData();
        for (int i = 0; i < list.size(); i++) {
            sum += list.get(i).doubleValue();
        }
        return sum;
    }
    
    public String sumStr(Column<String> col) {
        String sum = "";
        
        ArrayList<String> list = (ArrayList<String>) col.getData();
        for (int i = 0; i < list.size(); i++) {
            sum += list.get(i);
        }
        return sum;
    }

}
