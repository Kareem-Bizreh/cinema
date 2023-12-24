public class Pair<K, V> {
    private K key;
    private V value;

    public Pair() {
    }

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public boolean equals(Pair<K, V> other) {
        if(other.key == this.key && other.value == this.value)
        {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "(" + key + ", " + value + ")";
    }
}
