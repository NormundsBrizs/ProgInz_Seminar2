package lv.venta;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import lv.venta.enums.Degree;
import lv.venta.model.Course;
import lv.venta.model.Grade;
import lv.venta.model.Professor;
import lv.venta.model.Student;
import lv.venta.model.security.MyAuthority;
import lv.venta.model.security.MyUser;
import lv.venta.repo.ICourseRepo;
import lv.venta.repo.IGradeRepo;
import lv.venta.repo.IProfessorRepo;
import lv.venta.repo.IStudentRepo;
import lv.venta.repo.security.IMyAuthorityRepo;
import lv.venta.repo.security.IMyUserRepo;

@SpringBootApplication
public class ProgInSeminar2Application {

	public static void main(String[] args) {
		SpringApplication.run(ProgInSeminar2Application.class, args);
	}

	@Bean
	public CommandLineRunner saveDataInDB(IStudentRepo studRepo, IProfessorRepo profRepo, 
			ICourseRepo courseRepo, IGradeRepo gradeRepo, IMyUserRepo userRepo, IMyAuthorityRepo authRepo) {
		return new CommandLineRunner() {
			
			@Override
			public void run(String... args) throws Exception {
				Professor p1 = new Professor("Karina", "Šķirmante", Degree.master);
				Professor p2 = new Professor("Kārlis", "Immers", Degree.master);
				Professor p3 = new Professor("Raita", "Rollande", Degree.phd);
				profRepo.saveAll(Arrays.asList(p1, p2, p3));
				
				MyAuthority auth1 = new MyAuthority("ADMIN");
				MyAuthority auth2 = new MyAuthority("USER");
				authRepo.saveAll(Arrays.asList(auth1, auth2));
				
				PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
				
				MyUser user1 = new MyUser("admin", encoder.encode("admin"), auth1);
				MyUser user2 = new MyUser("Mikus", encoder.encode("123"), auth2);
				MyUser user3 = new MyUser("testUser", encoder.encode("test"), auth1, auth2);
				userRepo.saveAll(Arrays.asList(user1, user2, user3));
				
				auth1.addUser(user1);
				auth1.addUser(user3);
				auth2.addUser(user2);
				auth2.addUser(user3);
				authRepo.saveAll(Arrays.asList(auth1, auth2));
				
				Student s1 = new Student("Mikus Valts", "Šarovs", user2);
				Student s2 = new Student("Kristers", "Dogudovs", user3);
				studRepo.saveAll(Arrays.asList(s1, s2));
				
				Course c1 = new Course("Programmēšana JAVA", 4, p1);
				Course c2 = new Course("Tīmekļa tehnoloģijas", 3, p2);
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
				Grade g5 = new Grade(10, s2, c1);
				gradeRepo.saveAll(Arrays.asList(g1, g2, g3, g4, g5));
			}
		};
	}
}
