
package com.example.weebhook_demo.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

@RestController
public class WebhookController {
    private static final String TOKEN_STRING = "TOKEN-RECONTRA-SEGURO-123";

    @GetMapping("/")
    public String welcome() {
        return "BENBENUTO LAPADULA A LA SELECIONE";
    }
    

    @GetMapping("/webhooks")                                    
    public ResponseEntity<String> verifyWebhook(@RequestParam("hub.mode") String mode, @RequestParam("hub.verify_token") String token, @RequestParam("hub.challenge") int challenge){
        System.out.println("PULSEANDO WEBHOOK");

        if("subscribe".equals(mode) && TOKEN_STRING.equals(token)){
            return ResponseEntity.ok(String.valueOf(challenge));
        } else {
            return ResponseEntity.status(403).body("Forbidden");
        }
    }
    

    // @PostMapping("/webhooks")
    // public ResponseEntity<String> handleWebhook(@RequestBody String payload) {
    //     //TODO: process POST request
    //     System.out.println("received payload: " + payload);
    //     return ResponseEntity.ok("EVENT_RECEIVED"); 
    // }


    @GetMapping("/tezzzt")
    public ResponseEntity<String> verifyController(){
        try{
            return ResponseEntity.ok("ALGO SE PONDRA ACA");

        } catch (Exception e){
            return ResponseEntity.status(403).body("Forbidden");
        }

    }
    @PostMapping("/webhooks")
    public ResponseEntity<String> handleWebhook(@RequestBody String payload,
                                                @RequestHeader("X-Hub-Signature-256") String signature) {
        // Aquí puedes verificar la firma y procesar los datos
        System.out.println("Received payload: " + payload);
        System.out.println("Signature: " + signature);
        
        // Aquí puedes añadir la lógica para procesar el payload recibido
        return ResponseEntity.ok("EVENT_RECEIVED");
    }



    @GetMapping("/hello")
    public String sayHello() {
        return "Hello dev";
    }
    
}