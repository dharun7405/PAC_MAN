import javax.swing.*;
import java.awt.*;
import java.util.*;

public class PacMan extends JPanel {
    class Block{
        int x;
        int y;
        int width;
        int height;
        Image image;
        int startX;
        int startY;

        Block(Image image,int x,int y,int width,int height){
            this.image=image;
            this.x=x;
            this.y=y;
            this.width=width;
            this.height=height;
            this.startX=x;
            this.startY=y;
        }
    }

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

    HashSet<Block> walls;
    HashSet<Block> foods;
    HashSet<Block> ghosts;
    Block pacman;

    //X = wall, O = skip, P = pac man, ' ' = food
    //Ghosts: b = blue, o = orange, p = pink, r = red
    final String[] tileMap = {
            "XXXXXXXXXXXXXXXXXXX",
            "X        X        X",
            "X XX XXX X XXX XX X",
            "X                 X",
            "X XX X XXXXX X XX X",
            "X    X       X    X",
            "XXXX XXXX XXXX XXXX",
            "OOOX X       X XOOO",
            "XXXX X XXrXX X XXXX",
            "O       bpo       O",
            "XXXX X XXXXX X XXXX",
            "OOOX X       X XOOO",
            "XXXX X XXXXX X XXXX",
            "X        X        X",
            "X XX XXX X XXX XX X",
            "X  X     P     X  X",
            "XX X X XXXXX X X XX",
            "X    X   X   X    X",
            "X XXXXXX X XXXXXX X",
            "X                 X",
            "XXXXXXXXXXXXXXXXXXX"
    };

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

    public void loadMap(){
        walls = new HashSet<Block>();
        foods = new HashSet<Block>();
        ghosts = new HashSet<Block>();

        for(int r=0;r < rowCount;r++){
            for(int c=0;c < columnCount;c++){
                String row = tileMap[r];
                char tileMapChar = row.charAt(c);

                int x = c*tileSize;
                int y = r*tileSize;
            }
        }
    }
}
