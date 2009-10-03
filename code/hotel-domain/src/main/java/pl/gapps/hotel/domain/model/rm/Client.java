/**
 * 
 */
package pl.gapps.hotel.domain.model.rm;

import java.io.Serializable;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 * @author cyprian
 *
 */
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Client implements Serializable{

	private static final long serialVersionUID = 6784784758364578541L;

	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Integer clientId;
	
	@Persistent
	private String firstName;
	
	@Persistent
	private String lastName;
	
	@Persistent
	private String documentNumber;
	
	@Persistent
	private String legitimationNumber;

    /* metody dostêpowe */
	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDocumentNumber() {
		return documentNumber;
	}

	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}

	public String getLegitimationNumber() {
		return legitimationNumber;
	}

	public void setLegitimationNumber(String legitimationNumber) {
		this.legitimationNumber = legitimationNumber;
	}
}
