package cn.appsys.controller.devUser;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;

import org.apache.commons.lang.math.RandomUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.mysql.jdbc.StringUtils;

import cn.appsys.pojo.Category;
import cn.appsys.pojo.DataDictionary;
import cn.appsys.pojo.Devuser;
import cn.appsys.pojo.Info;
import cn.appsys.pojo.Version;
import cn.appsys.service.appinfo.AppInfoService;
import cn.appsys.service.categoryService.CategoryService;
import cn.appsys.service.dataDictionary.DataDictionaryService;
import cn.appsys.service.devUser.DevUserService;
import cn.appsys.service.version.VersionService;
import cn.appsys.tools.Constants;
import cn.appsys.tools.PageSupport;  
@Controller
public class DevUserController {
	@Resource
	private AppInfoService appInfoService;
	@Resource
	private DataDictionaryService dataDictionaryService; 
	@Resource
	private CategoryService categoryService;
	 
	@Resource
	private DevUserService devUserService;

	@Resource
	private VersionService versionService;
	@RequestMapping(value = "/devlogin.html",method = RequestMethod.GET)
	public String userLogin() {
		return "/devlogin";  
	}
	@RequestMapping(value = "/devdologin.html", method = RequestMethod.POST)
	public String dologin(String devCode, String devPassword,
			HttpServletRequest request, HttpSession session) {
		Devuser devuser = devUserService.getUserLogin(devCode, devPassword);
		//登陆成功
		if (null != devuser) {
			session.setAttribute(Constants.USER_SESSION, devuser);
			//重定向redirect
			return "redirect:/dev/devmain.html";
		} else {
			request.setAttribute("error", "用户名或密码错误！");
			return "/devlogin.html";
		}
	}
	@RequestMapping(value = "/dev/devmain.html")
	public String main() {
		return "/developer/main";
	}
	@RequestMapping(value = "/dev/devdevlist.html")
	public String devlist(Model model,
			@RequestParam(value="querySoftwareName",required=false)String querySoftwareName, 
			@RequestParam(value="queryFlatformId",required=false)String queryFlatformId,
			@RequestParam(value="queryCategoryLevel1",required=false)String queryCategoryLevel1,
			@RequestParam(value="queryCategoryLevel2",required=false)String queryCategoryLevel2,
			@RequestParam(value="queryCategoryLevel3",required=false)String queryCategoryLevel3, 
			@RequestParam(value="queryStatus",required=false)String queryStatus, 
			@RequestParam(value="id",required=false)String id,
			@RequestParam(value="pageIndex",required=false)String pageIndex) { 
	 //查询用户列表
				 int _queryFlatformId=0;
				 int _queryCategoryLevel1=0;
				 int _queryCategoryLevel2=0;
				 int _queryCategoryLevel3=0; 
				 int _queryStatus=0;
				 int _id=0;
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
				if(!StringUtils.isNullOrEmpty(queryStatus) ){
					_queryStatus=Integer.valueOf(queryStatus);
				} 
				if(!StringUtils.isNullOrEmpty(id) ){
					_id=Integer.valueOf(id);
				} 
		    	if(pageIndex != null){
		    		try{
		    			currentPageNo = Integer.valueOf(pageIndex);
		    		}catch(NumberFormatException e){
		    			return "redirect:/user/syserror.html";
		    		}
		    	}	
		    	//总数量（表）	
		    	int totalCount=appInfoService.devgetAppInfoCount(querySoftwareName, _queryFlatformId, _queryCategoryLevel1, _queryCategoryLevel2, _queryCategoryLevel3,_queryStatus, _id);
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
		    	List<DataDictionary> statusList=dataDictionaryService.getStatusName();
				 list=appInfoService.devgetAppInfoList(querySoftwareName, _queryFlatformId, _queryCategoryLevel1, _queryCategoryLevel2, _queryCategoryLevel3,_queryStatus, _id,currentPageNo, pageSize);
 
				 model.addAttribute("appInfoList", list);  
				model.addAttribute("flatFormList", Flatlist);

				model.addAttribute("queryStatus", queryStatus);
				
				model.addAttribute("categoryLevel1List", categoryService.getCategory1List()); 
				model.addAttribute("statusList", statusList);
				model.addAttribute("queryFlatformId", queryFlatformId);
				model.addAttribute("querySoftwareName", querySoftwareName); 
				model.addAttribute("pages", pages);  
				
		return "/developer/appinfolist";
	}
	@RequestMapping(value = "/dev/categorylevellist.json")
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
	
	
	@RequestMapping(value = "/dev/appview/{aid}")
	public String appCheck(Model model,@PathVariable String aid) {
		int _aid=0; 
		if (!StringUtils.isNullOrEmpty(aid)) {
			_aid=Integer.parseInt(aid);
		} 
		 Info  info = appInfoService.getAppInfoById(_aid); 
		 List<Version> list=versionService.getListById(_aid);
		 model.addAttribute("appInfo", info); 
		 model.addAttribute("appVersionList", list); 
		return "/developer/appinfoview";
	}
	@RequestMapping(value = "/dev/delapp.json")
	@ResponseBody
	public String deleteapp(String  id ) {
		 int _aid=0;
		 
		 if (!StringUtils.isNullOrEmpty(id)) {
			_aid=Integer.parseInt(id);
		}
		 
		versionService.del(_aid);
		 if (!(appInfoService.del(_aid)>0)) {
				return "false";
			}
		 return "true";
		 }
	
	
	@RequestMapping(value = "/dev/addapp")
	public String addApp(Model model ) {

    	List<DataDictionary> Flatlist=dataDictionaryService.getFlatFormName();
		model.addAttribute("flatFormList", Flatlist);
		return "/developer/appinfoadd";
	}
	
	
	@RequestMapping(value = "/dev/apkexist.json")
	@ResponseBody
	public String ApkCount(String  APKName) { 
		 if (StringUtils.isNullOrEmpty(APKName)) {
			return "empty";
		}
		 if (appInfoService.getAPKcount(APKName)>0) {
			return "exist";
		}
		 return "noexist";
		 }
	
	@RequestMapping(value = "/dev/addappsave")//    fileUploadError
	public String addAppsave(Info info ,HttpSession session,
			HttpServletRequest request,
			@RequestParam(value = "a_logoPicPath", required = false) MultipartFile attach) {
		
		String logoPicPath = null;
		 String path2=null;
		// 判断文件夹是否存在
		if (!attach.isEmpty()) {
			String path = request.getSession().getServletContext()
					.getRealPath("statics" + File.separator + "uploadfiles");
			String oldFileName = attach.getOriginalFilename();// 原文件名
			String preFix = FilenameUtils.getExtension(oldFileName);// 文件名的后缀
			int filesize = 5000000;// 文件的大小
			if (attach.getSize() > filesize) {
				request.setAttribute("fileUploadError", "上传大小不能超过500kb");
				System.out.println("失败");
				return "/developer/appinfoadd";
			} else if (preFix.equalsIgnoreCase("jpg")
					|| preFix.equalsIgnoreCase("png")
					|| preFix.equalsIgnoreCase("jpeg")
					|| preFix.equalsIgnoreCase("pneg")) {

				String fileName = System.currentTimeMillis()
						+ RandomUtils.nextInt(1000000) + "Personal.jpg";
				File targetFile = new File(path, fileName);
				if (!targetFile.exists()) {
					targetFile.mkdirs();
				}
				try {
					attach.transferTo(targetFile);
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("图片上传失败");
					request.setAttribute("fileUploadError", "图片上传失败");
				}
				logoPicPath = path + File.separator + fileName;
				path2 ="/AppInfoSSM/statics/uploadfiles/"+fileName;
			} else {
				System.out.println("上传格式不正确");
				request.setAttribute("fileUploadError", "上传格式不正确");
				return "redirect:/dev/addapp";
			}
		}
		info.setCreatedBy(((Devuser)session.getAttribute(Constants.USER_SESSION)).getId());
		info.setCreationDate(new Date()); 
		info.setDevId(((Devuser)session.getAttribute(Constants.USER_SESSION)).getId());
		info.setLogoLocPath(logoPicPath);
		info.setLogoPicPath(path2);
		 if (appInfoService.add(info)==1) {
			 return "redirect:/dev/devdevlist.html";
		 } 
			return "redirect:/dev/addapp";
	}
	
	
 
}
