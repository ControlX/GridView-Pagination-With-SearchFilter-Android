package com.github.controlx.catalogue.helper;

import com.github.controlx.catalogue.R;

import java.util.HashMap;

/**
 * Created by Abhishek Verma on 11/29/2016.
 */

public class ImageMapConstant {
    private static final HashMap<String, Integer> posterNameToImageMappings = new HashMap<>();

    public static void init(){
        posterNameToImageMappings.put("poster1.jpg", R.drawable.poster1);
        posterNameToImageMappings.put("poster2.jpg", R.drawable.poster2);
        posterNameToImageMappings.put("poster3.jpg", R.drawable.poster3);
        posterNameToImageMappings.put("poster4.jpg", R.drawable.poster4);
        posterNameToImageMappings.put("poster5.jpg", R.drawable.poster5);
        posterNameToImageMappings.put("poster6.jpg", R.drawable.poster6);
        posterNameToImageMappings.put("poster7.jpg", R.drawable.poster7);
        posterNameToImageMappings.put("poster8.jpg", R.drawable.poster8);
        posterNameToImageMappings.put("poster9.jpg", R.drawable.poster9);
    }

    public static int getDrawable(String posterName){
        Integer drawablePath = posterNameToImageMappings.get(posterName);

        if(drawablePath != null)
            return drawablePath;
        else
            return 0;
    }
}
