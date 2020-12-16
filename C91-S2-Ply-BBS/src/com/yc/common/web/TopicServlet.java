package com.yc.common.web;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.bbs.biz.TopicBiz;
import com.yc.bbs.dao.TopicDao;
import com.yc.common.bean.Result;
import com.yc.common.biz.BizException;


@WebServlet("/topic.s")
public class TopicServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
   
    private TopicDao tdao = new TopicDao();
    private TopicBiz tbiz = new TopicBiz();
    
	
	public void  queryByBid(HttpServletRequest request,HttpServletResponse response) throws SQLException, IOException {
		String bid =request.getParameter("bid");
		write(response,tdao.selectByBid(bid));
	}
	
	public void selectByIdWithReply(HttpServletRequest request,HttpServletResponse response) throws SQLException, IOException {
		String id =request.getParameter("id");
		write(response,tdao.selectByIdWithReply(id));
	}
	
	public void add(HttpServletRequest request,HttpServletResponse response) throws SQLException, IOException {
		String boardid =request.getParameter("boardid");
		String title =request.getParameter("title");
		String content =request.getParameter("content");
		/*@SuppressWarnings("unchecked")
		Map<String,Object>loginedUser = (Map<String,Object>)request.getSession().getAttribute("loginedUser");
		Object uid = loginedUser.get("uid");*/
		Object uid =1;
		
		try {
			tbiz.add(title,content,uid,boardid);
			write(response,Result.success("发帖成功！"));
		} catch (BizException e) {
			e.printStackTrace();
			write(response,Result.failure(e.getMessage()));
		}
	}


}
