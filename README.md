# Dictionary App

Search English vocabulary for meanings, parts of speech, pronunciation, audio and synonyms, with a recent search history.

Pynexia Android Development / Week 3.

[Hosted showcase](https://engeyad.github.io/pynexia-week3-dictionary/) · [PDF report](https://github.com/ENGEyad/pynexia-week3-dictionary/releases/latest/download/Dictionary-App-Report.pdf) · [APK](https://github.com/ENGEyad/pynexia-week3-dictionary/releases/latest/download/dictionary-debug.apk) · [Source ZIP](https://github.com/ENGEyad/pynexia-week3-dictionary/releases/latest/download/Dictionary-App.zip)

## Implementation

### Dictionary REST API

Retrofit fetches the selected word from Free Dictionary API and parses its JSON entry, phonetics and meanings.

### Useful word details

Definitions include example sentences, parts of speech and synonyms when available. Tap Play audio to hear returned pronunciation.

### Search history

Recent successful searches are kept on device and can be tapped to repeat a lookup.

## Build

JDK 17/21, Android SDK 35, Android 8.0/API 26 or newer. Initial dependency sync requires internet.

```powershell
.\gradlew.bat :dictionary:assembleDebug :dictionary:lintDebug
```

## Walkthrough

1. Enter an English word and tap Search.
2. Read the meanings, examples and synonyms.
3. Tap Play audio when a pronunciation file is available.
4. Select a recent search to look up the word again.

## Verification

Debug build passed. Automated tests were not run. Lint reported 0 errors and 21 warnings; see `verification/`. Pixel 8 emulator, Android 17/API 37. No physical-device or API 26 review.

## Scope

The dictionary API provides English entries. Definitions, examples, synonyms and audio are shown only when the API returns them. An internet connection is required for new lookups.

## References

- https://dictionaryapi.dev/

The hosted page displays screenshots and downloads; it is not an Android emulator.
