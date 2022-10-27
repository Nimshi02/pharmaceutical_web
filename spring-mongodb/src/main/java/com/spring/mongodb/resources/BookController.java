package com.spring.mongodb.resources;


import java.util.List;
import java.util.Optional;
//import javax.validation.Valid;

import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.startup.ClassLoaderFactory.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
//import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.mongodb.exceptions.ResourceNotFoundException;
import com.spring.mongodb.model.Drug;
import com.spring.mongodb.repository.DrugRepostory;
import com.spring.mongodb.model.*;


@RestController
public class BookController {

	@Autowired
	DrugRepostory drugRepository;
	
	@PostMapping("/AddDrug")
	public String saveDrug(@RequestBody Drug drug)
	{
		drugRepository.save(drug);
		return "Drug with id:"+drug.getId()+" added";
	}
	
	@GetMapping("/findAllDrugs")
	public List<Drug>getDrug(){
		return drugRepository.findAll();
	}

	@GetMapping("/findDrugs/{id}")
	public Optional<Drug> getDrug(@PathVariable int id){
		return drugRepository.findById(id);
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteDrug(@PathVariable int id) {
		drugRepository.deleteById(id);
		return "Drug deleted with id: "+id;
	}
	
	
	@PutMapping("/Update/{id}")
		public Optional<Drug>UpdateDrug(@PathVariable(value ="id")int Aid,@Validated @RequestBody Drug New)
		throws ResourceNotFoundException{
		Drug newDrug = drugRepository.findById(Aid)
				.orElseThrow(() -> new ResourceNotFoundException("Drug not found"));
		newDrug.setName(New.getName());
		newDrug.setDescription(New.getDescription());
		newDrug.setPrice(New.getPrice());
		newDrug.setQty(New.getQty());
		
		final Drug UpdateDrug = drugRepository.save(newDrug);
		return drugRepository.findById(Aid);
	}
	
	
	 @RequestMapping("/Display")
	    public String Save(Model model) {
	        model.addAttribute("DrugList", drugRepository.findAll());
	        return "redirect:index.html";
	        //RedirectView redirectView = new RedirectView();
	    	//redirectView.setUrl("Display.html");
	    	//return redirectView;
	 }
	

	@RequestMapping(value = "/addDrug", method = RequestMethod.POST)
	public RedirectView addnewDrug(@ModelAttribute Drug drug) {
	drugRepository.save(drug);
	RedirectView redirectView = new RedirectView();
	redirectView.setUrl("/");
	return redirectView;
	}
	
	
	 @RequestMapping(value="/addproduct")    
	 public ModelAndView direct(Model model)  
	 {    
	 ModelAndView modelAndView = new ModelAndView();
	 modelAndView.setViewName("/");             
	 return modelAndView;    
	 }   
	//@RequestMapping(value = "/search")
	//public String search(Model model, @RequestParam String search) {
	//model.addAttribute("DrugList", SaveDrugRepository.searchDrug(search));
		//model.addAttribute("search", search);
		//return "home"; 	}
	 
	 
	 
	 @RequestMapping(value="/login/{txtuname}/{txtpassword}")
	 public ModelAndView DirectAdminPage(@ModelAttribute Admin_login admin,@PathVariable(value ="txtuname")String username,@PathVariable(value ="txtpassword")String password)
	 {
		 ModelAndView modelAndView= new ModelAndView();
		boolean val=admin.validate(username,password);
		 if(val)
		 {
			 modelAndView.setViewName("/");
			modelAndView.addObject("Admin_login", admin);   
		 }
		 else
		 {
		 modelAndView.setViewName("admin");
		modelAndView.addObject("Admin_login", admin); 
		 }
		 return modelAndView;
		 
	 }
	 
	 
	 

}
		


