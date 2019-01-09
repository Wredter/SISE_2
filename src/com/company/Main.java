package com.company;

import Puzzle.Puzzle;
import Puzzle.PuzzleLoader;
import Solver.Solver;

import java.io.FileNotFoundException;

public class Main {

    @SuppressWarnings("Duplicates")
    public static void main(String[] args) {
//        for(int i=0;i<args.length;i++){
//            System.out.println(args[i]);
//        }
        String Path = "C:\\Users\\Jerry\\Documents\\GitHub\\SISE_2\\Data\\";
        switch (args[0]){
            case "bfs":{
                PuzzleLoader pl = new PuzzleLoader(Path+args[2]);
                //ArrayList<Character> listMoves = new ArrayList<>();
                //Solver solver = new Solver(p);
                try {
                    pl.loadPuzzle();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                Puzzle p = pl.getLoadedPuzzel();
                Solver s = new Solver(p);
                long start = System.nanoTime();
                s.bfs(args[1]);
                long end = System.nanoTime();
                float eclipseTime = (end - start)/1000000f;
                Puzzle w = s.resoltOfBFS;
                pl.generateSolutionFile(args[3],w);
                pl.generateStatsFile(args[4],s.odwiedzone,s.przetworzone,s.depth,eclipseTime);
                break;
            }
            case "dfs":{
                PuzzleLoader pl = new PuzzleLoader(Path+args[2]);
                //ArrayList<Character> listMoves = new ArrayList<>();
                //Solver solver = new Solver(p);
                try {
                    pl.loadPuzzle();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                Puzzle p = pl.getLoadedPuzzel();
                Solver s = new Solver(p);
                long start = System.nanoTime();
                s.dfs(p,args[1],21);
                long end = System.nanoTime();
                float eclipseTime = (end - start)/1000000f;
                Puzzle w = s.resoltOfDFS;
                pl.generateSolutionFile(args[3],w);
                pl.generateStatsFile(args[4],s.odwiedzone,s.przetworzone,s.depth,eclipseTime);
                break;
            }
            case "astr":{
                switch (args[1]){
                    case"manh":{
                        PuzzleLoader pl = new PuzzleLoader(Path+args[2]);
                        //ArrayList<Character> listMoves = new ArrayList<>();
                        //Solver solver = new Solver(p);
                        try {
                            pl.loadPuzzle();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        Puzzle p = pl.getLoadedPuzzel();
                        Solver s = new Solver(p);
                        long start = System.nanoTime();
                        s.AStar(p,args[1]);
                        long end = System.nanoTime();
                        float eclipseTime = (end - start)/1000000f;
                        Puzzle w = s.resoltOfAstar;
                        pl.generateSolutionFile(args[3],w);
                        pl.generateStatsFile(args[4],s.odwiedzone,s.przetworzone,s.depth,eclipseTime);
                        break;
                    }
                    case"hamm":{
                        PuzzleLoader pl = new PuzzleLoader(Path+args[2]);
                        //ArrayList<Character> listMoves = new ArrayList<>();
                        //Solver solver = new Solver(p);
                        try {
                            pl.loadPuzzle();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        Puzzle p = pl.getLoadedPuzzel();
                        Solver s = new Solver(p);
                        long start = System.nanoTime();
                        s.AStar(p,args[1]);
                        long end = System.nanoTime();
                        float eclipseTime = (end - start)/1000000f;
                        Puzzle w = s.resoltOfAstar;
                        pl.generateSolutionFile(args[3],w);
                        pl.generateStatsFile(args[4],s.odwiedzone,s.przetworzone,s.depth,eclipseTime);
                        break;
                    }
                }
            }
        }
    }
}
