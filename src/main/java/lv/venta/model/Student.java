package lv.venta.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Student {
	
	@Setter(value = AccessLevel.NONE)
	private long ids;
	
	private String name;
	private String surname;
	
	public Student(String name, String surname) {
		//TODO izsaukt set funkcijas 
	}
}
