package org.lzh;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class MyServlet {

    private final MyService myService;

    @Autowired
    public MyServlet(MyService myService) {
        this.myService = myService;
    }

    @GetMapping("/j2ee")
    public void getAllObjects(HttpServletResponse response) throws IOException {
        List<MyObject> objects;
        objects = myService.getAllObjects();
        String jsonResponse = new Gson().toJson(objects);
        response.setContentType("application/json");
        response.getWriter().write(jsonResponse);
    }

    @PostMapping("/j2ee")
    public void createObject(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");

        MyObject newObj = new MyObject();
        newObj.setName(name);
        newObj.setDescription(description);

        myService.createObject(newObj);
        response.setStatus(HttpServletResponse.SC_CREATED);
    }

    @PutMapping("/j2ee/{id}")
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

    @DeleteMapping("/j2ee/{id}")
    public void deleteObject(@PathVariable Long id, HttpServletResponse response) throws IOException {
        myService.deleteObject(id);
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
