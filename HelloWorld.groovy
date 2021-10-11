import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * spring run HelloWorld.groovy
 * curl http://localhost:8080
 * */

@RestController
class HelloWorld {
    @RequestMapping("/")
    def hello() {
        return "Hello World";
    }
}