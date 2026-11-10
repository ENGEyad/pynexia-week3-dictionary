package com.pynexia.week3.dictionary;

import java.util.List;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public final class DictionaryRepository {
 public interface Callback { void complete(List<DictionaryEntry> entries, String error); }
 private final DictionaryApi api=new Retrofit.Builder().baseUrl("https://api.dictionaryapi.dev/").client(new okhttp3.OkHttpClient.Builder().connectTimeout(8,java.util.concurrent.TimeUnit.SECONDS).readTimeout(15,java.util.concurrent.TimeUnit.SECONDS).callTimeout(20,java.util.concurrent.TimeUnit.SECONDS).build()).addConverterFactory(GsonConverterFactory.create()).build().create(DictionaryApi.class);
 public void lookup(String word,Callback callback) { api.lookup(word.trim()).enqueue(new retrofit2.Callback<List<DictionaryEntry>>() {
  @Override public void onResponse(retrofit2.Call<List<DictionaryEntry>> call,retrofit2.Response<List<DictionaryEntry>> r) { if(r.isSuccessful()&&r.body()!=null&&!r.body().isEmpty()) callback.complete(r.body(),null); else callback.complete(null,r.code()==404?"No dictionary entry found. Check the spelling and try again.":"Dictionary service returned HTTP "+r.code()+". Try again shortly."); }
  @Override public void onFailure(retrofit2.Call<List<DictionaryEntry>> call,Throwable t) { callback.complete(null,"Could not reach the dictionary. Check your connection and try again."); }
 }); }
}
