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
 * @author zhangna
 */
public class DataFrame implements IDataFrame {

    private List<Column> dataset;
    
    public DataFrame(List<Column> columns){
        this.dataset = columns;
    }
    
    public DataFrame(List<String> names, List<List<? extends Comparable>> columns){
        
        boolean allEqual = true;
        
        if(names.size() != columns.size()){
            System.out.println("The number of names is not equal to the number of values column. Aborting");
            return;
        }
        
        if(columns.isEmpty()){
            System.out.println("Trying to create an empty dataset. Aborting");
            return;
        }
        
        int sizeExpected = columns.get(0).size();
  
        for(int i = 0 ; i < columns.size() && allEqual;i++){
            allEqual = (columns.get(i).size() == sizeExpected);
        }
        
        if(!allEqual){
            System.out.println("The number of data is not the same in all columns. Aborting");
            return;
        }
        this.dataset = new ArrayList<>();
        for(int i = 0; i < names.size(); i++){
            Column cols = new Column(names.get(i), columns.get(i));
            this.dataset.add(cols);
        }
    }
    
    public DataFrame(String filename){
        System.out.println("TODO : parse a CSV file");
        
    }

    @Override
    public String getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String head(int nbLines) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String head() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String tail(int nbLines) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String tail() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getLineSubset(int startIndex) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getRowSubset(List<String> columnNames) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  

}
