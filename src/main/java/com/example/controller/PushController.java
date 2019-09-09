//package com.example.controller;
//
//import com.corundumstudio.socketio.SocketIOClient;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import javax.annotation.Resource;
//import java.util.HashMap;
//import java.util.UUID;
//
//@RequestMapping("/push")
//@Controller
//public class PushController {
//    @Resource
//    private ClientCache clientCache;
//
//    @GetMapping("/user/{userId}")
//    public String pushTuUser(@PathVariable("userId") String userId){
//        HashMap<UUID, SocketIOClient> userClient = clientCache.getUserClient(userId);
//        userClient.forEach((uuid, socketIOClient) -> {
//            //向客户端推送消息
//            socketIOClient.sendEvent("chatevent","服务端推送消息");
//        });
//        return "success";
//    }
//}
