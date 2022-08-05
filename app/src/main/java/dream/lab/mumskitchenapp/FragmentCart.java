package dream.lab.mumskitchenapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import dream.lab.mumskitchenapp.CartAdapter.CartAdapter;
import dream.lab.mumskitchenapp.CartAdapter.DishRvAdapter;
import dream.lab.mumskitchenapp.Models.Cart;
import dream.lab.mumskitchenapp.Models.Dish;
import dream.lab.mumskitchenapp.viewmodel.DishViewmodel;


public class FragmentCart extends Fragment implements CartAdapter.CartOnClickInterface {

    DishViewmodel dishViewmodel;
    CartAdapter cartAdapter = new CartAdapter(this);
    private RecyclerView cartRecyclerView;
    private List<Cart> cartList = new ArrayList<>();
    private TextView totalPrice, txtBack, txtCheckout;

    public FragmentCart() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        totalPrice = view.findViewById(R.id.textTotalPrice);
        txtBack = view.findViewById(R.id.textBack);
        txtCheckout = (Button)view.findViewById(R.id.button3);

        cartRecyclerView = view.findViewById(R.id.cartRecyclerView);
        cartRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        cartRecyclerView.setAdapter(cartAdapter);

        dishViewmodel = new ViewModelProvider(requireActivity()).get(DishViewmodel.class);
        dishViewmodel.getCart().observe(getViewLifecycleOwner(), new Observer<List<Cart>>() {
            @Override
            public void onChanged(List<Cart> carts) {

                cartAdapter.setCartList(carts);
                double totalCartPrice = 0;
                for (Cart cart : carts) {
                    totalCartPrice += cart.getDishPrice()*cart.getQty();

                }
                totalPrice.setText("$"+String.valueOf(totalCartPrice));

            }
        });

        txtBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);

                 */
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.add(R.id.fragmentContainerView2,new FragmentHotmeals());
                transaction.addToBackStack("name");
                transaction.commit();
            }
        });
        txtCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CheckoutActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void incrementQuantity(Cart cart) {
        boolean status = dishViewmodel.incrementQty(cart);
        if(status == false)
            Toast.makeText(this.getContext(), "Max quantity is 10!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void decrementQuantity(Cart cart) {
        boolean status = dishViewmodel.decrementQty(cart);
        if(status == false)
            Toast.makeText(this.getContext(), "Min quantity is 1!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void removeItem(Cart cart) {
        dishViewmodel.removeItemFromCart(cart);
    }
}