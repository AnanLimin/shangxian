/**
 * @date 2016/10/17
 * @author 李敏
 */
package com.icss.conroller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.icss.bean.Drawback;
import com.icss.bean.Iaer;
import com.icss.bean.Signed;
import com.icss.business.SignedBusiness;
import com.icss.util.PageBean;
import com.icss.util.ReadExcel;

@Controller
@RequestMapping("signed")
public class SignedController {
					
	@Resource(name="signedBusiness")
	private SignedBusiness signedBusiness=null;

	public void setSignedBusiness(SignedBusiness signedBusiness) {
		this.signedBusiness = signedBusiness;
	}
	/**
	 * @param session
	 * @return 所有签单信息（第一页）
	 */
	@RequestMapping("allsigninfo.do")
	public ModelAndView allsigninfo(HttpSession session){
		PageBean<Signed> page = signedBusiness.allsigninfo(1);
		session.setAttribute("pages", page.getPages());
		return new ModelAndView("sale/allsigned","allsigns",page.getList());
	}
		
	/**
	 * 
	 * @param request
	 * @return 查询指定页数的所有签单信息
	 */
	@RequestMapping("nextallsigninfo.do")
	public @ResponseBody String nextallsigninfo(HttpServletRequest request){
		int pagenum = Integer.parseInt(request.getParameter("pagenum"));
		PageBean<Signed> page = signedBusiness.allsigninfo(pagenum);
		JSONArray jsonArray = JSONArray.fromObject(page.getList());
		System.out.println(page.getList().size());
		return jsonArray.toString();
	}
	/**
	 * @return 销售已收款的签单排名
	 */
	@RequestMapping("saleranking.do")
	public @ResponseBody String saleranking(){
		JSONArray jsonArray = JSONArray.fromObject(signedBusiness.signranking());
		System.out.println(jsonArray.toString());
		return jsonArray.toString();
	}


	/**
	 * @param session
	 * @return 自己已签单客户的信息(查询第一页信息十条数据)
	 */
	@RequestMapping("signedinfo.do")
	public ModelAndView signed(HttpSession session){
		List<Signed> list = (signedBusiness.getSignedinfo(session)).getList();
		session.setAttribute("pages", (signedBusiness.getSignedinfo(session)).getPages());
		return new ModelAndView("sale/signed","signedinfo",list);
	}
	

	/**
	 * @param request
	 * @return 自己分页后所有的签单信息
	 */
	@RequestMapping("nextsignedinfo.do")
	public @ResponseBody String nextsignedinfo(HttpServletRequest request,HttpSession session){
		int pagenum = Integer.parseInt(request.getParameter("pagenum"));
		PageBean<Signed> page = signedBusiness.getnextSignedinfo(session, pagenum);
		JSONArray jsonArray = JSONArray.fromObject(page.getList());
		return jsonArray.toString();
	}
		
	/**
	 * 从导航栏查询（jsp显示）
	 * @param request
	 * @return 待收款的签单信息(查询第一页十条数据)
	 */
	@RequestMapping("firstincomepay.do")
	public ModelAndView incomepay(HttpSession session){
		List<Signed> list = (signedBusiness.financeSignedinfo(1,1)).getList();
		session.setAttribute("pages", (signedBusiness.financeSignedinfo(1,1)).getPages());
		return new ModelAndView("financial/financial","financial",list);
	}
	
	
	@RequestMapping("status.do")
	public ModelAndView status(HttpServletRequest request,HttpSession session){
		int stateid = Integer.parseInt(request.getParameter("status"));
		int pagenum = Integer.parseInt(request.getParameter("pagenum"));
		PageBean<Signed> list = signedBusiness.financeSignedinfo(stateid,pagenum);
		session.setAttribute("pages", list.getPages());
		return new ModelAndView("financial/financial","financial",list.getList());
	}
	
	/**
	 * 
	 * @param request
	 * @return  分页后的数据
	 * 用ajax传递数据(@ResponseBody)
	 */
	@RequestMapping("signstatus.do")
	public @ResponseBody String signstatus(HttpServletRequest request){
		int stateid = Integer.parseInt(request.getParameter("status"));
		int pagenum = Integer.parseInt(request.getParameter("pagenum"));
		PageBean<Signed> pagebean = signedBusiness.financeSignedinfo(stateid,pagenum);
		JSONArray jsonArray = JSONArray.fromObject(pagebean.getList());
		return jsonArray.toString();
	} 
	
	
	
	/**
	 * @param request
	 * @return 查询一条签单的信息
	 */
	@RequestMapping("onesign.do")
	public @ResponseBody String onesign(HttpServletRequest request){
		JSONArray jsonArray = JSONArray.fromObject(signedBusiness.selectSignedById(request));
		System.out.println(jsonArray.toString());
		return jsonArray.toString();
	}
	
	/**
	 * 增加一条收款记录，修改签单信息（有需要的话）
	 * @param signed
	 * @param request
	 * @return
	 */
	@RequestMapping("addoneiaer.do")
	public String addoneiaer(@ModelAttribute("iaer") Iaer iaer, HttpServletRequest request){
		signedBusiness.addAndChange(request, iaer);
		return "redirect:/signed/firstincomepay.do";
	}
	

	/**
	 * dbtype为1时代表申请类型是返款，为2时代表申请类型为退款
	 * @param session
	 * @return 待返款的申请记录信息（jsp）
	 */
	@RequestMapping("backmoney.do")
	public ModelAndView backmoney(HttpSession session){
		PageBean<Drawback> pagebean = signedBusiness.drawbackinfo(1, 1);
		session.setAttribute("pages", pagebean.getPages());
		return new ModelAndView("financial/backmoney","backmoney",pagebean.getList());
	}
	
	/**
	 * @param request
	 * @return 查询出一条返款信息
	 */
	@RequestMapping("onebackmoney.do")
	public @ResponseBody String onrbackmoney(HttpServletRequest request){
		int dbid = Integer.parseInt(request.getParameter("dbid"));
		JSONArray jsonArray = JSONArray.fromObject(signedBusiness.onebackinfo(dbid));
		System.out.println(jsonArray.toString());
		return jsonArray.toString();
	}
	
	/**
	 * dbtype为1时代表申请类型是返款，为2时代表申请类型为退款 
	 * @param session
	 * @return 待退款的申请记录信息（jsp）
	 */
	@RequestMapping("refund.do")
	public ModelAndView refund(HttpSession session){
		PageBean<Drawback> pagebean = signedBusiness.drawbackinfo(1, 2);
		session.setAttribute("pages", pagebean.getPages());
		return new ModelAndView("financial/refund","refund",pagebean.getList());
	}
	
	/**
	 * 
	 * @param session
	 * @return 查询财务收支记录(第一页)
	 */
	@RequestMapping("iaer.do")
	public ModelAndView iear(HttpSession session){
		PageBean<Iaer> page = signedBusiness.alliaerinfo(1);
		session.setAttribute("pages", page.getPages());
		return new ModelAndView("financial/iaer","iaers",page.getList());
	}
	
	/**
	 * 
	 */
	@RequestMapping("insertonesign.do")
	public String insertonesign(@ModelAttribute("signed") Signed signed){
		signedBusiness.addonesign(signed);
		return "redirect:/signed/signedinfo.do";
	}
	
	/**
	 * @param request
	 * @return 财务收支记录（指定页）
	 */
	@RequestMapping("nextiaer.do")
	public @ResponseBody String nextiaer(HttpServletRequest request){
		int pagenum = Integer.parseInt(request.getParameter("pagenum"));
		PageBean<Iaer> page = signedBusiness.alliaerinfo(pagenum);
		JSONArray jsonArray = JSONArray.fromObject(page.getList());
		return jsonArray.toString();
	}
	
	/**
	 * 
	 * @param request
	 * @return 批量删除签单信息
	 */
	@RequestMapping("delectmultiple.do")
	public String delectmultiple(HttpServletRequest request){
		String str = request.getParameter("str").substring(0, request.getParameter("str").length()-1);
		signedBusiness.delectmultiple(str);
		return "redirect:/signed/signedinfo.do";

	}
	
	/** 
     * 读取Excel数据到数据库 
     * @param file 
     * @param request 
     * @return 
     * @throws IOException ServletException 
     * @author chen
     */  
    @RequestMapping(value="readExcel.do")   
    public String readExcel(@RequestParam(value="mFile") MultipartFile mFile,HttpServletRequest request,HttpSession session,HttpServletResponse response) throws IOException, ServletException{  
    	
    	System.out.println("jdwedhqhdiqh");
    	MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
    	mFile = multipartRequest.getFile("mFile");
    	String path = request.getSession().getServletContext().getRealPath("/WEB-INF/upload/");//获取文件名 的路径
    	System.out.println(path);
    	String name = mFile.getOriginalFilename();   //获取文件名     
    	System.out.println(name);
    	InputStream inputStream = mFile.getInputStream();
    	 
    	 byte[] b = new byte[1048576];
    	 int length = inputStream.read(b);
    	 path += "\\" + name;
    	 FileOutputStream outputStream = new FileOutputStream(path);
    	 outputStream.write(b, 0, length);
    	 inputStream.close();
    	 outputStream.close();
       

    	ReadExcel xlsMain = new ReadExcel();
    	Signed signed=null;
    	try {
			List<Signed> ListResult =xlsMain.ReadInExcel(path);
			if(ListResult!=null){				
				for(int i=0;i<ListResult.size();i++){
					signed=ListResult.get(i);				
					signedBusiness.InsertSigned(signed);
				}				
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
        return "redirect:/signed/signedinfo.do" ;  
    }
    
    /**
     * @author chen
	 * 增加退返款记录
	 * @param drawback
	 * @param request
	 * @return
	 */
	@RequestMapping("UpdateByBack.do")
	public String updateByback(@ModelAttribute("DreaBack") Drawback drawback, HttpServletRequest request){
		//signedBusiness.updateByback(request, DreaBack);
		System.out.println("1");
		signedBusiness.updateByExit(request, drawback);
		
		return "redirect:/signed/Back.do";
	}
	/**
	 * 从导航栏查询（BackFee显示）
	 * @param session
	 * @return 查询该用户编写的所有签单信息。
	 * @author chen
	 */
	@RequestMapping("Back.do")
	public ModelAndView BackFee(HttpSession session){	
		List<Signed> list = (signedBusiness. getdbdinfo(session)).getList();
		session.setAttribute("pages", (signedBusiness. getdbdinfo(session)).getPages());
		return new ModelAndView("sale/BackFee","Back",list);
	}
	/**
	 * @author chen
	 * @param request
	 * @return 自己分页后所有的退返信息
	 */
	@RequestMapping("Backinfo.do")
	public @ResponseBody String Backinfo(HttpServletRequest request,HttpSession session){
		int pagenum = Integer.parseInt(request.getParameter("pagenum"));
		System.out.println("+++++"+pagenum);
		PageBean<Signed> page = signedBusiness.Backinfo(session, pagenum);
		JSONArray jsonArray = JSONArray.fromObject(page.getList());
		return jsonArray.toString();
	}
	/**
	 * @author chen
	 * @param request ajax
	 * @return 查询所有stateid 5-9的信息
	 */
	@RequestMapping("slectbyall.do")
	public @ResponseBody String slectbyall(HttpServletRequest request,HttpSession session){
		int pagenum = Integer.parseInt(request.getParameter("pagenum"));
		PageBean<Drawback> page = signedBusiness.select(session, pagenum);
		JSONArray jsonArray = JSONArray.fromObject(page.getList());
		return jsonArray.toString();
	}
	/**
	 * @author chen
	 * @param session
	 * @return 查询第一页所有stateid 5-9的信息
	 */
	@RequestMapping("ourmanagment.do")
	public ModelAndView slectbyall(HttpSession session){
		/*System.out.println("1111");
		PageBean<Signed> page = signedBusiness.selectByAll(1);
		session.setAttribute("pages", page.getPages());
		return new ModelAndView("sale/ourmanagment","InfoByall",page.getList());*/
		PageBean<Drawback> page = signedBusiness.select(session, 1);
		System.out.println(page.getPages());
		
			session.setAttribute("pages", page.getPages());
		
	
		
		return new ModelAndView("sale/ourmanagment","InfoByall",page.getList());
		
	}
	/**
	 * @author chen
	 * @param session
	 * @return 更新一条stateid 5-9的信息
	 */
	@RequestMapping("updateByStateid.do")
	public String updateByStateid( HttpServletRequest request,HttpSession session){
		signedBusiness.agree(session, request);		
		return "redirect:/signed/ourmanagment.do";
	}
	
}
