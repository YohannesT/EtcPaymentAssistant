package com.apptech.yohannes.paymentassistant.services;

import android.graphics.Bitmap;
import android.media.ExifInterface;
import android.os.Environment;

import com.googlecode.tesseract.android.TessBaseAPI;

/**
 * Created by Yohannes on 7/22/2014.
 */
public class OCRService {
    TessBaseAPI tessBase;

    public OCRService()
    {
            tessBase = new TessBaseAPI();

        String path ="/mnt/sdcard/tesseract";
        tessBase.setVariable("tessedit_char_whitelist", "0123456789");
        tessBase.init(path, "eng");
    }

    public String Read(Bitmap bitmap)
    {
        tessBase.setImage(bitmap);
        return  cleanUp(tessBase.getUTF8Text());
    }

    private String cleanUp(String text)
    {
        String clean = "";
        for(char c : text.toCharArray())
        {
            if(Character.isDigit(c))
                clean += c;
        }
        return clean;
    }
}
