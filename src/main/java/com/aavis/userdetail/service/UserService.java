package com.aavis.userdetail.service;

import static com.aavis.userdetail.util.DatastoreUtil.USER_KIND;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aavis.userdetail.model.Address;
import com.aavis.userdetail.model.LoginModel;
import com.aavis.userdetail.model.Result;
import com.aavis.userdetail.model.User;
import com.google.cloud.datastore.Cursor;
import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.FullEntity;
import com.google.cloud.datastore.IncompleteKey;
import com.google.cloud.datastore.Key;
import com.google.cloud.datastore.KeyFactory;
import com.google.cloud.datastore.Query;
import com.google.cloud.datastore.QueryResults;
import com.google.cloud.datastore.StructuredQuery;
import com.google.cloud.datastore.StructuredQuery.OrderBy;
import com.google.cloud.datastore.StructuredQuery.PropertyFilter;

@Service
public class UserService {

	@Autowired
	Datastore datastore;

	private KeyFactory userKeyFactory;

	@PostConstruct
	public void initializeKeyFactories() {
		// log.info("Initializing key factories");
		// datastore = DatastoreOptions.getDefaultInstance().getService();
		datastore = DatastoreOptions.newBuilder().setHost("http://localhost:8081").setProjectId("webserver1-194309")
				.build().getService();
		userKeyFactory = datastore.newKeyFactory().setKind(USER_KIND);
	}

	public Long createUser(User user) {

		// IncompleteKey key = userKeyFactory.newKey(user.getEmail().hashCode()); // Key
		// will be assigned once written
		IncompleteKey key = userKeyFactory.newKey(user.getEmail().hashCode());
		FullEntity<IncompleteKey> incUserEntity = Entity.newBuilder(key) // Create the Entity
				.set(User.ID, System.currentTimeMillis()).set(User.FNAME, user.getFname())
				.set(User.LNAME, user.getLname()).set(User.EMAIL, user.getEmail())
				.set(User.CREATED_BY, user.getCreatedBy()).set(User.LOGGED_IN_BY, user.getLoggedInBy())
				.set(User.LOGGED_IN_TYPE, user.getLoggedInType()).build();
		Entity userEntity = datastore.add(incUserEntity); // Save the Entity
		return userEntity.getKey().getId();
	}

	public Result<User> getUsers(String startCursorString) {
		Cursor startCursor = null;
		if (startCursorString != null && !startCursorString.equals("")) {
			startCursor = Cursor.fromUrlSafe(startCursorString); // Where we left off
		}
		Query<Entity> query = Query.newEntityQueryBuilder() // Build the Query
				.setKind(USER_KIND) // We only care about Books
				.setLimit(10) // Only show 10 at a time
				.setStartCursor(startCursor) // Where we left off
				.setOrderBy(OrderBy.asc(User.EMAIL)).build(); // Use default Index "title"
		QueryResults<Entity> resultList = datastore.run(query); // Run the query
		List<User> resultUsers = entitiesToUsers(resultList); // Retrieve and convert Entities
		Cursor cursor = resultList.getCursorAfter(); // Where to start next time
		if (cursor != null && resultUsers.size() == 10) { // Are we paging? Save Cursor
			String cursorString = cursor.toUrlSafe(); // Cursors are WebSafe
			return new Result<>(resultUsers, cursorString);
		} else {
			return new Result<>(resultUsers);
		}
	}

	public User viewProfile(Long userId) {

		Entity userEntity = datastore.get(userKeyFactory.newKey(userId));
		return entityToUser(userEntity);

	}

	public User viewProfile(LoginModel login) {
		Query<Entity> query = Query.newEntityQueryBuilder().setKind(USER_KIND).setFilter(PropertyFilter.eq(User.EMAIL, login.getEmail())).build();
		QueryResults<Entity> resultList = datastore.run(query);
		User user = entityToUser(resultList.next());
		return user;

	}

	public User entityToUser(Entity entity) {
		return new User.Builder() // Convert to User form
				.id(entity.contains(User.ID) ? entity.getLong(User.ID) : null)
				.fname(entity.contains(User.FNAME) ? entity.getString(User.FNAME) : null)
				.lname(entity.contains(User.LNAME) ? entity.getString(User.LNAME) : null)
				.email(entity.contains(User.EMAIL) ? entity.getString(User.EMAIL) : null)
				.createdBy(entity.contains(User.CREATED_BY) ? entity.getString(User.CREATED_BY) : null)
				.loggedInBy(entity.contains(User.LOGGED_IN_BY) ? entity.getString(User.LOGGED_IN_BY) : null)
				.loggedInType(entity.contains(User.LOGGED_IN_TYPE) ? entity.getString(User.LOGGED_IN_TYPE) : null)
				.address(entity.contains(User.ADDRESS) ? getAddressEntity(entity.getEntity(User.ADDRESS)) : null)
				.imageUrl(entity.contains(User.IMAGE_URL) ? entity.getString(User.IMAGE_URL) : null).build();
	}

	private Address getAddressEntity(FullEntity<IncompleteKey> entity) {
		String area = entity.contains(Address.AREA) ? entity.getString(Address.AREA) : null;
		String city = entity.contains(Address.CITY) ? entity.getString(Address.CITY) : null;
		String state = entity.contains(Address.STATE) ? entity.getString(Address.STATE) : null;
		String country = entity.contains(Address.COUNTRY) ? entity.getString(Address.COUNTRY) : null;
		
		return new Address(country, state, city, area);
	}

	public List<User> entitiesToUsers(QueryResults<Entity> resultList) {
		List<User> resultUsers = new ArrayList<>();
		while (resultList.hasNext()) { // We still have data
			resultUsers.add(entityToUser(resultList.next())); // Add the Book to the List
		}
		return resultUsers;
	}

	public void updateUser(User user, Long id) {
		
		FullEntity<Key> addressEntity = null;
		Key key = userKeyFactory.newKey(id);
		
		if (null != user.getAddress()) {
			addressEntity = FullEntity.newBuilder(key).set(Address.COUNTRY, user.getAddress().getCountry())
					.set(Address.STATE, user.getAddress().getState()).set(Address.CITY, user.getAddress().getCity())
					.set(Address.AREA, user.getAddress().getArea()).build();
		}

		Entity entity = datastore.get(userKeyFactory.newKey(id));
		Entity userEntity = Entity.newBuilder(entity).set("address", addressEntity).build();

		datastore.update(userEntity); // Update the Entity
	}

}
