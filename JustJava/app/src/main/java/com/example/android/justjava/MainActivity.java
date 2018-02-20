/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;

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
import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 0;
    int pricePerCup = 5;
    int chocolateAdded = 2;
    int whippedCreamAdded = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
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
        int price = calculatePrice();

        /**
         * Gets name input from EditText view
         * assigns name to nameEditText
         */
        EditText name_Edit_Text = (EditText) findViewById(R.id.name_Edit_Text);
        String nameEditText = name_Edit_Text.getText().toString();


        /**
         * checks to see if whipped cream topping checkbox is checked
         * assigns true or false boolean value to hasWhippedCream
         */
        CheckBox checkedWhippedCream = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = checkedWhippedCream.isChecked();

        /**
         * checks to see if chocolate checkbox is checked
         * assigns true or false boolean value to hasChocolate
         */
        CheckBox checkChocolate = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = checkChocolate.isChecked();

        String priceMessage = createOrderSummary(price, hasWhippedCream, hasChocolate, nameEditText);
        displayMessage(priceMessage);
    }

    /**
     * This method is called when the + button is clicked.
     */
    public void increment(View view) {
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    /**
     * This method is called when the - button is clicked.
     */
    public void decrement(View view) {
        quantity = quantity - 1;
        displayQuantity(quantity);
    }

    /**
     * Calculates the price of the order
     */
    private int calculatePrice() {

        // sets price integer
        int price = 0;
        /**
         * checks to see if whipped cream topping checkbox is checked
         * assigns true or false boolean value to hasWhippedCream
         */
        CheckBox checkedWhippedCream = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = checkedWhippedCream.isChecked();

        /**
         * checks to see if chocolate checkbox is checked
         * assigns true or false boolean value to hasChocolate
         */
        CheckBox checkChocolate = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = checkChocolate.isChecked();

        if (hasChocolate == false && hasWhippedCream == false) {
            price = quantity * pricePerCup;
        } else if (hasChocolate == true && hasWhippedCream == false) {
            price = quantity * (pricePerCup + chocolateAdded);
        } else if (hasChocolate == false && hasWhippedCream == true) {
            price = quantity * (pricePerCup + whippedCreamAdded);
        } else {
            price = quantity * (pricePerCup + whippedCreamAdded + chocolateAdded);
        }

        return price;
    }

    /**
     * creates an order summary String
     *
     * @param price is the price of the order
     * @return priceMessage returns the order summary output to the program
     */
    private String createOrderSummary(int price, boolean addWhippedCream, boolean addChocolate, String nameText) {
        String priceMessage = "Name: " + nameText;
        priceMessage += "\nAdd whipped cream? " + addWhippedCream;
        priceMessage += "\nAdd chocolate? " + addChocolate;
        priceMessage += "\nQuantity : " + quantity;
        priceMessage += "\nTotal Price: $" + calculatePrice();
        priceMessage +="\nThank you!";
        return priceMessage;
    }
}