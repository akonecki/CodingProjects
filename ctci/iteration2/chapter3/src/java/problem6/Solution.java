import java.util.Queue;
import java.util.LinkedList;

public class Solution {
    public static class Shelter {
        private Queue<ShelterAnimal> dogs = new LinkedList<ShelterAnimal>();
        private Queue<ShelterAnimal> cats = new LinkedList<ShelterAnimal>();
        int index = 0;

        private static class ShelterAnimal {
            private Animal animal;
            private int index;

            public ShelterAnimal(Animal animal, int index) {
                this.animal = animal;
                this.index = index;
            }
        }

        public void enqueue(Cat cat) {
            cats.add(new ShelterAnimal(cat, this.index));
            this.index++;
        }

        public void enqueue(Dog dog) {
            dogs.add(new ShelterAnimal(dog, this.index));
            this.index++;
        }
        
        public Cat getCat() {
            if (cats.isEmpty()) {
                return null;
            }
            else {
                return (Cat) cats.remove().animal;
            }
        }

        public Dog getDog() {
            if (dogs.isEmpty()) {
                return null;
            }
            else {
                return (Dog) dogs.remove().animal;
            }
        }

        public Animal getAny() {
            if (!dogs.isEmpty() && !cats.isEmpty()) {
                if (dogs.peek().index < cats.peek().index) {
                    return dogs.remove().animal;
                }
                else {
                    return cats.remove().animal;
                }
            }
            else if (!dogs.isEmpty()) {
                return dogs.remove().animal;
            }
            else {
                return cats.remove().animal;
            }
        }
    }

    public static class Animal {
        private String name;

        public Animal(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }
    }

    public static class Cat extends Animal {
        public Cat(String name) {
            super(name);
        }
    }

    public static class Dog extends Animal {
        public Dog(String name) {
            super(name);
        }
    }

    public static void main(String [] args) {
        Shelter myShelter = new Shelter();
        myShelter.enqueue(new Dog("helper"));
        myShelter.enqueue(new Cat("snowball"));
        myShelter.enqueue(new Cat("orange"));
        myShelter.enqueue(new Cat("blacky"));
        myShelter.enqueue(new Cat("booboo"));
        myShelter.enqueue(new Dog("beulah"));
        myShelter.enqueue(new Dog("tempest"));
        myShelter.enqueue(new Dog("patches"));

        assert (myShelter.getDog().getName() == "helper");
        assert (myShelter.getDog().getName() == "beulah");
        assert (myShelter.getAny().getName() == "snowball");
        assert (myShelter.getCat().getName() == "orange");
    }
}