package com.driver.services;

import com.driver.model.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ConnectionService {

    public User connect(int userId, String countryName) throws Exception;

    public User disconnect(int userId) throws Exception;

    public User communicate(int senderId, int receiverId) throws Exception;
}
