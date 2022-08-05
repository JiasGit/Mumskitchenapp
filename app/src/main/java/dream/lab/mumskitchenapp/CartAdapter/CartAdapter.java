package dream.lab.mumskitchenapp.CartAdapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import dream.lab.mumskitchenapp.Models.Cart;
import dream.lab.mumskitchenapp.R;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.cartViewHolder> {

    private List<Cart> cartList = new ArrayList<>();
    private CartOnClickInterface cartOnClickInterface;

    public CartAdapter(List<Cart> cartList) {
        this.cartList = cartList;
    }
    private Context context;

    public CartAdapter(CartOnClickInterface cartOnClickInterface) {
        this.cartOnClickInterface = cartOnClickInterface;

    }
    public CartAdapter(Context context) {
        this.context = context;

    }

    public void setCartList(List<Cart> cartList){
        this.cartList = cartList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public cartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false);
        cartViewHolder holder = new cartViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull cartViewHolder holder, int position) {
        holder.cartDishName.setText(cartList.get(position).getDishName());
        holder.cartDishQty.setText(String.valueOf(cartList.get(position).getQty()));
        holder.subTotal.setText("$"+String.valueOf(cartList.get(position).getDishPrice()*cartList.get(position).getQty()));
        Glide.with(holder.itemView.getContext())
                .asBitmap()
                .load(cartList.get(position).getImageURL())
                .into(holder.cartImage);
        holder.increment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cartOnClickInterface.incrementQuantity(cartList.get(holder.getAdapterPosition()));
                /*
                if(cartList.get(holder.getAdapterPosition()).getQty() <10){
                    cartList.get(holder.getAdapterPosition()).setQty(cartList.get(holder.getAdapterPosition()).getQty()+1);
                    Log.d("CartAdapter", "cartList getQty: "+ cartList.get(holder.getAdapterPosition()).getQty());
                    //holder.cartDishQty.setText(String.valueOf(cartList.get(holder.getAdapterPosition()).getQty()));
                    //holder.subTotal.setText(String.valueOf(cartList.get(holder.getAdapterPosition()).getDishPrice()*cartList.get(holder.getAdapterPosition()).getQty()));
                }else{
                    Toast.makeText(holder.itemView.getContext(), "Max quantity is 10!", Toast.LENGTH_SHORT).show();
                }

                 */
            }
        });

        holder.decrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               cartOnClickInterface.decrementQuantity(cartList.get(holder.getAdapterPosition()));
                /*
                if(cartList.get(holder.getAdapterPosition()).getQty() > 1) {
                    cartList.get(holder.getAdapterPosition()).setQty(cartList.get(holder.getAdapterPosition()).getQty() - 1);
                    //holder.cartDishQty.setText(String.valueOf(cartList.get(holder.getAdapterPosition()).getQty()));
                    //holder.subTotal.setText(String.valueOf(cartList.get(holder.getAdapterPosition()).getDishPrice()*cartList.get(holder.getAdapterPosition()).getQty()));
                }else {
                    Toast.makeText(holder.itemView.getContext(), "Min quantity is 1!", Toast.LENGTH_SHORT).show();
                }

                 */
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //listener.remove(cartList.get(holder.getAdapterPosition()));
               // cartList.remove(holder.getAdapterPosition());
                cartOnClickInterface.removeItem(cartList.get(holder.getAdapterPosition()));
            }
        });

    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    public class cartViewHolder extends RecyclerView.ViewHolder {
        private TextView cartDishName, cartDishQty, increment, decrement, delete, subTotal;
        private ImageView cartImage;
        public cartViewHolder(@NonNull View itemView) {
            super(itemView);

            cartDishName = itemView.findViewById(R.id.textName);
            cartDishQty = itemView.findViewById(R.id.textQty);
            cartImage = itemView.findViewById(R.id.imageView);
            increment = itemView.findViewById(R.id.textIncrement);
            decrement = itemView.findViewById(R.id.textDecrement);
            delete = itemView.findViewById(R.id.textDelete);
            subTotal = itemView.findViewById(R.id.textSubTotal);

        }
    }

    public interface CartOnClickInterface{
        void incrementQuantity(Cart cart);
        void decrementQuantity(Cart cart);
        void removeItem(Cart cart);
    }
    /*
    public void setCartOnClickInterface(CartOnClickInterface listener){
        this.listener = listener;
    }

     */


}