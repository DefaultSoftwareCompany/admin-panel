package com.dsc.controller;

import com.dsc.model.User;
import com.dsc.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/api/admin/user/all")
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/api/owner/user/toggle/admin/{userId}")
    public ModelAndView toggleAdminRole(@PathVariable Long userId, ModelAndView modelAndView) {
        service.toggleAdminRole(userId);
        modelAndView.setViewName("redirect:/api/admin/user/all");
        return modelAndView;
    }

    @GetMapping("/api/admin/user/toggle/admin/{userId}")
    public ModelAndView toggleEditorRole(@PathVariable Long userId, ModelAndView modelAndView) {
        service.toggleEditorRole(userId);
        modelAndView.setViewName("redirect:/api/admin/user/all");
        return modelAndView;
    }

    @GetMapping("/api/owner/user/delete/{userId}")
    public ModelAndView delete(@PathVariable Long userId, ModelAndView modelAndView) {
        modelAndView.setViewName("redirect:/api/admin/user/all");
        service.delete(userId);
        return modelAndView;
    }

}
