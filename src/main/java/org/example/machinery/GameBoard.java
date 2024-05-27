package org.example.machinery;

import java.util.Arrays;
import java.util.function.IntFunction;

import static org.example.machinery.Shape.BISHOP;
import static org.example.machinery.Shape.EMPTY;
import static org.example.machinery.Shape.KING;
import static org.example.machinery.Shape.KNIGHT;
import static org.example.machinery.Shape.PAWN;
import static org.example.machinery.Shape.QUEEN;
import static org.example.machinery.Shape.ROOK;
import static org.example.machinery.Team.BLACK;
import static org.example.machinery.Team.WHITE;

public record GameBoard(Piece[] board, Team movingTeam, Move[] history) {
    public static final GameBoard STARTING_BOARD = GameBoard.create(i -> {
        char id = """
                RNBQKBNR
                PPPPPPPP
                ........
                ........
                ........
                ........
                pppppppp
                rnbqkbnr""".replaceAll("\\n", "").charAt(i);
        return Piece.create(Character.isUpperCase(id) ? WHITE : BLACK, switch (Character.toLowerCase(id)) {
            case 'r' -> ROOK;
            case 'n' -> KNIGHT;
            case 'b' -> BISHOP;
            case 'q' -> QUEEN;
            case 'k' -> KING;
            case 'p' -> PAWN;
            case '.' -> EMPTY;
            default -> throw new IllegalStateException("Unexpected value: " + Character.toLowerCase(id));
        }, MovingState.HAS_NOT_MOVED);
    }, WHITE, new Move[]{});
    
    public static GameBoard create(IntFunction<Piece> indexPieceConverter, Team movingTeam, Move[] history) {
        Piece[] board = new Piece[64];
        Arrays.setAll(board, indexPieceConverter);
        return new GameBoard(board, movingTeam, history);
    }
    
    public GameBoard blindMove(Move move, Team newMovingTeam, Move[] newHistory) {
        return GameBoard.create(i -> {
            if (i == move.getStartIndex()) {
                return Piece.EMPTY_TILE;
            } else if (i == move.getEndIndex()) {
                return board[move.getStartIndex()];
            } else {
                return board[i];
            }
        }, newMovingTeam, newHistory);
    }
    
    public GameBoard blindMove(Move move) {
        return this.blindMove(move, this.movingTeam, this.history);
    }
    
    public String toDisplayString() {
        String header = "   a  b  c  d  e  f  g  h\n";
        StringBuilder out = new StringBuilder().append(header);
        for (int row = 7; row >= 0; row--) {
            out.append(row + 1).append(" ");
            for (int col = 0; col < 8; col++) {
                out.append(board[row * 8 + col].toDisplayString());
            }
            out.append(" ").append(row + 1).append("\n");
        }
        return out.append(header).toString();
    }
}
