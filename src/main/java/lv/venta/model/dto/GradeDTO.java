package lv.venta.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GradeDTO {
	private String studentName;
	private String studentSurname;
	private String courseTitle;
	private int gradeValue;
}
