package com.milo.veryimportantlink.link.application;

import com.milo.veryimportantlink.link.application.port.LinkUseCase;
import com.milo.veryimportantlink.link.domain.Link;
import com.milo.veryimportantlink.link.domain.LinkRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LinkService implements LinkUseCase {

    private final LinkRepository repository;

    @Override
    public List<Link> findaAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Link> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Link> findByTag(String tag) {
        return repository.findAll()
                .stream()
                .filter(link -> link.getTags().contains(tag))
                .collect(Collectors.toList());
    }

    @Override
    public List<Link> findByDescription(String description) {
        return repository.findAll()
                .stream()
                .filter(link -> link.getDescription().contains(description))
                .collect(Collectors.toList());
    }

    @Override
    public void removeById(Long id) {
        repository.removeById(id);
    }

    @Override
    public Link createLink(CreateLinkCommand command) {
        Link newLink = command.toLink();
        return  repository.save(newLink);
    }

    @Override
    public UpdateLinkResponse updateLink(UpdateLinkCommand command) {
        return repository.findById(command.getId())
                .map(link -> {
                    Link linkToUpdate = command.updateFields(link);
                    repository.save(linkToUpdate);
                    return UpdateLinkResponse.SUCCESS;
                }).orElse(new UpdateLinkResponse(
                        false, Collections.singletonList("Link not found with Id: " + command.getId())
                ));
    }
}
