/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thekoalas.koalas;

import java.util.ArrayList;
import java.util.List;

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
    
    @Override
    public String toString(){
        String retour = "";
        for(int i = 0; i < data.size();i++){
            retour += data.get(i).toString();
        }
        return retour;
    }
}
