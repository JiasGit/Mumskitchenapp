package dream.lab.mumskitchenapp.Models;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

public class Dish {

    private String id;
    private String dishName;
    private double dishPrice;
    private String imageURL;


    public Dish(String id, String dishName, double dishPrice, String imageURL) {
        this.id = id;
        this.dishName = dishName;
        this.dishPrice = dishPrice;
        this.imageURL = imageURL;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public double getDishPrice() {
        return dishPrice;
    }

    public void setDishPrice(double dishPrice) {
        this.dishPrice = dishPrice;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id='" + id + '\'' +
                ", dishName='" + dishName + '\'' +
                ", dishPrice=" + dishPrice +
                ", imageURL='" + imageURL + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dish dish = (Dish) o;
        return Double.compare(dish.getDishPrice(), getDishPrice()) == 0 && getId().equals(dish.getId()) && getDishName().equals(dish.getDishName()) && getImageURL().equals(dish.getImageURL());
    }


    public static DiffUtil.ItemCallback<Dish> itemCallback = new DiffUtil.ItemCallback<Dish>() {
        @Override
        public boolean areItemsTheSame(@NonNull Dish oldItem, @NonNull Dish newItem) {
            return oldItem.getId().equals(newItem.getId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Dish oldItem, @NonNull Dish newItem) {
            return oldItem.equals(newItem);
        }
    };
}
