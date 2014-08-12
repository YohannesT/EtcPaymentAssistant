package com.apptech.yohannes.paymentassistant.services;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;


import com.googlecode.tesseract.android.TessBaseAPI;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Yohannes on 7/22/2014.
 */
public class OCRService {
    private Context context;
    private TessBaseAPI tessBase;
    private String path;
    private final String fileName = "eng.traineddata";

    public OCRService(Context context, String _path)
    {
        tessBase = new TessBaseAPI();
        this.context = context;
        path = _path + "/.odata/";

        if(!new File(path, fileName).exists())
            SetupResource();

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

    private void SetupResource()
    {
        try
        {
            InputStream is = context.getAssets().open("eng.mp3");
            String _path = path + "/tessdata";
            File folder = new File(_path);
            if(!folder.exists()) folder.mkdirs();

            File outFile = new File(_path, fileName);
            outFile.createNewFile();

            OutputStream os = new FileOutputStream(outFile);

            byte [] buff = new byte[1024];
            int len = 0;
            while((len = is.read(buff)) > 0)
                os.write(buff, 0, len);

            boolean x = outFile.exists();

            os.flush();
            os.close();
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
