package click_play;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class Login implements Serializable{
    
    private String userName;
    private String password;
    
    public Login(){
        
    }
    
    public void autenticate() {
        
    }
}
