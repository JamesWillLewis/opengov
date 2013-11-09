/*
 *  This file is part of OpenGov.
 *
 *  OpenGov is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  OpenGov is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with OpenGov.  If not, see <http://www.gnu.org/licenses/>.
 */
package za.org.opengov.ussd.util;

import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import za.org.opengov.stockout.entity.Subject;

/**
 * Provides a persistent key-value store, which maintains data in between
 * transactions, such as with state-less web-service calls. Particularly useful
 * for maintaining session data between HTTP requests, where session state is
 * not maintained between requests. For instance, the key used could be a unique
 * ID associated to the particular session. The controller would return this ID
 * with the HTTP response, and the client would then use this ID in it's next
 * request message (as a header, for instance). This ID can then be used to
 * perform a look-up which returns the key's value, which could be an entire
 * object of various fields containing some data which is needed between
 * requests.
 * 
 * @author James Lewis (james.will.lewis@gmail.com)
 */
@Service("keyValueStore")
@Scope("singleton")
public class KeyValueStore {

	/**
	 * Concurrent hash map, which ensures thread safety. Operations on the
	 * hash-map are fully atomic.
	 */
	@Resource(name = "sessionKeyValueStore")
	private ConcurrentHashMap<String, Object> concurrentKeyValueStore;

	public KeyValueStore() {
	}

	/**
	 * Assign a string value to the key.
	 * 
	 * @param key
	 *            Key which indexes the value.
	 * @param Object
	 *            Value to indexed by the given key.
	 * @return Value referenced by the key.
	 */
	public Object put(String key, String Object) {
		return concurrentKeyValueStore.put(key, Object);
	}

	/**
	 * Assign an object value to the key.
	 * 
	 * @param key
	 *            Key which indexes the value.
	 * @param Object
	 *            Value to indexed by the given key.
	 * @return Value referenced by the key.
	 */
	public Object put(String key, Object object) {
		return concurrentKeyValueStore.put(key, object);
	}

	/**
	 * Assign an array of objects to the key.
	 * 
	 * @param key
	 *            Key which indexes the value.
	 * @param Objects
	 *            Values to indexed by the given key.
	 * @return Value referenced by the key.
	 */
	public Object put(String key, String[] Objects) {
		return concurrentKeyValueStore.put(key, Objects);
	}

	/**
	 * Return the value referenced by the key.
	 * 
	 * @param key Key which references the object to be retrieved.
	 * @return Object referenced by the key.
	 */
	public Object get(String key) {
		return concurrentKeyValueStore.get(key);
	}

	/**
	 * Delete the value at the key. This should be called after a session is
	 * complete to ensure no memory leaks, since the garbage collector has no
	 * way to determine that the value at the specific key is no longer in use.
	 * 
	 * @param key Key which references the object to be deleted.
	 * @return Object referenced by the key (now removed).
	 */
	public Object remove(String key) {
		return concurrentKeyValueStore.remove(key);

	}

}
