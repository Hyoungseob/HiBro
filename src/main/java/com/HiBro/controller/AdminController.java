package com.HiBro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController{
    @GetMapping("/admin")
    public String admin(){
        return "administrator/admin_member";
    }

    @GetMapping("/admin/point")
    public String point(){
        return "administrator/admin_point";
    }

    @GetMapping("/admin/inquiry")
    public String inquiry(){
        return "administrator/admin_inquiry";
    }
}
