package com.apptech.yohannes.paymentassistant.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.apptech.yohannes.paymentassistant.R;
import java.util.List;

/**
 * Created by Yohannes on 8/2/2014.
 */
public class CashListAdapter extends ArrayAdapter<Integer>{
    private Context context;
    private List<Integer> integers;
    private TextView cashAmount;

    public CashListAdapter(Context context, List<Integer> integers) {
        super(context, R.layout.cash_amount, integers);
        this.context = context;
        this.integers = integers;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
       LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate( R.layout.cash_amount, parent, false);
        cashAmount = (TextView)rowView.findViewById(R.id.txtCashAmount);

        cashAmount.setText( String.valueOf(integers.get(position)));
        return rowView;
    }
}
