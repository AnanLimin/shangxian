/**
 * @date 2016/10/17
 * @author 李敏
 * 签单相关的业务
 */
package com.icss.business;


import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.icss.bean.Drawback;
import com.icss.bean.Iaer;
import com.icss.bean.Signed;
import com.icss.bean.User;
import com.icss.dao.SignedMapper;
import com.icss.util.PageBean;

public class SignedBusiness {
	private SignedMapper signedDao;
	
	public SignedMapper getSignedDao() {
		return signedDao;
	}

	public void setSignedDao(SignedMapper signedDao) {
		this.signedDao = signedDao;
	}
	
	/**
	 * @param pagenum
	 * @return 查询所有的信息
	 */
	public PageBean<Signed> allsigninfo(int pagenum){
		return signedDao.allsign(pagenum);
	}
	/**
	 * 
	 * @return 查看签单排名
	 */
	public List<Signed> signranking(){
		return signedDao.salecontribute();
	}
	
	/**
	 * 插入新增签单-条
	 * @param signed
	 * @return
	 */
	public int addonesign(Signed signed){
		signed.setDept(signedDao.getdname(signed.getSale()));
		System.out.println("/////////////////////"+signed.getDept());
		return signedDao.insert(signed);
	}

	/**
	 * @param session
	 * 查询自己的签单（第一页）
	 */
	public PageBean<Signed> getSignedinfo(HttpSession session){
		String sale = ((User)session.getAttribute("tempuser")).getUsername();
		return signedDao.signedinfoIsMine(sale,1);
	}
	/**
	 * @param session
	 * 查询自己的签单（第一页）
	 */
	public PageBean<Signed> getnextSignedinfo(HttpSession session,int pagenum){
		String sale = ((User)session.getAttribute("tempuser")).getUsername();
		return signedDao.signedinfoIsMine(sale,pagenum);
	}
	
	
	/**
	 * 
	 * @param sale
	 * @param pagenum
	 * @return 查看指定页数自己的签单
	 */
	public List<Signed> nextPageSigninfo(String sale ,int pagenum){
		return (signedDao.signedinfoIsMine(sale, pagenum)).getList();
	}
	
	/**
	 * @param stateid
	 * @return 根据状态查询签单相关信息
	 */
	public PageBean<Signed> financeSignedinfo(int stateid,int pagenum){
		return signedDao.pending(stateid,pagenum); 
	}
	
	/**
	 * @param request
	 * @return 根据id查询整个签单信息
	 */
	public Signed selectSignedById(HttpServletRequest request){
		int sid = Integer.parseInt(request.getParameter("sid"));
		System.out.println(sid);
		return signedDao.onesignedinfo(sid);
	}
	
	/**
	 * 
	 * @param request
	 * @param iaer
	 * @return 插入数据进收支记录表，更改签单状态
	 */
	public int addAndChange(HttpServletRequest request, Iaer iaer){
		//插入收支记录
		signedDao.addrecord(iaer);
		//更改签单状态()
		if(request.getParameter("stateid")!=""&&request.getParameter("stateid")!=null ){
			Signed signed = new Signed();
			signed.setSid(iaer.getSid());
			signed.setStateid(Integer.parseInt(request.getParameter("stateid")));
			signedDao.updateByPrimaryKeySelective(signed);
		}
		if(request.getParameter("ask")!=""&&request.getParameter("ask")!=null ){
			Drawback drawback = new Drawback();
			drawback.setSid(iaer.getSid());;
			drawback.setStateid(Integer.parseInt(request.getParameter("ask")));
			signedDao.updatedrawback(drawback);
		}
		return 1;
	}
	
	/**
	 * @param pagenum
	 * @param dbtype
	 * @return 根据类型查看退返款信息(多条)
	 */
	public PageBean<Drawback> drawbackinfo(int pagenum,int dbtype){
		return signedDao.drawbackinfo(pagenum, dbtype);
	}
	
	/**
	 * @param dbid
	 * @return 查看一条退返款申请信息
	 */
	public Drawback onebackinfo(int dbid){
		return signedDao.onebackinfo(dbid);
	}
	
	/**
	 * @return 所有的收支记录
	 */
	public PageBean<Iaer> alliaerinfo(int pagenum){
		return signedDao.alliaerinfo(pagenum);
	}
	
	public int delectmultiple(String str){
		String [] strArrays = str.trim().split(",");
		Integer[] intArray=new Integer[strArrays.length];     
        for (int i = 0; i < intArray.length; i++) {
            intArray[i] = Integer.parseInt((strArrays[i]));
        }
        List<Integer> list  = Arrays.asList(intArray);
		return signedDao.deletemultiple(list);
	}
	
	/** 
     * 读取Excel数据到数据库 
     * @param signed 
     * @return 
     * @author chen
     */  
	
	public void InsertSigned(Signed signed){
		signedDao.insertSelective(signed);	
	
	}
	/**
	 * @author chen
	 * @param session
	 * 查询自己的退款返款信息
	 */
	public PageBean<Signed> getdbdinfo(HttpSession session){
		String emp = ((User)session.getAttribute("tempuser")).getUsername();
		return signedDao.dbinfoIsMine(emp, 1);
	}
	/**
	 * @author chen
	 * @param session
	 * 查询自己的签单（第一页）
	 */
	public PageBean<Signed> Backinfo(HttpSession session,int pagenum){
		String sale = ((User)session.getAttribute("tempuser")).getUsername();
		return signedDao.dbinfoIsMine(sale,pagenum);
	}
	/**
	 * @author chen
	 * @param request
	 * @param drawback
	 * @return 更新退款返款记录
	 */
	public int updateByExit(HttpServletRequest request,Drawback drawback){
		drawback.setDbtime(request.getParameter("time"));
		
		drawback.setDbemp(request.getParameter("sale"));
		drawback.setDbamount(Float.valueOf(request.getParameter("backfee")));
		String statid =request.getParameter("stateid");
		System.out.println(statid);
		drawback.setDbremark(request.getParameter("remark"));
		
		if(statid.equals("2")){	//只有状态为2才能退款。		
			drawback.setDbtype(2);//退款为2
			drawback.setStateid(5);//dreaback表 状态变更为5
			if(request.getParameter("stateid")!=""&&request.getParameter("stateid")!=null ){
				
				Signed signed = new Signed();
				signed.setScustomerbankcardid(request.getParameter("scustomerbankcardid"));
				signed.setScustomercardid(request.getParameter("scustomercardid"));
				signed.setSid(drawback.getSid());
				signed.setStateid(4);//signed 表 状态也变为4
				signedDao.updateByPrimaryKeySelective(signed);
			}
			
		}
		else{			
			drawback.setDbtype(1);//返款为1
			drawback.setStateid(5);//dreaback表 状态变更为5
			if(request.getParameter("stateid")!=""&&request.getParameter("stateid")!=null ){
				
				Signed signed = new Signed();
				signed.setScustomerbankcardid(request.getParameter("scustomerbankcardid"));
				signed.setScustomercardid(request.getParameter("scustomercardid"));
				signed.setSid(drawback.getSid());
				signed.setStateid(3);//signed 表 状态也变为5
				signedDao.updateByPrimaryKeySelective(signed);
			}
		}
		signedDao.insertDreaBack(drawback);//增加退返款信息记录。										
		return 1;
	}	
	
	/**
	 * @param pagenum
	 * @return 查询所有stateid 5-9的信息
	 */
	public PageBean<Signed> selectByAll(int pagenum){
		return signedDao.selectByAll(pagenum);
	}
	
	/**
	 * @param pagenum
	 * @return 更新一条stateid 5-9的信息
	 */
	public int updateByStateid(Signed signed){
		
		return signedDao.updateByStateid(signed);
	}
	
	

	/**
	 * 根据职位来查询申请
	 */
	public PageBean<Drawback> select(HttpSession session,int pagenum){
		String ename = ((User)session.getAttribute("tempuser")).getUsername();
		System.out.println(ename);
		System.out.println(pagenum);
		String job = signedDao.myjob(ename);
		PageBean<Drawback> page = new PageBean<Drawback>(null);
		System.out.println(page.getPages());
		if(job.indexOf("主管")!=-1){
			System.out.println("1");
			page = signedDao.selectbyjob(pagenum,5);
		}else if(job.indexOf("总监")!=-1){
			System.out.println("2");
			page = signedDao.selectbyjob(pagenum,6);
		}else if(job.indexOf("副总")!=-1){
			System.out.println("3");
			page = signedDao.selectbyjob(pagenum,7);
		}else if(job.indexOf("总经理")!=-1){
			System.out.println("4");
			page= signedDao.selectbyjob(pagenum,8);
		}
		return page;
	}
	
	/**
	 * 根据职位来同意申请信息
	 * @author limin
	 */
	public void agree(HttpSession session ,HttpServletRequest request){
		System.out.println("**************************");
		String str = request.getParameter("str").substring(0, request.getParameter("str").length()-1);
		System.out.println(str);
		String [] strArrays = str.trim().split(",");
		System.out.println(strArrays.toString());
		System.out.println("**************************");
		Integer[] intArray=new Integer[strArrays.length];     
        for (int i = 0; i < intArray.length; i++) {
            intArray[i] = Integer.parseInt((strArrays[i]));
        }
        List<Integer> list  = Arrays.asList(intArray);
		String ename = ((User)session.getAttribute("tempuser")).getUsername();
		String job = signedDao.myjob(ename);
		if(job.indexOf("主管")!=-1){
			signedDao.zhuguanagree(list);
		}else if(job.indexOf("总监")!=-1){
			signedDao.zongjianagree(list);
		}else if(job.indexOf("副总")!=-1){
			signedDao.fuzongagree(list);
		}else if(job.indexOf("总经理")!=-1){
			signedDao.zongjingliagree(list);
		}
		
	}

}
