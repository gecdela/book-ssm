package com.mybatis.service;
import com.mybatis.beans.Department;
import com.mybatis.beans.Book;
import com.mybatis.beans.Record;
import com.mybatis.beans.User;
import com.mybatis.dao.DepartmentMapper;
import com.mybatis.dao.BookMapper;
import com.mybatis.dao.RecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class BookService {

    @Autowired
    private RecordMapper recordMapper;
    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private DepartmentMapper deptMapper;

    public List<Record> getRecordsByPage(int pageIndex, int size)
    {
        return recordMapper.getRecordsByPage(pageIndex,size);
    }
    public int deleteRecordById(Integer id) {
        // TODO Auto-generated method stub
        return recordMapper.deleteRecordById(id);
    }

    public List<Book> getBooks(){
        return bookMapper.getBooks();
    }

    public int delete(Integer id) {
        // TODO Auto-generated method stub
       return bookMapper.deleteBookById(id);
    }

    public void update(Book book)
    {
        bookMapper.updateBook(book);
    }
    public void save(Book book) {
        // TODO Auto-generated method stub
        bookMapper.addBook(book);

    }

    public Book getBookById(Integer id) {
        // TODO Auto-generated method stub
        return bookMapper.getBookById(id);
    }

    public List<Book> getBooksByPage(Integer pageIndex,Integer size) {
        // TODO Auto-generated method stub
        return bookMapper.getBooksByPage(pageIndex,size);
    }


    public int getLength() {
        return bookMapper.getLength();
    }

    public List<Book> query(String s,Integer pageIndex,Integer size) {
        return bookMapper.query(s,pageIndex,size);
    }

    public int borrowBook(int bookId, int userId, Long startDate, Long deadline, int status) {
        return bookMapper.borrowBook(bookId,userId,startDate,deadline,status);
    }

    public int update(int bookId,int status) {
        return bookMapper.updateBookById(bookId,status);
    }

    public int getRecordsLength() {
        return recordMapper.getLength();
    }

    public int returnBook(int status, int bookId, int userId, long returnDate) {
        return bookMapper.returnBook(bookId,userId,status,returnDate);
    }
}
