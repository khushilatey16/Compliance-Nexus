package com.nexus.backend.specification;

import com.nexus.backend.entity.Act;
import org.springframework.data.jpa.domain.Specification;

public class ActSpecification {

    public static Specification<Act> titleContains(String searchString) {
        return (root, query, builder) -> builder.like(root.get("title"), "%" + searchString + "%");
    }

    // Add more specifications as needed
}