package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {

	private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
	private AtomicInteger counter = new AtomicInteger(0);

	{
		save(new Meal(LocalDateTime.of(2019, Month.JANUARY, 25, 10, 0), "Завтрак", 500));
		save(new Meal(LocalDateTime.of(2019, Month.JANUARY, 25, 13, 0), "Обед", 1000));
		save(new Meal(LocalDateTime.of(2019, Month.JANUARY, 25, 20, 0), "Ужин", 500));
		save(new Meal(LocalDateTime.of(2019, Month.JANUARY, 26, 10, 0), "Завтрак", 500));
		save(new Meal(LocalDateTime.of(2019, Month.JANUARY, 26, 13, 0), "Обед", 1000));
		save(new Meal(LocalDateTime.of(2019, Month.JANUARY, 26, 20, 0), "Ужин", 510));
	}

	@Override
	public Meal save(Meal meal) {
		if (meal.isNew()) {
			meal.setId(counter.incrementAndGet());
		}
		return repository.put(meal.getId(), meal);
	}

	@Override
	public void delete(int id) {
		repository.remove(id);
	}

	@Override
	public Meal get(int id) {
		return repository.get(id);
	}

	@Override
	public Collection<Meal> getAll() {
		return repository.values();
	}
}
