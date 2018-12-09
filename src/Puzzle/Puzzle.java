package Puzzle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Puzzle {
    //public int[][] fields;
    public ArrayList<Integer> fields2;
    public int freeSpot [];
    int sizei;
    int sizej;
    public int depth;
    //public int manhCost;
    public int hcost;
    public int gcost;
    public boolean wasVisited = false;
    public char lastmove;
    public Puzzle parent = null;
    //int inversionCounter;
    public ArrayList<Puzzle> children;

    public Puzzle(int sizei, int sizej, ArrayList<Integer> fields){
        this.sizei = sizei;
        this.sizej = sizej;
        depth = 0;
        //fields = new int[size][size];
        freeSpot = new int[2];
        fields2 = fields;
        for (int i = 0; i < sizei; i++) {
            for (int j = 0; j < sizej; j++) {
                if (fields2.get(sizej * i + j) == 0) {
                    freeSpot[0] = i;
                    freeSpot[1] = j;
                }
            }
        }
    }
    public Puzzle(int sizei,int sizej,int numberOfRandomMoves){
        this.sizei = sizei;
        this.sizej = sizej;
        parent =null;
        depth = 0;
        //fields = new int[size][size];
        freeSpot = new int[2];
        fields2 = new ArrayList<>();
        for(int i = 0;i<sizei*sizej;i++){
            fields2.add(i);
        }
        fields2.remove(0);
        fields2.add(0);
        //System.out.println(fields2);

        for (int i = 0; i < sizei; i++) {
            for (int j = 0; j < sizej; j++) {
                if (fields2.get(sizej * i + j) == 0) {
                    freeSpot[0] = i;
                    freeSpot[1] = j;
                }
                //fields[i][j] = kol.get(size * i + j);
            }
        }
        //System.out.println(fields2);
        //show();
        int w;
        for(int i =0;i<numberOfRandomMoves;i++){
            Random rng = new Random();
            w = rng.nextInt(4);
            switch (w){
                case 0:
                    if(moveDown()){
                        break;
                    }
                case 1:
                    if(moveUp()){
                        break;
                    }
                case 2:
                    if(moveLeft()){
                        break;
                    }
                case 3:
                    if(moveRight()) {
                        break;
                    }
            }
        }
        lastmove = 'G';
    }
    public Puzzle(Puzzle puzzle){
        this.sizei = puzzle.sizei;
        this.sizej = puzzle.sizej;
        //fields = new int[size][size];
        parent = puzzle;
        depth = puzzle.depth+1;
        lastmove = puzzle.lastmove;
        freeSpot = new int[2];
        fields2 = new ArrayList<>(puzzle.fields2);
        //kol = new ArrayList<>(puzzle.kol);
        for (int i = 0; i < sizei; i++) {
            for (int j = 0; j < sizej; j++) {
//                if (kol.get(size * i + j) == 0) {
//                    freeSpot[0] = i;
//                    freeSpot[1] = j;
//                }
                if (fields2.get(sizej * i + j) == 0)
                {
                    freeSpot[0] = i;
                    freeSpot[1] = j;
                }
                //fields[i][j] = puzzle.fields[i][j];
            }
        }
    }
    public boolean moveDown(){
        if(freeSpot[0]+1<sizei) {
            //fields[freeSpot[0]][freeSpot[1]] = fields[freeSpot[0] + 1][freeSpot[1]];
            //fields[freeSpot[0] + 1][freeSpot[1]] = 0;
            Collections.swap(fields2,sizej*freeSpot[0]+freeSpot[1],sizej*(freeSpot[0]+1)+freeSpot[1]);
            freeSpot[0]+=1;
            lastmove = 'D';
            //System.out.println("D");
            return true;
        }
        else{
            //System.out.println("illegal move");
            return false;
        }
    }
    public boolean moveUp() {
        if (freeSpot[0] - 1>=0) {
            //fields[freeSpot[0]][freeSpot[1]] = fields[freeSpot[0] - 1][freeSpot[1]];
            //fields[freeSpot[0] - 1][freeSpot[1]] = 0;
            Collections.swap(fields2,sizej*freeSpot[0]+freeSpot[1],sizej*(freeSpot[0]-1)+freeSpot[1]);
            freeSpot[0]-=1;
            lastmove = 'U';
            //System.out.println("U");
            return true;
        }
        else{
            //System.out.println("illegal move");
            return false;
        }
    }
    public boolean moveRight() {
        if (freeSpot[1] + 1<sizej) {
            //fields[freeSpot[0]][freeSpot[1]] = fields[freeSpot[0]][freeSpot[1] + 1];
            //fields[freeSpot[0]][freeSpot[1] + 1] = 0;
            Collections.swap(fields2,sizej*freeSpot[0]+freeSpot[1],sizej*(freeSpot[0])+freeSpot[1]+1);
            freeSpot[1]+=1;
            lastmove = 'R';
            //System.out.println("R");
            return true;
        }
        else{
            //System.out.println("illegal move");
            return false;
        }
    }
    public boolean moveLeft() {
        if (freeSpot[1] - 1>=0) {
            //fields[freeSpot[0]][freeSpot[1]] = fields[freeSpot[0]][freeSpot[1] - 1];
            //fields[freeSpot[0]][freeSpot[1] - 1] = 0;
            Collections.swap(fields2,sizej*freeSpot[0]+freeSpot[1],sizej*(freeSpot[0])+freeSpot[1]-1);
            freeSpot[1]-=1;
            lastmove = 'L';
            //System.out.println("L");
            return true;
        }
        else{
            //System.out.println("illegal move");
            return false;
        }
    }
    public void show(){
        for(int i =0;i<sizei;i++){
            for(int j=0;j<sizej;j++){
                System.out.print(fields2.get(sizej * i + j)+" ");
            }
            System.out.println();
        }
        /*
        System.out.println("isSolvable: "+isSolvable());
        System.out.println("freeSpot["+freeSpot[0]+","+freeSpot[1]+"]");
        System.out.println(kol);
        System.out.println("invercionCounter: "+inversionCounter);
        */
    }
    public boolean isSolved(){
        for(int i = 0;i < sizei;i++){
            for(int j = 0 ;j< sizej;j++){
                if(fields2.get(sizej * i + j)!=((sizej*i) + j)+1&&fields2.get(sizej * i + j)!=0)
                    return false;
            }
        }
        return true;
    }
    public void load(String generateOrder){
        char move;
        children = new ArrayList<>();
        Puzzle temp = new Puzzle(this);
        if(generateOrder.length()!=4){
            return;
        }else {
            for (int i = 0; i < 4; i++) {
                move = generateOrder.charAt(i);

                switch (move){
                    case 'R': {
                        if (temp.moveRight() && lastmove != 'L') {
                            children.add(temp);
                        }
                        temp = new Puzzle(this);
                        break;
                    }
                    case 'L': {
                        if (temp.moveLeft() && lastmove != 'R') {
                            children.add(temp);
                        }
                        temp = new Puzzle(this);
                        break;
                    }
                    case 'U': {
                        if (temp.moveUp() && lastmove != 'D') {
                            children.add(temp);
                        }
                        temp = new Puzzle(this);
                        break;
                    }
                    case 'D': {
                        if (temp.moveDown() && lastmove != 'U') {
                            children.add(temp);
                        }
                        temp = new Puzzle(this);
                        break;
                    }
                }
            }
        }
        return;
    }
    @SuppressWarnings("Duplicates")
    public int calcCostManhatan(){
        int currentcost = 0;
        for(Integer i:fields2){
            int pos = fields2.indexOf(i);
            int finalpos = i-1;
            if(i!=0){
                if(pos > finalpos){
                    while(pos >= finalpos){
                        pos -= sizej;
                        currentcost++;
                    }
                    currentcost--;
                    pos +=sizej;
                    if(pos > finalpos){
                        while (pos>finalpos){
                            pos --;
                            currentcost++;
                        }
                    }else if(pos<finalpos){
                        while (pos < finalpos){
                            pos++;
                            currentcost++;
                        }
                    }
                }else if(pos < finalpos){
                    while (pos <= finalpos) {
                        pos +=sizej;
                        currentcost++;
                    }
                    currentcost--;
                    pos -= sizej;
                    if (pos > finalpos) {
                        while (pos > finalpos) {
                            pos--;
                            currentcost++;
                        }
                    } else if(pos<finalpos){
                        while (pos < finalpos) {
                            pos++;
                            currentcost++;
                        }
                    }
                }
            }
        }
        hcost = currentcost;
        return currentcost;
    }
    public int calcCostHaming(){
        int currentCost =0;
        for(Integer i:fields2){
            if(i!=0) {
                if (i != fields2.indexOf(i) + 1) {
                    currentCost++;
                }
            }
        }
        hcost = currentCost;
        return currentCost;
    }
    public int calcGCost(){
        int gcost = 0;
        Puzzle temp = new Puzzle(this);
        while (temp.parent!=null){
            gcost ++;
            temp = temp.parent;
        }
        this.gcost = gcost;
        return gcost;
    }
    public boolean isSame(Puzzle puzzle){
        for(int i=0;i<sizej*sizei;i++){
            if(fields2.get(i)!=puzzle.fields2.get(i)){
                return false;
            }
        }
        return true;
    }

}
