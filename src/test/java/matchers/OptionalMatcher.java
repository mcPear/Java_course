package matchers;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeDiagnosingMatcher;

import java.util.Optional;

/**
 * Created by maciej on 09.04.17.
 */

//HOMEWORK - Chapter 6
public class OptionalMatcher {

    public static<T>TypeSafeDiagnosingMatcher isPresent(){
        return new TypeSafeDiagnosingMatcher<Optional>() {
            @Override
            protected boolean matchesSafely(Optional item, Description description) {
                return item.isPresent();
            }

            @Override
            public void describeTo(Description description) {

            }
        };
    }

    public static<T>TypeSafeDiagnosingMatcher isEmpty(){
        return new TypeSafeDiagnosingMatcher<Optional>() {
            @Override
            protected boolean matchesSafely(Optional item, Description description) {
                return !item.isPresent();
            }

            @Override
            public void describeTo(Description description) {

            }
        };
    }

}
