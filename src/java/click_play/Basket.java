package click_play;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.util.UUID;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class Basket implements Serializable {

    private List<HelpBasket> helpList = new ArrayList<>();
    private double basketTotalPrice = 0;
    private Order basketOrder = new Order();
    MySqlConnection aConnection = new MySqlConnection();

    public Basket() {

    }

    public List<HelpBasket> getHelpList() {
        return helpList;
    }

    public void setHelpList(List<HelpBasket> helpList) {
        this.helpList = helpList;
    }

    public double getBasketTotalPrice() {
        return basketTotalPrice;
    }

    public void setBasketTotalPrice(double basketTotalPrice) {
        this.basketTotalPrice = basketTotalPrice;
    }

    public Order getBasketOrder() {
        return basketOrder;
    }

    public void setBasketOrder(Order basketOrder) {
        this.basketOrder = basketOrder;
    }

    ///////////////////////Functionality//////////////////////
    public void addToBasket(Movie m) {
        if (!contains(m) && m.getStock() > 0) {
            helpList.add(new HelpBasket(m, 1, m.getPrice()));
            basketTotalPrice += m.getPrice();
        }
    }

    public void incrimentUp(HelpBasket m) {
        if (m.getMovieH().getStock() > m.getQuantity()) {
            int newQuantity = m.getQuantity() + 1;
            m.setQuantity(newQuantity);
            basketTotalPrice += m.getMovieH().getPrice();
            m.localPrice += m.getMovieH().getPrice();
        }
    }

    public void incrementDown(HelpBasket m) {
        if (m.getQuantity() > 0) {
            int newQuantity = m.getQuantity() - 1;
            m.setQuantity(newQuantity);
            basketTotalPrice -= m.getMovieH().getPrice();
            m.localPrice -= m.getMovieH().getPrice();
            if (m.getQuantity() == 0) {
                removeItemFromBasket(m);
            }
        }
    }

    public void removeItemFromBasket(HelpBasket m) {
        basketTotalPrice -= m.getQuantity() * m.getMovieH().getPrice();
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

    public String proceed() {

        return "confirmOrder.xhtml";
    }

    public String makeOrder() throws SQLException {
        //totalPrice
        basketOrder.setTotalPrice(basketTotalPrice);
        //orderStatus
        basketOrder.setOrderStatus("New");
        //Generate Order Number
        basketOrder.setOrderID(generateOrderID());

        aConnection.connect();
        String sql = "INSERT INTO click_play.orders (orderID, firstName, lastName, street, zipCode, phoneNumber, email, totalPrice, orderStatus) "
                + "VALUES ('" + basketOrder.getOrderID() + "', '" + basketOrder.getFirstName() + "', '" + basketOrder.getLastName() + "', '" + basketOrder.getStreet() + "', '" + basketOrder.getZipCode() + "', '" + basketOrder.getPhoneNumber() + "', '" + basketOrder.getEmail() + "', '" + basketOrder.getTotalPrice() + "', '" + basketOrder.getOrderStatus() + "');";

        aConnection.executeStatement(sql);
        for (int i = 0; i < helpList.size(); i++) {
            HelpBasket movie = helpList.get(i);
            sql = "INSERT INTO click_play.order_products (orderID, movieID, quantity)"
                    + "VALUES ('" + basketOrder.getOrderID() + "', '" + movie.getMovieH().getId() + "', '" + movie.getQuantity() + "');";
            aConnection.executeStatement(sql);
            int newStock = movie.getMovieH().getStock() - movie.quantity;
            sql = "UPDATE click_play.movies SET stock = '" + newStock + "' WHERE title = '" + movie.getMovieH().getTitle() + "';";
            aConnection.executeStatement(sql);

        }

        aConnection.disconnect();
        return "madeOrder.xhtml";
    }

    public String generateOrderID() {
        return UUID.randomUUID().toString();

    }

    ///////////////Class///////////////////////////
    public class HelpBasket implements Serializable {

        private Movie movieH;
        private int quantity;
        private double localPrice;

        public HelpBasket(Movie m, int q , double p) {
            movieH = m;
            quantity = q;
            localPrice = p;
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

        public double getLocalPrice() {
            return localPrice;
        }

        public void setLocalPrice(double localPrice) {
            this.localPrice = localPrice;
        }

    }

}
