package lv.venta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import lv.venta.service.IFilterService;

@Controller
@RequestMapping("/filter")
public class FilterController {

	@Autowired
	private IFilterService filterService;
	
	@GetMapping("/grades/student/{id}") // localhost:8080/filter/grades/student/1
	public String getControllerGradesByStudentId(@PathVariable(name = "id") long id, Model model) {
		try {
			model.addAttribute("package", filterService.filterGradesByStudentId(id));
			return "show-grades";
		} catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}
	}
	
	
}
