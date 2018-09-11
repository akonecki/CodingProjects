public class Solution{
    public static void reverse_words (char[] sentence) {
      // reverse first
      for (int index = 0; index < sentence.length / 2; index++) {
        char temp = sentence[index];
        
        sentence[index] = sentence[sentence.length - 1 - index];
        sentence[sentence.length - 1 - index] = temp;
      }
      
      System.out.println(sentence);
      
      // reverse up to individual spaces.
      for (int start = 0; start < sentence.length; ) {
        System.out.println(sentence);
        if (sentence[start] == ' ' || sentence[start] == '\t') {
          start++;  
        }
        else {
          // reverse up to the space
          int end = start;
          for (; end < sentence.length; end++) {
            if (sentence[end] == ' ') {
              break;  
            }
          }
          
          System.out.println(start + " " + end);
          
          // now reverse
          int pivot = end;
          while (start < end) {
            char temp = sentence[start];
            sentence[start] = sentence[end - 1];
            sentence[end - 1] = temp;
            end--;
            start++;
          }
          start = pivot;
        }
      }
    }
    
    public static void main(String [] args) {
        String sentence = "   hello world!   ";
        char [] array = sentence.toCharArray();
        reverse_words(array);
        System.out.println(array);
    }
  }  