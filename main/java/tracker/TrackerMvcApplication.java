package tracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.redis.core.StringRedisTemplate;
import tracker.config.RedisConfig;

@SpringBootApplication
public class TrackerMvcApplication {
    public static void main(String[] args) {
        SpringApplication.run(TrackerMvcApplication.class, args);
        /*AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(RedisConfig.class);
        ctx.refresh();
        StringRedisTemplate stringRedisTemplate  = ctx.getBean(StringRedisTemplate.class);
        // Using set to set value
        stringRedisTemplate.opsForValue().set("R", "Ram");
        stringRedisTemplate.opsForValue().set("S", "Shyam");
        //Fetch values from set
        System.out.println(stringRedisTemplate.opsForValue().get("R"));
        System.out.println(stringRedisTemplate.opsForValue().get("S"));
        //Using Hash Operation
        String mohan = "Mohan";
        stringRedisTemplate.opsForHash().put("M", String.valueOf(mohan.hashCode()),mohan);
        System.out.println(stringRedisTemplate.opsForHash().get("M", String.valueOf(mohan.hashCode())));*/
    }
}
