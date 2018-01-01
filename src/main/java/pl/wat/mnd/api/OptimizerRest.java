package pl.wat.mnd.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.wat.mnd.logic.OptimizerService;

@RestController
@RequestMapping("/api")
public class OptimizerRest {

    @Autowired
    private OptimizerService optimizerService;

    @GetMapping("/test/{id}")
    @ResponseBody public String test(@PathVariable("id") int id){
        System.out.println("Uruchomiono proces o id:" +id);
        optimizerService.optymalizeOrder(id);
        return "OK FOR ORDER_ID:"+id;
    }

    @GetMapping("/run")
    public void run(@RequestParam int id){
        System.out.println("Uruchomiono proces o id:" +id);
        optimizerService.optymalizeOrder(id);
    }
}
