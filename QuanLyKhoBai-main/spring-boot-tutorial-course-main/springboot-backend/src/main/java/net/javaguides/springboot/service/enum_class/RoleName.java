//package net.javaguides.springboot.model.enum_class;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//
//public enum RoleName {
//    ADMIN("ADMIN_PERMISSION"),
//    USER("USER_PERMISSION");
//
//    private final String permission;
//
//    RoleName(String permission) {
//        this.permission = permission;
//    }
//
//    public String getPermission() {
//        return permission;
//    }
//
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
//        authorities.add(new SimpleGrantedAuthority(permission));
//        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
//        return authorities;
//    }
//}
