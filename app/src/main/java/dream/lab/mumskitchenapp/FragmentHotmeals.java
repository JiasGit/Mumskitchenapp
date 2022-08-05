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


public class FragmentHotmeals extends Fragment {
    private static final String TAG = "FragmentHotmeals";
    private List<Dish> dishes = new ArrayList<>();
    DishRvAdapter dishRvAdapter = new DishRvAdapter();
    DishViewmodel dishViewmodel;
    private RecyclerView dishesRecView;
    private TextView meat, vege, others;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_hotmeals, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        meat = view.findViewById(R.id.textView14);
        vege = view.findViewById(R.id.textView16);
        others = view.findViewById(R.id.textView17);

        dishesRecView = view.findViewById(R.id.recyclerView);
        dishesRecView.setLayoutManager(new GridLayoutManager(this.getContext(),2));
        dishesRecView.setAdapter(dishRvAdapter);

        dishViewmodel = new ViewModelProvider(requireActivity()).get(DishViewmodel.class);
        dishViewmodel.getDishHot().observe(getViewLifecycleOwner(), new Observer<List<Dish>>() {
            @Override
            public void onChanged(List<Dish> dishes) {
                //dishRvAdapter.submitList(dishes); // for ListAdapter
                //Toast.makeText(view.getContext(), "on Changed", Toast.LENGTH_SHORT).show();
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

        meat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.add(R.id.fragmentContainerView2,new FragmentMeatdish());
                transaction.addToBackStack("name");
                transaction.commit();
            }
        });
        vege.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.add(R.id.fragmentContainerView2,new FragmentVegedish());
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