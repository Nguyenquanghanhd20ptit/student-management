package laptrinh.Controller;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.TinyBitSet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import laptrinh.Service.studentService_interface;
import laptrinh.entity.student;
import laptrinh.repository.student_repository;
import laptrinh.validate.editStudentValidate;
import laptrinh.validate.studentValidate;


@Controller
public class StudentController {
	@Autowired
	private student_repository student_repository;
	
	@Autowired
	private studentValidate studentValidate;
	@Autowired
	private studentService_interface studentService;
	@Autowired
	private editStudentValidate editStudentValidate;
	@GetMapping("/listStudent/{s}/{page}")
	public String listStudent(HttpServletRequest request,Model model,@PathVariable(name ="s") String s,@PathVariable(name = "page")int page){
		int quantyPage  = 2;
		Pageable pageable = PageRequest .of(page-1, quantyPage);
		List<student> ListStudent = new ArrayList<student>();
		if(s.equals("gpa")) {
			ListStudent = student_repository.findStudentGpa(pageable);
		}
		else {
			ListStudent  = student_repository.findStudentHoten(pageable);
		}
		List<student> ListStudent2 = student_repository.findAll();
		int totalPage = 1;
		 if(ListStudent2.size() % quantyPage == 0) {
			 totalPage = ListStudent2.size()/quantyPage;
		 }
		 else {
			 totalPage = (ListStudent2.size()/quantyPage)+1;
		}
		 model.addAttribute("totalPage", totalPage);
		model.addAttribute("ListStudent", ListStudent);
		return "student/listStudent";
	}
	
	@GetMapping("/addStudent")
	public String addStudent(Model model) {
		student student = new student();
		model.addAttribute("student", student);
		return "student/addStudent";
	}
	
	@PostMapping("/addStudent")
	public String AddStudent(Model model,@ModelAttribute(name = "student")@Valid student student,BindingResult bindingResult) {
		System.out.println(student.getNgaysinh());
		 studentValidate.validate(student, bindingResult);
		 if(bindingResult.hasErrors()) {
			 return "student/addStudent";
		 }
		 student.setXeploai(studentService.xepHang(student.getGpa()));
		 student_repository.save(student);
		 return "redirect:/listStudent/msv/1";
	}
	@GetMapping("/editStudent/{id}")
	public String editStudent(Model model,@PathVariable(name = "id")int id) {
		 student student = student_repository.getById(id);
		 model.addAttribute("student", student);
		 return "student/editStudent";
	}
	@PostMapping("/editStudent/{id}")
	public String editStudent(Model model,@ModelAttribute(name = "student")@Valid student student,BindingResult bindingResult) {
		 editStudentValidate.validate(student, bindingResult);
		 if(bindingResult.hasErrors()) {
			 return "student/editStudent";
		 }
		 student.setXeploai(studentService.xepHang(student.getGpa()));
		 student_repository.save(student);
		 return "redirect:/listStudent/msv/1";
	}
	@GetMapping("/deleteStudent/{id}")
	public String deleteUser(Model model,@PathVariable(name = "id")int id) {
		student_repository.deleteById(id);
		 return "redirect:/listStudent/msv/1";
	}
	@GetMapping("/detailStudent/{id}")
	public String detailStudent(Model model,@PathVariable(name = "id")int id) {
		student student = student_repository.getById(id);
		model.addAttribute("student", student);
		return "student/detailStudent";
	}
	
	@PostMapping("/searchStudent/{s}/{page}")
	public String searchStudent(HttpServletRequest request,HttpSession session,Model model) {
		String category = request.getParameter("category");
		String search = request.getParameter("search");
		session.setAttribute("Category",category);
		session.setAttribute("Search", search);
		return "redirect:/searchStudent/msv/1";
	}
	@GetMapping("/searchStudent/{s}/{page}")
	public String SearchStudent(HttpServletRequest request, HttpSession session, 
			Model model,@PathVariable(name = "page") int page) {
		String category = (String) session.getAttribute("Category");
		String search = (String) session.getAttribute("Search");
		int quantyPage  = 2;
		Pageable pageable = PageRequest .of(page-1, quantyPage);
		List<student> ListStudent = new ArrayList<student>();
		List<student> ListStudent2 = new ArrayList<student>();
		if(category.equals("msv")) {
			ListStudent = student_repository.searchStudentMsv(pageable,search);
			ListStudent2 = student_repository.searchStudentMsvTotal(search);
		}
		else {
			ListStudent  = student_repository.searchStudentName(pageable,search);
			ListStudent2 = student_repository.searchStudentNameTotal(search);
		}
		int totalPage = 1;
		 if(ListStudent2.size() % quantyPage == 0) {
			 totalPage = ListStudent2.size()/quantyPage;
		 }
		 else {
			 totalPage = (ListStudent2.size()/quantyPage)+1;
		}
		 model.addAttribute("totalPage", totalPage);
		model.addAttribute("ListStudent", ListStudent);
		return "student/searchStudent";
	}
}
