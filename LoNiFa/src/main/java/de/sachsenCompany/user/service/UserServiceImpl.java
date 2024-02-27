package de.sachsenCompany.user.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.sachsenCompany.user.domain.User;
import de.sachsenCompany.user.domain.UserRepository;
/**
 * Die UserServiceImpl-Klasse implementiert die UserService-Schnittstelle und
 * bietet die Logik für die Verwaltung von User an.
 */
@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository userRepository;
	
	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public void addUser(User user) {
		userRepository.save(user);
	}

	@Override
	public Optional<User> getUserById(Integer userId) {
		return userRepository.findById(userId);
	}

	@Override
	public void updateUser(User user) {
		userRepository.save(user);
	}

	@Override
	public User findByLoginName(String name) {
		return userRepository.findByLoginName(name);
	}

}
