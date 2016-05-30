package click_play;

import click_play.MySqlConnection;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Named
@SessionScoped
public class Search implements Serializable {

    String searchString;
    List<Movie> searchList;
    List<Order> searchOrder;
    Order tempOrder;

    MySqlConnection aConnection = new MySqlConnection();

    public Search() {

    }

    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }

    public List<Movie> getSearchList() {
        return searchList;
    }

    public void setSearchList(List<Movie> searchList) {
        this.searchList = searchList;
    }

    public List<Order> getSearchOrder() {
        return searchOrder;
    }

    public void setSearchOrder(List<Order> searchOrder) {
        this.searchOrder = searchOrder;
    }

    public Order getTempOrder() {
        return tempOrder;
    }

    public void setTempOrder(Order tempOrder) {
        this.tempOrder = tempOrder;
    }

///////////////////////Functionality//////////////////////
    public String searchByName() throws SQLException {
        if (searchList == null) {
            searchList = new ArrayList<>();
        } else {
            searchList.clear();
        }

        aConnection.connect();
        String sql = "SELECT * FROM click_play.movies WHERE Title LIKE'%" + searchString + "%';";
        aConnection.executeStatement(sql);
        aConnection.resutSet();
        while (aConnection.getResultSet().next()) {
            Movie movie = new Movie();
            movie.setId(aConnection.getResultSet().getInt(1));
            movie.setTitle(aConnection.getResultSet().getString(2));
            movie.setCategoryM(aConnection.getResultSet().getString(3));
            movie.setPrice(aConnection.getResultSet().getDouble(4));
            movie.setDescription(aConnection.getResultSet().getString(5));
            movie.setStock(aConnection.getResultSet().getInt(6));
            movie.setImage(aConnection.getResultSet().getString(7));
            searchList.add(movie);
        }

        aConnection.disconnect();
        searchString="";
        return "search.xhtml";
    }

    public String searchByOrderNumber() throws SQLException {
        if (searchOrder == null) {
            searchOrder = new ArrayList<>();
        } else {
            searchOrder.clear();
        }

        if (searchList == null) {
            searchList = new ArrayList<>();
        } else {
            searchList.clear();
        }

        aConnection.connect();
        String sql = "SELECT * FROM click_play.orders where orderid = '" + searchString + "';";
        aConnection.executeStatement(sql);
        aConnection.resutSet();
        while (aConnection.getResultSet().next()) {
            Order aOrder = new Order();
            aOrder.setOrderID(aConnection.getResultSet().getString(2));
            aOrder.setFirstName(aConnection.getResultSet().getString(3));
            aOrder.setLastName(aConnection.getResultSet().getString(4));
            aOrder.setStreet(aConnection.getResultSet().getString(5));
            aOrder.setZipCode(aConnection.getResultSet().getString(6));
            aOrder.setPhoneNumber(aConnection.getResultSet().getString(7));
            aOrder.setEmail(aConnection.getResultSet().getString(8));
            aOrder.setTotalPrice(aConnection.getResultSet().getDouble(9));
            aOrder.setOrderStatus(aConnection.getResultSet().getString(10));
            searchOrder.add(aOrder);
        }

        sql = "SELECT * FROM click_play.order_products where orderid = '" + searchString + "';";
        aConnection.executeStatement(sql);
        aConnection.resutSet();
        while (aConnection.getResultSet().next()) {
            Movie aMovie = new Movie();
            aMovie.setId(aConnection.getResultSet().getInt(3));
            aMovie.setQuantity(aConnection.getResultSet().getInt(4));
            searchList.add(aMovie);
        }

        for (int i = 0; i < searchList.size(); i++) {
            sql = "SELECT * FROM click_play.movies WHERE id = " + searchList.get(i).getId();
            aConnection.executeStatement(sql);
            aConnection.resutSet();
            while (aConnection.getResultSet().next()) {
                searchList.get(i).setTitle(aConnection.getResultSet().getString(2));
                searchList.get(i).setCategoryM(aConnection.getResultSet().getString(3));
                searchList.get(i).setPrice(aConnection.getResultSet().getDouble(4));
                searchList.get(i).setDescription(aConnection.getResultSet().getString(5));
                searchList.get(i).setStock(aConnection.getResultSet().getInt(6));
                searchList.get(i).setImage(aConnection.getResultSet().getString(7));
            }
        }

        aConnection.disconnect();
        searchString="";
        return "order.xhtml";
    }

}
