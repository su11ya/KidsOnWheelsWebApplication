package ca.sheridancollege.su11.beans;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class Parent {
	
	@Id
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;
	
	@NonNull
	private String userName;
	
	@NonNull
	private String password;
	
	
	@NonNull
	private String firstName;
	
	@NonNull
	private String lastName;
	
	private String gender;
    private Long phone;
    private String email;
   
    
    private String address;
    

    @OneToMany
    private List<Child> children;


	
	

}
