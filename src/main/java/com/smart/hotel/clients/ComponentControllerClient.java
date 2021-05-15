package com.smart.hotel.clients;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Service
@RequiredArgsConstructor
public class ComponentControllerClient {

    @Resource
    private RestTemplate componentControllerRestClient;

    public void lockNumber() {
        componentControllerRestClient.put("/number/lock", null);
    }

    public void unlockNumber() {
        componentControllerRestClient.put("/number/unlock", null);
    }
}
