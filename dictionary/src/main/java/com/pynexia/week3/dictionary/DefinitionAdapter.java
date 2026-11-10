package com.pynexia.week3.dictionary;

import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
public final class DefinitionAdapter extends RecyclerView.Adapter<DefinitionAdapter.Holder> {
 private final List<Row> rows=new ArrayList<>();
 void show(List<DictionaryEntry> entries) { rows.clear(); for(DictionaryEntry entry:entries) for(DictionaryEntry.Meaning meaning:entry.meanings) { int limit=Math.min(meaning.definitions.size(),4); for(int i=0;i<limit;i++) { DictionaryEntry.Definition d=meaning.definitions.get(i); rows.add(new Row(entry.word,meaning.partOfSpeech,d.definition,d.example,join(d.synonyms.isEmpty()?meaning.synonyms:d.synonyms),entry.phoneticText(),entry.firstAudio())); } } notifyDataSetChanged(); }
 private static String join(List<String> words) { if(words==null||words.isEmpty()) return "No synonyms listed"; return String.join(", ",words.subList(0,Math.min(words.size(),8))); }
 @NonNull @Override public Holder onCreateViewHolder(@NonNull ViewGroup p,int t) { return new Holder(LayoutInflater.from(p.getContext()).inflate(R.layout.row_definition,p,false)); }
 @Override public void onBindViewHolder(@NonNull Holder h,int position) { Row r=rows.get(position); h.word.setText(r.word+(r.phonetic.isEmpty()?"":"  "+r.phonetic)); h.pos.setText(r.pos==null?"Meaning":r.pos); h.definition.setText(r.definition==null?"Definition unavailable":r.definition); h.example.setText(r.example==null?"":"“"+r.example+"”"); h.synonyms.setText("Synonyms · "+r.synonyms); h.audio.setVisibility(r.audio==null?View.GONE:View.VISIBLE); h.audio.setOnClickListener(v->{ MediaPlayer player=new MediaPlayer(); try { player.setDataSource(r.audio); player.setOnPreparedListener(MediaPlayer::start); player.setOnCompletionListener(mp->{mp.release();}); player.setOnErrorListener((mp,what,extra)->{mp.release();return true;}); player.prepareAsync(); } catch(Exception e) { player.release(); } }); }
 @Override public int getItemCount(){return rows.size();}
 private static final class Row { final String word,pos,definition,example,synonyms,phonetic,audio; Row(String w,String p,String d,String e,String s,String ph,String a){word=w;pos=p;definition=d;example=e;synonyms=s;phonetic=ph;audio=a;} }
 static final class Holder extends RecyclerView.ViewHolder { final TextView word,pos,definition,example,synonyms; final Button audio; Holder(View v){super(v);word=v.findViewById(R.id.word);pos=v.findViewById(R.id.partOfSpeech);definition=v.findViewById(R.id.definition);example=v.findViewById(R.id.example);synonyms=v.findViewById(R.id.synonyms);audio=v.findViewById(R.id.audio);} }
}
