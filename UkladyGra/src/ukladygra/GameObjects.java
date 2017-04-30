/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ukladygra;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.util.*;

/**
 * Klasa wczytująca elementy gry, obrazki z częsciami układów
 * @author Mikołaj
 */
public class GameObjects {
/**
 * czytanie obrazków z folderu
 * @param file 
 */
    private void addPicture(String file) {
        File imageFile = new File(file);

        try {

            gObjects.add(ImageIO.read(imageFile));

        } catch (IOException e) {
            System.err.println("Blad odczytu obrazka");
            e.printStackTrace();
        }
    }
/**
 * Stworzenie listy Obrazków
 */
    ArrayList<Image> gObjects = new ArrayList<>();
/**
 * stworzenie listy lokacji dla obiektów (obrazkow)
 */
    ArrayList<Point> locations = new ArrayList<>();
/**
 * stworzenie listy "celów" dla poruszających się obiektów
 */
    ArrayList<Point> destinations = new ArrayList<>();

    ArrayList<Boolean> doDraw;
    /**
     * dzdefiniowanie listy przechwującej wartości flag
     */
    ArrayList<Integer> doScore;
    /**
    Pole falgi
    */
    Integer flag;
/**
 * odwołenie do wyboru w menu
 */
    Menu picker;
    /**
     * konstruktor klasy GameObjects
     * @param picker 
     */
    GameObjects(Menu picker) {
        
        /**
         * ustawienie odwołania do wyboru z menu
         */
        this.picker = picker;
        
        Init();
    }
   
    /**
     * funkcja restartująca, czysci wszystkie listy a następnie znów je zapełnia funkcją Init, która nadaje nowe lokacje
     */
    public void Restart(){
        
        gObjects.clear();
        locations.clear();
        destinations.clear();
        doDraw.clear();
        doScore.clear();
        Init();
        
    }
    /**
     * dodanie obrazków do listy a także lokacji i destynacji elementów
     */
    private final void Init() {
        flag = picker.picker.getSelectedIndex();
        addPicture("big-intestines.png");
        addPicture("neuron.png");
        addPicture("hip-bone.png");
        addPicture("human-liver.png");
        addPicture("medical.png");
        addPicture("pancreas.png");
        addPicture("small-intestine.png");
        addPicture("throat.png");
        addPicture("teeth-line.png");
        addPicture("human-eye.png");
        addPicture("brain.png");
        addPicture("human-hand-bones.png");
        addPicture("spine.png");
        addPicture("skull.png");
        addPicture("knee.png"); 
        addPicture("ear.png");
        addPicture("big-nose.png");
        addPicture("tap.png");
        Random rd = new Random();
        doDraw = new ArrayList<>(gObjects.size());
        doScore = new ArrayList<>(gObjects.size());
/**
 * dla każdego elementu lisy dodaj do list lokalizację, destynację oraz ustaw flagę,
 * wststkie listy mają tę samą długosć i są sprawdzane później rownolegle
 */
        for (int i = 0; i < gObjects.size(); i++) {
            locations.add(new Point(rd.nextInt(1000), rd.nextInt(700)));
            destinations.add(new Point(rd.nextInt(1000), rd.nextInt(700)));
            doDraw.add(true);
            
            doScore.add(flag);
         
            
        }
        
        /**
         * ustawienie niektórych flag na inną wartość
         */
      doScore.set(1, flag+1);
      doScore.set(9, flag+1);
      doScore.set(10, flag+1);
      doScore.set(15, flag+1);
      doScore.set(16, flag+1);
      doScore.set(17, flag+1); 
      doScore.set(2, flag+2);
      doScore.set(11, flag+2);
      doScore.set(12, flag+2);
      doScore.set(13, flag+2);
      doScore.set(14, flag+2);
      doScore.set(8, flag+2);
      
      
        }
    /**
     * funkcja usuwająca elementy z list
     * @param index 
     */
    void removeElement(int index){
        
        this.destinations.remove(index);
        this.doDraw.remove(index);
        this.doScore.remove(index);
        this.gObjects.remove(index);
        this.locations.remove(index);
        
        
        
    }

}
