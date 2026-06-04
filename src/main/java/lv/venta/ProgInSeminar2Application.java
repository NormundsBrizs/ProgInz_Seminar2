package lv.venta;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import lv.venta.enums.Degree;
import lv.venta.model.Course;
import lv.venta.model.Grade;
import lv.venta.model.Professor;
import lv.venta.model.Student;
import lv.venta.repo.ICourseRepo;
import lv.venta.repo.IGradeRepo;
import lv.venta.repo.IProfessorRepo;
import lv.venta.repo.IStudentRepo;

@SpringBootApplication
public class ProgInSeminar2Application {

	public static void main(String[] args) {
		SpringApplication.run(ProgInSeminar2Application.class, args);
	}

	@Bean
	public CommandLineRunner saveDataInDB(IStudentRepo studRepo, IProfessorRepo profRepo, ICourseRepo courseRepo, IGradeRepo gradeRepo) {
		return new CommandLineRunner() {
			
			@Override
			public void run(String... args) throws Exception {
				Professor p1 = new Professor("Karina", "Šķirmante", Degree.master);
				Professor p2 = new Professor("Kārlis", "Immers", Degree.master);
				Professor p3 = new Professor("Raita", "Rollande", Degree.phd);
				profRepo.saveAll(Arrays.asList(p1, p2, p3));
				
				Student s1 = new Student("Mikus Valts", "Šarovs");
				Student s2 = new Student("Kristers", "Dogudovs");
				studRepo.saveAll(Arrays.asList(s1, s2));
				
				Course c1 = new Course("Programmēšana JAVA", 4, p1);
				Course c2 = new Course("Tīmekļa tehnoloģijas", 6, p2);
				Course c3 = new Course("Programmatūras inženierija I", 6, p1);
				c3.addProfessor(p3);
				courseRepo.saveAll(Arrays.asList(c1, c2, c3));
				
				p1.addCourse(c1);
				p1.addCourse(c3);
				p2.addCourse(c2);
				p3.addCourse(c3);
				profRepo.saveAll(Arrays.asList(p1, p2, p3));
				
				Grade g1 = new Grade(3, s1, c1);
				Grade g2 = new Grade(6, s1, c2);
				Grade g3 = new Grade(8, s2, c1);
				Grade g4 = new Grade(4, s2, c2);
				gradeRepo.saveAll(Arrays.asList(g1, g2, g3, g4));
			}
		};
	}
}
