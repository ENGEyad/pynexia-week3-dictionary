package com.pynexia.week3.dictionary;

import java.util.ArrayList;
import java.util.List;
public final class DictionaryEntry {
 public String word;
 public List<Phonetic> phonetics=new ArrayList<>();
 public List<Meaning> meanings=new ArrayList<>();
 public String firstAudio() { for (Phonetic p:phonetics) if(p.audio!=null && p.audio.startsWith("https://")) return p.audio; return null; }
 public String phoneticText() { for (Phonetic p:phonetics) if(p.text!=null && !p.text.isBlank()) return p.text; return ""; }
 public static final class Phonetic { public String text; public String audio; }
 public static final class Meaning { public String partOfSpeech; public List<Definition> definitions=new ArrayList<>(); public List<String> synonyms=new ArrayList<>(); }
 public static final class Definition { public String definition; public String example; public List<String> synonyms=new ArrayList<>(); }
}
