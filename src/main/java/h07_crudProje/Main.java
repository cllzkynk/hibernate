package h07_crudProje;

public class Main {

	public static void main(String[] args) {
	
		
		CrudMetodlar metod=new CrudMetodlar();

		metod.sessionFactoryOlustur();
		
		//veritabanına personel ekle
		
//		metod.personelEkle("ömer", "tufan", 7700);
//		
//		metod.personelEkle("sulayman", "matkuliyev", 8800);
//		
//		metod.personelEkle("kursat", "turgut", 9900);
		
		
		//metod.idIlePersonelSil(182);
		
		
		//metod.idIleMaasGuncelle(188, 10000);
		
		metod.sil();
		metod.tumPersoneliListele();
		
		
		
		
	}

}
