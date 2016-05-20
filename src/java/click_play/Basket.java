package click_play;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;

//@ManagedBean
@Named
@SessionScoped
public class Basket implements Serializable {

    private List<HelpBasket> helpList = new ArrayList<>();
    private double totalPrice = 0;

    public Basket() {

    }

    public List<HelpBasket> getHelpList() {
        return helpList;
    }

    public void setHelpList(List<HelpBasket> helpList) {
        this.helpList = helpList;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    ///////////////////////Functionality//////////////////////
    public void addToBasket(Movie m) {
        if (!contains(m) && m.getStock() > 0) {
            helpList.add(new HelpBasket(m, 1));
            totalPrice += m.getPrice();
        }
    }

    public void incrimentUp(HelpBasket m) {
        if (m.getMovieH().getStock() > m.getQuantity()) {
            int newQuantity = m.getQuantity() + 1;
            m.setQuantity(newQuantity);
            totalPrice += m.getMovieH().getPrice();
        }
    }

    public void incrementDown(HelpBasket m) {
        if (m.getQuantity() > 0) {
            int newQuantity = m.getQuantity() - 1;
            m.setQuantity(newQuantity);
            totalPrice -= m.getMovieH().getPrice();
            if (m.getQuantity() == 0) {
                removeItemFromBasket(m);
            }
        }
    }

    public void removeItemFromBasket(HelpBasket m) {
        totalPrice -= m.getQuantity() * m.getMovieH().getPrice();
        helpList.remove(m);
    }

    private boolean contains(Movie m) {
        for (int i = 0; i < helpList.size(); i++) {
            if (helpList.get(i).getMovieH().getId() == m.getId()) {
                return true;
            }
        }
        return false;
    }

    public class HelpBasket implements Serializable {

        private Movie movieH;
        private int quantity;

        public HelpBasket(Movie m, int q) {
            movieH = m;
            quantity = q;
        }

        public Movie getMovieH() {
            return movieH;
        }

        public void setMovieH(Movie movieH) {
            this.movieH = movieH;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

    }

}
