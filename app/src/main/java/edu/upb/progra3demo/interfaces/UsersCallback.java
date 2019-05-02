package edu.upb.progra3demo.interfaces;

import java.util.List;

import edu.upb.progra3demo.model.User;

public interface UsersCallback {
    void onUsersResults(List<User> users);
}
