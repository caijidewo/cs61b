import org.junit.Test;
import static org.junit.Assert.*;

public class ArrayDequeTest {
    @Test
    public void testAddFirst(){
        ArrayDeque<Integer> input=new ArrayDeque<>();
        for(int i=0;i<5;i++)
        input.addFirst(i);
        int actual1,expected1,actual2,expected2;
        actual1=input.get(1);
        expected1=4;
        assertEquals(expected1,actual1);
        actual2=input.get(3);
        expected2=2;
        assertEquals(expected2,actual2);
    }
    @Test
    public void testLast(){
        ArrayDeque<Integer> input=new ArrayDeque<>();
        for(int i=0;i<5;i++)
            input.addLast(i);
        int actual1,expected1,actual2,expected2;
        actual1=input.get(1);
        expected1=0;
        assertEquals(expected1,actual1);
        actual2=input.get(5);
        expected2=4;
        assertEquals(expected2,actual2);
    }
    @Test
    public void testRemoveFirstAndLast(){
        ArrayDeque<Integer> input=new ArrayDeque<>();
        for(int i=0;i<101;i++)
            input.addLast(i);
        input.removeFirst();
        input.addFirst(111);
        input.removeLast();
        input.addLast(999);
        int actual1,expected1,actual2,expected2;
        actual1=input.get(1);
        expected1=111;
        assertEquals(expected1,actual1);
        actual2=input.get(101);
        expected2=999;
        assertEquals(expected2,actual2);
        //int actual3,expected3;
    }
    @Test
    public void testSize(){
        ArrayDeque<Integer> input=new ArrayDeque<>();
        for(int i=0;i<88;i++)
            input.addFirst(i);
        int actualsize1=input.size();
        int expectedsize1=88;
        assertEquals(expectedsize1,actualsize1);
        int actualsize2,expectedsize2;
        input.removeLast();
        actualsize2=input.size();
        expectedsize2=actualsize1-1;
        assertEquals(expectedsize2,actualsize2);
        int actuallsize3,expectedsize3;
        for(int i=0;i<4;i++){
            input.removeFirst();
        }
        actuallsize3=input.size();
        expectedsize3=actualsize2-4;
        assertEquals(expectedsize3,actuallsize3);
    }
    @Test
    public void testIsEmptyandIsFull(){
        ArrayDeque<Integer> input=new ArrayDeque<>();
        boolean actual1,expected1;
        expected1=true;
        actual1=input.isEmpty();
        assertEquals(expected1,actual1);
        for(int i=0;i<6;i++)
            input.addFirst(i);
        boolean actual2,expected2;
        actual2=input.isFull();
        expected2=true;
        assertEquals(expected2,actual2);
    }
    @Test
    public void testExeicise(){
        ArrayDeque<Integer> input3=new ArrayDeque<>();
        ArrayDeque<Integer> input4=input3;
        assertEquals(input3,input4);
    }
    @Test
    public void testGetRecursive(){
        LinkedListDeque<Integer> lld1 = new LinkedListDeque<>();
        for(int i=0;i<10;i++)
        lld1.addLast(i);
        Integer actual=lld1.getRecursive(0);
        Integer expected=0;
        assertEquals(expected,actual);
    }
}
