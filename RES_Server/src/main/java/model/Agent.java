package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Agent database table.
 * 
 */
@Entity
@NamedQuery(name="Agent.findAll", query="SELECT a FROM Agent a")
public class Agent implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int agentid;

	private int hasApartment;

	public Agent() {
	}

	public int getAgentid() {
		return this.agentid;
	}

	public void setAgentid(int agentid) {
		this.agentid = agentid;
	}

	public int getHasApartment() {
		return this.hasApartment;
	}

	public void setHasApartment(int hasApartment) {
		this.hasApartment = hasApartment;
	}

}