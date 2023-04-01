package com.driver.services.impl;

import com.driver.model.*;
import com.driver.repository.ConnectionRepository;
import com.driver.repository.ServiceProviderRepository;
import com.driver.repository.UserRepository;
import com.driver.services.ConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConnectionServiceImpl implements ConnectionService {
    @Autowired
    UserRepository userRepository2;
    @Autowired
    ServiceProviderRepository serviceProviderRepository2;
    @Autowired
    ConnectionRepository connectionRepository2;

    @Override
    public User connect(int userId, String countryName) throws Exception{
        User user = userRepository2.findById(userId).get();
        if(user.getConnected()){
            throw new Exception("Already connected");
        }
        else if(user.getCountry().equals(countryName)){
            return user;
        }
        else{
            List<ServiceProvider> serviceProviderList = user.getServiceProviderList();
            if(serviceProviderList==null){
                throw new Exception("Unable to connect");
            }
            for(ServiceProvider serviceProvider: serviceProviderList){
                if(serviceProvider.getCountryList().contains(countryName.toUpperCase())){
                    user.setConnected(true);
                    Country country = new Country();
                    for(CountryName countryName1: CountryName.values()){
                        if(countryName1.equals(countryName.toUpperCase())){
                            country.setCountryName(countryName1);
                            country.setCode(countryName1.toCode());
                            break;
                        }
                    }
                    user.getServiceProviderList().add(serviceProvider);

                    // adding connection to the connectionList of user
                    Connection connection = new Connection();
                    connection.setUser(user);
                    connection.setServiceProvider(serviceProvider);
                    user.getConnectionList().add(connection);
                    /////
                    user.setCountry(country);
                    user.setMaskedIp(country.getCode()+"."+serviceProvider.getId()+"."+user.getId());
                    return user;
                }
            }
        }
        return user;
    }
    @Override
    public User disconnect(int userId) throws Exception {
        User user = userRepository2.findById(userId).get();
        if(user.getConnected()==false){
            throw new Exception("Already disconnected");
        }
        user.setConnected(false);
        user.setMaskedIp(null);
        return user;
    }
    @Override
    public User communicate(int senderId, int receiverId) throws Exception {
        User user = userRepository2.findById(senderId).get();
        return user;
    }
}