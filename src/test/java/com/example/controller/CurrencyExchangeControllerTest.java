package com.example.controller;

import com.example.currency_exchange.controller.CurrencyExchangeController;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.SecondaryTable;

import static matchers.NumberMatcher.isEven;
import static matchers.NumberMatcher.isNegative;

/**
 * Created by maciej on 28.03.17.
 */
public class CurrencyExchangeControllerTest {


    @Before //odpalane przed każdym testem
    public void setUp(){
        System.out.print("setting up\n");
    }

    @After
    public void setDown(){
        System.out.print("setting down\n");
    }


    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    CurrencyExchangeController currencyExchangeController = new CurrencyExchangeController();

    @Test
    public void shouldHandleExceptionWhenThrown(){
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("Something went wrong");

        currencyExchangeController.currencyMultiplier1(20l,10d,"badCode","GBP");
    }

    @Test
    public void twoMultiplyByTwoShouldReturnFour(){
        CurrencyExchangeController currencyExchangeController = new CurrencyExchangeController();

        Long currentNumber = 2l;

        Long result = currentNumber*2;

        Assert.assertEquals(result,currencyExchangeController.multiplyByTwo(currentNumber));

    }

    @Test(expected = RuntimeException.class)
    public void currencyMultiplier1ShouldThrowException(){

        CurrencyExchangeController currencyExchangeController = new CurrencyExchangeController();
        String wrongCurrencySignature = "XXx";
        currencyExchangeController.currencyMultiplier1(234l,20d,wrongCurrencySignature,"GBP");

    }

    @Test
    public void currencyMultiplier1ExceptionMessageShouldEqualChosen(){
        try{
            CurrencyExchangeController currencyExchangeController = new CurrencyExchangeController();
            String wrongCurrencySignature = "XXx";
            currencyExchangeController.currencyMultiplier1(234l,20d,wrongCurrencySignature,"GBP");
            Assert.fail(); // to ważne, bo bez tego może przejść gdy nie poleci wyjątek
        }
        catch(RuntimeException e){
            Assert.assertEquals("Something went wrong",e.getMessage());
        }
    }

    @Test
    public void shouldReturnEvenWhenMultiplyingFiveByFour(){
        Long result = currencyExchangeController.multiplyByTwo(5l);
        Assert.assertThat(result, isEven());
        //Assert.assertTrue(result%2==0);
    }

    @Test
    public void shouldReturnNegativeNumber(){
        Long result = currencyExchangeController.multiplyByTwo(-5l);
        Assert.assertThat(result, isNegative());
        //Assert.assertTrue(result%2==0);
    }



}
