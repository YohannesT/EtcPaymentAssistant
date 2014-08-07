package com.apptech.yohannes.paymentassistant.services;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import com.apptech.yohannes.paymentassistant.domain.Contact;

/**
 * Created by Yohannes on 7/20/2014.
 */
public class PhoneService {
    private Context _context;

    public PhoneService(Context context)
    {
        _context = context;
    }

    public Boolean Call(String no)
    {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        callIntent.setData(Uri.parse("tel:" + no));
        try
        {
            _context.startActivity(callIntent);
            return true;
        }
        catch (Exception ex)
        {
            Toast.makeText(_context, ex.getMessage(),  Toast.LENGTH_LONG).show();
            return false;
        }
    }

    public Boolean Call(Contact contact)
    {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        callIntent.setData(Uri.parse("tel:" + contact.getPhoneNumber()));
        try
        {
            _context.startActivity(callIntent);
            return true;
        }
        catch (Exception ex)
        {
            Toast.makeText(_context, ex.getMessage(),  Toast.LENGTH_LONG).show();
            return false;
        }
    }

    private void Delay(String amount)
    {
        try
        {
            Thread.sleep(Long.parseLong(amount));
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
