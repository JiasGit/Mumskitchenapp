package dream.lab.mumskitchenapp.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import dream.lab.mumskitchenapp.Models.Dish;

public class DishRepo {

    private MutableLiveData<List<Dish>> mutableDishList;

    public LiveData<List<Dish>> getDishHot(){

            mutableDishList = new MutableLiveData<>();
            loadDishHot();
        return mutableDishList;
    }
    public LiveData<List<Dish>> getDishMeat(){

            mutableDishList = new MutableLiveData<>();
            loadDishMeat();

        return mutableDishList;
    }
    public LiveData<List<Dish>> getDishVege(){

        mutableDishList = new MutableLiveData<>();
        loadDishVege();

        return mutableDishList;
    }

    public LiveData<List<Dish>> getDishOthers(){

        mutableDishList = new MutableLiveData<>();
        loadDishOthers();

        return mutableDishList;
    }



    private void loadDishHot() {
        List<Dish> dishList = new ArrayList<>();
        dishList.add(new Dish("1","Fried Chichen", 25.0, "https://thumbs.dreamstime.com/b/chinese-food-7881413.jpg"));
        dishList.add(new Dish("2","Sesame Lamb", 40.0, "https://thumbs.dreamstime.com/b/chinese-food-detail-plate-sesame-garnished-lamb-vegetables-31340748.jpg"));
        dishList.add(new Dish("3","Pork", 29.0,"https://thumbs.dreamstime.com/b/chinese-food-12338538.jpg"));
        dishList.add(new Dish("4","TouFu", 15.0,"https://images.squarespace-cdn.com/content/v1/5c844f05fb22a510b5fa6c1d/1585898422724-FWMM8QXQBFD3AU2PKP53/Canva+-+Tofu%2C+and+traditional+Japanese+food.+%281%29.jpg"));
        dishList.add(new Dish("5","Stir fry Shrimp", 30.0, "https://thumbs.dreamstime.com/b/stir-fry-shrimp-broccoli-garlic-chinese-food-clos-stir-fry-shrimp-broccoli-garlic-chinese-food-closeup-103244318.jpg"));
        dishList.add(new Dish("6","BBQ Salmon", 39.0, "https://thumbs.dreamstime.com/b/chinese-food-22555216.jpg"));
        dishList.add(new Dish("7","Seafood", 42.0, "https://thumbs.dreamstime.com/b/chinese-food-28822130.jpg"));
        dishList.add(new Dish("8","Spring Roll", 25.0, "https://thumbs.dreamstime.com/b/chinese-food-8157531.jpg"));
        dishList.add(new Dish("9","Fried Rice", 20.0, "https://thumbs.dreamstime.com/b/schezwan-chicken-fried-rice-black-bowl-dark-slate-background-indo-chinese-cuisine-dish-schezwan-chicken-fried-rice-black-159831808.jpg"));
        mutableDishList.setValue(dishList);
    }
    private void loadDishMeat() {
        List<Dish> dishList = new ArrayList<>();
        dishList.add(new Dish("5","Stir fry Shrimp", 30.0, "https://thumbs.dreamstime.com/b/stir-fry-shrimp-broccoli-garlic-chinese-food-clos-stir-fry-shrimp-broccoli-garlic-chinese-food-closeup-103244318.jpg"));
        dishList.add(new Dish("6","BBQ Salmon", 39.0, "https://thumbs.dreamstime.com/b/chinese-food-22555216.jpg"));
        dishList.add(new Dish("7","Seafood", 42.0, "https://thumbs.dreamstime.com/b/chinese-food-28822130.jpg"));
        dishList.add(new Dish("1","Fried Chichen", 25.0, "https://thumbs.dreamstime.com/b/chinese-food-7881413.jpg"));
        dishList.add(new Dish("2","Sesame Lamb", 40.0, "https://thumbs.dreamstime.com/b/chinese-food-detail-plate-sesame-garnished-lamb-vegetables-31340748.jpg"));
        dishList.add(new Dish("3","Pork", 29.0,"https://thumbs.dreamstime.com/b/chinese-food-12338538.jpg"));
        mutableDishList.setValue(dishList);
    }
    private void loadDishVege() {
        List<Dish> dishList = new ArrayList<>();

        dishList.add(new Dish("4","TouFu", 15.0,"https://images.squarespace-cdn.com/content/v1/5c844f05fb22a510b5fa6c1d/1585898422724-FWMM8QXQBFD3AU2PKP53/Canva+-+Tofu%2C+and+traditional+Japanese+food.+%281%29.jpg"));

        mutableDishList.setValue(dishList);
    }
    private void loadDishOthers() {
        List<Dish> dishList = new ArrayList<>();

        dishList.add(new Dish("8","Spring Roll", 25.0, "https://thumbs.dreamstime.com/b/chinese-food-8157531.jpg"));
        dishList.add(new Dish("9","Fried Rice", 20.0, "https://thumbs.dreamstime.com/b/schezwan-chicken-fried-rice-black-bowl-dark-slate-background-indo-chinese-cuisine-dish-schezwan-chicken-fried-rice-black-159831808.jpg"));
        mutableDishList.setValue(dishList);
    }
}
