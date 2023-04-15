package com.example.mydigitalcloset.closetPage;

import static org.junit.Assert.*;

import android.app.Instrumentation;
import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import com.example.mydigitalcloset.R;

import org.junit.Assert;
import org.junit.Test;

public class SavedFitTest {

    //SavedFit Activity should be initialized properly via the constructor to the proper package
    @Test
    public void savedFitActivityInitializationTest() {
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        Context appContext = instrumentation.getTargetContext();
        String expected = "com.example.mydigitalcloset";
        String actual = appContext.getPackageName();
        assertEquals(expected,actual);
    }

    //When SavedFit is called, onCreate should activate successfully
    @Test
    public void savedFitOnCreateTest() {

        Boolean expected = true;
        Boolean actual = AllSavedOutfits.isCreatedSuccessfully();
        Assert.assertEquals(expected,actual);
    }

    //View of SavedFit activity should be the AllSavedOutfit Layout
    @Test
    public void savedFitContentViewTest()
    {
        //ViewGroup layout = (ViewGroup) getWindow().getDecorView().getChildAt(0);
        int expectedLayout = R.id.SavedOutfitLayout;
        int actualLayout = SavedFit.getContentView();
        Assert.assertEquals(expectedLayout,actualLayout);
    }
}