
/**
 * Write a description of WordPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordPlay {

    public boolean isVowel(char ch) {
        ch = Character.toLowerCase(ch);
        if(ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
            return true;
        } else {
            return false;
        }
    }
    
    public String replaceVowels(String phrase, char ch) {
        ch = Character.toLowerCase(ch);
        for(int i = 0; i < phrase.length(); i++) {
            if(isVowel(phrase.charAt(i))) {
                phrase = phrase.replace(phrase.charAt(i), '*');
            } 
        }
        return phrase;
    }
    
    public String emphasize(String phrase, char ch) {
        char chL = Character.toLowerCase(ch);
        char chU = Character.toUpperCase(ch);
        String emp = "";
        for(int i = 0; i < phrase.length(); i++) {
            if((phrase.charAt(i) == chL) || (phrase.charAt(i) == chU)) {
                if((i + 1) % 2 == 0) {
                    emp += '+';
                } else {
                    emp += '*';
                }
            } else {
                emp += phrase.charAt(i);
            }
        }
        return emp;
    }
    
    public void tester() {
        
        System.out.println(isVowel('k'));
        System.out.println(isVowel('a'));
        System.out.println(isVowel('e'));
        System.out.println(isVowel('i'));
        System.out.println(isVowel('o'));
        System.out.println(isVowel('u'));
        System.out.println(isVowel('d'));
        System.out.println(isVowel('j'));
        
        String s = replaceVowels("Hello World", '*');
        System.out.println(s);
        
        s = emphasize("dna ctgaaactga", 'a');
        System.out.println(s);
        
        s = emphasize("Mary Bella Abracadabra", 'a');
        System.out.println(s);
        
    }
    
}
