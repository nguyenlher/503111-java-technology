package edu.tdtu.Lab10.mapper;

import edu.tdtu.Lab10.dto.request.UserRequest;
import edu.tdtu.Lab10.dto.response.UserResponse;
import edu.tdtu.Lab10.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User createUser(UserRequest request);
    UserResponse toUserResponse(User user);
}
