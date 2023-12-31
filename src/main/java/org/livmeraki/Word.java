package org.livmeraki;

public class Word {
    private String vocab;
    private int level;
    private String meaning;

    Word(){

    }
    public Word(String vocab, int level, String meaning) {
        this.vocab = vocab;
        this.level = level;
        this.meaning = meaning;
    }

    public String getVocab() {
        return vocab;
    }

    public void setVocab(String vocab) {
        this.vocab = vocab;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public String toString() {
        String levelStar = "*";
        for (int i=1;i<level;i++){
            levelStar += "*";
        }
        String str = String.format("%-3s", levelStar)
                + String.format("%15s", vocab) + "  "
                +meaning;
        return str;
    }
}
