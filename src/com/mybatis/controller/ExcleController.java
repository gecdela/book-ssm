//package com.mybatis.controller;
//
//import java.io.UnsupportedEncodingException;
//import java.net.URLEncoder;
//
//import javax.servlet.ServletOutputStream;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.mybatis.service.ExcleImpl;
//
//
//
///**
// * @author lenovo
// * ʹ��POI����Excel���
// */
//@Controller
//public class ExcleController {
//    //����ֱ��new��
//    ExcleImpl  excleImpl=new ExcleImpl();
//    
//@RequestMapping(value="/download_excel")    
//
////��ȡurl�����ϵĲ���
//public @ResponseBody String dowm(HttpServletResponse response,@RequestParam("id") String id,@RequestParam("name") String name){
//     response.setContentType("application/binary;charset=UTF-8");
//              try{
//                  ServletOutputStream out=response.getOutputStream();
//                  try {
//                      //�����ļ�ͷ�����һ�����������������ļ���(�������ǽУ�����.pdf)
//                      response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(name+".xls", "UTF-8"));
//                  } catch (UnsupportedEncodingException e1) {
//                      e1.printStackTrace();
//                  }
//               
//                  String[] titles = { "�û�id", "�û�����", "�û�����", "�û�����" }; 
//                  
//                  excleImpl.export(list,titles, out);      
//                  return "success";
//              } catch(Exception e){
//                  e.printStackTrace();
//                  return "������Ϣʧ��";
//              }
//          }
//}
