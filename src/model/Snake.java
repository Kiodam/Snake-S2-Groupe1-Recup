package src.model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.awt.*;
import java.util.ArrayList;


/**
* méthode pour les déplacements du serpent
 * vérifie que le serpent ne se touche pas
 * vérifie que le serpent ne sort pas de la grille
 */
public class Snake {
    public static String cheminSkin = "src/ressource/image/Skin/Green/";
    private Color headColor = Color.web("2eb872");
    private Color bodyColor = Color.web("a3de83");
    private boolean dead = false;
    private final Point head = new Point();
    private final ArrayList<Point> body = new ArrayList<>();
    private final int gx;
    private final int gy;
    private boolean ignoreBorders = false;
    private boolean noBorder;
    private Obstacle obstacle;
    private Image imgInputHead = new Image(cheminSkin+"snakehead.png");
    private ImageView imageHead = new ImageView(imgInputHead);
    private Image imgInputBody = new Image(cheminSkin+"snakeBody.png");
    private ImageView imageBody = new ImageView(imgInputBody);
    private Image imgInputTurn = new Image(cheminSkin+"snakeTurnLeft.png");
    private ImageView imageTurn = new ImageView(imgInputTurn);
    private Image imgInputTail = new Image(cheminSkin+"snakeTail.png");
    private ImageView imageTail = new ImageView(imgInputTail);
    private int snakeDir = 270;
    private int nbItemEaten = 0;

    public Snake(int x, int y, boolean noBorder, Obstacle obstacle) {
        this.obstacle = obstacle;
        this.noBorder = noBorder;
        this.head.setLocation(x / 2, y / 2);
        this.body.add(new Point(x / 2 + 1, y / 2));
        this.gx = x;
        this.gy = y;
    }

    /**
     * déplace le serpent dans la dirction indiquer
     * U = up
     * D = down
     * L = left
     * R = right
     */
    public void move(char dir) {
        body.add(new Point(head));
        //switch case pour chaque direction possible (haut, bas, gauche, droite)

        switch (dir) {
            case 'U':
                snakeDir = 0;
                head.translate(0, -1);
                if (borderControl() && (!ignoreBorders && !noBorder) || !(!obstacle.getPointsObstacles().contains(head) || ignoreBorders || noBorder)) {
                    head.translate(0, 1);
                    dead = true;
                } else if (borderControl() && (ignoreBorders || noBorder)) {
                    head.setLocation(head.getX(), gy - 1);
                }
                break;

            case 'D':
                snakeDir = 180;
                head.translate(0, 1);
                if (borderControl() && (!ignoreBorders && !noBorder) || !(!obstacle.getPointsObstacles().contains(head) || ignoreBorders || noBorder)) {
                    head.translate(0, -1);
                    dead = true;
                } else if (borderControl() && (ignoreBorders || noBorder)) {
                    head.setLocation(head.getX(), 0);
                }
                break;

            case 'L':
                snakeDir = 270;
                head.translate(-1, 0);
                if (borderControl() && (!ignoreBorders && !noBorder) || !(!obstacle.getPointsObstacles().contains(head) || ignoreBorders || noBorder)) {
                    head.translate(1, 0);
                    dead = true;
                } else if (borderControl() && (ignoreBorders || noBorder)) {
                    head.setLocation(gx - 1, head.getY());
                }
                break;

            case 'R':
                snakeDir = 90;
                head.translate(1, 0);
                if (borderControl() && (!ignoreBorders && !noBorder) || !(!obstacle.getPointsObstacles().contains(head) || ignoreBorders || noBorder)) {
                    head.translate(-1, 0);
                    dead = true;
                } else if (borderControl() && (ignoreBorders || noBorder)) {
                    head.setLocation(0, head.getY());
                }
                break;
        }

        // Le serpent doit avoir une longueur > 4 pour se touche
        if (body.size() > 4 && hitSelf() && !body.get(0).equals(head)) {
            dead = true;
        }
    }

    /**
     * Indique l'angle dans lequel le corps du snake doit être placé, soit horizontalement, soit verticalement.
     * @param bodyCount quel numéro du corps du serpent est déterminé
     * @return angle du corps
     */
    public int getBodyDir(int bodyCount) {
        if (bodyCount == 0) {
            return 0;
        } else if (body.get(bodyCount).getY() == body.get(bodyCount - 1).getY()) {
            return 270;
        } else {
            return 0;
        }
    }

    /**
     * vérifie que la pomme se situe sur la tête du serpent
     * enlève l'extrémité de la queue
     */
    public boolean ateItem(Item item) {
        if (!head.equals(item.getPos())) {
            body.remove(0);
        }
        return head.equals(item.getPos());
    }

    /**
     * Retrurn true si la tête du serpent est à l'exterieur de la queue
     */
    private boolean borderControl() {
        return head.getX() < 0 || head.getY() < 0 || head.getX() >= gx || head.getY() >= gy;
    }

    /**
     * retrun true si la tête touche le corp;
     */
    public boolean hitSelf() {
        return body.contains(head);
    }

    /**
     * incrémentation du nombre d'item mangé
     */
    public void incNbItemEaten() {
        this.nbItemEaten++;
    }

    /**
     * réinitialise le nombre d'item mangé
     */
    public void resetNbItemEaten() {
        this.nbItemEaten = 0;
    }

    public ArrayList<Point> getBody() {
        return new ArrayList<>(body);
    }

    public Point getHead() {
        return new Point(head);
    }

    public boolean getDead() {
        return dead;
    }

    public void ignoreBorders(boolean ign) {
        ignoreBorders = ign;
    }

    public ImageView getImageViewHead() {
        return imageHead;
    }

    public ImageView getImageViewBody() {
        return imageBody;
    }

    public ImageView getImageViewTurn() {
        return imageTurn;
    }

    public int getSnakeDir() {
        return snakeDir;
    }

    public ImageView getImageViewTail() {
        return imageTail;
    }

    public int getNbItemEaten() {
        return nbItemEaten;
    }
}
