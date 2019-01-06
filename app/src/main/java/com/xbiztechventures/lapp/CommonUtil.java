package com.xbiztechventures.lapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

public class CommonUtil {

    public void showDialog(Context context ,String msg){
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
    }
}
