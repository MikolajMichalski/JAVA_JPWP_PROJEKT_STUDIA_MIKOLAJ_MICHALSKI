/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ukladygra;

import java.awt.Point;
import java.util.Random;


/**
 * Klasa odpowiadająca za ruch obiektów
 * @author Mikołaj
 */
public class ObjectMover {
    /**
     * pole klasy GameObjects, odwołanie do tej klasy
     */
    GameObjects objects;
    /**
     * konstruktor klasy ObjectMover ustawia odwołanie do GameArea i GameObjects
     * @param obj
     * @param gameArea 
     */
    ObjectMover(GameObjects obj,GameArea gameArea)
    {
        this.gameArea = gameArea;
        this.objects = obj;
    }
    /**
     * generownie liczby losowej
     */
    Random rand = new Random();
    
    GameArea gameArea;
    
    /**
     * funkcja opowiadjąca za ruch obiektów
     */
    public void run(){
        
        for(int i=0; i<objects.gObjects.size();i++)  // dla kazdego obiektu z listy, przypisz lokacje i destynacje do zmiennyc typu Point
        {
            Point location = objects.locations.get(i);
            Point dest = objects.destinations.get(i);
            
            if(Point.distance(location.x, location.y, dest.x, dest.y)<2) //jeś,i dystans pomiędzy lokacją i destynacją jest mniejszy od 2, to wyznacz nowe destynacje
            {
                dest.x = rand.nextInt(1000);
                dest.y = rand.nextInt(700);
            }
            else // jeśli nie to poruszaj się w kierunku destynacji
            {
                
                int x = (int) Math.signum((dest.x - location.x)); //wyznnaczanie kierunku destynacji x
                int y = (int) Math.signum((dest.y - location.y)); // wyznaczenie kierunku destynacji y
                
                location.x = location.x + x;
                location.y = location.y + y;
            }
            
                         
            gameArea.repaint(); //przerysowaywaine Panelu GameArea
            gameArea.validate();

          
            }
        
        
        
    
        
    }

     
}
