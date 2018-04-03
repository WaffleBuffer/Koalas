/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thekoalas.tests;

import com.thekoalas.koalas.Column;
import com.thekoalas.koalas.ColumnsNotSameSizeException;
import com.thekoalas.koalas.DataFrame;
import com.thekoalas.koalas.Koalas;
import com.thekoalas.koalas.NameAlreadyDefinedException;
import com.thekoalas.koalas.NotAsMuchNamesAsColumnsException;
import java.util.ArrayList;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author medardt
 */
public class KoalasTest {

    public KoalasTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testHello() {
        String test = Koalas.hello();

        assertEquals("Hello :D", test);
    }

    @Test
    public void testCreationFromColumns() {
        ArrayList<Column> list = new ArrayList<>();
        ArrayList<Integer> col1IntList = new ArrayList<>();
        col1IntList.add(1);
        col1IntList.add(2);
        ArrayList<String> col2StringList = new ArrayList<>();
        col2StringList.add("Row1");
        col2StringList.add("Row2");
        list.add(new Column("A", col1IntList));
        list.add(new Column("B", col2StringList));
        DataFrame data = new DataFrame(list);
        String expected = "A B 1 Row1 2 Row2";
        assertEquals(expected.replaceAll("\\s+", ""), data.display().replaceAll("\\s+", ""));
    }

    @Test
    public void testCreationFromValues() {
        //Créer un dataset à partir de noms et de données.
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
        col3.add("Row1");
        col3.add("Row2");
        col3.add("Row3");

        List<List<? extends Comparable>> l2 = new ArrayList<>();
        l2.add(col1);
        l2.add(col2);
        l2.add(col3);

        DataFrame data = new DataFrame(names, l2);
        String expected = "A B C 1 2 Row1 2 2 Row2 3 2 Row3";
        assertEquals(expected.replaceAll("\\s+", ""), data.display().replaceAll("\\s+", ""));
    }

    @Test(expected = ColumnsNotSameSizeException.class)
    public void testExceptionColumnsNotSameSize() {
        //Créer un dataset à partir de noms et de données.
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
        col3.add("Row1");
        col3.add("Row2");

        List<List<? extends Comparable>> l2 = new ArrayList<>();
        l2.add(col1);
        l2.add(col2);
        l2.add(col3);

        DataFrame data = new DataFrame(names, l2);

    }

    @Test(expected = NotAsMuchNamesAsColumnsException.class)
    public void testNotAsMuchNamesAsColumns() {
        //Créer un dataset à partir de noms et de données.
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
        col3.add("Row1");
        col3.add("Row2");
        col3.add("Row3");

        List<List<? extends Comparable>> l2 = new ArrayList<>();
        l2.add(col1);
        l2.add(col2);

        DataFrame data = new DataFrame(names, l2);

    }

    @Test(expected = NameAlreadyDefinedException.class)
    public void testNameAlreadyDefined() {
        //Créer un dataset à partir de noms et de données.
        ArrayList<String> names = new ArrayList<>();
        names.add("A");
        names.add("A");
        names.add("B");

        ArrayList<Integer> col1 = new ArrayList<>();
        col1.add(1);
        col1.add(2);
        col1.add(3);

        ArrayList<Integer> col2 = new ArrayList<>();
        col2.add(2);
        col2.add(2);
        col2.add(2);
        ArrayList<String> col3 = new ArrayList<>();
        col3.add("Row1");
        col3.add("Row2");
        col3.add("Row3");

        List<List<? extends Comparable>> l2 = new ArrayList<>();
        l2.add(col1);
        l2.add(col2);
        l2.add(col3);

        DataFrame data = new DataFrame(names, l2);

    }

    @Test
    public void testHeadNoArgs() {
        List<Column> actualCol = new ArrayList<>();
        List<Integer> col1 = new ArrayList<>();
        col1.add(1);
        col1.add(2);
        col1.add(3);
        col1.add(4);
        col1.add(5);
        col1.add(6);
        col1.add(7);
        col1.add(8);
        col1.add(9);
        actualCol.add(new Column("A", col1));
        DataFrame data = new DataFrame(actualCol);

        String expected = "A 1 2 3 4 5";

        assertEquals(expected.replaceAll("\\s+", ""), data.head().replaceAll("\\s+", ""));

    }

    @Test
    public void testHead2() {
        List<Column> actualCol = new ArrayList<>();
        List<Integer> col1 = new ArrayList<>();
        col1.add(1);
        col1.add(2);
        col1.add(3);
        col1.add(4);
        col1.add(5);
        col1.add(6);
        col1.add(7);
        col1.add(8);
        col1.add(9);
        actualCol.add(new Column("A", col1));
        DataFrame data = new DataFrame(actualCol);

        String expected = "A 1 2";

        assertEquals(expected.replaceAll("\\s+", ""), data.head(2).replaceAll("\\s+", ""));

    }

    @Test
    public void testHeadMoreThanNumberOfValues() {
        List<Column> actualCol = new ArrayList<>();
        List<Integer> col1 = new ArrayList<>();
        col1.add(1);
        col1.add(2);
        col1.add(3);
        col1.add(4);
        col1.add(5);
        col1.add(6);
        col1.add(7);
        col1.add(8);
        col1.add(9);
        actualCol.add(new Column("A", col1));
        DataFrame data = new DataFrame(actualCol);

        assertEquals(data.display().replaceAll("\\s+", ""), data.head(100).replaceAll("\\s+", ""));

    }

    @Test
    public void testTailNoArgs() {
        List<Column> actualCol = new ArrayList<>();
        List<Integer> col1 = new ArrayList<>();
        col1.add(1);
        col1.add(2);
        col1.add(3);
        col1.add(4);
        col1.add(5);
        col1.add(6);
        col1.add(7);
        col1.add(8);
        col1.add(9);
        actualCol.add(new Column("A", col1));
        DataFrame data = new DataFrame(actualCol);

        String expected = "A 5 6 7 8 9";

        assertEquals(expected.replaceAll("\\s+", ""), data.tail().replaceAll("\\s+", ""));

    }

    @Test
    public void testTail2() {
        List<Column> actualCol = new ArrayList<>();
        List<Integer> col1 = new ArrayList<>();
        col1.add(1);
        col1.add(2);
        col1.add(3);
        col1.add(4);
        col1.add(5);
        col1.add(6);
        col1.add(7);
        col1.add(8);
        col1.add(9);
        actualCol.add(new Column("A", col1));
        DataFrame data = new DataFrame(actualCol);

        String expected = "A 8 9";

        assertEquals(expected.replaceAll("\\s+", ""), data.tail(2).replaceAll("\\s+", ""));

    }

    @Test
    public void testTailMoreThanNumberOfValues() {
        List<Column> actualCol = new ArrayList<>();
        List<Integer> col1 = new ArrayList<>();
        col1.add(1);
        col1.add(2);
        col1.add(3);
        col1.add(4);
        col1.add(5);
        col1.add(6);
        col1.add(7);
        col1.add(8);
        col1.add(9);
        actualCol.add(new Column("A", col1));
        DataFrame data = new DataFrame(actualCol);

        assertEquals(data.display().replaceAll("\\s+", ""), data.tail(100).replaceAll("\\s+", ""));

    }

    @Test
    public void testGetLineSubset() {
        List<Column> actualCol = new ArrayList<>();
        List<Integer> col1 = new ArrayList<>();
        col1.add(1);
        col1.add(2);
        col1.add(3);
        col1.add(4);
        col1.add(5);
        col1.add(6);
        col1.add(7);
        col1.add(8);
        col1.add(9);
        actualCol.add(new Column("A", col1));
        DataFrame data = new DataFrame(actualCol);

        List<Column> expectedCol = new ArrayList<>();
        List<Integer> colE1 = new ArrayList<>();
        colE1.add(6);
        colE1.add(7);
        colE1.add(8);
        colE1.add(9);
        expectedCol.add(new Column("A", colE1));
        DataFrame expectedData = new DataFrame(expectedCol);

        assertEquals(expectedData, data.getLineSubset(5));

    }
    
    @Test
    public void testGetLineSubsetBiggerThanPossible() {
        List<Column> actualCol = new ArrayList<>();
        List<Integer> col1 = new ArrayList<>();
        col1.add(1);
        col1.add(2);
        col1.add(3);
        col1.add(4);
        col1.add(5);
        col1.add(6);
        col1.add(7);
        col1.add(8);
        col1.add(9);
        actualCol.add(new Column("A", col1));
        DataFrame data = new DataFrame(actualCol);

        assertEquals(data, data.getLineSubset(100));

    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
