package Solver;

import Puzzle.Puzzle;

import java.util.ArrayList;

public class Solver {
    Puzzle currentPuzzle;
    int poziomDrzewa = 0;
    public int odwiedzone = 0;
    public int przetworzone = 0;
    public int depth=0;
    ArrayList<Puzzle> openList = new ArrayList<>();
    ArrayList<Puzzle> closedList = new ArrayList<>();
    ArrayList<Puzzle> kolejkaDoSzukania = new ArrayList<>();
    public Puzzle resoltOfDFS;
    public Puzzle resoltOfBFS;
    public Puzzle resoltOfAstar;
    public Solver(Puzzle puzzle){
        currentPuzzle = puzzle;
        kolejkaDoSzukania.add(currentPuzzle);
    }
    public void bfs (String generateOrder){
        ArrayList<Puzzle> copy = new ArrayList<>();
        for(Puzzle p : kolejkaDoSzukania){
            p.isSolved();
            odwiedzone++;
            if(p.isSolved()){
                //System.out.println(poziomDrzewa);
                //currentPuzzle.show();
                resoltOfBFS = p;
                return;
            }
            //System.out.println("No solutions on this level");
        }

        //odwiedzoneStany.addAll(kolejkaDoSzukania);
        copy.addAll(kolejkaDoSzukania);
        kolejkaDoSzukania.clear();
        //System.out.println("Loading next level");
        //System.out.println(poziomDrzewa);
        poziomDrzewa +=1;
        for(Puzzle p : copy){
            p.load(generateOrder);
            przetworzone+=p.children.size();
            kolejkaDoSzukania.addAll(p.children);
        }
        //System.out.println("Run bfs");
        depth++;
        bfs(generateOrder);
    }
    public boolean dfs(Puzzle puzzle,String generateOrder,int maxDepth){
        boolean tmp;
        if(puzzle.isSolved()){
            resoltOfDFS = puzzle;
            return true;
        }else  if(puzzle.depth == maxDepth){
            return false;
        }
        puzzle.load(generateOrder);
        przetworzone+=puzzle.children.size();
        for(Puzzle p : puzzle.children){
            depth++;
            tmp = dfs(p,generateOrder,maxDepth);
            odwiedzone++;
            if(tmp){
                //resoultOfDFS = p;
                return true;
            }
        }
        return false;
    }
    @SuppressWarnings("Duplicates")
    public void AStar(Puzzle puzzle, String heuristic){
        openList.add(puzzle);
        boolean wasitthere = false;
        Puzzle current;
        switch (heuristic){
            case "manh":{
                while (!openList.isEmpty()){
                    depth++;
                    current = openList.get(0);
                    int minf = current.calcGCost()+current.calcCostManhatan();
                    for(Puzzle p:openList){
                        if(p.calcGCost()+p.calcCostManhatan()<minf){
                            current = p;
                        }
                    }
                    if(current.isSolved()){
                        resoltOfAstar = current;
                    }
                    openList.remove(current);
                    closedList.add(current);
                    current.load("ULDR");
                    for(Puzzle p:current.children){
                        for(Puzzle closed:closedList) {
                            if (closed.isSame(p)) {
                                wasitthere = true;
                            }
                        }
                        if(wasitthere){
                            continue;
                        }
                        for(Puzzle opened:openList) {
                            if (opened.isSame(p)) {
                                wasitthere = true;
                            }
                        }
                        if(!wasitthere){
                            openList.add(p);
                        }else if(current.calcGCost()+1>=p.calcGCost()){
                            continue;
                        }
                    }
                }
                odwiedzone = closedList.size();
                przetworzone = closedList.size()+openList.size();

                break;
            }
            case "hamm":{
                while (!openList.isEmpty()){
                    depth++;
                    current = openList.get(0);
                    int minf = current.calcGCost()+current.calcCostHaming();
                    for(Puzzle p:openList){
                        if(p.calcGCost()+p.calcCostHaming()<minf){
                            current = p;
                        }
                    }
                    if(current.isSolved()){
                        resoltOfAstar = current;
                    }
                    openList.remove(current);
                    closedList.add(current);
                    current.load("ULDR");
                    for(Puzzle p:current.children){
                        for(Puzzle closed:closedList) {
                            if (closed.isSame(p)) {
                                wasitthere = true;
                            }
                        }
                        if(wasitthere){
                            continue;
                        }
                        for(Puzzle opened:openList) {
                            if (opened.isSame(p)) {
                                wasitthere = true;
                            }
                        }
                        if(!wasitthere){
                            openList.add(p);
                        }else if(current.calcGCost()+1>=p.calcGCost()){
                            continue;
                        }
                    }
                }
                odwiedzone = closedList.size();
                przetworzone = closedList.size()+openList.size();

                break;
            }
        }






    }
}
