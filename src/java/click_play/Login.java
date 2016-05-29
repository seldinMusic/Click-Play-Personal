package click_play;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class Login implements Serializable {

    private String loginusername;
    private String loginpass;
    private String loginusernameH;
    private String loginpassH;

    MySqlConnection aConnection = new MySqlConnection();

    public Login() {

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

    public void autenticate() {

    }
    
    public String sumup2 () throws SQLException {
      if (sumup()){
          return "MainAdmin.xhtml";
      }else{
          return "login.xhtml";
      }
        
    }

    public boolean sumup() throws SQLException {
        boolean r = false;
        if ((compareusername() == true) && (comparepass() == true)) {
            r = true;
        }
        return r;
    }

    public List<Login> getadmins() throws SQLException {
        List<Login> result = new ArrayList<>();
        aConnection.connect();
        String sql = "SELECT * FROM click_play.admin";
        aConnection.executeStatement(sql);
        aConnection.resutSet();
        while (aConnection.getResultSet().next()) {
            Login admin = new Login();
            admin.setLoginusername(aConnection.getResultSet().getString(2));
            admin.setLoginpass(aConnection.getResultSet().getString(3));

            result.add(admin);
        }
        aConnection.disconnect();
        return result;
    }

    int o = 0;

    public boolean compareusername() throws SQLException {

        int l = getLoginusernameH().length();

        boolean r = true;

        for (int i = 0; i < getadmins().size(); i++) {

            int l1 = getadmins().get(i).loginusername.length();

            if (l1 == l) {
                for (int j = 0; j < l; j++) {
                    if (getLoginusernameH().charAt(j) != getadmins().get(i).loginusername.charAt(j)) {
                        r = false;

                    }
                    o = i;
                }

            }
            System.out.print(o);

        }
        return r;

    }

    public boolean comparepass() throws SQLException {

        int l = getLoginpassH().length();
        boolean r = true;
        int l1 = getadmins().get(o).loginpass.length();

        if (l1 == l) {
            for (int j = 0; j < l; j++) {
                if (getLoginpassH().charAt(j) != getadmins().get(o).loginpass.charAt(j)) {
                    r = false;
                }

            }
        } else {
            r = false;
        }

        return r;

    }
}
