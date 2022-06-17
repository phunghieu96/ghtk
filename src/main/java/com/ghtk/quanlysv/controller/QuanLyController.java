package com.ghtk.quanlysv.controller;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ghtk.quanlysv.service.ReadExcel;

@Controller
public class QuanLyController {
	@Autowired
	private ReadExcel readExcel;

	@GetMapping("")
	public String index() {
		return "index";
	}
	@PostMapping("/add")
	public String addDataFromExcelFile(@RequestParam("excelFile") MultipartFile excelFile) throws IOException, ParseException {
		readExcel.addDataFromExcelFile(excelFile);
		return "redirect:search";
	}

	@GetMapping("/search")
	public String search(Model model) {
		model.addAttribute("students", readExcel.getAllStudent());
		return "search";
	}

	@PostMapping("/search")
	public String searchStudent(@RequestParam(name = "id", defaultValue = "")
	String id, @RequestParam(name = "name", defaultValue = "") String name, Model model) {
		model.addAttribute("students", readExcel.searchStudent(id, name));
		return "search";
	}
}
