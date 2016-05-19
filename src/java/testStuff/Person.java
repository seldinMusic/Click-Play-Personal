package testStuff;

import java.io.Serializable;
import java.util.Map;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Named;

//@ManagedBean
@Named
@SessionScoped
public class Person implements Serializable {

    private String name;
    private String country;
    DisplayPerson dp = new DisplayPerson();

    public Person() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    
    public String outcome(){
        
        FacesContext fc = FacesContext.getCurrentInstance();
        this.country = getCountryParam(fc);
        
        return "page2";
    }
    
    public String getCountryParam(FacesContext fc){
        
        Map<String, String> parms = fc.getExternalContext().getRequestParameterMap();
        return parms.get("country");
    }

}
