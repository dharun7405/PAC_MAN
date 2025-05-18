import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.util.*;


public class PacMan extends JPanel implements ActionListener,KeyListener {
    class Block{
        int x;
        int y;
        int width;
        int height;
        Image image;
        int startX;
        int startY;
        char direction = 'U';
        int velocityX = 0;
        int velocityY = 0;

        Block(Image image,int x,int y,int width,int height){
            this.image=image;
            this.x=x;
            this.y=y;
            this.width=width;
            this.height=height;
            this.startX=x;
            this.startY=y;
        }

        void updateDirection(char direction){
            this.direction=direction;
            updateVelocity();
        }

        void updateVelocity(){
            switch (this.direction) {
                case 'U' -> {
                    this.velocityX = 0;
                    this.velocityY = -tileSize / 4;
                }
                case 'D' -> {
                    this.velocityX = 0;
                    this.velocityY = tileSize / 4;
                }
                case 'L' -> {
                    this.velocityX = -tileSize / 4;
                    this.velocityY = 0;
                }
                case 'R' -> {
                    this.velocityX = tileSize / 4;
                    this.velocityY = 0;
                }
            }
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
    Timer gameLoop;

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
        addKeyListener(this);
        setFocusable(true);

        wallImage        = new ImageIcon(Objects.requireNonNull(getClass().getResource("./img/wall.png"))).getImage();
        blueGhostImage   = new ImageIcon(Objects.requireNonNull(getClass().getResource("./img/blueGhost.png"))).getImage();
        orangeGhostImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("./img/orangeGhost.png"))).getImage();
        redGhostImage    = new ImageIcon(Objects.requireNonNull(getClass().getResource("./img/redGhost.png"))).getImage();
        pinkGhostImage   = new ImageIcon(Objects.requireNonNull(getClass().getResource("./img/pinkGhost.png"))).getImage();
        pacmanUpImage    = new ImageIcon(Objects.requireNonNull(getClass().getResource("./img/pacmanUp.png"))).getImage();
        pacmanDownImage  = new ImageIcon(Objects.requireNonNull(getClass().getResource("./img/pacmanDown.png"))).getImage();
        pacmanLeftImage  = new ImageIcon(Objects.requireNonNull(getClass().getResource("./img/pacmanLeft.png"))).getImage();
        pacmanRightImage = new ImageIcon(Objects.requireNonNull(getClass().getResource("./img/pacmanRight.png"))).getImage();

        loadMap();
        gameLoop = new Timer(50,this);
        gameLoop.start();

        System.out.println(walls.size());
        System.out.println(foods.size());
        System.out.println(ghosts.size());
    }

    public void loadMap(){
        walls = new HashSet<Block>();
        foods = new HashSet<Block>();
        ghosts = new HashSet<Block>();

        for(int r=0;r < rowCount;r++){
            for(int c=0;c < columnCount;c++){
                String row = tileMap[r];
                char tileMapChar = row.charAt(c);

                int x = c * tileSize;
                int y = r * tileSize;

                if(tileMapChar=='X'){
                    Block wall =new Block(wallImage,x,y,tileSize,tileSize);
                    walls.add(wall);
                }
                else if(tileMapChar=='b'){
                    Block ghost =new Block(blueGhostImage,x,y,tileSize,tileSize);
                    ghosts.add(ghost);
                }
                else if(tileMapChar=='o'){
                    Block ghost =new Block(orangeGhostImage,x,y,tileSize,tileSize);
                    ghosts.add(ghost);
                }
                else if(tileMapChar=='p'){
                    Block ghost =new Block(pinkGhostImage,x,y,tileSize,tileSize);
                    ghosts.add(ghost);
                }
                else if(tileMapChar=='r'){
                    Block ghost =new Block(redGhostImage,x,y,tileSize,tileSize);
                    ghosts.add(ghost);
                }
                else if(tileMapChar=='P'){
                    pacman =new Block(pacmanRightImage,x,y,tileSize,tileSize);
                }
                else if(tileMapChar==' '){
                    Block food = new Block(null,x+14,y+14,4,4);
                    foods.add(food);
                }
            }
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g){
        g.drawImage(pacman.image,pacman.x,pacman.y,pacman.width,pacman.height,null);

        for(Block ghost : ghosts){
            g.drawImage(ghost.image,ghost.x,ghost.y,ghost.width,ghost.height,null);
        }

        for(Block wall : walls){
            g.drawImage(wall.image,wall.x,wall.y,wall.width,wall.height,null);
        }

        g.setColor(Color.WHITE);
        for(Block food : foods){
            g.drawRect(food.x,food.y,food.width,food.height);
        }
    }

    public void move(){
        pacman.x += pacman.velocityX;
        pacman.y += pacman.velocityY;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP -> pacman.updateDirection('U');
            case KeyEvent.VK_DOWN -> pacman.updateDirection('D');
            case KeyEvent.VK_LEFT -> pacman.updateDirection('L');
            case KeyEvent.VK_RIGHT -> pacman.updateDirection('R');
        }
    }
}
