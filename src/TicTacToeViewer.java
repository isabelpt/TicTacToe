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

    public Image[] getIcons() {
        return icons;
    }

    public void paint(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        drawNums(g);

        Square[][] squares = backend.getBoard();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                squares[i][j].draw(g);
            }
        }

        if (backend.getGameOver()) {
            writeWinner(g);
        }
    }

    public void drawNums(Graphics g) {
        g.setColor(Color.red);
        g.setFont(new Font("Serif", Font.PLAIN, 50));
        int x_col, y_col, x_row, y_row;

        // Print columns
        x_col = ((WINDOW_WIDTH - BOARD_WIDTH) / 2) + (BOARD_WIDTH / 6) - 10;
        y_col = (WINDOW_HEIGHT - BOARD_HEIGHT) / 2 - 40;
        x_row = (WINDOW_WIDTH - BOARD_WIDTH) / 4;
        y_row = ((WINDOW_HEIGHT - BOARD_HEIGHT) / 2) + (BOARD_HEIGHT / 6) + 10;
        for (int i = 0; i < 3; i++) {
            g.drawString(String.valueOf(i), x_col, y_col);
            x_col += BOARD_WIDTH / 3;

            g.drawString(String.valueOf(i), x_row, y_row);
            y_row += BOARD_WIDTH / 3;
        }
    }

    public void writeWinner(Graphics g) {
        g.setColor(Color.black);
        g.setFont(new Font("Serif", Font.BOLD, 100));
        String output = backend.checkTie() ? "It's a tie!" : backend.getWinner() + " Wins!";
        g.drawString(output, WINDOW_WIDTH / 4, WINDOW_HEIGHT - (WINDOW_HEIGHT - BOARD_HEIGHT) / 4);
    }
}
