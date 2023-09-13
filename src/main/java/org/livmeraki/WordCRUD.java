package org.livmeraki;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class WordCRUD implements ICRUD{
    Scanner s = new Scanner(System.in);
    ArrayList<Word> wordList = new ArrayList<>();
    private int index;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }


    WordCRUD(){
        index = -1;
    }

    @Override
    public void add() {
        int level = 0;
        String vocab = "";
        String meaning = "";

        index += 1;

        while(true) {
            System.out.print("=> 난이도(1,2,3) & 새 단어 입력 : ");

            String userInput = s.nextLine();

            int i = userInput.indexOf(' ');

            try {
                level = Integer.parseInt(userInput.substring(0, i));
                vocab = userInput.substring(i+1);
                if(level>3 || level<1){
                    System.out.println("1~3 사이의 숫자를 입력해주세요.");
                    continue;
                }
                break;
            }
            catch(Exception e){
                System.out.println("잘못 입력하셨습니다. 정해진 형식에 맞게 입력해주세요.");
            }

        }
        System.out.print("뜻 입력 : ");
        meaning = s.nextLine();

        wordList.add(new Word(vocab, level, meaning));
        System.out.println("새 단어가 단어장에 추가되었습니다 !!!");

    }
    @Override
    public void read() {
        printAll("");
    }

    public void printAll(String key){
        ArrayList<Integer> selected = search(key);
        System.out.println("--------------------------------");
        if(selected.toArray().length==0)
            System.out.println("단어가 없습니다.");
        for(int i=0;i<selected.size();i++) {
            System.out.print(i + 1 + " ");
            System.out.println(wordList.get(selected.get(i)).toString());
        }
        System.out.println("--------------------------------");
    }

    @Override
    public void update() {
        System.out.print("=> 수정할 단어 검색 : ");
        String keyword = s.nextLine();
        printAll(keyword);
        ArrayList<Integer> selectedNum = search(keyword);
        int userNum;
        if(selectedNum.toArray().length==0)
        {

            return;
        }

        while(true){
            System.out.print("=> 수정할 번호 선택 : ");
            userNum = s.nextInt();
            s.nextLine();
            if(userNum<selectedNum.toArray().length && userNum>=0){
                break;
            }
            System.out.println("위의 인덱스 범위 내의 수를 입력하세요.");
        }



        System.out.print("=> 뜻 입력 : ");
        wordList.get(selectedNum.get(userNum-1)).setMeaning(s.nextLine());
        System.out.println("단어 수정이 성공적으로 되었습니다!!");
        return;
    }

    public ArrayList<Integer> search(String key) {

        ArrayList<Integer> selected = new ArrayList<Integer>();
        for(int i=0;i<wordList.size();i++){
            if(wordList.get(i).getVocab().contains(key)){
                selected.add(i);
            }
        }
        return selected;
    }

    public void printAllLevel() {

        System.out.print("=> 레벨(1:초급, 2:중급, 3:고급) 선택 : ");
        int lev = s.nextInt();
        int i;

        System.out.println("--------------------------------");
        for(i=0;i<wordList.size();i++){
            if(wordList.get(i).getLevel()==lev) {
                System.out.print(i + 1 + " ");
                System.out.println(wordList.get(i).toString());
            }
        }
        if(i==0)
            System.out.println("단어가 없습니다.");
        System.out.println("--------------------------------");

        return;
    }

    @Override
    public void delete() {
        System.out.print("=> 삭제할 단어 검색 : ");
        String keyword = s.nextLine();
        printAll(keyword);
        ArrayList<Integer> selectedNum = search(keyword);

        System.out.print("=> 삭제할 번호 선택 : ");
        int userNum = s.nextInt();
        System.out.print("=> 정말로 삭제하실래요?(Y/n) ");
        s.nextLine();
        if(s.nextLine().equals("Y")){
            wordList.remove((int)selectedNum.get(userNum-1));
            System.out.println("선택한 단어 삭제 완료 !!! ");
        }
    }

    public void saveFile(){
        try {
            FileWriter fwr = new FileWriter("wordlist.txt");
            for(int i=0;i<wordList.size();i++) {
                fwr.write(wordList.get(i).getLevel() + "|");
                fwr.write(wordList.get(i).getVocab() + "|");
                fwr.write(wordList.get(i).getMeaning() + "\n");
            }
            fwr.close();
        } catch (IOException e){
            System.out.println("An error occurred.");
        } finally {
            System.out.println("모든 단어 파일 저장 완료!!!");
        }

    }

    public int readFile(){
        int countWord=0;
        try {
            File f = new File("wordlist.txt");
            Scanner s = new Scanner(f);
            while(s.hasNextLine()){
                String tmp = s.nextLine();
                wordList.add((new Word(tmp.substring(tmp.indexOf('|')+1, tmp.lastIndexOf('|')), Integer.parseInt(tmp.substring(0, tmp.indexOf('|'))), tmp.substring(tmp.lastIndexOf('|')+1))));
                countWord++;
            }

        } catch (FileNotFoundException e){
            System.out.println("An error has occurred.");
        }
        return countWord;

    }

}
