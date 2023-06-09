package vttp2023.batch3.ssf.frontcontroller.respositories;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class AuthenticationRepository {

	@Autowired
    @Qualifier("Disabled")
    private RedisTemplate<String, Object> template;

	public void disableUser(String username){
		template.opsForValue()
			.set(username,"disabled", Duration.ofMinutes(30));
	}

	public boolean isDisabled (String username){
		if (template.opsForValue().get(username) == null){
			return false;
		}
		return true;
	}
}
