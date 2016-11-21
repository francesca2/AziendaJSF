package it.alfasoft.francesca.bean;

import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@ManagedBean(name="bustaWS", eager=true)
@XmlRootElement
public class BustaPagaWS implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id_BustaPagaWS;
	
	
	private Date data;
	private long importo;
	private String codiceBusta;
	
	public BustaPagaWS() {
	}

	public BustaPagaWS(Date data, long importo, String codiceBusta) {
		this.data = data;
		this.importo = importo;
		this.codiceBusta = codiceBusta;
	}

	public long getId_BustaPagaWS() {
		return id_BustaPagaWS;
	}

	public void setId_BustaPagaWS(long id_BustaPagaWS) {
		this.id_BustaPagaWS = id_BustaPagaWS;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public long getImporto() {
		return importo;
	}

	public void setImporto(long importo) {
		this.importo = importo;
	}

	public String getCodiceBusta() {
		return codiceBusta;
	}

	public void setCodiceBusta(String codiceBusta) {
		this.codiceBusta = codiceBusta;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
