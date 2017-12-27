package pl.wat.mnd.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.wat.mnd.logic.OptimizerService;

@RestController
@RequestMapping("/api")
public class OptimizerRest {

    @Autowired
    private OptimizerService optimizerService;

    @GetMapping("/test")
    @ResponseBody public String test(){
        return "OPTYMALIZATOR REST DZIAŁA";
    }

    @GetMapping("/run")
    public void run(@RequestParam int id){
        System.out.println("Uruchomiono proces o id:" +id);
        optimizerService.optymalizeOrder(id);
    }
}
