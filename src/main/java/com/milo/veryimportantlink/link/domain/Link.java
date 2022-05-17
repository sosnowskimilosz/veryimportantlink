package com.milo.veryimportantlink.link.domain;

import lombok.Data;

import java.util.List;

@Data
public class Link {

    Long id;
    String description;
    String address;
    List<String> tags;

    public Link(String description, String address, List<String> tags) {
        this.description = description;
        this.address = address;
        this.tags = tags;
    }
}
