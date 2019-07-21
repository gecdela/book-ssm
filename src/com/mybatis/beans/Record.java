package com.mybatis.beans;

import java.util.Date;

public class Record {
	private int recordId;
	private User user;
	private Book book;
	private Long startDate;
	private Long deadline;
	private Long returnDate;

	public Record() {
	}

	private int status;


	public int getRecordId() {
		return recordId;
	}

	public void setRecordId(int recordId) {
		this.recordId = recordId;
	}

	public Long getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Long returnDate) {
		this.returnDate = returnDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Long getStartDate() {
		return startDate;
	}

	public void setStartDate(Long startDate) {
		this.startDate = startDate;
	}

	public Long getDeadline() {
		return deadline;
	}

	public void setDeadline(Long deadline) {
		this.deadline = deadline;
	}

	public int getStatus() {
		return status;
	}

	public Record(int recordId, User user, Book book, Long startDate, Long deadline, Long returnDate, int status) {
		this.recordId = recordId;
		this.user = user;
		this.book = book;
		this.startDate = startDate;
		this.deadline = deadline;
		this.returnDate = returnDate;
		this.status = status;
	}

	@Override
	public String toString() {
		return "Record{" +
				"recordId=" + recordId +
				", user=" + user +
				", book=" + book +
				", startDate=" + startDate +
				", deadline=" + deadline +
				", returnDate=" + returnDate +
				", status=" + status +
				'}';
	}

	public void setStatus(int status) {
		this.status = status;
	}
}



