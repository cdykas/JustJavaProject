package com.example.killj.justjavaproject;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    int quantity = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

        // displayMessage(createOrderSummary(quantity));
        EditText nameField = (EditText)  findViewById(R.id.input);
        String name = nameField.getText().toString();
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java Order for " + name);
        intent.putExtra(Intent.EXTRA_TEXT, createOrderSummary(quantity, name));
        if(intent.resolveActivity(getPackageManager()) != null)
        {
            startActivity(intent);
        }


    }

    public String createOrderSummary(int number, String name)
    {
        CheckBox whippedCream = (CheckBox)findViewById(R.id.box_whippedcream);
        CheckBox chocolate = (CheckBox)findViewById(R.id.box_chocolate);
        boolean hasWhippedCream = whippedCream.isChecked();
        boolean hasChocolate = chocolate.isChecked();

        return "Name: " + name + "\nQuantity: " + quantity + "\nWhipped Cream: " + hasWhippedCream + "\nChocolate: " + hasChocolate + "\nTotal: " + NumberFormat.getCurrencyInstance().format((calculatePrice())) + "\nThank you!";
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_number);
        quantityTextView.setText("" + number);
    }
    /**
     * This method displays the given price on the screen.
     */


    private int calculatePrice()
    {
        CheckBox whippedCream = (CheckBox)findViewById(R.id.box_whippedcream);
        CheckBox chocolate = (CheckBox)findViewById(R.id.box_chocolate);
        boolean hasWhippedCream = whippedCream.isChecked();
        boolean hasChocolate = chocolate.isChecked();
        int price = quantity * 5;
        if(hasChocolate)
        {
            price = price+2;
        }
        if(hasWhippedCream)
        {
            price = price + 1;
        }
        return price;
    }

    public void increment(View view)
    {
        if(quantity<100) {
            quantity++;
        }
        display(quantity);
    }
    public void decrement(View view)
    {
        if(quantity >1) {
            quantity--;
        }
        display(quantity);
    }


}
