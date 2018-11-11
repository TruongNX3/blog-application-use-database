package com.codegym.controller;

import com.codegym.model.Blog;
import com.codegym.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class BlogController {
    @Autowired
    private BlogService blogService;

    @GetMapping("/create")
    public ModelAndView showList() {
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("blog", new Blog());
        return modelAndView;
    }
    @PostMapping("create")
    public ModelAndView modelAndView(@ModelAttribute Blog blog) {
        blogService.save(blog);
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("blog", new Blog());
        modelAndView.addObject("massage","Created succsesfully");
        return modelAndView;
    }

    @GetMapping("/list")
    public ModelAndView list() {
        List<Blog> lists= blogService.findAll();
        ModelAndView modelAndView = new ModelAndView("list");
        modelAndView.addObject("list", lists);
        return modelAndView;
    }
    @GetMapping("/edit/{id}")
    public ModelAndView editForm(@PathVariable Long id){
        Blog blog = blogService.findById(id);
        if (blog != null) {
            ModelAndView modelAndView = new ModelAndView("edit");
            modelAndView.addObject("blog", blog);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/edit")
    public ModelAndView update(@ModelAttribute Blog blog) {
        blogService.save(blog);
        ModelAndView modelAndView = new ModelAndView("edit");
        modelAndView.addObject("blog",blog);
        modelAndView.addObject("massage", "Edit successfully");
        return modelAndView;
    }
    @GetMapping("/delete/{id}")
    public ModelAndView deleteForm(@PathVariable Long id){
        Blog blog = blogService.findById(id);
        if (blog != null) {
            ModelAndView modelAndView = new ModelAndView("delete");
            modelAndView.addObject("blog", blog);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("error.404");
            return modelAndView;
        }

    }

    @PostMapping("/detele")
    public String delete(@ModelAttribute Blog blog) {
        blogService.remove(blog.getId());
        return "redirect:list";
    }
}
