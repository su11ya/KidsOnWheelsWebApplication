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
import org.springframework.web.bind.annotation.RestController;

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

}
