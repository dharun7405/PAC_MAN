import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class PacMan extends JPanel {
    final int rowCount = 21;
    final int columnCount = 19;
    final int tileSize = 32;
    final int boardWidth = columnCount * tileSize;
    final int boardHeight = rowCount * tileSize;

    Image wallImage;
    Image blueGhostImage;
    Image orangeGhostImage;
    Image redGhostImage;
    Image pinkGhostImage;
    Image pacmanUpImage;
    Image pacmanDownImage;
    Image pacmanLeftImage;
    Image pacmanRightImage;

    PacMan(){
        setPreferredSize(new Dimension(boardWidth,boardHeight));
        setBackground(Color.black);

        wallImage        = new ImageIcon(Objects.requireNonNull(getClass().getResource("./img/wall.png"))).getImage();
        blueGhostImage   = new ImageIcon(Objects.requireNonNull(getClass().getResource("./img/blueGhost.png"))).getImage();
        orangeGhostImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("./img/orangeGhost.png"))).getImage();
        redGhostImage    = new ImageIcon(Objects.requireNonNull(getClass().getResource("./img/redGhost.png"))).getImage();
        pinkGhostImage   = new ImageIcon(Objects.requireNonNull(getClass().getResource("./img/pinkGhost.png"))).getImage();
        pacmanUpImage    = new ImageIcon(Objects.requireNonNull(getClass().getResource("./img/pacmanUp.png"))).getImage();
        pacmanDownImage  = new ImageIcon(Objects.requireNonNull(getClass().getResource("./img/pacmanDown.png"))).getImage();
        pacmanLeftImage  = new ImageIcon(Objects.requireNonNull(getClass().getResource("./img/pacmanLeft.png"))).getImage();
        pacmanRightImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("./img/pacmanRight.png"))).getImage();
    }
}
