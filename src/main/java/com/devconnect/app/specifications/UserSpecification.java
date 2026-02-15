package com.devconnect.app.specifications;

import com.devconnect.app.dtos.user.UserSearchDto;
import com.devconnect.app.entities.User;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class UserSpecification {
    public static Specification<User> build(UserSearchDto searchDto) {
        return ((root, query, cb) -> {
            List<Predicate> predicateList = new ArrayList<>();
            if (searchDto.getFirstName() != null && !searchDto.getFirstName().isBlank()) {
                predicateList.add(
                        cb.like(
                                cb.lower(root.get("firstName")),
                                "%" + searchDto.getFirstName().toLowerCase() + "%"
                        )
                );
            }
            if (searchDto.getMiddleName() != null && !searchDto.getMiddleName().isBlank()) {
                predicateList.add(
                        cb.like(
                                cb.lower(root.get("middleName")),
                                "%" + searchDto.getMiddleName().toLowerCase() + "%"
                        )
                );
            }
            if (searchDto.getLastName() != null && !searchDto.getLastName().isBlank()) {
                predicateList.add(
                        cb.like(
                                cb.lower(root.get("lastName")),
                                "%" + searchDto.getLastName().toLowerCase() + "%"
                        )
                );
            }
            if (searchDto.getUsername() != null && !searchDto.getUsername().isBlank()) {
                predicateList.add(
                        cb.like(
                                cb.lower(root.get("username")),
                                "%" + searchDto.getUsername().toLowerCase() + "%"
                        )
                );
            }
            if (searchDto.getCreatedAfter() != null) {
                predicateList.add(
                        cb.greaterThanOrEqualTo(root.get("createdAt"), searchDto.getCreatedAfter())
                );
            }
            if (searchDto.getCreatedBefore() != null) {
                predicateList.add(
                        cb.lessThanOrEqualTo(root.get("createdAt"), searchDto.getCreatedBefore())
                );
            }
            if (searchDto.getUserRoleList() != null && !searchDto.getUserRoleList().isEmpty()) {
                predicateList.add(
                        root.get("userRole").in(searchDto.getUserRoleList())
                );
            }
            return cb.and(predicateList.toArray(new Predicate[0]));
        });
    }
}
