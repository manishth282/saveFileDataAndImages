package org.project.imagesaveform.service;

import org.project.imagesaveform.model.User;
import org.project.imagesaveform.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	public User saveUser(User user) {
		return userRepository.save(user);
	}
}
