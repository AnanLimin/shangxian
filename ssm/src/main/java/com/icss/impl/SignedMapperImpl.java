package com.icss.impl;

import java.util.List;

import com.github.pagehelper.PageHelper;
import com.icss.bean.Drawback;
import com.icss.bean.Iaer;
import com.icss.bean.Signed;
import com.icss.dao.SignedMapper;
import com.icss.util.BasicSqlSupport;
import com.icss.util.PageBean;

public class SignedMapperImpl extends BasicSqlSupport implements SignedMapper{

	static final Integer PAGESIZE = 10;
	
	@Override
	public int deleteByPrimaryKey(Integer sid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(Signed record) {
		// TODO Auto-generated method stub
		return this.session.insert("com.icss.dao.SignedMapper.insert",record);
	}

	@Override
	public int insertSelective(Signed record) {
		// TODO Auto-generated method stub
		return this.session.insert("com.icss.dao.SignedMapper.insertSelective", record);
	}

	@Override
	public Signed selectByPrimaryKey(Integer sid) {
		// TODO Auto-generated method stub
		return this.session.selectOne("com.icss.dao.SignedMapper.selectByPrimaryKey", sid);
	}

	@Override
	public int updateByPrimaryKeySelective(Signed record) {
		// TODO Auto-generated method stub
		return this.session.update("com.icss.dao.SignedMapper.updateByPrimaryKeySelective", record);
	}

	@Override
	public int updateByPrimaryKey(Signed record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public PageBean<Signed> signedinfoIsMine(String sale,int pagenum) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pagenum, PAGESIZE);
		List<Signed> list = this.session.selectList("com.icss.dao.SignedMapper.signedinfoIsMine",sale);
		return new PageBean<Signed>(list);
	}

	@Override
	public PageBean<Signed> pending(int stateid,int pagenum) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pagenum, PAGESIZE);
		List<Signed> list = this.session.selectList("com.icss.dao.SignedMapper.pending",stateid);
		return new PageBean<Signed>(list);
	}

	@Override
	public Signed onesignedinfo(int sid) {
		// TODO Auto-generated method stub
		return this.session.selectOne("com.icss.dao.SignedMapper.onesignedinfo",sid);
	}

	@Override
	public int addrecord(Iaer iaer) {
		// TODO Auto-generated method stub
		return this.session.insert("com.icss.dao.SignedMapper.addrecord", iaer);
	}

	@Override
	public PageBean<Drawback> drawbackinfo(int pagenum,int dbtype) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pagenum, PAGESIZE);
		List<Drawback> list = this.session.selectList("com.icss.dao.SignedMapper.drawbackinfo",dbtype);
		return new PageBean<Drawback>(list);
	}

	@Override
	public Drawback onebackinfo(int dbid) {
		// TODO Auto-generated method stub
		return this.session.selectOne("com.icss.dao.SignedMapper.onebackinfo", dbid);
	}

	@Override
	public int updatedrawback(Drawback drawback) {
		// TODO Auto-generated method stub
		return this.session.update("com.icss.dao.SignedMapper.updatedrawback", drawback);
	}

	@Override
	public PageBean<Iaer> alliaerinfo(int pagenum) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pagenum, PAGESIZE);
		List<Iaer> list = this.session.selectList("com.icss.dao.SignedMapper.alliaerinfo");
		return new PageBean<Iaer>(list);
	}

	@Override
	public String getdname(String sale) {
		// TODO Auto-generated method stub
		return this.session.selectOne("com.icss.dao.SignedMapper.getdname", sale);
	}

	@Override
	public int deletemultiple(List<Integer> list) {
		// TODO Auto-generated method stub
		try{
			this.session.delete("com.icss.dao.SignedMapper.deletemultiple", list);
			return 1;
		}catch(Exception e){
			return 0;
		}
		
	}

	@Override
	public int insertDreaBack(Drawback drawback) {
		// TODO Auto-generated method stub
		return this.session.insert("com.icss.dao.SignedMapper.insertDreaBack", drawback);
	}

	@Override
	public PageBean<Signed> dbinfoIsMine(String dbemp, int pagenum) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pagenum, PAGESIZE);
		System.out.println(dbemp);
		List<Signed> list = this.session.selectList("com.icss.dao.SignedMapper.dbinfoIsMine",dbemp);
		return new PageBean<Signed>(list);
	}

	@Override
	public List<Signed> salecontribute() {
		// TODO Auto-generated method stub
		return this.session.selectList("com.icss.dao.SignedMapper.salecontribute");
	}
	
	@Override
	public PageBean<Signed> allsign(int pagenum) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pagenum, PAGESIZE);
		List<Signed> list = this.session.selectList("com.icss.dao.SignedMapper.allsign");
		return new PageBean<Signed>(list);
	}

	@Override
	public PageBean<Signed> selectByAll(int pagenum) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pagenum, PAGESIZE);
		List<Signed> list = this.session.selectList("com.icss.dao.SignedMapper.selectByAll");
		return new PageBean<Signed>(list);
	}

	@Override
	public int updateByStateid(Signed record) {
		// TODO Auto-generated method stub
		return this.session.update("com.icss.dao.SignedMapper.updateByStateid", record);
	}
	@Override
	public int zhuguanagree(List<Integer> list) {
		// TODO Auto-generated method stub
		return this.session.update("com.icss.dao.SignedMapper.zhuguanagree",list);
	}

	@Override
	public int zongjianagree(List<Integer> list) {
		// TODO Auto-generated method stub
		
		return this.session.update("com.icss.dao.SignedMapper.zongjianagree",list);
	}

	@Override
	public int fuzongagree(List<Integer> list) {
		// TODO Auto-generated method stub
		return this.session.update("com.icss.dao.SignedMapper.fuzongagree",list);
	}

	@Override
	public int zongjingliagree(List<Integer> list) {
		// TODO Auto-generated method stub
		return this.session.update("com.icss.dao.SignedMapper.zongjingliagree",list);
	}

	@Override
	public String superjob(String ename) {
		// TODO Auto-generated method stub
		return this.session.selectOne("com.icss.dao.SignedMapper.superjob", ename);
	}

	@Override
	public String myjob(String ename) {
		// TODO Auto-generated method stub
		return this.session.selectOne("com.icss.dao.SignedMapper.myjob", ename);
	}

	@Override
	public PageBean<Drawback> selectbyjob(int pagenum, int stateid) {
		// TODO Auto-generated method stub
		PageHelper.startPage(pagenum, PAGESIZE);
		List<Drawback> list = this.session.selectList("com.icss.dao.SignedMapper.selectbyjob",stateid);
		return new PageBean<Drawback>(list);
	}
	@Override
	public List<Signed> select(Integer sid) {
		// TODO Auto-generated method stub
		return this.session.selectList("com.icss.dao.SignedMapper.selectByPrimaryKey", sid);
	}

}
