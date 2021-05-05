/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author A
 */
public class Player {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Board b = new Board("test", 6, 8);
        b.display(); 
        b.addHorizontalWall(0, 0, b.getWidth());
        b.addHorizontalWall(b.getHeight()-1, 0, b.getWidth());
        b.addVerticalWall(1, 0, b.getHeight()-1);
        b.addVerticalWall(1, b.getWidth()-1, b.getHeight()-1);
        b.display(); 
        b.addBox(2,1);
        b.addBox(2,2);
        b.addTarget(3,4);
        b.addCharacter(1,1);
        b.display(); 
    }
}
