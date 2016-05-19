package click_play;

import java.io.Serializable;
import java.sql.SQLException;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class Admin implements Serializable {

    private String userName;
    private String password;

    MySqlConnection aConnection = new MySqlConnection();

    public Admin() {

    }

    public void addAccount() {

    }

    public void removeAccount() {

    }

    public void setPassword() {

    }

    public void addMovie() throws SQLException {

    }

    public void removeMovie() {

    }

    public void editMovie() {

    }

    public void updateStock() {

    }

    public void updateDeliveryStatus() {

    }

    public void addCategory() {

    }
}
