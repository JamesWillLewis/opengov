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
