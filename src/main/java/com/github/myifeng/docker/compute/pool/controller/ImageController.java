package com.github.myifeng.docker.compute.pool.controller;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.Image;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/image")
public class ImageController {

    private final DockerClient dockerClient;

    public ImageController(DockerClient dockerClient) {
        this.dockerClient = dockerClient;
    }

    @GetMapping
    public List<Image> list(){
        List<Image> exec = dockerClient.listImagesCmd().withShowAll(true).exec();
        return exec;
    }

}
