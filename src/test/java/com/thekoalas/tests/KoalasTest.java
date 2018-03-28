/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thekoalas.tests;

import com.thekoalas.koalas.Koalas;
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

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
