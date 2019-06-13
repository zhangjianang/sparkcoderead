package leetcode;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by adimn on 2019/6/13.
 */
public class L10WildCardTest {
    L10WildCard wc;

    @Before
    public void before() {
        wc = new L10WildCard();
    }

    @Test
    public void testIsMatch() {
        assertEquals(true, wc.isMatch("aaa", "ab*a*c*a"));
    }

    @Test
    public void testIsMatch2() {
        assertEquals(true, wc.isMatch("aaa", "a*aaa"));
        assertEquals(true, wc.isMatch("aaa", "a*aa"));
        assertEquals(true, wc.isMatch("aaa", "a*a"));
        assertEquals(false, wc.isMatch("aaa", "ab*a"));
    }

    @Test
    public void testIsMatch3() {
        assertEquals(true, wc.isMatch("aab", "c*a*b"));
        assertEquals(false, wc.isMatch("mississippi", "mis*is*p*."));
    }

    @Test
    public void testIsMatch4() {
        assertEquals(true, wc.isMatch("ab", ".*"));
    }

    @Test
    public void testIsMatch5() {
        assertEquals(false, wc.isMatch("aaa", "a"));
        assertEquals(true, wc.isMatch("aa", "a*"));
    }
}