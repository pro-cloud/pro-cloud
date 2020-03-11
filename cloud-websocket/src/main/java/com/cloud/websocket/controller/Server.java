package com.cloud.websocket.controller;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Server implements Serializable {

    private List<Cpu> cpus;
}
