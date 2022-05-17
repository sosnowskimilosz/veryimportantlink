package com.milo.veryimportantlink;

import com.milo.veryimportantlink.link.application.port.LinkUseCase;
import com.milo.veryimportantlink.link.application.port.LinkUseCase.CreateLinkCommand;
import com.milo.veryimportantlink.link.application.port.LinkUseCase.UpdateLinkCommand;
import com.milo.veryimportantlink.link.domain.Link;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class StartUpApplication implements CommandLineRunner {

    private final LinkUseCase service;

    @Override
    public void run(String... args) throws Exception {

        CreateLinkCommand createLinkCommand = new CreateLinkCommand("Onet", "www.onet.pl", List.of("news", "sport"));
        Link link1 = service.createLink(createLinkCommand);
        Link link2 = service.createLink(new CreateLinkCommand("Interia", "www.interia.pl", List.of("news", "sport")));
        Link link3 = service.createLink(new CreateLinkCommand("Sport", "www.sport.pl", List.of("sport")));

        System.out.println(service.findaAll());



    }
}
