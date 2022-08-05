package dream.lab.mumskitchenapp.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class Cart implements Parcelable{
/*
    private Dish dish;
    private int quantity;

    public Cart(Dish dish, int quantity) {
        this.dish = dish;
        this.quantity = quantity;
    }

    protected Cart(Parcel in) {
        quantity = in.readInt();
    }

    public static final Creator<Cart> CREATOR = new Creator<Cart>() {
        @Override
        public Cart createFromParcel(Parcel in) {
            return new Cart(in);
        }

        @Override
        public Cart[] newArray(int size) {
            return new Cart[size];
        }
    };

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "dish=" + dish +
                ", quantity=" + quantity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return getQuantity() == cart.getQuantity() && getDish().equals(cart.getDish());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(quantity);
    }
    */


    private String id;
    private String dishName;
    private double dishPrice;
    private String imageURL;
    private int qty;

    public Cart(String id, String dishName, double dishPrice, String imageURL, int qty) {
        this.id = id;
        this.dishName = dishName;
        this.dishPrice = dishPrice;
        this.imageURL = imageURL;
        this.qty = qty;
    }

    protected Cart(Parcel in) {
        id = in.readString();
        dishName = in.readString();
        dishPrice = in.readDouble();
        imageURL = in.readString();
        qty = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(dishName);
        dest.writeDouble(dishPrice);
        dest.writeString(imageURL);
        dest.writeInt(qty);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Cart> CREATOR = new Creator<Cart>() {
        @Override
        public Cart createFromParcel(Parcel in) {
            return new Cart(in);
        }

        @Override
        public Cart[] newArray(int size) {
            return new Cart[size];
        }
    };

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

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id='" + id + '\'' +
                ", dishName='" + dishName + '\'' +
                ", dishPrice=" + dishPrice +
                ", imageURL='" + imageURL + '\'' +
                ", qty=" + qty +
                '}';
    }

}
