package h06_ManyToMany;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class H4_Fetch {

	public static void main(String[] args) {
		
		Configuration con = new Configuration().configure("hibernate.cfg.xml").
				addAnnotatedClass(H1_Ogrenci.class).addAnnotatedClass(H2_Kitap.class);
		SessionFactory sf = con.buildSessionFactory();
		Session session = sf.openSession();
		
		Transaction tx = session.beginTransaction();
	
		
	//1) id si 1111 	olan öğrenciyi getir 
		
	System.out.println("1111 no lu öğrenci "+session.get(H1_Ogrenci.class,1111));	
		
	//2) id si 101	olan kitabı getir
	
  System.out.println(" 101 no lu kitap"+session.get(H2_Kitap.class,101));	
		
	//3) adı "math book" olan kitabı getir	
		
	String hqlQuery1=	"FROM H2_Kitap k WHERE k.book='math book'";
		
	System.out.println("math "+session.createQuery(hqlQuery1).getSingleResult());	 // getSingleResult= tek satır döndürecekse tercih edilir
		

	//4)Adı "Ali Can" olan öğrenciyi getirin. Kitapları da görmek istiyorum.HQL yazıyoruz, from classismi yazılır.
	//herşey isteniyorsa select* gerek yok
	
	String hqlQuery2=	"FROM H1_Ogrenci o WHERE o.name='Ali Can'";
	
	Object sorgu2=session.createQuery(hqlQuery2).getSingleResult();
	System.out.println("Ali Can "+sorgu2);
	
	
	// 5) sadece student isimlerini getir
	
	String hqlQuery3= "SELECT o.name FROM H1_Ogrenci o";
	
List<H1_Ogrenci> sorgu3=	session.createQuery(hqlQuery3).getResultList();
	
	System.out.println("ogrenciler "+sorgu3);
	
	//6) kitap isimlerini getir
	
System.out.println(session.createQuery("SELECT k.book FROM H2_Kitap k").getResultList());	
	
tx.commit();
	}

}
