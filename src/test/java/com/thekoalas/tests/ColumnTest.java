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

public class ColumnTest {
    
    DataFrame defaultDataFrame;
    
    public ColumnTest() {
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
        col1IntList.add(1);

        ArrayList<Integer> col2IntList = new ArrayList<Integer>();
        col2IntList.add(5);
        col2IntList.add(6);
        col2IntList.add(8);
        list.add(new Column("A", col1IntList));
        list.add(new Column("B", col2IntList));

        defaultDataFrame = new DataFrame(list);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testNull() {
        
        Column col = new Column("test", new ArrayList());
        
        assertNull(col.min());
        assertNull(col.max());
        assertEquals((Object)Double.NaN, (Object)col.sum());
        assertEquals((Object)Double.NaN, (Object)col.mean());
    }
    
    @Test
    public void testSetName() {
        
        Column col = new Column("test", new ArrayList());
        
        String expected = "Koala";
        col.setName(expected);
        assertEquals(expected, col.getName());
    }
    
    @Test
    public void testSetData() {
        
        ArrayList<Integer> col1IntList = new ArrayList<Integer>();

        col1IntList.add(2);
        col1IntList.add(1);
        col1IntList.add(1);

        ArrayList<Integer> col2IntList = new ArrayList<Integer>();
        col2IntList.add(5);
        col2IntList.add(6);
        col2IntList.add(8);
        
        Column col = new Column("test", col1IntList);
        col.setData(col2IntList);
        
        assertEquals(col.getData(), col2IntList);
    }
    
    @Test
    public void testEquals() {
        
        ArrayList<Integer> col1IntList = new ArrayList<Integer>();

        col1IntList.add(2);
        col1IntList.add(1);
        col1IntList.add(1);

        ArrayList<Integer> col2IntList = new ArrayList<Integer>();
        col2IntList.add(5);
        col2IntList.add(6);
        col2IntList.add(9);
        Column col = new Column("test", col1IntList);
        
        assertTrue(col.equals(col));
        assertFalse(col.equals(null));

        assertFalse(col.equals("Koalas!!!!!!!!"));

        Column col2 = new Column("test", col1IntList);

        assertTrue(col.equals(col2));

        Column col3 = new Column("Koalas!!!!!!!!", col1IntList);

        assertFalse(col.equals(col3));
    }
}
