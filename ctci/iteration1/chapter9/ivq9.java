import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class ivq9 {
    
    // [F]uncation Implementation
    public static void printValidNQueens(int N) {
        HashMap<Integer, HashSet<Integer>> board = buildEmptyBoard(N);

        printValidNQueens(N, board, 0, 0);
    }

    private static void printValidNQueens(int N, HashMap<Integer, HashSet<Integer>> board, int row, int count) {
        if (row >= board.size()) {
            if (count != N) {
                return;
            }
            else {
                // Print the board
                printBoard(N, board);
                return;
            }
        }
        else {
            // get the set of available spaces to place a queen for the given 
            // row.
            HashSet<Integer> boardRow = board.get(row);

            if (boardRow.isEmpty()) {
                return;
            }
            else {
                for (int col : boardRow) {
                    HashMap<Integer, HashSet<Integer>> newBoard = buildEmptyBoard(board);
                    removeSpaces(newBoard, row, col);
                    printValidNQueens(N, newBoard, row + 1, count + 1);
                }
            }
        }
    }

    private static void printBoard(int N, HashMap<Integer, HashSet<Integer>> board) {
        for (int row = 0; row < N; row++) {
            StringBuffer sb = new StringBuffer(N);
            HashSet<Integer> boardRow = board.get(row);

            if (boardRow == null) {
                throw new UnsupportedOperationException(
                    "Board is invalid"
                );
            }

            for (int col = 0; col < N; col++) {
                if (boardRow.contains(col)) {
                    sb.append('Q');
                }
                else {
                    sb.append('-');
                }
            }
            System.out.println(sb.toString());
        }

        System.out.println("");
    }

    private static void removeSpaces(HashMap<Integer, HashSet<Integer>> board, int row, int col) {
        HashSet<Integer> boardRow = board.get(row);

        Integer [] rowPositions = new Integer [boardRow.size()];
        boardRow.toArray(rowPositions);

        // remove left / right
        for (int key : rowPositions) {
            if (key != col) {
                boardRow.remove(key);
            }
        }

        // Place back in.
        board.put(row, boardRow);

        // remove top / bottom
        for (int rowKey = 0; rowKey < board.size(); rowKey++) {
            if (rowKey != row) {
                boardRow = board.get(rowKey);
                boardRow.remove(col);
                board.put(rowKey, boardRow);
            }
        }

        // Remove Diagnal UpperLeft
        int diagVertical = row - 1, diagHorizontal = col - 1;

        while (diagVertical >= 0 && diagHorizontal >= 0) {
            boardRow = board.get(diagVertical);
            boardRow.remove(diagHorizontal);
            board.put(diagVertical, boardRow);
            diagVertical--;
            diagHorizontal--;
        }
        
        // Remove Diagonal Upper Right
        diagVertical = row - 1;
        diagHorizontal = col + 1;

        while (diagVertical >= 0 && diagHorizontal < board.size()) {
            boardRow = board.get(diagVertical);
            boardRow.remove(diagHorizontal);
            board.put(diagVertical, boardRow);
            diagVertical--;
            diagHorizontal++;
        }

        // Remove Diagonal Lower Right
        diagVertical = row + 1;
        diagHorizontal = col + 1;

        while (diagVertical < board.size() && diagHorizontal < board.size()) {
            boardRow = board.get(diagVertical);
            boardRow.remove(diagHorizontal);
            board.put(diagVertical, boardRow);
            diagVertical++;
            diagHorizontal++;
        }

        // Remove Diagonal Lower Left
        diagVertical = row + 1;
        diagHorizontal = col - 1;

        while (diagVertical < board.size() && diagHorizontal >= 0) {
            boardRow = board.get(diagVertical);
            boardRow.remove(diagHorizontal);
            board.put(diagVertical, boardRow);
            diagVertical++;
            diagHorizontal--;
        }
    }

    private static HashMap<Integer, HashSet<Integer>> buildEmptyBoard(int N) {
        HashMap<Integer, HashSet<Integer>> board = new HashMap<Integer, HashSet<Integer>>();

        for (int row = 0; row < N; row++) {
            HashSet<Integer> availableCols = new HashSet<Integer>();
            for (int col = 0; col < N; col++) {
                availableCols.add(col);
            }
            board.put(row, availableCols);
        }

        return board;
    }
    
    private static HashMap<Integer, HashSet<Integer>> buildEmptyBoard(HashMap<Integer, HashSet<Integer>> oldBoard) {
        HashMap<Integer, HashSet<Integer>> board = new HashMap<Integer, HashSet<Integer>>();

        for (int key : oldBoard.keySet()) {
            HashSet<Integer> boardRow = new HashSet<Integer>(oldBoard.get(key).size());

            for (int value : oldBoard.get(key)) {
                boardRow.add(value);
            }

            board.put(key, boardRow);
        }

        return board;
    }

    public static void main(String [] args) {
        printValidNQueens(10);
    }
}