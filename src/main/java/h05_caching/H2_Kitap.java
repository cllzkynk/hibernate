package h05_caching;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
@Entity
@Table(name="kitaplar2")
@Cacheable
@Cache(region="H2_Kitap", usage=CacheConcurrencyStrategy.READ_WRITE)
public class H2_Kitap {
	
	@Id
	private int id;
	private String isim;
	
	@ManyToOne
	@JoinColumn(name="OGRENCİ")
	private H1_Ogrenci ogrenci;

	public H2_Kitap() {
		
	}
	
	public H2_Kitap(int id, String isim) {
		this.id = id;
		this.isim = isim;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIsim() {
		return isim;
	}

	public void setIsim(String isim) {
		this.isim = isim;
	}

	

	public H1_Ogrenci getOgrenci() {
		return ogrenci;
	}

	public void setOgrenci(H1_Ogrenci ogrenci) {
		this.ogrenci = ogrenci;
	}
	@Override
	public String toString() {
		return "Kitap id=" + id + ", isim=" + isim;
	}
}