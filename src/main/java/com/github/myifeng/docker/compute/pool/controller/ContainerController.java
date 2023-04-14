package com.github.myifeng.docker.compute.pool.controller;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.Container;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/container")
public class ContainerController {

    private final DockerClient dockerClient;

    public ContainerController(DockerClient dockerClient) {
        this.dockerClient = dockerClient;
    }

    @GetMapping
    public List<Container> list(){
        List<Container> exec = dockerClient.listContainersCmd().withShowAll(true).exec();
        return exec;
    }

    @PostMapping("/restart/{id}")
    public String restart(@PathVariable("id") String id){
        dockerClient.restartContainerCmd(id).exec();
        return "ok";
    }

    @PostMapping("/stop/{id}")
    public String stop(@PathVariable("id") String id){
        dockerClient.stopContainerCmd(id).exec();
        return "ok";
    }

    @PostMapping("/remove/{id}")
    public String remove(@PathVariable("id") String id){
        dockerClient.removeContainerCmd(id).exec();
        return "ok";
    }
}
