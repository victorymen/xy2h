package com.xy2h.sso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/page")
public class PageController {

	@RequestMapping("/register")
	public String showRegister(){
		return "register";
	}

	@RequestMapping("/login")
	public String showLogin(String redirect,Model model){
		model.addAttribute("redirect",redirect);
		return "login";
	}
	@RequestMapping("/logout")
	public String showLogout(){
		return "index";
	}

	@RequestMapping("/admin/register")
	public String showAdminRegister(){
		return "admin/register";
	}

	@RequestMapping("/admin/login")
	public String showAdminLogin(String redirect,Model model){
		model.addAttribute("redirect",redirect);
		return "admin/login";
	}
}
