package edu.bsellers.test.web_0mq_bridge;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

@RestController
public class FirstController {
	public FirstController() {
		try (ZContext context = new ZContext()) {
			socket = context.createSocket(SocketType.REQ);
			socket.bind("tcp://*:5555");
		}
	}
	
	@GetMapping("/")
	public String index() {
        String request = "Hello";
        socket.send(request.getBytes(ZMQ.CHARSET), 0);
        return "Sent Hello to 0mq port 5555";
	}
	
	ZMQ.Socket socket;
}
