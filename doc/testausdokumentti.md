# Testausdokumentti

## Yksikkötestit

Ohjelmalle on kirjoitettu automaattisia testejä JUnit-työkalulla. Testit keskittyvät algoritmin ytimen eli Huffman-puun testaamiseen. Testikattavuus ei ole kovin korkealla, sillä ajanpuutteen vuoksi en kirjoittanut testejä tiedostoja käsitteleville luokille.

## Suorityskykytestaus

Testasin ohjelman tehokkuutta eri kokoisilla tekstitiedostoilla.

Esimerkkinä 867184 tavun kokoisella englanninkielisellä tekstitiedostolla saadut arvot (dracula.txt, löytyy testdata-kansiosta):

- pakkaussuhde: 0,56
- pakkaukseen kulunut aika: 107 millisekunttia
- purkamiseen kulunut aika: 4,7 s

Purkamisen pullonkauula tulee vielä selvemmin näkyviin suuremmilla tekstitiedostoilla. 10 megan satunnaisesti generoidulla tekstitiedostolla tuloksena oli seuraavat arvot:

- pakkaussuhde 0.75
- pakkaukseen kulunut aika: 13 millisekuntia
- purkamiseen kulunuta aika: 60 sekuntia

