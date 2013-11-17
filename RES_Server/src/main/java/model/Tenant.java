package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Tenant database table.
 * 
 */
@Entity
@NamedQuery(name="Tenant.findAll", query="SELECT t FROM Tenant t")
public class Tenant implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int tenantid;

	public Tenant() {
	}

	public int getTenantid() {
		return this.tenantid;
	}

	public void setTenantid(int tenantid) {
		this.tenantid = tenantid;
	}

}