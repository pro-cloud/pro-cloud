package com.cloud.websocket.controller;

import lombok.Data;

import java.io.Serializable;

@Data
public class Cpu implements Serializable {
    private String key;
    private String value;
}
