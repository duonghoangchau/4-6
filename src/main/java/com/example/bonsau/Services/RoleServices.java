package com.example.bonsau.Services;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.bonsau.Entities.Role;
import com.example.bonsau.Repository.RoleRepository;
import java.util.List;
import java.util.Optional;
@RequiredArgsConstructor
@Transactional
@Service
public class RoleServices {
    @Autowired
    private RoleRepository roleRepository;
    public List<Role> getAllRole(){
        return roleRepository.findAll();
    }
    public Optional<Role> getRoleById(Long id) {
        return roleRepository.findById(id);
    }

    public void addRole(Role role) {
        roleRepository.save(role);
    }

    public void updateRole(Role role) {
        Role existingRole = roleRepository.findById(role.getRoleID())
                .orElseThrow(() -> new IllegalStateException("Role with ID " +
                        role.getRoleID() + " does not exist."));
        existingRole.setRoleName(role.getRoleName());
        roleRepository.save(existingRole);
    }

    public void deleteRoleById(Long id ) {
        if(!roleRepository.existsById(id)) {
            throw new IllegalStateException("Role with ID " + id + " does not exist.");
        }
        roleRepository.deleteById(id);
    }
}
