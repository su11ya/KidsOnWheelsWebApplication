package ca.sheridancollege.su11.controllers;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.sheridancollege.su11.beans.Child;
import ca.sheridancollege.su11.beans.Parent;
//import ca.sheridancollege.su11.repositories.ChildRepository;
//import ca.sheridancollege.su11.repositories.ParentRepository;
import ca.sheridancollege.su11.services.FirebaseService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
//@RequestMapping("/api/kidsonwheels")
//@CrossOrigin(origins="http://localhost:8080")

public class HomeController {

	// Inject the FirebaseService instance
	private final FirebaseService firebaseService;
	

	
//	@GetMapping("/")
//    public String index(Model model) throws ExecutionException, InterruptedException {
//        List<Parent> parents = firebaseService.getAllParents();
//        model.addAttribute("parents", parents);
//        return "index";
//    }
	
	@GetMapping("/")
	public String index(Model model) throws ExecutionException, InterruptedException {
	    List<Parent> parents = firebaseService.getAllParents();
	    model.addAttribute("parents", parents);
	    model.addAttribute("parent", new Parent()); // Add this line
	    return "index";
	}

	
	@PostMapping("/api/kidsonwheels/parent")
    public String addParent(@ModelAttribute Parent parent, Model model) {
        firebaseService.addParent(parent);
        return "redirect:/";
    }
	
	
	@GetMapping("/edit/{id}")
	public String editParent(@PathVariable String id, Model model) throws ExecutionException, InterruptedException {
	    Parent parent = firebaseService.getParentById(id);
	    model.addAttribute("parent", parent);
	    return "edit-parent";
	}
	
	@PostMapping("/edit")
	public String updateParent(@ModelAttribute Parent parent) {
	    firebaseService.updateParent(parent);
	    return "redirect:/";
	}
	
	 @PostMapping("/delete/{id}")
	    public String deleteParent(@PathVariable("id") String id) throws ExecutionException, InterruptedException {
	        
		 	firebaseService.deleteParent(id);
	        return "redirect:/";
	    }
	 

	 
	 @GetMapping("/parent/{parentId}/children")
	    public String viewChildren(@PathVariable String parentId, Model model) throws ExecutionException, InterruptedException {
	        Parent parent = firebaseService.getParentById(parentId);
	        List<Child> children = firebaseService.getChildrenByParentId(parentId);
	        model.addAttribute("parent", parent);
	        model.addAttribute("children", children);
	        model.addAttribute("child", new Child());
	        return "view-children";
	    }

	    @PostMapping("/parent/{parentId}/addChild")
	    public String addChild(@PathVariable String parentId, @ModelAttribute Child child, Model model) {
	        //child.setParentId(parentId);
	        child.setId(parentId);
	        firebaseService.addChild(parentId, child);
	        return "redirect:/parent/" + parentId + "/children";
	    }
	    
	    
	    
	    @PostMapping("/search")
	    public String searchParentByPhoneNumber(@RequestParam("phoneNumber") Long phoneNumber, Model model) throws ExecutionException, InterruptedException {
	        Parent parent = firebaseService.getParentByPhoneNumber(phoneNumber);
	        if (parent != null) {
	            model.addAttribute("searchedParent", parent);
	        } else {
	            model.addAttribute("searchError", "No parent found with the provided phone number.");
	        }
	        return index(model);
	    }
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
}
