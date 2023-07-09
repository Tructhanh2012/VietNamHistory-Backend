package com.second.version.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@RequiredArgsConstructor
public enum Role {
    EDITOR(Set.of(
            Permission.EDITOR_READ,
            Permission.EDITOR_CREATE,
            Permission.EDITOR_UPDATE,
            Permission.EDITOR_DELETE
    )),
    MEMBER(Set.of(
            Permission.MEMBER_CREATE,
            Permission.MEMBER_DELETE,
            Permission.MEMBER_READ,
            Permission.MEMBER_UPDATE
    )),
    ADMIN(Set.of(
            Permission.ADMIN_READ,
            Permission.ADMIN_UPDATE,
            Permission.ADMIN_DELETE,
            Permission.ADMIN_CREATE
    ));
    @Getter
    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}