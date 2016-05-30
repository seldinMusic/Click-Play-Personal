package click_play;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class MovieBean implements Serializable {

    private Movie tempMovie;
    private String beanCategory;
    private List<Movie> mBeanList;
    MySqlConnection aConnection = new MySqlConnection();

    public MovieBean() {

    }

    public Movie getTempMovie() {
        return tempMovie;
    }

    public void setTempMovie(Movie tempMovie) {
        this.tempMovie = tempMovie;
    }

    public String getBeanCategory() {
        return beanCategory;
    }

    public void setBeanCategory(String beanCategory) {
        this.beanCategory = beanCategory;
    }

    public List<Movie> getmBeanList() {
        return mBeanList;
    }

    public void setmBeanList(List<Movie> mBeanList) {
        this.mBeanList = mBeanList;
    }

    /////////////////////Functionality//////////////////////
    public String showMovie(Movie m) {
        setTempMovie(m);
        return "movie.xhtml";
    }

    public List<Movie> getDbMovies() throws SQLException {
        if (mBeanList == null) {
            mBeanList = new ArrayList<>();
        }
        mBeanList.clear();

        aConnection.connect();
        String sql = "SELECT * FROM click_play.movies";
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
            mBeanList.add(movie);
        }
        aConnection.disconnect();
        return mBeanList;
    }

    public List<Movie> getMoviesByCategory() throws SQLException {
        if (mBeanList == null) {
            mBeanList = new ArrayList<>();
        }
        mBeanList.clear();
        aConnection.connect();
        String sql;
        if (beanCategory == null) {
            beanCategory = "All";
        }

        if (beanCategory.equals("All")) {
            sql = "SELECT * FROM click_play.movies";
        } else {
            sql = "SELECT * FROM click_play.movies WHERE category = '" + beanCategory + "'";
        }

        aConnection.executeStatement(sql);

        aConnection.resutSet();

        while (aConnection.getResultSet()
                .next()) {
            Movie movie = new Movie();
            movie.setId(aConnection.getResultSet().getInt(1));
            movie.setTitle(aConnection.getResultSet().getString(2));
            movie.setCategoryM(aConnection.getResultSet().getString(3));
            movie.setPrice(aConnection.getResultSet().getDouble(4));
            movie.setDescription(aConnection.getResultSet().getString(5));
            movie.setStock(aConnection.getResultSet().getInt(6));
            movie.setImage(aConnection.getResultSet().getString(7));
            mBeanList.add(movie);
        }

        aConnection.disconnect();
        return mBeanList;
    }
}
