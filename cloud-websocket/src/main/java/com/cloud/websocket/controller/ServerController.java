package com.cloud.websocket.controller;

import cn.hutool.json.JSONUtil;
import com.cloud.common.util.base.Result;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/serverv")
public class ServerController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    @GetMapping
    public Result login() {

        Server server = new Server();
        List<Cpu> cpus = Lists.newArrayList();
        Cpu cpu = new Cpu();
        cpu.setKey("22");
        cpu.setValue("444");
        cpus.add(cpu);
        server.setCpus(cpus);


        simpMessagingTemplate.convertAndSend("/topic/server", JSONUtil.toJsonStr(server));
        return Result.success("22");
    }

    @GetMapping("ai")
    public Result loginv() {

        Server server = new Server();
        List<Cpu> cpus = Lists.newArrayList();
        Cpu cpu = new Cpu();
        cpu.setKey("272");
        cpu.setValue("444");
        cpus.add(cpu);
        server.setCpus(cpus);

        simpMessagingTemplate.convertAndSendToUser("1", "/topic/server", JSONUtil.toJsonStr(server));
        simpMessagingTemplate.convertAndSendToUser("3", "/topic/server", JSONUtil.toJsonStr(server));
        return Result.success("22");
    }

    @GetMapping("aiv")
    public Result loginvai() {

        Server server = new Server();
        List<Cpu> cpus = Lists.newArrayList();
        Cpu cpu = new Cpu();
        cpu.setKey("272");
        cpu.setValue("444");
        cpus.add(cpu);
        server.setCpus(cpus);

        simpMessagingTemplate.convertAndSendToUser("2", "/topic/server", JSONUtil.toJsonStr(server));
        return Result.success("22");
    }
}
