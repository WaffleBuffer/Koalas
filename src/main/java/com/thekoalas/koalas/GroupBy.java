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

    public GroupBy(List<String> groupNames,List<GroupedData> data) {
        this.groupNames = groupNames;
        this.data = data;
    }

    private String colHeaders(int function, List<String> names) {
        String retour = "";
        String[] func = {"min(", "max(", "sum(", "mean("};
        for (int i = 0; i < groupNames.size(); i++) {
            retour += groupNames.get(i) + "\t";
        }

        for (int i = 0; i < names.size(); i++) {
            retour += func[function] + names.get(i) + ")\t";
        }
        retour += "\n";
        return retour;
    }

    public String min(List<String> names) {
        String retour = colHeaders(0, names);
        for (int i = 0; i < data.size(); i++) {
            ArrayList<Comparable> list = data.get(i).min(names);
            retour += data.get(i).getGroupValues();
            for(int j = 0; j < list.size();j++){
                retour += list.get(j) + "\t";
            }
            retour += "\n";
        }
        return retour;
    }
    
    public String min(String[] names){
        return min(new ArrayList(Arrays.asList(names)));
    }
    

    public String max(List<String> names) {
        String retour = colHeaders(1, names);
        for (int i = 0; i < data.size(); i++) {
            ArrayList<Comparable> list = data.get(i).max(names);
            retour += data.get(i).getGroupValues();
            for(int j = 0; j < list.size();j++){
                retour += list.get(j) + "\t";
            }
            retour += "\n";
        }
        return retour;
    }
    
    public String max(String[] names){
        return max(new ArrayList(Arrays.asList(names)));
    }

    public String sum(List<String> names) {
        String retour = colHeaders(2, names);
        for (int i = 0; i < data.size(); i++) {
            ArrayList<Double> list = data.get(i).sum(names);
            retour += data.get(i).getGroupValues();
            for(int j = 0; j < list.size();j++){
                retour += list.get(j) + "\t";
            }
            retour += "\n";
        }
        return retour;
    }
    
    public String sum(String[] names){
        return sum(new ArrayList(Arrays.asList(names)));
    }

    public String mean(List<String> names) {
        String retour = colHeaders(3, names);
        for (int i = 0; i < data.size(); i++) {
            ArrayList<Double> list = data.get(i).mean(names);
            retour += data.get(i).getGroupValues();
            for(int j = 0; j < list.size();j++){
                retour += list.get(j) + "\t";
            }
            retour += "\n";
        }
        return retour;
    }
    
    public String mean(String[] names){
        return mean(new ArrayList(Arrays.asList(names)));
    }
    
    @Override
    public String toString(){
        String retour = "";
        for(int i = 0; i < data.size();i++){
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
