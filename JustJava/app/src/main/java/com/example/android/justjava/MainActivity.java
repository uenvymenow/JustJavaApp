/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android.justjava.R;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int numberOfCoffees) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + numberOfCoffees);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {


        /**
         * Gets name input from EditText view
         * assigns name to nameEditText
         */
        EditText name_Edit_Text = findViewById(R.id.name_Edit_Text);
        String nameEditText = name_Edit_Text.getText().toString();


        /**
         * checks to see if whipped cream topping checkbox is checked
         * assigns true or false boolean value to hasWhippedCream
         */
        CheckBox checkedWhippedCream = findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = checkedWhippedCream.isChecked();

        /**
         * checks to see if chocolate checkbox is checked
         * assigns true or false boolean value to hasChocolate
         */
        CheckBox checkChocolate = findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = checkChocolate.isChecked();

        int price = calculatePrice(hasWhippedCream, hasChocolate);

        String subjectMessage = "JustJava order for " + nameEditText;
        String priceMessage = createOrderSummary(price, hasWhippedCream, hasChocolate, nameEditText);

        /**
         * Creates an intent and fills out the information to send to email address along with the order summary
         */
        Intent orderSummaryIntent = new Intent(Intent.ACTION_SENDTO);
        orderSummaryIntent.setData(Uri.parse("mailto:")); // only email apps should handle this
        orderSummaryIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"uenvymenow@gmail.com"});
        orderSummaryIntent.putExtra(Intent.EXTRA_SUBJECT, subjectMessage);
        orderSummaryIntent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        if (orderSummaryIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(orderSummaryIntent);
        }
    }

    /**
     * This method is called when the + button is clicked.
     */
    public void increment(View view) {

        // Creates a String to use in the Toast message that informs upper limit of quantity
        String upperLimit = getString(R.string.maxQuantityToast);

        // ensures the quantity value does not go above 100
        if (quantity < 100) {
            quantity = quantity + 1;
        } else {
            // Creates a toast message with lowerLimit quantity displayed and exits control flow
            Toast.makeText(this, upperLimit, Toast.LENGTH_SHORT).show();
            return;
        }

        displayQuantity(quantity);
    }

    /**
     * This method is called when the - button is clicked.
     */
    public void decrement(View view) {

        // Creates a String to use in the Toast message that informs lower limit of quantity
        String lowerLimit = getString(R.string.minQuantityToast);

        // ensures the quantity value does not go below 0
        if (quantity > 0) {
            quantity = quantity - 1;
        } else {
            // Creates a toast message with lowerLimit quantity displayed and exits control flow
            Toast.makeText(this, lowerLimit, Toast.LENGTH_SHORT).show();
            return;
        }

        displayQuantity(quantity);
    }

    /**
     * Calculates the price of the order
     *
     * @param addChocolate    is whether or not the user wants chocolate topping
     * @param addWhippedCream is whether or not the user wants whipped cream topping
     * @return total price
     */
    private int calculatePrice(boolean addWhippedCream, boolean addChocolate) {

        // sets price integer
        int price = 5;

        // Add $1 if user wants whipped cream
        if (addWhippedCream) {
            price = price + 1;
        }

        // Add $2 if user wants chocolate
        if (addChocolate) {
            price = price + 2;
        }

        // Calculate the total order price by multiplying quantity by price
        return quantity * price;
    }

    /**
     * creates an order summary String
     *
     * @param price is the price of the order
     * @return priceMessage returns the order summary output to the program
     */
    private String createOrderSummary(int price, boolean addWhippedCream, boolean addChocolate, String nameText) {
        String priceMessage = getString(R.string.nameOrderSummary, nameText);
        priceMessage += "\n" + getString(R.string.addWhippedCream)+ addWhippedCream;
        priceMessage += "\n" + getString(R.string.addChocolate) + addChocolate;
        priceMessage += "\n" + getString(R.string.quantityOrderSummary)+ quantity;
        priceMessage += "\n" + getString(R.string.totalOrderSummary)+ price;
        priceMessage += "\n" + getString(R.string.thankYouOrderSummary);
        return priceMessage;
    }
}