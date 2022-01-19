package h02_embeddable;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import h01_anotasyonlar.H1_Sehir;

public class H3_SaveFetch {

	public static void main(String[] args) {
		
		SessionFactory sf=	new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(H1_Ogrenci.class).buildSessionFactory();
				
			Session session =sf.openSession();
			Transaction tx=session.beginTransaction();
		
		
//	H2_Dersler ders1= new H2_Dersler("MUZIK","MATEMATİK");
//			
//		H1_Ogrenci ogrenci1=new H1_Ogrenci(111,"Islam Gunduz",100,ders1);	
//			
//		session.save(ogrenci1);
//		
//		H1_Ogrenci ogrenci2=new H1_Ogrenci(222,"Kursat Turgut",99,new H2_Dersler ("BEDEN", "FIZIK"));
//			
//		session.save(ogrenci2);
//		
		
		//Fetch işlemi 
		

//	System.out.println("ogrenci1 "+ session.get(H1_Ogrenci.class,111)	);	
//			
//			
//		Scanner scan=new Scanner(System.in);	
//			
//		System.out.println("silmek istediğiniz no yu giriniz");	
//		
//		int no =scan.nextInt();
//			
//		
//		session.delete(session.get(H1_Ogrenci.class, no));
		
		session.get(H1_Ogrenci.class, 222).setAd("ıslam");
	
		
		//scan.close();
		sf.close();
		session.close();
		
		
		
		
		
		
tx.commit();
	}

}
