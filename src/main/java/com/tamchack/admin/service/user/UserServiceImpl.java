package com.tamchack.admin.service.user;

import com.tamchack.admin.payload.response.UserListResponse;
import com.tamchack.admin.payload.response.UserResponse;
import com.tamchack.tamchack.domain.member.User;
import com.tamchack.tamchack.repository.member.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public UserListResponse getListUser(Pageable pageable) {
        Page<User> users = userRepository.findAllBy(pageable);

        return new UserListResponse(users.getTotalPages(),
                users.map(this::buildUser)
                        .stream().collect(Collectors.toList()));
    }

    private UserResponse buildUser(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .password(user.getPassword())
                .name(user.getName())
                .build();
    }
}
