import java.util.Queue;
import java.util.List;
import java.util.LinkedList;

public class ivq3 {
    private static class JukeBox {
        private static JukeBox mInstance;
        private static final int COUNT = 10; 
        private static Queue<Record> mPlayQueue = new LinkedList<Record>();
        private static Record currentRecord = null;

        private JukeBox() {

        }

        public JukeBox getInstance() {
            if (JukeBox.mInstance == null) {
                JukeBox.mInstance = new JukeBox();
            }

            return JukeBox.mInstance;
        }

        // Operates on the current player
        public void play() {
            if (!JukeBox.mPlayQueue.isEmpty()) {
                JukeBox.currentRecord = JukeBox.mPlayQueue.remove();
                // actual play
            }
        }

        public void pause() {
            if (JukeBox.currentRecord != null) {
                // tell player to pause
            }
        }

        public void skip() {
            this.stop();
        }

        public void stop() {

        }

        // Operates on the list of songs to play
        public void add(Record song) {

        }

        public void remove(Record song) {

        }

        // Operates on a data storage
        public List<Record> search(String searchString) {
            return null;
        }
    }

    private class Record {
        private final String title;
        private final String album;
        private final String lyrics;
        private Song song;

        public Record(String title, String album, String lyrics) {
            this.title = title;
            this.album = album;
            this.lyrics = lyrics;
        }

        private class Song {

        }
    }
}