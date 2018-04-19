import java.util.Iterator;

// Would be just a copy of a hash that is similar to say the LinearProbing array hash.
// Usage would be on the client side that would have say a set of approved IP addresses 
// where each IP is a Key and the client may have a list of hashes.
//
// Leaving blank since at this API level is the same as the Linear Probing.
public class SET <Key> implements Iterable {
    public SET() {

    }

    public void add(Key key) {
        return;
    }

    public boolean contains(Key key) {
        return false;
    }

    public void remove(Key key) {
        return;
    }

    public int size() {
        return 0;
    }

    public Iterator<Key> iterator() {
        return null;
    }

    public static void main(String [] args) {

    }
}