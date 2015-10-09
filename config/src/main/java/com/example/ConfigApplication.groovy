package com.example

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.config.server.EnableConfigServer

@EnableConfigServer
@EnableDiscoveryClient
@SpringBootApplication
class ConfigApplication {

    static void main(String[] args) {
        SpringApplication.run ConfigApplication, args
    }
}
