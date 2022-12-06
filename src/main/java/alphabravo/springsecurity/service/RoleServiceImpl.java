package alphabravo.springsecurity.service;

import alphabravo.springsecurity.model.Role;
import alphabravo.springsecurity.repositories.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepo roleRepo;

    @Autowired
    public RoleServiceImpl(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepo.findAll();
    }
}
