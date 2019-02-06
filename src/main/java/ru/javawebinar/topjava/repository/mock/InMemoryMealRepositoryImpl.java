package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
	private final Integer USER_ID = 1;
	private final Integer ADMIN_ID = 2;
	private Map<Integer, List<Meal>> repository = new ConcurrentHashMap<>();

	private AtomicInteger mealId = new AtomicInteger(0);

	{
		save(new Meal(USER_ID, LocalDateTime.of(2019, Month.JANUARY, 25, 10, 0), "Завтрак", 500));
		save(new Meal(USER_ID, LocalDateTime.of(2019, Month.JANUARY, 25, 13, 0), "Обед", 1000));
		save(new Meal(USER_ID, LocalDateTime.of(2019, Month.JANUARY, 25, 20, 0), "Ужин", 500));
		save(new Meal(USER_ID, LocalDateTime.of(2019, Month.JANUARY, 26, 10, 0), "Завтрак", 500));
		save(new Meal(USER_ID, LocalDateTime.of(2019, Month.JANUARY, 26, 13, 0), "Обед", 1000));
		save(new Meal(USER_ID, LocalDateTime.of(2019, Month.JANUARY, 26, 20, 0), "Ужин", 510));
	}

	@Override
	public Meal save(Meal meal) {
		if (meal.isNew()) {
			meal.setId(mealId.incrementAndGet());
		}
		List<Meal> mealList = repository.getOrDefault(meal.getUserId(), new LinkedList<>());
		mealList.add(meal);
		repository.put(meal.getUserId(), mealList);
		return meal;
	}

	@Override
	public boolean delete(int userId, int mealId) {
		List<Meal> meals = repository.getOrDefault(userId, null);
		return Optional.ofNullable(meals).map(mealList -> mealList.removeIf(meal -> mealId == meal.getId())).orElse(false);
	}

	@Override
	public Meal get(int userId, int mealId) {
		List<Meal> meals = repository.getOrDefault(userId, null);
		return Optional.ofNullable(meals).map(mealList -> mealList.get(mealId)).orElse(null);
	}

	@Override
	public Collection<Meal> getAll(int userId) {
		List<Meal> meals = repository.getOrDefault(userId, null);
		return  Optional.ofNullable(meals)
				.map(mealList -> mealList.stream()
						.sorted(Comparator.comparing(Meal::getDateTime).reversed())
						.collect(Collectors.toList()))
				.orElse(null);
	}
}
