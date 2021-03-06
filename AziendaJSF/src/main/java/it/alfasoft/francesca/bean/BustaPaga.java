package it.alfasoft.francesca.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import it.alfasoft.francesca.dao.DipendenteDao;
import it.alfasoft.francesca.utility.IsValid;

import javax.faces.bean.ManagedBean;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@ManagedBean(name="busta", eager=true)
public class BustaPaga implements IsValid, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id_BustaPaga;
	
	@ManyToOne
	private DipendenteBean dipendente;
	
	private Date data;
	private long importo;
	
	public BustaPaga() {
	}

	public long getId_BustaPaga() {
		return id_BustaPaga;
	}

	public void setId_BustaPaga(long id_BustaPaga) {
		this.id_BustaPaga = id_BustaPaga;
	}
	
	public DipendenteBean getDipendente() {
		return dipendente;
	}
	
	public void setDipendente(DipendenteBean dipendente) {
		this.dipendente = dipendente;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public boolean isValid() {
		boolean result=false;
		DipendenteDao ddao=new DipendenteDao();
		List<DipendenteBean> lista=ddao.getTuttiDipendenti();
		if(lista.contains(dipendente) && importo<=dipendente.getStipendio())
		{
			result=true;
		}
		return result;
	}

}
