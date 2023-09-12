package org.livmeraki;

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
                vocab = userInput.substring(i);
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

        System.out.print("=> 수정할 번호 선택 : ");
        int userNum = s.nextInt();
        s.nextLine();

        System.out.print("=> 뜻 입력 : ");
        wordList.get(userNum-1).setMeaning(s.nextLine());
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

    @Override
    public void delete() {

    }

    public void saveFile(){
        try {
            FileWriter fwr = new FileWriter("wordlist.txt");
            fwr.write(wordList.get(0).toString());
            fwr.close();
        } catch (IOException e){
            System.out.println("An error occurred.");
        }

    }


}
