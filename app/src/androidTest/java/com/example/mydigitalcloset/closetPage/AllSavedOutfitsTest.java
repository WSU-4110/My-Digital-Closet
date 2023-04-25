package com.example.mydigitalcloset.closetPage;
import static org.junit.Assert.assertEquals;

import android.app.Instrumentation;
import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import com.example.mydigitalcloset.R;

import org.junit.Assert;
import org.junit.Test;

public class AllSavedOutfitsTest {

    //Activity should be initialized properly via the constructor to the proper package
    @Test
    public void activityInitializationTest() {
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        Context appContext = instrumentation.getTargetContext();
        String expected = "com.example.mydigitalcloset";
        String actual = appContext.getPackageName();
        assertEquals(expected,actual);
    }

    //When AllSavedOutfits is called, onCreate should activate successfully
    @Test
    public void onCreateTest() {

        Boolean expected = true;
        Boolean actual = AllSavedOutfits.isCreatedSuccessfully();
        Assert.assertEquals(expected,actual);
    }

    //View of AllSavedOutfit activity should be the AllSavedOutfit Layout
    @Test
    public void contentViewTest()
    {
        //ViewGroup layout = (ViewGroup) getWindow().getDecorView().getChildAt(0);
        int expectedLayout = R.id.AllSavedOutfitsLayout;
        int actualLayout = AllSavedOutfits.getContentView();
        Assert.assertEquals(expectedLayout,actualLayout);
    }

}