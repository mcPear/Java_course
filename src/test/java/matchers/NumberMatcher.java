package matchers;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeDiagnosingMatcher;

/**
 * Created by maciej on 28.03.17.
 */
public class NumberMatcher {

    //żeby za każdym razem nie pisać czy parzyste, to pisze się takiego matchera i on jest używany w teście odpowiednio
    public static<T>TypeSafeDiagnosingMatcher isEven(){
        return new TypeSafeDiagnosingMatcher<Long>() {
            @Override
            protected boolean matchesSafely(Long item, Description description) {
                return item%2==0;
            }

            @Override
            public void describeTo(Description description) {

            }
        };
    }

    public static<T>TypeSafeDiagnosingMatcher isNegative(){
        return new TypeSafeDiagnosingMatcher<Long>() {
            @Override
            protected boolean matchesSafely(Long item, Description description) {
                return item<0;
            }

            @Override
            public void describeTo(Description description) {

            }
        };
    }


}
