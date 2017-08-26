package com.imooc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import com.mysql.jdbc.Connection;
//import com.mysql.jdbc.PreparedStatement;

import net.sf.json.JSONArray;



@SuppressWarnings("serial")
public class SearchServlet extends HttpServlet {
	 static List<String> datas = new ArrayList<String>();

/*static{
	datas.add("ajax");
	datas.add("ajax post");
	datas.add("becky");
	datas.add("bill");
	datas.add("james");
	datas.add("jerry");
}*/
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		 	Connection conn = getConn();
		    String sql = "select * from tdb_goods";
		    PreparedStatement pstmt;
		    try {
		        pstmt = (PreparedStatement)conn.prepareStatement(sql);
		        ResultSet rs = pstmt.executeQuery();
		        int col = rs.getMetaData().getColumnCount();
		        while (rs.next()) {
		           datas.add(rs.getString(1));
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		 System.out.println(datas);
		response.setCharacterEncoding("utf-8");	
		//response.getWriter().write(JSONArray.fromObject(datas).toString());
		String keyword = request.getParameter("keyword");
		List<String> listData=getData(keyword);
		response.getWriter().write(JSONArray.fromObject(listData).toString());
		datas.clear();
	}
	
      public List<String> getData(String keyword){
    	  
    	  List<String> list = new ArrayList<String>();
    	  for(String data:datas){
    		  if(data.contains(keyword)){
    			  list.add(data);
    		  }
    	  }
    	  return  list;
      }
      
  	//数据库的连接
  	private static Connection getConn() {
  	    String driver = "com.mysql.jdbc.Driver";
  	    String url = "jdbc:mysql://localhost/study1";
  	    String username = "root";
  	    String password = "1234567890";
  	    Connection conn = null;
  	    try {
  	        Class.forName(driver); 
  	        conn = (Connection) DriverManager.getConnection(url, username, password);
  	    } catch (ClassNotFoundException e) {
  	        e.printStackTrace();
  	        System.out.println("fail");
  	    } catch (SQLException e) {
  	        e.printStackTrace();
  	        System.out.println("fail");
  	    }
  	    return conn;
  	}
}

