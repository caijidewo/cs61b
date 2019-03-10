package hw3.hash;

import org.junit.Test;


import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

import static org.junit.Assert.*;


public class TestSimpleOomage {

    @Test
    public void testHashCodeDeterministic() {
        SimpleOomage so = SimpleOomage.randomSimpleOomage();
        int hashCode = so.hashCode();
        for (int i = 0; i < 100; i += 1) {
            assertEquals(hashCode, so.hashCode());
        }
    }

    @Test
    public void testHashCodePerfect() {
        /* TODO: Write a test that ensures the hashCode is perfect,
          meaning no two SimpleOomages should EVER have the same
          hashCode UNLESS they have the same red, blue, and green values!
         */
        SimpleOomage s1 = new SimpleOomage(5, 10, 15);
        SimpleOomage s2 = new SimpleOomage(5, 10, 15);
        SimpleOomage s3 = new SimpleOomage(5, 15, 10);
        SimpleOomage s4 = new SimpleOomage(10, 5, 15);
        SimpleOomage s5 = new SimpleOomage(10, 15, 5);
        SimpleOomage s6 = new SimpleOomage(15, 5, 10);
        SimpleOomage s7 = new SimpleOomage(5, 10, 15);
        SimpleOomage s8 = new SimpleOomage(15, 10, 5);
        SimpleOomage s9 = new SimpleOomage(20, 30, 35);
        SimpleOomage s10 = new SimpleOomage(255, 255, 255);
        assertEquals(s1.hashCode(), s2.hashCode());
        assertEquals(s2.hashCode(), s7.hashCode());
        assertNotEquals(s1.hashCode(), s3.hashCode());
        assertNotEquals(s1.hashCode(), s4.hashCode());
        assertNotEquals(s1.hashCode(), s5.hashCode());
        assertNotEquals(s1.hashCode(), s6.hashCode());
        assertNotEquals(s1.hashCode(), s8.hashCode());
        assertNotEquals(s1.hashCode(), s9.hashCode());
        assertEquals(true, s10.hashCode() < 0x7FFFFFFF);
    }

    @Test
    public void testEquals() {
        SimpleOomage ooA = new SimpleOomage(5, 10, 20);
        SimpleOomage ooA2 = new SimpleOomage(5, 10, 20);
        SimpleOomage ooB = new SimpleOomage(50, 50, 50);
        assertEquals(ooA, ooA2);
        assertNotEquals(ooA, ooB);
        assertNotEquals(ooA2, ooB);
        assertNotEquals(ooA, "ketchup");
    }

    @Test
    public void testHashCodeAndEqualsConsistency() {
        SimpleOomage ooA = new SimpleOomage(5, 10, 20);
        SimpleOomage ooA2 = new SimpleOomage(5, 10, 20);
        HashSet<SimpleOomage> hashSet = new HashSet<>();
        hashSet.add(ooA);
        assertTrue(hashSet.contains(ooA2));
    }

    /* TODO: Uncomment this test after you finish haveNiceHashCode Spread in OomageTestUtility */
    @Test
    public void testRandomOomagesHashCodeSpread() {
        List<Oomage> oomages = new ArrayList<>();
        int N = 10000;

        for (int i = 0; i < N; i += 1) {
            oomages.add(SimpleOomage.randomSimpleOomage());
        }

        assertTrue(OomageTestUtility.haveNiceHashCodeSpread(oomages, 10));
    }

    /** Calls tests for SimpleOomage. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestSimpleOomage.class);
    }
}
