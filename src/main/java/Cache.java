public interface Cache {

    void set(Object key, Object object);
    Object get(Object key);
}