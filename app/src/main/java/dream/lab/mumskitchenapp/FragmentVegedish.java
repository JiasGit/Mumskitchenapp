package dream.lab.mumskitchenapp;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import dream.lab.mumskitchenapp.CartAdapter.DishRvAdapter;
import dream.lab.mumskitchenapp.Models.Dish;
import dream.lab.mumskitchenapp.viewmodel.DishViewmodel;

public class FragmentVegedish extends Fragment {
    private List<Dish> dishes = new ArrayList<>();
    DishRvAdapter dishRvAdapter = new DishRvAdapter();
    DishViewmodel dishViewmodel;
    private RecyclerView dishesRecView;
    private TextView hotmeals, meat, others;


    public FragmentVegedish() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_vegedish, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        hotmeals = view.findViewById(R.id.textView22);
        meat = view.findViewById(R.id.textView23);
        others = view.findViewById(R.id.textView25);

        dishesRecView = view.findViewById(R.id.recyclerViewVege);
        dishesRecView.setLayoutManager(new GridLayoutManager(this.getContext(),2));
        dishesRecView.setAdapter(dishRvAdapter);

        dishViewmodel = new ViewModelProvider(requireActivity()).get(DishViewmodel.class);
        dishViewmodel.getDishVege().observe(getViewLifecycleOwner(), new Observer<List<Dish>>() {
            @Override
            public void onChanged(List<Dish> dishes) {
                dishRvAdapter.setDishes(dishes);
            }
        });

        dishRvAdapter.setRvOnclickInterface(new DishRvAdapter.RvOnclickInterface() {
            @Override
            public void onItemClick(Dish dish) {
                Snackbar.make(view, " Successfully added to cart!", Snackbar.LENGTH_SHORT).show();
                dishViewmodel.addItemToCart(dish);
            }
        });

        hotmeals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.add(R.id.fragmentContainerView2,new FragmentHotmeals());
                transaction.addToBackStack("name");
                transaction.commit();
            }
        });
        meat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.add(R.id.fragmentContainerView2,new FragmentMeatdish());
                transaction.addToBackStack("name");
                transaction.commit();
            }
        });
        others.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.add(R.id.fragmentContainerView2,new FragmentOthers());
                transaction.addToBackStack("name");
                transaction.commit();
            }
        });
    }

}