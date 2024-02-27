package de.lonifa.user.service;

import java.util.List;
import java.util.Optional;

import de.lonifa.user.domain.User;

/**
 * Eine Schnittstelle, die Methoden für die Verwaltung von Usern bereitstellt.
 */
public interface UserService {
	/**
	 * Gibt eine Liste aller User zurück.
	 *
	 * @return Liste aller User.
	 */
	List<User> getAllUsers();

	/**
	 * Fügt einen neuen User hinzu.
	 *
	 * @param user Der hinzuzufügende Kunde.
	 */
	void addUser(User user);

	/**
	 * Sucht einen User anhand seiner eindeutigen ID.
	 *
	 * @param userId Die eindeutige ID des User.
	 * @return Ein Optional, das den gefundenen User enthält oder leer ist, wenn
	 *         kein Kunde mit der angegebenen ID gefunden wurde.
	 */
	Optional<User> getUserById(Integer userId);

	/**
	 * Aktualisiert die Informationen eines vorhandenen User.
	 *
	 * @param user Der zu aktualisierende user.
	 */
	void updateUser(User user);

	/**
	 * Sucht einen User anhand seines Login-Namens.
	 *
	 * @param name Der Login-Name des user.
	 * @return Der gefundene User oder null, wenn kein User mit dem angegebenen
	 *         Login-Namen gefunden wurde.
	 */
	User findByLoginName(String name);
}
