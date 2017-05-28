package com.afanasyeu.academy.controller;

import com.afanasyeu.academy.model.Student;
import com.afanasyeu.academy.model.Teacher;
import com.afanasyeu.academy.service.StudentService;
import com.afanasyeu.academy.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@SessionAttributes({"teacher", "student"})
public class MainController {

	@Autowired
	private TeacherService teacherService;

	@Autowired
	private StudentService studentService;

	@Autowired
	@Qualifier("teacherValidator")
	private Validator teacherValidator;

    @Autowired
    @Qualifier("studentValidator")
    private Validator studentValidator;

	@RequestMapping(value="/signup", method=RequestMethod.GET)
	public String signup(Model model) {
		Teacher teacher = new Teacher();
		model.addAttribute("teacher", teacher);
		return "signup";
	}
	
	@RequestMapping(value="/signup", method=RequestMethod.POST)
	public String signup(@ModelAttribute("teacher") Teacher teacher, BindingResult result, Model model) {
		teacherValidator.validate(teacher, result);
		if (result.hasErrors()){
			return "signup";
		}
        teacherService.insertTeacher(teacher);
        return "redirect:login.html";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login(Model model) {
		Teacher teacher = new Teacher();
		model.addAttribute("teacherLogin", teacher);
		return "login";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(@ModelAttribute("teacherLogin") Teacher teacher, BindingResult result, HttpSession httpSession) {
	    Teacher curTeacher = teacherService.getTeacherByLogin(teacher.getUserName(), teacher.getPassword());
		if (curTeacher != null) {
            httpSession.setAttribute("curTeacher", curTeacher);
			return "redirect:students.html";
		} else {				
			return "redirect:/";
		}
	}

	@RequestMapping(value = "/students", method = RequestMethod.GET)
	public String getStudentsByTeacher(Model model, HttpSession httpSession) {
		Teacher teacher = (Teacher) httpSession.getAttribute("curTeacher");
		if (teacher != null) {
			model.addAttribute("student", studentService.getStudentsByTeacherId(teacher.getId()));
			return "students";
		} else {
			return "redirect:login.html";
		}
	}

	@RequestMapping(value = "/students/{studentId}", method = RequestMethod.GET)
	public String deleteStudentsByTeacher(@PathVariable long studentId, HttpSession httpSession) {
		Teacher teacher = (Teacher) httpSession.getAttribute("curTeacher");
		studentService.deleteStudentById(studentId, teacher.getId());
		return "redirect:/students.html";
	}

    @RequestMapping(value = "/addStudent", method = RequestMethod.GET)
    public String addStudent(Model model, HttpSession httpSession) {
		Teacher curTeacher = (Teacher) httpSession.getAttribute("curTeacher");
        if (curTeacher != null) {
			Student student = new Student();
			model.addAttribute("student", student);
            return "addStudent";
        } else {
            return "login";
        }
    }

	@RequestMapping(value = "/addStudent", method = RequestMethod.POST)
	public String addStudent(@ModelAttribute("student") Student student, Model model,
                             BindingResult result, HttpSession httpSession) {
		if (studentService.getStudentByFNameLNameDBirth(student)) {
			model.addAttribute("message", "Student already exist!");
			return "addStudent";
		}
        studentValidator.validate(student, result);
        if (result.hasErrors()){
            return "addStudent";
        }
        Teacher curTeacher = (Teacher) httpSession.getAttribute("curTeacher");
        student.setTeacherId(curTeacher.getId());
        studentService.insertStudent(student);
        return "redirect:/students.html";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String addStudent(HttpSession httpSession) {
		httpSession.invalidate();
		return "redirect:/";
	}
}