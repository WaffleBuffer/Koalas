package com.thekoalas.tests;

import com.thekoalas.koalas.Column;
import com.thekoalas.koalas.DataFrame;
import com.thekoalas.koalas.GroupedData;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class GroupedDataTest {
    
    private GroupedData groupedData;
    
    public GroupedDataTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
        String groupId = "test";
        
        List<String> groupNames = new ArrayList<String>();
        groupNames.add("A");
        groupNames.add("B");
        
        ArrayList<Column> list = new ArrayList<Column>();
        ArrayList<Integer> col1IntList = new ArrayList<Integer>();

        col1IntList.add(2);
        col1IntList.add(1);
        col1IntList.add(1);

        ArrayList<Integer> col2IntList = new ArrayList<Integer>();
        col2IntList.add(5);
        col2IntList.add(6);
        col2IntList.add(8);
        list.add(new Column("A", col1IntList));
        list.add(new Column("B", col2IntList));
        
        ArrayList<Comparable> groupedDataList = new ArrayList<Comparable>(col1IntList);
        
        groupedData = new GroupedData(groupId, groupNames, list, groupedDataList);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testEqual() {
        
        assertFalse(groupedData.equals(null));
        assertFalse(groupedData.equals("Koala!!!!"));
        
        String groupId = "Koala";
        
        List<String> groupNames = new ArrayList<String>();
        groupNames.add("A");
        groupNames.add("B");
        
        ArrayList<Column> list = new ArrayList<Column>();
        ArrayList<Integer> col1IntList = new ArrayList<Integer>();

        col1IntList.add(2);
        col1IntList.add(1);
        col1IntList.add(1);

        ArrayList<Integer> col2IntList = new ArrayList<Integer>();
        col2IntList.add(5);
        col2IntList.add(6);
        col2IntList.add(8);
        list.add(new Column("A", col1IntList));
        list.add(new Column("B", col2IntList));
        
        ArrayList<Comparable> groupedDataList = new ArrayList<Comparable>(col1IntList);
        
        GroupedData comp1 = new GroupedData(groupId, groupNames, list, groupedDataList);
        
        assertFalse(groupedData.equals(comp1));
        
        groupId = "test";
        
        groupNames = new ArrayList<String>();
        groupNames.add("B");
        groupNames.add("A");
        
        GroupedData comp2 = new GroupedData(groupId, groupNames, list, groupedDataList);
        
        assertFalse(groupedData.equals(comp2));
        
        groupId = "test";
        
        groupNames = new ArrayList<String>();
        groupNames.add("A");
        groupNames.add("B");
        
        col1IntList = new ArrayList<Integer>();

        col1IntList.add(1);
        col1IntList.add(1);
        col1IntList.add(1);
        
        groupedDataList = new ArrayList<Comparable>(col1IntList);
        
        GroupedData comp3 = new GroupedData(groupId, groupNames, list, groupedDataList);
        
        assertFalse(groupedData.equals(comp3));
        
        list = new ArrayList<Column>();
        col1IntList = new ArrayList<Integer>();

        col1IntList.add(2);
        col1IntList.add(1);
        col1IntList.add(1);
        
        list.add(new Column("A", col1IntList));
        list.add(new Column("B", col2IntList));
        
        groupedDataList = new ArrayList<Comparable>(col1IntList);
        
        GroupedData comp4 = new GroupedData(groupId, groupNames, list, groupedDataList);
        
        assertTrue(groupedData.equals(comp4));
    }
}
