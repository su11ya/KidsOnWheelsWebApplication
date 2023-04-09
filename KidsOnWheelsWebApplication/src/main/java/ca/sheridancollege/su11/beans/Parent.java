package ca.sheridancollege.su11.beans;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
@Entity
public class Parent {
	
	@Id
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String id;
	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	private String gender;
    private Long phone;
    private String email;
    private String address;
    


    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Child> children;

	
	

}
