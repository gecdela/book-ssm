package com.mybatis.dao;

import com.mybatis.beans.Book;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BookMapper {
	public Book getBookById(Integer bookId);
	public void addBook(Book Book);
	public boolean updateBook(Book Book);
	public int deleteBookById(Integer bookId);
	public List<Book> getBooks();
	public List<Book> getBooksByPage(@Param("pageIndex") Integer pageIndex, @Param("size") Integer size);
	public List<Book> query(@Param("condition")String condition, @Param("pageIndex")Integer pageIndex,@Param("size") Integer size);

	public int getLength();

	public int borrowBook(@Param("bookId") int bookId, @Param("userId") int userId, @Param("startDate") Long startDate, @Param("deadline") Long deadline, @Param("status")int status);

	public int updateBookById(@Param("bookId")int bookId,@Param("status")int status);

    public int returnBook(@Param("bookId") int bookId, @Param("userId") int userId,@Param("status") int status,@Param("returnDate") long returnDate);
}
