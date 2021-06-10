package src.model;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

/**
 * géstion du le HighScore
 */
public class HighScore {
    private String path = "HighScore.txt";

    private boolean newHighScore = false;
    private String[][] scores = new String[10][2];

    public HighScore() {
            for (int i = 0; i < 10; i++) {
                scores[i][1] = "0";
            }
            for (int i = 0; i < 10; i++) {
                scores[i][0] = "   ";
            }
    }

    /**
     * lit le .txt avec les HighScores
     */
    public void readHighScoreFromFile() {
        File file = new File("HighScore.txt");
        int id = 0;
        try {
            if (file.createNewFile()) {
            } else {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    String next = scanner.nextLine();
                    if (id % 2 == 0) {
                        scores[id / 2][0] = next;
                    } else {
                        scores[id / 2][1] = next;
                    }
                    id++;
                }
                scanner.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        sortArray();
    }

    /**
     * Ajoute un nouveau score
     *
     * @param pseudo
     */
    public void addScore(Game game, String pseudo) {
        sortArray();
        if (game.getScorePoints() > Integer.parseInt(scores[9][1])) {
            newHighScore = true;
            String newScore = Integer.toString(game.getScorePoints());
            scores[9][0] = pseudo;
            scores[9][1] = newScore;
        }
        sortArray();
        try {
            writeNewHighScore();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * La fonction compare les scores dans le tableau,
     * et le trie de manière à ce que le nom corresponde au score.
     *  le tri se fait ensuite par ordre croissant
     */
    public void sortArray() {
        Arrays.sort(scores, (first, second) -> Integer.valueOf(second[1]).compareTo(Integer.valueOf(first[1])));
    }

    /**
     * Ecrit le nouveau highscore dans un fichier
     *
     * @throws IOException
     */
    private void writeNewHighScore() throws IOException {
        File file = new File(path).getAbsoluteFile();

        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        for (int i = 0; i < scores.length; i++) {
            writer.write(String.valueOf(scores[i][0]));
            writer.newLine();
            writer.write(String.valueOf(scores[i][1]));
            if (i != scores.length - 1) writer.newLine();
        }
        writer.close();
    }

    public String[][] getTotalArray() {
        return scores;
    }
}

