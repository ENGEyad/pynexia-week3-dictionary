package com.pynexia.week3.dictionary;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
public interface DictionaryApi { @GET("api/v2/entries/en/{word}") Call<List<DictionaryEntry>> lookup(@Path(value="word", encoded=true) String word); }
