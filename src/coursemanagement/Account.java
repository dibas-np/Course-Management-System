package coursemanagement;

import java.sql.PreparedStatement;

/**
 *
 * @author Dibas Sigdel
 */
public abstract class Account {

    String name;
    int ID;
    PreparedStatement pst;

    abstract boolean isInDatabase(int id);

}
