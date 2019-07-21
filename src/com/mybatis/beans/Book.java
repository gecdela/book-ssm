package com.mybatis.beans;

public class Book {
	private String bookName;
	private String bookPrice;
	private String bookAuthor;
	private String bookPic;
	private int bookId;
	private int status;

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getBookPrice() {
		return bookPrice;
	}

	public void setBookPrice(String bookPrice) {
		this.bookPrice = bookPrice;
	}

	public String getBookAuthor() {
		return bookAuthor;
	}

	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}

	public String getBookPic() {
		return bookPic;
	}

	public void setBookPic(String bookPic) {
		this.bookPic = bookPic;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	public Book(int bookId, String bookName, String bookPrice, String bookAuthor, String bookPic, int status) {
		this.bookName = bookName;
		this.bookPrice = bookPrice;
		this.bookAuthor = bookAuthor;
		this.bookPic = bookPic;
		this.bookId = bookId;
		this.status = status;
	}

	public Book() {
	}

	@Override
	public String toString() {
		return "Book{" +
				"bookName='" + bookName + '\'' +
				", bookPrice='" + bookPrice + '\'' +
				", bookAuthor='" + bookAuthor + '\'' +
				", bookPic='" + bookPic + '\'' +
				", bookId=" + bookId +
				", status=" + status +
				'}';
	}
}
