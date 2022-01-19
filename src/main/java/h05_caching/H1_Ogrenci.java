package h05_caching;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


@Entity
@Table(name="ogrenciler2")
@Cacheable //javaya bildiriyorum cach in açıldığını
@Cache(region="H1_Ogrenci", usage=CacheConcurrencyStrategy.READ_WRITE)//hibernate e bildiriyorum cache yi aç 
 public class H1_Ogrenci {
	
	@Id
	private int ogrId;
	
	private String ogrAd;
	
	private int ogrNot;
	
	//Parent tablodan silme
			//------------------------------------------
			// Exception olmaksizin silmek icin 
			//  A) Once Child sonra parent silinir.
			//  B) SQL deki gibi Cascade ozelligi aktif hale getirilir. 
			// (@OneToMany(mappedBy = "ogrenci", orphanRemoval = true, cascade = CascadeType.ALL) 

	// Child'ı silmeden Parent silmek icin ==>> orphanRemoval=true, cascade = CascadeType.ALL
	// Getirme islemleri icin ===> fetch = FetchType.EAGER veya fetch = FetchType.LAZY 
	@OneToMany(mappedBy="ogrenci",orphanRemoval=true, cascade = CascadeType.ALL)
	private List <H2_Kitap> kitapListesi ;

	public H1_Ogrenci() {
	
	}
	
	public H1_Ogrenci(int ogrId, String ogrAd, int ogrNot) {
		this.ogrId = ogrId;
		this.ogrAd = ogrAd;
		this.ogrNot = ogrNot;
	}

	public int getOgrId() {
		return ogrId;
	}

	public void setOgrId(int ogrId) {
		this.ogrId = ogrId;
	}

	public String getOgrAd() {
		return ogrAd;
	}

	public void setOgrAd(String ogrAd) {
		this.ogrAd = ogrAd;
	}

	public int getOgrNot() {
		return ogrNot;
	}

	public void setOgrNot(int ogrNot) {
		this.ogrNot = ogrNot;
	}

	public List<H2_Kitap> getKitapListesi() {
		return kitapListesi;
	}

	public void setKitapListesi(List<H2_Kitap> kitapListesi) {
		this.kitapListesi = kitapListesi;
	}

	@Override
	public String toString() {
		return "Ogrenci ogrId=" + ogrId + ", ogrAd=" + ogrAd + ", ogrNot=" + ogrNot;
	}
//	@Override
//	public String toString() {
//		return "Ogrenci ogrId=" + ogrId + ", ogrAd=" + ogrAd + ", ogrNot=" + ogrNot + ", kitapListesi="
//				+ kitapListesi + "]";
//	}
	
}
