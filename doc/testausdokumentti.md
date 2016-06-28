# Testausdokumentti

## Yksikkötestit

Ohjelmalle on kirjoitettu automaattisia testejä JUnit-työkalulla. Testit keskittyvät algoritmin ytimen eli Huffman-puun testaamiseen. Testikattavuus ei ole kovin korkealla, sillä ajanpuutteen vuoksi en kirjoittanut testejä tiedostoja käsitteleville luokille.

## Suorityskykytestaus

Testasin ohjelman tehokkuutta eri kokoisilla tekstitiedostoilla.

Esimerkkinä 867184 tavun kokoisella englanninkielisellä tekstitiedostolla saadut arvot (dracula.txt, löytyy testdata-kansiosta):

- pakkaukseen kulunut aika: 107 millisekunttia
- pakkaussuhde: 0,56
- purkamiseen kulunut aika: 4,7 s
