package click_play;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

//@ManagedBean
@Named
@SessionScoped
public class Basket implements Serializable {

    private List<Movie> basketList = new ArrayList<>();
    private double totalPrice = 0;

    public Basket() {

    }

    public List<Movie> getBasketList() {
        return basketList;
    }

    public void setBasketList(List<Movie> basketList) {
        this.basketList = basketList;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    ///////////////////////Functionality//////////////////////
    public void inStock() {

    }

    public void addToBasket(Movie m) {
        int increment = 1;
        if (!basketList.contains(m)) {
            m.setQuantity(increment);
            basketList.add(m);
            totalPrice += m.getPrice();
        } else {
            totalPrice -= m.getQuantity() * m.getPrice();
            m.setQuantity(+increment);
            totalPrice += m.getQuantity() * m.getPrice();
        }
    }

    public void updateItemInBasket() {

    }

    public void removeItemFromBasket(Movie m) {
        totalPrice -= m.getPrice();
        basketList.remove(m);
    }

}
