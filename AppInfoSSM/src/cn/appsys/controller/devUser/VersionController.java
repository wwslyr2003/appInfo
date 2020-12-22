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
import org.springframework.web.multipart.MultipartFile;

import com.mysql.jdbc.StringUtils;

import cn.appsys.pojo.Devuser;
import cn.appsys.pojo.Info;
import cn.appsys.pojo.Version;
import cn.appsys.service.appinfo.AppInfoService;
import cn.appsys.service.version.VersionService;
import cn.appsys.tools.Constants;

@Controller
public class VersionController {
	@Resource
	private VersionService versionService;
	@Resource
	private AppInfoService appInfoService;

	@RequestMapping(value = "/dev/appversionadd")
	public String appversionadd(Model model,  String aid) { 
		int _aid = 0;
		if (!(StringUtils.isNullOrEmpty(aid))) {
			_aid = Integer.parseInt(aid);
		}
 
		List<Version> list = versionService.getListById(_aid); 
		model.addAttribute("appVersionList", list); 
		model.addAttribute("aid", _aid);
		return "/developer/appversionadd";
	}
 
	@RequestMapping(value = "/dev/appversionmodify")
	public String appversionmodify(Model model, String vid, String aid) {
		int _vid = 0;
		int _aid = 0;
		if (!(StringUtils.isNullOrEmpty(aid))) {
			_aid = Integer.parseInt(aid);
		}

		if (!(StringUtils.isNullOrEmpty(vid))) {
			_vid = Integer.parseInt(vid);
		}
		List<Version> list = versionService.getListById(_aid);
		Version ver = versionService.getById(_vid);
		model.addAttribute("appVersionList", list);
		model.addAttribute("appVersion", ver);
		return "/developer/appversionmodify";
	}
	
	
	
	@RequestMapping(value = "/dev/verupdate.html")//    fileUploadError
	public String updatever(Version ver ,
			HttpSession session,
			HttpServletRequest request,
			@RequestParam(value = "attach", required = false) MultipartFile attach) {
		
		String logoPicPath = null;
		 String path2=null;
		 String fileName=null;
		// 判断文件夹是否存在
		if (!attach.isEmpty()) {
			String path = request.getSession().getServletContext()
					.getRealPath("statics" + File.separator + "uploadfiles");
			  fileName = attach.getOriginalFilename();// 原文件名
			String preFix = FilenameUtils.getExtension(fileName);// 文件名的后缀 
		  if (preFix.equalsIgnoreCase("apk") ) { 
				File targetFile = new File(path, fileName);
				if (!targetFile.exists()) {
					targetFile.mkdirs();
				}
				try {
					attach.transferTo(targetFile);
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("文件上传失败");
					request.setAttribute("fileUploadError", "文件上传失败");
				}
				logoPicPath = path + File.separator + fileName;
				path2 ="/AppInfoSSM/statics/uploadfiles/"+fileName;
			} else {
				System.out.println("上传格式不正确");
				request.setAttribute("fileUploadError", "上传格式不正确");
				return "/developer/appversionmodify";
			}
			ver.setDownloadLink(path2);
			ver.setApkLocPath(logoPicPath);
			ver.setApkFileName(fileName);
		}
	
		ver.setModifyBy(((Devuser)session.getAttribute(Constants.USER_SESSION)).getId());
		ver.setModifyDate(new Date()); 
		 if (versionService.update(ver)==1) {
			 return "redirect:/dev/devdevlist.html";
		 } 
			return "/developer/appversionmodify";
	}
	@RequestMapping(value = "/dev/veraddSave.html")//    fileUploadError
	public String veraddSave(Version ver ,
			HttpSession session,
			HttpServletRequest request,
			@RequestParam(value = "a_downloadLink", required = false) MultipartFile attach) {
		
		String logoPicPath = null;
		 String path2=null;
		 String fileName=null;
		// 判断文件夹是否存在
		if (!attach.isEmpty()) {
			String path = request.getSession().getServletContext()
					.getRealPath("statics" + File.separator + "uploadfiles");
			  fileName = attach.getOriginalFilename();// 原文件名
			String preFix = FilenameUtils.getExtension(fileName);// 文件名的后缀 
		  if (preFix.equalsIgnoreCase("apk") ) { 
				File targetFile = new File(path, fileName);
				if (!targetFile.exists()) {
					targetFile.mkdirs();
				}
				try {
					attach.transferTo(targetFile);
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("文件上传失败");
					request.setAttribute("fileUploadError", "文件上传失败");
				}
				logoPicPath = path + File.separator + fileName;
				path2 ="/AppInfoSSM/statics/uploadfiles/"+fileName;
			} else {
				System.out.println("上传格式不正确");
				request.setAttribute("fileUploadError", "上传格式不正确");
				return "/developer/appversionadd";
			}
		}
		ver.setDownloadLink(path2);
		ver.setApkLocPath(logoPicPath);
		ver.setApkFileName(fileName);
		ver.setCreatedBy(((Devuser)session.getAttribute(Constants.USER_SESSION)).getId());
		ver.setCreationDate(new Date()); 
		 if (versionService.add(ver)==1) { 
			 int id= versionService.findIdByAid(ver.getAppId());
			 appInfoService.updateversionId(id, ver.getAppId());
			 return "redirect:/dev/devdevlist.html";
		 } 
			return "/developer/appversionadd";
	}
	

}
