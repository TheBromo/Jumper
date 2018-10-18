package ch.bbw;

import java.io.*;

public class ScoreManager {

    public void addScore(int score) {
        try {
            Writer output;

            output = new BufferedWriter(new FileWriter("highScores.jmp", true));
            output.append(String.valueOf(-score)).append("\n");
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getHighScore() {
        int highScore = 0;
        try {

            BufferedReader reader = new BufferedReader(new FileReader("highScores.jmp"));
            String line;
            while ((line = reader.readLine()) != null) {
                int score = Integer.parseInt(line);
                if (score > highScore) highScore = score;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return highScore;
    }
}
