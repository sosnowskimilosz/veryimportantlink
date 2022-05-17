package com.milo.veryimportantlink.link.domain;

import java.util.List;
import java.util.Optional;

public interface LinkRepository {

    List<Link> findAll();

    Optional<Link> findById(Long id);

    void removeById(Long id);

    Link save(Link link);

}
