package dream.lab.mumskitchenapp.CartAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import dream.lab.mumskitchenapp.Models.Dish;
import dream.lab.mumskitchenapp.R;

public class DishRvAdapter extends RecyclerView.Adapter<DishRvAdapter.viewHolder> {

    private List<Dish> dishes = new ArrayList<>();
    private Context context;
    private View view;
    private RvOnclickInterface listener;
    /*
        public DishRvAdapter(Context context){
            this.context = context;
        }


        public DishRvAdapter(ArrayList<Dish> dishes) {
            this.dishes = dishes;
            //this.rvOnclickInterface = rvOnclickInterface;
        }


        protected DishRvAdapter(){
            super(Dish.itemCallback);
        }

     */

    public void setDishes(List<Dish> dishes){
        this.dishes = dishes;
        notifyDataSetChanged();
    }



    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        viewHolder holder = new viewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.dishName.setText(dishes.get(position).getDishName());
        holder.dishPrice.setText("$" + String.valueOf(dishes.get(position).getDishPrice()));
        Glide.with(holder.parent.getContext())
                .asBitmap()
                .load(dishes.get(position).getImageURL())
                .into(holder.image);

        holder.addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if(listener!=null && position != RecyclerView.NO_POSITION)
                listener.onItemClick(dishes.get(holder.getAdapterPosition()));

            }
        });


    }

    @Override
    public int getItemCount() {
        return dishes.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        private TextView dishName, dishPrice, addToCart;
        private CardView parent;
        private ImageView image;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            dishName = itemView.findViewById(R.id.dishName);
            dishPrice = itemView.findViewById(R.id.dishPrice);
            image = itemView.findViewById(R.id.image);
            addToCart = itemView.findViewById(R.id.textAddtoCart);
            parent = itemView.findViewById(R.id.parent);

        }
    }

    public interface RvOnclickInterface {
        void onItemClick(Dish dish);
    }
    public void setRvOnclickInterface(RvOnclickInterface listener){
        this.listener = listener;
    }
}
