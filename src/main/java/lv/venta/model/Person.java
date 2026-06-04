package lv.venta.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// inheritance also in DB
@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "PersonTable")
@Inheritance(strategy = InheritanceType.JOINED)
public class Person {

	@Column(name = "Idp")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(value = AccessLevel.NONE)
	private long idp;
	
	@Column(name = "Name")
	@NotNull
	@NotEmpty
	@Pattern(regexp = "[A-Ž]{1}[a-ž]{2,20}([ ]{1}([A-Ž]{1}[a-ž]{2,20}))?", message="Jāsatur tikai burti")
	private String name;
	
	@Column(name = "Surname")
	@NotNull
	@NotEmpty
	@Pattern(regexp = "[A-Ž]{1}[a-ž]{2,20}([ -]{1}([A-Ž]{1}[a-ž]{2,20}))?", message="Jāsatur tikai burti")
	private String surname;
	
	public Person(String name, String surname) {
		setName(name);
		setSurname(surname);
	}
}
