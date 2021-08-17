package com.tamchack.admin.service.user;

import com.tamchack.admin.payload.response.UserListResponse;
import com.tamchack.admin.payload.response.UserResponse;
import com.tamchack.tamchack.domain.member.User;
import com.tamchack.tamchack.exception.UserNotFoundException;
import com.tamchack.tamchack.repository.member.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserListResponse getListUser(Pageable pageable) {
        Page<User> users = userRepository.findAllBy(pageable);

        int totalPage = users.getTotalPages();

        List<UserResponse> mapUser = users.map(this::buildUser)
                .stream().collect(Collectors.toList());

        return new UserListResponse(totalPage, mapUser);
    }

    private UserResponse buildUser(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .build();
    }

    @Override
    @Transactional
    public void deleteUser(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

    }
}
