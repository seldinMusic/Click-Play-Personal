package testStuff;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;

//@ManagedBean
@Named
@SessionScoped
public class DisplayPerson implements Serializable {

    List<Person> dList = new ArrayList<Person>();

    public DisplayPerson() {

    }

    public List<Person> getdList() {
        return dList;
    }

    public void setdList(List<Person> dList) {
        this.dList = dList;
    }

    public void addToList(Person p) {
        dList.add(p);
    }

    public void removeFromList(Person p) {
        dList.remove(p);
    }
}
