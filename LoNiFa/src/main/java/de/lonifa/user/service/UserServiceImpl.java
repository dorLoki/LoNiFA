package de.lonifa.user.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import de.lonifa.user.domain.User;
import de.lonifa.user.domain.UserRepository;
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
	public void addUser(@NonNull User user) {
		userRepository.save(user);
	}

	@Override
	public Optional<User> getUserById(@NonNull Integer userId) {
		return userRepository.findById(userId);
	}

	@Override
	public void updateUser(@NonNull User user) {
		userRepository.save(user);
	}

	@Override
	public User findByLoginName(@NonNull String name) {
		return userRepository.findByLoginName(name);
	}

}
