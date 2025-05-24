package com.jsn.userManagement.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class DashboardController {


    //    @RequestMapping(path = "/home", method = RequestMethod.GET)
    @RequestMapping(path = "/dashboard")
    public void redirect(HttpServletResponse response) {
        try {
            response.sendRedirect("swagger-ui/index.html");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
