# Simple phonetic algorithm benchmark #
A [phonetic algorithm](https://en.wikipedia.org/wiki/Phonetic_algorithm) is an algorithm that can determine the pronunciation of a word, which allows you to find similar words when indexed. For example the word *Kathy* has many similar pronunciation, such as *Kathi*, *Catie*, *Keti*, etc., which should match. Matching can be further improved by the use of [fuzzy matching](https://en.wikipedia.org/wiki/Fuzzy_matching_(computer-assisted_translation)) techniques to find approximate matching strings, in which for example *Will* and *William* would match.


Databases often have phonetic algorithm string functions built in. See for example [Soundex for MySQL](https://dev.mysql.com/doc/refman/8.0/en/string-functions.html#function_soundex), and also [multiple Metaphones and Levenshtein for PostgreSQL](https://www.postgresql.org/docs/9.1/fuzzystrmatch.html).

I was wondering myself how these algorithms differ in the quality of matches, but I couldn't find the results on the Internet that I was looking for. Then I noticed that [Apache Commons Codec](https://commons.apache.org/proper/commons-codec/) (a Java Library) has [a bunch of these algorithms](https://commons.apache.org/proper/commons-codec/apidocs/org/apache/commons/codec/language/package-summary.html#package.description) in a very easy to use API. For a programmer, this means fun! So I wrote a very simple benchmark.

## Tested phonetic algorithms ##
 * [Caverphone 1.0 and 2.0](https://en.wikipedia.org/wiki/Caverphone)
 * [Cologne phonetics](https://en.wikipedia.org/wiki/Cologne_phonetics)
 * [Daitch-Mokotoff Soundex](https://en.wikipedia.org/wiki/Daitch%E2%80%93Mokotoff_Soundex)
 * [Metaphone, Double Metaphone](https://en.wikipedia.org/wiki/Metaphone)
 * [Match rating approach](https://enacademic.com/dic.nsf/enwiki/11709505) developed by Western Airlines in 1977
 * [NYSIIS (New York State Identification and Intelligence System)](https://en.wikipedia.org/wiki/New_York_State_Identification_and_Intelligence_System)
 * [Soundex](https://en.wikipedia.org/wiki/Soundex)
 * [Refined Soundex](https://commons.apache.org/proper/commons-codec/apidocs/org/apache/commons/codec/language/RefinedSoundex.html)

 

## Benchmark test data and method ##
```kotlin
listOf(
    listOf("Katy anna", "Katie n", "Katie ana"),
    listOf("caty", "cati", "catti", "catie", "cathy", "cathi", "ketie", "kety", "keti", "kathy", "kathi", "kathie"),
    listOf("greatness", "Graetnes"),
    listOf("Smith", "Schmidt", "Smyth"),
    listOf("Catherine", "Katherine", "Katarina"),
    listOf("Teresa", "Theresa"),
    listOf("Johnathan", "Jonathan")
)
```
The test data could be a lot better, which may result in the benchmark scores.

Each data line list an group of similar words from which a simple matching score is calculated for each algorithm. The scores of each group are then summed for each algorithm, resulting in a final score for each algorithm.


## Benchmark results ##
### Detailed results ###
```
Encoder: Caverphone
- Katy anna	->	KTNA111111
- Katie n	->	KTN1111111
- Katie ana	->	KTNA111111
Name group similarity score: 0.6666666666666666

- caty	->	KTA1111111
- cati	->	KTA1111111
- catti	->	KTA1111111
- catie	->	KTA1111111
- cathy	->	KTA1111111
- cathi	->	KTA1111111
- ketie	->	KTA1111111
- kety	->	KTA1111111
- keti	->	KTA1111111
- kathy	->	KTA1111111
- kathi	->	KTA1111111
- kathie	->	KTA1111111
Name group similarity score: 1.0

- greatness	->	KRTNS11111
- Graetnes	->	KRTNS11111
Name group similarity score: 1.0

- Smith	->	SMT1111111
- Schmidt	->	SKMT111111
- Smyth	->	SMT1111111
Name group similarity score: 0.6666666666666666

- Catherine	->	KTRN111111
- Katherine	->	KTRN111111
- Katarina	->	KTRNA11111
Name group similarity score: 0.6666666666666666

- Teresa	->	TRSA111111
- Theresa	->	TRSA111111
Name group similarity score: 1.0

- Johnathan	->	YNTN111111
- Jonathan	->	YNTN111111
Name group similarity score: 1.0

---------
Encoder: Caverphone1
- Katy anna	->	KTYN11
- Katie n	->	KTN111
- Katie ana	->	KTN111
Name group similarity score: 0.6666666666666666

- caty	->	KT1111
- cati	->	KT1111
- catti	->	KT1111
- catie	->	KT1111
- cathy	->	KT1111
- cathi	->	KT1111
- ketie	->	KT1111
- kety	->	KT1111
- keti	->	KT1111
- kathy	->	KT1111
- kathi	->	KT1111
- kathie	->	KT1111
Name group similarity score: 1.0

- greatness	->	KRTNS1
- Graetnes	->	KRTNS1
Name group similarity score: 1.0

- Smith	->	SMT111
- Schmidt	->	SKMT11
- Smyth	->	SMT111
Name group similarity score: 0.6666666666666666

- Catherine	->	KTRN11
- Katherine	->	KTRN11
- Katarina	->	KTRN11
Name group similarity score: 1.0

- Teresa	->	TRS111
- Theresa	->	TRS111
Name group similarity score: 1.0

- Johnathan	->	YNTN11
- Jonathan	->	YNTN11
Name group similarity score: 1.0

---------
Encoder: Caverphone2
- Katy anna	->	KTNA111111
- Katie n	->	KTN1111111
- Katie ana	->	KTNA111111
Name group similarity score: 0.6666666666666666

- caty	->	KTA1111111
- cati	->	KTA1111111
- catti	->	KTA1111111
- catie	->	KTA1111111
- cathy	->	KTA1111111
- cathi	->	KTA1111111
- ketie	->	KTA1111111
- kety	->	KTA1111111
- keti	->	KTA1111111
- kathy	->	KTA1111111
- kathi	->	KTA1111111
- kathie	->	KTA1111111
Name group similarity score: 1.0

- greatness	->	KRTNS11111
- Graetnes	->	KRTNS11111
Name group similarity score: 1.0

- Smith	->	SMT1111111
- Schmidt	->	SKMT111111
- Smyth	->	SMT1111111
Name group similarity score: 0.6666666666666666

- Catherine	->	KTRN111111
- Katherine	->	KTRN111111
- Katarina	->	KTRNA11111
Name group similarity score: 0.6666666666666666

- Teresa	->	TRSA111111
- Theresa	->	TRSA111111
Name group similarity score: 1.0

- Johnathan	->	YNTN111111
- Jonathan	->	YNTN111111
Name group similarity score: 1.0

---------
Encoder: ColognePhonetic
- Katy anna	->	426
- Katie n	->	426
- Katie ana	->	426
Name group similarity score: 1.0

- caty	->	42
- cati	->	42
- catti	->	42
- catie	->	42
- cathy	->	42
- cathi	->	42
- ketie	->	42
- kety	->	42
- keti	->	42
- kathy	->	42
- kathi	->	42
- kathie	->	42
Name group similarity score: 1.0

- greatness	->	47268
- Graetnes	->	47268
Name group similarity score: 1.0

- Smith	->	862
- Schmidt	->	862
- Smyth	->	862
Name group similarity score: 1.0

- Catherine	->	4276
- Katherine	->	4276
- Katarina	->	4276
Name group similarity score: 1.0

- Teresa	->	278
- Theresa	->	278
Name group similarity score: 1.0

- Johnathan	->	0626
- Jonathan	->	0626
Name group similarity score: 1.0

---------
Encoder: DaitchMokotoffSoundex
- Katy anna	->	536000
- Katie n	->	536000
- Katie ana	->	536000
Name group similarity score: 1.0

- caty	->	430000
- cati	->	430000
- catti	->	430000
- catie	->	430000
- cathy	->	430000
- cathi	->	430000
- ketie	->	530000
- kety	->	530000
- keti	->	530000
- kathy	->	530000
- kathi	->	530000
- kathie	->	530000
Name group similarity score: 0.5

- greatness	->	593640
- Graetnes	->	593640
Name group similarity score: 1.0

- Smith	->	463000
- Schmidt	->	463000
- Smyth	->	463000
Name group similarity score: 1.0

- Catherine	->	439600
- Katherine	->	539600
- Katarina	->	539600
Name group similarity score: 0.6666666666666666

- Teresa	->	394000
- Theresa	->	394000
Name group similarity score: 1.0

- Johnathan	->	163600
- Jonathan	->	163600
Name group similarity score: 1.0

---------
Encoder: Metaphone
- Katy anna	->	KTN
- Katie n	->	KTN
- Katie ana	->	KTN
Name group similarity score: 1.0

- caty	->	KT
- cati	->	KT
- catti	->	KT
- catie	->	KT
- cathy	->	K0
- cathi	->	K0
- ketie	->	KT
- kety	->	KT
- keti	->	KT
- kathy	->	K0
- kathi	->	K0
- kathie	->	K0
Name group similarity score: 0.5833333333333334

- greatness	->	KRTN
- Graetnes	->	KRTN
Name group similarity score: 1.0

- Smith	->	SM0
- Schmidt	->	SKMT
- Smyth	->	SM0
Name group similarity score: 0.6666666666666666

- Catherine	->	K0RN
- Katherine	->	K0RN
- Katarina	->	KTRN
Name group similarity score: 0.6666666666666666

- Teresa	->	TRS
- Theresa	->	0RS
Name group similarity score: 0.5

- Johnathan	->	JN0N
- Jonathan	->	JN0N
Name group similarity score: 1.0

---------
Encoder: DoubleMetaphone
- Katy anna	->	KTN
- Katie n	->	KTN
- Katie ana	->	KTN
Name group similarity score: 1.0

- caty	->	KT
- cati	->	KT
- catti	->	KT
- catie	->	KT
- cathy	->	K0
- cathi	->	K0
- ketie	->	KT
- kety	->	KT
- keti	->	KT
- kathy	->	K0
- kathi	->	K0
- kathie	->	K0
Name group similarity score: 0.5833333333333334

- greatness	->	KRTN
- Graetnes	->	KRTN
Name group similarity score: 1.0

- Smith	->	SM0
- Schmidt	->	XMT
- Smyth	->	SM0
Name group similarity score: 0.6666666666666666

- Catherine	->	K0RN
- Katherine	->	K0RN
- Katarina	->	KTRN
Name group similarity score: 0.6666666666666666

- Teresa	->	TRS
- Theresa	->	0RS
Name group similarity score: 0.5

- Johnathan	->	JN0N
- Jonathan	->	JN0N
Name group similarity score: 1.0

---------
Encoder: MatchRatingApproachEncoder
- Katy anna	->	KTYN
- Katie n	->	KTN
- Katie ana	->	KTN
Name group similarity score: 0.6666666666666666

- caty	->	CTY
- cati	->	CT
- catti	->	CT
- catie	->	CT
- cathy	->	CTHY
- cathi	->	CTH
- ketie	->	KT
- kety	->	KTY
- keti	->	KT
- kathy	->	KTHY
- kathi	->	KTH
- kathie	->	KTH
Name group similarity score: 0.25

- greatness	->	GRTNS
- Graetnes	->	GRTNS
Name group similarity score: 1.0

- Smith	->	SMTH
- Schmidt	->	SCHMDT
- Smyth	->	SMYTH
Name group similarity score: 0.3333333333333333

- Catherine	->	CTHRN
- Katherine	->	KTHRN
- Katarina	->	KTRN
Name group similarity score: 0.3333333333333333

- Teresa	->	TRS
- Theresa	->	THRS
Name group similarity score: 0.5

- Johnathan	->	JHNTHN
- Jonathan	->	JNTHN
Name group similarity score: 0.5

---------
Encoder: Nysiis
- Katy anna	->	CATYAN
- Katie n	->	CATAN
- Katie ana	->	CATAN
Name group similarity score: 0.6666666666666666

- caty	->	CATY
- cati	->	CAT
- catti	->	CAT
- catie	->	CATY
- cathy	->	CATY
- cathi	->	CAT
- ketie	->	CATY
- kety	->	CATY
- keti	->	CAT
- kathy	->	CATY
- kathi	->	CAT
- kathie	->	CATY
Name group similarity score: 0.5833333333333334

- greatness	->	GRATN
- Graetnes	->	GRATN
Name group similarity score: 1.0

- Smith	->	SNAT
- Schmidt	->	SNAD
- Smyth	->	SNYT
Name group similarity score: 0.3333333333333333

- Catherine	->	CATARA
- Katherine	->	CATARA
- Katarina	->	CATARA
Name group similarity score: 1.0

- Teresa	->	TARAS
- Theresa	->	TARAS
Name group similarity score: 1.0

- Johnathan	->	JANATA
- Jonathan	->	JANATA
Name group similarity score: 1.0

---------
Encoder: Soundex
- Katy anna	->	K350
- Katie n	->	K350
- Katie ana	->	K350
Name group similarity score: 1.0

- caty	->	C300
- cati	->	C300
- catti	->	C300
- catie	->	C300
- cathy	->	C300
- cathi	->	C300
- ketie	->	K300
- kety	->	K300
- keti	->	K300
- kathy	->	K300
- kathi	->	K300
- kathie	->	K300
Name group similarity score: 0.5

- greatness	->	G635
- Graetnes	->	G635
Name group similarity score: 1.0

- Smith	->	S530
- Schmidt	->	S530
- Smyth	->	S530
Name group similarity score: 1.0

- Catherine	->	C365
- Katherine	->	K365
- Katarina	->	K365
Name group similarity score: 0.6666666666666666

- Teresa	->	T620
- Theresa	->	T620
Name group similarity score: 1.0

- Johnathan	->	J535
- Jonathan	->	J535
Name group similarity score: 1.0

---------
Encoder: RefinedSoundex
- Katy anna	->	K306080
- Katie n	->	K30608
- Katie ana	->	K306080
Name group similarity score: 0.6666666666666666

- caty	->	C3060
- cati	->	C3060
- catti	->	C3060
- catie	->	C3060
- cathy	->	C3060
- cathi	->	C3060
- ketie	->	K3060
- kety	->	K3060
- keti	->	K3060
- kathy	->	K3060
- kathi	->	K3060
- kathie	->	K3060
Name group similarity score: 0.5

- greatness	->	G4906803
- Graetnes	->	G4906803
Name group similarity score: 1.0

- Smith	->	S38060
- Schmidt	->	S30806
- Smyth	->	S38060
Name group similarity score: 0.6666666666666666

- Catherine	->	C30609080
- Katherine	->	K30609080
- Katarina	->	K30609080
Name group similarity score: 0.6666666666666666

- Teresa	->	T609030
- Theresa	->	T609030
Name group similarity score: 1.0

- Johnathan	->	J4080608
- Jonathan	->	J4080608
Name group similarity score: 1.0
```

### Final scores ###
```
Encoders ordered by score descending
Encoder:ColognePhonetic, score:7.0
Encoder:Caverphone1, score:6.333333333333333
Encoder:DaitchMokotoffSoundex, score:6.166666666666667
Encoder:Soundex, score:6.166666666666667
Encoder:Caverphone, score:6.0
Encoder:Caverphone2, score:6.0
Encoder:Nysiis, score:5.583333333333334
Encoder:RefinedSoundex, score:5.5
Encoder:Metaphone, score:5.416666666666666
Encoder:DoubleMetaphone, score:5.416666666666666
Encoder:MatchRatingApproachEncoder, score:3.5833333333333335
```
The higher the score, the better. So Cologne Phonetic is the winner in this benchmark with this data. (Different data may result in a different winner.)

## About source code ##
Code is written in [Kotlin](https://kotlinlang.org/), a Java based language.

## Further reading sources ##
 * [An Overview of Fuzzy Name Matching Techniques](https://www.rosette.com/blog/overview-fuzzy-name-matching-techniques/)
 * [Using Fuzzy Matching to Search by Sound with Python](https://www.informit.com/articles/article.aspx?p=1848528)
