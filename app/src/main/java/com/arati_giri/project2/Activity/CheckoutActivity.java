package com.arati_giri.project2.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.arati_giri.project2.Domain.ItemsDomain;
import com.arati_giri.project2.Helper.ManagmentCart;
import com.arati_giri.project2.databinding.ActivityCheckoutBinding;

import java.util.ArrayList;

public class CheckoutActivity extends AppCompatActivity {

    ActivityCheckoutBinding binding;
    private ArrayList<ItemsDomain> cartItems;
    private ManagmentCart managmentCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCheckoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String[] paymentMethods = {"Debit Card", "Credit Card"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, paymentMethods);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerPaymentMethod.setAdapter(adapter);

        binding.spinnerPaymentMethod.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                binding.textInputLayoutCreditCard.setVisibility(View.GONE);
                binding.textInputLayoutDebitCard.setVisibility(View.GONE);

                if (position == 0) {
                    binding.textInputLayoutDebitCard.setVisibility(View.VISIBLE);
                    binding.textInputLayoutDebitCVV.setVisibility(View.VISIBLE);
                    binding.textInputLayoutDebitExpiryDate.setVisibility(View.VISIBLE);
                    binding.textInputLayoutCreditCard.setVisibility(View.GONE);
                    binding.textInputLayoutCVV.setVisibility(View.GONE);
                    binding.textInputLayoutExpiryDate.setVisibility(View.GONE);
                } else if (position == 1) {
                    binding.textInputLayoutCreditCard.setVisibility(View.VISIBLE);
                    binding.textInputLayoutCVV.setVisibility(View.VISIBLE);
                    binding.textInputLayoutExpiryDate.setVisibility(View.VISIBLE);
                    binding.textInputLayoutDebitCard.setVisibility(View.GONE);
                    binding.textInputLayoutDebitCVV.setVisibility(View.GONE);
                    binding.textInputLayoutDebitExpiryDate.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });

        managmentCart = new ManagmentCart(this);

        // Get cart items from intent extras
        cartItems = (ArrayList<ItemsDomain>) getIntent().getSerializableExtra("cartItems");

        binding.buttonSubmitOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateInputs()) {
                    managmentCart.clearCart();
                    startActivity(new Intent(CheckoutActivity.this, ThankYouActivity.class));
                }
            }
        });


    }

    private boolean validateInputs() {
        String firstName = binding.editTextFirstName.getText().toString().trim();
        String lastName = binding.editTextLastName.getText().toString().trim();
        String email = binding.editTextEmail.getText().toString().trim();
        String phone = binding.editTextPhone.getText().toString().trim();
        String address = binding.editTextAddress.getText().toString().trim();
        String paymentMethod = binding.spinnerPaymentMethod.getSelectedItem().toString();

        if (firstName.isEmpty()) {
            showError("Please enter your first name");
            return false;
        }

        if (lastName.isEmpty()) {
            showError("Please enter your last name");
            return false;
        }

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            showError("Please enter a valid email address");
            return false;
        }

        if (phone.isEmpty() || !Patterns.PHONE.matcher(phone).matches()) {
            showError("Please enter a valid phone number");
            return false;
        }

        if (address.isEmpty()) {
            showError("Please enter your mailing address");
            return false;
        }

        if (paymentMethod.equals("Credit Card")) {
            String creditCardNumber = binding.editTextCreditCardNumber.getText().toString().trim();
            String cvv = binding.editTextCVV.getText().toString().trim();
            String expiryDate = binding.editTextExpiryDate.getText().toString().trim();

            if (creditCardNumber.isEmpty() || creditCardNumber.length() < 16) {
                showError("Please enter a valid credit card number");
                return false;
            }

            if (cvv.isEmpty() || cvv.length() != 3) {
                showError("Please enter a valid CVV");
                return false;
            }

            if (expiryDate.isEmpty()) {
                showError("Please enter the expiry date of your credit card");
                return false;
            }
        }

        if (paymentMethod.equals("Debit Card")) {
            String debitCardNumber = binding.editTextDebitCardNumber.getText().toString().trim();
            String cvv = binding.editTextDebitCVV.getText().toString().trim();
            String expiryDate = binding.editTextDebitExpiryDate.getText().toString().trim();

            if (debitCardNumber.isEmpty() || debitCardNumber.length() < 16) {
                showError("Please enter a valid debit card number");
                return false;
            }

            if (cvv.isEmpty() || cvv.length() !=3) {
                showError("Please enter a valid CVV");
                return false;
            }

            if (expiryDate.isEmpty()) {
                showError("Please enter the expiry date of your credit card");
                return false;
            }
        }

        return true;
    }

    private void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}