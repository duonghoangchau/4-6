package com.example.bonsau.controller;

import com.example.bonsau.Repository.RoleRepository;
import com.example.bonsau.Services.RoleServices;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import com.example.bonsau.Entities.Role;

@Controller
@RequiredArgsConstructor
@RequestMapping("/roles")
public class RoleController {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private RoleServices roleService;


    @GetMapping("")
    public String listRoles(Model model) {
        List<Role> roles = roleService.getAllRole();
        model.addAttribute("roles", roles);
        return "Role/Role-list";
    }


    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("role", new Role());
        return "Role/add-role";
    }

    @PostMapping("/add")
    public String addRole(@Valid Role role, BindingResult result) {
        roleService.addRole(role);
        return "redirect:/roles";
    }


    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Role role = roleService.getRoleById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid role Id:" + id));
        model.addAttribute("role", role);
        return "Role/update-role";
    }

    // POST request to update category
    @PostMapping("/update/{role_id}")
    public String updateRole(@PathVariable() Long role_id, @Valid Role role) {
        roleService.updateRole(role);
        return "redirect:/roles";
    }

    @GetMapping("/delete/{id}")
    public String deleteRole(@PathVariable("id") Long id, Model model) {
        Role role    = roleService.getRoleById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid role Id:"+ id));
        roleService.deleteRoleById(id);
        model.addAttribute("roles", roleService.getAllRole());
        return "redirect:/roles";
    }
}