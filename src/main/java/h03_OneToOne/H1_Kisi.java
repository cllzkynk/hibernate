package h03_OneToOne;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="kisiler")
 public class H1_Kisi {
	
	@Id //@id kelimesi buradan foreign key olacaklar (ortaklık buradan demek)
	@Column(name="kisi_id") //sql de gözükecek isim
	private int kisiId; //javada gözükecek isim
	
	@Column(name="kisi_adi")
	private String kisiAd;
	
	private int kisiYas;
	
	//tablonun içine gömme yapsaydık emmeddable derdik, biz iki tabloyu birebir ilişkilendiriyoruz=> OnetoOne 
	
	@OneToOne(mappedBy="kisi") //buradan kişi ismiyle (vize) git==>H2_Gunluk class ına (orada kisi ismiyle obje bekliyor)
	private H2_Gunluk gunluk; //gidilecek class tan burada da bir temsilci olmalı, istersek burada istersek 
//id+yazılar                  //diğer classta birleştirme yapabilelim diye. bu soruda H2_Gunlukte birleştirme yaptık,
	                         //buradaki bilgileri kisi objesiyle gunlukte de gösterdik.tostring de kisiyi günlükte gösterdik
	
	public H1_Kisi() { 
		
	}
							public H1_Kisi(int kisiId, String kisiAd, int kisiYas) {
								
								this.kisiId = kisiId;
								this.kisiAd = kisiAd;
								this.kisiYas = kisiYas;
							}
							public int getKisiId() {
								return kisiId;
							}
							public void setKisiId(int kisiId) {
								this.kisiId = kisiId;
							}
							public String getKisiAd() {
								return kisiAd;
							}
							public void setKisiAd(String kisiAd) {
								this.kisiAd = kisiAd;
							}
							public int getKisiYas() {
								return kisiYas;
							}
							public void setKisiYas(int kisiYas) {
								this.kisiYas = kisiYas;
							}
							

	

	                          //bu class ta günlük class ını çağırdım bir sonraki soruda sadece kitap listesi çağırdım
		
	@Override
	public String toString() { 
		return "H1_Kisi :kisiId=" + kisiId + ", kisiAd=" + kisiAd + ", kisiYas=" + kisiYas;
								//ya burada günlük olacak ya günlük toString inde ogrenci. ikisi olursa stackoverflowerror bellek dolu hatası,
                                //birde ikisine de eklesek, burada gunluk çağırılacak, gunluğe git ögrenci çağırılacak, 
		                          //sonsuz döngü olur.   ONETOONE DA HANGİSİ PARENT FARKETMEZ
							}	                 
	
}

