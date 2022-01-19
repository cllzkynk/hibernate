package h01_anotasyonlar;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;



public class H3_Fetch {
	public static void main(String[] args) {
		
		
	SessionFactory sf=	new Configuration().configure("hibernate.cfg.xml")
		.addAnnotatedClass(H1_Sehir.class).buildSessionFactory();
		
	Session session =sf.openSession();
	Transaction tx=session.beginTransaction();
	
	
	//sehir_tablosundan istenilen id li kişiyi getir
	
	System.out.println(session.get(H1_Sehir.class,34)); //select * from sehir_tablosu where sehir_plaka=34
	
    System.out.println(session.get(H1_Sehir.class, 35).getSehirNufus());	
    //select sehirNufus from sehir_tablosu where sehirPlaka=35
		tx.commit();
		
		sf.close();
		session.close();
	}

}
