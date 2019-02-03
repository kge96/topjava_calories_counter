package ru.javawebinar.topjava.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.AbstractNamedEntity;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryUserRepositoryImpl implements UserRepository {

	private static final Logger log = LoggerFactory.getLogger(InMemoryUserRepositoryImpl.class);

	private ConcurrentMap<Integer, User> USERS = new ConcurrentHashMap<>();

	private AtomicInteger counter = new AtomicInteger(0);

	{
		save(new User("user-1", "user1@mail.ru", "12345", Role.ROLE_USER));
		save(new User("user-2", "user2@mail.ru", "54321", Role.ROLE_USER));
		save(new User("admin", "admin@mail.ru", "root", Role.ROLE_ADMIN));
	}

	@Override
	public boolean delete(int id) {
		log.info("delete {}", id);
		return USERS.remove(id) != null;
	}

	@Override
	public User save(User user) {
		log.info("save {}", user);
		if (user.isNew()) {
			user.setId(counter.incrementAndGet());
		}
		return USERS.put(user.getId(), user);
	}

	@Override
	public User get(int id) {
		log.info("get {}", id);
		return USERS.get(id);
	}

	@Override
	public List<User> getAll() {
		log.info("getAll");
		return USERS.values().stream()
				.sorted(Comparator.comparing(AbstractNamedEntity::getName))
				.collect(Collectors.toList());
	}

	@Override
	public User getByEmail(String email) {
		log.info("getByEmail {}", email);

		return USERS.values().stream()
				.filter(user -> email.equals(user.getEmail()))
				.findAny().orElse(null);
	}
}
