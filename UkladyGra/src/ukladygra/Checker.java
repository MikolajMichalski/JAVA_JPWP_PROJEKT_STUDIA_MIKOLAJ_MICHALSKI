/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ukladygra;

import java.awt.*;
import java.util.*;
import javax.swing.*;

/**
 * Klasa sprawdzająca czy nastąpiło kliknięcie na element grywalny
 * @author Mikołaj
 */
public class Checker {
/**
 * pola dla odwołań do ObjectMover, GameArea, GameObjects
 */
    ObjectMover objM;
    GameArea gA;
    GameObjects objects;
    /**
     * zmienna dla liczenia punktów
     */
    public int score = 0;
    /**
     * zmienna do odliczania pozostałych kliknięć
     */
    public int clickCount = 6;
/**
 * konstruktor klasy Checker, ustaiwa odwołania do innych klas
 * @param objects
 * @param gA 
 */
    public Checker(GameObjects objects, GameArea gA) {
        this.objects = objects;
        this.gA = gA;

    }
/**
 * funkcja porównująca aktualną lokację elementu grywalnego z lokacją kliknięcia myszki
 * @param x
 * @param y 
 */
    public void CompareLoc(int x, int y) {
        /**
         * pobiera wielkości obrazkówz listy gObjects
         */
        for (int i = 0; i < objects.gObjects.size(); i++) {
            Point location = objects.locations.get(i);
            Point size = new Point();
            size.x = objects.gObjects.get(i).getWidth(null);
            size.y = objects.gObjects.get(i).getHeight(null);
            
            /**
             * jesli lokacje się pokrywają to wykonuj okreslone czynnosci
             */
            if (location.x <= x && location.y <= y && location.x + size.x >= x && location.y + size.y >= y) {
                /**
                 * jesli flaga obiektu w danym trybie się zgadza wowczas zwiększ liczbę punktów
                 */
                if(objects.doScore.get(i)==gA.modeNumber)
                {
                    score++;
                     
                    
                }
                else //w przeciwnym razie odejmij punkt
                {
                    score--;
                }
               
                clickCount--; //zmiejsz liczbę kliknięć
                
                 
               /**
                * w Panelu GameArea w etykiecie pointsLabel uaktualniaj liczbę punktów
                */
               
                gA.pointsLabel.setText("Score: "+Integer.toString(score));
                /**
                 * uaktualniaj liczbę pozostałych kliknięć w eykiecie
                 */
                gA.gameOver.setText("Pozostało kliknięć: "+Integer.toString(clickCount));
                
            
                 /**
                  * usuń element z listy obiektów
                  */
                objects.removeElement(i);
               
            
              
                /**
                 * przerysuj panel GameArea
                 */
              
                gA.repaint();
            
               
            
            }

        }
        

    }

}
