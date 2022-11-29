package alphabravo.springsecurity.InitValues;

import alphabravo.springsecurity.model.Person;
import alphabravo.springsecurity.model.Role;
import alphabravo.springsecurity.repositories.PersonRepo;
import alphabravo.springsecurity.repositories.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Set;

@Component
public class DataInitValues {
    private final RoleRepo roleRepo;
    private final PersonRepo personRepo;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DataInitValues(RoleRepo roleRepo, PersonRepo personRepo, PasswordEncoder passwordEncoder) {
        this.roleRepo = roleRepo;
        this.personRepo = personRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        Role user = roleRepo.findRoleByRole("ROLE_USER");
        if (user == null) user = new Role("ROLE_USER");
        roleRepo.save(user);

        Role admin = roleRepo.findRoleByRole("ROLE_ADMIN");
        if (admin == null) admin = new Role("ROLE_ADMIN");
        roleRepo.save(admin);

        Person HR = personRepo.findPersonByUsername("HR");
        if (HR == null) HR = new Person("HR", "cucumber", Set.of(user));
        HR.setPassword(passwordEncoder.encode(HR.getPassword()));
        personRepo.save(HR);

        Person chief = personRepo.findPersonByUsername("chief");
        if (chief == null) chief = new Person("chief", "banana", Set.of(user, admin));
        chief.setPassword(passwordEncoder.encode(chief.getPassword()));
        personRepo.save(chief);
    }
}
