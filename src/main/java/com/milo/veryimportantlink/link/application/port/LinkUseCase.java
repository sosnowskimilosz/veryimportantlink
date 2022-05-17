package com.milo.veryimportantlink.link.application.port;


import com.milo.veryimportantlink.link.domain.Link;
import lombok.Value;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public interface LinkUseCase {

    List<Link> findaAll();

    Optional<Link> findById(Long id);

    List<Link> findByTag(String tag);

    List<Link> findByDescription(String description);

    void removeById(Long id);

    Link createLink(CreateLinkCommand command);

    UpdateLinkResponse updateLink(UpdateLinkCommand command);

    @Value
    class CreateLinkCommand {
        String description;
        String link;
        List<String> tags;

        public Link toLink(){
            return new Link(description,link,tags);
        }
    }

    @Value
    class UpdateLinkCommand {
        Long id;
        String description;
        String address;
        List<String> tags;

        public Link updateFields(Link link){
            if(link.getDescription() != null){
                link.setDescription(description);
            }
            if(link.getAddress() != null){
                link.setAddress(address);
            }
            if(link.getTags() != null){
                link.setTags(tags);
            }
            return link;
        }
    }

    @Value
    class UpdateLinkResponse {
        boolean success;
        List<String> errors;

        public static UpdateLinkResponse SUCCESS = new UpdateLinkResponse(true, Collections.emptyList());
    }
}
