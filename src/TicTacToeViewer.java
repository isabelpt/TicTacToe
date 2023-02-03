/**
 * Front end for TicTacToe game
 * Displays each square, numbers for rows/cols, and the result
 * (tie or winner)
 * @author Isabel Prado-Tucker
 */

import javax.swing.*;
import java.awt.*;
public class TicTacToeViewer extends JFrame {
    /** Backend **/
    private TicTacToe backend;
    /** Window Dimensions **/
    public final int WINDOW_WIDTH = 800,
                      WINDOW_HEIGHT = 800;
    /** Board Dimensions **/
    public final int BOARD_WIDTH = (int) (WINDOW_WIDTH * 0.60),
                      BOARD_HEIGHT = (int) (WINDOW_HEIGHT * 0.60);
    /** Images **/
    private Image[] icons;

    /**
     * Constructor which initializes the backend,
     * The icon images, and sets window information
     *
     * @param backend the TicTacToe backend
     */
    public TicTacToeViewer(TicTacToe backend) {
        this.backend = backend;

        icons = new Image[2];
        icons[0] = new ImageIcon("Resources/isabel.png").getImage();
        icons[1] = new ImageIcon("Resources/taylor.png").getImage();

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("TicTacToe");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
    }

    /******************** Getters ********************/
    public Image[] getIcons() {
        return icons;
    }

    /**
     * Displays the graphics on the window
     * Draws the row and column numbers
     * Draws each square
     * If game over, prints winner or tie
     * **/
    @Override
    public void paint(Graphics g) {
        g.setColor(Color.white);
        // Reset screen
        g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        drawNums(g);
        // Go through each square in board
        Square[][] squares = backend.getBoard();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                squares[i][j].draw(g); // Draws outline and/or image and/or green background
            }
        }
        // Display the winner once the game is over
        if (backend.getGameOver()) {
            writeWinner(g);
        }
    }

    /**
     * Draws the row and column index numbers
     * Calculates location based on window and board dimensions
     * @param g
     */
    public void drawNums(Graphics g) {
        g.setColor(Color.red);
        g.setFont(new Font("Serif", Font.PLAIN, 50));
        int x_col, y_col, x_row, y_row;

        // Print columns and rows
        // -10 to account for width of number
        x_col = ((WINDOW_WIDTH - BOARD_WIDTH) / 2) + (BOARD_WIDTH / 6) - 10;
        // -40 to account for height of number
        y_col = (WINDOW_HEIGHT - BOARD_HEIGHT) / 2 - 40;
        x_row = (WINDOW_WIDTH - BOARD_WIDTH) / 4;
        // +10 to account for height of number
        y_row = ((WINDOW_HEIGHT - BOARD_HEIGHT) / 2) + (BOARD_HEIGHT / 6) + 10;
        for (int i = 0; i < 3; i++) {
            g.drawString(String.valueOf(i), x_col, y_col);
            // Move to next column
            x_col += BOARD_WIDTH / 3;

            g.drawString(String.valueOf(i), x_row, y_row);
            // Move to next row
            y_row += BOARD_WIDTH / 3;
        }
    }

    /**
     * Displays the winner (or tie) of the game
     * @param g
     */
    public void writeWinner(Graphics g) {
        g.setColor(Color.black);
        g.setFont(new Font("Serif", Font.BOLD, 100));
        // Sets message depending on tie or not
        String output = backend.checkTie() ? "It's a tie!" : backend.getWinner() + " Wins!";
        // Display on bottom middle of window
        g.drawString(output, WINDOW_WIDTH / 4, WINDOW_HEIGHT - (WINDOW_HEIGHT - BOARD_HEIGHT) / 4);
    }
}

