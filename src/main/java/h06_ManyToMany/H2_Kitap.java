package h06_ManyToMany;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class H2_Kitap {
	
	
	@Id
	private int book_id;
	
	private String book;

	/*
	 İki tablo arasında "Many To Many" ilişkisi oluşturmak istediğinizde, @ManyToMany ek açıklamasını kullanmanız gerekir.
*/
   
   /*
     Note: @mappedBy("books") kullanmazsanız, fazladan 2 farklı tablo oluşturacaktır.
çünkü H2_Kitap tablosu H2_Kitap_H1_Ogrenci tablosunu oluştururken, H1_Ogrenci tablosu ise H1_Ogrenci_H2_Kitap tablosunu oluşturur.
İki tablo arasında ilişki oluşturmak için 2 tabloya gerek yoktur
bunu önlemek için @mappedBy("books") kullanmalıyız
   */
	
	@ManyToMany(mappedBy = "books")
	private List<H1_Ogrenci> students=new ArrayList<>();
	
	public H2_Kitap() {
		
	}

	public H2_Kitap(int book_id, String book) {
		
		this.book_id = book_id;
		this.book = book;
	}

	public int getBook_id() {
		return book_id;
	}

	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}

	public String getBook() {
		return book;
	}

	public void setBook(String book) {
		this.book = book;
	}

	public List<H1_Ogrenci> getStudents() {
		return students;
	}

	public void setStudents(List<H1_Ogrenci> students) {
		this.students = students;
	}

//	@Override
//	public String toString() {
//		return "book_id=" + book_id + ", book=" + book + ", students=" + students ;
//	}

	@Override
	public String toString() {
		return "book_id=" + book_id + ", book=" + book ;
	}
	
	
	
	

}
