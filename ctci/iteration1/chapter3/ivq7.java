public class ivq7 {
    private Node mHead = null;
    private Node mTail = null;
    private Node mDog = null;
    private Node mCat = null;

    public interface Pet {
    }

    public static class Dog implements Pet {
        private int id;
        public Dog(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }
    }

    public static class Cat implements Pet {
        private int id;
        public Cat(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }
    }

    private class Node {
        private Pet mPet = null;
        private Node mNext = null;

        public Node(Pet pet) {
            this.mPet = pet;
        }

        public Node findNext() {
            Node node = this.mNext;

            while (node != null) {
                if (node.mPet.getClass() == this.mPet.getClass()) {
                    break;
                }
                node = node.mNext;
            }

            return node;
        }

        public boolean isDog() {
            return this.mPet.getClass() == Dog.class;
        }

        public boolean isCat() {
            return this.mPet.getClass() == Cat.class;
        }
    }

    public void enqueue(Pet pet) {
        if (pet == null) {
            return;
        }

        Node node = new Node(pet);

        if (this.mTail == null) {
            this.mHead = node;
        }
        else {
            this.mTail.mNext = node;
        }

        if (this.mDog == null && node.isDog()) {
            this.mDog = node;
        }
        else if (this.mCat == null & node.isCat()) {
            this.mCat = node;
        }

        this.mTail = node;
    }

    public Pet dequeueAny() {
        if (this.mHead == null) {
            return null;
        }

        Pet pet = null;
        Node node = null;

        node = this.mHead;
        this.mHead = node.mNext;

        if (node.isCat()) {
            this.mCat = node.findNext();
        }
        else {
            this.mDog = node.findNext();
        }

        if (this.mHead == null) {
            this.mTail = null;
            this.mDog = null;
            this.mCat = null;
        }

        pet = node.mPet;

        return pet;
    }

    public Cat dequeueCat() {
        if (this.mHead == null || this.mCat == null) {
            return null;
        }

        Node node = this.mCat;

        if (this.mHead == this.mCat) {
            this.mHead = this.mHead.mNext;
        }

        this.mCat = node.findNext();

        if (this.mHead == null) {
            this.mTail = null;
        }        

        return (Cat) node.mPet;
    }

    public Dog dequeueDog() {
        if (this.mHead == null || this.mDog == null) {
            return null;
        }

        Node node = this.mDog;

        if (this.mHead == this.mDog) {
            this.mHead = this.mHead.mNext;
        }

        this.mDog = node.findNext();

        if (this.mHead == null) {
            this.mTail = null;
        }        

        return (Dog) node.mPet;
    }

    public static void main(String [] args) {
        ivq7 pets = new ivq7();
        pets.enqueue(new Dog(100));
        pets.enqueue(new Dog(75));
        pets.enqueue(new Dog(86));
        pets.enqueue(new Dog(33));
        pets.enqueue(new Cat(15));

        assert (((Dog)(pets.dequeueAny())).id == 100);
        assert (((Dog)(pets.dequeueAny())).id == 75);
        assert (((Dog)(pets.dequeueAny())).id == 86);
        assert (((Cat)(pets.dequeueCat())).id == 15);
        assert (pets.dequeueCat() == null);
        pets.enqueue(new Cat(15));
        pets.enqueue(new Cat(15));
        pets.enqueue(new Cat(15));
        assert (pets.dequeueCat() != null);
        assert (pets.dequeueCat() != null);
        assert (pets.dequeueCat() != null);
    }
}