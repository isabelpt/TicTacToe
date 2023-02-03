import javax.swing.*;
import java.awt.*;

/**
 * A class written to support the TicTacToe Game.
 *
 * Each Square object is one position of the TicTacToe
 * board. It maintains information on the marker, its
 * location on the board, and whether it is part
 * of the winning set.
 *
 * @author: Nandhini Namasivayam
 * @version: Jan 2023
 */

public class Square {
    private String marker;
    private int row;
    private int col;
    private boolean isWinningSquare;
    /** Graphics Window**/
    private TicTacToeViewer window;

    /**
     * Constructor to initialize one Square of the
     * TicTacToe board
     * @param row the row the square is in
     * @param col the column the square is in
     */
    public Square(int row, int col, TicTacToeViewer window) {
        this.window = window;
        this.row = row;
        this.col = col;
        this.marker = TicTacToe.BLANK;
        this.isWinningSquare = false;
    }

    /******************** Getters and Setters ********************/
    public String getMarker() {
        return this.marker;
    }

    public void setMarker(String marker) {
        this.marker = marker;
    }

    public void setWinningSquare() {
        this.isWinningSquare = true;
    }

    /**
     * Checks if the square has the BLANK marker
     * @return True if the square is empty, False otherwise
     */
    public boolean isEmpty() {
        return this.marker.equals(TicTacToe.BLANK);
    }

    /**
     * Calculates the dimensions of the square and draws border
     * If not empty, draws the image and if it is a winning square
     * makes it green
     * @param g graphics object to draw on window
     */
    public void draw(Graphics g) {
        g.setColor(Color.black);
        int x = (window.BOARD_WIDTH / 3 * (row + 1)),
            y = (window.BOARD_WIDTH / 3 * (col + 1));
        int width = window.BOARD_WIDTH / 3,
            height = window.BOARD_HEIGHT / 3;
        g.drawRect(x, y, width, height);
        if (isEmpty()) return;
        if(isWinningSquare) {
            g.setColor(Color.green);
            g.fillRect(x, y, width, height);
        }

        Image icon = marker.equals("X") ? new ImageIcon(window.getIcons()[0]).getImage()
                : new ImageIcon(window.getIcons()[1]).getImage();
        g.drawImage(icon, x, y, width, height, window);
    }

    /**
     * @return the marker for the square
     */
    public String toString() {
        return this.marker;
    }
}
