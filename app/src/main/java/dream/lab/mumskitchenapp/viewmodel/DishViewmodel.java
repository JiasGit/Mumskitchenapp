package dream.lab.mumskitchenapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import dream.lab.mumskitchenapp.Models.Cart;
import dream.lab.mumskitchenapp.Models.Dish;
import dream.lab.mumskitchenapp.repositories.CartRepo;
import dream.lab.mumskitchenapp.repositories.DishRepo;

public class DishViewmodel extends ViewModel {

    DishRepo dishRepo = new DishRepo();
    CartRepo cartRepo = new CartRepo();

    public LiveData<List<Dish>> getDishHot() {
        return dishRepo.getDishHot();
    }
    public LiveData<List<Dish>> getDishMeat() {
        return dishRepo.getDishMeat();
    }
    public LiveData<List<Dish>> getDishVege() { return dishRepo.getDishVege();}
    public LiveData<List<Dish>> getDishOthers() { return dishRepo.getDishOthers();}

    public LiveData<List<Cart>> getCart(){
        return cartRepo.getCart();
    }

    public boolean addItemToCart(Dish dish){
        return cartRepo.addItemToCart(dish);
    }

    public void removeItemFromCart(Cart cart){ cartRepo.removeItemFromCart(cart);}

    public boolean incrementQty(Cart cart){ return cartRepo.incrementQty(cart);}

    public boolean decrementQty(Cart cart){ return cartRepo.decrementQty(cart);}
}
