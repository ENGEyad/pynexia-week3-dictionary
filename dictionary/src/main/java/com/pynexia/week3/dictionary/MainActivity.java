package com.pynexia.week3.dictionary;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.activity.ComponentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
public final class MainActivity extends ComponentActivity {
 private final DictionaryRepository repository=new DictionaryRepository(); private EditText input; private TextView status; private LinearLayout history; private DefinitionAdapter adapter;
 @Override protected void onCreate(Bundle state){super.onCreate(state);setContentView(R.layout.activity_main);input=findViewById(R.id.wordInput);status=findViewById(R.id.status);history=findViewById(R.id.history);RecyclerView list=findViewById(R.id.definitions);list.setLayoutManager(new LinearLayoutManager(this));adapter=new DefinitionAdapter();list.setAdapter(adapter);findViewById(R.id.search).setOnClickListener(v->search());findViewById(R.id.retry).setOnClickListener(v->search());renderHistory(); if(state!=null&&state.getString("word")!=null){input.setText(state.getString("word"));search();}}
 private void search(){String word=input.getText().toString().trim();if(word.isEmpty()){input.setError("Enter a word to look up");return;}input.clearFocus();((android.view.inputmethod.InputMethodManager)getSystemService(INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(input.getWindowToken(),0);status.setText("Looking up “"+word+"”…");findViewById(R.id.searchProgress).setVisibility(View.VISIBLE);findViewById(R.id.retry).setVisibility(View.GONE);repository.lookup(word,(entries,error)->runOnUiThread(()->{findViewById(R.id.searchProgress).setVisibility(View.GONE);if(isFinishing())return;if(error!=null){status.setText(error);findViewById(R.id.retry).setVisibility(View.VISIBLE);adapter.show(new ArrayList<>());}else{status.setText(entries.get(0).word+" · "+entries.size()+" entry/entries");adapter.show(entries);saveHistory(word);}}));}
 private void saveHistory(String word){Set<String> old=new LinkedHashSet<>(getPreferences(0).getStringSet("history",new LinkedHashSet<>()));old.remove(word);old.add(word);while(old.size()>8)old.remove(old.iterator().next());getPreferences(0).edit().putStringSet("history",old).apply();renderHistory();}
 private void renderHistory(){history.removeAllViews();List<String> words=new ArrayList<>(getPreferences(0).getStringSet("history",new LinkedHashSet<>()));java.util.Collections.reverse(words);for(String word:words){Button chip=new Button(this);chip.setText(word);chip.setAllCaps(false);chip.setMinHeight(38);chip.setOnClickListener(v->{input.setText(word);search();});history.addView(chip);}}
 @Override protected void onSaveInstanceState(Bundle out){out.putString("word",input.getText().toString());super.onSaveInstanceState(out);}
}
