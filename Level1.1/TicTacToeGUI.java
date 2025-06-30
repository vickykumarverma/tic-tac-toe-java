import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToeGUI extends JFrame implements ActionListener {
    private JButton[][] buttons = new JButton[3][3];
    private boolean playerXTurn = true;
    private int moveCount = 0;

    public TicTacToeGUI() {
        setTitle("Tic-Tac-Toe Game");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 3));
        initializeButtons();
    }

    private void initializeButtons() {
        Font font = new Font("Arial", Font.BOLD, 60);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(font);
                buttons[i][j].addActionListener(this);
                add(buttons[i][j]);
            }
        }
    }

    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton) e.getSource();
        if (!clicked.getText().equals("")) return;

        clicked.setText(playerXTurn ? "X" : "O");
        moveCount++;

        if (checkForWin()) {
            JOptionPane.showMessageDialog(this,
                    "Player " + (playerXTurn ? "X" : "O") + " wins!");
            askPlayAgain();
        } else if (moveCount == 9) {
            JOptionPane.showMessageDialog(this, "It's a draw!");
            askPlayAgain();
        } else {
            playerXTurn = !playerXTurn;
        }
    }

    private boolean checkForWin() {
        String currentPlayer = playerXTurn ? "X" : "O";

        // Rows and columns
        for (int i = 0; i < 3; i++) {
            if (currentPlayer.equals(buttons[i][0].getText()) &&
                currentPlayer.equals(buttons[i][1].getText()) &&
                currentPlayer.equals(buttons[i][2].getText())) return true;

            if (currentPlayer.equals(buttons[0][i].getText()) &&
                currentPlayer.equals(buttons[1][i].getText()) &&
                currentPlayer.equals(buttons[2][i].getText())) return true;
        }

        // Diagonals
        if (currentPlayer.equals(buttons[0][0].getText()) &&
            currentPlayer.equals(buttons[1][1].getText()) &&
            currentPlayer.equals(buttons[2][2].getText())) return true;

        if (currentPlayer.equals(buttons[0][2].getText()) &&
            currentPlayer.equals(buttons[1][1].getText()) &&
            currentPlayer.equals(buttons[2][0].getText())) return true;

        return false;
    }

    private void askPlayAgain() {
        int response = JOptionPane.showConfirmDialog(this,
                "Do you want to play again?", "Play Again?",
                JOptionPane.YES_NO_OPTION);

        if (response == JOptionPane.YES_OPTION) {
            resetGame();
        } else {
            System.exit(0);
        }
    }

    private void resetGame() {
        playerXTurn = true;
        moveCount = 0;
        for (JButton[] row : buttons) {
            for (JButton btn : row) {
                btn.setText("");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TicTacToeGUI().setVisible(true);
        });
    }
}
