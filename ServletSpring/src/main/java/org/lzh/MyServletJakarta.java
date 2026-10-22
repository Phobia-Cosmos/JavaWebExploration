package org.lzh;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest; // Change import statement
import jakarta.servlet.http.HttpServletResponse; // Change import statement
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@Controller
public class MyServletJakarta {

    private final MyService myService;

    @Autowired
    public MyServletJakarta(MyService myService) {
        this.myService = myService;
    }

    @GetMapping("/jakarta")
    public void getAllObjects(HttpServletResponse response) throws IOException {
        List<MyObject> objects;
        objects = myService.getAllObjects();
        String jsonResponse = new Gson().toJson(objects);
        response.setContentType("application/json");
        response.getWriter().write(jsonResponse);
    }

    @PostMapping("/jakarta")
    public void createObject(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");

        MyObject newObj = new MyObject();
        newObj.setName(name);
        newObj.setDescription(description);

        myService.createObject(newObj);
        response.setStatus(HttpServletResponse.SC_CREATED);
    }

    @PutMapping("/jakarta/{id}")
    public void updateObject(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");

        MyObject updatedObj = new MyObject();
        updatedObj.setId(id);
        updatedObj.setName(name);
        updatedObj.setDescription(description);

        myService.updateObject(id, updatedObj);
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @DeleteMapping("/jakarta/{id}")
    public void deleteObject(@PathVariable Long id, HttpServletResponse response) throws IOException {
        myService.deleteObject(id);
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
