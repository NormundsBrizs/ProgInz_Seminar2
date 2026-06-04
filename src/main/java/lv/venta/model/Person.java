package lv.venta.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// inheritance only within Java, no @table or @Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@MappedSuperclass
public class Person {

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
}
