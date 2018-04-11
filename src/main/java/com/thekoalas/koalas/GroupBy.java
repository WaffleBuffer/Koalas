/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thekoalas.koalas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author GONTARD Benjamin
 */
public class GroupBy {

    private List<String> groupNames;
    private List<GroupedData> data;

    public GroupBy(List<String> groupNames, List<GroupedData> data) {
        this.groupNames = groupNames;
        this.data = data;
    }

    public DataFrame min(List<String> names) {
        ArrayList<Column> cols = new ArrayList<>();
        ArrayList<ArrayList<Comparable>> groupValues = new ArrayList<>();
        ArrayList<ArrayList<Comparable>> minValues = new ArrayList<>();
        for (int i = 0; i < this.data.size(); i++) {
            groupValues.add(this.data.get(i).getGroupValues());
        }
        for (int i = 0; i < data.size(); i++) {
            minValues.add(this.data.get(i).min(names));
        }

        for (int i = 0; i < groupNames.size(); i++) {
            String name = groupNames.get(i);
            ArrayList<Comparable> colValues = new ArrayList<>();
            for (int j = 0; j < data.size(); j++) {
                colValues.add(groupValues.get(j).get(i));
            }
            cols.add(new Column(name, colValues));
        }

        for (int i = 0; i < names.size(); i++) {
            String name = "min(" + names.get(i) + ")";
            ArrayList<Comparable> colValues = new ArrayList<>();
            for (int j = 0; j < data.size(); j++) {
                colValues.add(minValues.get(j).get(i));
            }
            cols.add(new Column(name, colValues));
        }

        DataFrame d = new DataFrame(cols);
        return d;
    }

    public DataFrame min(String[] names) {
        return min(new ArrayList(Arrays.asList(names)));
    }

    public String minPrint(List<String> names) {
        return min(names).toString();
    }

    public String minPrint(String[] names) {
        return minPrint(new ArrayList(Arrays.asList(names)));
    }

    public DataFrame max(List<String> names) {
        ArrayList<Column> cols = new ArrayList<>();
        ArrayList<ArrayList<Comparable>> groupValues = new ArrayList<>();
        ArrayList<ArrayList<Comparable>> maxValues = new ArrayList<>();
        for (int i = 0; i < this.data.size(); i++) {
            groupValues.add(this.data.get(i).getGroupValues());
        }
        for (int i = 0; i < data.size(); i++) {
            maxValues.add(this.data.get(i).max(names));
        }

        for (int i = 0; i < groupNames.size(); i++) {
            String name = groupNames.get(i);
            ArrayList<Comparable> colValues = new ArrayList<>();
            for (int j = 0; j < data.size(); j++) {
                colValues.add(groupValues.get(j).get(i));
            }
            cols.add(new Column(name, colValues));
        }

        for (int i = 0; i < names.size(); i++) {
            String name = "max(" + names.get(i) + ")";
            ArrayList<Comparable> colValues = new ArrayList<>();
            for (int j = 0; j < data.size(); j++) {
                colValues.add(maxValues.get(j).get(i));
            }
            cols.add(new Column(name, colValues));
        }

        DataFrame d = new DataFrame(cols);
        return d;
    }

    public DataFrame max(String[] names) {
        return max(new ArrayList(Arrays.asList(names)));
    }

    public String maxPrint(List<String> names) {
        return max(names).toString();
    }

    public String maxPrint(String[] names) {
        return maxPrint(new ArrayList(Arrays.asList(names)));
    }

    public DataFrame sum(List<String> names) {
        ArrayList<Column> cols = new ArrayList<>();
        ArrayList<ArrayList<Comparable>> groupValues = new ArrayList<>();
        ArrayList<ArrayList<Double>> sumValues = new ArrayList<>();
        for (int i = 0; i < this.data.size(); i++) {
            groupValues.add(this.data.get(i).getGroupValues());
        }
        for (int i = 0; i < data.size(); i++) {
            sumValues.add(this.data.get(i).sum(names));
        }

        for (int i = 0; i < groupNames.size(); i++) {
            String name = groupNames.get(i);
            ArrayList<Comparable> colValues = new ArrayList<>();
            for (int j = 0; j < data.size(); j++) {
                colValues.add(groupValues.get(j).get(i));
            }
            cols.add(new Column(name, colValues));
        }

        for (int i = 0; i < names.size(); i++) {
            String name = "sum(" + names.get(i) + ")";
            ArrayList<Comparable> colValues = new ArrayList<>();
            for (int j = 0; j < data.size(); j++) {
                if (groupNames.contains(names.get(i))) {
                    Comparable val = checkValues(names.get(i), j);
                    if (val instanceof Number) {
                        colValues.add(val);
                    } else {
                        colValues.add(Double.NaN);
                    }
                } else {
                    colValues.add(sumValues.get(j).get(i));
                }
            }
            cols.add(new Column(name, colValues));
        }

        DataFrame d = new DataFrame(cols);
        return d;

    }

    public DataFrame sum(String[] names) {
        return sum(new ArrayList(Arrays.asList(names)));
    }

    public String sumPrint(List<String> names) {
        return sum(names).toString();
    }

    public String sumPrint(String[] names) {
        return sumPrint(new ArrayList(Arrays.asList(names))).toString();
    }

    private Comparable checkValues(String name, int index) {
        for (int i = 0; i < groupNames.size(); i++) {
            if (name.equals(groupNames.get(i))) {
                return data.get(index).getGroupValues().get(i);
            }
        }
        return null;
    }

    public DataFrame mean(List<String> names) {
        ArrayList<Column> cols = new ArrayList<>();
        ArrayList<ArrayList<Comparable>> groupValues = new ArrayList<>();
        ArrayList<ArrayList<Double>> meanValues = new ArrayList<>();
        for (int i = 0; i < this.data.size(); i++) {
            groupValues.add(this.data.get(i).getGroupValues());
        }
        for (int i = 0; i < data.size(); i++) {
            meanValues.add(this.data.get(i).mean(names));
        }

        for (int i = 0; i < groupNames.size(); i++) {
            String name = groupNames.get(i);
            ArrayList<Comparable> colValues = new ArrayList<>();
            for (int j = 0; j < data.size(); j++) {
                colValues.add(groupValues.get(j).get(i));
            }
            cols.add(new Column(name, colValues));
        }

        for (int i = 0; i < names.size(); i++) {
            String name = "mean(" + names.get(i) + ")";

            ArrayList<Comparable> colValues = new ArrayList<>();
            for (int j = 0; j < data.size(); j++) {
                if (groupNames.contains(names.get(i))) {
                    Comparable val = checkValues(names.get(i), j);
                    if (val instanceof Number) {
                        colValues.add(val);
                    } else {
                        colValues.add(Double.NaN);
                    }
                } else {
                    colValues.add(meanValues.get(j).get(i));
                }
            }
            cols.add(new Column(name, colValues));
        }

        DataFrame d = new DataFrame(cols);
        return d;
    }

    public DataFrame mean(String[] names) {
        return mean(new ArrayList(Arrays.asList(names)));
    }

    public String meanPrint(List<String> names) {
        return mean(names).toString();
    }

    public String meanPrint(String[] names) {
        return meanPrint(new ArrayList(Arrays.asList(names)));
    }

    @Override
    public String toString() {
        String retour = "";
        for (int i = 0; i < data.size(); i++) {
            retour += data.get(i).toString();
        }
        return retour;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final GroupBy other = (GroupBy) obj;
        if (!Objects.equals(this.groupNames, other.groupNames)) {
            return false;
        }
        if (!Objects.equals(this.data, other.data)) {
            return false;
        }
        return true;
    }

}
