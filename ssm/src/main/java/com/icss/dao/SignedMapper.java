package com.icss.dao;


import java.util.List;

import com.icss.bean.Drawback;
import com.icss.bean.Iaer;
import com.icss.bean.Signed;
import com.icss.util.PageBean;

public interface SignedMapper {
    int deleteByPrimaryKey(Integer sid);

    int insert(Signed record);

    int insertSelective(Signed record);

    Signed selectByPrimaryKey(Integer sid);

    int updateByPrimaryKeySelective(Signed record);

    int updateByPrimaryKey(Signed record);
    
    PageBean<Signed> signedinfoIsMine(String sale,int pagenum);
    
    PageBean<Signed> pending(int stateid,int pagenum);
    
    Signed onesignedinfo(int sid);
    
    int addrecord(Iaer iaer);
    
    PageBean<Drawback> drawbackinfo(int pagenum,int dbtype);
    
    Drawback onebackinfo(int dbid);
    
    int updatedrawback(Drawback drawback);
    
    PageBean<Iaer> alliaerinfo(int pagenum);
    
    String getdname(String sale);
    
    int deletemultiple(List<Integer> list);
       
    int insertDreaBack(Drawback drebBack);
    
    PageBean<Signed> dbinfoIsMine(String dbemp,int pagenum);
    
    PageBean<Signed> allsign(int pagenum);
    
    List<Signed> salecontribute();
    
    PageBean<Signed> selectByAll(int pagenum);
    
    
    int updateByStateid(Signed record);
    
    
    
    int zhuguanagree(List<Integer> list);
    int zongjianagree(List<Integer> list);
    int fuzongagree(List<Integer> list);
    int zongjingliagree(List<Integer> list);
    
    String superjob(String eanme);
    
    String myjob(String ename);
    
    PageBean<Drawback> selectbyjob(int pagenum, int stateid);

    
}