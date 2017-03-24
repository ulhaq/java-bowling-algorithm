package bowling;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 *
 * @author Ulhaq
 */
public class Bowling {
    private int totalScores;
    private int[][] points;
    private int[] scores;
    private int[][] pointsFrames;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Bowling b = new Bowling();
        int[][] arr = new int[][] {{3,4},{2,1},{3,5},{3,2},{4,6},{6,4},{10,0},{9,1},{5,5},{5,5},{3,0}};
        
        b.setPoints(arr);
        b.setScores(b.getPoints());
        System.out.print(Arrays.toString(b.getScores()));
    }
    
    /**
     *
     * @param points
     */
    public void setPoints(int[][] points) {
        this.points = points;
    }
    
    /**
     *
     * @return the points
     */
    public int[][] getPoints() {
        return this.points;
    }
        
    /**
     *
     * @param points
     */
    public void setScores(int[][] points) {
        this.scores = (points.length < 10) ? new int[points.length] : new int[points.length-1];
        
        int currentTotalScores = 0;
        int length = points.length;
        this.pointsFrames = points;
        
        for(int frame = 0; frame < length; frame++) {
            if(frame < 10) {
                if(this.isStrike(points[frame]) == true) {
                    this.scores[frame] = this.strikeScores(frame) + currentTotalScores;
                }
                else if(this.isSpare(points[frame]) == true) {
                    this.scores[frame] = this.spareScores(frame) + currentTotalScores;
                }
                else {
                    this.scores[frame] = this.missScores(frame) + currentTotalScores;
                }
                currentTotalScores = this.scores[frame];
            }
        }
    }
    
    /**
     *
     * @return the scores
     */
    public int[] getScores() {
        return this.scores;
    }
        
    /**
     *
     * @param scores
     */
    public void setTotalScores(int[] scores) {
        this.totalScores = scores[scores.length-1];;
    }
    
    /**
     *
     * @return the total scores
     */
    public int getTotalScores() {
        return this.totalScores;
    }
    
    /**
     *
     * @return boolean
     */
    private boolean isStrike(int[] frame) {
        return (frame[0] == 10 && frame[1] == 0) ? true : false;
    }
    
    private boolean isSpare(int[] frame) {
        return (!this.isStrike(frame) && IntStream.of(frame).sum() == 10) ? true : false;
    }
    
    private int missScores(int currentFrame) {
        return IntStream.of(this.pointsFrames[currentFrame]).sum();
    }
    
    private int strikeScores(int currentFrame) {
        if(currentFrame < this.pointsFrames.length-1) {
            if(this.isStrike(this.pointsFrames[currentFrame+1]) && currentFrame < this.pointsFrames.length-2) {
                return 10 + IntStream.of(this.pointsFrames[currentFrame+1]).sum() + this.pointsFrames[currentFrame+2][0];
            }
            return 10 + IntStream.of(this.pointsFrames[currentFrame+1]).sum();
        }
        return 10;
    }
    
    private int spareScores(int currentFrame) {
        if(currentFrame < this.pointsFrames.length) {
            return 10 + this.pointsFrames[currentFrame+1][0];
        }
        return 10;
    }
}
