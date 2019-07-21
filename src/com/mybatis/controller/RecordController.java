package com.mybatis.controller;

import com.mybatis.beans.Book;
import com.mybatis.beans.Record;
import com.mybatis.service.BookService;
import com.mybatis.utils.Jackson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Controller
public class RecordController {

    @Autowired
    private BookService bookService;

    @ResponseBody
    @RequestMapping(value="/api/records/getRecordsByPage",method= RequestMethod.GET)
    public String getRecordsByPage(HttpServletRequest request)
    {
        String json_str = request.getParameter("params");
        Map<String, Object> map = Jackson.JsonToMap(json_str);
        int pageIndex = (int) map.get("pageIndex");
        int size = (int) map.get("size");
        List<Record> records = bookService.getRecordsByPage((pageIndex-1)*size,size);
        int total = bookService.getRecordsLength();
        int code = 200;
        boolean status = true;
        Map<String,Object> page_map = new HashMap<>();
        page_map.put("pageIndex", pageIndex);
        page_map.put("size", size);
        page_map.put("list", records);
        page_map.put("total", total);
        String result = fill(page_map,code,status);
        return result;
    }
    @RequestMapping(value="/api/records/deleleRecordById",method=RequestMethod.GET)
    @ResponseBody
//    public String delBookByGet(@RequestParam("eid") Integer id) {
    public String deleleRecordById(HttpServletRequest request) {
        String json_str = request.getParameter("params");
        Map<String, Object> map = Jackson.JsonToMap(json_str);
        int recordId = (int) map.get("id");
        int r = bookService.deleteRecordById(recordId);
        int code = 200;
        boolean status = true;
        String result = fill(r,code,status);
        return result;
    }
    @RequestMapping(value="/api/records/delRecordsByBatch",method=RequestMethod.GET)
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
                System.out.println("删除record："+id_array[i]);
                bookService.deleteRecordById(Integer.parseInt(id_array[i]));
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
}
