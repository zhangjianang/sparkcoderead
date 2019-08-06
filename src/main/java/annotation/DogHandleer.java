package annotation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by adimn on 2019/8/1.
 */
public class DogHandleer {
    public static void action(Object obj) throws InvocationTargetException, IllegalAccessException {
        Method[] methods = obj.getClass().getDeclaredMethods();
        for(Method mt : methods){
            Dog ac =(Dog) mt.getAnnotation(Dog.class);
            String result =ac.name();
            if(result.equals("10")){
                // 0 表示要执行
                mt.invoke(obj);
            }else if ("ang".equals(result)){
                mt.invoke(obj);
            }
        }
    }

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        UseDog dog =new UseDog();
        DogHandleer.action(dog);
    }

}

class UseDog{
    @Dog
    public void say(){
        System.out.println("小狗会说话...");
    }

    @Dog(name = "0")
    public void sing(){
        System.out.println("小狗会唱歌...");
    }


}
