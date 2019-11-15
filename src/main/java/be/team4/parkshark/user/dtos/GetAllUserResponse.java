package be.team4.parkshark.user.dtos;

import be.team4.parkshark.user.User;

public class GetAllUserResponse {

    public Long id;
    public String username;

    private GetAllUserResponse(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    public static GetAllUserResponse of(User user) {
        return new GetAllUserResponse(
                user.getId().value(),
                user.getUsername());
    }

}
