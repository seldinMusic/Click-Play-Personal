package click_play;

import java.io.Serializable;
import java.sql.SQLException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class Admin implements Serializable {

    private int id;
    private String title;
    private String category;
    private double price;
    private String description;
    private int stock;
    private String image;
    private String origin;
    private String date;
    private String director;

    private int removeid;       // for removing admin 
    private String removemovieid;
    private String removeorderid;

    private String loginusername;
    private String loginpass;
    private String loginusernameH;                                   //for the login
    private String loginpassH;

    private String newpass;             // for editing admin pass 

    private int usereditid;
    private String adminName;
    private String adminPass;                                    // adding nez admin 

    private String movieeditid;

    private String addcategory;

    private String newtitle;
    private String newimage;
    private int newstock;
    private String neworigin;
    private String newdirector;                // new for movies
    private String newdate;
    private double newprice;
    private String newdes;
    private String newcategory;

    private int ordereditid;
    private String newstatus;                 // editing order

    private String searshH;
    private String searsh;

    MySqlConnection aConnection = new MySqlConnection();

    public Admin() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {

        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void getMovie() throws SQLException {
        aConnection.connect();

        aConnection.disconnect();
    }

    public int getRemoveid() {
        return removeid;
    }

    public void setRemoveid(int removeid) {
        this.removeid = removeid;
    }

    public String getLoginusername() {
        return loginusername;
    }

    public void setLoginusername(String loginusername) {
        this.loginusername = loginusername;
    }

    public String getLoginpass() {
        return loginpass;
    }

    public void setLoginpass(String loginpass) {
        this.loginpass = loginpass;
    }

    public String getLoginusernameH() {
        return loginusernameH;
    }

    public void setLoginusernameH(String loginusernameH) {
        this.loginusernameH = loginusernameH;
    }

    public String getLoginpassH() {
        return loginpassH;
    }

    public void setLoginpassH(String loginpassH) {
        this.loginpassH = loginpassH;
    }

    public String getNewpass() {
        return newpass;
    }

    public void setNewpass(String newpass) {
        this.newpass = newpass;
    }

    public int getUsereditid() {
        return usereditid;
    }

    public void setUsereditid(int usereditid) {
        this.usereditid = usereditid;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminPass() {
        return adminPass;
    }

    public void setAdminPass(String adminPass) {
        this.adminPass = adminPass;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getRemovemovieid() {
        return removemovieid;
    }

    public void setRemovemovieid(String removemovieid) {
        this.removemovieid = removemovieid;
    }

    public String getMovieeditid() {
        return movieeditid;
    }

    public void setMovieeditid(String movieeditid) {
        this.movieeditid = movieeditid;
    }

    public String getNewcategory() {
        return newcategory;
    }

    public void setNewcategory(String newcategory) {
        this.newcategory = newcategory;
    }

    public String getNewtitle() {
        return newtitle;
    }

    public void setNewtitle(String newtitle) {
        this.newtitle = newtitle;
    }

    public String getNewimage() {
        return newimage;
    }

    public void setNewimage(String newimage) {
        this.newimage = newimage;
    }

    public int getNewstock() {
        return newstock;
    }

    public void setNewstock(int newstock) {
        this.newstock = newstock;
    }

    public String getNeworigin() {
        return neworigin;
    }

    public void setNeworigin(String neworigin) {
        this.neworigin = neworigin;
    }

    public String getNewdirector() {
        return newdirector;
    }

    public void setNewdirector(String newdirector) {
        this.newdirector = newdirector;
    }

    public String getNewdate() {
        return newdate;
    }

    public void setNewdate(String newdate) {
        this.newdate = newdate;
    }

    public double getNewprice() {
        return newprice;
    }

    public void setNewprice(double newprice) {
        this.newprice = newprice;
    }

    public String getNewdes() {
        return newdes;
    }

    public void setNewdes(String newdes) {
        this.newdes = newdes;
    }

    public int getOrdereditid() {
        return ordereditid;
    }

    public void setOrdereditid(int ordereditid) {
        this.ordereditid = ordereditid;
    }

    public String getNewstatus() {
        return newstatus;
    }

    public void setNewstatus(String newstatus) {
        this.newstatus = newstatus;
    }

    public String getRemoveorderid() {
        return removeorderid;
    }

    public void setRemoveorderid(String removeorderid) {
        this.removeorderid = removeorderid;
    }

    public String getAddcategory() {
        return addcategory;
    }

    public void setAddcategory(String addcategory) {
        this.addcategory = addcategory;
    }

    public String getSearshH() {
        return searshH;
    }

    public void setSearshH(String searshH) {
        this.searshH = searshH;
    }

    public String getSearsh() {
        return searsh;
    }

    public void setSearsh(String searsh) {
        this.searsh = searsh;
    }

    public void addAccount() throws SQLException {
        aConnection.connect();
        String sql = "INSERT INTO click_play.admin (username,password)"
                + "VALUES ('" + adminName + "', '" + adminPass + "');";
        aConnection.executeStatement(sql);
        aConnection.disconnect();

    }

    public void removeAccount() throws SQLException {
        aConnection.connect();
        String sql = "DELETE FROM click_play.admin WHERE `idadmin`=" + removeid;
        aConnection.executeStatement(sql);
        aConnection.disconnect();

    }

    public void setPassword() throws SQLException {
        aConnection.connect();
        String sql = "UPDATE click_play.admin SET password ='" + newpass + "' WHERE `idadmin`=" + usereditid;
        aConnection.executeStatement(sql);
        aConnection.disconnect();

    }

    public void addMovie() throws SQLException {
        aConnection.connect();
        String sql = "INSERT INTO click_play.movies (title, category, price, description, stock, image,director,origin,date)"
                + "VALUES ('" + title + "', '" + category + "'," + price + ", '" + description + "' , '" + stock + "', '" + image + "', '" + director + "', '" + origin + "', '" + date + "');";
        aConnection.executeStatement(sql);
        aConnection.disconnect();
    }

    public void removeMovie() throws SQLException {
        aConnection.connect();
        String sql = "DELETE FROM click_play.movies WHERE `idmovies`=" + removemovieid;
        aConnection.executeStatement(sql);
        aConnection.disconnect();

    }

    public void editMovie() throws SQLException {
        aConnection.connect();
        String sql = "UPDATE click_play.movies SET title ='" + newtitle + "', category='" + newcategory + "' ,stock='" + newstock + "',price='" + newprice + "',image='" + newimage + "',description='" + newdes + "',director='" + newdirector + "',origin='" + neworigin + "',date='" + newdate + "' WHERE `idmovies`=" + movieeditid;

        aConnection.executeStatement(sql);
        aConnection.disconnect();

    }

    public void updateDeliveryStatus() throws SQLException {
        aConnection.connect();
        String sql = "UPDATE click_play.orders SET orderstatus ='" + newstatus + "' WHERE `idorders`=" + ordereditid;
        aConnection.executeStatement(sql);
        aConnection.disconnect();

    }

    public void removeorder() throws SQLException {
        aConnection.connect();
        String sql = "DELETE FROM click_play.orders WHERE `idorders`=" + removeorderid;
        aConnection.executeStatement(sql);
        aConnection.disconnect();
    }

    public void addCategory() throws SQLException {
        aConnection.connect();
        String sql = "INSERT INTO click_play.category (categoryname)"
                + "VALUES ('" + addcategory + "');";
        aConnection.executeStatement(sql);
        aConnection.disconnect();

    }
}
