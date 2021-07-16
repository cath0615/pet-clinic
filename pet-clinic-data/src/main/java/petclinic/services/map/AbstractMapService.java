package petclinic.services.map;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//An abstract class allows you to create functionality that subclasses can implement or override.
//An interface only allows you to define functionality, not implement it.
//Abstract classes cannot be instantiated
//we want to have a map implementation, a Spring Data JPA implementation so we create this class
public abstract class AbstractMapService<T, ID> {
    //Protected keyword in Java refers to one of its access modifiers. The methods or data members
    //declared as protected can be accessed from: Within the same class. Subclasses of same packages.
    protected Map<ID, T> map = new HashMap<>();

    Set<T> findAll() {
        //return back a new hashset of values
        return new HashSet<>(map.values());
    }

    T findById(ID id) {
        return map.get(id);
    }

    //bc we don't know the specific object, we override it to include id
    T save(ID id, T object) {
        map.put(id, object);
        return(object);
    }

    void deleteById(ID id) {
        map.remove(id);
    }

    void delete(T object) {
        map.entrySet().removeIf(entry -> entry.getValue().equals(object));
    }

    //the whole class gives us a base map service that we can implement
}
