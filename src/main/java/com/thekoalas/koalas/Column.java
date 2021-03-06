package com.thekoalas.koalas;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Column<T extends Comparable<T>> {

    private String name;
    private List data;

    public Column(String name, List data) {
        this.name = name;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
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
        final Column<?> other = (Column<?>) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return Objects.equals(this.data, other.data);
    }
    
    public Comparable min() {
        if (!data.isEmpty()) {
            Comparable min = (Comparable) data.get(0);
            for (int j = 1; j < data.size(); j++) {
                Comparable current = (Comparable) data.get(j);
                if (current.compareTo(min) < 0) {
                    min = current;
                }
            }
            return min;
        }
        return null;
    }

    public Comparable max() {
        if (!data.isEmpty()) {
            Comparable max = (Comparable) data.get(0);
            for (int j = 1; j < data.size(); j++) {
                Comparable current = (Comparable) data.get(j);
                if (current.compareTo(max) > 0) {
                    max = current;
                }
            }
            return max;
        }
        return null;
    }
    
     public Double sum() {
        
        if(data.isEmpty() || !(data.get(0) instanceof Number)){
            return Double.NaN;
        }
        double sum = 0;

        ArrayList<Number> list = (ArrayList<Number>) data;
        for (int i = 0; i < list.size(); i++) {
            sum += list.get(i).doubleValue();
        }
        return sum;
    }

    public Double mean() {
        if(data.isEmpty() || !(data.get(0) instanceof Number)){
            return Double.NaN;
        }
        return sum()/data.size();
    }
}
