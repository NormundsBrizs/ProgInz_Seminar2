package lv.venta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import lv.venta.model.Student;
import lv.venta.service.ICRUDStudentService;

@Controller
@RequestMapping("/student/crud")
public class StudentCRUDController {

	@Autowired
	private ICRUDStudentService studService;

	@GetMapping("/all") // localhost:8080/student/crud/all
	public String getControllerAllStudents(Model model) {
		try {
			model.addAttribute("package", studService.retrieveAll());
			return "show-all-students";
		} catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}
	}

	@GetMapping("/all/{id}") // localhost:8080/student/crud/all/1
	public String getControllerRetrieveStudentById(@PathVariable(name = "id") long id, Model model) {
		try {
			model.addAttribute("package", studService.retrieveById(id));
			return "show-one-student";
		} catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}
	}

	@GetMapping("/delete/{id}") // localhost:8080/student/crud/delete/2
	public String getDeleteStudentById(@PathVariable(name = "id") long id, Model model) {
		try {
			studService.deleteById(id);
			model.addAttribute("package", studService.retrieveAll());
			return "show-all-students";
		} catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}
	}

	@GetMapping("/add") // localhost:8080/student/crud/add
	public String getControllerCreateStudent(Model model) {
		model.addAttribute("student", new Student());
		return "add-student-page";
	}

	@PostMapping("/add")
	public String postController(@Valid Student student, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "add-student-page";
		}

		try {
			studService.create(student);
			return "redirect:/student/crud/all";
		} catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}
	}
}
