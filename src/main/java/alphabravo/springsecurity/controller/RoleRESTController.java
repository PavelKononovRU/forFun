package alphabravo.springsecurity.controller;

import alphabravo.springsecurity.model.Role;
import alphabravo.springsecurity.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class RoleRESTController {
    private final RoleService roleService;

    @Autowired
    public RoleRESTController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/roles")
    public ResponseEntity<List<Role>> allRoles() {
        List<Role> roles = roleService.getAllRoles();
        return ((roles != null) && (!roles.isEmpty()))
                ? new ResponseEntity<>(roles, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
