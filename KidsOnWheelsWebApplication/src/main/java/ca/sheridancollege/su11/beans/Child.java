package ca.sheridancollege.su11.beans;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
public class Child {
	@Id
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String childId;
	
	@NonNull
	private String firstName;
	
	@NonNull
    private String lastName;
	
    private String gender;
    private String dateOfBirth;

    @ManyToOne
    private Parent parent;

}
