package cn.appsys.controller.devUser;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import cn.appsys.pojo.DataDictionary;
import cn.appsys.pojo.Devuser;
import cn.appsys.pojo.Info;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.appsys.service.appinfo.AppInfoService;
import cn.appsys.service.dataDictionary.DataDictionaryService;
import cn.appsys.service.version.VersionService;
import cn.appsys.tools.Constants;

import com.alibaba.fastjson.JSON;
import com.mysql.jdbc.StringUtils;

@Controller
public class AppInfoController {
	@Resource
	private AppInfoService appinfoServicer;
	@Resource
	private DataDictionaryService dataDictionaryService;
	@Resource
	private VersionService versionService;

	@RequestMapping(value = "/dev/appinfoupdate")
	public String appinfoupdate(Model model, String aid) {
		int _aid = 0;
		if (!(StringUtils.isNullOrEmpty(aid))) {
			_aid = Integer.parseInt(aid);
		}
		Info info = appinfoServicer.getAppInfoById(_aid);
		model.addAttribute("appInfo", info);
		return "/developer/appinfomodify";
	}

	@RequestMapping(value = "/dev/datadictionarylist.json")
	@ResponseBody
	public String datadictionarylist() {
		List<DataDictionary> Flatlist = dataDictionaryService.getFlatFormName();
		return JSON.toJSONString(Flatlist);
	}

	@RequestMapping(value = "/dev/updateappsave")
	// fileUploadError
	public String updateappsave(
			Info info,
			HttpSession session,
			HttpServletRequest request,
			@RequestParam(value = "attach", required = false) MultipartFile attach) {

		String logoPicPath = null;
		String path2 = null;
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
				return "/developer/appinfomodify";
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
				path2 = "/AppInfoSSM/statics/uploadfiles/" + fileName;
			} else {
				System.out.println("上传格式不正确");
				request.setAttribute("fileUploadError", "上传格式不正确");
				return "/developer/appinfomodify";
			}

			info.setLogoLocPath(logoPicPath);
			info.setLogoPicPath(path2);
		}
		info.setCreatedBy(((Devuser) session
				.getAttribute(Constants.USER_SESSION)).getId());
		info.setCreationDate(new Date());
		info.setDevId(((Devuser) session.getAttribute(Constants.USER_SESSION))
				.getId());
		if (appinfoServicer.update(info) == 1) {
			return "redirect:/dev/devdevlist.html";
		}
		return "/developer/appinfomodify";
	}

	// appId+"/sale.jsondelfile.json
	@RequestMapping(value = "/dev/{appId}/sale.json")
	@ResponseBody
	public String sale(@PathVariable String appId) {
		int _aid = 0;
		int falg = 0;
		if (!(StringUtils.isNullOrEmpty(appId))) {
			_aid = Integer.parseInt(appId);
		}
		Info info = appinfoServicer.getAppInfoById(_aid);
		if (info.getStatus() == 2 || info.getStatus() == 5) {
			falg = appinfoServicer.updateAppInfoStatus(_aid, 4);
		} else if (info.getStatus() == 4) {
			falg = appinfoServicer.updateAppInfoStatus(_aid, 5);
		}

		return JSON.toJSONString(falg);
	}

	// appId+"/sale.jsondelfile.json id:id,flag:
	@RequestMapping(value = "/dev/delfile.json")
	@ResponseBody
	public String delfile(String id, String flag) {
		int _aid = 0;
		int falg = 0;
		String fullFilePath = null;
		if (!(StringUtils.isNullOrEmpty(id))) {
			_aid = Integer.parseInt(id);
		}
		if (flag.equals("logo")) {
			fullFilePath = appinfoServicer.getLocPath(_aid);
		} else if (flag.equals("apk")) {
			fullFilePath = versionService.getLocPath(_aid);
		}
		File deleteFile = new File(fullFilePath);
		if (deleteFile.exists() && deleteFile.isFile() && deleteFile.delete()) {
			falg = 1;
		}
		return JSON.toJSONString(falg);
	}

}
