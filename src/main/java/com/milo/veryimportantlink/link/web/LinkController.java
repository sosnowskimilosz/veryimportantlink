package com.milo.veryimportantlink.link.web;

import com.milo.veryimportantlink.link.application.port.LinkUseCase;
import com.milo.veryimportantlink.link.domain.Link;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/link")
@AllArgsConstructor
public class LinkController {

    LinkUseCase linkService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Link> getAll(){
        return linkService.findaAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        return linkService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id){
        linkService.removeById(id);
    }

}
