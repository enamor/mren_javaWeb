package org.zn.controller;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zn.common.JsonModel;
import org.zn.common.Response;
import org.zn.photos.entity.PhotoAlbum;
import org.zn.photos.service.PhotoAlbumService;

@Controller
@RequestMapping("/photos")
public class PhotoAlbumController {
	private static Logger log = Logger.getLogger(PhotoAlbumController.class.getName());

	@Autowired
	private PhotoAlbumService photoAlbumService;
	
	@ResponseBody
	@RequestMapping(value = "/getPhotoAlbum.do", method = RequestMethod.GET)
	public JsonModel getPhotoAlbumList(HttpServletRequest request,
			@RequestParam("categoryId")String categoryId ,
			@RequestParam("start")Integer start ,
			@RequestParam("rows")Integer rows ,
			@RequestParam("state")Integer state) {
		
		JsonModel jsonModel = new JsonModel();
		try {
			List<PhotoAlbum> photos = photoAlbumService.getPhotoAlbumList(categoryId ,start ,rows ,state);
			jsonModel.setData(photos);
		} catch (Exception e) {
			log.error("获取相册列表", e);
			jsonModel.setCode(Response.SC_BAD_REQUEST);
			jsonModel.setMessage(Response.SC_BAD_MESSAGE);
			// TODO: handle exception
		}
		return jsonModel;
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/getLatestPhotoAlbum.do", method = RequestMethod.POST)
	public List<PhotoAlbum> getLatestPhotoAlbum() {
		List<PhotoAlbum> list = photoAlbumService.getLatestPhotoAlbum();
		return list;
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/createAlbum.do", method = RequestMethod.GET)
	public JsonModel createPhotoAlbum(
			@RequestParam("imageUrl")String imageUrl, //封面图
			@RequestParam("title")String title, //标题
			@RequestParam("source")String source,//来源
			@RequestParam("state")Integer state,
			@RequestParam("style")String style){ //状态
		
		JsonModel jsonModel = new JsonModel();
		try {
			PhotoAlbum albumPath = photoAlbumService.createPhotoAlbum(imageUrl, title, source ,state ,style);
			if (null != albumPath) {
				jsonModel.setData(albumPath);
			} else {
				jsonModel.setCode(Response.SC_BAD_REQUEST);
				jsonModel.setMessage(Response.SC_BAD_MESSAGE);
			}
			
		} catch (Exception e) {
			log.error("创建相册失败", e);
			jsonModel.setCode(Response.SC_BAD_REQUEST);
			jsonModel.setMessage(Response.SC_BAD_MESSAGE);
			// TODO: handle exception
		}
		return jsonModel;
	}

}
