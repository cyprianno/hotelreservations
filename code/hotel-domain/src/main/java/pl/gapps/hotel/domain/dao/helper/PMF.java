/**
 * 
 */
package pl.gapps.hotel.domain.dao.helper;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;
/**
 * @author cyprian
 * statyczne pobieranie PersistenceManagerFactory wg zaleceń Google dla JDO
 */
public final class PMF {
    private static final PersistenceManagerFactory pmfInstance =
        JDOHelper.getPersistenceManagerFactory("transactions-optional");

    private PMF() {}

    /**
     * Instancja fabryki Persistence Manager (należy z niej później pobrać PM)
     * @return pmfInstance
     */
    public static PersistenceManagerFactory get() {
        return pmfInstance;
    }
}