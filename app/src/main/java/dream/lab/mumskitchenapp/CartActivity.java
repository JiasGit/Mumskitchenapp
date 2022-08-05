package dream.lab.mumskitchenapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import dream.lab.mumskitchenapp.CartAdapter.CartAdapter;
import dream.lab.mumskitchenapp.Models.Cart;
import dream.lab.mumskitchenapp.Models.Dish;

public class CartActivity extends AppCompatActivity {

    private static final String TAG = "CartActivity";
    //CartViewModel cartViewModel;
    CartAdapter cartAdapter = new CartAdapter(this);
    private RecyclerView cartRecyclerView;
    private List<Cart> cartList = new ArrayList<>();
    private TextView totalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        totalPrice = findViewById(R.id.textTotal);

        cartRecyclerView = findViewById(R.id.cartRecyclerView);
        cartRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        Intent intent = this.getIntent();
        cartList = intent.getParcelableArrayListExtra("cartList");

        cartAdapter.setCartList(cartList);
        cartRecyclerView.setAdapter(cartAdapter);

        //Calculate total price
        double totalCartPrice = 0;
        if(cartList != null) {
            for (Cart cart : cartList) {
                totalCartPrice += cart.getDishPrice()*cart.getQty();

            }
        }
        totalPrice.setText(String.valueOf(totalCartPrice));
/*
        cartAdapter.setCartOnClickInterface(new CartAdapter.CartOnClickInterface() {
            @Override
            public void incrementQuantity(Cart cart) {
                cart.setQty(cart.getQty() + 1);
            }

            @Override
            public void decrementQuantity(Cart cart) {
                cart.setQty(cart.getQty() - 1);

            }

            @Override
            public void remove(Cart cart) {

            }
        });

 */


    }
}