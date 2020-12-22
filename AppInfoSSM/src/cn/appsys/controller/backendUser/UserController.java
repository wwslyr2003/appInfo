package cn.appsys.controller.backendUser;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.mysql.jdbc.StringUtils;

import cn.appsys.pojo.Category;
import cn.appsys.pojo.DataDictionary;
import cn.appsys.pojo.Info;
import cn.appsys.pojo.User;
import cn.appsys.pojo.Version;
import cn.appsys.service.appinfo.AppInfoService;
import cn.appsys.service.categoryService.CategoryService;
import cn.appsys.service.dataDictionary.DataDictionaryService;
import cn.appsys.service.user.UserService;
import cn.appsys.service.version.VersionService;
import cn.appsys.tools.Constants;
import cn.appsys.tools.PageSupport;
@Controller 
public class UserController {
	 @Resource 
	private UserService  userService;
	 @Resource 
		private AppInfoService  appInfoService;
	 @Resource 
		private  DataDictionaryService  dataDictionaryService ;
	 @Resource 
		private  VersionService versionService;
	 @Resource 
		private  CategoryService  categoryService ;
	
	 //@RequestMapping(value = "/backendlogin.html",method = RequestMethod.POST)
	 @RequestMapping(value = "/backendlogin.html",method = RequestMethod.GET)
	public String userLogin() {
		return "/backendlogin";
	}
	

	@RequestMapping(value = "/backenddologin.html", method = RequestMethod.POST)
	public String dologin(String userCode, String userPassword,
			HttpServletRequest request, HttpSession session) {
		User user = userService.getUserLogin(userCode, userPassword);
		//登陆成功
		if (null != user) {
			session.setAttribute(Constants.USER_SESSION, user);
			//重定向redirect
			return "redirect:/sys/backendmain.html";
		} else {
			request.setAttribute("error", "用户名或密码错误！");
			return "/backendlogin";
		}
	}

	@RequestMapping(value = "/sys/backendmain.html")
	public String main() {
		return "/backend/main";
	}
	@RequestMapping(value = "/logout.html")
	public String logout(HttpSession session) {
		//销毁session
		session.removeAttribute(Constants.USER_SESSION);
		return "/backendlogin";
	}
	
	@RequestMapping(value = "/sys/backendapp.html")
	public String app(Model model,
			@RequestParam(value="querySoftwareName",required=false)String querySoftwareName, 
			@RequestParam(value="queryFlatformId",required=false)String queryFlatformId,
			@RequestParam(value="queryCategoryLevel1",required=false)String queryCategoryLevel1,
			@RequestParam(value="queryCategoryLevel2",required=false)String queryCategoryLevel2,
			@RequestParam(value="queryCategoryLevel3",required=false)String queryCategoryLevel3,
			@RequestParam(value="pageIndex",required=false)String pageIndex) { 
		//查询用户列表
				 int _queryFlatformId=0;
				 int _queryCategoryLevel1=0;
				 int _queryCategoryLevel2=0;
				 int _queryCategoryLevel3=0;
				List<Info> list = null;
				//设置页面容量
		    	int pageSize = Constants.pageSize;
		    	//当前页码
		    	int currentPageNo = 1;
				if(querySoftwareName == null){
					querySoftwareName = "";
				} 
				if(!StringUtils.isNullOrEmpty(queryFlatformId) ){
					_queryFlatformId =Integer.valueOf(queryFlatformId);
				} 
				if(!StringUtils.isNullOrEmpty(queryCategoryLevel1) ){
					_queryCategoryLevel1=Integer.valueOf(queryCategoryLevel1);
				} 
				if(!StringUtils.isNullOrEmpty(queryCategoryLevel2) ){
					_queryCategoryLevel2=Integer.valueOf(queryCategoryLevel2);
				} 
				if(!StringUtils.isNullOrEmpty(queryCategoryLevel3) ){
					_queryCategoryLevel3=Integer.valueOf(queryCategoryLevel3);
				} 
		    	if(pageIndex != null){
		    		try{
		    			currentPageNo = Integer.valueOf(pageIndex);
		    		}catch(NumberFormatException e){
		    			return "redirect:/user/syserror.html";
		    		}
		    	}	
		    	//总数量（表）	
		    	int totalCount=appInfoService.getAppInfoCount(querySoftwareName, _queryFlatformId, _queryCategoryLevel1, _queryCategoryLevel2, _queryCategoryLevel3);
		    	PageSupport pages=new PageSupport();
		    	pages.setCurrentPageNo(currentPageNo);
		    	pages.setPageSize(pageSize);
		    	pages.setTotalCount(totalCount);
		    	int totalPageCount = pages.getTotalPageCount();
		    	
		    	//控制首页和尾页
		    	if(currentPageNo < 1){
		    		currentPageNo = 1;
		    	}else if(currentPageNo > totalPageCount){
		    		currentPageNo = totalPageCount;
		    	}
		    	List<DataDictionary> Flatlist=dataDictionaryService.getFlatFormName();
				 list=appInfoService.getAppInfoList(querySoftwareName, _queryFlatformId,  _queryCategoryLevel1, _queryCategoryLevel2, _queryCategoryLevel3, currentPageNo, pageSize);
				model.addAttribute("appInfoList", list);  
				model.addAttribute("flatFormList", Flatlist);
				model.addAttribute("categoryLevel1List", categoryService.getCategory1List());
				model.addAttribute("queryFlatformId", queryFlatformId);
				model.addAttribute("querySoftwareName", querySoftwareName);

				model.addAttribute("pages", pages); 
		
		return "/backend/applist";
	}
	///sys/check?aid=50&vid=0
	@RequestMapping(value = "/sys/check")
	public String appCheck(Model model,String aid,String vid) {
		int _aid=0;
		int _vid=0;
		if (!StringUtils.isNullOrEmpty(aid)) {
			_aid=Integer.parseInt(aid);
		}
		if (!StringUtils.isNullOrEmpty(vid)) {
			_vid=Integer.parseInt(vid);
		}
		 Info  info = appInfoService.getAppInfoById(_aid);
		 Version ver= versionService.getById(_vid);
		 model.addAttribute("appInfo", info);
		 model.addAttribute("appVersion", ver);
		return "/backend/appcheck";
	}
	//updateAppInfoStatus
	@RequestMapping(value = "/sys/updatestatus")
	public String updateAppInfoStatus(String id,String status) {
		int _aid=0;
		int _status=0;
		if (!StringUtils.isNullOrEmpty(id)) {
			_aid=Integer.parseInt(id);
		}
		if (!StringUtils.isNullOrEmpty(status)) {
			_status=Integer.parseInt(status);
		} 
		int falg =appInfoService.updateAppInfoStatus(_aid, _status);
	 System.out.println(falg);
		return "redirect:/sys/backendapp.html";
	}
	@RequestMapping(value = "/sys/categorylevellist.json")
	@ResponseBody
	public String pwdmodifyonly(String  pid ) {
		 int _pid=0;
		 List<Category> list=null;
		 if (!StringUtils.isNullOrEmpty(pid)) {
			_pid=Integer.parseInt(pid);
		}
		 list= categoryService.getCategoryOtherList(_pid);
		 return JSON.toJSONString(list);
		 }

}
