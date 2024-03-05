package ba.smoki.six;

import java.util.List;

public interface Dao<E>{

    List<E> findAll();

    List<String> findColumnNames();

    void update(E entity);
}
