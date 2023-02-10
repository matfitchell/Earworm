

// import java.time.LocalDate;
// import java.time.LocalDateTime;
// import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

//import com.example.Tutorial.registration.token.ConfirmationToken;
//import com.example.Tutorial.registration.token.ConfirmationTokenService;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    // private final BCryptPasswordEncoder bCryptPasswordEncoder;
    // private final ConfirmationTokenService confirmationTokenService;
    private final static String USER_NOT_FOUND_MSG = "user with email %s not found";

    @Autowired
    public StudentService(StudentRepository studentRepository
    /* ConfirmationTokenService confirmationTokenService */) {
        this.studentRepository = studentRepository;
        // this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        // this.confirmationTokenService = confirmationTokenService;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public String addNewStudent(Student student) {
        Optional<Student> userByEmail = studentRepository.findStudentByEmail(student.getEmail());
        if (userByEmail.isPresent()) {
            throw new IllegalStateException("Email taken");
        }
        // String encodedPassword = bCryptPasswordEncoder.encode(student.getPassword());
        student.setPassword(student.getPassword());
        studentRepository.save(student);

        String token = UUID.randomUUID().toString();
        // ConfirmationToken confirmationToken = new ConfirmationToken(token,
        // LocalDateTime.now(),
        // LocalDateTime.now().plusMinutes(15), student);

        // confirmationTokenService.saveConfirmationToken(confirmationToken);

        return token;
    }

    public void deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if (!exists) {
            throw new IllegalStateException("student with id " + studentId + " does not exist");
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId, String username, String email) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("student with id " + studentId + " does not exist"));

        if (username != null && username.length() > 0 && !Objects.equals(student.getUsername(), username)) {
            student.setUsername(username);
        }

        if (email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)) {
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
            if (studentOptional.isPresent()) {
                throw new IllegalStateException("email taken");
            }
            student.setEmail(email);
        }
    }

    @Transactional
    public void setAge(Long id, Integer age) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("student with id" + id + "not found"));
        // student.setAge(age);
    }

}
