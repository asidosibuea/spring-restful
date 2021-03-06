package dto;

import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;

import com.fasterxml.jackson.annotation.JsonFormat;

public class BookAllDto {
	private String title;
	private String description;
	private String author;
	private String genre;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private Date buyDate;
	private String buyPrice;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(char genre) {
		if(genre == 'N'){
			this.genre = "Novel";
		} else if(genre == 'A'){
			this.genre = "Autobiography";
		} else if(genre == 'C'){
			this.genre = "Comic";
		} else if(genre == 'H'){
			this.genre = "History";
		} else if(genre == 'O'){
			this.genre = "Others";
		} else{
			this.genre = "Unknown";
		}
	}

	public Date getBuyDate() {
		return buyDate;
	}

	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}

	public String getBuyPrice() {
		return buyPrice;
	}
	
	public void setBuyPrice(double buyPrice) {
		Locale indonesianLocale = new Locale("id", "ID");
		String formatIndo = NumberFormat.getCurrencyInstance(indonesianLocale).format(buyPrice); 
		this.buyPrice = formatIndo;
	}
}
