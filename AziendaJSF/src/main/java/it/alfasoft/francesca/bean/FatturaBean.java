package it.alfasoft.francesca.bean;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.xml.bind.annotation.XmlRootElement;

@ManagedBean(name="fattura", eager=true)
@XmlRootElement
public class FatturaBean {
	
	private Date dataEmissione;
	private double importo;
	private String codiceFattura;
	
	public FatturaBean() {
	}

	public Date getDataEmissione() {
		return dataEmissione;
	}

	public void setDataEmissione(Date dataEmissione) {
		this.dataEmissione = dataEmissione;
	}

	public double getImporto() {
		return importo;
	}

	public void setImporto(double importo) {
		this.importo = importo;
	}

	public String getCodiceFattura() {
		return codiceFattura;
	}

	public void setCodiceFattura(String codiceFattura) {
		this.codiceFattura = codiceFattura;
	}
	
}
