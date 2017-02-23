package org.zn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@RequestMapping(value = "/index.do", method = RequestMethod.GET)
	public String index(ModelMap map) {
		ModelAndView mav = new ModelAndView("index");
		mav.addObject("personId", 12);
		return "admin/index";
	}
	
	@RequestMapping(value = "/albumIndex.do", method = RequestMethod.GET)
	public String albumIndex(ModelMap map) {
		
		return "admin/album/albumIndex";
	}

	@RequestMapping("/jspTest.do")
	public String modelHello(ModelMap map) {
		return "jspTest";
	}

	@RequestMapping("/htmlTest.do")
	public String toIndex(ModelMap map) {
		return "htmlTest";
	}
}
