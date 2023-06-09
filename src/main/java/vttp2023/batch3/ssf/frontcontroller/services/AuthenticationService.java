package vttp2023.batch3.ssf.frontcontroller.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import vttp2023.batch3.ssf.frontcontroller.respositories.AuthenticationRepository;

@Service
public class AuthenticationService {

	@Autowired
	private AuthenticationRepository authRepo;

	private final String authenticateURL = "https://authservice-production-e8b2.up.railway.app/api/authenticate";

	// TODO: Task 2
	// DO NOT CHANGE THE METHOD'S SIGNATURE
	// Write the authentication method in here
	public boolean authenticate(String username, String password) throws Exception {
		
		JsonObject userJson = Json.createObjectBuilder()
			.add("username", username)
			.add("password", password)
			.build();

		RequestEntity<String> request = RequestEntity
			.post(authenticateURL)
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON)
			.body(userJson.toString(), String.class);
			
		RestTemplate template = new RestTemplate();
		try{
			template.exchange(request, String.class);
		} catch (Exception e) {
			return false;
		}
		
		return true;
		
	}

	// TODO: Task 3
	// DO NOT CHANGE THE METHOD'S SIGNATURE
	// Write an implementation to disable a user account for 30 mins
	public void disableUser(String username) {
		authRepo.disableUser(username);
	}

	// TODO: Task 5
	// DO NOT CHANGE THE METHOD'S SIGNATURE
	// Write an implementation to check if a given user's login has been disabled
	public boolean isLocked(String username) {
		return authRepo.isDisabled(username);
	}
}
