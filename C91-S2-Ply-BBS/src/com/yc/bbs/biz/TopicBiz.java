package com.yc.bbs.biz;

import java.sql.SQLException;

import com.yc.bbs.dao.TopicDao;
import com.yc.common.biz.BizException;
import com.yc.common.util.Utils;

public class TopicBiz {
	
	public TopicDao tdao = new TopicDao();
	public void add(String title,String content,Object uid,String boardid) throws BizException {
		Utils.checkNull(title, "标题不能为空");
		Utils.checkNull(content, "内容不能为空");
		
		try {
			tdao.insert(title, content, uid, boardid);
		} catch (SQLException e) {
			throw new BizException("系统繁忙，请稍后再试",e);
		}
	}

}
