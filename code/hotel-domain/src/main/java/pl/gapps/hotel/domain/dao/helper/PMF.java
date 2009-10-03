/**
 * 
 */
package pl.gapps.hotel.domain.dao.helper;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;
/**
 * @author cyprian
 *
 */
public final class PMF {
    private static final PersistenceManagerFactory pmfInstance =
        JDOHelper.getPersistenceManagerFactory("transactions-optional");

    private PMF() {}

    public static PersistenceManagerFactory get() {
        return pmfInstance;
    }
}