package annotation;

import java.lang.annotation.Documented;

/**
 * Created by adimn on 2019/8/1.
 */

@Documented
public @interface Dog {
    public String name() default "ang";
}


@interface Undocumented {

}

@Dog
@Undocumented
class UseDogAndDocumented {
    public static void main(String[] args) {

    }
}

