package com.goit.feature.mvc.user;

import com.goit.feature.mvc.notes.Note;
import com.goit.feature.mvc.user.roles.EUserRole;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
//@Service
public class CustomJdbcUserDetailsService implements UserDetailsService {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails user = getByNameOrNull(username);

        if (user == null) {
            throw new UsernameNotFoundException(username + " not found!");
        }

        return user;
    }

    private UserDetails getByNameOrNull(String name) {
        String sql = "SELECT name, password, role FROM users WHERE name=:name";
        System.out.println("sql = " + sql + name);
        return jdbcTemplate.queryForObject(
                sql,
                Map.of("name", name),
                new UserDataMapper()
        );
    }

    private static class UserDataMapper implements RowMapper<UserDetails> {
        @Override
        public UserDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
//            Long id = rs.getLong("id");
            String name = rs.getString("name");
            String password = rs.getString("password");
            String role = rs.getString("role");
            return new UserDetails() {
                @Override
                public Collection<? extends GrantedAuthority> getAuthorities() {
                    return Collections.singleton(() -> role);
                }

//                public Long getId() {
//                    return id;
//                }

                @Override
                public String getPassword() {
                    return password;
                }

                @Override
                public String getUsername() {
                    return name;
                }

                @Override
                public boolean isAccountNonExpired() {
                    return true;
                }

                @Override
                public boolean isAccountNonLocked() {
                    return true;
                }

                @Override
                public boolean isCredentialsNonExpired() {
                    return true;
                }

                @Override
                public boolean isEnabled() {
                    return true;
                }
            };
        }
    }

    @Builder
    @Data
    private static class UserData {
        private String name;
        private String password;
        private EUserRole role;
        private List<Note> notes;
    }
}
