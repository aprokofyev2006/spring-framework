import org.junit.jupiter.api.*;

import java.nio.file.AccessDeniedException;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @BeforeAll
    static void setUpAll(){
        System.out.println("BeforeAll is executed");
    }

    @AfterAll
    static void tearDownAll(){
        System.out.println("AfterAll is executed");
    }

    @BeforeEach
    void setUpEach(){
        System.out.println("BeforeEach is executed");
    }

    @AfterEach
    void tearDownEach(){
        System.out.println("AfterEach is executed");
    }

    @Test
    void testCase1(){
        fail("Not implemented yet");
    }

    @Test
    void testCase2(){
        //assertTrue(Calculator.operator.equals("add"));
        System.out.println("test case 2");
        assertEquals("add", Calculator.operator);
    }

    @Test
    void testCase3(){
        assertArrayEquals(new int[]{1,2,3},new int[]{1,2,3});
    }

    @Test
    void testCase4(){
        assertArrayEquals(new int[]{1,2,3},new int[]{1,2,4});
    }

    @Test
    void testCase5(){
        String nullString = null;
        String notNullString = "Cydeo";
        assertNull(nullString);
        assertNotNull(notNullString);
        //assertNotNull(nullString);
    }

    @Test
    void testCase6(){
        Calculator c1 = new Calculator();
        Calculator c2 = c1;

        Calculator c3 = new Calculator();

        assertSame(c1, c2);

        assertNotSame(c1, c3);

    }

    @Test
    @Tag("calc")
    void add() {
        int actual = Calculator.add(2,3);
        Assertions.assertEquals(4,actual,"Actual value is not matching with expected");
    }

    @Test
    @Tag("calc")
    void add2(){
        assertThrows(IllegalArgumentException.class, () -> Calculator.add2(3,2));
    }
}