package contacts;

import java.io.Serializable;

public abstract class Records implements Serializable {

    public abstract Records create();

    public abstract void edit();

    public abstract String toString();

    public abstract String getNameList();

    public abstract String getPhoneNumber();
}
