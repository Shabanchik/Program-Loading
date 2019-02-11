package com.example.pupil.programfiles;

import android.util.Log;

public class SomeClass {
    private void iskluchenia(){
        int a=10;
        int b = 0;
        try{
            int c=a/b;
        }
        catch (ArithmeticException e){
            Log.e("Error",e.getLocalizedMessage());
        }
        finally {
            Log.d("App","CLOSE");
        }
    }
}
