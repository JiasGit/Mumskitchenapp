package dream.lab.mumskitchenapp.repositories;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import dream.lab.mumskitchenapp.Models.Cart;
import dream.lab.mumskitchenapp.Models.Dish;

public class CartRepo {
    private MutableLiveData<List<Cart>> mutableCart = new MutableLiveData<>();
    private boolean isAdded;
    private Context context;

    public LiveData<List<Cart>> getCart(){
        if(mutableCart.getValue() == null){
            initCart();
        }
        return mutableCart;
    }

    private void initCart() {
        mutableCart.setValue(new ArrayList<Cart>());
    }

    public boolean addItemToCart(Dish dish){
        if(mutableCart.getValue() == null){
            initCart();
        }
/*
        List<Cart> cartItemList = new ArrayList<>(mutableCart.getValue());
        Cart cartItem = new Cart(dish, 1);
        cartItemList.add(cartItem);
        mutableCart.setValue(cartItemList);
        return true;
 */


        List<Cart> cartItemList = new ArrayList<>(mutableCart.getValue());
        isAdded = false;
        for(int i = 0; i<cartItemList.size(); i++) {
            if (dish.getId() == cartItemList.get(i).getId()) {
                cartItemList.get(i).setQty(cartItemList.get(i).getQty() + 1);
                isAdded = true;
            }
        }
        mutableCart.setValue(cartItemList);

        if(isAdded==false) {
            Cart cartItem = new Cart(dish.getId(), dish.getDishName(), dish.getDishPrice(), dish.getImageURL(), 1);
            cartItemList.add(cartItem);
            mutableCart.setValue(cartItemList);
        }
        return true;


    }

    public void removeItemFromCart(Cart cart){
        if(mutableCart.getValue() == null){
            return;
        }
        List<Cart> cartList = new ArrayList<>(mutableCart.getValue());
        cartList.remove(cart);
        mutableCart.setValue(cartList);
    }

    public boolean incrementQty(Cart cart){
        List<Cart> cartList = new ArrayList<>(mutableCart.getValue());
        if(cart.getQty() <10){
            cart.setQty(cart.getQty() + 1);
            cartList.set(cartList.indexOf(cart), cart);
            mutableCart.setValue(cartList);
            return true;
        }
        return false;
    }

    public boolean decrementQty(Cart cart){
        List<Cart> cartList = new ArrayList<>(mutableCart.getValue());
        if(cart.getQty() > 1){
            cart.setQty(cart.getQty() - 1);
            cartList.set(cartList.indexOf(cart), cart);
            mutableCart.setValue(cartList);
            return true;
        }
        return false;
    }

}
