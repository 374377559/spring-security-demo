package com.huaxin.action;

import java.util.List;

import com.huaxin.bean.Notice;
import com.huaxin.dao.NoticeDao;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class NoticeAction extends ActionSupport {
	private List<Notice> datas;
	private NoticeDao noticeDao;
	private Notice notice;

	public Notice getNotice() {
		return notice;
	}

	public void setNotice(Notice notice) {
		this.notice = notice;
	}

	public NoticeDao getNoticeDao() {
		return noticeDao;
	}

	public void setNoticeDao(NoticeDao noticeDao) {
		this.noticeDao = noticeDao;
	}

	public List<Notice> getDatas() {
		return datas;
	}

	public void setDatas(List<Notice> datas) {
		this.datas = datas;
	}

	@Override
	public String execute() throws Exception {
		this.datas = this.noticeDao.findAll();
		return SUCCESS;
	}

}
