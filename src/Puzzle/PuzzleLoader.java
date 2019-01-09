package Puzzle;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class PuzzleLoader {
    Puzzle loadedPuzzel;
    Scanner fileIn;
    String path;
    ArrayList<Character> listMoves= new ArrayList<>();
    public PuzzleLoader(String path){
        this.path = path;

    }
    public void loadPuzzle() throws FileNotFoundException {
        fileIn = new Scanner(new File(path));
        int sizeI;
        int sizeJ;
        sizeI = fileIn.nextInt();
        sizeJ = fileIn.nextInt();
        ArrayList<Integer> fields = new ArrayList<>();
        for(int i=0;i<sizeI*sizeJ;i++) {
            fields.add(Integer.parseInt(fileIn.next()));
        }
        loadedPuzzel = new Puzzle(sizeI,sizeJ,fields);
    }

    public Puzzle getLoadedPuzzel() {
        return loadedPuzzel;
    }
    public void generateSolutionFile(String path,Puzzle p){
        File f = new File("C:\\Users\\Jerry\\Documents\\GitHub\\SISE_2\\Data\\"+path);
        if(f.exists() && !f.isDirectory()) {
            f.delete();
        }
        String solution = new String();

        while(p.parent!=null) {
            listMoves.add(p.lastmove);
            p = p.parent;
        }
        Collections.reverse(listMoves);
        for(Character c:listMoves){
            solution = solution+c;
        }
        File file = new File("C:\\Users\\Jerry\\Documents\\GitHub\\SISE_2\\Data\\"+path);
        try {
            PrintWriter zapis = new PrintWriter("C:\\Users\\Jerry\\Documents\\GitHub\\SISE_2\\Data\\"+path);
            zapis.println(listMoves.size());
            zapis.println(solution);
            zapis.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void generateStatsFile(String path,int odw, int prze,int depth,float time){
        File f = new File("C:\\Users\\Jerry\\Documents\\GitHub\\SISE_2\\Data\\"+path);
        if(f.exists() && !f.isDirectory()) {
            f.delete();
        }
        File file = new File("C:\\Users\\Jerry\\Documents\\GitHub\\SISE_2\\Data\\"+path);
        try {
            PrintWriter zapis = new PrintWriter("C:\\Users\\Jerry\\Documents\\GitHub\\SISE_2\\Data\\"+path);
            zapis.println(listMoves.size());
            zapis.println(odw);
            zapis.println(prze);
            zapis.println(depth);
            zapis.println(time);
            zapis.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
