package h07_crudProje;

import java.util.List;
import java.util.Scanner;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;



public class CrudMetodlar {

	private static SessionFactory sf;
	
	
	public void sessionFactoryOlustur() {
		
		try {
		Configuration con = new Configuration().configure("hibernate.cfg.xml").
				addAnnotatedClass(Personel.class);
		
		
		sf = con.buildSessionFactory();
		
		
	
		}catch(Throwable e)    ////oturum açmak veya yanlış gidebilecek her şeyi kesinlikle ele almak 
                               //istediğiniz bir iş parçacığının en yüksek "tümünü yakala" düzeyi
		{ 
			System.out.println("Session factory oluşurken hata oluştu " + e);
			
			throw new ExceptionInInitializerError(e); // tercihen bunu yada üsttekini (başlatma hatası)
		}
		
		}
	
	
	// veritabanına bir personel ekleyen metod (insert)
	
	public void personelEkle (String ad, String soyad, int maas) {
		
		Session session = sf.openSession();
		
		Transaction tx = session.beginTransaction();
		
		try {
		Personel personel=new Personel(ad,soyad,maas);
		session.save(personel);
		
		tx.commit();
		
		System.out.println("veriler gitti");
		}catch(HibernateException e) {
			if(tx!=null) {
				tx.rollback();
			}
			e.printStackTrace(); //exception olan satırı göster uzun açıklama yap
			
		}finally { //herhalukarda yani catch olsun olmasın çalış
			session.close();
		}
		
		
	}
	
	// veritabanından personel silen metod(delete)
	
	
	public void idIlePersonelSil(long personelId ) {
		
        Session session = sf.openSession();
	    Transaction tx = session.beginTransaction();
	try {
	Personel personel=	session.get(Personel.class,personelId);
		
	if(personel==null) {
		System.out.println(personelId+" nolu kisinin kaydı bulunamamıştır");
		
	}else {
		session.delete(personel);
		tx.commit();
		System.out.println(personelId+ " nolu kişinin kaydı silinmiştir");
		System.out.println("silinen" + personel);
		
	}
	}catch(HibernateException e) {
		if(tx!=null) {
			tx.rollback();
		}
		e.printStackTrace(); //exception olan satırı göster uzun açıklama yap
		
	}finally { //herhalukarda yani catch olsun olmasın çalış
		session.close();
	}
	
	}
	
	
	//id ile bir kaydın maas bilgisini güncelleyen metod(update)
	
	
	public void idIleMaasGuncelle(long personelId, int maas) {
		Session session = sf.openSession();
	    Transaction tx = session.beginTransaction();
	
	    try {
	 Personel personel=   session.get(Personel.class,personelId );
		
	 if(personel==null) {
		 System.out.println(personelId+" nolu kisinin kaydı bulunamamıştır");
	 }else {
		 
		 personel.setMaas(maas); //set le tabloyu güncelleyebiliyoruz
		 tx.commit();
		 System.out.println(personelId + " nolu personelin yeni maası "+ maas);
		 
	 }
	 
	    }catch(HibernateException e) {
			if(tx!=null) {
				tx.rollback();
			}
			e.printStackTrace(); //exception olan satırı göster uzun açıklama yap
			
		}finally { //herhalukarda yani catch olsun olmasın çalış
			session.close();
		}
	 
		
	}
	
	//veritabanından tüm kayıtları listeleyen metod (READ) // bütün kişilerin listesini getirmek jpa ile uzun sürer,
	// bu yüzden HQL ile getirelim. select *  hql sorgusunda yazılmaz
	
	public void tumPersoneliListele() {
		Session session = sf.openSession();
	    Transaction tx = session.beginTransaction();
	
	  try  { 
	  List<Personel> personeller= session.createQuery("FROM Personel").getResultList();  
		
		personeller.stream().forEach((t)->System.out.println(t));
		tx.commit();
	
	}	catch(HibernateException e) {
		if(tx!=null) {
			tx.rollback();
		}
		e.printStackTrace(); //exception olan satırı göster uzun açıklama yap
		
	}finally { //herhalukarda yani catch olsun olmasın çalış
		session.close();
	}
	}
	
	
public void sil() {
		
		
		Session session = sf.openSession();
		 
		Transaction	tx = session.beginTransaction();
		
	   Scanner scan=new Scanner(System.in);
	   System.out.println("lütfen silmek istediğiniz kişinin id sini giriniz");
	   long no =scan.nextLong();

		
      Personel personel = session.get(Personel.class, no);
			
			if(personel == null) {
				System.out.println(no + " nolu kisinin kaydi bulunamamistir.");
			}else {
				session.delete(personel);
				tx.commit(); //önce silinmeyi kaydet sonra syso ile göster
				System.out.println(no + " nolu kisinin kaydi silinmistir.");
			}
			scan.close();
			session.close();
	}
	
	

}
