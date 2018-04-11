/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thekoalas.koalas;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author GONTARD Benjamin
 */
public class GroupedData {

    private String groupId;
    private List<String> groupNames;
    private ArrayList<Column> columns;
    private ArrayList<Comparable> groupValues;

    public GroupedData(String groupId, List<String> groupNames, ArrayList<Column> columns, ArrayList<Comparable> groupValues) {
        this.groupId = groupId;
        this.groupNames = groupNames;
        this.columns = columns;
        this.groupValues = groupValues;
    }

    private List<Integer> checkColName(List<String> names) {
        List<Integer> colIndices = new ArrayList<>();
        for (int j = 0; j < names.size(); j++) {
            int col = -1;
            for (int i = 0; i < columns.size() && col == -1; i++) {
                if (names.get(j).equals(columns.get(i).getName())) {
                    col = i;
                }
            }
            if (col == -1) {
                throw new UnknownNameException();
            }
            colIndices.add(col);
        }
        return colIndices;
    }

    public ArrayList<Comparable> min(List<String> names) {
        ArrayList<Comparable> retour = new ArrayList<>();
        List<Integer> colIndices = checkColName(names);
        System.out.println(colIndices);
        for (int i = 0; i < colIndices.size(); i++) {
            retour.add(columns.get(colIndices.get(i)).min());
        }
        return retour;
    }

    public ArrayList<Comparable> max(List<String> names) {
        ArrayList<Comparable> retour = new ArrayList<>();
        List<Integer> colIndices = checkColName(names);

        for (int i = 0; i < colIndices.size(); i++) {
            retour.add(columns.get(colIndices.get(i)).max());
        }
        return retour;
    }
    public Comparable checkValues(String name){
        for(int i=0; i < groupNames.size();i++){
            
        }
        return null;
    }

    public ArrayList<Double> sum(List<String> names) {

        ArrayList<Double> retour = new ArrayList<>();
        List<Integer> colIndices = checkColName(names);
        for (int i = 0; i < colIndices.size(); i++) {
            retour.add(columns.get(colIndices.get(i)).sum());
        }
        return retour;
    }

    public ArrayList<Double> mean(List<String> names) {

        ArrayList<Double> retour = new ArrayList<>();
        List<Integer> colIndices = checkColName(names);
        for (int i = 0; i < colIndices.size(); i++) {
            retour.add(columns.get(colIndices.get(i)).mean());
        }
        return retour;
    }

    @Override
    public String toString() {
        String retour = "Grouped by ";
        retour += groupNames + "\n";

        for (int i = 0; i < columns.size(); i++) {
            retour += columns.get(i).getName() + "\t ";
        }
        int size = columns.get(0).getData().size();
        retour += "\n";
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < columns.size(); j++) {
                retour += this.columns.get(j).getData().get(i) + "\t";
            }
            retour += "\n";
        }
        return retour;
    }
    
    public String getGroupValues(){
        String retour = " ";
        for(int i = 0 ; i < groupValues.size();i++){
            retour += groupValues.get(i) + "\t";
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
        final GroupedData other = (GroupedData) obj;
        if (!Objects.equals(this.groupId, other.groupId)) {
            return false;
        }
        if (!Objects.equals(this.groupNames, other.groupNames)) {
            return false;
        }
        if (!Objects.equals(this.columns, other.columns)) {
            return false;
        }
        if (!Objects.equals(this.groupValues, other.groupValues)) {
            return false;
        }
        return true;
    }

  

    
    
}
