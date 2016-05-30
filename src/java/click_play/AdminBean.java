package click_play;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class AdminBean implements Serializable {

    private String allPurposeString;
    private Movie newMovie;
    private Admin adminTemp;
    private List<Order> adminOrderList;
    private List<Admin> adminList;
    private List<String> allCategoriesString;

    MySqlConnection aConnection = new MySqlConnection();

    public AdminBean() {
        this.adminOrderList = new ArrayList<>();
        this.newMovie = new Movie();
        this.allCategoriesString = new ArrayList<>();
        this.adminTemp = new Admin();
        this.adminList = new ArrayList<>();
    }

    public String getAllPurposeString() {
        return allPurposeString;
    }

    public void setAllPurposeString(String allPurposeString) {
        this.allPurposeString = allPurposeString;
    }

    public Movie getNewMovie() {
        return newMovie;
    }

    public void setNewMovie(Movie newMovie) {
        this.newMovie = newMovie;
    }

    public Admin getAdminTemp() {
        return adminTemp;
    }

    public void setAdminTemp(Admin adminTemp) {
        this.adminTemp = adminTemp;
    }

    public List<Order> getAdminOrderList() {
        return adminOrderList;
    }

    public void setAdminOrderList(List<Order> adminOrderList) {
        this.adminOrderList = adminOrderList;
    }

    public List<Admin> getAdminList() {
        return adminList;
    }

    public void setAdminList(List<Admin> adminList) {
        this.adminList = adminList;
    }

    public List<String> getAllCategoriesString() {
        return allCategoriesString;
    }

    public void setAllCategoriesString(List<String> allCategoriesString) {
        this.allCategoriesString = allCategoriesString;
    }

    //////////////Functionality//////////////////////
    ////////////////////////Order related Methods/////////////////
    public List<Order> getDBAllOrders() throws SQLException {
        adminOrderList.clear();
        aConnection.connect();
        String sql = "SELECT * FROM click_play.orders";
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
            adminOrderList.add(aOrder);
        }
        aConnection.disconnect();
        return adminOrderList;
    }

    public void updateOrderStatus(Order o) throws SQLException {
        aConnection.connect();
        String sql = "UPDATE click_play.orders SET orderStatus ='" + allPurposeString + "' WHERE firstName='" + o.getFirstName() + "';";
        aConnection.executeStatement(sql);
        aConnection.disconnect();
        allPurposeString = "";
    }

/////////////////////Movie related methods//////////////////////////
    public void removeOrder(Order o) throws SQLException {
        aConnection.connect();
        String sql = "DELETE FROM click_play.orders WHERE firstName='" + o.getFirstName() + "';";
        aConnection.executeStatement(sql);
        aConnection.disconnect();
        allPurposeString = "";
    }

    public void addNewMovie(Movie m) throws SQLException {
        aConnection.connect();
        String sql = "INSERT INTO click_play.movies (title, category, price, description, stock, image)"
                + "VALUES ('" + m.getTitle() + "', '" + m.getCategoryM() + "'," + m.getPrice() + ", '" + m.getDescription() + "' , '" + m.getStock() + "', '" + m.getImage() + "');";
        aConnection.executeStatement(sql);
        aConnection.disconnect();
    }

    public List<String> getCategorysFromDB() throws SQLException {
        allCategoriesString.clear();
        aConnection.connect();
        String sql = "SELECT * FROM click_play.category;";
        aConnection.executeStatement(sql);
        aConnection.resutSet();
        while (aConnection.getResultSet().next()) {
            String cat;
            cat = aConnection.getResultSet().getString(2);
            if (!cat.equals("All")) {
                allCategoriesString.add(cat);
            }
        }
        aConnection.disconnect();
        return allCategoriesString;
    }

    public void addNewCategory() throws SQLException {
        aConnection.connect();
        String sql = "INSERT INTO click_play.category (category)"
                + "VALUES ('" + allPurposeString + "');";
        aConnection.executeStatement(sql);
        aConnection.disconnect();
        allPurposeString = "";

    }

    public String showMovie(Movie m) {
        setNewMovie(m);
        return "movieEditMovie.xhtml";
    }

    public void editMovie(Movie m) throws SQLException {
        aConnection.connect();
        String sql = "UPDATE click_play.movies SET title ='" + m.getTitle() + "', category='" + m.getCategoryM() + "' ,stock='" + m.getStock() + "',price='" + m.getPrice() + "',image='" + m.getImage() + "',description='" + m.getDescription() + "' WHERE id=" + m.getId();
        aConnection.executeStatement(sql);
        aConnection.disconnect();

    }

    public void removeMovie(Movie m) throws SQLException {
        aConnection.connect();
        String sql = "DELETE FROM click_play.movies WHERE id=" + m.getId();
        aConnection.executeStatement(sql);
        aConnection.disconnect();

    }

///////////Admin Acount Methods///////////////////////////////
    public List<Admin> getDbAdmin() throws SQLException {
        if (adminList == null) {
            adminList = new ArrayList<>();
        } else {
            adminList.clear();
        }

        aConnection.connect();
        String sql = "SELECT * FROM click_play.admin";
        aConnection.executeStatement(sql);
        aConnection.resutSet();
        while (aConnection.getResultSet().next()) {
            Admin admin = new Admin();
            admin.setAdminId(aConnection.getResultSet().getInt(1));
            admin.setUserName(aConnection.getResultSet().getString(2));
            admin.setPassword(aConnection.getResultSet().getString(3));
            adminList.add(admin);
        }
        aConnection.disconnect();
        return adminList;
    }

    public void addAccount() throws SQLException {
        aConnection.connect();
        String sql = "INSERT INTO click_play.admin (username,password)"
                + "VALUES ('" + adminTemp.getPassword() + "', '" + adminTemp.getUserName() + "');";
        aConnection.executeStatement(sql);
        adminTemp.setPassword("");
        adminTemp.setUserName("");
        aConnection.disconnect();

    }

    public void removeAccount() throws SQLException {
        aConnection.connect();
        String sql = "DELETE FROM click_play.admin WHERE idadmin=" + adminTemp.getAdminId();
        aConnection.executeStatement(sql);
        adminTemp.setAdminId(0);
        aConnection.disconnect();

    }

    public void updatePasswordNew() throws SQLException {
        aConnection.connect();
        String sql;
        sql = "UPDATE click_play.admin SET password ='" + adminTemp.getPassword() + "' WHERE idadmin='" + adminTemp.getAdminId() + "';";
        aConnection.executeStatement(sql);
        aConnection.disconnect();
        adminTemp.setPassword("");
        adminTemp.setAdminId(0);
    }

}
