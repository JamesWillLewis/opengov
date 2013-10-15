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

@Service("keyValueStore")
@Scope("singleton")
public class KeyValueStore {
	
	@Resource(name="sessionKeyValueStore")
	private ConcurrentHashMap<String, Object> concurrentKeyValueStore;
	
	public KeyValueStore() {
	}
	
	public Object put(String key,String Object){
		return concurrentKeyValueStore.put(key, Object);
	}
	public Object put(String key,Object object){
		return concurrentKeyValueStore.put(key, object);
	}
	
	public Object put(String key,String[] Object){
		return concurrentKeyValueStore.put(key, Object);
	}
	
	public Object get(String key){
		return concurrentKeyValueStore.get(key);
	}
		
	public Object remove(String key){
		return concurrentKeyValueStore.remove(key);
		
	}

}
