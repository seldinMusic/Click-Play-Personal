package click_play;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class Category implements Serializable {

    private int id;
    private String categoryC;
    private List<Category> dbCategory;
    MySqlConnection aConnection = new MySqlConnection();

    public Category() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryC() {
        return categoryC;
    }

    public void setCategoryC(String categoryC) {
        this.categoryC = categoryC;
    }

    public List<Category> getDbCategory() {
        return dbCategory;
    }

    public void setDbCategory(List<Category> dbCategory) {
        this.dbCategory = dbCategory;
    }

    //////////////Functionality///////////////

    public List<Category> getCategorysFromDB() throws SQLException {
        if (dbCategory == null) {
            dbCategory = new ArrayList<>();
        }
        dbCategory.clear();
        aConnection.connect();
        String sql = "SELECT * FROM click_play.category;";
        aConnection.executeStatement(sql);
        aConnection.resutSet();
        while (aConnection.getResultSet().next()) {
            Category cat = new Category();
            cat.setId(aConnection.getResultSet().getInt(1));
            cat.setCategoryC(aConnection.getResultSet().getString(2));
            dbCategory.add(cat);
        }
        aConnection.disconnect();
        return dbCategory;
    }
}
