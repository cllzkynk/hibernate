package h05_caching;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import org.hibernate.cfg.Configuration;


public class H4_Fetch {
	

	// ==================== HIBERNATE CACHING MEKANİZMASI =============================
	/* 	Hibernate'te default olarak L1-cache sistemi kullanılmaktadir.
	 * 
	 *  Eğer kullanıcı bir veriyi tekrar tekrar talep eder ise 
	 *  hibernate bu veriyi veritabından getirip hem L1 cache'e saklar. 
	 *  Hem de kullanıma sunar. Dolayısıyla, kullanıcı aynı veriye tekrar
	 *  ihtiyaç duyarsa bu veri veritabanından değil cache'den getirilmektedir.  
	 *  
	 *  L1-Cache'i session tabanlı bir cache'dir.
	 *  Bir uygulamada birden fazla session olabilir. Ancak her session'ın 
	 *  verileri o sesion'a özeldir. Dolayısıyla her oturumun ayrı bir L1
	 *  cache'i olduğu düşünülebilir. Session kapatıldığında L1'deki  verilere
	 *  erişilemez. 
	 *  
	 *  Session.evict(): Cache'deki belirtilen veriyi kaldırmak için 
		refresh(): belleği günceller.
		clear(): cache'deki tüm nesne ve entity'leri siler.
	 * 
	 * ------------------------------ L2 CACHE ---------------------------------------
	 * Hibernate L2-cache mekanizmasını da barındırmaktadır.
	 * L2-Cache, default kapalıdır. Kullanmak için aktif hale getirilmelidir.
	 * 
	 * L2, tüm session'lar (SessionFactory) için ortak bir cache alanı sunar.
	 * Yani L2 cache'deki verilere bir uygulamadaki tüm session'lar erişebilir.
	 * 
	 * Bir session, veritabanından bir veri çekince bu veri hem L2,hem L1 hem de 
	 * session' a sunulur. Aynı veri tekrar lazım olursa L1'den çekilir. Ancak,
	 * session kapatılırsa L1'deki veri silinir lakin aynı veriye başka bir session
	 * ihtiyac duyarsa bu veri L2'den çekilmiş olur. Yani veritabanına gitmeye gerek kalmaz.
	 *  
	 * */
	
	public static void main(String[] args) {
		Configuration con = new Configuration().
				configure("hibernate.cfg.xml").
				addAnnotatedClass(H1_Ogrenci.class).
				addAnnotatedClass(H2_Kitap.class);
		
		SessionFactory  sf = con.buildSessionFactory();
		
		Session session1 = sf.openSession();
		
		Transaction tx1 = session1.beginTransaction();
		
//		H1_Ogrenci ogrenci = new H1_Ogrenci();
//		H2_Kitap kitap = new H2_Kitap();
		
		// id'si 111 olan ogrencinin bilgilerini çekelim.
		//ogrenci = session1.get(H1_Ogrenci.class, 11);
		System.out.println(session1.get(H1_Ogrenci.class, 11));
	
		// id'si 222 olan ogrencinin bilgilerini çekelim.
		//ogrenci = session1.get(H1_Ogrenci.class, 12);
		System.out.println(session1.get(H1_Ogrenci.class, 12));
		
		// id'si 111 olan ogrencinin bilgilerini tekrar çekelim.
	//	ogrenci = session1.get(H1_Ogrenci.class, 11);
		System.out.println(session1.get(H1_Ogrenci.class, 11));
			
		// id'si 222 olan ogrencinin bilgilerini tekrar çekelim.
	//	ogrenci = session1.get(H1_Ogrenci.class, 12);
		System.out.println(session1.get(H1_Ogrenci.class, 12));
		
		//session1'i kapatılım.
		tx1.commit();
		session1.close();  // Session kapanınca L1'deki tüm bilgiler silindi.
		System.out.println("------ SESSION1 kapatıldı --------");
	
		
		// session 1'i yeniden açıp id'si 111 olan ogrencinin bilgilerini tekrar çekelim.
		session1 = sf.openSession();
		tx1 = session1.beginTransaction();
	
	  // Cache'den değil veritabanından geldi.
		
		System.out.println("SESSION1:" + session1.get(H1_Ogrenci.class, 11));
		tx1.commit();
		// Ayrı bir sessionda aynı veriye (111) erişmek istersek ne olur?
		// Cevap: Bu veri, diğer session'a ait oldugu icin bu session'nın cache'inde bulunmaz.
		// Bu sebeple ile yeniden veritabanına gitmek gerekecektir.
		
		Session session2 = sf.openSession();
		Transaction tx2 = session2.beginTransaction();

		System.out.println("SESSION2:" + session2.get(H1_Ogrenci.class, 11));
	//	System.out.println("SESSION2:" + session2.get(H1_Ogrenci.class, 11).getKitapListesi());
		
		tx2.commit();
		
		
//		// Ayrı session'ların aynı veriyi cache'den alabilmesi için L2 cache sisteminin acilmasi gerekir.
//		// Bunun için 
//		// 1) Aşağıda anotasyonların ilgili nesnelere eklenemsi gerekir.
//		//      @Cacheable
//        //      @Cache(region="H2_Kitap", usage=CacheConcurrencyStrategy.READ_WRITE)
//		
//		// 2) POM dosyasına Cache ile ilgili dependency'leri eklemek gerekir.
//		//     https://mvnrepository.com/artifact/org.hibernate/hibernate-ehcache
//		
//		// 3) cfg dosyasına asagidaki konfigürasyonları eklemek gerekir. 
//		//		<!-- Following 2 lines are for Second Level Cache -->
//		//   	<property name="hibernate.cache.use_second_level_cache">true</property>         
//		//		<property name="hibernate.cache.region.factory_class">org.hibernate.cache.ehcache.internal.EhcacheRegionFactory</property>
//		//		<property name="hibernate.cache.provider_class">org.hibernate.cache.internal.EhcacheProvider</property>
//		
		session2.close();
		session1.close();
		sf.close();
		
	}

}
