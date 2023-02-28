package com.goit.feature.mvc.user.dto;

import com.goit.feature.mvc.notes.Note;
import com.goit.feature.mvc.user.User;
import com.goit.feature.mvc.user.roles.EUserRole;
import com.goit.feature.mvc.user.views.EViewType;
import lombok.Data;

import java.util.List;

@Data
public class UserDto {
    private String name;
    private EUserRole role;
    private EViewType viewType;
    private List<Note> notes;

    public static UserDto fromUser(User user) {
        UserDto res = new UserDto();
        res.setName(user.getUsername());
        res.setRole(user.getRole());
        res.setNotes(user.getNotes());
        res.setViewType(user.getViewType());
        return res;
    }

    public static User toUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getName());
        user.setRole(userDto.getRole());
        user.setNotes(userDto.getNotes());
        user.setViewType(userDto.getViewType());
        return user;
    }
}
