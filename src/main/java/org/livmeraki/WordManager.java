package org.livmeraki;

import java.util.ArrayList;
import java.util.Scanner;

public class WordManager {
    public static void main(){

        int menuInput, index;
        WordCRUD WCRUD = new WordCRUD();
        Scanner s = new Scanner(System.in);

        while(true){
            System.out.print("*** 영단어 마스터 ***\n" +
                    "1. 모든 단어 보기\n" +
                    "2. 수준별 단어 보기\n" +
                    "3. 단어 검색\n" +
                    "4. 단어 추가\n" +
                    "5. 단어 수정\n" +
                    "6. 단어 삭제\n" +
                    "7. 파일 저장\n" +
                    "0. 나가기\n" +
                    "*************************\n" +
                    "=> 원하는 메뉴는? ");
            menuInput = s.nextInt();

            if(menuInput==0) break;
            else if (menuInput == 1){
                WCRUD.read();
            }
            else if (menuInput == 2){
                System.out.println("This menu is not yet ready to be implemented. Sorry for your troubles.");
            }
            else if (menuInput == 3){
                System.out.println("This menu is not yet ready to be implemented. Sorry for your troubles.");
            }
            else if (menuInput == 4){
                WCRUD.add();
            }
            else if (menuInput == 5){
                System.out.println("This menu is not yet ready to be implemented. Sorry for your troubles.");
            }
            else if (menuInput == 6){
                System.out.println("This menu is not yet ready to be implemented. Sorry for your troubles.");
            }
            else if (menuInput == 7){
                System.out.println("This menu is not yet ready to be implemented. Sorry for your troubles.");
            }
        }


    }


}
