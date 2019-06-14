package algorithm;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by adimn on 2019/6/14.
 */
public class DynamicMaxTest {
    DynamicMax dm;
    @Before
    public void before(){
        dm = new DynamicMax();
    }

    @Test
    public void testCut(){
        int[] price = {1,5,8,9,10,17,17,20,24,30};
        System.out.println(DynamicMax.cut(price,4));
    }
}