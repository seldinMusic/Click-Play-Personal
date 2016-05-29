package click_play;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class AdminModAcc implements Serializable {

    private int adminId;
    private String userName;
    private String password;
    private List<AdminModAcc> adminList;

    MySqlConnection aConnection = new MySqlConnection();

    public AdminModAcc() {

    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<AdminModAcc> getAdminList() {
        return adminList;
    }

    public void setAdminList(List<AdminModAcc> adminList) {
        this.adminList = adminList;
    }

    //////////////Functionality///////////////////
    public List<AdminModAcc> getDbAdmin() throws SQLException {
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
            AdminModAcc admin = new AdminModAcc();
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
                + "VALUES ('" + password + "', '" + userName + "');";
        aConnection.executeStatement(sql);
        password = "";
        userName = "";
        aConnection.disconnect();

    }

    public void removeAccount() throws SQLException {
        aConnection.connect();
        String sql = "DELETE FROM click_play.admin WHERE `idadmin`=" + adminId;
        aConnection.executeStatement(sql);
        adminId = 0;
        aConnection.disconnect();

    }

    public void updatePassword() throws SQLException {
        aConnection.connect();
        String sql = "UPDATE click_play.admin SET password ='" + getPassword() + "' WHERE `idadmin`=" + getAdminId();
        aConnection.executeStatement(sql);
        password = "";
        adminId = 0;
        aConnection.disconnect();

    }

}
