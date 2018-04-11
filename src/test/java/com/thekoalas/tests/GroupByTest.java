package com.thekoalas.tests;

import com.thekoalas.koalas.Column;
import com.thekoalas.koalas.DataFrame;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class GroupByTest {
    
    public GroupByTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
        ArrayList<Column> list = new ArrayList<Column>();
        ArrayList<Integer> col1IntList = new ArrayList<Integer>();

        col1IntList.add(2);
        col1IntList.add(1);

        ArrayList<String> col2StringList = new ArrayList<String>();
        col2StringList.add("Row1");
        col2StringList.add("Row2");
        list.add(new Column("A", col1IntList));
        list.add(new Column("B", col2StringList));

        defaultDataFrame = new DataFrame(list);
    }
    
    @After
    public void tearDown() {
        
    }
}
