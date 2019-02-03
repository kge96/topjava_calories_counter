package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.AbstractBaseEntity;
import ru.javawebinar.topjava.model.AbstractNamedEntity;
import ru.javawebinar.topjava.util.exception.NotFoundException;

public class ValidationUtil {

	public static <T> T checkNotFoundWithId(T object, int id) {
		return checkNotFound(object, "id=" + id);
	}

	public static void checkNotFoundWithId(boolean found, int id) {
		checkNotFound(found, "id=" + id);
	}

	public static <T> T checkNotFound(T object, String msg) {
		checkNotFound(object != null, msg);
		return object;
	}

	public static void checkNotFound(boolean found, String msg) {
		if (!found) {
			throw new NotFoundException("Not found entity with " + msg);
		}
	}

	public static void checkNew(AbstractNamedEntity entity) {
		if (!entity.isNew()) {
			throw new IllegalStateException(entity + " must be new (id=null)");
		}
	}

	public static void assureIdConsistent(AbstractBaseEntity entity, int id) {
		if (entity.isNew()) {
			entity.setId(id);
		} else if (entity.getId() != id) {
			throw new IllegalStateException(entity + " must be with id= " + id);
		}
	}
}
