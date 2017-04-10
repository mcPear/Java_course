package com.example.controller;

import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

import static matchers.OptionalMatcher.isEmpty;
import static matchers.OptionalMatcher.isPresent;

/**
 * Created by maciej on 09.04.17.
 */

//HOMEWORK - Chapter 6
public class TestUsingOptionalMatcher {

    @Test
    public void shouldBePresent(){
        Optional item = Optional.of(5f);
        Assert.assertThat(item, isPresent());
    }

    @Test
    public void shouldBeEmpty(){
        Optional item = Optional.empty();
        Assert.assertThat(item, isEmpty());
    }

}
