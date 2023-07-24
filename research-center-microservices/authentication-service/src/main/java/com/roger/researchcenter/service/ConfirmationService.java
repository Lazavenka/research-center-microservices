package com.roger.researchcenter.service;

import com.roger.researchcenter.model.User;

public interface ConfirmationService {
    void sendConfirmation(User user, String token);
}
