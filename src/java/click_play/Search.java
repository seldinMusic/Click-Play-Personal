package click_play;

import click_play.MySqlConnection;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//@ManagedBean
@Named
@SessionScoped
public class Search implements Serializable {

    String searchString;
    List<Movie> searchList;
    List<Order> searchOrder;

    MySqlConnection aConnection = new MySqlConnection();
    Movie movie = new Movie();

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

///////////////////////Functionality//////////////////////
    public String searchByName() throws SQLException {
        if (searchList == null) {
            searchList = new ArrayList<>();
        }
        searchList.clear();
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
        return "search.xhtml";
    }

    public void searchByOrderNumber() {

    }

}
