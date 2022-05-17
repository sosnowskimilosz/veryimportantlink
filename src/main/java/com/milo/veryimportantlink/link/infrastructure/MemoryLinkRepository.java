package com.milo.veryimportantlink.link.infrastructure;

import com.milo.veryimportantlink.link.domain.Link;
import com.milo.veryimportantlink.link.domain.LinkRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class MemoryLinkRepository implements LinkRepository {

    Map<Long, Link> storage = new HashMap<>();
    AtomicLong ID_VALUE = new AtomicLong(0);

    @Override
    public List<Link> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public Optional<Link> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public void removeById(Long id) {
        storage.remove(id);
    }

    @Override
    public Link save(Link link) {
        if(link.getId() != null){
            storage.put(link.getId(), link);
        }else{
            Long nextId=nextId();
            link.setId(nextId);
            storage.put(nextId, link);
        }
        return link;
    }

    private Long nextId() {
        return ID_VALUE.getAndIncrement();
    }
}
