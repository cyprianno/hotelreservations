/**
 * 
 */
package pl.gapps.hotel.domain.dao.helper;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;
/**
 * @author cyprian
 * statyczne pobieranie PersistenceManagerFactory wg zalece� Google dla JDO
 */
public final class PMF {
    private static final PersistenceManagerFactory pmfInstance =
        JDOHelper.getPersistenceManagerFactory("transactions-optional");

    private PMF() {}

    /**
     * Instancja fabryki Persistence Manager (nale�y z niej p�niej pobra� PM)
     * @return pmfInstance
     */
    public static PersistenceManagerFactory get() {
        return pmfInstance;
    }
}