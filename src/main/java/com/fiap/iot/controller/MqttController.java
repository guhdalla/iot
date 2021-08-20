package com.fiap.iot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fiap.iot.mqtt.MqttConnection;

@Controller
public class MqttController {
	
	MqttConnection mqtt = MqttConnection.getInstance();

//	@Autowired
//	public MqttController(MqttConnection mqtt){
//		this.mqtt = mqtt;
//	}
	
	@RequestMapping("/mqtt")
	public String index() {
		return "mqtt-form";
	}

	@RequestMapping("/sub")
	public String sub() {
		mqtt.sub("bgmbnewgen8462/iot", 2);
		return "mqtt-form";
	}
	
	@RequestMapping("/pub")
	public String pub() {
		mqtt.pub("bgmbnewgen8462/iot", "Hello World", 2);
		return "mqtt-form";
	}

	public boolean verify(){
		return mqtt.getClient().isConnected();
	}
	
}
