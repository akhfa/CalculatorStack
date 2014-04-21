/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Calc;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PDA {
    private Vector<inputPDA> state;
    private int nState;
    private Vector<Integer> finalState;
    private Vector<String> hasil;
    
    public PDA(){
        nState = 0;
        state = new Vector<inputPDA>();
        finalState = new Vector<Integer>();
        hasil = new Vector<String>();
    }
    
    public int getNState(){
        return nState;
    }
    
    public void setState(int t1, String s1, String s2, int t2, String s3){
        inputPDA p = new inputPDA();
        p.t1 = t1;
        p.t2 = t2;
        p.s1 = s1;
        p.s2 = s2;
        p.s3 = s3;
        state.add(p);
    }
    
    public Vector<Integer> getFinalState(){
        return finalState;
    }
    
    public void scanPDA(){
        Scanner input = new Scanner(System.in);
        int n, temp;
        n = input.nextInt();
        for(int i = 0; i < n; i++){
            System.out.println("masuk " + i);
            this.addState();
        }
        n = input.nextInt();
        for(int i = 0; i < n; i++){
            temp = input.nextInt();
            finalState.add(temp);
        }
    }
    
    public void addState(){
        int t1 = 0, t2 = 0;
        String s1 = new String();
        String s2 = new String(); 
        String s3 = new String();
        Scanner input = new Scanner(System.in);
        t1 = input.nextInt();
        s1 = input.next();
        s2 = input.next();
        t2 = input.nextInt();
        s3 = input.next();
        this.setState(t1,s1,s2,t2,s3);
    }
    
    public void addFinalState(int State)
    {
        finalState.add(State);
    }
    
    public void cekState(){
        for(int i = 0; i < state.size(); i++){
            if(state.get(i).t1 > nState){
                nState = state.get(i).t1;
            }
            if(state.get(i).t2 > nState){
                nState = state.get(i).t2;
            }
        }
    }
    
    public void toCFG(){
        this.cekState();
        //System.out.println("CFG dari PDA anda adalah :");
        if(finalState.isEmpty()){
            for(int i = 0; i <= nState; i++){
                //System.out.println("S -> [q0 Z q"+i+"]");
                hasil.add("S -> [q0 Z q"+i+"]");
            }
        }
        while(!finalState.isEmpty()){
            //System.out.println("S -> [q0 Z q"+finalState.get(0)+"]");
            hasil.add("S -> [q0 Z q"+finalState.get(0)+"]");
            finalState.remove(0);
        }
        while(!state.isEmpty()){
            if(state.get(0).s3.compareTo("null") == 0 && !(state.get(0).s2.compareTo("null") == 0)) {
                //System.out.print("[q"+state.get(0).t1+" "+state.get(0).s2+" "+"q"+state.get(0).t2+"] -> ");
                if( state.get(0).s1.compareTo("null") == 0){
                    //System.out.println("e");
                    hasil.add("[q"+state.get(0).t1+" "+state.get(0).s2+" "+"q"+state.get(0).t2+"] -> e");
                }
                else{
                    //System.out.println(state.get(0).s1);
                    hasil.add("[q"+state.get(0).t1+" "+state.get(0).s2+" "+"q"+state.get(0).t2+"] -> " + state.get(0).s1);
                }
            }
            else{
                if(state.get(0).s3.length() == 2){
                    for(int i = 0; i <= nState; i++){
                        for(int j = 0; j <= nState; j++){
                            /*
                            System.out.println("[q" + state.get(0).t1 + " " + state.get(0).s2 +
                            " q" + i + "] -> " + state.get(0).s1 + 
                            "[q" + state.get(0).t2 + " " + state.get(0).s3.substring(0, 1) +
                            " q" + j + "]" + 
                            "[q" + j + " " + state.get(0).s3.substring(1,2) +
                            " q" + i + "]");*/
                            hasil.add("[q" + state.get(0).t1 + " " + state.get(0).s2 +
                            " q" + i + "] -> " + state.get(0).s1 + 
                            "[q" + state.get(0).t2 + " " + state.get(0).s3.substring(0, 1) +
                            " q" + j + "]" + 
                            "[q" + j + " " + state.get(0).s3.substring(1,2) +
                            " q" + i + "]");
                        }
                    }
                }
                else{
                    //(q,0,Z) = (p,Y) maka pd CFG: [qZr] -> 0 [pYr]
                    for(int i = 0; i <= nState; i++){
                        if(state.get(0).s1.compareTo("null") == 0){
                            if(state.get(0).s2.compareTo("null") == 0){
                                /*
                                System.out.println("[q"+state.get(0).t1 + " e q" + i + "] -> " + 
                                "[q" + state.get(0).t2 + " " + state.get(0).s3 +
                                " q" + i + "]");*/
                                hasil.add("[q"+state.get(0).t1 + " e q" + i + "] -> " + 
                                "[q" + state.get(0).t2 + " " + state.get(0).s3 +
                                " q" + i + "]");
                            }
                            else{
                                /*
                                System.out.println("[q"+state.get(0).t1 + " " + state.get(0).s2 +
                                " q" + i + "] -> " + "[q" + state.get(0).t2 + " " + state.get(0).s3 +
                                " q" + i + "]");*/
                                hasil.add("[q"+state.get(0).t1 + " " + state.get(0).s2 +
                                " q" + i + "] -> " + "[q" + state.get(0).t2 + " " + state.get(0).s3 +
                                " q" + i + "]");
                            }
                        }
                        else{
                            if(state.get(0).s2.compareTo("null") == 0){
                                if(state.get(0).s3.compareTo("null") == 0){
                                    /*
                                    System.out.println("[q"+state.get(0).t1 + " e q" + i + "] -> " + state.get(0).s1 +
                                    "[q" + state.get(0).t2 + " e q" + i + "]");*/
                                    hasil.add("[q"+state.get(0).t1 + " e q" + i + "] -> " + state.get(0).s1 +
                                    "[q" + state.get(0).t2 + " e q" + i + "]");
                                }
                                else{
                                    /*
                                    System.out.println("[q"+state.get(0).t1 + " e q" + i + "] -> " + state.get(0).s1 +
                                    "[q" + state.get(0).t2 + " " + state.get(0).s3 +
                                    " q" + i + "]");*/
                                    hasil.add("[q"+state.get(0).t1 + " e q" + i + "] -> " + state.get(0).s1 +
                                    "[q" + state.get(0).t2 + " " + state.get(0).s3 +
                                    " q" + i + "]");
                                }
                            }
                            else{
                                if(state.get(0).s3.compareTo("null") == 0){
                                    /*
                                    System.out.println("[q"+state.get(0).t1 + " " + state.get(0).s2 +
                                    " q" + i + "] -> " + state.get(0).s1 +
                                    "[q" + state.get(0).t2 + " e q" + i + "]");*/
                                    hasil.add("[q"+state.get(0).t1 + " " + state.get(0).s2 +
                                    " q" + i + "] -> " + state.get(0).s1 +
                                    "[q" + state.get(0).t2 + " e q" + i + "]");
                                }
                                else{
                                    /*
                                    System.out.println("[q"+state.get(0).t1 + " " + state.get(0).s2 +
                                    " q" + i + "] -> " + state.get(0).s1 +
                                    "[q" + state.get(0).t2 + " " + state.get(0).s3 +
                                    " q" + i + "]");*/
                                    hasil.add("[q"+state.get(0).t1 + " " + state.get(0).s2 +
                                    " q" + i + "] -> " + state.get(0).s1 +
                                    "[q" + state.get(0).t2 + " " + state.get(0).s3 +
                                    " q" + i + "]");
                                }
                            }
                        }
                    }
                }
                
            }
            state.remove(0);
        }
    }
    
    public void PrintCFG()
    {
        for(String i:hasil)
        {
            System.out.println(i);
        }
    }
    
    public void print()
    {
        for(inputPDA InPDA : state)
        {
            System.out.println(InPDA.t1 + "\t" + InPDA.s1 + "\t" + InPDA.s2 + "\t" + InPDA.t2 + "\t" + InPDA.s3);
        }
    }
    
    public void WriteCFGtoFile()
    {
        PrintWriter PW = null;
        try {
            PW = new PrintWriter("CFG.out");
            for(String i : hasil)
            {
                PW.println(i);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PDA.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            PW.close();
        }
    }
        }
