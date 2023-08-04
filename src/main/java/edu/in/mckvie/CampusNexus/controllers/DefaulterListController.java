package edu.in.mckvie.CampusNexus.controllers;

import edu.in.mckvie.CampusNexus.entities.User;
import edu.in.mckvie.CampusNexus.repositories.DefaulterListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/permanent")
public class DefaulterListController {
    @Autowired
    private DefaulterListRepository defaulterListRepository;

    @GetMapping("/list/{sem}")
    public List<User> getList(@PathVariable String sem){
        List<User> ls=null;
        switch(sem){
            case "sem1":ls=this.defaulterListRepository.findBySem1();break;
            case "sem2":ls=this.defaulterListRepository.findBySem2();break;
            case "sem3":ls=this.defaulterListRepository.findBySem3();break;
            case "sem4":ls=this.defaulterListRepository.findBySem4();break;
            case "sem5":ls=this.defaulterListRepository.findBySem5();break;
            case "sem6":ls=this.defaulterListRepository.findBySem6();break;
            case "sem7":ls=this.defaulterListRepository.findBySem7();break;
            case "sem8":ls=this.defaulterListRepository.findBySem8();break;
        }
        System.out.println(ls);
        return ls;
    }
}
