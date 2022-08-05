package dream.lab.mumskitchenapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import dream.lab.mumskitchenapp.Models.Cart;
import dream.lab.mumskitchenapp.Models.Dish;
import dream.lab.mumskitchenapp.Models.User;
import dream.lab.mumskitchenapp.viewmodel.DishViewmodel;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "MainActivity";
    DishViewmodel dishViewmodel;
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;
    private TextView cartCount;
    private ImageView cartImage;

    FragmentManager fragmentManager = getSupportFragmentManager();

    private ArrayList<Cart> cartList = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Firebase
        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();
        //TextView
        final TextView userName = findViewById(R.id.editTextUserName);

        cartImage = findViewById(R.id.imageView4);
        cartCount = findViewById(R.id.textViewCount);




        // Fetch user's name from firebase and display it on the screen
        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if(userProfile != null){
                    String fullname = userProfile.name;
                    userName.setText(fullname);
                }else{
                    userName.setText(null);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        // Go to Account page when user click on his/her name
        userName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AccountActivity.class));
            }
        });

        // Switch to different fragment upon user's click
        cartImage.setOnClickListener(this);

        dishViewmodel = new ViewModelProvider(this).get(DishViewmodel.class);
        dishViewmodel.getCart().observe(this, new Observer<List<Cart>>() {
            @Override
            public void onChanged(List<Cart> carts) {
                int quantity = 0;
                if(carts.size() < 1){
                    cartCount.setVisibility(View.INVISIBLE);
                }else {
                    for (Cart cart : carts) {
                        quantity += cart.getQty();
                    }
                    cartCount.setVisibility(View.VISIBLE);
                    cartCount.setText(String.valueOf(quantity));
                }
            }
        });



    }


    @Override
    public void onClick(View view) {

        switch (view.getId()){
            /*
            case R.id.textView2:
                fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView2, FragmentHotmeals.class, null)
                        .setReorderingAllowed(true)
                        .addToBackStack("name") // name can be null
                        .commit();
                hotMeal.setBackgroundColor(getResources().getColor(R.color.orange));
                hotMeal.setTextColor(getResources().getColor(R.color.black));
                meatDish.setBackgroundColor(getResources().getColor(R.color.dark_grey));
                meatDish.setTextColor(getResources().getColor(R.color.white));
                vegeDish.setBackgroundColor(getResources().getColor(R.color.dark_grey));
                vegeDish.setTextColor(getResources().getColor(R.color.white));
                others.setBackgroundColor(getResources().getColor(R.color.dark_grey));
                others.setTextColor(getResources().getColor(R.color.white));
                break;
            case R.id.textView3:
                fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView2, FragmentMeatdish.class, null)
                        .setReorderingAllowed(true)
                        .addToBackStack("name") // name can be null
                        .commit();
                meatDish.setBackgroundColor(getResources().getColor(R.color.orange));
                meatDish.setTextColor(getResources().getColor(R.color.black));
                hotMeal.setBackgroundColor(getResources().getColor(R.color.dark_grey));
                hotMeal.setTextColor(getResources().getColor(R.color.white));
                vegeDish.setBackgroundColor(getResources().getColor(R.color.dark_grey));
                vegeDish.setTextColor(getResources().getColor(R.color.white));
                others.setBackgroundColor(getResources().getColor(R.color.dark_grey));
                others.setTextColor(getResources().getColor(R.color.white));
                break;
            case R.id.textView4:
                fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView2, FragmentVegedish.class, null)
                        .setReorderingAllowed(true)
                        .addToBackStack("name") // name can be null
                        .commit();
                vegeDish.setBackgroundColor(getResources().getColor(R.color.orange));
                vegeDish.setTextColor(getResources().getColor(R.color.black));
                meatDish.setBackgroundColor(getResources().getColor(R.color.dark_grey));
                meatDish.setTextColor(getResources().getColor(R.color.white));
                hotMeal.setBackgroundColor(getResources().getColor(R.color.dark_grey));
                hotMeal.setTextColor(getResources().getColor(R.color.white));
                others.setBackgroundColor(getResources().getColor(R.color.dark_grey));
                others.setTextColor(getResources().getColor(R.color.white));
                break;
            case R.id.textView5:
                fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView2, FragmentOthers.class, null)
                        .setReorderingAllowed(true)
                        .addToBackStack("name") // name can be null
                        .commit();
                others.setBackgroundColor(getResources().getColor(R.color.orange));
                others.setTextColor(getResources().getColor(R.color.black));
                meatDish.setBackgroundColor(getResources().getColor(R.color.dark_grey));
                meatDish.setTextColor(getResources().getColor(R.color.white));
                vegeDish.setBackgroundColor(getResources().getColor(R.color.dark_grey));
                vegeDish.setTextColor(getResources().getColor(R.color.white));
                hotMeal.setBackgroundColor(getResources().getColor(R.color.dark_grey));
                hotMeal.setTextColor(getResources().getColor(R.color.white));
                break;

             */
            case R.id.imageView4:
                /*
                Intent intent = new Intent(MainActivity.this, CartActivity.class);
                intent.putParcelableArrayListExtra("cartList", cartList);
                startActivity(intent);
                 */
                fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView2, FragmentCart.class, null)
                        .setReorderingAllowed(true)
                        .addToBackStack("name") // name can be null
                        .commit();
        }
    }

}