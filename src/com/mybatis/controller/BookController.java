package com.mybatis.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.http.HttpServletRequest;

import com.mybatis.utils.HttpRequestUtil;
import com.mybatis.utils.Jackson;
import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.mybatis.beans.Book;
import com.mybatis.service.BookService;
import sun.net.www.http.HttpClient;


@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @ResponseBody
    @RequestMapping(value="/api/books/getBooksByPage",method=RequestMethod.GET)
//    public String getByPage(@RequestParam("pageIndex") Integer pageIndex,@RequestParam("size") Integer size) {
    public String getBooksByPage(HttpServletRequest request) {
        String json_str = request.getParameter("params");
        Map<String, Object> map = Jackson.JsonToMap(json_str);
        int pageIndex = (int) map.get("pageIndex");
        int size = (int) map.get("size");
        List<Book> books = bookService.getBooksByPage((pageIndex-1)*size,size);
        int total = bookService.getLength();
        int code = 200;
        boolean status = true;
        Map<String,Object> page_map = new HashMap<>();
        page_map.put("pageIndex", pageIndex);
        page_map.put("size", size);
        page_map.put("list", books);
        page_map.put("total", total);
        String result = fill(page_map,code,status);
        return result;
    }

    @RequestMapping(value="/api/books/addBookByPost",method=RequestMethod.POST)
    @ResponseBody
    public String addBookByPost(@RequestBody Book book) {
        System.out.println("添加book：" + book);
        bookService.save(book);
        int code = 200;
        boolean status = true;
        String result = fill("ok",code, status);
        return result;
    }
    @RequestMapping(value="/api/books/editBookByPost",method=RequestMethod.POST)
    @ResponseBody
    public String editBookByPost(@RequestBody Book book) {
        System.out.println("更新book：" + book);
        bookService.update(book);
        int code = 200;
        boolean status = true;
        String result = fill("ok",code, status);
        return result;
    }


    @RequestMapping(value="/api/books/delBookByGet",method=RequestMethod.GET)
    @ResponseBody
//    public String delBookByGet(@RequestParam("eid") Integer id) {
    public String delBookByGet(HttpServletRequest request) {
        String json_str = request.getParameter("params");
        Map<String, Object> map = Jackson.JsonToMap(json_str);
        int bookId = (int) map.get("bookId");
        bookService.delete(bookId);
        int code = 200;
        boolean status = true;
        String result = fill("ok",code,status);
        return result;
    }

    @RequestMapping(value="/api/books/delBookByBatch",method=RequestMethod.GET)
    @ResponseBody
    public String delBookByBatch(HttpServletRequest request) {
        String json_str = request.getParameter("params");
        Map<String, Object> map = Jackson.JsonToMap(json_str);
        String ids = (String) map.get("ids");
        String[] id_array = ids.split(",");
        String result="";
        boolean status = true;
        int code=200;
        try
        {
            for(int i = 0;i < id_array.length;i++)
            {
                System.out.println("删除book："+id_array[i]);
                bookService.delete(Integer.parseInt(id_array[i]));
            }
        }
        catch (Exception e)
        {
            code = 500;
            status = false;
        }
        result = fill("ok", code, status);
        return result;
    }
    @RequestMapping(value="/api/books/query",method=RequestMethod.GET)
    @ResponseBody
    public String query(HttpServletRequest request) {
        String json_str = request.getParameter("params");
        Map<String, Object> map = Jackson.JsonToMap(json_str);
        String condition = (String) map.get("condition");
        int pageIndex = (int) map.get("pageIndex");
        int size = (int) map.get("size");
        Map<String,Object> resturn_map = new HashMap<>();
        boolean status = true;
        int code=200;
        List<Book> list = null;
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try
        {
            list = bookService.query("%"+condition+"%", (pageIndex-1)*size, size);
        }
        catch (Exception e)
        {
            code = 500;
            status = false;
        }
        Map<String,Object> page_map = new HashMap<>();
        page_map.put("list", list);
        page_map.put("total", list.size());
        String result = fill(page_map,code,status);
        return result;
    }
    @RequestMapping(value="/api/books/getBookById",method=RequestMethod.GET)
    @ResponseBody
    public String getBookById(HttpServletRequest request) {
        String json_str = request.getParameter("params");
        Map<String, Object> map = Jackson.JsonToMap(json_str);
        int bookId = (int) map.get("bookId");
        Book book = bookService.getBookById(bookId);
        int code = 200;
        boolean status = true;
        String result = fill(book,code,status);
        return result;
    }
    @RequestMapping(value="/api/books/borrowBook",method=RequestMethod.GET)
    @ResponseBody
    public String borrowBook(HttpServletRequest request) {
        String json_str = request.getParameter("params");
        Map<String, Object> map = Jackson.JsonToMap(json_str);
        int bookId = (int) map.get("bookId");
        int userId = (int) map.get("userId");
        Long startDate = (Long) map.get("startDate");
        Long deadline = (Long) map.get("deadline");
        int r1 = bookService.borrowBook(bookId,userId,startDate,deadline,0);
        int r2 = bookService.update(bookId,0);
        int r = 0;
        if(r1==1&&r2==1) r = 1;
        int code = 200;
        boolean status = true;
        String result = fill(r,code,status);
        return result;
    }
    @RequestMapping(value="/api/books/returnBook",method=RequestMethod.GET)
    @ResponseBody
    public String returnBook(HttpServletRequest request) {
        String json_str = request.getParameter("params");
        Map<String, Object> map = Jackson.JsonToMap(json_str);
        int bookId = (int) map.get("bookId");
        int userId = (int) map.get("userId");
        long stime = System.currentTimeMillis();//获取从1970年1月1日到现在过去的毫秒数
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//格式化时间

        int r1 = bookService.returnBook(0,bookId,userId,stime);
        int r = 0;
        int r2 = 0;
        int code;
        boolean status = true;
        String result = "";
        if(r1==1) {
            r = 1;
            r2 = bookService.update(bookId,1);
            if(r1==1&&r2==1)
            {
                code = 200;
                result = fill(r,code,status);
            }
        }
        else {
            code = 500;
            result = fill(r,code,status);
        }

        return result;
    }
    private  String fill(Object data, int code, boolean status)
    {
        Map<String,Object> return_map = new HashMap<>();
        return_map.put("data",data);
        return_map.put("code",code);
        return_map.put("status",status);
        String result = ReturnMaps(return_map);
        return result;
    }
    private String ReturnMaps(Map<String, Object> map)
    {
        return  Jackson.MapToJson(map);
    }
    @RequestMapping(value="/api/search",method=RequestMethod.GET)
    @ResponseBody
    public String search() {
        HttpRequestUtil http = new HttpRequestUtil();

        String res = http.HttpRequest("https://www.baidu.com/s?wd=12");
        System.out.println(res);
        return res;
    }

    @RequestMapping(value="/api/search2",method=RequestMethod.GET)
    @ResponseBody
    public byte[] search2() throws IOException {
        URL url = new URL("http://www.baidu.com/s?ie=utf-8&bs=23&f=8&rsv_bp=1&wd=23&rsv_sug3=1&rsv_sug4=89&rsv_sug1=1&inputT=0");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setDoOutput(true);
        InputStream inStream = conn.getInputStream();

        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        byte[] data = outStream.toByteArray();
        outStream.close();
        inStream.close();
        System.out.println(new String(data, "utf-8"));
        return data;
    }


//    @RequestMapping(value="/api/search3",method=RequestMethod.GET)
//    @ResponseBody
//    public String search3() {
//        HttpClient httpclient= HttpClient.;
//        HttpGet httpGet = new HttpGet("http://www.baidu.com");
//        HttpResponse httpResponse= httpclient.execute(httpGet);
//        if (httpResponse.getStatusLine().getStatusCode() == 200) {
//            HttpEntity entity = httpResponse.getEntity();
//            String response = EntityUtil.toString(entity, "utf-8");System.out.println(response);
//        }
//    }

//    CloseableHttpClient httpclient= HttpClients.createDefault();HttpGethttpGet= new HttpGet("http://www.baidu.com");

}
