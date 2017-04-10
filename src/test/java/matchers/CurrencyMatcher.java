package matchers;

import com.example.currency_exchange.entity.ExchangeRate;
import com.example.currency_exchange.model.ExchangeModel;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeDiagnosingMatcher;

import java.util.Currency;
import java.util.Optional;

/**
 * Created by maciej on 09.04.17.
 */

//HOMEWORK - Chapter 6
public class CurrencyMatcher {

    public static<T>TypeSafeDiagnosingMatcher exchangesFrom(Currency currencyFrom){
        return new TypeSafeDiagnosingMatcher<ExchangeRate>() { //ExchangeRate instead of CurrencyPair
            @Override
            protected boolean matchesSafely(ExchangeRate exchangeRate, Description description) {
                return exchangeRate.getCurrencyFrom().equals(currencyFrom.toString());
            }

            @Override
            public void describeTo(Description description) {

            }
        };
    }

}
