package h06_ManyToMany;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class H1_Ogrenci {
	
	// many to many = ogrenciler-> kütüphane kitapları

	@Id
	private int std_id;
	
	private String name;
	
	private int grade;

	
	
	/*
	  İki tablo arasında "Many To Many" ilişkisi oluşturmak istediğinizde @ManyToMany kullanmanız gerekir.
ve kitaplar için bir liste oluşturmanız gerekir.
	*/
	
	/*
	 Örneğimizde,  birleştirme tablosu, H1_Ogrenci tarafında(@JoinTable ek açıklamasını kullanarak) belirtilmiştir.

	 
	  @JoinTable, "bağlantı tablosunu" tanımlamak için kullanılır. genelde, H1_Ogrenci_H2_Kitap şeklinde isim verilir
	
	/*
	@JoinColumn ek açıklaması, ana tabloyla bağlantı sütununu belirtmek için kullanılır.
	Burada bağlantı sütunu "std_id"dir.

	 H2_Kitap relationship in öbür tarafında olduğundan "book_id" ters birleştirme (inverseJoinColumns) sütunudur.

	*/
	
	
	
	@ManyToMany
	@JoinTable(
            name = "H1_Ogrenci_H2_Kitap", 
            joinColumns = { @JoinColumn(name = "ogrenci") }, 
            inverseJoinColumns = { @JoinColumn(name = "kitap") }
        )
	private List<H2_Kitap> books=new ArrayList<>();
	
	public H1_Ogrenci() {

	}
	public H1_Ogrenci(int std_id, String name, int grade) {
		
		this.std_id = std_id;
		this.name = name;
		this.grade = grade;
	}
	public int getStd_id() {
		return std_id;
	}
	public void setStd_id(int std_id) {
		this.std_id = std_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public List<H2_Kitap> getBooks() {
		return books;
	}
	public void setBooks(List<H2_Kitap> books) {
		this.books = books;
	}
	@Override
	public String toString() {
		return "std_id=" + std_id + ", name=" + name + ", grade=" + grade + ", books=" + books ;
	}


//	@Override
//	public String toString() {
//		return "std_id=" + std_id + ", name=" + name + ", grade=" + grade  ;
//	}
//	
	
	
	
	
	
}
