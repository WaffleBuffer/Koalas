package com.thekoalas.tests;

import com.thekoalas.koalas.NoColumnsException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class NoColumnsExceptionTest {
    
    public NoColumnsExceptionTest() {
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
    public void testAllConstructors() {
     
        NoColumnsException ex = new NoColumnsException();
        NoColumnsException ex1 = new NoColumnsException("test");
        NoColumnsException ex2 = new NoColumnsException("test", null);
        NoColumnsException ex3 = new NoColumnsException((Throwable)null);
        NoColumnsException ex4 = new NoColumnsException("test", (Throwable)null, true, true);
    }
}
