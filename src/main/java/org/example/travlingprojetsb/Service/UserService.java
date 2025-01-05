package org.example.travlingprojetsb.Service;



import org.example.travlingprojetsb.Entity.User;
import org.example.travlingprojetsb.dto.UserDto;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    User findByEmail(String email);

    List<UserDto>findAllUsers();
}
