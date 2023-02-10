
// import java.time.LocalDate;
// import java.util.List;

// import org.springframework.boot.CommandLineRunner;
// import org.springframework.cglib.core.Local;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;

// @Configuration
// public class StudentConfig {

// @Bean
// CommandLineRunner commandLineRunner(
// StudentRepository repository) {
// return args -> {
// Student maria = new Student(
// "marialoka",
// "maria56@gmail.com",
// LocalDate.of(2000, 5, 4).getYear(),
// "password123",
// 123);

// Student sancho = new Student(
// "sanchito",
// "ranch@gmail.com",
// LocalDate.of(2004, 7, 12).getYear(),
// "password123",
// 345);

// repository.saveAll(List.of(maria, sancho));
// };
// }
// }
