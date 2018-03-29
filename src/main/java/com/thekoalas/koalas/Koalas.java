package com.thekoalas.koalas;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author medardt
 */
public abstract class Koalas {

    public static void main(String[] args) {
        //Tests Naiqi/Benjamin, pour s'assurer que tout fonctionne correctement !
        //Creer un dataset a partir de colonnes.
        ArrayList<Column> list = new ArrayList<>();
        list.add(new Column("A", new ArrayList<Integer>()));
        DataFrame data1 = new DataFrame(list);
        //Creer un dataset a partir de noms et de donnees.
        ArrayList<String> names = new ArrayList<>();
        names.add("A");
        names.add("B");
        names.add("C");
        ArrayList<Integer> col1 = new ArrayList<>();
        ArrayList<Integer> col2 = new ArrayList<>();
        ArrayList<String> col3 = new ArrayList<>();
        List<List<? extends Comparable>> l2 = new ArrayList<>();
        l2.add(col1);
        l2.add(col2);
        l2.add(col3);
        DataFrame data = new DataFrame(names, l2);
        System.out.println("Hello koalas !");
    }

    public static String hello() {
        return "Hello :D";
    }

}

