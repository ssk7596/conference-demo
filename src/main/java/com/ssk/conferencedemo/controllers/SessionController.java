package com.ssk.conferencedemo.controllers;

import com.ssk.conferencedemo.models.Session;
import com.ssk.conferencedemo.repositories.SessionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/sessions")
public class SessionController {

    @Autowired
    private SessionRepository sessionRepository;

    @GetMapping
    public List<Session> getAllSessions(){
        return sessionRepository.findAll();
    }

    @GetMapping("{id}")
    public  Session getSessionById(@PathVariable Long id){
        return sessionRepository.getOne(id);
    }

    @PostMapping
    public Session create(@RequestBody final Session session){
       return sessionRepository.saveAndFlush(session);
    }

    @RequestMapping(value = "{id}",method = RequestMethod.DELETE)
    public void deleteSessionById(@PathVariable Long id){
        sessionRepository.deleteById(id);
    }

    @PutMapping("{id}")
    public Session update(@PathVariable Long id,@RequestBody final Session newSession){
        Session existingSession = sessionRepository.getOne(id);
        BeanUtils.copyProperties(newSession,existingSession,"session_id");
        return sessionRepository.saveAndFlush(existingSession);
    }
}
