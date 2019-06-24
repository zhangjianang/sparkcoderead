package algorithm;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

/**
 * Created by adimn on 2019/6/14.
 */
public class DynamicMaxTest {

    @Mock
    MyDao myDao;

    DynamicMax dm;

    @BeforeTest
    public void initData(){
        MockitoAnnotations.initMocks(this);
        dm = new DynamicMax();
        dm.setMyDao(myDao);

    }

    @Test
    public void testCut(){
        int[] price = {1,5,8,9,10,17,17,20,24,30};


        System.out.println(DynamicMax.cut(price,4));
    }

    @Test
    public void testMyMock(){
        initData();
        Mockito.when(myDao.getJson(10L)).thenReturn("this is mock");
        dm.genSthById(10);
    }
}