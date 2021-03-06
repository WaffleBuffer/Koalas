package com.thekoalas.koalas;

import com.opencsv.CSVReader;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;

public class DataFrame implements IDataFrame {

    private List<Column> dataset;

    private DataFrame() {

    }

    public DataFrame(List<Column> columns) {

        setDataset(columns);
    }

    @Override
    public List<Column> getDataset() {
        return dataset;
    }

    @Override
    public void setDataset(List<Column> dataset) throws NoColumnsException {

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

    public DataFrame(List<String> names, List<List<? extends Comparable<?>>> columns) throws NotAsMuchNamesAsColumnsException, NoColumnsException, ColumnsNotSameSizeException {

        boolean allEqual = true;

        if (names.size() != columns.size()) {
            throw new NotAsMuchNamesAsColumnsException();
        }

        if (columns.isEmpty()) {
            throw new NoColumnsException();
        }

        int sizeExpected = columns.get(0).size();
        HashSet<String> map = new HashSet();

        for (int i = 0; i < columns.size() && allEqual; i++) {
            allEqual = (columns.get(i).size() == sizeExpected);
            if (map.contains(names.get(i))) {
                throw new NameAlreadyDefinedException();
            } else {
                map.add(names.get(i));
            }
        }

        if (!allEqual) {
            throw new ColumnsNotSameSizeException();
        }

        this.dataset = new ArrayList<>();
        for (int i = 0; i < names.size(); i++) {
            Column cols = new Column(names.get(i), columns.get(i));
            this.dataset.add(cols);
        }
    }

    public DataFrame(String filename) throws IOException, ParseException {

        File file = new File(filename);
        if (!file.exists()) {
            throw new IOException("File doesn't exists");
        }

        ArrayList<Column> colList = new ArrayList<>();
        try (
                Reader reader = Files.newBufferedReader(Paths.get(filename));
                CSVReader csvReader = new CSVReader(reader);) {

            List<String[]> records = csvReader.readAll();

            if (records.isEmpty()) {
                throw new NoColumnsException("Missing columns name");
            }

            int expectedWidth = records.get(0).length;
            int expectedHeight = records.size();

            for (int i = 0; i < expectedWidth; i++) {

                String name = records.get(0)[i];
                Consts.DataType currentType = Consts.DataType.DATE;

                for (int j = 1; j < expectedHeight; j++) {

                    String[] line = records.get(j);
                    if (line.length != expectedWidth) {
                        throw new ColumnsNotSameSizeException();
                    }

                    if (currentType == Consts.DataType.DATE) {
                        try {
                            String data = records.get(j)[i].toLowerCase();
                            String dateFormat = DateUtile.determineDateFormat(data);
                            if (dateFormat != null) {
                                SimpleDateFormat format = new SimpleDateFormat(dateFormat, Locale.ENGLISH);
                                Date date = format.parse(records.get(j)[i]);
                            } else {
                                currentType = Consts.DataType.INT;
                            }
                        } catch (ParseException e) {
                            currentType = Consts.DataType.INT;
                        }
                    }

                    if (currentType == Consts.DataType.INT) {
                        try {
                            Integer.parseInt(records.get(j)[i]);

                        } catch (NumberFormatException e) {
                            currentType = Consts.DataType.DOUBLE;
                        }
                    }

                    if (currentType == Consts.DataType.DOUBLE) {
                        try {
                            Double.parseDouble(records.get(j)[i]);

                        } catch (NumberFormatException e) {
                            currentType = Consts.DataType.STRING;
                        }
                    }

                }

                ArrayList values;
                switch (currentType) {
                    case DATE:
                        values = new ArrayList<Date>();
                        break;

                    case DOUBLE:
                        values = new ArrayList<Double>();

                        break;
                    case INT:
                        values = new ArrayList<Integer>();
                        break;
                    default:
                        values = new ArrayList<String>();
                        break;
                }

                for (int j = 1; j < expectedHeight; j++) {
                    switch (currentType) {
                        case DATE:
                            String dateFormat = DateUtile.determineDateFormat(records.get(j)[i]);
                            SimpleDateFormat format = new SimpleDateFormat(dateFormat, Locale.ENGLISH);
                            Date date = format.parse(records.get(j)[i]);
                            values.add(date);
                            break;

                        case DOUBLE:
                            values.add(Double.parseDouble(records.get(j)[i]));

                            break;
                        case INT:
                            values.add(Integer.parseInt(records.get(j)[i]));
                            break;
                        default:
                            values.add(records.get(j)[i]);
                            break;
                    }

                }

                Column col = new Column(name, values);
                colList.add(col);

            }

        }
        setDataset(colList);

    }

    @Override
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

        // We should never go in this if but just in case, we let it here
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
    public DataFrame getLineSubset(int startIndex, int endIndex) {
        int linesPossible = this.dataset.get(0).getData().size();
        if (startIndex < 0 || endIndex < 0 || startIndex >= linesPossible || endIndex >= linesPossible) {
            throw new IndexOutOfBoundsException("Invalid indexes in getLineSubset");
        }
        List<Column> cols = new ArrayList<>();

        for (int i = 0; i < this.dataset.size(); i++) {
            int endIndexTmp = endIndex;
            String name = this.dataset.get(i).getName();
            ArrayList list = (ArrayList) ((ArrayList) this.dataset.get(i).getData()).clone();
            for (int j = 0; j < startIndex; j++) {
                list.remove(0);
                --endIndexTmp;
            }
            for (int j = list.size() - 1; j >= endIndexTmp; j--) {
                System.out.println("remove");
                list.remove(list.size() - 1);
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
    public DataFrame getColumnSubset(String[] columnNames) {

        return getColumnSubset(new ArrayList(Arrays.asList(columnNames)));
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
        return Objects.equals(this.dataset, other.dataset);
    }

    @Override
    public String statistics() {
        String retour = "";
        for (int i = 0; i < this.dataset.size(); i++) {
            retour += "Colonne " + this.dataset.get(i).getName() + " : minimum is " + this.dataset.get(i).min() + " maximum is " + this.dataset.get(i).max() + " sum is " + this.dataset.get(i).sum() + " mean is " + this.dataset.get(i).mean() + "\n";
        }

        return retour;
    }
    
    @Override
    public GroupBy groupBy(String name1, String... names){
        ArrayList<String> namesToPass = new ArrayList<>();
        namesToPass.add(name1);
        for (String s : names) {
            namesToPass.add(s);
        }
        return groupBy(namesToPass);
    }

    @Override
    public GroupBy groupBy(String[] colName) {
        return groupBy(new ArrayList(Arrays.asList(colName)));
    }

    @Override
    public GroupBy groupBy(List<String> colName) {
        List<Integer> colIndices = new ArrayList<>();
        for (int j = 0; j < colName.size(); j++) {
            int col = -1;
            for (int i = 0; i < dataset.size() && col == -1; i++) {
                if (colName.get(j).equals(dataset.get(i).getName())) {
                    col = i;
                }
            }
            if (col == -1) {
                throw new UnknownNameException();
            }
            colIndices.add(col);
        }
        int col = colIndices.get(0);
        HashMap<String, ArrayList<Integer>> map = new HashMap<>();
        HashMap<String, ArrayList<Comparable>> mapValues = new HashMap<>();
        List<Comparable> colValues = dataset.get(col).getData();
        for (int i = 0; i < colValues.size(); i++) {
            String currentString = "";
            ArrayList<Comparable> groupValues = new ArrayList<>();
            for (Integer val : colIndices) {
                Comparable current = (Comparable) this.dataset.get(val).getData().get(i);
                groupValues.add(current);
                currentString += current.toString();
            }

            if (!map.containsKey(currentString)) {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(i);
                map.put(currentString, list);
                mapValues.put(currentString, groupValues);
            } else {
                ArrayList<Integer> list = map.get(currentString);
                list.add(i);
                map.put(currentString, list);
            }

        }
        Set<String> set = map.keySet();
        ArrayList<String> listGroup = new ArrayList<>(set);
        ArrayList<GroupedData> groupD = new ArrayList<>();
        for (int i = 0; i < listGroup.size(); i++) {
            ArrayList<Integer> list = map.get(listGroup.get(i));
            ArrayList<Column> cols = new ArrayList<>();
            for (int j = 0; j < this.dataset.size(); j++) {
                String name = this.dataset.get(j).getName();
                ArrayList<Comparable> values = new ArrayList<>();
                for (int k = 0; k < list.size(); k++) {
                    values.add((Comparable) this.dataset.get(j).getData().get(list.get(k)));

                }
                Column colCreated = new Column(name, values);
                cols.add(colCreated);

            }
            GroupedData d = new GroupedData(listGroup.get(i), colName, cols, mapValues.get(listGroup.get(i)));
            groupD.add(d);

        }
        GroupBy g = new GroupBy(colName, groupD);

        return g;
    }

}
