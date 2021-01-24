package com.github.sweetim;

import com.github.sweetim.model.EntryResultModel;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        EntryResultModel model = new EntryResultModel();
        model.ok = false;
        model.result = "haha";
        assertTrue(model.toString().length() > 0);
    }
}