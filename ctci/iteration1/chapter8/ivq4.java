import java.util.List;

public class ivq4 {
    public static class ParkingMonitorSystem {
        // instance of the parking structure
        // indicate count of number of occuppied spots
        // indicate the number of vehicles within the structure itself
    }
    
    public static class ParkingStructure {
        // would have maybe a list of lists of parking spots
    }

    public static class ParkingSpot {
        // location information
        // maybe size
        // sppot would have an associated sensor
    }

    public static class Sensor {
        private final Camera mCamera;
        private final Light mLight;

        public Sensor() {
            this.mCamera = new Camera(1,1,1,1);
            this.mLight = new Light();
        }

        public boolean isSpaceOccuppied() {
            Camera.DetectableObject object = this.mCamera.detectObject();
            if (object ==  Camera.DetectableObject.Vehicle ||  
                object == Camera.DetectableObject.Blocked) {
                this.mLight.setColor(Light.Color.Red);
                this.mLight.setState(1);
                return true;
            } 
            else {
                return false;
            }    
        }

        /**
         * @brief camera supports detecting of a car in every spot 1-1 
         * relationship.
         */
        public static class Camera {
            private final int mId;
            private final int mFloor;
            private final double mLatitude;
            private final double mLongitude;
            
            public enum DetectableObject {
                Person(0), Bike(1), Vehicle(2), Blocked(3);
                int value;
                private DetectableObject(int v) {
                    this.value = v;
                }
                public static DetectableObject getObject(int value) {
                    switch(value) {
                        case 0:
                            return DetectableObject.Person;
                        case 1:
                            return DetectableObject.Bike;
                        case 2:
                            return DetectableObject.Vehicle;
                        default:
                            return DetectableObject.Blocked;
                    }
                }
            }

            public Camera(int id, int floor, double latitude, double longitude) {
                this.mId = id;
                this.mFloor = floor;
                this.mLatitude = latitude;
                this.mLongitude = longitude;
            }

            public DetectableObject detectObject() {
                if (true) {
                    return DetectableObject.Vehicle;
                }  
                else {
                    return DetectableObject.Person;
                }  
            }
        }

        private static class Light {
            private int mState;
            private Color mColor;

            public enum Color {
                Red(0), Green(1), Blue(2), Yellow(3);
                int value = 0;
                private Color(int v) {
                    this.value = v;
                }
                public static Color getColor(int v) {
                    switch(v) {
                        case 1:
                            return Green;
                        case 2:
                            return Blue;
                        case 3:
                            return Yellow;
                        default:
                            return Red;
                    }
                }
            }

            public void setColor(Color color) {
                this.mColor = color;

                this.dispatch();
            }

            public void setState(int state) {
                this.mState = state;

                this.dispatch();
            }

            // Actuall commands the controller for light.
            public void dispatch() {

            }
        }
    }

}