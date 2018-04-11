package com.thekoalas.tests;

import com.thekoalas.koalas.Column;
import com.thekoalas.koalas.DataFrame;
import com.thekoalas.koalas.GroupBy;
import com.thekoalas.koalas.UnknownNameException;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class GroupByTest {
    
    DataFrame defaultDataFrame;
    
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
    
    @Test(expected = UnknownNameException.class)
    public void testGroupByUnknownName() {
        ArrayList<String> name = new ArrayList<>();
        name.add("This will fail");
        defaultDataFrame.groupBy(name);
    }

    @Test
    public void testGroupByOneColumnOneGroup() {

        ArrayList<String> names = new ArrayList<>();
        names.add("A");
        names.add("B");
        names.add("C");

        ArrayList<Integer> col1 = new ArrayList<>();
        col1.add(1);
        col1.add(2);
        col1.add(3);
        ArrayList<Integer> col2 = new ArrayList<>();
        col2.add(2);
        col2.add(2);
        col2.add(2);
        ArrayList<String> col3 = new ArrayList<>();
        col3.add("Un");
        col3.add("Deux");
        col3.add("Trois");
        List<List<? extends Comparable<?>>> l2 = new ArrayList<>();
        l2.add(col1);
        l2.add(col2);
        l2.add(col3);

        DataFrame data = new DataFrame(names, l2);

        ArrayList<String> toGroup = new ArrayList<>();
        toGroup.add("B");
        GroupBy g = data.groupBy(toGroup);

        String expected = "Grouped by [B]\n"
                + "A	 B	 C	 \n"
                + "1	2	Un	\n"
                + "2	2	Deux	\n"
                + "3	2	Trois	";

        assertEquals(expected.replaceAll("\\s+", ""), g.toString().replaceAll("\\s+", ""));
    }

    @Test
    public void testGroupByOneColumnTwoGroups() {

        ArrayList<String> names = new ArrayList<>();
        names.add("A");
        names.add("B");
        names.add("C");

        ArrayList<Integer> col1 = new ArrayList<>();
        col1.add(1);
        col1.add(2);
        col1.add(3);
        ArrayList<Integer> col2 = new ArrayList<>();
        col2.add(2);
        col2.add(2);
        col2.add(3);
        ArrayList<String> col3 = new ArrayList<>();
        col3.add("Un");
        col3.add("Deux");
        col3.add("Trois");
        List<List<? extends Comparable<?>>> l2 = new ArrayList<>();
        l2.add(col1);
        l2.add(col2);
        l2.add(col3);

        DataFrame data = new DataFrame(names, l2);

        ArrayList<String> toGroup = new ArrayList<>();
        toGroup.add("B");
        GroupBy g = data.groupBy(toGroup);

        String expected = "Grouped by [B]\n"
                + "A	 B	 C	 \n"
                + "1	2	Un	\n"
                + "2	2	Deux	\n"
                + "Grouped by [B]\n"
                + "A	 B	 C	 \n"
                + "3	3	Trois	";

        assertEquals(expected.replaceAll("\\s+", ""), g.toString().replaceAll("\\s+", ""));
    }

    @Test
    public void testGroupBySeveralColumnsSeveralGroups() {
        ArrayList<String> names = new ArrayList<>();
        names.add("A");
        names.add("B");
        names.add("C");

        ArrayList<Integer> col1 = new ArrayList<>();
        col1.add(1);
        col1.add(1);
        col1.add(3);
        col1.add(4);
        ArrayList<Integer> col2 = new ArrayList<>();
        col2.add(2);
        col2.add(2);
        col2.add(3);
        col2.add(4);
        ArrayList<String> col3 = new ArrayList<>();
        col3.add("Un");
        col3.add("Deux");
        col3.add("Trois");
        col3.add("Quatre");
        List<List<? extends Comparable<?>>> l2 = new ArrayList<>();
        l2.add(col1);
        l2.add(col2);
        l2.add(col3);

        DataFrame data = new DataFrame(names, l2);

        ArrayList<String> toGroup = new ArrayList<>();
        toGroup.add("A");
        toGroup.add("B");
        GroupBy g = data.groupBy(toGroup);

        String expected = "Grouped by [A, B]\n"
                + "A	 B	 C	 \n"
                + "3	3	Trois	\n"
                + "Grouped by [A, B]\n"
                + "A	 B	 C	 \n"
                + "4	4	Quatre	\n"
                + "Grouped by [A, B]\n"
                + "A	 B	 C	 \n"
                + "1	2	Un	\n"
                + "1	2	Deux	";

        assertEquals(expected.replaceAll("\\s+", ""), g.toString().replaceAll("\\s+", ""));
    }
    
    @Test
    public void testGroupByCompareToOtherMethod() {
        
        
        ArrayList<String> names = new ArrayList<>();
        names.add("A");
        names.add("B");
        names.add("C");

        ArrayList<Integer> col1 = new ArrayList<>();
        col1.add(1);
        col1.add(1);
        col1.add(3);
        col1.add(4);
        ArrayList<Integer> col2 = new ArrayList<>();
        col2.add(2);
        col2.add(2);
        col2.add(3);
        col2.add(4);
        ArrayList<String> col3 = new ArrayList<>();
        col3.add("Un");
        col3.add("Deux");
        col3.add("Trois");
        col3.add("Quatre");
        List<List<? extends Comparable<?>>> l2 = new ArrayList<>();
        l2.add(col1);
        l2.add(col2);
        l2.add(col3);

        DataFrame data = new DataFrame(names, l2);
        
        
        ArrayList<String> name = new ArrayList<>();
        name.add("A");
        name.add("B");
        
        String[] nameTab = {"A","B"};
        
        assertEquals(data.groupBy(name),data.groupBy(nameTab));
    
    }
    
    @Test
    public void minPrintTest() {
        
        String[] cols = {"A"};
        GroupBy gb = defaultDataFrame.groupBy(cols);
        
        String[] subCols = {"B"};
        String min = gb.minPrint(subCols);
        
        String expected = "Amin(B)1625";
        assertEquals(expected.replaceAll("\\s+", ""), min.replaceAll("\\s+", ""));
    }
    
    @Test
    public void maxPrintTest() {
        
        String[] cols = {"A"};
        GroupBy gb = defaultDataFrame.groupBy(cols);
        
        String[] subCols = {"B"};
        String max = gb.maxPrint(subCols);
        
        String expected = "Amax(B)1825";
        assertEquals(expected.replaceAll("\\s+", ""), max.replaceAll("\\s+", ""));
    }
    
    @Test
    public void meanPrintTest() {
        
        String[] cols = {"A"};
        GroupBy gb = defaultDataFrame.groupBy(cols);
        
        String[] subCols = {"B"};
        String mean = gb.meanPrint(subCols);
        
        String expected = "Amean(B)17.025.0";
        assertEquals(expected.replaceAll("\\s+", ""), mean.replaceAll("\\s+", ""));
    }
    
    @Test
    public void sumPrintTest() {
        
        String[] cols = {"A"};
        GroupBy gb = defaultDataFrame.groupBy(cols);
        
        String[] subCols = {"B"};
        String sum = gb.sumPrint(subCols);
        
        String expected = "Asum(B)114.025.0";
        assertEquals(expected.replaceAll("\\s+", ""), sum.replaceAll("\\s+", ""));
    }
    
    @Test
    public void testEquals() {
        
        String[] cols = {"A"};
        GroupBy gb = defaultDataFrame.groupBy(cols);
        assertTrue(gb.equals(gb));
        assertFalse(gb.equals(null));

        assertFalse(gb.equals("Koalas!!!!!!!!"));

        GroupBy gb2 = defaultDataFrame.groupBy(cols);

        assertTrue(gb.equals(gb2));
        
        ArrayList<Column> list = new ArrayList<Column>();
        ArrayList<Integer> col1IntList = new ArrayList<Integer>();

        col1IntList.add(2);
        col1IntList.add(1);
        col1IntList.add(1);

        ArrayList<Integer> col2IntList = new ArrayList<Integer>();
        col2IntList.add(5);
        col2IntList.add(6);
        col2IntList.add(9);
        list.add(new Column("A", col1IntList));
        list.add(new Column("B", col2IntList));

        DataFrame defaultDataFrame2 = new DataFrame(list);
        GroupBy gb3 = defaultDataFrame2.groupBy(cols);

        assertFalse(gb.equals(gb3));
        
        String[] cols2 = {"B"};
        GroupBy gb4 = defaultDataFrame.groupBy(cols2);
        assertFalse(gb.equals(gb4));
    }
}
