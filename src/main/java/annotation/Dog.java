package annotation;

import java.lang.annotation.*;

/**
 * Created by adimn on 2019/8/1.
 */

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Dog {
    public String name() default "ang";
}


@interface Undocumented {

}

@Undocumented
class UseDogAndDocumented {

}

