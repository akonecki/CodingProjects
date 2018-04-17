public class ivq8 {
    public static boolean isRotation(String s1, String s2) {
        return ivq8.isSubstring(s1, s2 + s2);
    }

    public static boolean isSubstring(String s1, String s2) {
        return s2.contains(s1);
    }

    public static void main(String [] args) {
        String s1 = "hellothere";
        String s2 = "otherehell";
        String s3 = "ellotherhe";

        System.out.println(ivq8.isRotation(s1, s2));
        System.out.println(ivq8.isRotation(s1, s3));
        System.out.println(ivq8.isRotation(s2, s3));
    }
}